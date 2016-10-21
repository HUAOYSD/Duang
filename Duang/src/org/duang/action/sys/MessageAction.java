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
import org.duang.entity.MemberInfo;
import org.duang.entity.Message;
import org.duang.service.MessageService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 消息Action
 * @ClassName:  MessageAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月18日 上午11:25:36
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "message")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "messageList", type = "dispatcher", location = "WEB-INF/page/sys/message/messageList.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class MessageAction extends BaseAction<Message> {
	private static final long serialVersionUID = 1L;
	
	private MessageService messageService;
	@Resource(name = "messageserviceimpl")
	public void setService(MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * 页面跳转 ---消息列表页面
	 * @Title: gotoBindInfoList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月18日 上午11:27:34
	 * @return: String      
	 * @throws
	 */
	public String gotoMessageList(){
		return "messageList";
	}
	
	public void queryAllMessage() {
		try {
			List<Message> list = messageService.queryAllEntity(getPageUtil(),Order.asc("time"));
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
			LoggerUtils.error("消息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("消息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
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
	 * @date 2016年8月18日 下午2:53:00
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<Message> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (Message message : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", message.getId());
				map.put("time", DateUtils.date2Str(message.getTime()));
				map.put("title", message.getTitle());
				map.put("content", message.getContent());
				map.put("readed", message.getReaded());
				map.put("receiverRealName", message.getMemberInfoByReceiver().getRealName());
				map.put("receiverLoginName", message.getMemberInfoByReceiver().getLoginName());
				map.put("senderRealName", message.getMemberInfoBySender().getRealName());
				map.put("senderLoginName", message.getMemberInfoBySender().getLoginName());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("消息封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("消息封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 封装集合
	 * @Title: fillDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月18日 下午2:53:00
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(@SuppressWarnings("rawtypes") List list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0;i<list.size();i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] array = (Object[]) list.get(i);
				MemberInfo receiver = (MemberInfo) array[0];
				MemberInfo sender = (MemberInfo) array[1];
				Message message = (Message)array[2];
				//客户的基本信息
				map.put("id", message.getId());
				map.put("time", DateUtils.date2Str(message.getTime()));
				map.put("title", message.getTitle());
				map.put("content", message.getContent());
				map.put("readed", message.getReaded());
				map.put("receiverRealName", receiver.getRealName());
				map.put("receiverLoginName", receiver.getLoginName());
				map.put("senderRealName", sender.getRealName());
				map.put("senderLoginName", sender.getLoginName());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("消息封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("消息封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 根据参数查询
	 * @Title: queryByParameter   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月18日 下午2:53:19
	 * @return: void      
	 * @throws
	 */
	public void queryByParameter() {
		try {
			
			condsUtils.addProperties(true, "memberInfoByReceiver");
			condsUtils.concatValue(new String[] { "receiver", "as" });
			if (DataUtils.notEmpty(entity.getMemberInfoByReceiver().getRealName())) {
				condsUtils.addProperties(false, "receiver.realName");
				condsUtils.concatValue(new String[] {entity.getMemberInfoByReceiver().getRealName(), "like" });
			}
			condsUtils.addProperties(false, "memberInfoBySender");
			condsUtils.concatValue(new String[] { "sender", "as" });
			if (DataUtils.notEmpty(entity.getMemberInfoBySender().getRealName())) {
				condsUtils.addProperties(false, "sender.realName");
				condsUtils.concatValue(new String[] {entity.getMemberInfoBySender().getRealName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getTitle())) {
				condsUtils.addProperties(false, "title");
				condsUtils.concatValue(new String[] {entity.getTitle(), "like" });
			}
			if (DataUtils.notEmpty(getRequest().getParameter("startTime")) && DataUtils.notEmpty(getRequest().getParameter("endTime"))) {
				condsUtils.concat("time", new Object[]{DateUtils.str2Date(getRequest().getParameter("startTime")+" 00:00:00", "yyyy-MM-dd HH:mm:ss"), DateUtils.str2Date(getRequest().getParameter("endTime")+" 59:59:59", "yyyy-MM-dd HH:mm:ss"), "between"});
			}
			//查询数据
			@SuppressWarnings("rawtypes")
			List list = messageService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			//封装json
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				//fillDataObjectArray方法用于重新组合数据集，让其能够符合页面展示
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("消息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("消息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
}
