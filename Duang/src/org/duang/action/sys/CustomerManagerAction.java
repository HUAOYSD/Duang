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
import org.codehaus.jackson.map.ObjectMapper;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.SessionTools;
import org.duang.entity.CustomerManager;
import org.duang.entity.InvestMember;
import org.duang.entity.MemberInfo;
import org.duang.entity.SysUser;
import org.duang.service.CustomerManagerService;
import org.duang.service.InvestMemberService;
import org.duang.util.ConstantCode;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 理财用户Action
 * 
 * @ClassName: SysInvestMemberAction
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
public class CustomerManagerAction extends BaseAction<InvestMember> {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private CustomerManagerService customerManagerService;

	@Resource(name = "customermanagerserviceimpl")
	public void setService(CustomerManagerService customerManagerService) {
		this.customerManagerService = customerManagerService;
	}

	
	/**
	 * 查询所有的客户经理的id和name
	 * 
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
			List<CustomerManager> list = customerManagerService.queryAllEntity(null);
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
	 * 
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
	 * 在用条件查询的时候，查询出来为List<Object[]>，所以需要进行封装
	 * 
	 * @Title: fillDataObjectArray
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param list
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月3日 下午3:29:33
	 * @return: List<Map<String,Object>>
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(List<CustomerManager> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (CustomerManager cm : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", cm.getId());
				map.put("name", cm.getName());
				map.put("workNumber", cm.getWorkNumber());
				map.put("sex", cm.getSex());
				map.put("idcard", cm.getIdcard());
				map.put("email", cm.getEmail());
				map.put("photo", cm.getPhone());
				map.put("qr", cm.getQr());
				map.put("remark", cm.getRemark());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装客户经理错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装客户经理错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}

}
