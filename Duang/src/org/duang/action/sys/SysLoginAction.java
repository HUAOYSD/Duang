package org.duang.action.sys;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.SessionTools;
import org.duang.entity.SysPower;
import org.duang.entity.SysUser;
import org.duang.service.SysPowerService;
import org.duang.service.SysUserService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DrawImage;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**
 * 系统用户登录控制类 
 * @ClassName: SysUserAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 5y
 * @date 2016-7-17 下午2:07:35 
 */ 
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="sys")
@ParentPackage("sys")
@Results(value={
		@Result(name="left", type="dispatcher", location="WEB-INF/page/sys/main/left.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.LOGIN, type="dispatcher", location="WEB-INF/page/sys/main/login.jsp"),
		@Result(name=ResultPath.HOME,type="dispatcher", location="WEB-INF/page/sys/main/goHome.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher", location="error.jsp")
})
public class SysLoginAction extends BaseAction<SysUser>{


	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 8766958805913002975L;

	private SysUserService service;
	private SysPowerService powerService;
	@Resource(name="sysuserserviceimpl")
	public void setService(SysUserService service) {
		this.service = service;
	}
	@Resource
	public void setPowerService(SysPowerService powerService) {
		this.powerService = powerService;
	}

	
	/**
	 * 跳转到系统用户登录页面
	 * @return
	 */
	public String goLoginView() {
		return LOGIN;
	}


	/**   
	 * 后台登录
	 * @Title: login   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月28日 下午8:58:26
	 * @return: String      
	 * @throws   
	 */  
	public String login() {
		msg = "用户名或密码输入错误";
		if(DataUtils.isEmpty(entity.getName()) || DataUtils.isEmpty(entity.getPassword())) {
			return LOGIN;
		}
		try {
			condsUtils.addProperties(true, "name","password");
			condsUtils.addValues(true, entity.getName().trim(), DES.encryptDES(entity.getPassword().trim()));
			List<SysUser> list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
			if(list!=null && list.size()==1) {
				SessionTools.setSessionSysUser(list.get(0));
				getRequest().setAttribute("sysmain", "go");
				return ResultPath.HOME;
			} else {
				return LOGIN;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户登录ACTION，方法login错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户登录ACTION，方法login错误："+e.getLocalizedMessage(), this.getClass());
			return LOGIN;
		}
	}


	/**   
	 * 生成验证码
	 * @Title: checkCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月28日 下午10:59:26
	 * @return: String      
	 * @throws   
	 */  
	public String getValidateCode(){
		try {
			DrawImage draw = new DrawImage();
			draw.drawImage(getRequest(), getResponse(null), "nl");
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户登录ACTION，方法getValidateCode错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户登录ACTION，方法getValidateCode错误："+e.getLocalizedMessage(), this.getClass());
		}
		return null;
	}


	/**   
	 * 验证验证码
	 * @Title: checkValidateCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年7月29日 上午9:53:23
	 * @return: void      
	 * @throws   
	 */  
	public void checkValidateCode(){
		try {
			jsonObject.put("success", true);
			//先取消验证码
			//			String validateCode = getRequest().getParameter("validateCode");
			//			if (DataUtils.notEmpty(validateCode)) {
			//				String readVal = (String) SessionTools.getSessionValue(SessionTools.RANDOMCODEKEY);
			//				if (readVal.equalsIgnoreCase(validateCode)) {
			//					SessionTools.removeSession(SessionTools.RANDOMCODEKEY);
			//					jsonObject.put("success", true);
			//				}else{
			//					jsonObject.put("success", false);
			//				}
			//			}else {
			//				jsonObject.put("success", false);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 系统用户注销
	 * @Title: logout   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月28日 下午9:26:47
	 * @return: String      
	 * @throws   
	 */  
	public String logout() {
		SysUser user = SessionTools.getSessionSysUser();
		if(user != null) {
			SessionTools.removeSessionSysUser();
		}
		return LOGIN;
	}


	/**   
	 * 系统的左边权限列表
	 * 获取一级菜单
	 * @Title: goLeft   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月28日 下午9:44:50
	 * @return: String      
	 * @throws   
	 */  
	public String goLeft(){
		SysUser user = SessionTools.getSessionSysUser();
		if (user != null) {
			try {
				Map<SysPower, List<Map<String, Object>>> map = new LinkedHashMap<SysPower, List<Map<String,Object>>>();
				List<SysPower> topMenuList = null;
				if ("admin".equals(user.getName())) {//超级系统用户
					topMenuList = powerService.queryEntity("parentId", "syspowers", null, org.hibernate.criterion.Order.asc("sortIndex"));
				}else{
					topMenuList = powerService.queryTopPowerByUser(user.getId());
				}
				if (topMenuList != null) {
					for (int i = 0; i < topMenuList.size(); i++) {
						map.put(topMenuList.get(i), getSubMenu(user, topMenuList.get(i).getId()));
					}
				}
				ServletActionContext.getContext().put("menulist", map);
				return "left";
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("系统用户登录ACTION，方法goLeft错误："+e.getMessage(), this.getClass());
				LoggerUtils.error("系统用户登录ACTION，方法goLeft错误："+e.getLocalizedMessage(), this.getClass());
				return LOGIN;
			}
		}else {
			return LOGIN;
		}
	}


	/**   
	 * 得到父菜单下有权限的子菜单
	 * @Title: getSubMenu   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年7月28日 下午10:11:38
	 * @return: List<Map<String, Object>>      
	 * @throws   
	 */  
	private List<Map<String, Object>> getSubMenu(SysUser sysUser, String parentId) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
		try {
			if (sysUser != null && DataUtils.notEmpty(parentId)) {
				List<SysPower> permitPowerList = powerService.queryPowerByUserAndParent(sysUser.getId(), parentId);
				if(permitPowerList != null && permitPowerList.size() > 0) {
					for (int i = 0; i < permitPowerList.size(); i++) {
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("id", permitPowerList.get(i).getId());
						map.put("name", permitPowerList.get(i).getName());
						map.put("iconCls", DataUtils.isEmpty(permitPowerList.get(i).getIcon()) ? "icon-text_list_numbers" : permitPowerList.get(i).getIcon());
						map.put("url", permitPowerList.get(i).getUrl());
						listmap.add(map);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户登录ACTION，方法getSubMenu错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户登录ACTION，方法getSubMenu错误："+e.getLocalizedMessage(), this.getClass());
		}
		return listmap;
	}

}
