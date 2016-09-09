package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.InvestList;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————投资记录Action
 * @ClassName:  InvestListAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月9日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_investlist")
public class InvestListAction extends BaseAction<InvestList>{
	
	
	/**   
	 * 增加投资记录
	 * @Title: addInvestList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 上午10:55:47
	 * @return: void      
	 * @throws   
	 */  
	public void addInvestList(){
		
	}
	
	
	/**   
	 * 查询理财记录列表
	 * @Title: queryInvestList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午4:25:24
	 * @return: void      
	 * @throws   
	 */  
	public void queryInvestList(){
		
	}
	
	
	/**   
	 * 根据id查询理财记录详情
	 * @Title: findInvestInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 上午11:33:23
	 * @return: void      
	 * @throws   
	 */  
	public void findInvestInfo(){
		
	}
	
	
	
}
