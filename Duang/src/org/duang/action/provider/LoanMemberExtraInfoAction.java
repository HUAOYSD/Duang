package org.duang.action.provider;
import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.LoanMemberExtraInfo;
import org.duang.service.LoanMemberExtraInfoService;
import org.duang.service.MemberInfoService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 借贷人家庭联系人和公司信息
 * @ClassName:  LoanMemberExtraInfoAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_loanMemberExtra")
public class LoanMemberExtraInfoAction extends BaseAction<LoanMemberExtraInfo>{

	private LoanMemberExtraInfoService loanMemberExtraInfoService;
	@Resource(name = "loanmemberextrainfoserviceimpl")
	public void setService(LoanMemberExtraInfoService loanMemberExtraInfoService) {
		this.loanMemberExtraInfoService = loanMemberExtraInfoService;
	}
	
	private MemberInfoService memberInfoService;
	@Resource
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
	}
	
	/**
	 * 封装参数
	 * @Title: getLoanMemberExtraInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年12月8日 下午5:05:54
	 * @return: LoanMemberExtraInfo      
	 * @throws
	 */
	private LoanMemberExtraInfo getLoanMemberExtraInfo() throws Exception{
		LoanMemberExtraInfo loanMemberExtraInfo = new LoanMemberExtraInfo(DataUtils.randomUUID());
		String token = getRequest().getParameter("token");
		if(DataUtils.notEmpty(token)){
			String memberInfoId = MemberCollection.getInstance(DES.decryptDES(token),memberInfoService).getMainField(DES.decryptDES(token));
			loanMemberExtraInfo.setMemberId(memberInfoId);
		}
		
		if(DataUtils.notEmpty(getRequest().getParameter("compName"))){
			loanMemberExtraInfo.setCompName(DES.decryptDES(getRequest().getParameter("compName")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compAddress"))){
			loanMemberExtraInfo.setCompAddress(DES.decryptDES(getRequest().getParameter("compAddress")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compType"))){
			loanMemberExtraInfo.setCompType(DES.decryptDES(getRequest().getParameter("compType")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compPhone"))){
			loanMemberExtraInfo.setCompPhone(DES.decryptDES(getRequest().getParameter("compPhone")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("jobDepart"))){
			loanMemberExtraInfo.setJobDepart(DES.decryptDES(getRequest().getParameter("jobDepart")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("jobEntryTime"))){
			loanMemberExtraInfo.setJobEntryTime(DateUtils.str2Date(DES.decryptDES(getRequest().getParameter("jobEntryTime")), "yyyy-MM-dd"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("jobIncome"))){
			loanMemberExtraInfo.setJobIncome(DES.decryptDES(getRequest().getParameter("jobIncome")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("jobSalaryDate"))){
			loanMemberExtraInfo.setJobSalaryDate(DateUtils.str2Date(DES.decryptDES(getRequest().getParameter("jobSalaryDate")), "yyyy-MM-dd"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("familyName"))){
			loanMemberExtraInfo.setFamilyName(DES.decryptDES(getRequest().getParameter("familyName")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("familyType"))){
			loanMemberExtraInfo.setFamilyType(DES.decryptDES(getRequest().getParameter("familyType")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("familyPhone"))){
			loanMemberExtraInfo.setFamilyPhone(DES.decryptDES(getRequest().getParameter("familyPhone")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("familyCompName"))){
			loanMemberExtraInfo.setFamilyCompName(DES.decryptDES(getRequest().getParameter("familyCompName")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compColleName"))){
			loanMemberExtraInfo.setCompColleName(DES.decryptDES(getRequest().getParameter("compColleName")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compCollePost"))){
			loanMemberExtraInfo.setCompCollePost(DES.decryptDES(getRequest().getParameter("compCollePost")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compCollePhone"))){
			loanMemberExtraInfo.setCompCollePhone(DES.decryptDES(getRequest().getParameter("compCollePhone")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("otherName"))){
			loanMemberExtraInfo.setOtherName(DES.decryptDES(getRequest().getParameter("otherName")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("otherType"))){
			loanMemberExtraInfo.setOtherType(DES.decryptDES(getRequest().getParameter("otherType")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("otherPhone"))){
			loanMemberExtraInfo.setOtherPhone(DES.decryptDES(getRequest().getParameter("otherPhone")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("otherComp"))){
			loanMemberExtraInfo.setOtherComp(DES.decryptDES(getRequest().getParameter("otherComp")));
		}
		return loanMemberExtraInfo;
	}
	
	
	/**
	 * 添加借贷人的附加信息（亲属和公司信息） 
	 * 
	 * @Title: insertLoanMemberExtraInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月8日 上午10:59:55
	 * @return: void      
	 * @throws
	 */
	public void insertLoanMemberExtraInfo(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			//判断参数是否为空
			if(DataUtils.notEmpty(token)){
				 entity= getLoanMemberExtraInfo();
				 LoanMemberExtraInfo loanMemberExtraInfo = loanMemberExtraInfoService.findEntity("memberId", entity.getMemberId());
				 if(loanMemberExtraInfo != null){
					 entity.setId(loanMemberExtraInfo.getId()); 
					 success = loanMemberExtraInfoService.updateEntity(entity);
				 }else {
					 success = loanMemberExtraInfoService.saveEntity(entity);
				 }
				 if(!success){
					msg = "服务器超时，请稍后重试";
				 }
			}else{
				msg = "用户token为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("LoanMemberExtraInfoAction——insertLoanMemberExtraInfo方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("LoanMemberExtraInfoAction——insertLoanMemberExtraInfo方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
}
