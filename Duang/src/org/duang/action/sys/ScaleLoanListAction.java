package org.duang.action.sys;

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
import org.duang.entity.LoanList;
import org.duang.entity.MemberInfo;
import org.duang.entity.ScaleLoanList;
import org.duang.enums.Platform;
import org.duang.enums.loan.BackStyle;
import org.duang.enums.loan.LoanMode;
import org.duang.enums.loan.Scale;
import org.duang.service.ScaleLoanListService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 借贷记录与理财标关联Action
 * @ClassName: ScaleLoanListAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年7月26日 下午1:55:48
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "scaleloanlist")
@ParentPackage("sys")
@Results(value = { 
		@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") 
})
public class ScaleLoanListAction extends BaseAction<ScaleLoanList> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private ScaleLoanListService service;

	@Resource
	public void setService(ScaleLoanListService service) {
		this.service = service;
	}


	/**   
	 * 借贷分配
	 * @Title: confirmAllotLoanList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月18日 下午5:19:56
	 * @return: void      
	 * @throws   
	 */  
	public void confirmAllotLoanList(){
		try {
			String scaleId = getRequest().getParameter("scaleid");
			String[] loanListIds = getRequest().getParameterValues("loanListIds");
			if (DataUtils.notEmpty(scaleId) && loanListIds!=null && loanListIds.length>0) {
				if (service.matchScaleLoanRecords(scaleId, loanListIds)) {
					jsonObject.put("success", true);
				}else{
					jsonObject.put("success", false);
				}
			}else {
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷记录与理财标关联ACTION方法confirmAllotLoanList错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("借贷记录与理财标关联ACTION方法confirmAllotLoanList错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}
	
	
	/**   
	 * 根据理财标id查询理财标所含的借贷记录及其状态
	 * @Title: findScaleLoanListInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param scaleid
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月22日 下午2:14:37
	 * @return: Object      
	 * @throws   
	 */  
	public void findScaleLoanListInfo(String scaleid) {
		try {
			condsUtils.addProperties(true, "scale", "loanList", "loanListAlias.loanMember", "order");
			condsUtils.addValues(true, new Object[]{"scaleAlias","as"}, new Object[]{"loanListAlias","as"}, new Object[]{"loanMemberAlias","as"}, Order.desc("loanListAlias.createTime"));
			@SuppressWarnings("rawtypes")
			List list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			fillDatagridCons(list);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷记录与理财标关联ACTION方法findScaleLoanListInfo错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("借贷记录与理财标关联ACTION方法findScaleLoanListInfo错误："+e.getLocalizedMessage(), this.getClass());
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
					LoanList loanlist = (LoanList)((Object[])temp)[3];
					MemberInfo memberinfo = (MemberInfo)((Object[])temp)[2];
					if (loanlist != null) {
						resultMap.put("loanType", LoanMode.valueOf("M"+loanlist.getLoanType()).toString());
						resultMap.put("createTime", DateUtils.getTimeStamp(loanlist.getCreateTime()));
						resultMap.put("backStyle", BackStyle.valueOf("B"+loanlist.getBackStyle()).toString());
						resultMap.put("money", loanlist.getMoney());
						resultMap.put("realMoney", loanlist.getRealMoney());
						resultMap.put("isSell", Scale.valueOf("S"+loanlist.getIsSell()).toString());
						resultMap.put("passTime", DateUtils.getTimeStamp(loanlist.getPassTime()));
						resultMap.put("loanStyle", Platform.valueOf("P"+loanlist.getLoanStyle()).toString());
						resultMap.put("manageCost", loanlist.getManageCost());
						resultMap.put("poundage", loanlist.getPoundage());
						resultMap.put("getMoney", loanlist.getGetMoney());
					}
					if (memberinfo != null) {
						resultMap.put("loanMemberName", memberinfo.getRealName());
						resultMap.put("loanMemberPhone", memberinfo.getPhone());
						resultMap.put("loanMemberIdcard", memberinfo.getIdCard());
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
