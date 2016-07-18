package org.duang.common.interceptor;

import org.apache.struts2.ServletActionContext;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.SessionTools;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**   
 * Session拦截器
 * @ClassName:  SessionInterceptor   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年7月17日 上午10:32:35      
 */  
public class SessionInterceptor extends MethodFilterInterceptor {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;

	/**   
	 * 做登录拦截的地方
	 * <p>Title: doIntercept</p>   
	 * <p>Description: </p>  
	 * @author 白攀
	 * @date 2016年7月17日 上午10:32:57
	 * @param arg0
	 * @return
	 * @throws Exception   
	 * @see com.opensymphony.xwork2.interceptor.MethodFilterInterceptor#doIntercept(com.opensymphony.xwork2.ActionInvocation)   
	 */  
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		//SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		LoggerUtils.info("进入了拦截器");
		if (SessionTools.getSessionSysUser() == null) {
			ServletActionContext.getRequest().setAttribute("msg", "您还没有登录或登录已超时，请重新登录，3秒后将跳转到登录页面！<br><a href='javascript:alert('自个儿跳');'>如不能自动跳转请点击此处</a>");
			return ResultPath.NOSESSION;
		}
		return arg0.invoke();
	}

}
