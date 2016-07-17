package org.duang.common.system;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session监听器
 * ClassName: SessionListener 
 * date: 2016年7月15日 下午3:13:11 
 * @author 白攀
 */
public class SessionListener implements HttpSessionListener {  
  
    public static Map<String,HttpSession> userMap = new HashMap<String,HttpSession>();  
      
    private MySessionContext myc = MySessionContext.getInstance();  
  
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {  
        myc.addSession(httpSessionEvent.getSession());  
    }  
  
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {  
        HttpSession session = httpSessionEvent.getSession();  
        myc.delSession(session);  
    }  
  
}  