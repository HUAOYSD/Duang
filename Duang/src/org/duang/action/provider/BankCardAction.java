package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.BindCard;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————银行卡Action
 * @ClassName:  BankCardAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_bankcard")
public class BankCardAction extends BaseAction<BindCard>{


	/**   
	 * 查询银行卡列表
	 * @Title: queryCardList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午3:04:04
	 * @return: void      
	 * @throws   
	 */  
	public void queryCardList(){
	}
	
	
	/**   
	 * 增加绑定银行卡
	 * @Title: insertBindCard   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午3:36:34
	 * @return: void      
	 * @throws   
	 */  
	public void insertBindCard(){
	}
	
	
	/**   
	 * 解绑银行卡
	 * @Title: unwrapCard   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午3:45:08
	 * @return: void      
	 * @throws   
	 */  
	public void unwrapCard(){
	}

	
}
