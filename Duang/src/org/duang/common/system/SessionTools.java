package org.duang.common.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Session工具类
 * ClassName: SessionTools 
 * date: 2016年7月15日 下午3:15:53 
 * @author 白攀
 */
public class SessionTools {

	public static final String SYSUSER="sysUser"; //系统用户
	public static final String MEMBER="member"; //会员


	/** 
	 * 获取httpsession
	 * @Title: getSession 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return HttpSession    返回类型 
	 * @author 白攀
	 * @date 2014-9-1 下午2:07:29
	 */ 
	public static HttpSession getSession(){
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
		return request.getSession();
	}


	/** 
	 * 设置session值
	 * @Title: setSessionValue 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param key
	 * @param value
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2014-9-1 下午2:07:53
	 */ 
	public static boolean setSessionValue(String key,Object value){
		try {
			getSession().setAttribute(key, value);
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
		return getSession().getAttribute(key);
	}


	/** 
	 * 移出session
	 * @Title: removeSession 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param key
	 * @return boolean    返回类型 
	 * @author 白攀
	 * @date 2014-9-1 下午2:13:15
	 */ 
	public static boolean removeSession(String key){
		try {
			getSession().removeAttribute(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



}
