package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.MemberInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————会员Action
 * @ClassName:  MemberAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_member")
public class MemberAction extends BaseAction<MemberInfo>{
	
	
	/**   
	 * 验证登录密码
	 * @Title: checkLoginPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void checkLoginPassword(){
	}
	
	
	/**   
	 * 修改登录密码
	 * @Title: modifyLoginPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyLoginPassword(){
	}
	
	
	/**   
	 * 验证支付密码
	 * @Title: checkPayPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void checkPayPassword(){
	}
	
	
	/**   
	 * 修改支付密码
	 * @Title: modifyPayPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyPayPassword(){
	}

	
	/**   
	 * 修改用户名
	 * @Title: modifyNickName   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyNickName(){
	}
	
	
	/**   
	 * 验证用户真实名字和身份证号
	 * @Title: modifyNameAndIdcard   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyNameAndIdcard(){
	}
}
