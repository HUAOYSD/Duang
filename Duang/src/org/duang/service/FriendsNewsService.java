package org.duang.service;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.FriendsNews;

/**
 * 财友动态
 * @ClassName:  FriendsNewsService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年10月13日 下午5:16:51
 */
public interface FriendsNewsService extends CommonInterface<FriendsNews>{
	/**
	 * 保存用户发布的朋友圈
	 * @Title: addFriendsNewsAndImg   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param friendsNews
	 * @param: @param imgData
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月16日 下午2:37:07
	 * @return: boolean      
	 * @throws
	 */
	public abstract boolean  addFriendsNewsAndImg(FriendsNews friendsNews) throws Exception;
}
