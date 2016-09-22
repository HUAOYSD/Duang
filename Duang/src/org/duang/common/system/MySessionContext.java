package org.duang.common.system;


import java.util.HashMap;
import javax.servlet.http.HttpSession;

/**
 * 自定义Session上下文
 * ClassName: MySessionContext 
 * date: 2016年7月15日 下午3:15:00 
 * @author 5y
 */
public class MySessionContext {  
	
	private static MySessionContext instance;  
	private HashMap<String, HttpSession> mymap;  

	private MySessionContext() {  
		mymap = new HashMap<String, HttpSession>();  
	}  
	
	public static MySessionContext getInstance() {  
		if (instance == null) {  
			instance = new MySessionContext();  
		}  
		return instance;  
	}  

	public synchronized void addSession(HttpSession session) {  
		if (session != null) {  
			mymap.put(session.getId(), session);  
		}  
	}  

	public synchronized void delSession(HttpSession session) {  
		if (session != null) {  
			mymap.remove(session.getId());  
		}  
	}  

	public synchronized HttpSession getSession(String session_id) {  
		if (session_id == null)  
			return null;  
		return (HttpSession) mymap.get(session_id);  
	}  

}  