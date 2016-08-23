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
import org.duang.entity.Friends;
import org.duang.entity.MemberInfo;
import org.duang.entity.Message;
import org.duang.service.FriendsService;
import org.duang.service.MessageService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 好友Action
 * @ClassName:  FriendsAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月23日 上午11:25:36
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "friends")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/friends/friends.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class FriendsAction extends BaseAction<Friends> {
	private static final long serialVersionUID = 1L;
	
	private FriendsService friendsService;
	@Resource(name = "messageserviceimpl")
	public void setService(FriendsService friendsService) {
		this.friendsService = friendsService;
	}

	/**
	 * 页面跳转 ---列表页面
	 * @Title: gotolist   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月23日 上午11:27:34
	 * @return: String      
	 * @throws
	 */
	public String gotolist(){
		return "list";
	}
	
	/**
	 * 查询所有
	 * @Title: queryAll   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月23日 下午3:49:21
	 * @return: void      
	 * @throws
	 */
	public void queryAll() {
		try {
			List<Friends> list = friendsService.queryAllEntity(Order.asc("optTime"));
			int count = friendsService.count();
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", count);
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("好友ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("好友ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
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
	 * @date 2016年8月23日 下午2:53:00
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<Friends> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (Friends friends : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", friends.getId());
				map.put("optTime", DateUtils.date2Str(friends.getOptTime()));
				map.put("together", friends.getTogether());
				map.put("selfLoginName", friends.getMemberInfoBySelf().getLoginName());
				map.put("selfRealName", friends.getMemberInfoBySelf().getRealName());
				map.put("targetLoginName", friends.getMemberInfoByTarget().getLoginName());
				map.put("targetRealName", friends.getMemberInfoByTarget().getRealName());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("好友封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("好友封装错误：" + e.getLocalizedMessage(), this.getClass());
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
	 * @date 2016年8月23日 下午2:53:00
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(@SuppressWarnings("rawtypes") List list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0;i<list.size();i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] array = (Object[]) list.get(i);
				MemberInfo self = (MemberInfo) array[0];
				MemberInfo target = (MemberInfo) array[1];
				Friends friends = (Friends)array[2];
				//客户的基本信息
				map.put("id", friends.getId());
				map.put("optTime", DateUtils.date2Str(friends.getOptTime()));
				map.put("together", friends.getTogether());
				map.put("selfLoginName", self.getLoginName());
				map.put("selfRealName", self.getRealName());
				map.put("targetLoginName", target.getLoginName());
				map.put("targetRealName", target.getRealName());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("好友封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("好友封装错误：" + e.getLocalizedMessage(), this.getClass());
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
				condsUtils.concat("time", new Object[]{DateUtils.str2Date(getRequest().getParameter("startTime")+" 00:00:00", "yyyy-MM-dd hh:mm:ss"), DateUtils.str2Date(getRequest().getParameter("endTime")+" 59:59:59", "yyyy-MM-dd hh:mm:ss"), "between"});
			}
			//查询数据
			@SuppressWarnings("rawtypes")
			List list = messageService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = messageService.count(condsUtils.getPropertys(), condsUtils.getValues());
			//封装json
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				//fillDataObjectArray方法用于重新组合数据集，让其能够符合页面展示
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", count);
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("好友ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("好友ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
}
