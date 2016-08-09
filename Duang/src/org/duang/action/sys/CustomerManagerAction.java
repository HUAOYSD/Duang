package org.duang.action.sys;

import java.util.ArrayList;
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
import org.duang.entity.CustomerManager;
import org.duang.enums.If;
import org.duang.service.CustomerManagerService;
import org.duang.util.DataUtils;
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
		@Result(name = ResultPath.LIST, type = "dispatcher", location = "WEB-INF/page/sys/investmember/investMemberList.jsp"),
		@Result(name = "addInvestMember", type = "dispatcher", location = "WEB-INF/page/sys/investmember/addInvestMember.jsp"),
		@Result(name = "editInvestMember", type = "dispatcher", location = "WEB-INF/page/sys/investmember/editInvestMember.jsp"),
		@Result(name = "uploadInvestMemberImg", type = "dispatcher", location = "WEB-INF/page/sys/investmember/uploadInvestMemberImg.jsp"),
		@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") 
})
public class CustomerManagerAction extends BaseAction<CustomerManager> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private CustomerManagerService service;

	@Resource(name = "customermanagerserviceimpl")
	public void setService(CustomerManagerService service) {
		this.service = service;
	}


	/**
	 * 查询所有的客户经理的id和name
	 * @Title: queryAllCusManagerIdAndName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月8日 下午3:27:37
	 * @return: void
	 * @throws
	 */
	public void queryAllCusManagerIdAndName() {
		String json = "";
		try {
			List<CustomerManager> list = service.queryEntity("isDelete", If.FALSE.getVal(), null, Order.desc("createtime"));
			listMap = fillIdName(list);
			json = JSONArray.fromObject(listMap).toString();
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户经理ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("客户经理ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult(json);
		}
	}


	/**
	 * 在用条件查询的时候，查询出来为List<Object[]>，所以需要进行封装
	 * @Title: fillDataObjectArray
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param list
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月3日 下午3:29:33
	 * @return: List<Map<String,Object>>
	 * @throws
	 */
	private List<Map<String, Object>> fillIdName(List<CustomerManager> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (CustomerManager cm : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", cm.getId());
				map.put("name", cm.getName());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装客户经理错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装客户经理错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
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
		List<CustomerManager> list;
		try {
			list = service.queryEntity("isDelete", If.FALSE.getVal(), getPageUtil(), Order.desc("createtime"));
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
	private void fillDatagridCons(List<CustomerManager> list) throws Exception {
		if (list !=null && list.size() > 0) {
			for(CustomerManager temp : list) {
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("name", temp.getName());
				resultMap.put("workNumber", temp.getWorkNumber());
				resultMap.put("sex", temp.getSex());
				resultMap.put("idcard", temp.getIdcard());
				resultMap.put("email", temp.getEmail());
				resultMap.put("phone", temp.getPhone());
				resultMap.put("remark", temp.getRemark());
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
				//				if(entity != null) {
				//					jsonObject.put("id", entity.getId());
				//				}
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
			if (entity != null && service.saveEntity(entity)) {
				jsonObject.put("success", true);
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
			if (entity != null && service.updateEntity(entity)) {
				jsonObject.put("success", true);
			}else {
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
				map.put("isDelete", If.TRUE.getVal());
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

}
