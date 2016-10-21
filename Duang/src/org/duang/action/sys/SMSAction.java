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
import org.duang.entity.SMS;
import org.duang.service.SMSService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 短信 action
 * @ClassName:  SMSAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年9月19日 上午10:50:51
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "sms")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "showSMS", type = "dispatcher", location = "WEB-INF/page/sys/sms/smsList.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class SMSAction extends BaseAction<SMS> {
	private static final long serialVersionUID = 1L;
	
	private SMSService SMSService;
	@Resource(name = "smsserviceimpl")
	public void setService(SMSService SMSService) {
		this.SMSService = SMSService;
	}
	/**
	 * 展示详细信息
	 * @Title: showSMS   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月19日 下午3:02:50
	 * @return: String      
	 * @throws
	 */
	public String showSMS() {
		return "showSMS";
	}
	/**
	 * @Title: query   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月18日 下午5:02:54
	 * @return: void      
	 * @throws
	 */
	public void query() {
		try {
			List<SMS> list = new ArrayList<SMS>();
			condsUtils.addProperties(true, "order");
			condsUtils.addValues(true, Order.desc("createTime"));
			if(entity != null && DataUtils.notEmpty(entity.getPhone())){
				condsUtils.addProperties(false, "phone");
				condsUtils.concatValue(new String[] { entity.getPhone(), "like" });
			}
			if(entity != null && DataUtils.notEmpty(entity.getContent())){
				condsUtils.addProperties(false, "content");
				condsUtils.concatValue(new String[] { entity.getContent(), "like" });
			}
			if(entity != null && (entity.getState()==0 || entity.getState()==1)){
				condsUtils.addProperties(false, "state");
				condsUtils.concatValue(entity.getState());
			}
			String startTime = getRequest().getParameter("startTime");
			String endTime = getRequest().getParameter("endTime");
			if (DataUtils.notEmpty(startTime) && DataUtils.notEmpty(endTime)) {
				condsUtils.concat("createTime", new Object[]{DateUtils.str2Date(startTime+" 00:00:00", "yyyy-MM-dd HH:mm:ss"), DateUtils.str2Date(endTime+" 59:59:59", "yyyy-MM-dd HH:mm:ss"), "between"});
			}
			list = SMSService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("合同ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("合同ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 封装集合
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月19日 下午5:06:44
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<SMS> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (SMS sms : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", sms.getId());
				map.put("createTime", DateUtils.date2Str(sms.getCreateTime()));
				map.put("state", sms.getState());
				map.put("content", sms.getContent());
				map.put("mesNum", sms.getMesNum());
				map.put("money", sms.getMoney());
				map.put("phone", sms.getPhone());
				
				map.put("platform", sms.getPlatform());
				map.put("price", sms.getPrice());
				
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("短信封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("短信封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
}
