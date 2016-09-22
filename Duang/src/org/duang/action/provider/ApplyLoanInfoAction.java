package org.duang.action.provider;
import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.ApplyLoanInfo;
import org.duang.entity.LoanList;
import org.duang.entity.MemberInfo;
import org.duang.enums.loan.Apply;
import org.duang.enums.loan.BackMoney;
import org.duang.enums.loan.Poundage;
import org.duang.enums.loan.Scale;
import org.duang.enums.loan.TakeMoney;
import org.duang.service.ApplyLoanInfoService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————普通和急速模式贷款的申请信息Action
 * @ClassName:  ApplyLoanInfoAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_applyLoanInfo")
public class ApplyLoanInfoAction extends BaseAction<ApplyLoanInfo>{

	private ApplyLoanInfoService applyLoanInfoService;
	@Resource(name = "applyloaninfoserviceimpl")
	public void setService(ApplyLoanInfoService applyLoanInfoService) {
		this.applyLoanInfoService = applyLoanInfoService;
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
	private ApplyLoanInfo getApplyLoanInfo() throws Exception{
		ApplyLoanInfo applyLoanInfo = new ApplyLoanInfo(DataUtils.randomUUID());
		
		LoanList loanList = new LoanList(DataUtils.randomUUID());
		loanList.setIsSell(Scale.S1.getVal());
		loanList.setPoundageState(Poundage.P1.getVal());
		loanList.setReturnStatus(BackMoney.B1.getVal());
		loanList.setLoanState(TakeMoney.T1.getVal());
		loanList.setApplyState(Apply.A1.getVal());
		if(DataUtils.notEmpty(getRequest().getParameter("p_days"))){
			loanList.setDays(DataUtils.str2int((DES.decryptDES(getRequest().getParameter("p_days")))));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_loanStyle"))){
			loanList.setLoanStyle(DataUtils.str2int((getRequest().getParameter("p_loanStyle"))));
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
		if(DataUtils.notEmpty(getRequest().getParameter("p_loanUse"))){
			loanList.setLoanUse(getRequest().getParameter("p_loanUse"));
		}
		
		applyLoanInfo.setLoanList(loanList);
		
		if(DataUtils.notEmpty(getRequest().getParameter("p_money"))){
			applyLoanInfo.setMoney(DataUtils.str2double(DES.decryptDES(getRequest().getParameter("p_money")), 6));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_sex"))){
			applyLoanInfo.setSex(DES.decryptDES(getRequest().getParameter("p_sex")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_name"))){
			applyLoanInfo.setName(DES.decryptDES(getRequest().getParameter("p_name")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_phone"))){
			applyLoanInfo.setPhone(DES.decryptDES(getRequest().getParameter("p_phone")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_idcard"))){
			applyLoanInfo.setIdcard(DES.decryptDES(getRequest().getParameter("p_idcard")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_email"))){
			applyLoanInfo.setEmail(DES.decryptDES(getRequest().getParameter("p_email")));
		}
		
		if(DataUtils.notEmpty(getRequest().getParameter("p_nativeAddress"))){
			applyLoanInfo.setNativeAddress(DES.decryptDES(getRequest().getParameter("p_nativeAddress")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_nativeInfo"))){
			applyLoanInfo.setNativeInfo(DES.decryptDES(getRequest().getParameter("p_nativeInfo")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_address"))){
			applyLoanInfo.setAddress(DES.decryptDES(getRequest().getParameter("p_address")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_liveStyle"))){
			applyLoanInfo.setLiveStyle(getRequest().getParameter("p_liveStyle"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_education"))){
			applyLoanInfo.setEducation(getRequest().getParameter("p_education"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_marriage"))){
			applyLoanInfo.setMarriage(getRequest().getParameter("p_marriage"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_house"))){
			applyLoanInfo.setHouse(getRequest().getParameter("p_house"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_hasCreditchar"))){
			applyLoanInfo.setHasCredit(getRequest().getParameter("p_hasCreditchar"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_creditCardchar"))){
			applyLoanInfo.setHasCredit(DES.decryptDES(getRequest().getParameter("p_creditCardchar")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_industry"))){
			applyLoanInfo.setIndustry(getRequest().getParameter("p_industry"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_jobStyle"))){
			applyLoanInfo.setJobStyle(getRequest().getParameter("p_jobStyle"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_job"))){
			applyLoanInfo.setJob(getRequest().getParameter("p_job"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_jobCity"))){
			applyLoanInfo.setJobCity(getRequest().getParameter("p_jobCity"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_company"))){
			applyLoanInfo.setCompany(getRequest().getParameter("p_company"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_publicTelchar"))){
			applyLoanInfo.setPublicTel(getRequest().getParameter("p_publicTelchar"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_salaryFromBankchar"))){
			applyLoanInfo.setSalaryFromBank(getRequest().getParameter("p_salaryFromBankchar"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_yearIncome"))){
			applyLoanInfo.setYearIncome(getRequest().getParameter("p_yearIncome"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_monthBack"))){
			applyLoanInfo.setMonthBack(DataUtils.str2double(getRequest().getParameter("p_monthBack"), 6));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_urgencyPerson"))){
			applyLoanInfo.setUrgencyPerson(getRequest().getParameter("p_urgencyPerson"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_urgencyPhone"))){
			applyLoanInfo.setUrgencyPhone(getRequest().getParameter("p_urgencyPhone"));
		}
		
		if(DataUtils.notEmpty(getRequest().getParameter("p_datums"))){
			applyLoanInfo.setDatums(getRequest().getParameter("p_datums"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_assetCertificates"))){
			applyLoanInfo.setAssetCertificates(getRequest().getParameter("p_assetCertificates"));
		}
		return applyLoanInfo;
	}
	
	/**   
	 * 增加申请普通和急速模式贷款的申请信息
	 * @Title: insertApplyLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月8日 上午10:46:49
	 * @return: void      
	 * @throws
	 * 
	 */  
	public void insertApplyLoanInfo(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			//判断参数是否为空
			if(DataUtils.notEmpty(token)){
				ApplyLoanInfo applyLoanInfo = getApplyLoanInfo();
				success = applyLoanInfoService.saveEntity(applyLoanInfo);
				if(!success){
					msg = "服务器超时，请稍后重试";
				}
			}else{
				msg = "用户token为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ApplyLoanInfoAction——insertApplyLoanInfo方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ApplyLoanInfoAction——insertApplyLoanInfo方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
}
