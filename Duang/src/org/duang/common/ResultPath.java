package org.duang.common;


/**
 * 通用返回路径
 * ClassName: ResultStr 
 * date: 2016年7月15日 下午5:16:27 
 * @author 白攀
 */
public class ResultPath {

	public static final String PREFIXX = "/WEB-INF/page";
	public static final String NOSESSION = "nosession";
	public static final String INFO = "info";
	
	/**
	 * 跳转到登录页面
	 */
	public static final String RETURN_TO_LOGIN="login";
	
	/**
	 * 跳转到主页面
	 */
	public static final String RETURN_TO_HOME="home";
	
	/**
	 * 跳转到客户管理页面
	 */
	public static final String RETURN_TO_CUSTOMERINFO="customerInfo";
	public static final String SUCCESS="success";
	public static final String ERROR="error";
}

