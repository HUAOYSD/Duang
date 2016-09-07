package org.duang.action.provider;
import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.SMS;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanMember;
import org.duang.entity.MemberInfo;
import org.duang.service.MemberInfoService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.MD5Utils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————注册Action
 * @ClassName:  RegisterAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_register")
public class RegisterAction extends BaseAction<MemberInfo>{

	private MemberInfoService service;

	@Resource
	public void setService(MemberInfoService service) {
		this.service = service;
	}


	/**   
	 * 检查手机号码是否存在
	 * @Title: checkPhone   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年9月5日 上午11:03:34
	 * @return: void      
	 * @throws   
	 */  
	public void checkPhone(){
		boolean success = false;
		try {
			String phone = getRequest().getParameter("phoneNum");
			if (DataUtils.notEmpty(phone)) {
				phone = DES.decryptBasedDes(phone);
				entity = service.findEntity("phone", phone.trim());
				if (entity != null) {
					msg = "您已注册，请直接登录";
				} else {
					success = true;
				}
			}else {
				msg = "手机号码为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("RegisterAction——checkPhone方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("RegisterAction——checkPhone方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}



	/**   
	 * 发送注册验证码
	 * @Title: sendValidateCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年9月5日 上午11:16:39
	 * @return: void      
	 * @throws   
	 */  
	public void sendValidateCode(){
		boolean success = false;
		try {
			String phone = getRequest().getParameter("phoneNum");
			if (DataUtils.notEmpty(phone)) {
				phone = DES.decryptBasedDes(phone);
				String platform = getRequest().getParameter("platform");
				if (DataUtils.notEmpty(platform) && ("IOS".equals(platform) || "Android".equals(platform))) {
					String signature = getRequest().getParameter("signature");
					if ("IOS".equals(platform) && SMS.IOS_SIGNATURE.equals(signature)) {
						jsonObject.put("vc", "123456");
						success = true;
					}else if ("Android".equals(platform) && SMS.ANDORID_SIGNATURE.equals(signature)) {
						jsonObject.put("vc", "654321");
						success = true;
					}else {
						msg = "短信签名错误";
					}
				}else {
					msg = "缺少平台参数";
				}
			}else {
				msg = "手机号码为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("RegisterAction——sendValidateCode方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("RegisterAction——sendValidateCode方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 注册
	 * @Title: register   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年9月5日 上午11:27:36
	 * @return: void      
	 * @throws   
	 */  
	public void register(){
		boolean success = false;
		try {
			String phone = getRequest().getParameter("phoneNum");
			if (DataUtils.notEmpty(phone)) {
				phone = DES.decryptBasedDes(phone);
				String password = getRequest().getParameter("pwd");
				if (DataUtils.notEmpty(password)) {
					entity.setPhone(password);
					entity.setPassword(MD5Utils.md5(password));
					entity.setId(DataUtils.randomUUID());
					//附加投资用户身份
					LoanMember loanMember = new LoanMember();
					loanMember.setId(DataUtils.randomUUID());
					entity.getLoanMembers().add(loanMember);
					//附加理财用户身份
					InvestMember investMember = new InvestMember();
					investMember.setId(DataUtils.randomUUID());
					investMember.setIsContract(0);//非契约用户
					entity.getInvestMembers().add(investMember);
					if (service.saveEntity(entity)) {
						success = true;
					}else {
						msg = "注册失败";
					}
				}else{
					msg = "密码不能为空";
				}
			}else {
				msg = "手机号码为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("RegisterAction——sendValidateCode方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("RegisterAction——sendValidateCode方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

}
