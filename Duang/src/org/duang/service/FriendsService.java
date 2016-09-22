package org.duang.service;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.Friends;

/**   
 * 好友业务接口
 * @ClassName:  FriendsService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui 
 * @date 2016年8月23日 下午2:18:52      
 */  
public interface FriendsService extends CommonInterface<Friends>{
	
	/**   
	 * 增加财友
	 * @Title: addFriend   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param selfid 自己的id
	 * @param: @param targetid 如果对方事先有关注你，需要更新对方的关注你的状态为互相关注
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月12日 上午10:20:28
	 * @return: boolean      
	 * @throws   
	 */  
	public abstract boolean addFriend(String selfid, String targetid) throws Exception;
	
	
	/**   
	 * 取消关注财友
	 * @Title: cancelFriend   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param selfid
	 * @param: @param targetid
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月12日 上午10:23:04
	 * @return: boolean      
	 * @throws   
	 */  
	public abstract boolean cancelFriend(String selfid, String targetid) throws Exception;

}
