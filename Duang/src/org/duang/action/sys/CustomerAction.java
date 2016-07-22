package org.duang.action.sys;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.entity.SysUser;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 客户控制类 
 * @ClassName: CustomerAction 
 * @Description: 客户控制类 
 * @author 李永辉
 * @date 2016-7-22 下午3:54:35 
 */
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="customer")
@ParentPackage("sys")
@Results(value={
		@Result(name=ResultPath.RETURN_TO_CUSTOMERINFO,type="dispatcher",location="WEB-INF/page/customer/customerInfo.jsp"),
		@Result(name=ResultPath.ERROR,type="dispatcher",location="error.jsp")
})
public class CustomerAction extends BaseAction<SysUser>{
	private static final long serialVersionUID = 1L;
	
	/**   
	 * 查询客户信息列表
	 * @Title: queryCustomers   
	 * @Description: 查询客户信息列表  
	 * @param:   
	 * @author 李永辉    
	 * @date 2016年7月22日 下午3:59:44
	 * @return: String 返回路径      
	 * @throws   
	 */  
	public String queryCustomers(){
		System.out.println("asdfasdfsd");
		return ResultPath.RETURN_TO_CUSTOMERINFO;
	}
}
