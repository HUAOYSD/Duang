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
import org.duang.entity.ApplyLoanCar;
import org.duang.entity.LoanList;
import org.duang.entity.LoanMemberRepayDate;
import org.duang.entity.MemberInfo;
import org.duang.service.LoanListService;
import org.duang.service.LoanMemberRepayDateService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 借款用户的还款状态
 * @ClassName:  LoanMemberRepayDateAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2017年1月5日 下午5:23:14
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "repay_date")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class LoanMemberRepayDateAction extends BaseAction<ApplyLoanCar> {
	private static final long serialVersionUID = 1L;
	
	private LoanMemberRepayDateService loanMemberRepayDateService;
	@Resource(name = "loanmemberrepaydateserviceimpl")
	public void setLoanMemberRepayDateService(LoanMemberRepayDateService loanMemberRepayDateService) {
		this.loanMemberRepayDateService = loanMemberRepayDateService;
	}
	
	private LoanListService loanListService;
	@Resource(name = "loanlistserviceimpl")
	public void setLoanListService(LoanListService loanListService) {
		this.loanListService = loanListService;
	}
	
	/**
	 * 查询借款用户的还款记录
	 * @Title: queryLoanMemberRepayByMonth   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2017年1月4日 下午4:03:06
	 * @return: void      
	 * @throws
	 */
	public void queryLoanMemberRepay(){
		try {
			String month = getRequest().getParameter("month");
			String year = getRequest().getParameter("year");
			String phone = getRequest().getParameter("phone");
			String realName = getRequest().getParameter("realName");
			String state = getRequest().getParameter("state");
			boolean isMemberInfo = true;
			String whereStr = "";
			if(DataUtils.notEmpty(month) && DataUtils.notEmpty(year) && !month.equals("0")&& !year.equals("0")){
				month = month.length()==1?("0"+month):month;
				whereStr+=" and repay_date like '" +(year+"-"+month)+"%'";
				jsonObject.put("year", year);
				jsonObject.put("month", month);
			}
			if(DataUtils.notEmpty(phone) || DataUtils.notEmpty(realName)){
				String loanListId = loanListService.findLoanListIdByPhoneOrRealName(phone, realName);
				if(DataUtils.notEmpty(loanListId)){
					whereStr+=" and loan_list_id='"+loanListId+"'";
				}else{
					//如果没有找到用户信息，就直接返回空
					isMemberInfo = false;
				}
				if(DataUtils.notEmpty(realName)){
					jsonObject.put("realName", realName);
				}
				if(DataUtils.notEmpty(phone)){
					jsonObject.put("phone", phone);
				}
			}
			if(isMemberInfo){
				if(DataUtils.notEmpty(state) && !state.equals("-1")){
					whereStr+=" and lmrd.state="+state;
					jsonObject.put("state", state);
				}
				String sql =" select  *  from loan_member_repay_date lmrd  where 1=1";
				
				if(DataUtils.notEmpty(whereStr)){
					sql+=whereStr;
				}
				sql+=" order by loan_list_id,repay_index";
				List<LoanMemberRepayDate>  loanMemberReapays = loanMemberRepayDateService.queryBySQL(sql, null, null, true);
				if (loanMemberReapays != null && loanMemberReapays.size() > 0) {
					jsonObject.put("result", true);
					jsonObject.put("rows", fillRepayDataObjectArray(loanMemberReapays));
				} else {
					jsonObject.put("rows", new JSONArray());
					jsonObject.put("result", false);
					jsonObject.put("msg", "没有符合条件的数据！");
				}
			}else{
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("查询借款用户的还款记录错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("查询借款用户的还款记录错误：" + e.getLocalizedMessage(), this.getClass());
		}finally {
			printJsonResult();
		}
	}

	/**
	 * 根据借贷id查询还款情况
	 * @Title: queryLoanMemberRepayByLoanListId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2017年1月6日 上午9:46:36
	 * @return: void      
	 * @throws
	 */
	public void  queryLoanMemberRepayByLoanListId(){
		try {
			String loanListId = getRequest().getParameter("loanListId");
			List<LoanMemberRepayDate>  loanMemberReapays=  loanMemberRepayDateService.queryEntity("loanListId", loanListId, null, Order.asc("repayIndex"));
			if (loanMemberReapays != null && loanMemberReapays.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectArray(loanMemberReapays));
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("根据借贷id查询还款错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("根据借贷id查询还款错误：" + e.getLocalizedMessage(), this.getClass());
		}finally {
			printJsonResult();
		}
	}
	
	/**
	 * 封装还款记录
	 * @Title: fillDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2017年1月6日 上午9:45:32
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(List<LoanMemberRepayDate> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (LoanMemberRepayDate loanMemberRepayDate : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("reapyIndex", loanMemberRepayDate.getRepayIndex());
				map.put("repayDate", DateUtils.getTimeStamp(loanMemberRepayDate.getRepayDate()));
				map.put("state", loanMemberRepayDate.getState());
				map.put("isOver", loanMemberRepayDate.getIsOver());
				map.put("overDays", loanMemberRepayDate.getOverDays());
				map.put("reRepayDate", loanMemberRepayDate.getReRepayDate()!=null?DateUtils.getTimeStamp(loanMemberRepayDate.getReRepayDate()):0);
				map.put("overSum", loanMemberRepayDate.getOverSum());
				map.put("repaySum", loanMemberRepayDate.getRepaySum());
				map.put("repayRealSum", loanMemberRepayDate.getRepayRealSum());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装借贷用户还款逾期情况错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装借贷用户还款逾期情况错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 封装用户信息和借贷记录
	 * @Title: fillRepayDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2017年1月6日 上午9:45:54
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillRepayDataObjectArray(List<LoanMemberRepayDate> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (LoanMemberRepayDate loanMemberRepayDate : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				LoanList loanList = loanListService.findById(loanMemberRepayDate.getLoanListId());
				MemberInfo memberInfo = loanList.getMemberInfo();
				map.put("loginName", memberInfo.getLoginName());
				map.put("realName", memberInfo.getRealName());
				map.put("phone", memberInfo.getPhone());
				map.put("reapyIndex", loanMemberRepayDate.getRepayIndex());
				map.put("repayDate", DateUtils.getTimeStamp(loanMemberRepayDate.getRepayDate()));
				map.put("state", loanMemberRepayDate.getState());
				map.put("isOver", loanMemberRepayDate.getIsOver());
				map.put("overDays", loanMemberRepayDate.getOverDays());
				map.put("reRepayDate", loanMemberRepayDate.getReRepayDate()!=null?DateUtils.getTimeStamp(loanMemberRepayDate.getReRepayDate()):0);
				map.put("overSum", loanMemberRepayDate.getOverSum());
				map.put("repaySum", loanMemberRepayDate.getRepaySum());
				map.put("repayRealSum", loanMemberRepayDate.getRepayRealSum());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装借贷用户还款逾期情况错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装借贷用户还款逾期情况错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
}
