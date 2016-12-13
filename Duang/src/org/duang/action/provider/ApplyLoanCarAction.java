package org.duang.action.provider;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
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
import org.duang.enums.UploadFile;
import org.duang.enums.loan.Apply;
import org.duang.enums.loan.BackStyle;
import org.duang.enums.loan.ReturnStatus;
import org.duang.enums.loan.LoanMode;
import org.duang.enums.loan.Poundage;
import org.duang.enums.loan.Scale;
import org.duang.enums.loan.TakeMoney;
import org.duang.service.ApplyLoanCarService;
import org.duang.service.MemberInfoService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.ImageString;
import org.duang.util.ReadProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————车产模式贷款的申请信息Action
 * @ClassName:  ApplyLoanCarAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
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
	
	private MemberInfoService memberInfoService;
	@Resource
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
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
		loanList.setIsSell(Scale.S1.getVal());
		loanList.setPoundageState(Poundage.P1.getVal());
		loanList.setReturnStatus(ReturnStatus.B1.getVal());
		loanList.setLoanState(TakeMoney.T1.getVal());
		loanList.setApplyState(Apply.A1.getVal());
		loanList.setLoanType(LoanMode.M1.getVal());
		loanList.setBackStyle(BackStyle.B2.getVal());
		if(DataUtils.notEmpty(getRequest().getParameter("p_days"))){
			loanList.setDays(DataUtils.str2int((DES.decryptDES(getRequest().getParameter("p_days")))));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_loanStyle"))){
			loanList.setLoanStyle(DataUtils.str2int((getRequest().getParameter("p_loanStyle"))));
		}
		if(DataUtils.notEmpty(getRequest().getParameter("p_loanUse"))){
			loanList.setLoanUse((getRequest().getParameter("p_loanUse")));
		}
		String token = getRequest().getParameter("token");
		if(DataUtils.notEmpty(token)){
			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setId(MemberCollection.getInstance(token,memberInfoService).getMainField(token));
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
		return applyLoanCar;
	}
	
	
	/**   
	 * 增加车产模式的借贷信息
	 * @Title: insertApplyCarLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
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
				jsonObject.put("id", applyLoanCar.getId());
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
				String suffPath = UploadFile.PATH.getVal(UploadFile.DATUMS_CAR.getVal(memberInfoId))+"\\";
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
					LoggerUtils.info("userId:" + memberInfoId + "----------------上传车贷资料：" + success, this.getClass());
					if (success) {
						datumsData.append(fileName);
						if(i<entity.getNum()-1){
							datumsData.append(";");
						}
					}
				}
				ApplyLoanCar applyLoanCar = applyLoanCarService.findById(id);
				String oldValue="";
				if(DataUtils.notEmpty(applyLoanCar.getDatums())){
					oldValue = applyLoanCar.getDatums()+";"+datumsData.toString();
				}else{
					oldValue = datumsData.toString();
				}
				applyLoanCar.setDatums(oldValue);
				success = applyLoanCarService.updateEntity(applyLoanCar);
			}else{
				msg = "缺少参数,请补充";
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("ApplyLoanCarAction uploadUseDatums：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ApplyLoanCarAction uploadUseDatums：" + e.getLocalizedMessage(), this.getClass());
		}    
		jsonObject.put("success", success);
		jsonObject.put("success", msg);
		printJsonResult();
	}
	
	/**
	 * 上传个人证明
	 * @Title: uploadUserAsset   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月7日 上午10:05:16
	 * @return: void      
	 * @throws
	 */
	public void uploadUserAsset(){
		boolean success=false;
		try{
			String id = getRequest().getParameter("id");
			String token = getRequest().getParameter("token");
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id)) {
				String memberInfoId = MemberCollection.getInstance(token, memberInfoService).getMainField(token);
				//跟路径
				String temPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
				//详细路径
				String suffPath = UploadFile.PATH.getVal(UploadFile.ASSET_CAR.getVal(memberInfoId))+"\\";
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
					LoggerUtils.info("userId:" + memberInfoId + "----------------上传车贷证明：" + success, this.getClass());
					if (success) {
						datumsData.append(fileName);
						if(i<entity.getNum()-1){
							datumsData.append(";");
						}
					}
				}
				ApplyLoanCar applyLoanCar = applyLoanCarService.findById(id);
				String oldValue="";
				if(DataUtils.notEmpty(applyLoanCar.getAssetCertificates())){
					oldValue = applyLoanCar.getAssetCertificates()+";"+datumsData.toString();
				}else{
					oldValue = datumsData.toString();
				}
				applyLoanCar.setAssetCertificates(oldValue);
				success = applyLoanCarService.updateEntity(applyLoanCar);
			}else{
				msg = "缺少参数,请补充";
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("ApplyLoanCarAction uploadUserAsset：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ApplyLoanCarAction uploadUserAsset：" + e.getLocalizedMessage(), this.getClass());
		}    
		jsonObject.put("success", success);
		jsonObject.put("success", msg);
		printJsonResult();
	}
}
