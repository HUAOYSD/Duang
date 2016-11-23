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
import org.duang.enums.UploadFile;
import org.duang.enums.billinvest.BillStatus;
import org.duang.enums.billinvest.UseType;
import org.duang.service.BillInvestService;
import org.duang.service.FriendsService;
import org.duang.service.MemberInfoService;
import org.duang.util.DES;
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
	//添加关注
	private static final int ADD_ATTENTION=1;
	//已经关注
	private static final int YET_ATTENTION=2;
	//相互关注
	private static final int EACH_OTHER_ATTENTION=3;
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
						//只需要展示我关注的就行。进行取消关注
						map.put("together", friends.getTogether()==ADD_ATTENTION?YET_ATTENTION:EACH_OTHER_ATTENTION);
						MemberInfo memberInfo = friends.getMemberInfoByTarget();
						map.put("friendid", memberInfo.getId());
						map.put("friendname", memberInfo.getNickname());
						map.put("friendImg", UploadFile.HEAD.appPath()+entity.getId()+"/head/"+ memberInfo.getUserImg());
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
						MemberInfo memberInfo = friends.getMemberInfoBySelf();
						map.put("friendid", memberInfo.getId());
						map.put("friendname", memberInfo.getNickname());
						map.put("friendImg", UploadFile.HEAD.appPath()+entity.getId()+"/head/"+ memberInfo.getUserImg());
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
					if(((Object[])list.get(0))[0] != null && ((Object[])list.get(0))[1]!=null){
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
									map.put("userImg", UploadFile.HEAD.appPath()+entity.getId()+"/head/"+ ((Object[])object)[5]);
									listMap.add(map);
								}
							}
						}
						jsonObject.put("tn", tn);
						jsonObject.put("tm", tm);
					}
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
			String targetMemberid = DES.decryptDES(getRequest().getParameter("targetMemberid"));
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(targetMemberid) && 
					DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))) {
				//判断是否已经关注
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("memberInfoBySelf.id", id);
				map.put("memberInfoByTarget.id", targetMemberid);
				Friends friends = service.findEntity(map);
				if (friends == null) {
					if (service.addFriend(id, targetMemberid)) {
						success = true;
					}else {
						msg = "关注失败";
					}
				}else {
					success = true;
					msg = "已经关注，可以在我的关注中找到他";
				}
			}else {
				msg = "关注失败，电话号码为空！";
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
	 * 通过二维码添加好友。
	 * @Title: addFriendsByQR   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */ 
	public void addFriendsByQR(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String phone =  getRequest().getParameter("phone");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(phone) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))) {
				//根据phone查询用户
				MemberInfo memberInfo = sysMemberInfoService.findEntity("phone", phone);
				if(memberInfo != null){
					if(!memberInfo.getId().equals(id)){
						//判断是否已经关注
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("memberInfoBySelf.id", id);
						map.put("memberInfoByTarget.id", memberInfo.getId());
						Friends friends = service.findEntity(map);
						if (friends == null) {
							if (service.addFriend(id, memberInfo.getId())) {
								success = true;
							}else {
								msg = "关注失败";
							}
						}else {
							success = true;
							msg = "已经关注，可以在 我的关注 中找到他";
						}
					}else{
						msg="自己不能加自己好友哦";
					}
				}else{
					msg="关注用户无效";
				}
			}else {
				msg = "参数无效";
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
			LoggerUtils.error("FriendsAction——removeFriends方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——removeFriends方法错误：" + e.getLocalizedMessage(), this.getClass());
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
				String sql = "select im.total_income,mi.real_name,mi.user_img,mi.nickname,mi.id from friends fri LEFT JOIN invest_member im on im.memberinfo_id=fri.target "+
							 " LEFT JOIN member_info mi on mi.id=im.memberinfo_id "+
							 " where fri.self='"+id+"' order by im.total_income desc";
				List<?> list = service.queryBySQL(sql, null, null, false);
				
				//查询自己的
				String selfsql="select total_income,real_name,user_img,nickname,member_info.id from invest_member LEFT JOIN member_info on "
								+ "	invest_member.memberinfo_id=member_info.id WHERE member_info.id='"+id+"'";
				List<?> selfList = service.queryBySQL(selfsql, null, null, false);
				if(list==null || list.size()==0){
					msg="目前还没有好友";
				}else {
					double selftIncome = DataUtils.str2double((((Object[])selfList.get(0))[0])+"",2);
					//判断自己是否已经加进排行榜里了
					boolean isSelf = false;
					for(int i=0;i<list.size();i++){
						Map<String, Object> map = new HashMap<String, Object>();
						double incomeSum = DataUtils.str2double((((Object[])list.get(i))[0])+"",2);
						if(incomeSum<=selftIncome && !isSelf){
							isSelf = true;
							map.put("total_income",  selftIncome);
							map.put("real_name", ((Object[])selfList.get(0))[1]);
							map.put("user_img", UploadFile.HEAD.appPath()+entity.getId()+"/head/"+ ((Object[])selfList.get(0))[2]);
							map.put("nickName", ((Object[])selfList.get(0))[3]);
							map.put("userId", ((Object[])selfList.get(0))[4]);
							map.put("index", i+1);
						}else {
							map.put("total_income",  incomeSum);
							map.put("real_name", ((Object[])list.get(i))[1]);
							map.put("user_img", UploadFile.HEAD.appPath()+entity.getId()+"/head/"+ ((Object[])list.get(i))[2]);
							map.put("nickName", ((Object[])list.get(i))[3]);
							map.put("userId", ((Object[])list.get(i))[4]);
							map.put("index", i+1);
						}
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
	
	
	/**
	 * 获取好友累计推荐收益排行
	 */
	public void querytopsByRecommend(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))) {
				StringBuffer sb = new StringBuffer();
				sb.append("SELECT SUM(MONEY) AS MYMONEY, MEMBER_INFO.REAL_NAME, MEMBER_INFO.NICKNAME,MEMBER_INFO.USER_IMG FROM FRIENDS ");
				sb.append("INNER JOIN BILL_INVEST ON BILL_INVEST.MEMBER_INFO = FRIENDS.SELF ");
				sb.append("INNER JOIN MEMBER_INFO ON MEMBER_INFO.ID = BILL_INVEST.MEMBER_INFO ");
				sb.append("WHERE SELF = '"+id+"' AND BILL_INVEST.USE_TYPE = "+UseType.UT8.getVal()+" AND BILL_INVEST.STATUS = "+BillStatus.BS2.getVal());
				sb.append(" ORDER BY MYMONEY LIMIT 0,1000");
				List<?> list = service.queryBySQL(sb.toString(), null, null, false);
				List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
				if(list==null || list.size()==0){
					msg="目前还没有好友或者好友无推荐收益";
				}else {
					for(int i=0;i<list.size();i++){
						if(list.get(i)!=null){
							Map<String, Object> map = new HashMap<String, Object>();
							String totalIncome = String.valueOf(((Object[])list.get(i))[0]);
							if(DataUtils.notEmpty(totalIncome)){
								map.put("total_income",  DataUtils.str2double(totalIncome+"",2));
							}else{
								map.put("total_income",  0);
							}
							map.put("real_name", ((Object[])list.get(i))[1]);
							map.put("nick_name", ((Object[])list.get(i))[2]);
							map.put("user_img", ((Object[])list.get(i))[3]);
							listMap.add(map);
						}
					}
				}
				success = true;
				jsonObject.put("result", listMap);
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("FriendsAction——querytopsByRecommend方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——querytopsByRecommend方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
}
