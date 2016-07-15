package org.duang.common.interceptor;

import org.apache.struts2.ServletActionContext;

/**
 * Session拦截器
 * @ClassName: SessionInterceptor 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 白攀
 * @date 2014-6-4 上午09:37:23 
 */
public class SessionInterceptor {
//extends MethodFilterInterceptor{

//	@Override
//	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
//		//SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
//		SysUser user = SessionTools.getSessionSysUser();
//		if (user == null) {
//			ServletActionContext.getRequest().setAttribute("msg", "您还没有登录或登录已超时，请重新登录，2秒后将跳转到登录页面！<br><a href='javascript:void(0)' onclick='skip()'>如不能自动跳转请点击此处</a>");
//			return "noSession";
//		}
//		return actionInvocation.invoke();
//	}

}
