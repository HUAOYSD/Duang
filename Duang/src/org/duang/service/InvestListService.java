package org.duang.service;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.InvestList;

/**   
 * 理财记录业务接口
 * @ClassName:  InvestListService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年8月12日 下午4:29:43      
 */  
public interface InvestListService extends CommonInterface<InvestList>{
	
	/**
	 * 提前赎回成功后的回调
	 * @param id
	 * @return
	 */
	public abstract boolean ransomed(String id, double income) throws Exception;
	
	
	
	/**
	 * 丰付投标回调
	 * @Title: investFFCallback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月3日 下午7:32:03
	 * @return: void      
	 * @throws
	 */
	public abstract boolean investFFCallback(String scaleid, String memberinfoid, double money, int investStyle, double giftSum, String requestid) throws Exception;
	
}
