package org.duang.action.sys;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.ScaleLoanList;
import org.duang.service.ScaleLoanListService;
import org.duang.util.DataUtils;
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


}
