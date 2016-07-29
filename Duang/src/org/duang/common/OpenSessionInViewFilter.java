package org.duang.common;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.orm.hibernate4.support.AsyncRequestInterceptor;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class OpenSessionInViewFilter extends OncePerRequestFilter{
	public static final String DEFAULT_SESSION_FACTORY_BEAN_NAME = "sessionFactory";
	private String sessionFactoryBeanName = "sessionFactory";

	public void setSessionFactoryBeanName(String sessionFactoryBeanName){
		this.sessionFactoryBeanName = sessionFactoryBeanName;
	}

	protected String getSessionFactoryBeanName(){
		return this.sessionFactoryBeanName;
	}

	protected boolean shouldNotFilterAsyncDispatch(){
		return false;
	}

	protected boolean shouldNotFilterErrorDispatch(){
		return false;
	}


	protected SessionFactory lookupSessionFactory(HttpServletRequest request){
		return lookupSessionFactory();
	}

	protected SessionFactory lookupSessionFactory(){
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Using SessionFactory '" + getSessionFactoryBeanName() + "' for OpenSessionInViewFilter");
		}
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		return (SessionFactory)wac.getBean(getSessionFactoryBeanName(), SessionFactory.class);
	}

	protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		try{
			Session session = sessionFactory.openSession();
			session.setFlushMode(FlushMode.MANUAL);
			return session;
		}catch (HibernateException ex) {
			throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
		}
	}

	private boolean applySessionBindingInterceptor(WebAsyncManager asyncManager, String key) {
		if (asyncManager.getCallableInterceptor(key) == null) {
			return false;
		}
		((AsyncRequestInterceptor)asyncManager.getCallableInterceptor(key)).bindSession();
		return true;
	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
		SessionFactory sessionFactory = lookupSessionFactory(request);
		boolean participate = false;
		WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
		String key = getAlreadyFilteredAttributeName();
		if (TransactionSynchronizationManager.hasResource(sessionFactory)){
			participate = true;
		}else {
			boolean isFirstRequest = !isAsyncDispatch(request);
			if ((isFirstRequest) || (!applySessionBindingInterceptor(asyncManager, key))) {
				this.logger.debug("Opening Hibernate Session in OpenSessionInViewFilter");
				Session session = openSession(sessionFactory);
				SessionHolder sessionHolder = new SessionHolder(session);
				TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);

				AsyncRequestInterceptor interceptor = new AsyncRequestInterceptor(sessionFactory, sessionHolder);
				asyncManager.registerCallableInterceptor(key, interceptor);
				asyncManager.registerDeferredResultInterceptor(key, interceptor);
			}
		}
		try{
			filterChain.doFilter(request, response);
		}finally{
			if (!participate) {
				SessionHolder sessionHolder1 = (SessionHolder)TransactionSynchronizationManager.unbindResource(sessionFactory);
				if (!isAsyncStarted(request)) {
					this.logger.debug("Closing Hibernate Session in OpenSessionInViewFilter");
					SessionFactoryUtils.closeSession(sessionHolder1.getSession());
				}
			}
		}
	}

}