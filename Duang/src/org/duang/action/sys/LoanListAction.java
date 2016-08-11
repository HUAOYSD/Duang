package org.duang.action.sys;

import java.util.ArrayList;
import java.util.Date;
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
import org.duang.entity.LoanMember;
import org.duang.entity.SysUser;
import org.duang.enums.If;
import org.duang.enums.Platform;
import org.duang.enums.loan.Apply;
import org.duang.enums.loan.BackMoney;
import org.duang.enums.loan.BackStyle;
import org.duang.enums.loan.LoanMode;
import org.duang.enums.loan.Poundage;
import org.duang.enums.loan.Scale;
import org.duang.enums.loan.TakeMoney;
import org.duang.service.CustomerManagerService;
import org.duang.service.LoanListService;
import org.duang.service.SysUserService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.MD5Utils;
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
		@Result(name = ResultPath.EDIT, type = "dispatcher", location = "WEB-INF/page/sys/loanlist/editLoanlist.jsp"),
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
			condsUtils.addProperties(true, "loanMember", "order", "isDelete");
			condsUtils.addValues(true, new Object[]{"myAlias","as"}, Order.desc("createtime"), entity.getIsDelete());

			if (DataUtils.notEmpty(entity.getName())) {
				condsUtils.concat("name", new Object[]{entity.getName(),"like"});
			}
			if (DataUtils.notEmpty(getRequest().getParameter("sysUserName"))) {
				condsUtils.concat("myAlias.name", getRequest().getParameter("sysUserName"));
			}
			if (DataUtils.notEmpty(entity.getWorkNumber())) {
				condsUtils.concat("workNumber", new Object[]{entity.getWorkNumber(),"like"});
			}
			if (DataUtils.notEmpty(entity.getPhone())) {
				condsUtils.concat("phone", entity.getPhone());
			}
			if (DataUtils.notEmpty(entity.getEmail())) {
				condsUtils.concat("email", new Object[]{entity.getEmail(),"like"});
			}
			if (DataUtils.notEmpty(entity.getIdcard())) {
				condsUtils.concat("idcard", new Object[]{entity.getIdcard(),"like"});
			}
			if (DataUtils.notEmpty(entity.getSex())) {
				condsUtils.concat("sex", entity.getSex());
			}

			@SuppressWarnings("rawtypes")
			List list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			fillDatagridCons(list);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户经理ACTION方法queryCustomerManagerByPage错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("客户经理ACTION方法queryCustomerManagerByPage错误："+e.getLocalizedMessage(), this.getClass());
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
					LoanList pk = (LoanList)((Object[])temp)[1];
					LoanMember fk = (LoanMember)((Object[])temp)[0];
					if (pk != null) {
						resultMap.put("id", pk.getId());
						resultMap.put("loanType", LoanMode.valueOf("M"+pk.getLoanType()));
						resultMap.put("pactNumber", pk.getPactNumber());
						resultMap.put("isSell", Scale.valueOf("S"+pk.getIsSell()));
						resultMap.put("poundageState", Poundage.valueOf("P"+pk.getPoundageState()));
						resultMap.put("money", pk.getMoney());
						resultMap.put("realMoney", pk.getRealMoney());
						resultMap.put("manageCost", pk.getManageCost());
						resultMap.put("poundage", pk.getPoundage());
						resultMap.put("getMoney", pk.getGetMoney());
						resultMap.put("yetMoney", pk.getYetMoney());
						resultMap.put("returnMoney", pk.getReturnMoney());
						resultMap.put("agoMoney", pk.getAgoMoney());
						resultMap.put("yetReturnMoney", pk.getYetReturnMoney());
						resultMap.put("returnStatus", BackMoney.valueOf("B"+pk.getReturnStatus()));
						resultMap.put("loanState", TakeMoney.valueOf("T"+pk.getLoanState()));
						resultMap.put("applyState", Apply.valueOf("A"+pk.getApplyState()));
						resultMap.put("loanUse", pk.getLoanUse());
						resultMap.put("loanInterest", pk.getLoanInterest());
						resultMap.put("createTime", DateUtils.getTimeStamp(pk.getCreateTime()));
						resultMap.put("signDate", DateUtils.getTimeStamp(pk.getSignDate()));
						resultMap.put("beginReturnDate", DateUtils.getTimeStamp(pk.getBeginReturnDate()));
						resultMap.put("endReturnDate", DateUtils.getTimeStamp(pk.getEndReturnDate()));
						resultMap.put("doneReturnDate", DateUtils.getTimeStamp(pk.getDoneReturnDate()));
						resultMap.put("loanStyle", Platform.valueOf("P"+pk.getLoanStyle()));
						resultMap.put("backStyle", BackStyle.valueOf("B"+pk.getBackStyle()));
					}
					if (fk != null) {
						resultMap.put("loanMemberName", fk.getMemberInfo().getRealName());
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


	

}
