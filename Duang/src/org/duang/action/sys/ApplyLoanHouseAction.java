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
import org.duang.entity.ApplyLoanHouse;
import org.duang.service.ApplyLoanHouseService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 房产贷款信息 action
 * @ClassName:  ApplyLoanInfoAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月22日 上午10:50:51
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "applyloanhouse")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "showApplyLoanHouse", type = "dispatcher", location = "WEB-INF/page/sys/loanlist/applyLoanHouse.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class ApplyLoanHouseAction extends BaseAction<ApplyLoanHouse> {
	private static final long serialVersionUID = 1L;
	
	private ApplyLoanHouseService applyLoanHouseService;
	@Resource(name = "applyloanhouseserviceimpl")
	public void setService(ApplyLoanHouseService applyLoanHouseService) {
		this.applyLoanHouseService = applyLoanHouseService;
	}
	
	/**
	 * 展示详细信息
	 * @Title: showApplyLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月22日 下午3:02:50
	 * @return: String      
	 * @throws
	 */
	public String showApplyLoanHouse() {
		try {
			entity = applyLoanHouseService.findEntity("loanList.id", entity.getLoanList().getId());
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("房产贷款信息查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("房产贷款信息查询错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "showApplyLoanHouse";
	}
	
	/**
	 * 根据id查询，判断是否存在
	 * 
	 * @Title: findApplyLoanHouse   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月22日 下午4:59:57
	 * @return: void      
	 * @throws
	 */
	public void findApplyLoanHouse(){
		try {
			entity = applyLoanHouseService.findEntity("loanList.id", entity.getLoanList().getId());
			if (entity != null) {
				jsonObject.put("result", true);
				jsonObject.put("msg", "1存在符合条件的数据");
				printJsonResult();
			} else {
				jsonObject.put("result", false);
				jsonObject.put("msg", "1没有符合条件的数据！");
				printJsonResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("房产贷款信息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("房产贷款信息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} 
	}
	
}
