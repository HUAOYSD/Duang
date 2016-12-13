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
			String memberInfoId = MemberCollection.getInstance(token,memberInfoService).getMainField(token);
			loanMemberExtraInfo.setMemberId(memberInfoId);
		}
		
		if(DataUtils.notEmpty(getRequest().getParameter("compName"))){
			loanMemberExtraInfo.setCompName(getRequest().getParameter("compName"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compAddress"))){
			loanMemberExtraInfo.setCompAddress(getRequest().getParameter("compAddress"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compType"))){
			loanMemberExtraInfo.setCompType(getRequest().getParameter("compType"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compPhone"))){
			loanMemberExtraInfo.setCompPhone(getRequest().getParameter("compPhone"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("jobDepart"))){
			loanMemberExtraInfo.setJobDepart(getRequest().getParameter("jobDepart"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("jobEntryTime"))){
			loanMemberExtraInfo.setJobEntryTime(DateUtils.str2Date(getRequest().getParameter("jobEntryTime"), "yyyy-MM-dd"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("jobIncome"))){
			loanMemberExtraInfo.setJobIncome(getRequest().getParameter("jobIncome"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("jobSalaryDate"))){
			loanMemberExtraInfo.setJobSalaryDate(DateUtils.str2Date(getRequest().getParameter("jobSalaryDate"), "yyyy-MM-dd"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("familyName"))){
			loanMemberExtraInfo.setFamilyName(getRequest().getParameter("familyName"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("familyType"))){
			loanMemberExtraInfo.setFamilyType(getRequest().getParameter("familyType"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("familyPhone"))){
			loanMemberExtraInfo.setFamilyPhone(getRequest().getParameter("familyPhone"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("familyCompName"))){
			loanMemberExtraInfo.setFamilyCompName(getRequest().getParameter("familyCompName"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compColleName"))){
			loanMemberExtraInfo.setCompColleName(getRequest().getParameter("compColleName"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compCollePost"))){
			loanMemberExtraInfo.setCompCollePost(getRequest().getParameter("compCollePost"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("compCollePhone"))){
			loanMemberExtraInfo.setCompCollePhone(getRequest().getParameter("compCollePhone"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("otherName"))){
			loanMemberExtraInfo.setOtherName(getRequest().getParameter("otherName"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("otherType"))){
			loanMemberExtraInfo.setOtherType(getRequest().getParameter("otherType"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("otherPhone"))){
			loanMemberExtraInfo.setOtherPhone(getRequest().getParameter("otherPhone"));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("otherComp"))){
			loanMemberExtraInfo.setOtherComp(getRequest().getParameter("otherComp"));
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
				LoanMemberExtraInfo loanMemberExtraInfo = getLoanMemberExtraInfo();
				success = loanMemberExtraInfoService.saveEntity(loanMemberExtraInfo);
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
