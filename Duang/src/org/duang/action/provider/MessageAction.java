package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.Message;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————消息Action
 * @ClassName:  MessageAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_message")
public class MessageAction extends BaseAction<Message>{


	/**   
	 * 查询消息列表
	 * @Title: queryMessage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午4:44:10
	 * @return: void      
	 * @throws   
	 */  
	public void queryMessage(){

	}


	/**   
	 * 查询单条消息记录详情
	 * @Title: findMessage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午4:57:41
	 * @return: void      
	 * @throws   
	 */  
	public void findMessage(){

	}


	/**   
	 * 增加消息记录
	 * @Title: insertMessage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午4:44:43
	 * @return: void      
	 * @throws   
	 */  
	public void insertMessage(){

	}


	/**   
	 * 标记全部已读
	 * @Title: updateAllReaded   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午4:45:10
	 * @return: void      
	 * @throws   
	 */  
	public void updateAllReaded(){

	}


	/**   
	 * 标记已读
	 * @Title: updateReaded   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月9日 下午4:45:36
	 * @return: void      
	 * @throws   
	 */  
	public void updateReaded(){

	}


}
