package org.duang.action.provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.Notification;
import org.duang.enums.NotificationStatus;
import org.duang.service.NotificationService;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————系统公告Action
 * @ClassName:  NotificationAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_notification")
public class NotificationAction extends BaseAction<Notification>{
	
	private NotificationService notificationService;
	@Resource
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	/**   
	 * 获取系统列表公告
	 * @Title: getSystemNotifys   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月5日 下午4:04:38
	 * @return: void      
	 * @throws   
	 */  
	public void getSystemNotifys(){
		boolean success = false;
		try {
			condsUtils.addProperties(true, "status");
			condsUtils.addValues(true, NotificationStatus.status2.getVal());
			condsUtils.addProperties(false, "order");
			condsUtils.addValues(false, Order.desc("publishTime"));
			//查询数据
			List<Notification> list = notificationService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
			success = true;
			jsonObject.put("result", fillDataObjectList(list));
			if (list == null || list.size() == 0) {
				msg = "没有查到符合条件的数据";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("NotificationAction——getSystemNotifys方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("NotificationAction——getSystemNotifys方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 获取系统弹窗公告
	 * @Title: getSystemWindowNotifys   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月5日 下午4:06:30
	 * @return: void      
	 * @throws   
	 */  
	public void getSystemWindowNotifys(){
		boolean success = false;
		try {
			condsUtils.addProperties(true, "status");
			condsUtils.addValues(true, NotificationStatus.status1.getVal());
			condsUtils.addProperties(false, "order");
			condsUtils.addValues(false, Order.desc("publishTime"));
			//查询数据
			List<Notification> list = notificationService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
			success = true;
			jsonObject.put("result", fillDataObjectList(list));
			if (list == null || list.size() == 0) {
				msg = "未查到符合条件的数据";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("NotificationAction——getSystemWindowNotifys方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("NotificationAction——getSystemWindowNotifys方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 封装对象集合
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月7日 上午10:26:06
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<Notification> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (Notification notification : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", notification.getId());
				map.put("title", notification.getTitle());
				map.put("content", notification.getContent());
				map.put("publishTime", DateUtils.date2Str(notification.getPublishTime(),"yyyy-MM-dd HH:mm:ss"));
				map.put("beginDate", DateUtils.date2Str(notification.getBeginDate(),"yyyy-MM-dd HH:mm:ss"));
				map.put("endDate", DateUtils.date2Str(notification.getEndDate(),"yyyy-MM-dd HH:mm:ss"));
				map.put("readed", notification.getReaded());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装通知信息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装通知信息错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
}
