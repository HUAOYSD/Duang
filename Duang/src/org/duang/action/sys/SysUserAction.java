package org.duang.action.sys;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.SysRole;
import org.duang.entity.SysUser;
import org.duang.service.SysUserService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.MD5Utils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**
 * 系统用户控制类 
 * @ClassName: SysUserAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 白攀
 * @date 2016-7-17 下午2:07:35 
 */ 
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="sysuser")
@ParentPackage("sys")
@Results(value={
		@Result(name="addSysUserView",type="dispatcher",location="WEB-INF/page/sys/sysuser/addUser.jsp"),
		@Result(name="editSysUserView",type="dispatcher",location="WEB-INF/page/sys/sysuser/editUser.jsp"),
		@Result(name="editPasswordView",type="dispatcher",location="WEB-INF/page/sys/sysuser/editPassword.jsp"),
		@Result(name=ResultPath.LIST,type="dispatcher",location="WEB-INF/page/sys/sysuser/sysUserList.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR,type="dispatcher",location="error.jsp")
})
public class SysUserAction extends BaseAction<SysUser>{


	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 8766958805913002975L;

	private SysUserService service;
	@Resource(name="sysuserserviceimpl")
	public void setService(SysUserService service) {
		this.service = service;
	}


	/**   
	 * 去系统用户列表
	 * @Title: showUser   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月25日 下午2:24:02
	 * @return: String      
	 * @throws   
	 */  
	public String showUser() {
		return ResultPath.LIST;
	}


	/**   
	 * 查询用户分页列表
	 * @Title: queryUserList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月27日 下午4:31:30
	 * @return: void      
	 * @throws   
	 */  
	public void queryUserList() {
		try {
			List<SysUser> list = service.queryAllEntity(getPageUtil(), Order.desc("createTime"));
			fillDatagridCons(list);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户ACTION，方法queryUserList错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户ACTION，方法queryUserList错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 填充listMap
	 * @Title: fillDatagridCons   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list  
	 * @author 白攀    
	 * @date 2016年7月27日 下午4:31:43
	 * @return: void      
	 * @throws   
	 */  
	public void fillDatagridCons(List<SysUser> list) throws Exception{
		if (list.size() > 0) {
			for(SysUser user : list) {
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("sysUserName", user.getName());
				//resultMap.put("phone", user.getPhone());
				//resultMap.put("email", user.getEmail());
				SysRole role = user.getSysRole();
				resultMap.put("roleName", role==null?"未分配":role.getRoleName());
				resultMap.put("updateDate", DateUtils.getTimeStamp(user.getUpdateTime()));
				resultMap.put("sysUserId", user.getId());
				resultMap.put("isnotdel", "admin".equals(user.getName()) ? true : false);
				listMap.add(resultMap);
			}
			jsonObject.put("total", getPageUtil().getCountRecords());
			jsonObject.put("currPage", getPageUtil().getCurrentPageNum());
			jsonObject.put("pageSize", getPageUtil().getPageRecords());
			jsonObject.put("rows",listMap);
		} else {
			jsonObject.put("total", 0);
			jsonObject.put("currPage", 0);
			jsonObject.put("pageSize", 0);
			jsonObject.put("rows",new JSONArray());
		}
	}


	/**   
	 * 得到用户信息
	 * @Title: getUserInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月27日 下午5:02:21
	 * @return: void      
	 * @throws   
	 */  
	public void getUserInfo() {
		String sysUserId = getRequest().getParameter("sysUserId");
		try {
			if (DataUtils.notEmpty(sysUserId)) {
				entity = service.findById(sysUserId);
				if(entity != null) {
					SysRole role = entity.getSysRole();
					jsonObject.put("sysRole.id", role==null?"":role.getId());
					jsonObject.put("name", entity.getName());
					jsonObject.put("password", entity.getPassword());
					jsonObject.put("passwordRes", entity.getPassword());
					jsonObject.put("createTime", entity.getCreateTime());
					jsonObject.put("remark", entity.getRemark());
					//jsonObject.put("phone", entity.getPhone());
					//jsonObject.put("email", entity.getEmail());
					//jsonObject.put("idcard", entity.getIdcard());
					jsonObject.put("id", entity.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户ACTION，方法getUserInfo错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户ACTION，方法getUserInfo错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 页面跳转
	 * @Title: openDialog   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月27日 下午5:02:12
	 * @return: String      
	 * @throws   
	 */  
	public String openDialog() {
		String path = getRequest().getParameter("path");
		if("addSysUserView".equals(path)) {
			return "addSysUserView";
		} else if("editSysUserView".equals(path)) {
			return "editSysUserView";
		} else if("editPassword".equals(path)) {
			return "editPasswordView";
		}
		return ResultPath.LIST;
	}


	/**
	 * 添加系统用户
	 * @Title: saveSysUser 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2014-6-3 上午10:05:13
	 */
	public void saveSysUser() {
		try {
			entity.setCreateTime(new Date());
			entity.setUpdateTime(new Date());
			entity.setId(DataUtils.randomUUID());
			entity.setPassword(MD5Utils.md5(entity.getPassword()));
			if (service.saveEntity(entity)) {
				jsonObject.put("success", true);
			}else {
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", false);
			LoggerUtils.error("系统用户ACTION，方法saveSysUser错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户ACTION，方法saveSysUser错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 更改用户信息
	 * @Title: updateSysUser   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月27日 下午5:12:22
	 * @return: void      
	 * @throws   
	 */  
	public void updateSysUser() {
		try {
			SysUser user = service.findById(entity.getId());
			entity.setUpdateTime(new Date());
			entity.setCreateTime(user.getCreateTime());
			if (service.updateEntity(entity)) {
				jsonObject.put("success", true);
			}else {
				jsonObject.put("success", false);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户ACTION，方法updateSysUser错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户ACTION，方法updateSysUser错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 删除用户
	 * @Title: deleteUser   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月27日 下午5:23:26
	 * @return: void      
	 * @throws   
	 */  
	public void deleteUser() {
		try {
			String sysUserId = getRequest().getParameter("sysUserId");
			if (DataUtils.notEmpty(sysUserId) && service.deleteEntity(sysUserId)) {
				jsonObject.put("success", true);
			}else {
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户ACTION，方法deleteUser错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户ACTION，方法deleteUser错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 修改密码
	 * @Title: updatePassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月27日 下午5:25:10
	 * @return: void      
	 * @throws   
	 */  
	public void updatePassword() {
		try{
			if (DataUtils.notEmpty(entity.getId())) {
				String pwd = entity.getPassword();
				entity = service.findById(entity.getId());
				entity.setPassword(MD5Utils.md5(pwd));
				if (service.updateEntity(entity)) {
					jsonObject.put("success", true);
				}else{
					jsonObject.put("success", false);
				}
			}else {
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户ACTION，方法updatePassword错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户ACTION，方法updatePassword错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 检查用户名
	 * @Title: checkSysUserName   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月27日 下午5:24:57
	 * @return: void      
	 * @throws   
	 */  
	public void checkSysUserName() {
		try {
			String name = getRequest().getParameter("name");
			name = java.net.URLDecoder.decode(name,"UTF-8");  
			String userid = getRequest().getParameter("userid"); //编辑的时候用的判断
			if (DataUtils.notEmpty(name)) {
				List<SysUser> list = service.queryEntity("name", name, null, null);
				if (DataUtils.notEmpty(userid)) {
					if ((list==null || list.size()<=0) || (list.size() == 1 && userid.equals(list.get(0).getId()))) {
						jsonObject.put("success", true);
					}else {
						jsonObject.put("success", false);
					}
				}else {
					if (list==null || list.size()<=0) {
						jsonObject.put("success", true);
					}else {
						jsonObject.put("success", false);
					}
				}
			}else {
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户ACTION，方法checkSysUserName错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统用户ACTION，方法checkSysUserName错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}

}
