package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.ApplyLoanCar;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————车产模式贷款的申请信息Action
 * @ClassName:  ApplyLoanCarAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_applyCarLoanInfo")
public class ApplyLoanCarAction extends BaseAction<ApplyLoanCar>{

	
	/**   
	 * 增加车产模式的借贷信息
	 * @Title: insertApplyCarLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */  
	public void insertApplyCarLoanInfo(){
	}
	
}
