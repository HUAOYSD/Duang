package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————交易数据Action
 * @ClassName:  TransactionAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_transaction")
public class TransactionAction extends BaseAction<Object>{

	
	/**   
	 * 获总投资、借贷数据
	 * @Title: getLoanAndInvestData   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月7日 下午4:44:27
	 * @return: void      
	 * @throws   
	 */  
	public void getLoanAndInvestData(){
	}
	
}
