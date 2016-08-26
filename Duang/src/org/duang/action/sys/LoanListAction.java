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
import org.duang.entity.LoanList;
import org.duang.entity.MemberInfo;
import org.duang.enums.Platform;
import org.duang.enums.loan.Apply;
import org.duang.enums.loan.BackMoney;
import org.duang.enums.loan.BackStyle;
import org.duang.enums.loan.LoanMode;
import org.duang.enums.loan.Poundage;
import org.duang.enums.loan.Scale;
import org.duang.enums.loan.TakeMoney;
import org.duang.service.LoanListService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**   
 * 借贷记录Action
 * @ClassName:  LoanListAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年8月10日 下午5:21:02      
 */  
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "loanlist")
@ParentPackage("sys")
@Results(value = { 
		@Result(name = ResultPath.LIST, type = "dispatcher", location = "WEB-INF/page/sys/loanlist/loanlist.jsp"),
		@Result(name = "allot", type = "dispatcher", location = "WEB-INF/page/sys/loanlist/allotLoanlist.jsp"),
		@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") 
})
public class LoanListAction extends BaseAction<LoanList> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private LoanListService service;

	@Resource()
	public void setService(LoanListService service) {
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
			condsUtils.addProperties(true, "memberInfo", "customerManager", "order");
			condsUtils.addValues(true, new Object[]{"memberAlias","as"}, new Object[]{"customerAlias","as"}, Order.desc("createTime"));
			if (DataUtils.notEmpty(getRequest().getParameter("loanMemberName"))) {
				condsUtils.concat("memberAlias.realName", URLDecoder.decode(getRequest().getParameter("loanMemberName"),"UTF-8"));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("loanMemberPhone"))) {
				condsUtils.concat("memberAlias.phone", URLDecoder.decode(getRequest().getParameter("loanMemberPhone"),"UTF-8"));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("loanMemberIdcard"))) {
				condsUtils.concat("memberAlias.idCard", URLDecoder.decode(getRequest().getParameter("loanMemberIdcard"),"UTF-8"));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("customerManagerName"))) {
				condsUtils.concat("customerAlias.name", URLDecoder.decode(getRequest().getParameter("customerManagerName"),"UTF-8"));
			}
			if (entity.getLoanType() != 0) {
				condsUtils.concat("loanType", entity.getLoanType());
			}
			if (entity.getApplyState() != 0) {
				condsUtils.concat("applyState", entity.getApplyState());
			}
			if (entity.getPoundageState() != 0) {
				condsUtils.concat("poundageState", entity.getPoundageState());
			}
			if (entity.getBackStyle() != 0) {
				condsUtils.concat("backStyle", entity.getBackStyle());
			}
			if (entity.getLoanState() != 0) {
				condsUtils.concat("loanState", entity.getLoanState());
			}
			if (entity.getIsSell() != 0) {
				condsUtils.concat("isSell", entity.getIsSell());
			}
			if (entity.getReturnStatus() != 0) {
				condsUtils.concat("returnStatus", entity.getReturnStatus());
			}
			if (entity.getLoanStyle() != 0) {
				condsUtils.concat("loanStyle", entity.getLoanStyle());
			}
			if (DataUtils.notEmpty(getRequest().getParameter("signDate_begin")) && DataUtils.notEmpty(getRequest().getParameter("signDate_end"))) {
				condsUtils.concat("signDate", new Object[]{DateUtils.str2Date(getRequest().getParameter("signDate_begin"), "yyyy-MM-dd"), DateUtils.str2Date(getRequest().getParameter("signDate_end"), "yyyy-MM-dd"), "between"});
			}
			@SuppressWarnings("rawtypes")
			List list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			fillDatagridCons(list);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷记录ACTION方法queryByPage错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("借贷记录ACTION方法queryByPage错误："+e.getLocalizedMessage(), this.getClass());
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
					LoanList pk = (LoanList)((Object[])temp)[2];
					MemberInfo fk = (MemberInfo)((Object[])temp)[1];
					CustomerManager fk2 = (CustomerManager)((Object[])temp)[0];
					if (pk != null) {
						resultMap.put("id", pk.getId());
						resultMap.put("loanType", LoanMode.valueOf("M"+pk.getLoanType()).toString());
						resultMap.put("pactNumber", pk.getPactNumber());
						resultMap.put("isSell", Scale.valueOf("S"+pk.getIsSell()).toString());
						resultMap.put("poundageState", Poundage.valueOf("P"+pk.getPoundageState()).toString());
						resultMap.put("money", pk.getMoney());
						resultMap.put("realMoney", pk.getRealMoney());
						resultMap.put("manageCost", pk.getManageCost());
						resultMap.put("poundage", pk.getPoundage());
						resultMap.put("getMoney", pk.getGetMoney());
						resultMap.put("yetMoney", pk.getYetMoney());
						resultMap.put("returnMoney", pk.getReturnMoney());
						resultMap.put("agoMoney", pk.getAgoMoney());
						resultMap.put("yetReturnMoney", pk.getYetReturnMoney());
						resultMap.put("returnStatus", BackMoney.valueOf("B"+pk.getReturnStatus()).toString());
						resultMap.put("loanState", TakeMoney.valueOf("T"+pk.getLoanState()).toString());
						resultMap.put("applyState", Apply.valueOf("A"+pk.getApplyState()).toString());
						resultMap.put("loanUse", pk.getLoanUse());
						resultMap.put("loanInterest", pk.getLoanInterest());
						resultMap.put("createTime", DateUtils.getTimeStamp(pk.getCreateTime()));
						resultMap.put("signDate", DateUtils.getTimeStamp(pk.getSignDate()));
						resultMap.put("beginReturnDate", DateUtils.getTimeStamp(pk.getBeginReturnDate()));
						resultMap.put("endReturnDate", DateUtils.getTimeStamp(pk.getEndReturnDate()));
						resultMap.put("doneReturnDate", DateUtils.getTimeStamp(pk.getDoneReturnDate()));
						resultMap.put("passTime", DateUtils.getTimeStamp(pk.getPassTime()));
						resultMap.put("applyContent", pk.getApplyContent());
						resultMap.put("loanStyle", Platform.valueOf("P"+pk.getLoanStyle()).toString());
						resultMap.put("backStyle", BackStyle.valueOf("B"+pk.getBackStyle()).toString());
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
	 * 查询根据条件日期查询全部审核通过的借贷记录
	 * @Title: queryPassLoanRecords   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月18日 下午2:47:07
	 * @return: void      
	 * @throws   
	 */  
	public void queryPassLoanRecords() {
		try {
			//审核通过且未起标
			condsUtils.addProperties(true, "customerManager", "memberInfo", "applyState", "isSell", "order");
			condsUtils.addValues(true, new Object[]{"customerAlias","as"}, new Object[]{"memberAlias","as"}, Apply.A2.getVal(), Scale.S1.getVal(), Order.desc("createTime"));
			if (DataUtils.notEmpty(getRequest().getParameter("begin_date")) && DataUtils.notEmpty(getRequest().getParameter("end_date"))) {
				condsUtils.concat("passTime", new Object[]{DateUtils.str2Date(getRequest().getParameter("begin_date")+" 00:00:00"), "ge"});
				condsUtils.concat("passTime", new Object[]{DateUtils.str2Date(getRequest().getParameter("end_date")+" 23:59:59"), "le"});
			}else{
				condsUtils.concat("passTime", new Object[]{DateUtils.getMinAtToday(), "ge"});
				condsUtils.concat("passTime", new Object[]{DateUtils.getMaxAtToday(), "le"});
			}
			@SuppressWarnings("rawtypes")
			List list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			fillDatagridCons(list);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷记录ACTION方法queryByPage错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("借贷记录ACTION方法queryByPage错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("total", 0);
			jsonObject.put("currPage", 0);
			jsonObject.put("pageSize", 0);
			jsonObject.put("rows",new JSONArray());
		} finally {
			printJsonResult();
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
			String path = getRequest().getParameter("path");
			if("allot".equals(path)) {
				getRequest().setAttribute("scaleid", getRequest().getParameter("scaleid"));
				return "allot";
			} 
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷记录ACTION方法openDialog错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("借贷记录ACTION方法openDialog错误："+e.getLocalizedMessage(), this.getClass());
		}
		return ResultPath.LIST;
	}


	/**   
	 * 分配客户经理
	 * @Title: updateCustomerManager   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月12日 下午3:37:24
	 * @return: void      
	 * @throws   
	 */  
	public void updateCustomerManager(){
		try {
			if (entity!=null && entity.getCustomerManager()!=null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(entity.getCustomerManager().getId())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("customerManager.id", entity.getCustomerManager().getId());
				if (service.updateEntity(map, "id", entity.getId())) {
					jsonObject.put("success", true);
				}else {
					jsonObject.put("success", false);
				}
			} else {
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷记录ACTION方法updateCustomerManager错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("借贷记录ACTION方法updateCustomerManager错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}
}
