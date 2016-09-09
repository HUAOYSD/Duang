package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.ApplyLoanInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————普通和急速模式贷款的申请信息Action
 * @ClassName:  ApplyLoanInfoAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_applyLoanInfo")
public class ApplyLoanInfoAction extends BaseAction<ApplyLoanInfo>{

	
	/**   
	 * 增加申请普通和急速模式贷款的申请信息
	 * @Title: insertApplyLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月8日 上午10:46:49
	 * @return: void      
	 * @throws   
	 */  
	public void insertApplyLoanInfo(){
		
	}
	
}
