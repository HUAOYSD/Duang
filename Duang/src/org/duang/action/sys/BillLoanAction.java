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
import org.duang.entity.BillLoan;
import org.duang.entity.BindCard;
import org.duang.entity.MemberInfo;
import org.duang.service.BillLoanService;
import org.duang.util.ConstantCode;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 投资者资金放款还款记录Action
 * 
 * @ClassName: BillLoanAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年8月24日 下午2:55:48
 */

@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "billloan")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/billloan/billLoanList.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class BillLoanAction extends BaseAction<BillLoan> {
	private static final long serialVersionUID = 1L;
	
	private BillLoanService billLoanService;
	@Resource(name = "billloanserviceimpl")
	public void setService(BillLoanService billLoanService) {
		this.billLoanService = billLoanService;
	}

	/**
	 * 页面跳转 ---列表
	 * @Title: gotoMemeberTicketRecordList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午3:37:56
	 * @return: String      
	 * @throws
	 */
	public String gotoList(){
		return "list";
	}
	
	/**
	 * 封装理财券使用记录信息    list封装的是BillLoan对象集
	 * @Title: fillDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午3:54:10
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	@SuppressWarnings("unused")
	private List<Map<String, Object>> fillDataObjectList(List<BillLoan> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (BillLoan billLoan : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", billLoan.getId());
				map.put("status", billLoan.getStatus());
				map.put("money", billLoan.getMoney());
				map.put("doneMoney", billLoan.getDoneMoney());
				map.put("billStatus", billLoan.getBillStatus());
				map.put("remark", billLoan.getRemark());
				map.put("style", billLoan.getStyle());
				map.put("optTime", DateUtils.date2Str(billLoan.getOptTime()));
				map.put("pactNumber", billLoan.getLoanList().getPactNumber());
				MemberInfo memberInfo = billLoan.getMemberInfo();
				map.put("realName", memberInfo.getRealName());
				map.put("nickname", memberInfo.getNickname());
				map.put("email", memberInfo.getEmail());
				map.put("sex", memberInfo.getSex());
				map.put("phone", memberInfo.getPhone());
				
				BindCard bindCard = billLoan.getBindCard();
				map.put("bankNo", bindCard.getBankNo());
				map.put("openBank", bindCard.getOpenBank());
				map.put("type", bindCard.getType());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装投资者资金放款还款记录信息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装投资者资金放款还款记录信息错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 在用条件查询的时候，查询出来为List<Object[]>，所以需要进行封装
	 * 
	 * @Title: fillDataObjectArray
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param list
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月16日 下午3:29:33
	 * @return: List<Map<String,Object>>
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(@SuppressWarnings("rawtypes") List list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] array = (Object[]) list.get(i);
				BillLoan billLoan = (BillLoan) array[1];
				map.put("id", billLoan.getId());
				map.put("status", billLoan.getStatus());
				map.put("billStatus", billLoan.getBillStatus());
				map.put("money", billLoan.getMoney());
				map.put("doneMoney", billLoan.getDoneMoney());
				map.put("billStatus", billLoan.getBillStatus());
				map.put("remark", billLoan.getRemark());
				map.put("style", billLoan.getStyle());
				map.put("optTime", DateUtils.date2Str(billLoan.getOptTime()));
				map.put("pactNumber", billLoan.getLoanList().getPactNumber());
				MemberInfo memberInfo = billLoan.getMemberInfo();
				map.put("realName", memberInfo.getRealName());
				map.put("nickname", memberInfo.getNickname());
				map.put("email", memberInfo.getEmail());
				map.put("sex", memberInfo.getSex());
				map.put("phone", memberInfo.getPhone());
				
				BindCard bindCard = billLoan.getBindCard();
				map.put("bankNo", bindCard.getBankNo());
				map.put("openBank", bindCard.getOpenBank());
				map.put("type", bindCard.getType());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装投资者资金放款还款记录错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装投资者资金放款还款记录错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 根据条件查询记录
	 * @Title: queryByPar   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午12:15:05
	 * @return: void      
	 * @throws
	 */
	public void queryByPar() {
		try {
			if(entity != null){
				condsUtils.addProperties(true, "memberInfo");
				condsUtils.concatValue(new String[] { "mi", "as" });
				MemberInfo memberInfo = entity.getMemberInfo();
				if(memberInfo != null){
					if(DataUtils.notEmpty(memberInfo.getRealName())){
						condsUtils.addProperties(false, "mi.realName");
						condsUtils.concatValue(new String[] {memberInfo.getRealName(), "like" });
					}
					if(DataUtils.notEmpty(memberInfo.getPhone())){
						condsUtils.addProperties(false, "mi.phone");
						condsUtils.concatValue(new String[] {memberInfo.getPhone(), "like" });
					}
				}
				if(entity.getStatus()!=ConstantCode.NOSELECTED && entity.getStatus()!=ConstantCode.INT_DEFAULT ){
					condsUtils.addProperties(false, "status");
					condsUtils.addValues(false, entity.getStatus());
				}
				if (DataUtils.notEmpty(getRequest().getParameter("optTime"))) {
					condsUtils.concat("optTime", new Object[]{DateUtils.str2Date(getRequest().getParameter("optTime")+" 00:00:00", "yyyy-MM-dd hh:mm:ss"), DateUtils.str2Date(getRequest().getParameter("optTime")+" 59:59:59", "yyyy-MM-dd hh:mm:ss"), "between"});
				}
			}
			@SuppressWarnings("rawtypes")
			List list = billLoanService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
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
			LoggerUtils.error("投资者资金放款还款记录ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("投资者资金放款还款记录ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
}
