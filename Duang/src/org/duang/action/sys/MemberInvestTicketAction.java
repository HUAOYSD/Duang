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
	public String gotoBindInfoList(){
		return "MemberInvestTicketList";
	}
	
	public void queryAllMemberInvestTicket() {
		try {
			List<MemberInvestTicket> list = memberInvestTicketService.queryAllEntity(null);
			int count = memberInvestTicketService.count();
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
			//封装参数
			if (DataUtils.notEmpty(entity.getMemberInfo().getRealName())) {
				condsUtils.addProperties(false, "memberInfo.realName");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getRealName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getMemberInfo().getLoginName())) {
				condsUtils.addProperties(false, "memberInfo.loginName");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getLoginName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getInvestTicket().getName())) {
				condsUtils.addProperties(false, "investTicket.name");
				condsUtils.concatValue(new String[] {entity.getInvestTicket().getName(), "like" });
			}
			//查询数据
			List<MemberInvestTicket> list = memberInvestTicketService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = memberInvestTicketService.count(condsUtils.getPropertys(), condsUtils.getValues());
			//封装json
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				//fillDataObjectArray方法用于重新组合数据集，让其能够符合页面展示
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
			LoggerUtils.error("消息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("消息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
}
