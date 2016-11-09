package org.duang.action.provider;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.SMSUtils;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.SMSVerificationCode;
import org.duang.enums.Platform;
import org.duang.service.SMSVerificationCodeService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————短信验证码Action
 * @ClassName:  SMSVerificationCodeAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_smscode")
public class SMSVerificationCodeAction extends BaseAction<SMSVerificationCode>{
	
	private SMSVerificationCodeService service;
	@Resource
	public void setService(SMSVerificationCodeService service) {
		this.service = service;
	}

	/**
	 * 获取短信验证码
	 * @Title: getVCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月25日 上午9:21:10
	 * @return: void      
	 * @throws
	 */
	public void getVCode(){
		boolean success = false;
		try {
			String phone =  getRequest().getParameter("phone");
			if(DataUtils.isEmpty(phone)){
				msg = "手机号码不对";
			}else {
				//解密
				SMSVerificationCode vCode = service.findEntity("phone", phone);
				if(vCode == null){
					//生成6位数字
					vCode = new SMSVerificationCode();
					vCode.setId(DataUtils.randomUUID());
					vCode.setPhone(phone);
					vCode.setvCode(DataUtils.sixNumber());
					Date currDate = new Date();
					long currDateLong = DateUtils.getTimeStamp(currDate);
					Date endDate = new Date(currDateLong+60*1000*10);
					vCode.setStartTime(currDate);
					vCode.setEndTime(endDate);
					success = service.saveEntity(vCode);
				}else {
					vCode.setvCode(DataUtils.sixNumber());
					Date currDate = new Date();
					long currDateLong = DateUtils.getTimeStamp(currDate);
					Date endDate = new Date(currDateLong+60*1000*10);
					vCode.setStartTime(currDate);
					vCode.setEndTime(endDate);
					success = service.updateEntity(vCode);
				}
				if(success){
					success = sendValidateCode(phone, vCode.getvCode());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("SMSVerificationCodeAction——getVCode方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("SMSVerificationCodeAction——getVCode方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 发送验证码短信
	 * @Title: sendValidateCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param phone
	 * @param: @param vCode
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年10月24日 下午6:39:49
	 * @return: boolean      
	 * @throws
	 */
	private boolean sendValidateCode(String phone,String vCode) throws Exception{
		boolean success = false;
		if (DataUtils.notEmpty(phone)) {
			phone = DES.decryptDES(phone);
			LoggerUtils.info("--------------------发送验证码"+phone+":"+vCode, this.getClass());
			String platform = getRequest().getParameter("platform");
			if (DataUtils.notEmpty(platform) && ("IOS".equals(platform) || "Android".equals(platform))) {
				String signature = getRequest().getParameter("signature");
				String content = "手机验证码是  "+vCode;
				if ("IOS".equals(platform) && SMSUtils.IOS_SIGNATURE.equals(signature)) {
					jsonObject.put("vc", vCode);
					success = SMSUtils.getInstance().sendSMS(content, phone, Platform.P3.getVal());
				}else if ("Android".equals(platform) && SMSUtils.ANDORID_SIGNATURE.equals(signature)) {
					jsonObject.put("vc", vCode);
					success = SMSUtils.getInstance().sendSMS(content, phone, Platform.P2.getVal());
				}else {
					msg = "短信签名错误";
				}
			}else {
				msg = "缺少平台参数";
			}
		}else {
			msg = "手机号码为空";
		}
		return success;
	}
	
	
}
