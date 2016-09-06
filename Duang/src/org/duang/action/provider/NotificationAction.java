package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.Notification;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————系统公告Action
 * @ClassName:  NotificationAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_notification")
public class NotificationAction extends BaseAction<Notification>{


	/**   
	 * 获取系统列表公告
	 * @Title: getSystemNotifys   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午4:04:38
	 * @return: void      
	 * @throws   
	 */  
	public void getSystemNotifys(){
	}
	
	
	/**   
	 * 获取系统弹窗公告
	 * @Title: getSystemWindowNotifys   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午4:06:30
	 * @return: void      
	 * @throws   
	 */  
	public void getSystemWindowNotifys(){
	}
	
}
