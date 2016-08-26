package org.duang.action.sys;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.Notification;
import org.duang.service.NotificationService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 通知 Action
 * @ClassName:  NotificationAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月19日 上午10:15:17
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "notification")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/notification/notificationList.jsp"),
			@Result(name = "add", type = "dispatcher", location = "WEB-INF/page/sys/notification/addNotification.jsp"),
			@Result(name = "edit", type = "dispatcher", location = "WEB-INF/page/sys/notification/addNotification.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class NotificationAction extends BaseAction<Notification> {
	private static final long serialVersionUID = 1L;
	
	private NotificationService notificationService;
	@Resource(name = "notificationserviceimpl")
	public void setService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	/**
	 * 页面跳转 ---跳转到列表页面
	 * @Title: gotoList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月19日 上午10:17:18
	 * @return: String      
	 * @throws
	 */
	public String gotoList(){
		return "list";
	}
	
	/**
	 * 页面跳转 ---跳转到添加页面
	 * @Title: gotoAdd   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月19日 上午10:17:45
	 * @return: String      
	 * @throws
	 */
	public String gotoAdd(){
		return "add";
	}
	
	
	/**
	 * 跳转到编辑页面
	 * @Title: gotoUpdateNotification   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月19日 上午10:18:38
	 * @return: String      
	 * @throws
	 */
	public String gotoUpdate(){
		String id = getRequest().getParameter("id");
		try{
			getRequest().setAttribute("id", id);
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("通知ACTION跳转到修改页面错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("通知ACTION跳转到修改页面错误："+e.getLocalizedMessage(), this.getClass());
		}
		return "edit";
	}
	
	/**
	 * 获取所有信息
	 * @Title: queryAllNotification   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月19日 下午4:53:21
	 * @return: void      
	 * @throws
	 */
	public void queryAllNotification() {
		try {
			List<Notification> list = notificationService.queryAllEntity(getPageUtil(),Order.asc("publishTime"));
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("通知ACTION查询所有信息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("通知ACTION查询所有信息错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 根据id获取对象
	 * @Title: getNotificationById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月19日 上午10:26:23
	 * @return: void      
	 * @throws
	 */
	public void getNotificationById() {
		try {
			entity = notificationService.findById(entity.getId());
			jsonObject.put("id", entity.getId());
			jsonObject.put("title", entity.getTitle());
			jsonObject.put("status", entity.getStatus());
			jsonObject.put("publishTime", DateUtils.date2Str(entity.getPublishTime()));
			jsonObject.put("content", entity.getContent());
			jsonObject.put("beginDate", DateUtils.date2Str(entity.getBeginDate()));
			jsonObject.put("endDate", DateUtils.date2Str(entity.getEndDate()));
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("通知ACTION根据Id获取对象错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("通知ACTION根据Id获取对象错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 封装对象集合
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月19日 上午10:26:06
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<Notification> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (Notification Notification : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", Notification.getId());
				map.put("title", Notification.getTitle());
				map.put("status", Notification.getStatus());
				map.put("publishTime", DateUtils.date2Str(Notification.getPublishTime()));
				map.put("content", Notification.getContent());
				map.put("beginDate", DateUtils.date2Str(Notification.getBeginDate()));
				map.put("endDate", DateUtils.date2Str(Notification.getEndDate()));
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装通知信息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装通知信息错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 根据参数查询通知信息
	 * @Title: queryByParameter   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月19日 上午10:31:25
	 * @return: void      
	 * @throws
	 */
	public void queryByParameter() {
		try {
			//封装参数
			if (DataUtils.notEmpty(entity.getTitle())) {
				condsUtils.addProperties(false, "title");
				condsUtils.concatValue(new String[] {entity.getTitle(), "like" });
			}
			if (DataUtils.notEmpty(getRequest().getParameter("publishTime"))) {
				condsUtils.addProperties(false, "publishTime");
				condsUtils.concatValue(new Object[] {DateUtils.str2Date(getRequest().getParameter("publishTime")+" 00:00:00","yyyy-MM-dd hh:mm:ss"),"gt" });
			}
			if (DataUtils.notEmpty(getRequest().getParameter("endTime"))) {
				condsUtils.concat("endDate", new Object[]{DateUtils.str2Date(getRequest().getParameter("endTime")+" 59:59:59", "yyyy-MM-dd hh:mm:ss"), "lt"});
			}
			if (DataUtils.notEmpty(getRequest().getParameter("startTime"))) {
				condsUtils.concat("beginDate", new Object[]{DateUtils.str2Date(getRequest().getParameter("startTime")+" 00:00:00", "yyyy-MM-dd hh:mm:ss"), "gt"});
			}
			//查询数据
			List<Notification> list = notificationService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			//封装json
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				//fillDataObjectArray方法用于重新组合数据集，让其能够符合页面展示
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("根据条件查询通知ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("根据条件查询通知ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			//输出json
			printJsonResult();
		}
	}
	
	
	/**
	 * 保存
	 * @Title: saveNotification   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午3:46:59
	 * @return: void      
	 * @throws
	 */
	public void saveNotification() {
		if (entity != null) {
			try {
				boolean issuccess = false;
				//编辑
				if(DataUtils.notEmpty(entity.getId())){
					issuccess = notificationService.updateEntity(entity);	
				}else{
					entity.setId(DataUtils.randomUUID());
					issuccess = notificationService.saveEntity(entity);
				}
				if (issuccess) {
					print(true,"添加通知成功");
				} else {
					print(false,"添加通知失败，请联系管理员");
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("通知ACTION保存错误：" + e.getMessage(), this.getClass());
				LoggerUtils.error("通知ACTION保存错误：" + e.getLocalizedMessage(), this.getClass());
			}
		}
	}
	
	/**
	 * 返回信息到前台
	 * @Title: print   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param msg  
	 * @author LiYonghui    
	 * @date 2016年8月17日 上午10:10:25
	 * @return: void      
	 * @throws
	 */
	private void print(boolean result,String msg){
		jsonObject.put("result", result);
		jsonObject.put("msg", msg);
		printJsonResult();
	}
	
}
