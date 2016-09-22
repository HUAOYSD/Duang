package org.duang.action.provider;
import java.util.ArrayList;
import java.util.Date;
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
import org.duang.common.system.MemberCollection;
import org.duang.entity.MemberInfo;
import org.duang.entity.Message;
import org.duang.enums.If;
import org.duang.service.MessageService;
import org.duang.util.DataUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————消息Action
 * @ClassName:  MessageAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_message")
public class MessageAction extends BaseAction<Message>{
	
	private MessageService messageService;
	@Resource(name = "messageserviceimpl")
	public void setService(MessageService messageService) {
		this.messageService = messageService;
	}

	/**   
	 * 查询消息列表
	 * @Title: queryMessage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午4:44:10
	 * @return: void      
	 * @throws   
	 */  
	public void queryMessage(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String count = getRequest().getParameter("count");
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(MemberCollection.getInstance().getMainField(token))){
				int queryCount = 20;
				if(DataUtils.notEmpty(count)){
					queryCount = Integer.parseInt(count);
				}
				getPageUtil().setCountRecords(queryCount);
				List<Message> messages = messageService.queryAllEntity(getPageUtil(), Order.desc("time"));
				if (messages == null || messages.size()==0) {
					msg = "未查到消息";
				}
				jsonObject.put("result", fillDataObjectArray(messages));
				success = true;
			}else{
				msg = "参数不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MessageAction——queryMessage方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MessageAction——queryMessage方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 查询单条消息记录详情
	 * @Title: findMessage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午4:57:41
	 * @return: void      
	 * @throws   
	 */  
	public void findMessage(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String p_id = getRequest().getParameter("p_id");
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(MemberCollection.getInstance().getMainField(token))){
				Message message = messageService.findById(p_id);
				if (message == null) {
					msg = "未找到消息";
				}
				jsonObject.put("result", fillDataObject(message));
				success = true;
			}else{
				msg = "参数不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MessageAction——findMessage方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MessageAction——findMessage方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 增加消息记录
	 * @Title: insertMessage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午4:44:43
	 * @return: void      
	 * @throws   
	 */  
	public void insertMessage(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String p_senderid = getRequest().getParameter("p_senderid");
			String p_receiver = getRequest().getParameter("p_receiver");
			String title = getRequest().getParameter("title");
			String content = getRequest().getParameter("content");
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(MemberCollection.getInstance().getMainField(token)) && 
				DataUtils.notEmpty(p_senderid) && DataUtils.notEmpty(p_receiver)&& DataUtils.notEmpty(title)&& DataUtils.notEmpty(content)){
				Message message = new Message();
				message.setId(DataUtils.randomUUID());
				message.setReaded(If.If0.getVal());
				message.setTime(new Date());
				message.setTitle(title);
				message.setContent(content);
				
				MemberInfo p_reMemberInfo = new MemberInfo();
				p_reMemberInfo.setId(p_receiver);
				message.setMemberInfoByReceiver(p_reMemberInfo);
				MemberInfo p_seMemberInfo = new MemberInfo();
				p_seMemberInfo.setId(p_senderid);
				success = messageService.saveEntity(message);
				if(!success){
					msg = "接收人无效";
				}
			}else{
				msg = "参数不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MessageAction——insertMessage方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MessageAction——insertMessage方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 标记全部已读
	 * @Title: updateAllReaded   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午4:45:10
	 * @return: void      
	 * @throws   
	 */  
	public void updateAllReaded(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(MemberCollection.getInstance().getMainField(token))){
				String sql = "update message  set readed = 1";
				success = messageService.executeSql(sql);
				if (!success) {
					msg = "未找到消息";
				}
			}else{
				msg = "参数不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MessageAction——updateAllReaded方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MessageAction——updateAllReaded方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 标记已读
	 * @Title: updateReaded   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午4:45:36
	 * @return: void      
	 * @throws   
	 */  
	public void updateReaded(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String p_id = getRequest().getParameter("p_id");
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(MemberCollection.getInstance().getMainField(token))){
				
				Message message = messageService.findById(p_id);
				message.setReaded(If.If1.getVal());
				success = messageService.updateEntity(message);
				if (!success) {
					msg = "未找到消息";
				}
			}else{
				msg = "参数不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MessageAction——updateReaded方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MessageAction——updateReaded方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 封装参数
	 * @Title: fillDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月12日 上午10:16:33
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(List<Message> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Message message = list.get(i);
				//客户的基本信息
				map.put("id", message.getId());
				map.put("content", message.getContent());
				map.put("title", message.getTitle());
				map.put("time", message.getTime());
				map.put("read", message.getReaded());
				MemberInfo memberinfo_receiver = message.getMemberInfoByReceiver();
				map.put("receiver", memberinfo_receiver.getRealName());
				MemberInfo memberinfo_send = message.getMemberInfoBySender();
				map.put("sender", memberinfo_send.getRealName());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装消息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装消息错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 封装参数
	 * @Title: fillDataObject   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月12日 上午10:16:33
	 * @return: Map<String,Object>      
	 * @throws
	 */
	private Map<String, Object> fillDataObject(Message message) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				map.put("id", message.getId());
				map.put("content", message.getContent());
				map.put("title", message.getTitle());
				map.put("time", message.getTime());
				map.put("read", message.getReaded());
				MemberInfo memberinfo_receiver = message.getMemberInfoByReceiver();
				map.put("receiver", memberinfo_receiver.getRealName());
				MemberInfo memberinfo_send = message.getMemberInfoBySender();
				map.put("sender", memberinfo_send.getRealName());
				listMap.add(map);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装消息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装消息错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return map;
	}
}
