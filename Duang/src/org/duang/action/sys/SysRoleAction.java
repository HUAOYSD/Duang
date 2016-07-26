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
import org.duang.service.SysRoleService;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 系统用户角色Action类
 * @ClassName:  PowerAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年7月25日 下午2:50:11      
 */  
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="sysrole")
@ParentPackage("sys")
@Results(value={
		@Result(name="addRoleView", type="dispatcher", location="WEB-INF/page/sys/sysrole/addRole.jsp"),
		@Result(name="editRoleView", type="dispatcher", location="WEB-INF/page/sys/sysrole/editRole.jsp?sysRoleId=${sysRoleId}"),
		@Result(name=ResultPath.LIST, type="dispatcher", location="WEB-INF/page/sys/sysrole/sysRoleList.jsp"),
		@Result(name=ResultPath.LIST, type="dispatcher", location="WEB-INF/page/sys/sysrole/sysRoleList.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher", location="error.jsp")
})
public class SysRoleAction extends BaseAction<SysRole>{
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2338429900391951446L;

	private SysRoleService service;
	@Resource(name="sysroleserviceimpl")
	public void setService(SysRoleService service) {
		this.service = service;
	}


	/**   
	 * 跳转到角色管理界面
	 * @Title: showRole   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月25日 下午3:35:19
	 * @return: String      
	 * @throws   
	 */  
	public String showRole() {
		return ResultPath.LIST;
	}

	/**   
	 * 页面跳转
	 * @Title: openDialog   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月26日 上午10:44:12
	 * @return: String      
	 * @throws   
	 */  
	public String openDialog() {
		String path = getRequest().getParameter("path");
		if("addRoleView".equals(path)) {
			return "addRoleView";
		} else if("editRoleView".equals(path)) {
			return "editRoleView";
		} else if("powerToRoleView".equals(path)) {
			return "powerToRoleView";
		}
		return ResultPath.LIST;
	}


	/**   
	 * 查询角色列表
	 * @Title: queryRolePageList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月25日 下午5:01:07
	 * @return: void      
	 * @throws   
	 */  
	public void queryRolePageList() {
		List<SysRole> list;
		try {
			list = service.queryAllEntity(getPageUtil(), null);
			fillDatagridCons(list);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统角色ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统角色ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("total", 0);
			jsonObject.put("currPage", 0);
			jsonObject.put("pageSize", 0);
			jsonObject.put("rows",new JSONArray());
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 填充List数据
	 * @Title: fillDatagridCons   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list  
	 * @author 白攀    
	 * @date 2016年7月25日 下午5:08:47
	 * @return: void      
	 * @throws   
	 */  
	private void fillDatagridCons(List<SysRole> list) {
		try {
			if (list !=null && list.size() > 0) {
				for(SysRole role : list) {
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("sysRoleName", role.getRoleName());
					resultMap.put("sysRoleDesc", role.getRoleDesc());
					resultMap.put("optDate", role.getOptionTime().getTime());
					resultMap.put("sysRoleId", role.getId());
					listMap.add(resultMap);
				}
				jsonObject.put("total", getPageUtil().getCountRecords());
				jsonObject.put("currPage", getPageUtil().getCurrentPageNum());
				jsonObject.put("pageSize", getPageUtil().getPageRecords());
				jsonObject.put("rows", listMap);
			}else {
				jsonObject.put("total", 0);
				jsonObject.put("currPage", 0);
				jsonObject.put("pageSize", 0);
				jsonObject.put("rows",new JSONArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统角色ACTION填充数据错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统角色ACTION填充数据错误："+e.getLocalizedMessage(), this.getClass());
		}
	}


	//	/**
	//	 * 查询角色下拉列表
	//	 * @Title: queryRoleList 
	//	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	//	 * @return void    返回类型 
	//	 */
	//	public void queryRoleList() {
	//		String json = "";
	//		try {
	//			List<SysRole> list = roleService.queryRoleList();
	//			for(SysRole role : list){
	//				Map<String,Object> map = new HashMap<String,Object>();
	//				map.put("roleId", role.getSysRoleId());
	//				map.put("roleName", role.getSysRoleName());
	//				listMap.add(map);
	//			}
	//			json = JSONArray.fromObject(listMap).toString();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		} finally {
	//			printJsonResult(json);
	//		}
	//	}


	/**   
	 * 获取角色信息
	 * @Title: getRoleInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月25日 下午3:49:27
	 * @return: void      
	 * @throws   
	 */  
	public void getRoleInfo() {
		String sysRoleId = super.getRequest().getParameter("sysRoleId");
		try {
			if (DataUtils.notEmpty(sysRoleId)) {
				entity = service.findById(sysRoleId);
				if(entity != null) {
					jsonObject.put("sysRoleName", entity.getRoleName());
					jsonObject.put("sysRoleDesc", entity.getRoleDesc());
					jsonObject.put("sysRoleId", entity.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统角色ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统角色ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
			super.msg = "系统角色ACTION查询错误："+e.getMessage();
			jsonObject.put("msg", super.msg);
		} finally {
			super.printJsonResult();
		}
	}


	/**   
	 * 添加权限
	 * @Title: saveRole   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月25日 下午3:55:17
	 * @return: void      
	 * @throws   
	 */  
	//	public void saveRole() {
	//		try {
	//			String powerIdsStr = request.getParameter("powerIds");
	//			String[] powerIds = powerIdsStr.split("space");
	//			role.setSysRoleId(DataUtil.randomUUID());
	//			role.setOptDate(new Date());
	//			roleService.save(role);
	//			for(String powerId : powerIds) {
	//				Power p = priService.queryPowerById(powerId);
	//				RolePower rolePower = new RolePower();
	//				rolePower.setRolePowerId(DataUtil.randomUUID());
	//				rolePower.setPower(p);
	//				rolePower.setSysRole(role);
	//				rpService.saveRolePower(rolePower);
	//			}
	//			jsonObject.put("success", true);
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			jsonObject.put("success", false);
	//		} finally {
	//			printJsonResult();
	//		}
	//	}


	/**   
	 * 更改角色信息
	 * @Title: updateRole   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月25日 下午3:55:43
	 * @return: void      
	 * @throws   
	 */  
	public void updateRole() {
		try {
			if (entity != null) {
				entity.setOptionTime(new Date());
				if (service.updateEntity(entity)) {
					jsonObject.put("success", true);
				}else {
					jsonObject.put("success", false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统角色ACTION修改错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统角色ACTION修改错误："+e.getLocalizedMessage(), this.getClass());
			super.msg = "系统角色ACTION修改错误："+e.getMessage();
			jsonObject.put("success", false);
			jsonObject.put("msg", super.msg);
		} finally {
			super.printJsonResult();
		}
	}


	/**   
	 * 删除系统角色信息
	 * @Title: deleteRole   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月25日 下午3:59:41
	 * @return: void      
	 * @throws   
	 */  
	public void deleteRole() {
		try {
			String sysRoleId = super.getRequest().getParameter("sysRoleId");
			if (DataUtils.notEmpty(sysRoleId)) {
				if (service.deleteEntity(sysRoleId)) {//删除中间表数据 并且 删除角色表数据
					jsonObject.put("success", true);
				}else {
					jsonObject.put("success", false);
				}
			}else{
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统角色ACTION删除错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统角色ACTION删除错误："+e.getLocalizedMessage(), this.getClass());
			super.msg = "系统角色ACTION删除错误："+e.getMessage();
			jsonObject.put("success", false);
			jsonObject.put("msg", super.msg);
		} finally {
			super.printJsonResult();
		}
	}
}
