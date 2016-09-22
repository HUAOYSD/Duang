package org.duang.common.system;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session监听器
 * ClassName: SessionListener 
 * date: 2016年7月15日 下午3:13:11 
 * @author 5y
 */
public class SessionListener implements HttpSessionListener {  
      
    private MySessionContext myc = MySessionContext.getInstance();  
  
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {  
        myc.addSession(httpSessionEvent.getSession());  
    }  
  
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {  
         HttpSession session = httpSessionEvent.getSession();  
        myc.delSession(session);  
    }  
  
}  