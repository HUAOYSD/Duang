package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.Scale;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————标类Action
 * @ClassName:  ScaleAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_scale")
public class ScaleAction extends BaseAction<Scale>{

	
	/**   
	 * 查询房标列表
	 * @Title: queryHouseScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午4:38:04
	 * @return: void      
	 * @throws   
	 */  
	public void queryHouseScale(){
	}
	
	
	/**   
	 * 查询车标列表
	 * @Title: queryCarScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午4:38:04
	 * @return: void      
	 * @throws   
	 */  
	public void queryCarScale(){
	}
	
	
	/**   
	 * 查询信标列表
	 * @Title: queryHouseScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午4:38:04
	 * @return: void      
	 * @throws   
	 */  
	public void queryCreditScale(){
	}
	
}
