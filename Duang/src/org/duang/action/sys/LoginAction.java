package org.duang.action.sys;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.entity.SysUser;
import org.duang.util.DrawImage;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 用户登录控制类 
 * @ClassName: LoginAction 
 * @Description: 用户登录 
 * @author 李永辉
 * @date 2016-7-20 下午3:54:35 
 */
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="login")
@ParentPackage("sys")
@Results(value={
		@Result(name=com.opensymphony.xwork2.Action.LOGIN, type="dispatcher",location="login.jsp"),
		@Result(name=ResultPath.RETURN_TO_HOME, type="dispatcher",location="page/home/home.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher",location="error.jsp")
})
public class LoginAction extends BaseAction<SysUser>{
	private static final long serialVersionUID = 1L;
	
	/**   
	 * 登录
	 * @Title: login   
	 * @Description: 登录初始化   
	 * @param:   
	 * @author 李永辉    
	 * @date 2016年7月20日 下午3:59:44
	 * @return: String 返回路径      
	 * @throws   
	 */  
	public String login(){
		return LOGIN;
	}
	
	/**   
	 * @Title: loginSuccess   
	 * @Description: 登录，用户信息校验   
	 * @param:   
	 * @author 李永辉    
	 * @date 2016年7月20日 下午3:59:44
	 * @return: String 返回路径      
	 * @throws   
	 */  
	public String loginSuccess(){
		return ResultPath.RETURN_TO_HOME;
	}
	
	public String checkCode(){
		try {
			DrawImage draw = new DrawImage();
			draw.drawImage(getRequest(), getResponse(null), "nl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
