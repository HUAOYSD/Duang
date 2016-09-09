package org.duang.action.provider;
import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanMember;
import org.duang.entity.MemberInfo;
import org.duang.enums.If;
import org.duang.service.MemberInfoService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————登录Action
 * @ClassName:  LoginAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_login")
public class LoginAction extends BaseAction<MemberInfo>{

	private MemberInfoService service;

	@Resource
	public void setService(MemberInfoService service) {
		this.service = service;
	}


	/**   
	 * 登录
	 * @Title: checkPhone   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年9月5日 上午11:03:34
	 * @return: void      
	 * @throws   
	 */  
	public void login(){
		boolean success = false;
		try {
			String phone = getRequest().getParameter("phoneNum"), pwd = getRequest().getParameter("pwd");
			if (DataUtils.notEmpty(phone) && DataUtils.notEmpty(pwd)) {
				phone = DES.decryptDES(phone);
				entity = service.findEntity("phone", phone);
				if (entity != null) {
					if (pwd.equals(entity.getPassword())) {
						String token = DataUtils.randomUUID();
						fillMemberInfo(token);
						JSONObject resultjson = new JSONObject();
						resultjson.put("pd", jsonObject.optString("pd"));
						resultjson.put("token", jsonObject.optString("token"));
						resultjson.put("id", jsonObject.optString("id"));
						MemberCollection.getInstance().putJsonObject(token, resultjson);
						success = true;
					} else {
						msg = "密码错误";
					}
				} else {
					msg = "该手机号未注册";
				}
			}else{
				msg = "手机号或密码不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("LoginAction——login方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("LoginAction——login方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 根据token获取登录信息
	 * @Title: findInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月6日 上午11:42:25
	 * @return: void      
	 * @throws   
	 */  
	public void findInfo(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token"), pwd = getRequest().getParameter("pwd");
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(pwd)) {
				String pd = MemberCollection.getInstance().getField(token, "pd");
				if (DataUtils.notEmpty(pd)) {
					if (pwd.equals(pd)) {
						entity = service.findById(MemberCollection.getInstance().getMainField(token));
						if (entity != null) {
							fillMemberInfo(token);
							JSONObject resultjson = new JSONObject();
							resultjson.put("pd", jsonObject.optString("pd"));
							resultjson.put("token", jsonObject.optString("token"));
							resultjson.put("id", jsonObject.optString("id"));
							MemberCollection.getInstance().putJsonObject(token, resultjson);
							success = true;
						}else {
							msg = "未获取到该用户";
						}
					}else{
						msg = "密码错误，请重新登录";
					}
				}else{
					msg = "登录失效请重新登录";
				}
			}else{
				msg = "获取失败，请重新登录";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("LoginAction——login方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("LoginAction——login方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 注销
	 * @Title: loginout   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月6日 下午2:53:05
	 * @return: void      
	 * @throws   
	 */  
	public void loginout(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			if (DataUtils.notEmpty(token)) {
				String id = MemberCollection.getInstance().getMainField(token);
				if (DataUtils.notEmpty(id)) {
					MemberCollection.getInstance().removeJsonObject(token, id);
				}
				success = true;
			}else{
				msg = "请重新登录";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("LoginAction——login方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("LoginAction——login方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 填充需要信息
	 * @Title: fillMemberInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberInfo  
	 * @author 白攀    
	 * @date 2016年9月6日 上午11:38:00
	 * @return: void      
	 * @throws   
	 */  
	private void fillMemberInfo(String token){
		jsonObject.put("time", System.currentTimeMillis());
		jsonObject.put("pd", entity.getPassword());
		jsonObject.put("token", token);
		jsonObject.put("id", entity.getId());
		jsonObject.put("name", entity.getRealName());
		jsonObject.put("nickname", entity.getNickname());
		jsonObject.put("phone", entity.getPhone());
		jsonObject.put("idcard", entity.getIdCard());
		jsonObject.put("email", entity.getEmail());
		jsonObject.put("age", entity.getAge());
		jsonObject.put("sex", entity.getSex());
		jsonObject.put("photo", entity.getUserImg());
		jsonObject.put("isEliteAccount", If.valueOf("If"+entity.getIsEliteAccount()).toString());

		InvestMember investMember = entity.getInvestMembers().iterator().next();
		jsonObject.put("money", investMember.getTotalMoney());
		jsonObject.put("investing", investMember.getInvesting());
		jsonObject.put("balance", investMember.getBalance());
		jsonObject.put("totalEarnings", investMember.getTotalIncome());
		jsonObject.put("currentEarnings", investMember.getCurrentIncome());

		LoanMember loanMember = entity.getLoanMembers().iterator().next();
		jsonObject.put("loanMoney", loanMember.getLendMoney());
		jsonObject.put("overdue", loanMember.getExpectMoney());
		jsonObject.put("residue", loanMember.getResidueMoney());
	}


}
