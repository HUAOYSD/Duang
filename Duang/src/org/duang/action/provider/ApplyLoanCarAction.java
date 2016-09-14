package org.duang.action.provider;
import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.ApplyLoanCar;
import org.duang.entity.LoanList;
import org.duang.entity.MemberInfo;
import org.duang.service.ApplyLoanCarService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————车产模式贷款的申请信息Action
 * @ClassName:  ApplyLoanCarAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_applyCarLoanInfo")
public class ApplyLoanCarAction extends BaseAction<ApplyLoanCar>{

	private ApplyLoanCarService applyLoanCarService;
	@Resource(name = "applyloancarserviceimpl")
	public void setService(ApplyLoanCarService applyLoanCarService) {
		this.applyLoanCarService = applyLoanCarService;
	}
	
	/**
	 * 封装参数
	 * @Title: getApplyLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年9月13日 下午5:05:54
	 * @return: ApplyLoanInfo      
	 * @throws
	 */
	private ApplyLoanCar getApplyLoanCar() throws Exception{
		ApplyLoanCar applyLoanCar = new ApplyLoanCar(DataUtils.randomUUID());
		
		LoanList loanList = new LoanList(DataUtils.randomUUID());
		if(DataUtils.notEmpty(getRequest().getParameter("p_days"))){
			loanList.setDays(DataUtils.str2int((DES.decryptDES(getRequest().getParameter("p_days")))));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_loanStyle"))){
			loanList.setLoanStyle(DataUtils.str2int((getRequest().getParameter("p_loanStyle"))));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_loanUse"))){
			loanList.setLoanUse((getRequest().getParameter("p_loanUse")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_backStyle"))){
			loanList.setBackStyle(DataUtils.str2int((getRequest().getParameter("p_backStyle"))));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("token"))){
			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setId(MemberCollection.getInstance().getMainField(getRequest().getParameter("token")));
			loanList.setMemberInfo(memberInfo);
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_money"))){
			loanList.setMoney(DataUtils.str2double(DES.decryptDES(getRequest().getParameter("p_money")), 6));
		}
		applyLoanCar.setLoanList(loanList);
		
		if(DataUtils.notEmpty(getRequest().getParameter("p_name"))){
			applyLoanCar.setName(DES.decryptDES(getRequest().getParameter("p_name")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_phone"))){
			applyLoanCar.setPhone(DES.decryptDES(getRequest().getParameter("p_phone")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_idcard"))){
			applyLoanCar.setIdcard(DES.decryptDES(getRequest().getParameter("p_idcard")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_province"))){
			applyLoanCar.setProvince(DES.decryptDES(getRequest().getParameter("p_province")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_city"))){
			applyLoanCar.setCity(DES.decryptDES(getRequest().getParameter("p_city")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_brand"))){
			applyLoanCar.setBrand(DES.decryptDES(getRequest().getParameter("p_brand")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_age"))){
			applyLoanCar.setAge(DES.decryptDES(getRequest().getParameter("p_age")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_limit"))){
			applyLoanCar.setLimit(DES.decryptDES(getRequest().getParameter("p_limit")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_engine"))){
			applyLoanCar.setEngine(DES.decryptDES(getRequest().getParameter("p_engine")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("carProperty"))){
			applyLoanCar.setCarProperty(DES.decryptDES(getRequest().getParameter("carProperty")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_datums"))){
			applyLoanCar.setDatums(getRequest().getParameter("p_datums"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_assetCertificates"))){
			applyLoanCar.setAssetCertificates(getRequest().getParameter("p_assetCertificates"));
		}
		return applyLoanCar;
	}
	
	
	/**   
	 * 增加车产模式的借贷信息
	 * @Title: insertApplyCarLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */  
	public void insertApplyCarLoanInfo(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			//判断参数是否为空
			if(DataUtils.notEmpty(token)){
				ApplyLoanCar applyLoanCar = getApplyLoanCar();
				success = applyLoanCarService.saveEntity(applyLoanCar);
				if(!success){
					msg = "服务器超时，请稍后重试";
				}
			}else{
				msg = "用户token为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ApplyLoanCarAction——insertApplyCarLoanInfo方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ApplyLoanCarAction——insertApplyCarLoanInfo方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
}
