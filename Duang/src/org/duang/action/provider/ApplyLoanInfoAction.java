package org.duang.action.provider;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.ApplyLoanInfo;
import org.duang.entity.LoanList;
import org.duang.entity.LoanListRate;
import org.duang.entity.MemberInfo;
import org.duang.enums.UploadFile;
import org.duang.enums.loan.Apply;
import org.duang.enums.loan.BackStyle;
import org.duang.enums.loan.LoanMode;
import org.duang.enums.loan.Poundage;
import org.duang.enums.loan.ReturnStatus;
import org.duang.enums.loan.Scale;
import org.duang.enums.loan.TakeMoney;
import org.duang.service.ApplyLoanInfoService;
import org.duang.service.LoanListRateService;
import org.duang.service.MemberInfoService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.ImageString;
import org.duang.util.ReadProperties;
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
	private MemberInfoService memberInfoService;
	@Resource
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
	}
	private LoanListRateService loanListRateService;
	@Resource
	public void setLoanListRateService(LoanListRateService loanListRateService) {
		this.loanListRateService = loanListRateService;
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
	private ApplyLoanInfo getApplyLoanInfo(String token) throws Exception{
		ApplyLoanInfo applyLoanInfo = new ApplyLoanInfo(DataUtils.randomUUID());
		
		LoanList loanList = new LoanList(DataUtils.randomUUID());
		loanList.setIsSell(Scale.S1.getVal());
		loanList.setPoundageState(Poundage.P1.getVal());
		loanList.setReturnStatus(ReturnStatus.B1.getVal());
		loanList.setLoanState(TakeMoney.T1.getVal());
		loanList.setApplyState(Apply.A1.getVal());
		loanList.setLoanType(LoanMode.M3.getVal());
		loanList.setBackStyle(BackStyle.B1.getVal());
		loanList.setCreateTime(new Date());
		if(DataUtils.notEmpty(getRequest().getParameter("p_days"))){
			loanList.setDays(DataUtils.str2int(DES.decryptDES(getRequest().getParameter("p_days"))));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_loanStyle"))){
			loanList.setLoanStyle(DataUtils.str2int(DES.decryptDES(getRequest().getParameter("p_loanStyle"))));
		}
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setId(MemberCollection.getInstance(DES.decryptDES(token),memberInfoService).getMainField(DES.decryptDES(token)));
		loanList.setMemberInfo(memberInfo);
		
		if(DataUtils.notEmpty(getRequest().getParameter("p_money"))){
			double  money  = DataUtils.str2double(DES.decryptDES(getRequest().getParameter("p_money")), 6);
			loanList.setMoney(money);
			loanList.setRealMoney(money);
			LoanListRate loanListRate = loanListRateService.getLoanListRate();
			if(loanListRate != null){
				loanList.setManageCost(loanListRate.getPlatformRate()*money);
				loanList.setPoundage(loanListRate.getHandRate()*money);
				loanList.setLoanInterest(loanListRate.getLoanRate()*money);
				loanList.setGetMoney(money);
				loanList.setReturnMoney(DataUtils.str2double(String.valueOf(money+loanList.getManageCost()+loanList.getPoundage()+loanList.getLoanInterest()), 6));
			}
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_loanUse"))){
			loanList.setLoanUse(DES.decryptDES(getRequest().getParameter("p_loanUse")));
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
			applyLoanInfo.setLiveStyle(DES.decryptDES(getRequest().getParameter("p_liveStyle")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_education"))){
			applyLoanInfo.setEducation(DES.decryptDES(getRequest().getParameter("p_education")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_marriage"))){
			applyLoanInfo.setMarriage(DES.decryptDES(getRequest().getParameter("p_marriage")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_house"))){
			applyLoanInfo.setHouse(DES.decryptDES(getRequest().getParameter("p_house")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_hasCreditchar"))){
			applyLoanInfo.setHasCredit(DES.decryptDES(getRequest().getParameter("p_hasCreditchar")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_creditCardchar"))){
			applyLoanInfo.setHasCredit(DES.decryptDES(getRequest().getParameter("p_creditCardchar")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_industry"))){
			applyLoanInfo.setIndustry(DES.decryptDES(getRequest().getParameter("p_industry")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_jobStyle"))){
			applyLoanInfo.setJobStyle(DES.decryptDES(getRequest().getParameter("p_jobStyle")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_job"))){
			applyLoanInfo.setJob(DES.decryptDES(getRequest().getParameter("p_job")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_jobCity"))){
			applyLoanInfo.setJobCity(DES.decryptDES(getRequest().getParameter("p_jobCity")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_company"))){
			applyLoanInfo.setCompany(DES.decryptDES(getRequest().getParameter("p_company")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_publicTelchar"))){
			applyLoanInfo.setPublicTel(DES.decryptDES(getRequest().getParameter("p_publicTelchar")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_salaryFromBankchar"))){
			applyLoanInfo.setSalaryFromBank(DES.decryptDES(getRequest().getParameter("p_salaryFromBankchar")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_yearIncome"))){
			applyLoanInfo.setYearIncome(DES.decryptDES(getRequest().getParameter("p_yearIncome")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_monthBack"))){
			applyLoanInfo.setMonthBack(DataUtils.str2double(DES.decryptDES(getRequest().getParameter("p_monthBack")), 6));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_urgencyPerson"))){
			applyLoanInfo.setUrgencyPerson(DES.decryptDES(getRequest().getParameter("p_urgencyPerson")));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_urgencyPhone"))){
			applyLoanInfo.setUrgencyPhone(DES.decryptDES(getRequest().getParameter("p_urgencyPhone")));
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
		LoggerUtils.info("\t\n-------------借贷申请开始", this.getClass());
		try {
			String token = getRequest().getParameter("token");
			//判断参数是否为空
			if(DataUtils.notEmpty(token)){
				ApplyLoanInfo applyLoanInfo = getApplyLoanInfo(token);
				success = applyLoanInfoService.saveEntity(applyLoanInfo);
				if(!success){
					msg = "服务器超时，请稍后重试";
				}
				jsonObject.put("id", applyLoanInfo.getId());
			}else{
				msg = "用户token为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ApplyLoanInfoAction——insertApplyLoanInfo方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ApplyLoanInfoAction——insertApplyLoanInfo方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		LoggerUtils.info("\t\n-------------借贷申请结束："+success, this.getClass());
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 上传个人资料
	 * @Title: uploadUseDatums   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月7日 上午10:05:16
	 * @return: void      
	 * @throws
	 */
	public void uploadUserDatums(){
		boolean success=false;
		try{
			String id = getRequest().getParameter("id");
			String token = getRequest().getParameter("token");
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id)) {
				String memberInfoId = MemberCollection.getInstance(token, memberInfoService).getMainField(token);
				//跟路径
				String temPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
				//详细路径
				String suffPath = UploadFile.PATH.getVal(UploadFile.DATUMS_CREDIT.getVal(memberInfoId))+"\\";
				//备份跟路径
				String backupPath = ReadProperties.getStringValue(ReadProperties.initPrperties("backupdb.properties"), "fileBasicPath");
				// 遍历图片数量
				ArrayList<String> imgDataList = new ArrayList<String>();
				imgDataList.add(entity.getImg_1());
				imgDataList.add(entity.getImg_2());
				imgDataList.add(entity.getImg_3());
				imgDataList.add(entity.getImg_4());
				imgDataList.add(entity.getImg_5());
				imgDataList.add(entity.getImg_6());
				imgDataList.add(entity.getImg_7());
				imgDataList.add(entity.getImg_8());
				imgDataList.add(entity.getImg_9());
				//拼接资料文件名字
				StringBuffer datumsData = new StringBuffer("");
				for (int i = 0; i < entity.getNum(); i++) {
					//文件名称
					String fileName = DataUtils.randomUUID()+".jpg";
					String fullpath = DataUtils.fileUploadPath(temPath, suffPath, fileName);
					success = ImageString.generateImage(imgDataList.get(i), fullpath);
					//备份路径
					ImageString.generateImage(imgDataList.get(i), DataUtils.fileUploadPath(backupPath, suffPath, fileName));
					LoggerUtils.info("userId:" + memberInfoId + "----------------上传信贷资料：" + success, this.getClass());
					if (success) {
						datumsData.append(fileName);
						if(i<entity.getNum()-1){
							datumsData.append(";");
						}
					}
				}
				ApplyLoanInfo applyLoanInfo = applyLoanInfoService.findById(id);
				
				String datumsFile="";
				if(DataUtils.notEmpty(applyLoanInfo.getDatums())){
					datumsFile = applyLoanInfo.getDatums()+";"+datumsData.toString();
				}else{
					datumsFile = datumsData.toString();
				}
				String sql = "UPDATE apply_loan_info SET datums='"+datumsFile+"' where id='"+id+"'";
				success = applyLoanInfoService.executeSql(sql);
			}else{
				msg = "缺少参数,请补充";
			}
		}catch(Exception e){
			msg = "服务器维护，请稍后再试";
			e.printStackTrace();
			LoggerUtils.error("ApplyLoanInfoAction uploadUseDatums：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ApplyLoanInfoAction uploadUseDatums：" + e.getLocalizedMessage(), this.getClass());
		}    
		jsonObject.put("success", success);
		jsonObject.put("msg", msg);
		printJsonResult();
	}
	
	/**
	 * 上传证明
	 * @Title: uploadUseAsset   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月7日 上午10:05:16
	 * @return: void      
	 * @throws
	 */
	public void uploadUserAttest(){
		boolean success=false;
		try{
			String id = getRequest().getParameter("id");
			String token = getRequest().getParameter("token");
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id)) {
				String memberInfoId = MemberCollection.getInstance(token, memberInfoService).getMainField(token);
				//跟路径
				String temPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
				//详细路径
				String suffPath = UploadFile.PATH.getVal(UploadFile.ASSET_CREDIT.getVal(memberInfoId))+"\\";
				//备份跟路径
				String backupPath = ReadProperties.getStringValue(ReadProperties.initPrperties("backupdb.properties"), "fileBasicPath");
				// 遍历图片数量
				ArrayList<String> imgDataList = new ArrayList<String>();
				imgDataList.add(entity.getImg_1());
				imgDataList.add(entity.getImg_2());
				imgDataList.add(entity.getImg_3());
				imgDataList.add(entity.getImg_4());
				imgDataList.add(entity.getImg_5());
				imgDataList.add(entity.getImg_6());
				imgDataList.add(entity.getImg_7());
				imgDataList.add(entity.getImg_8());
				imgDataList.add(entity.getImg_9());
				//拼接资料文件名字
				StringBuffer datumsData = new StringBuffer("");
				for (int i = 0; i < entity.getNum(); i++) {
					//文件名称
					String fileName = DataUtils.randomUUID()+".jpg";
					String fullpath = DataUtils.fileUploadPath(temPath, suffPath, fileName);
					success = ImageString.generateImage(imgDataList.get(i), fullpath);
					//备份路径
					ImageString.generateImage(imgDataList.get(i), DataUtils.fileUploadPath(backupPath, suffPath, fileName));
					LoggerUtils.info("userId:" + memberInfoId + "----------------上传信贷证明：" + success, this.getClass());
					if (success) {
						datumsData.append(fileName);
						if(i<entity.getNum()-1){
							datumsData.append(";");
						}
					}
				}
				ApplyLoanInfo applyLoanInfo = applyLoanInfoService.findById(id);
				
				String assetFile="";
				if(DataUtils.notEmpty(applyLoanInfo.getAssetCertificates())){
					assetFile = applyLoanInfo.getAssetCertificates()+";"+datumsData.toString();
				}else{
					assetFile = datumsData.toString();
				}
				String sql = "UPDATE apply_loan_info SET asset_certificates='"+assetFile+"' where id='"+id+"'";
				applyLoanInfoService.executeSql(sql);
			}else{
				msg = "缺少参数,请补充";
			}
		}catch(Exception e){
			msg = "服务器维护，请稍后再试";
			e.printStackTrace();
			LoggerUtils.error("ApplyLoanInfoAction uploadUserAttest：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ApplyLoanInfoAction uploadUserAttest：" + e.getLocalizedMessage(), this.getClass());
		}    
		jsonObject.put("success", success);
		jsonObject.put("msg", msg);
		printJsonResult();
	}
	
	/**
	 * 上传征信报告
	 * @Title: uploadUserCreditFile   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月7日 上午10:05:16
	 * @return: void      
	 * @throws
	 */
	public void uploadUserCreditFile(){
		boolean success=false;
		try{
			String id = getRequest().getParameter("id");
			String token = getRequest().getParameter("token");
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id)) {
				String memberInfoId = MemberCollection.getInstance(token, memberInfoService).getMainField(token);
				//跟路径
				String temPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
				//详细路径
				String suffPath = UploadFile.PATH.getVal(UploadFile.FILE_CREDIT.getVal(memberInfoId))+"\\";
				//备份跟路径
				String backupPath = ReadProperties.getStringValue(ReadProperties.initPrperties("backupdb.properties"), "fileBasicPath");
				// 遍历图片数量
				ArrayList<String> imgDataList = new ArrayList<String>();
				imgDataList.add(entity.getImg_1());
				imgDataList.add(entity.getImg_2());
				imgDataList.add(entity.getImg_3());
				imgDataList.add(entity.getImg_4());
				imgDataList.add(entity.getImg_5());
				imgDataList.add(entity.getImg_6());
				imgDataList.add(entity.getImg_7());
				imgDataList.add(entity.getImg_8());
				imgDataList.add(entity.getImg_9());
				//拼接资料文件名字
				StringBuffer datumsData = new StringBuffer("");
				for (int i = 0; i < entity.getNum(); i++) {
					//文件名称
					String fileName = DataUtils.randomUUID()+".jpg";
					String fullpath = DataUtils.fileUploadPath(temPath, suffPath, fileName);
					success = ImageString.generateImage(imgDataList.get(i), fullpath);
					//备份路径
					ImageString.generateImage(imgDataList.get(i), DataUtils.fileUploadPath(backupPath, suffPath, fileName));
					LoggerUtils.info("userId:" + memberInfoId + "----------------上传信贷征信报告：" + success, this.getClass());
					if (success) {
						datumsData.append(fileName);
						if(i<entity.getNum()-1){
							datumsData.append(";");
						}
					}
				}
				ApplyLoanInfo applyLoanInfo = applyLoanInfoService.findById(id);
				String creditFile="";
				if(DataUtils.notEmpty(applyLoanInfo.getCreditFile())){
					creditFile = applyLoanInfo.getCreditFile()+";"+datumsData.toString();
				}else{
					creditFile = datumsData.toString();
				}
				String sql = "UPDATE apply_loan_info SET credit_file='"+creditFile+"' where id='"+id+"'";
				applyLoanInfoService.executeSql(sql);
			}else{
				msg = "缺少参数,请补充";
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("ApplyLoanInfoAction uploadUserCreditFile：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ApplyLoanInfoAction uploadUserCreditFile：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}    
		jsonObject.put("success", success);
		jsonObject.put("msg", msg);
		printJsonResult();
	}
}
