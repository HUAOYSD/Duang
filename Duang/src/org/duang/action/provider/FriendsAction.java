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
import org.duang.common.system.MemberCollection;
import org.duang.entity.Friends;
import org.duang.entity.MemberInfo;
import org.duang.service.BillInvestService;
import org.duang.service.FriendsService;
import org.duang.service.MemberInfoService;
import org.duang.util.DataUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**   
 * 接口开发————财友Action
 * @ClassName:  FriendsAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月10日 上午10:54:16      
 */ 
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_friends")
public class FriendsAction extends BaseAction<Friends>{

	private FriendsService service;
	private BillInvestService billInvestService;
	@Resource
	public void setService(FriendsService service) {
		this.service = service;
	}
	@Resource
	public void setBillInvestService(BillInvestService billInvestService) {
		this.billInvestService = billInvestService;
	}

	/**
	 * 客户基本信息
	 */
	private MemberInfoService sysMemberInfoService;

	@Resource(name = "sysmemberinfoserviceimpl")
	public void setService(MemberInfoService sysMemberInfoService) {
		this.sysMemberInfoService = sysMemberInfoService;
	}
	/**   
	 * 查询我关注的
	 * @Title: queryMyStars   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */  
	public void queryMyStars(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))) {
				List<Friends> list = service.queryEntity("memberInfoBySelf.id", id, null, Order.desc("optTime"));
				if (DataUtils.notEmpty(list)) {
					for (Friends friends : list) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", friends.getId());
						map.put("together", friends.getTogether());
						MemberInfo memberInfo = friends.getMemberInfoByTarget();
						map.put("friendid", memberInfo.getId());
						map.put("friendname", memberInfo.getNickname());
						listMap.add(map);
					}
				}else {
					msg = "无记录";
				}
				success = true;
				jsonObject.put("result", listMap);
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 查询关注我的
	 * @Title: queryStarMes   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */  
	public void queryStarMes(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))) {
				List<Friends> list = service.queryEntity("memberInfoByTarget.id", id, null, Order.desc("optTime"));
				if (DataUtils.notEmpty(list)) {
					for (Friends friends : list) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", friends.getId());
						map.put("together", friends.getTogether());
						MemberInfo memberInfo = friends.getMemberInfoByTarget();
						map.put("friendid", memberInfo.getId());
						map.put("friendname", memberInfo.getNickname());
						listMap.add(map);
					}
				}else {
					msg = "无记录";
				}
				success = true;
				jsonObject.put("result", listMap);
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 获取我的二维码
	 * @Title: getMyQR   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */  
	public void getMyQR(){
		
	}


	/**   
	 * 获取和我差不多投资额度的会员
	 * 投资额度和该会员投资额度相差不过5万的前20人
	 * @Title: getFairlysMember   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */  
	public void getFairlysMember(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))) {
				List<?> list = billInvestService.findCostInfo(id);
				if (list!=null && list.size()>0 && list.get(0) instanceof Object[]) {
					int tm = DataUtils.str2int(((Object[])list.get(0))[0].toString());
					double tn = DataUtils.str2double(((Object[])list.get(0))[1].toString(), 2);
					list = billInvestService.queryFairlysMemberCostInfo(id, tm);
					if (list != null) {
						for (Object object : list) {
							if (object instanceof Object[]) {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("investCount", ((Object[])object)[0]);
								map.put("invertMoney", ((Object[])object)[1]);
								map.put("memberid", ((Object[])object)[2]);
								map.put("membernickname", ((Object[])object)[3]);
								map.put("membername", ((Object[])object)[4]);
								listMap.add(map);
							}
						}
					}
					jsonObject.put("tn", tn);
					jsonObject.put("tm", tm);
				}else {
					msg = "您未有投资记录";
				}
				success = true;
				jsonObject.put("result", listMap);
			}else{
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 添加财友
	 * @Title: addFriends   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */ 
	public void addFriends(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String targetMemberid = getRequest().getParameter("targetMemberid");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))) {
				if (DataUtils.notEmpty(targetMemberid)) {
					if (service.addFriend(id, targetMemberid)) {
						success = true;
					}else {
						msg = "关注失败";
					}
				}else {
					msg = "关注用户无效";
				}
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}



	/**   
	 * 取消关注财友
	 * @Title: removeFriends   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月12日 上午10:14:33
	 * @return: void      
	 * @throws   
	 */  
	public void removeFriends(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String targetMemberid = getRequest().getParameter("targetMemberid");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))) {
				if (DataUtils.notEmpty(targetMemberid)) {
					if (service.cancelFriend(id, targetMemberid)) {
						success = true;
					}else {
						msg = "操作失败";
					}
				}else {
					msg = "操作失败";
				}
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	
	/**   
	 * 获取好友累计收益排行
	 * @Title: querytops   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年10月9日 下午4:46:41
	 * @return: void      
	 * @throws   
	 */  
	public void querytops(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))) {
				String sql = "select im.total_income,mi.real_name,mi.user_img from friends fri LEFT JOIN invest_member im on im.memberinfo_id=fri.target "+
							 " LEFT JOIN member_info mi on mi.id=im.memberinfo_id "+
							 " where fri.self='"+id+"' order by im.total_income desc";
				List<?> list = service.queryBySQL(sql, null, null, false);
				List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
				if(list==null || list.size()==0){
					msg="目前还没有好友";
				}else {
					for(int i=0;i<list.size();i++){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("total_income",  DataUtils.str2double((((Object[])list.get(i))[0])+"",2));
						map.put("real_name", ((Object[])list.get(i))[1]);
						map.put("user_img", ((Object[])list.get(i))[2]);
						listMap.add(map);
					}
				}
				success = true;
				jsonObject.put("result", listMap);
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("FriendsAction——querytops方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——querytops方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

}
