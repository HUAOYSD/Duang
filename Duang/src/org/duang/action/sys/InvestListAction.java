package org.duang.action.sys;

import java.net.URLDecoder;
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
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.CustomerManager;
import org.duang.entity.InvestList;
import org.duang.entity.MemberInfo;
import org.duang.enums.Platform;
import org.duang.enums.invest.Status;
import org.duang.enums.invest.UseTicket;
import org.duang.entity.Scale;
import org.duang.service.InvestListService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**   
 * 理财记录Action
 * @ClassName:  InvestListAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年8月12日 下午4:32:30      
 */  
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "investlist")
@ParentPackage("sys")
@Results(value = { 
		@Result(name = ResultPath.LIST, type = "dispatcher", location = "WEB-INF/page/sys/investlist/investlist.jsp"),
		@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") 
})
public class InvestListAction extends BaseAction<InvestList> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private InvestListService service;

	@Resource()
	public void setService(InvestListService service) {
		this.service = service;
	}


	/**   
	 * 根据分页查询
	 * @Title: queryCustomerManagerByPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月9日 上午10:30:46
	 * @return: void      
	 * @throws   
	 */  
	public void queryByPage() {
		try {
			condsUtils.addProperties(true, "investMember", "customerManager", "myAlias.memberInfo", "scale", "order");
			condsUtils.addValues(true, new Object[]{"myAlias","as"}, new Object[]{"customerAlias","as"}, new Object[]{"memberAlias","as"}, new Object[]{"scaleAlias","as"}, Order.desc("createTime"));
			if (DataUtils.notEmpty(getRequest().getParameter("loanMemberName"))) {
				condsUtils.concat("memberAlias.realName", URLDecoder.decode(getRequest().getParameter("loanMemberName"),"UTF-8"));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("loanMemberPhone"))) {
				condsUtils.concat("memberAlias.phone", URLDecoder.decode(getRequest().getParameter("loanMemberPhone"),"UTF-8"));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("loanMemberIdcard"))) {
				condsUtils.concat("memberAlias.idCard", URLDecoder.decode(getRequest().getParameter("loanMemberIdcard"),"UTF-8"));
			}
			if (entity.getUseTicket() != 0) {
				condsUtils.concat("loanType", entity.getUseTicket());
			}
			if (entity.getInvestStyle() != 0) {
				condsUtils.concat("applyState", entity.getInvestStyle());
			}
			if (entity.getStatus() != 0) {
				condsUtils.concat("poundageState", entity.getStatus());
			}
			if (DataUtils.notEmpty(getRequest().getParameter("date_begin")) && DataUtils.notEmpty(getRequest().getParameter("date_end"))) {
				condsUtils.concat("signDate", new Object[]{DateUtils.str2Date(getRequest().getParameter("date_begin"), "yyyy-MM-dd"), DateUtils.str2Date(getRequest().getParameter("date_end"), "yyyy-MM-dd"), "between"});
			}
			@SuppressWarnings("rawtypes")
			List list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			fillDatagridCons(list);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财记录ACTION方法queryByPage错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财记录ACTION方法queryByPage错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("total", 0);
			jsonObject.put("currPage", 0);
			jsonObject.put("pageSize", 0);
			jsonObject.put("rows",new JSONArray());
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 填充List数据
	 * @Title: fillDatagridCons   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list  
	 * @author 白攀    
	 * @date 2016年7月25日 下午5:08:47
	 * @return: void      
	 * @throws   
	 */  
	private void fillDatagridCons(@SuppressWarnings("rawtypes") List list) throws Exception {
		if (list !=null && list.size() > 0) {
			for(Object temp : list) {
				if (temp instanceof Object[]) {
					Map<String,Object> resultMap = new HashMap<String,Object>();
					InvestList pk = (InvestList)((Object[])temp)[4];
					MemberInfo fk = (MemberInfo)((Object[])temp)[3];
					//InvestMember fk1 = (InvestMember)((Object[])temp)[2];
					CustomerManager fk2 = (CustomerManager)((Object[])temp)[1];
					Scale fk3 = (Scale)((Object[])temp)[0];
					if (pk != null) {
						resultMap.put("id", pk.getId());
						resultMap.put("money", pk.getMoney());
						resultMap.put("yetMoney", pk.getYetMoney());
						resultMap.put("spaceMoney", pk.getSpaceMoney());
						resultMap.put("backIncome", pk.getBackIncome());
						resultMap.put("backMoney", pk.getBackMoney());
						resultMap.put("useTicket", UseTicket.valueOf("UT"+pk.getUseTicket()).toString());
						resultMap.put("expectIncome", pk.getExpectIncome());
						resultMap.put("totalMoney", pk.getTotalMoney());
						resultMap.put("income", pk.getIncome());
						resultMap.put("ticketBonus", pk.getTicketBonus());
						resultMap.put("status", Status.valueOf("S"+pk.getStatus()).toString());
						resultMap.put("openDate", DateUtils.getTimeStamp(pk.getOpenDate()));
						resultMap.put("backDate", DateUtils.getTimeStamp(pk.getBackDate()));
						resultMap.put("pactNumber", pk.getPactNumber());
						resultMap.put("investStyle", Platform.valueOf("P"+pk.getInvestStyle()).toString());
						resultMap.put("endPayTime", DateUtils.getTimeStamp(pk.getEndPayTime()));
						resultMap.put("poundageTurn", pk.getPoundageTurn());
						resultMap.put("poundagePrivilege", pk.getPoundagePrivilege());
					}
					if (fk != null) {
						resultMap.put("loanMemberName", fk.getRealName());
						resultMap.put("loanMemberNickName", fk.getNickname());
						resultMap.put("loanMemberPhone", fk.getPhone());
						resultMap.put("loanMemberIdcard", fk.getIdCard());
					}
					if (fk2 != null) {
						resultMap.put("customerManagerName", fk2.getName());
					}
					if (fk3 != null) {
						resultMap.put("scaleName", fk3.getName());
					}
					listMap.add(resultMap);
				}
			}
			jsonObject.put("total", getPageUtil().getCountRecords());
			jsonObject.put("currPage", getPageUtil().getCurrentPageNum());
			jsonObject.put("pageSize", getPageUtil().getPageRecords());
			jsonObject.put("rows", listMap);
		}else {
			jsonObject.put("total", 0);
			jsonObject.put("currPage", 0);
			jsonObject.put("pageSize", 0);
			jsonObject.put("rows",new JSONArray());
		}
	}


	/**   
	 * 页面跳转
	 * @Title: openDialog   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年8月11日 下午5:30:38
	 * @return: String      
	 * @throws   
	 */  
	public String openDialog() {
		try {
			//			String path = getRequest().getParameter("path");
			//			if(ResultPath.ADD.equals(path)) {
			//				return ResultPath.ADD;
			//			} else if(ResultPath.EDIT.equals(path)) {
			//				return ResultPath.EDIT;
			//			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财记录ACTION方法openDialog错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财记录ACTION方法openDialog错误："+e.getLocalizedMessage(), this.getClass());
		}
		return ResultPath.LIST;
	}
}
