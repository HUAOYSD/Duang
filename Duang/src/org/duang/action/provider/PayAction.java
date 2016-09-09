package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————支付Action
 * @ClassName:  PayAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_wallet")
public class PayAction extends BaseAction<Object>{

	
	/**   
	 * 充值
	 * @Title: deposit   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午3:39:54
	 * @return: void      
	 * @throws   
	 */  
	public void deposit(){
	}
	
	
	/**   
	 * 体现
	 * @Title: withdrawals   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午3:40:45
	 * @return: void      
	 * @throws   
	 */  
	public void withdrawals(){
	}
	
	
	/**   
	 * 支付————理财订单
	 * @Title: payInvest   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午3:41:36
	 * @return: void      
	 * @throws   
	 */  
	public void payInvest(){
		
	}
	
	
	/**   
	 * 赎回————理财订单
	 * @Title: redemptiveInvest   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午3:42:33
	 * @return: void      
	 * @throws   
	 */  
	public void redemptiveInvest(){
		
	}
	

}
