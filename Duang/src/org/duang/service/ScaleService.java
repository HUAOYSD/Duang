package org.duang.service;

import java.util.Map;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.MemberMiddle;
import org.duang.entity.Scale;

/**   
 * 标业务接口
 * @ClassName:  SysInvestMemberService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月25日 下午4:27:27      
 */ 
public interface ScaleService extends CommonInterface<Scale>{
 
	/**
	 * 查询标的状态  //0新建标，1流标，2可投标，3已完成
	 * @Title: findScaleState   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月11日 上午11:09:10
	 * @return: String      
	 * @throws
	 */
	public abstract int findScaleState(String id) throws Exception;
	
	/**
	 * 满标自动放款操作
	 * @Title: fullScaleLoanMoney   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param scaleId
	 * @param: @param money
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月23日 下午5:00:41
	 * @return: boolean      
	 * @throws
	 */
	public abstract boolean fullScaleLoanMoney(String scaleId, double money) throws Exception;
	
	/**
	 * 流标赎回操作
	 * @Title: fullScaleLoanMoney   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param scaleId
	 * @param: @param money
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月23日 下午5:00:41
	 * @return: boolean      
	 * @throws
	 */
	public abstract boolean failScaleBackMoney(String scaleId, double money) throws Exception;
	
	/**
	 * 标满，手动放款
	 * @Title: fullScaleLoanMoney   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param scale
	 * @param: @param num
	 * @param: @param subledgerList
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年12月5日 上午11:39:08
	 * @return: boolean      
	 * @throws
	 */
	public abstract Map<String, Object> fullScaleLoanMoney(Scale scale, double num ,String subledgerList, MemberMiddle memberMiddle) throws Exception;
}
