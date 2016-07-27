package org.duang.action.sys;
import java.util.ArrayList;
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
import org.duang.entity.SysPower;
import org.duang.service.SysPowerService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 系统用户权限Action类
 * @ClassName:  PowerAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年7月25日 下午2:50:11      
 */  
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="syspower")
@ParentPackage("sys")
@Results(value={
		@Result(name="editPowerView", type="dispatcher", location="WEB-INF/page/sys/syspower/editPower.jsp"),
		@Result(name="addPowerView", type="dispatcher", location="WEB-INF/page/sys/syspower/addPower.jsp"),
		@Result(name=ResultPath.LIST, type="dispatcher", location="WEB-INF/page/sys/syspower/sysPowerList.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher", location="error.jsp")
})
public class SysPowerAction extends BaseAction<SysPower>{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;

	private SysPowerService service;
	@Resource(name="syspowerserviceimpl")
	public void setService(SysPowerService service) {
		this.service = service;
	}


	/**   
	 * 角色分配权限时所用的树形多选框
	 * @Title: getPowerTreeCheckbox   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月26日 上午11:22:53
	 * @return: void      
	 * @throws   
	 */  
	public void getPowerTreeCheckbox() {
		JSONArray jsonArray = new JSONArray();
		try {
			//得到最顶级权限
			List<SysPower> list = service.queryEntity("parentId", "0", null, Order.asc("sortIndex"));
			if(list != null && list.size() >0) {
				for(SysPower top :list) {
					Map<String,Object> map = new HashMap<String,Object> ();
					map.put("id", top.getId());
					map.put("text", top.getName());
					//map.put("state", "closed");
					map.put("checkbox", true);
					map.put("children", makeTreePowerCheckbox(top.getId()));
					jsonArray.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统权限ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统权限ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult(jsonArray);
		}
	}


	/**   
	 * 递归得到复选框权限树
	 * @Title: makeTreePowerCheckbox   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param powerId
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月26日 下午12:57:09
	 * @return: JSONArray      
	 * @throws   
	 */  
	public JSONArray makeTreePowerCheckbox(String powerId) throws Exception {
		List<SysPower> list = service.queryEntity("parentId", powerId, null, Order.asc("sortIndex"));
		JSONArray jsonArray = new JSONArray();
		if(list != null && list.size() > 0) {
			for(SysPower power : list) {
				Map<String,Object> map = new HashMap<String,Object> ();
				map.put("id", power.getId());
				map.put("text", power.getName());
				//map.put("state", "closed");
				map.put("children", makeTreePowerCheckbox(power.getId()));
				jsonArray.add(map);
			}
		}
		return jsonArray;
	}


	/**   
	 * 跳转到权限管理界面
	 * @Title: showPower   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月26日 下午3:42:14
	 * @return: String      
	 * @throws   
	 */  
	public String showPower() {
		return ResultPath.LIST;
	}


	/**   
	 * 打开Dialog页面跳转
	 * @Title: openDialog   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月26日 下午3:48:47
	 * @return: String      
	 * @throws   
	 */  
	public String openDialog() {
		String path = getRequest().getParameter("path");
		if("addPowerView".equals(path)) {
			return "addPowerView";
		} else if("editPowerView".equals(path)) {
			return "editPowerView";
		}
		return ResultPath.LIST;
	}


	/**   
	 * 得到权限树形列表 
	 * @Title: queryPoweTreeList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月26日 下午5:05:16
	 * @return: void      
	 * @throws   
	 */  
	public void queryPoweTreeList() {
		try {
			//顶级权限id为0
			List<SysPower> list = service.queryEntity("parentId", "0", null, null);
			if (list != null && list.size() > 0) {
				SysPower top = list.get(0);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("powerId", top.getId());
				map.put("powerName", top.getName());
				map.put("isnotdel", true);
				map.put("optTime", DateUtils.getTimeStamp(top.getOptionTime()));
				listMap.add(map);
				makePowerTree(top.getId());
				jsonObject.put("total", service.count());
				jsonObject.put("rows", listMap);
			}
		}catch(Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统权限ACTION，方法queryPoweTreeList错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统权限ACTION，方法queryPoweTreeList错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 递归生成树
	 * @Title: makePowerTree   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id  
	 * @author 白攀    
	 * @date 2016年7月26日 下午4:09:29
	 * @return: void      
	 * @throws   
	 */  
	public void makePowerTree(String id) throws Exception{
		List<SysPower> list = service.queryEntity("parentId", id, null, Order.asc("sortIndex"));
		if(list!=null && list.size()>0) {
			for(SysPower power : list) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("powerId", power.getId());
				map.put("powerName", power.getName());
				map.put("isnotdel", false);
				map.put("optTime", DateUtils.getTimeStamp(power.getOptionTime()));
				map.put("_parentId", power.getParentId());
				listMap.add(map);
				makePowerTree(power.getId());
			}
		}
	}


	/**   
	 * 生成权限list下拉别表使用
	 * @Title: queryPowerList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月26日 下午3:38:00
	 * @return: void      
	 * @throws   
	 */  
	public void queryPowerList() {
		String json = "";
		try {
			List<SysPower> list = service.queryAllEntity(Order.asc("sortIndex"));
			if(list != null) {
				for(SysPower power : list) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("parentId", power.getId());
					map.put("parentName", power.getName());
					listMap.add(map);
				}
			}
			json = JSONArray.fromObject(listMap).toString();
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统权限ACTION，方法queryPowerList错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统权限ACTION，方法queryPowerList错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult(json);
		}
	}


	/**   
	 * 得到全面信息
	 * @Title: getPowerInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月26日 下午4:20:42
	 * @return: void      
	 * @throws   
	 */  
	public void getPowerInfo() {
		String powerId = getRequest().getParameter("powerId");
		try {
			if (DataUtils.notEmpty(powerId)) {
				SysPower power = service.findById(powerId);
				if(power != null) {
					jsonObject.put("id", powerId);
					jsonObject.put("parentId", power.getParentId().equals("0") ? "" : power.getParentId());
					jsonObject.put("name", power.getName());
					jsonObject.put("url", power.getUrl());
					jsonObject.put("sortIndex", power.getSortIndex());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统权限ACTION，方法getPowerInfo错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统权限ACTION，方法getPowerInfo错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 保存权限
	 * @Title: savePower   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月26日 下午5:03:05
	 * @return: void      
	 * @throws   
	 */  
	public void savePower() {
		try {
			entity.setOptionTime(new Date());
			entity.setId(DataUtils.randomUUID());
			service.saveEntity(entity);
			jsonObject.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统权限ACTION，方法savePower错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统权限ACTION，方法savePower错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 更新权限
	 * @Title: updatePower   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月26日 下午5:05:02
	 * @return: void      
	 * @throws   
	 */  
	public void updatePower() {
		try {
			entity.setOptionTime(new Date());
			service.updateEntity(entity);
			jsonObject.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统权限ACTION，方法updatePower错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统权限ACTION，方法updatePower错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 删除权限
	 * @Title: deletePower   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月26日 下午4:39:21
	 * @return: void      
	 * @throws   
	 */  
	public void deletePower() {
		try {
			String powerId = getRequest().getParameter("powerId");
			if (DataUtils.notEmpty(powerId)) {
				SysPower power = service.findById(powerId);
				if (power!=null) {
					List<SysPower> list = new ArrayList<SysPower>();
					list.add(power);
					recursiveFindSubPower(powerId, list);
					if (service.deletePower(list)) {//删除中间表数据 并且 删除权限表数据
						jsonObject.put("success", true);
					} else {
						jsonObject.put("success", false);
					}
				}else {
					jsonObject.put("success", false);
				}
			}else{
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统权限ACTION，方法deletePower错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统权限ACTION，方法deletePower错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 递归查找子权限
	 * @Title: recursiveFindSubPower   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param powerId
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 白攀    
	 * @date 2016年7月26日 下午4:42:28
	 * @return: List<SysPower>      
	 * @throws   
	 */  
	public void recursiveFindSubPower(String powerId, List<SysPower> list) throws Exception{
		List<SysPower> listNew = service.queryEntity("parentId", powerId, null, null);
		if(listNew != null && listNew.size() > 0) {
			for(SysPower power : listNew) {
				list.add(power);
				recursiveFindSubPower(power.getId(), list);
			}
		}
	}


	/**   
	 * 验证权限名称是否存在
	 * @Title: checkPowerName   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月26日 下午4:29:56
	 * @return: void      
	 * @throws   
	 */  
	public void checkPowerName() {
		try {
			String name = getRequest().getParameter("name");
			name = java.net.URLDecoder.decode(name,"UTF-8");  
			String powerid = getRequest().getParameter("powerid"); //编辑的时候用的判断
			if (DataUtils.notEmpty(name)) {
				List<SysPower> list = service.queryEntity("name", name, null, null);
				if (DataUtils.notEmpty(powerid)) {
					if ((list==null || list.size()<=0) || (list.size() == 1 && powerid.equals(list.get(0).getId()))) {
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
			LoggerUtils.error("系统权限ACTION，方法checkPowerName错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统权限ACTION，方法checkPowerName错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}


}
