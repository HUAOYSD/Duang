package org.duang.action.provider;
import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.SMSUtils;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanMember;
import org.duang.entity.MemberInfo;
import org.duang.service.MemberInfoService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————注册Action
 * @ClassName:  RegisterAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
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
	 * @author 5y    
	 * @date 2016年9月5日 上午11:03:34
	 * @return: void      
	 * @throws   
	 */  
	public void checkPhone(){
		boolean success = false;
		try {
			String phone = getRequest().getParameter("phoneNum");
			if (DataUtils.notEmpty(phone)) {
				phone = DES.decryptDES(phone);
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
	 * @author 5y    
	 * @date 2016年9月5日 上午11:16:39
	 * @return: void      
	 * @throws   
	 */  
	public void sendValidateCode(){
		/*Map<String, String[]> map = getRequest().getParameterMap();
		for (Entry<String, String[]> obj : map.entrySet()) {
			System.out.println(obj.getValue().length+"\t"+obj.getKey()+"\t"+obj.getValue()[0].toString()+"\t");
		}*/
		boolean success = false;
		try {
			String phone = getRequest().getParameter("phoneNum");
			if (DataUtils.notEmpty(phone)) {
				phone = DES.decryptDES(phone);
				String platform = getRequest().getParameter("platform");
				if (DataUtils.notEmpty(platform) && ("IOS".equals(platform) || "Android".equals(platform))) {
					String signature = getRequest().getParameter("signature");
					String vc = DataUtils.sixNumber();
					//String content = "您的注册验证码是："+vc;
					if ("IOS".equals(platform) && SMSUtils.IOS_SIGNATURE.equals(signature)) {
						jsonObject.put("vc", vc);
						//success = SMSUtils.getInstance().sendSMS(content, phone, Platform.P3.getVal());
						success = true;
					}else if ("Android".equals(platform) && SMSUtils.ANDORID_SIGNATURE.equals(signature)) {
						jsonObject.put("vc", vc);
						//success = SMSUtils.getInstance().sendSMS(content, phone, Platform.P2.getVal());
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
	 * @author 5y    
	 * @date 2016年9月5日 上午11:27:36
	 * @return: void      
	 * @throws   
	 */  
	public void register(){
		boolean success = false;
		try {
			String phone = getRequest().getParameter("phoneNum");
			if (DataUtils.notEmpty(phone)) {
				phone = DES.decryptDES(phone);
				String password = getRequest().getParameter("pwd");
				String registerStyle = getRequest().getParameter("registerStyle");
				if (DataUtils.notEmpty(password) && DataUtils.notEmpty(registerStyle)) {
					String id = DataUtils.randomUUID();
					entity.setId(id);
					entity.setPhone(password);
					entity.setPassword(DES.encryptDES(password));
					entity.setNickname("手机用户"+phone);
					entity.setLoginName(getRequest().getParameter("phoneNum"));
					entity.setRegisterStyle(Integer.parseInt(registerStyle));
					//附加投资用户身份
					LoanMember loanMember = new LoanMember();
					loanMember.setId(DataUtils.randomUUID());
					loanMember.setMemberInfo(entity);
					entity.getLoanMembers().add(loanMember);
					//附加理财用户身份
					InvestMember investMember = new InvestMember();
					investMember.setMemberInfo(entity);
					investMember.setId(DataUtils.randomUUID());
					investMember.setIsContract(0);//非契约用户
					entity.getInvestMembers().add(investMember);
					if (service.saveEntity(entity)) {
						success = true;
						jsonObject.put("id", DES.encryptDES(id));
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
			LoggerUtils.error("RegisterAction——register方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("RegisterAction——register方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 完善注册信息
	 * @Title: fullInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月23日 下午4:08:08
	 * @return: void      
	 * @throws   
	 */  
	public void fullInfo() {
		boolean success = false;
		try {
			String id = getRequest().getParameter("p_id");
			String idcard = getRequest().getParameter("p_idcard");
			String email = getRequest().getParameter("p_email");
			String name = getRequest().getParameter("p_name");
			if (DataUtils.notEmpty(id) && DataUtils.notEmpty(idcard) && DataUtils.notEmpty(email)) {
				id = DES.decryptDES(id);
				idcard = DES.decryptDES(idcard);
				email = DES.decryptDES(email);
				name = DES.decryptDES(name);
				entity = service.findById(id);
				entity.setRealName(name);
				entity.setIdCard(idcard);
				entity.setEmail(email);
				if (service.updateEntity(entity)) {
					success = true;
				}else {
					msg = "请稍后再试";
				}
			}else {
				msg = "参数不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("RegisterAction——fullInfo方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("RegisterAction——fullInfo方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

}
