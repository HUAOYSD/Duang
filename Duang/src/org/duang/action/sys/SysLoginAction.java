package org.duang.action.sys;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

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
import org.duang.util.DataUtils;
import org.duang.util.DrawImage;
import org.duang.util.MD5Utils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**
 * 系统用户登录控制类 
 * @ClassName: SysUserAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 白攀
 * @date 2016-7-17 下午2:07:35 
 */ 
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="sys")
@ParentPackage("sys")
@Results(value={
		@Result(name="left", type="dispatcher", location="WEB-INF/page/sys/main/left.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.LOGIN, type="dispatcher", location="WEB-INF/page/sys/main/login.jsp"),
		@Result(name=ResultPath.HOME,type="dispatcher", location="WEB-INF/page/sys/main/home.jsp"),
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
	 * @author 白攀    
	 * @date 2016年7月28日 下午8:58:26
	 * @return: String      
	 * @throws   
	 */  
	public String login() {
		System.out.println(getRequest().getParameter("name"));
		System.out.println(getRequest().getParameter("password"));
		System.out.println(getRequest().getParameter("validateCode"));
		Enumeration<String> params = getRequest().getAttributeNames();
		while(params.hasMoreElements()){
			String key = params.nextElement();
			String value = getRequest().getParameter(key);
			System.err.println(key+"\t"+value);
		}
		msg = "用户名或密码输入错误";
		if(DataUtils.isEmpty(entity.getName()) || DataUtils.isEmpty(entity.getPassword())) {
			return LOGIN;
		}
		try {
			condsUtils.addProperties(true, "name","password");
			condsUtils.addValues(true, entity.getName(), MD5Utils.md5(entity.getPassword()));
			List<SysUser> list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
			if(list!=null && list.size()==1) {
				SessionTools.setSessionSysUser(list.get(0));
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
	 * @author 白攀    
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
		}
		return null;
	}


	/**   
	 * 系统用户注销
	 * @Title: logout   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
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
	 * @author 白攀    
	 * @date 2016年7月28日 下午9:44:50
	 * @return: String      
	 * @throws   
	 */  
	public String goLeft(){
		SysUser user = SessionTools.getSessionSysUser();
		if (user != null) {
			try {
				List<SysPower> topMenuList = null;
				if ("admin".equals(user.getName())) {//超级系统用户
					topMenuList = powerService.queryEntity("parentId", "syspowers", null, org.hibernate.criterion.Order.asc("sortIndex"));
				}else{
					topMenuList = powerService.queryPowerByUserAndParent(user.getId(), "syspowers");
				}
				ServletActionContext.getContext().put("topMenuList", topMenuList);
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
	 * @author 白攀    
	 * @date 2016年7月28日 下午10:11:38
	 * @return: void      
	 * @throws   
	 */  
	public void getSubMenu() {
		JSONArray json = new JSONArray();
		try {
			String powerId = getRequest().getParameter("powerId");
			SysUser sysUser = SessionTools.getSessionSysUser();
			if (sysUser != null && DataUtils.notEmpty(powerId)) {
				List<SysPower> permitPowerList = powerService.queryPowerByUserAndParent(sysUser.getId(), powerId);
				if(permitPowerList != null && permitPowerList.size() > 0) {
					for(SysPower sub : permitPowerList) {
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("id", sub.getId());
						map.put("text", sub.getName());
						map.put("iconCls", DataUtils.isEmpty(sub.getIcon()) ? "tree-icon" : sub.getIcon());
						map.put("url", sub.getUrl());
						json.add(map);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户登录ACTION，方法getSubMenu错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户登录ACTION，方法getSubMenu错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			super.printJsonResult(json);
		}
	}

}
