package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.LoanList;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————借贷记录Action
 * @ClassName:  LoanListAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月9日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_loanlist")
public class LoanListAction extends BaseAction<LoanList>{
	
	
	
	/**   
	 * 查询借贷记录列表
	 * @Title: queryLoanList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午4:25:24
	 * @return: void      
	 * @throws   
	 */  
	public void queryLoanList(){
	}
	
	
	/**   
	 * 查找单个借贷记录详情
	 * @Title: findLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午4:30:13
	 * @return: void      
	 * @throws   
	 */  
	public void findLoanInfo(){
	}
	
	
	
}
