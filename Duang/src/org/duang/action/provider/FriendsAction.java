package org.duang.action.provider;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.Friends;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**   
 * 接口开发————财友Action
 * @ClassName:  FriendsAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月10日 上午10:54:16      
 */ 
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_friends")
public class FriendsAction extends BaseAction<Friends>{
	
	/**   
	 * 查询我关注的
	 * @Title: queryMyStars   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */  
	public void queryMyStars(){
		
	}
	
	
	/**   
	 * 查询关注我的
	 * @Title: queryStarMes   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */  
	public void queryStarMes(){
		
	}
	
	
	/**   
	 * 获取我的二维码
	 * @Title: getMyQR   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */  
	public void getMyQR(){
		
	}
	
	
	/**   
	 * 获取和我差不多投资额度的会员
	 * @Title: getFairlysMember   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */  
	public void getFairlysMember(){
		
	}
	
	
	/**   
	 * 添加财友
	 * @Title: addFriends   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月8日 下午4:20:35
	 * @return: void      
	 * @throws   
	 */ 
	public void addFriends(){
		
	}

}
