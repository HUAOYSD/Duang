package org.duang.common.system;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.duang.entity.MemberInfo;
import org.duang.entity.SysUser;


/**
 * Session工具类
 * ClassName: SessionTools 
 * date: 2016年7月15日 下午3:15:53 
 * @author 白攀
 */
public class SessionTools {

	public static final String SYSUSER="sysUser"; //系统用户
	public static final String MEMBER="member"; //会员
	public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";//验证码

	/** 
	 * 获取httpsession
	 * @Title: getSession 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return Map<String,Object>    返回类型 
	 * @author 白攀
	 * @date 2016-7-16 下午2:07:29
	 */ 
	public static Map<String,Object> getSession(){
		Map<String,Object> sessionMap = ServletActionContext.getContext().getSession();
		return sessionMap;
	}


	/** 
	 * 设置session值
	 * @Title: setSessionValue 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param key
	 * @param value
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-16 下午2:07:53
	 */ 
	public static boolean setSessionValue(String key,Object value){
		try {
			Map<String,Object> sessionMap = ServletActionContext.getContext().getSession();
			sessionMap.put(key, value);
			return true;	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取值
	 * @Title: getSessionValue 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param key
	 * @return Object    返回类型 
	 * @author 白攀
	 * @date 2013-12-19 上午10:28:44
	 */
	public static Object getSessionValue(String key){
		return ServletActionContext.getContext().getSession().get(key);
	}


	/** 
	 * 移出session
	 * @Title: removeSession 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param key
	 * @return boolean    返回类型 
	 * @author 白攀
	 * @date 2016-7-16 下午2:13:15
	 */ 
	public static boolean removeSession(String key){
		try {
			ServletActionContext.getContext().getSession().remove(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 获取当前登陆的系统用户名
	 * @Title: getSessionSysUserName 
	 * @return
	 * @return String    返回类型 
	 * @author 白攀
	 * @date 2016-7-16 上午10:04:43
	 */
	public static String getSessionSysUserName(){
		SysUser sysUser= getSessionSysUser();
		if(sysUser==null){
			return null;
		}
		return sysUser.getName();
	}


	/**
	 * 获取系统当前的登陆的系统用户
	 * @Title: getSessionSysUser 
	 * @return SysUser  返回类型 
	 * @author 白攀
	 * @date 2016-7-16 上午9:58:08
	 */
	public static SysUser getSessionSysUser(){
		Map<String,Object> sessionMap = ServletActionContext.getContext().getSession();
		return (SysUser) sessionMap.get(SYSUSER);
	}


	/**
	 * 设置当前登陆的系统用户
	 * @Title: setSessionSysUser 
	 * @param sysUser
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-16 上午10:01:08
	 */
	public static void setSessionSysUser(SysUser sysUser){
		Map<String,Object> sessionMap = ServletActionContext.getContext().getSession();
		sessionMap.put(SYSUSER, sysUser);
	}


	/**
	 * 移除sessionSysUser
	 * @Title: removeSessionSysUser 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-16 上午8:45:24
	 */
	public static void removeSessionSysUser(){
		Map<String,Object> sessionMap = ServletActionContext.getContext().getSession();
		sessionMap.remove(SYSUSER);
	}


	/**   
	 * 获取当前登陆的会员用户名
	 * @Title: getSessionMemberName   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年9月5日 上午10:42:55
	 * @return: String      
	 * @throws   
	 */  
	public static String getSessionMemberName(){
		MemberInfo memberInfo= getSessionMember();
		if(memberInfo==null){
			return null;
		}
		return memberInfo.getNickname();
	}


	/**   
	 * 获取系统当前的登陆的会员用户
	 * @Title: getSessionMember   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年9月5日 上午10:43:58
	 * @return: MemberInfo      
	 * @throws   
	 */  
	public static MemberInfo getSessionMember(){
		Map<String,Object> sessionMap = ServletActionContext.getContext().getSession();
		return (MemberInfo) sessionMap.get(MEMBER);
	}


	/**   
	 * 设置当前登陆的会员用户
	 * @Title: setSessionMember   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberinfo  
	 * @author 白攀    
	 * @date 2016年9月5日 上午10:45:42
	 * @return: void      
	 * @throws   
	 */  
	public static void setSessionMember(MemberInfo memberinfo){
		Map<String,Object> sessionMap = ServletActionContext.getContext().getSession();
		sessionMap.put(MEMBER, memberinfo);
	}


	/**   
	 * 移除SessionMember
	 * @Title: removeSessionMember   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 上午10:47:10
	 * @return: void      
	 * @throws   
	 */  
	public static void removeSessionMember(){
		Map<String,Object> sessionMap = ServletActionContext.getContext().getSession();
		sessionMap.remove(MEMBER);
	}
}
