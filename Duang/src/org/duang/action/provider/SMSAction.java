package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.SMSUtils;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.SMS;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————短信Action
 * @ClassName:  SMSAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月20日 上午10:43:30      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_sms")
public class SMSAction extends BaseAction<SMS>{

	
	public void sendSMS(){
		boolean success = false;
		try {
			String phone = getRequest().getParameter("phoneNum");
			String content = getRequest().getParameter("p_content");
			if (DataUtils.notEmpty(phone) && DataUtils.notEmpty(content)) {
				phone = DES.decryptDES(phone);
				content = DES.decryptDES(content);
				String platform = getRequest().getParameter("platform");
				if (DataUtils.notEmpty(platform) && ("IOS".equals(platform) || "Android".equals(platform))) {
					String signature = getRequest().getParameter("signature");
					if ("IOS".equals(platform) && SMSUtils.IOS_SIGNATURE.equals(signature)) {
						//success = SMSUtils.getInstance().sendSMS(content, phone, Platform.P3.getVal());
						success = true;
					}else if ("Android".equals(platform) && SMSUtils.ANDORID_SIGNATURE.equals(signature)) {
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
			LoggerUtils.error("SMSAction——sendSMS方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("SMSAction——sendSMS方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
}
