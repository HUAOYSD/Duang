package org.duang.action.sys;

import java.net.URLDecoder;
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
import org.duang.common.system.SessionTools;
import org.duang.entity.CustomerManager;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanMember;
import org.duang.entity.MemberInfo;
import org.duang.entity.SysUser;
import org.duang.enums.If;
import org.duang.enums.Platform;
import org.duang.service.CustomerManagerService;
import org.duang.service.SysUserService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 客户经理Action
 * @ClassName: CustomerManagerAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年7月26日 下午1:55:48
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "customermanager")
@ParentPackage("sys")
@Results(value = { 
		@Result(name = ResultPath.LIST, type = "dispatcher", location = "WEB-INF/page/sys/customerManager/customerManagerList.jsp"),
		@Result(name = "chose", type = "dispatcher", location = "WEB-INF/page/sys/customerManager/choseCustomerManagerList.jsp"),
		@Result(name = ResultPath.ADD, type = "dispatcher", location = "WEB-INF/page/sys/customerManager/addCustomerManager.jsp"),
		@Result(name = ResultPath.EDIT, type = "dispatcher", location = "WEB-INF/page/sys/customerManager/editCustomerManager.jsp"),
		@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") 
})
public class CustomerManagerAction extends BaseAction<CustomerManager> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private CustomerManagerService service;
	private SysUserService sysUserService;

	@Resource(name = "customermanagerserviceimpl")
	public void setService(CustomerManagerService service) {
		this.service = service;
	}
	@Resource
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}


	/**   
	 * 根据分页查询客户经理
	 * @Title: queryCustomerManagerByPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月9日 上午10:30:46
	 * @return: void      
	 * @throws   
	 */  
	public void queryCustomerManagerByPage() {
		try {
			condsUtils.addProperties(true, "sysUser", "order", "isDelete");
			condsUtils.addValues(true, new Object[]{"myAlias","as"}, Order.desc("createtime"), entity.getIsDelete());

			if (DataUtils.notEmpty(entity.getName())) {
				condsUtils.concat("name", new Object[]{entity.getName(),"like"});
			}
			if (DataUtils.notEmpty(getRequest().getParameter("sysUserName"))) {
				condsUtils.concat("myAlias.name", URLDecoder.decode(getRequest().getParameter("sysUserName"),"UTF-8"));
			}
			if (DataUtils.notEmpty(entity.getWorkNumber())) {
				condsUtils.concat("workNumber", new Object[]{entity.getWorkNumber(),"like"});
			}
			if (DataUtils.notEmpty(entity.getPhone())) {
				condsUtils.concat("phone", entity.getPhone());
			}
			if (DataUtils.notEmpty(entity.getEmail())) {
				condsUtils.concat("email", new Object[]{entity.getEmail(),"like"});
			}
			if (DataUtils.notEmpty(entity.getIdcard())) {
				condsUtils.concat("idcard", new Object[]{entity.getIdcard(),"like"});
			}
			if (DataUtils.notEmpty(entity.getSex())) {
				condsUtils.concat("sex", entity.getSex());
			}

			@SuppressWarnings("rawtypes")
			List list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			fillDatagridCons(list);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户经理ACTION方法queryCustomerManagerByPage错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("客户经理ACTION方法queryCustomerManagerByPage错误："+e.getLocalizedMessage(), this.getClass());
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
	private void fillDatagridCons(@SuppressWarnings("rawtypes") List list) throws Exception {
		if (list !=null && list.size() > 0) {
			for(Object temp : list) {
				if (temp instanceof Object[]) {
					Map<String,Object> resultMap = new HashMap<String,Object>();
					CustomerManager cm = (CustomerManager)((Object[])temp)[1];
					SysUser user = (SysUser)((Object[])temp)[0];
					if (cm != null) {
						resultMap.put("name", cm.getName());
						resultMap.put("workNumber", cm.getWorkNumber());
						resultMap.put("sex", cm.getSex());
						resultMap.put("idcard", cm.getIdcard());
						resultMap.put("email", cm.getEmail());
						resultMap.put("phone", cm.getPhone());
						resultMap.put("remark", cm.getRemark());
						resultMap.put("createtime", DateUtils.getTimeStamp(cm.getCreatetime()));
						resultMap.put("id", cm.getId());
					}
					if (user != null) {
						resultMap.put("sysUserName", user.getName());
					}
					listMap.add(resultMap);
				}
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
	}


	/**   
	 * 获取客户经理信息
	 * @Title: getCustomerManagerInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月9日 上午10:57:10
	 * @return: void      
	 * @throws   
	 */  
	public void getCustomerManagerInfo() {
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId())) {
				entity = service.findById(entity.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户经理ACTION方法getCustomerManagerInfo错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("客户经理ACTION方法getCustomerManagerInfo错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			super.printJsonResult();
		}
	}


	/**   
	 * 新增客户经理
	 * @Title: saveCustomerManager   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月9日 上午11:05:01
	 * @return: void      
	 * @throws   
	 */  
	public void saveCustomerManager() {
		try {
			if (entity != null) {
				entity.setId(DataUtils.randomUUID());
				entity.setCreatetime(new Date());
				entity.getSysUser().setId(DataUtils.randomUUID());
				entity.getSysUser().setCreateTime(new Date());
				entity.getSysUser().setPassword(DES.encryptDES(entity.getSysUser().getName()+"12345"));
				entity.getSysUser().setRemark("客户经理账号");

				//增加会员
				MemberInfo memberInfo = new MemberInfo(DataUtils.randomUUID());
				memberInfo.setLoginName(entity.getSysUser().getName());
				memberInfo.setRealName(entity.getName());
				memberInfo.setEmail(entity.getEmail());
				memberInfo.setSex(entity.getSex());
				memberInfo.setPhone(entity.getPhone());
				memberInfo.setIdCard(entity.getIdcard());
				memberInfo.setMiDescribe("客户经理角色");
				memberInfo.setIsdelete("0");
				memberInfo.setCreateTime(new Date());
				memberInfo.setCreateuser(SessionTools.getSessionSysUserName());
				memberInfo.setIsEliteAccount(0);
				memberInfo.setPassword(DES.encryptDES(entity.getSysUser().getName()+"12345"));
				memberInfo.setRegisterStyle(Platform.P4.getVal());
				memberInfo.setCustomerManager(entity);
				
				//理财会员
				InvestMember investMember = new InvestMember(DataUtils.randomUUID(), memberInfo, 0, 0, 0, 0, 0, 0, 0);
				memberInfo.getInvestMembers().add(investMember);
				//投资会员
				LoanMember loanMember = new LoanMember(DataUtils.randomUUID(), memberInfo, 0, 0, 0, 0);
				memberInfo.getLoanMembers().add(loanMember);
				entity.getMemberInfos().add(memberInfo);
				
				if (service.saveEntity(entity)) {
					jsonObject.put("success", true);
				}else{
					jsonObject.put("success", false);
				}
			}else {
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户经理ACTION方法saveCustomerManager错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("客户经理ACTION方法saveCustomerManager错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 更新客户经理
	 * @Title: updateCustomerManager   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月9日 上午11:39:48
	 * @return: void      
	 * @throws   
	 */  
	public void updateCustomerManager() {
		try {
			SysUser sysUser = sysUserService.findById(entity.getSysUser().getId());
			entity.setSysUser(sysUser);
			if (sysUser != null && service.updateEntity(entity)) {
				jsonObject.put("success", true);
			}else{
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户经理ACTION方法updateCustomerManager错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("客户经理ACTION方法updateCustomerManager错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 删除客户经理
	 * @Title: deleteCustomerManager   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月9日 上午11:41:02
	 * @return: void      
	 * @throws   
	 */  
	public void deleteCustomerManager() {
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("isDelete", If.If1.getVal());
				if (service.updateEntity(map, "id", entity.getId())) {
					jsonObject.put("success", true);
				}else {
					jsonObject.put("success", false);
				}
			}else {
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户经理ACTION方法deleteCustomerManager错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("客户经理ACTION方法deleteCustomerManager错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 删除还原
	 * @Title: restoreCustomerManager   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月10日 下午3:12:27
	 * @return: void      
	 * @throws   
	 */  
	public void restoreCustomerManager(){
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("isDelete", If.If0.getVal());
				if (service.updateEntity(map, "id", entity.getId())) {
					jsonObject.put("success", true);
				}else {
					jsonObject.put("success", false);
				}
			}else {
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户经理ACTION方法restoreCustomerManager错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("客户经理ACTION方法restoreCustomerManager错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
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
	 * @date 2016年8月9日 下午2:49:25
	 * @return: String      
	 * @throws   
	 */  
	public String openDialog() {
		try {
			String path = getRequest().getParameter("path");
			if(ResultPath.ADD.equals(path)) {
				return ResultPath.ADD;
			} else if(ResultPath.EDIT.equals(path)) {
				if (entity != null && DataUtils.notEmpty(entity.getId())) {
					entity = service.findById(entity.getId());
				}
				return ResultPath.EDIT;
			} else if ("chose".equals(path)) {
				return "chose";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户经理ACTION方法getCustomerManagerInfo错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("客户经理ACTION方法getCustomerManagerInfo错误："+e.getLocalizedMessage(), this.getClass());
		}
		return ResultPath.LIST;
	}
}
