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
import org.duang.service.MemberInfoService;
import org.duang.service.MessageService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
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

	private MemberInfoService memberInfoService;
	@Resource
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
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
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(MemberCollection.getInstance(token,memberInfoService).getMainField(token))){
				int queryCount = 20;
				if(DataUtils.notEmpty(count)){
					queryCount = Integer.parseInt(count);
				}
				getPageUtil().setCountRecords(queryCount);
				condsUtils.addProperties(false, "state");
				condsUtils.addValues(false, If.If1.getVal());
				condsUtils.addProperties(false, "order");
				condsUtils.addValues(false, Order.desc("time"));
				List<Message> messages = messageService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
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
	 * 查询接受者消息列表 
	 * @Title: queryMessageByReceiver   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月14日 上午10:54:47
	 * @return: void      
	 * @throws
	 */
	public void queryMessageByReceiver(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = "";
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,memberInfoService).getMainField(token))){
				
				condsUtils.addProperties(true, "memberInfoByReceiver","memberInfoByReceiverAlias.id", "state", "order");
				condsUtils.addValues(true, new Object[]{"memberInfoByReceiverAlias","as"},id, 1, Order.desc("time"));
				List<Message> messages = messageService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
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
			LoggerUtils.error("MessageAction——queryMessageByReceiver方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MessageAction——queryMessageByReceiver方法错误：" + e.getLocalizedMessage(), this.getClass());
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
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(MemberCollection.getInstance(token,memberInfoService).getMainField(token))){
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
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(MemberCollection.getInstance(token,memberInfoService).getMainField(token)) && 
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
			//获取修改的信息id
			String p_ids = getRequest().getParameter("p_ids");
			//判断参数是否为空
			if(DataUtils.notEmpty(p_ids)){
				String ids[] = p_ids.split(",");
				//定义拼接id
				StringBuffer idSB = new StringBuffer("");
				for(int i=0;i<ids.length;i++){
					idSB.append("'"+ids[i]+"'");
					if(i<ids.length-1){
						idSB.append(",");
					}
				}
				String  sql = "update message  set readed=1 WHERE id in ("+idSB.toString()+")";
				success = messageService.executeSql(sql);
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
	 * 全部标记为已读
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月14日 上午11:42:56
	 * @return: void      
	 * @throws
	 */
	public void updateReadedAll(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			//判断参数是否为空
			String id ="";
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,memberInfoService).getMainField(token))){
				String  sql = "update message  set readed=1 WHERE  receiver = '"+id+"'";
				success = messageService.executeSql(sql);
				if (!success) {
					msg = "未找到消息";
				}
			}else{
				msg = "参数不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MessageAction——updateAll方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MessageAction——updateAll方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**
	 * 删除  
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月14日 上午11:42:56
	 * @return: void      
	 * @throws
	 */
	public void delete(){
		boolean success = false;
		try {
			String p_ids = getRequest().getParameter("p_id");
			//判断参数是否为空
			if(DataUtils.notEmpty(p_ids)){
				String ids[] = p_ids.split(",");
				//定义拼接id
				StringBuffer idSB = new StringBuffer("");
				for(int i=0;i<ids.length;i++){
					idSB.append("'"+ids[i]+"'");
					if(i<ids.length-1){
						idSB.append(",");
					}
				}
				String  sql = "update message  set state=0 WHERE id in ("+idSB.toString()+")";
				success = messageService.executeSql(sql);
				if (!success) {
					msg = "未找到消息";
				}
			}else{
				msg = "参数不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MessageAction——delete方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MessageAction——delete方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 删除登录人的所有消息
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月14日 上午11:42:56
	 * @return: void      
	 * @throws
	 */
	public void deleteAll(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			//判断参数是否为空
			String id ="";
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,memberInfoService).getMainField(token))){
				String  sql = "update message  set state=0 WHERE  receiver = '"+id+"'";
				success = messageService.executeSql(sql);
				if (!success) {
					msg = "未找到消息";
				}
			}else{
				msg = "参数不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MessageAction——deleteAll方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MessageAction——deleteAll方法错误：" + e.getLocalizedMessage(), this.getClass());
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
				map.put("time",DateUtils.date2Str(message.getTime(),"yyyy-MM-dd HH:mm:ss"));
				map.put("readed", message.getReaded());
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
				map.put("time", DateUtils.date2Str(message.getTime(),"yyyy-MM-dd HH:mm:ss"));
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
