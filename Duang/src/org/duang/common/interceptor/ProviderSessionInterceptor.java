package org.duang.common.interceptor;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.util.DateUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

  
/**   
 * 接口端使用的用户登录验证
 * @ClassName:  ProviderSessionInterceptor   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:39:58      
 */  
public class ProviderSessionInterceptor extends MethodFilterInterceptor {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;

	
	/**   
	 * 该方法不适用于接口开发
	 * 登录拦截
	 * <p>Title: doIntercept</p>   
	 * <p>Description: </p>  
	 * @author 白攀
	 * @date 2016年9月5日 上午10:40:53
	 * @param arg0
	 * @return
	 * @throws Exception   
	 * @see com.opensymphony.xwork2.interceptor.MethodFilterInterceptor#doIntercept(com.opensymphony.xwork2.ActionInvocation)   
	 */  
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		//SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		LoggerUtils.info(DateUtils.date2Str(new Date())+" 进入了Sys拦截器", this.getClass());
		if (org.duang.common.system.SessionTools.getSessionMember() == null) {
			ServletActionContext.getRequest().setAttribute("msg", "您未登录");
			return ResultPath.NOSESSION;
		}
		return arg0.invoke();
	}
	

}
