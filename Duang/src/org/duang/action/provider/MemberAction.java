package org.duang.action.provider;
import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.MemberInfo;
import org.duang.service.MemberInfoService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————会员Action
 * @ClassName:  MemberAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_member")
public class MemberAction extends BaseAction<MemberInfo>{
	
	private MemberInfoService service;

	@Resource
	public void setService(MemberInfoService service) {
		this.service = service;
	}
	
	/**   
	 * 验证登录密码
	 * @Title: checkLoginPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void checkLoginPassword(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("pwd"))) {
				MemberInfo memberInfo = service.findEntity("id", entity.getId());
				if (memberInfo != null) {
					if (getRequest().getParameter("pwd").equals(memberInfo.getPassword())) {
						success = true;
					} else {
						msg = "登录密码错误";
					}
				} else {
					msg = "未查到该用户";
				}
			}else{
				msg = "密码不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——checkLoginPassword方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——checkLoginPassword方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 修改登录密码
	 * @Title: modifyLoginPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyLoginPassword(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("pwd"))) {
				MemberInfo memberInfo = service.findEntity("id", entity.getId());
				if (memberInfo != null) {
					memberInfo.setPassword(getRequest().getParameter("pwd"));
					success = service.updateEntity(memberInfo);
					if(!success){
						msg = "修改登录密码错误，连接超时";
					}
				} else {
					msg = "未查到该用户";
				}
			}else{
				msg = "密码不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——modifyLoginPassword方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——modifyLoginPassword方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 验证支付密码
	 * @Title: checkPayPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void checkPayPassword(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("pwd"))) {
				MemberInfo memberInfo = service.findEntity("id", entity.getId());
				if (memberInfo != null) {
					if (getRequest().getParameter("pwd").equals(memberInfo.getPayPassword())) {
						success = true;
					} else {
						msg = "支付密码错误";
					}
				} else {
					msg = "未查到该用户";
				}
			}else{
				msg = "密码为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——checkPayPassword方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——checkPayPassword方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 修改支付密码
	 * @Title: modifyPayPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyPayPassword(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("pwd"))) {
				MemberInfo memberInfo = service.findEntity("id", entity.getId());
				if (memberInfo != null) {
					memberInfo.setPayPassword(getRequest().getParameter("pwd"));
					success = service.updateEntity(memberInfo);
					if(!success){
						msg = "修改支付密码错误，连接超时";
					}
					
				} else {
					msg = "未查到该用户";
				}
			}else{
				msg = "密码不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——modifyPayPassword方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——modifyPayPassword方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	
	/**   
	 * 修改用户名
	 * @Title: modifyNickName   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyNickName(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("nickname"))) {
				String DESNickname = getRequest().getParameter("nickname");
				String nickName = DES.decryptDES(DESNickname);
				MemberInfo memberInfoByNickName = service.findEntity("nickname", nickName);
				if(memberInfoByNickName !=null){
					success = false;
					msg = "该昵称已被使用，请重新填写";
				}else{
					MemberInfo memberInfo = service.findEntity("id", entity.getId());
					if (memberInfo != null) {
						memberInfo.setNickname(nickName);
						success = service.updateEntity(memberInfo);
						if(!success){
							msg = "修改失败，请检查网络";
						}
						
					} else {
						msg = "未查到该用户";
					}
				}
			}else{
				msg = "用户名不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——modifyNickName方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——modifyNickName方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 验证用户真实名字和身份证号
	 * @Title: modifyNameAndIdcard   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyNameAndIdcard(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(getRequest().getParameter("idcard")) && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("name"))) {
				String DESIdCard = getRequest().getParameter("idcard");
				String idcard = DES.decryptDES(DESIdCard);
				MemberInfo memberInfoByNickName = service.findEntity("idCard", idcard);
				if(memberInfoByNickName !=null){
					success = false;
					msg = "身份证信息已被占用，请检查";
				}else{
					MemberInfo memberInfo = service.findEntity("id", entity.getId());
					if (memberInfo != null) {
						String DESName = getRequest().getParameter("name");
						memberInfo.setRealName(DES.decryptDES(DESName));
						memberInfo.setIdCard(idcard);
						success = service.updateEntity(memberInfo);
						if(!success){
							msg = "身份证信息已被占用，请检查";
						}
					} else {
						msg = "未查到该用户";
					}
				}
			}else{
				msg = "用户名不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——modifyNameAndIdcard方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——modifyNameAndIdcard方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
}
