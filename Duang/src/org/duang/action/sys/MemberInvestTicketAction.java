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
import org.duang.entity.InvestTicket;
import org.duang.entity.MemberInfo;
import org.duang.entity.MemberInvestTicket;
import org.duang.service.MemberInvestTicketService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 我的理财券Action
 * @ClassName:  MemberInvestTicketAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月18日 下午1:59:34
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "memberinvestticket")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "memberInvestTicketList", type = "dispatcher", location = "WEB-INF/page/sys/memberinvestticket/memberInvestTicketList.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class MemberInvestTicketAction extends BaseAction<MemberInvestTicket> {
	private static final long serialVersionUID = 1L;
	
	private MemberInvestTicketService memberInvestTicketService;
	@Resource(name = "memberinvestticketserviceimpl")
	public void setService(MemberInvestTicketService memberInvestTicketService) {
		this.memberInvestTicketService = memberInvestTicketService;
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
	public String gotoList(){
		return "memberInvestTicketList";
	}
	
	public void queryAllMemberInvestTicket() {
		try {
			List<MemberInvestTicket> list = memberInvestTicketService.queryAllEntity(getPageUtil(),null);
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
	 * @date 2016年8月18日 下午2:52:40
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<MemberInvestTicket> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (MemberInvestTicket memberInvestTicket : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", memberInvestTicket.getId());
				map.put("isUse", memberInvestTicket.getIsUse());
				map.put("resourceStyle", memberInvestTicket.getResourceStyle());
				
				map.put("loginName", memberInvestTicket.getMemberInfo().getLoginName());
				map.put("realName", memberInvestTicket.getMemberInfo().getRealName());
				map.put("nickname", memberInvestTicket.getMemberInfo().getNickname());
				map.put("email", memberInvestTicket.getMemberInfo().getEmail());
				map.put("sex", memberInvestTicket.getMemberInfo().getSex());
				map.put("phone", memberInvestTicket.getMemberInfo().getPhone());
				
				//理财券
				map.put("ticketName", memberInvestTicket.getInvestTicket().getName());
				map.put("remark", memberInvestTicket.getInvestTicket().getRemark());
				map.put("describes", memberInvestTicket.getInvestTicket().getDescribes());
				map.put("money", memberInvestTicket.getInvestTicket().getMoney());
				map.put("beginTime",DateUtils.date2Str(memberInvestTicket.getInvestTicket().getBeginTime()));
				map.put("endTime",DateUtils.date2Str(memberInvestTicket.getInvestTicket().getEndTime()));
				map.put("createTime",DateUtils.date2Str(memberInvestTicket.getInvestTicket().getCreateTime()));
				map.put("minMoney", memberInvestTicket.getInvestTicket().getMinMoney());
				map.put("productIds", memberInvestTicket.getInvestTicket().getProductIds());
				map.put("state", memberInvestTicket.getInvestTicket().getState());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("用户理财券封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("用户理财券封装错误：" + e.getLocalizedMessage(), this.getClass());
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
	 * @date 2016年8月19日 下午2:52:40
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(@SuppressWarnings("rawtypes") List list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (int i=0;i<list.size();i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				Object[] array = (Object[]) list.get(i);
				InvestTicket ticket = (InvestTicket) array[0];
				MemberInfo memberInfo = (MemberInfo) array[1];
				MemberInvestTicket memberInvestTicket = (MemberInvestTicket)array[2];
				
				
				map.put("id", memberInvestTicket.getId());
				map.put("isUse", memberInvestTicket.getIsUse());
				map.put("resourceStyle", memberInvestTicket.getResourceStyle());
				
				//用户信息
				map.put("loginName", memberInfo.getLoginName());
				map.put("realName", memberInfo.getRealName());
				map.put("nickname", memberInfo.getNickname());
				map.put("email", memberInfo.getEmail());
				map.put("sex", memberInfo.getSex());
				map.put("phone", memberInfo.getPhone());
				
				//理财券
				map.put("ticketName", ticket.getName());
				map.put("remark", ticket.getRemark());
				map.put("describes", ticket.getDescribes());
				map.put("money", ticket.getMoney());
				map.put("beginTime",DateUtils.date2Str(ticket.getBeginTime()));
				map.put("endTime",DateUtils.date2Str(ticket.getEndTime()));
				map.put("createTime",DateUtils.date2Str(ticket.getCreateTime()));
				map.put("minMoney", ticket.getMinMoney());
				map.put("productIds", ticket.getProductIds());
				map.put("state", ticket.getState());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("用户理财券封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("用户理财券封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 根据参数查询
	 * @Title: queryByParameter   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月18日 下午2:52:11
	 * @return: void      
	 * @throws
	 */
	public void queryByParameter() {
		try {
			condsUtils.addProperties(true, "memberInfo");
			condsUtils.concatValue(new String[] { "memberin", "as" });
			if (DataUtils.notEmpty(entity.getMemberInfo().getRealName())) {
				condsUtils.addProperties(false, "memberin.realName");
				condsUtils.concatValue(new String[] {entity.getMemberInfo().getRealName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getMemberInfo().getLoginName())) {
				condsUtils.addProperties(false, "memberin.loginName");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getLoginName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getMemberInfo().getPhone())) {
				condsUtils.addProperties(false, "memberin.phone");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getPhone(), "like" });
			}
			condsUtils.addProperties(false, "investTicket");
			condsUtils.concatValue(new String[] { "ticket", "as" });
			if (DataUtils.notEmpty(entity.getInvestTicket().getName())) {
				condsUtils.addProperties(false, "ticket.name");
				condsUtils.concatValue(new String[] {entity.getInvestTicket().getName(), "like" });
			}
			if (DataUtils.notEmpty(getRequest().getParameter("endTime"))) {
				condsUtils.concat("ticket.endTime", new Object[]{DateUtils.str2Date(getRequest().getParameter("endTime")+" 59:59:59", "yyyy-MM-dd HH:mm:ss"), "lt"});
			}
			if (DataUtils.notEmpty(getRequest().getParameter("startTime"))) {
				condsUtils.concat("ticket.beginTime", new Object[]{DateUtils.str2Date(getRequest().getParameter("startTime")+" 00:00:00", "yyyy-MM-dd HH:mm:ss"), "gt"});
			}
			//查询数据
			@SuppressWarnings("rawtypes")
			List list = memberInvestTicketService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
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
