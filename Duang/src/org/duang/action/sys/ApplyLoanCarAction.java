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
import org.duang.entity.ApplyLoanCar;
import org.duang.service.ApplyLoanCarService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 车产贷款信息 action
 * @ClassName:  ApplyLoanCarAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月22日 上午10:50:51
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "applyloancar")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "showapplyloancar", type = "dispatcher", location = "WEB-INF/page/sys/loanlist/applyLoanCar.jsp"),
			@Result(name = "showDatumsAndAssetInfo", type = "dispatcher", location = "WEB-INF/page/sys/loanlist/showDatumsAndAssetInfo.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class ApplyLoanCarAction extends BaseAction<ApplyLoanCar> {
	private static final long serialVersionUID = 1L;
	
	private ApplyLoanCarService applyLoanCarService;
	@Resource(name = "applyloancarserviceimpl")
	public void setService(ApplyLoanCarService applyLoanCarService) {
		this.applyLoanCarService = applyLoanCarService;
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
	public String showApplyLoanCar() {
		try {
			entity = applyLoanCarService.findEntity("loanList.id", entity.getLoanList().getId());
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("车产贷款信息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("车产贷款信息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "showapplyloancar";
	}
	
	/**
	 * 判断id是否存在
	 * @Title: findApplyLoanCar   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月22日 下午4:15:38
	 * @return: void      
	 * @throws
	 */
	public void findApplyLoanCar(){
		try {
			entity = applyLoanCarService.findEntity("loanList.id", entity.getLoanList().getId());
			if (entity != null) {
				jsonObject.put("result", true);
				jsonObject.put("msg", "存在符合条件的数据");
				printJsonResult();
			} else {
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
				printJsonResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("车产贷款信息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("车产贷款信息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} 
	}
	
	/**
	 * 个人资料，或者个人收入
	 * @Title: showApplyLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月22日 下午3:02:50
	 * @return: String      
	 * @throws
	 */
	public String showDatumsAndAsset() {
		try {
			entity = applyLoanCarService.findEntity("loanList.id", entity.getLoanList().getId());
			if(entity!=null){
				//个人资料
				if("datums".equals(getRequest().getParameter("type"))){
					getRequest().setAttribute("path", entity.getDatums());
				}else if("asset".equals(getRequest().getParameter("type"))){
					getRequest().setAttribute("path", entity.getAssetCertificates());
				}
			}else{
				getRequest().setAttribute("path", "");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("车产贷款信息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("车产贷款信息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "showDatumsAndAssetInfo";
	}
}
