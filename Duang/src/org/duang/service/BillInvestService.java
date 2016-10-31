package org.duang.service;

import java.util.List;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.BillInvest;
import org.duang.entity.InvestList;
import org.duang.entity.InvestMember;

/**   
 * 理财资金记录业务接口
 * @ClassName:  BillInvestService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月23日 下午2:18:52      
 */  
public interface BillInvestService extends CommonInterface<BillInvest>{
	
	/**   
	 * 查询累计投资次数与金额
	 * @Title: findCostInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberid
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月12日 下午2:48:36
	 * @return: List<BillInvest>      
	 * @throws   
	 */  
	public abstract List<BillInvest> findCostInfo(String memberid) throws Exception;
	
	
	/**   
	 * 获取和我差不多投资额度的会员
	 * 投资额度和该会员投资额度相差不过5万的前20人
	 * @Title: queryFairlysMemberCostInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberid
	 * @param: @param tm
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月12日 下午2:51:36
	 * @return: List<BillInvest>      
	 * @throws   
	 */  
	public abstract List<BillInvest> queryFairlysMemberCostInfo(String memberid, double tm) throws Exception;
	
	
	/**   
	 * 充值成功更新资产
	 * @Title: updateBill   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param billInvest
	 * @param: @param investMember
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月23日 下午1:52:32
	 * @return: boolean      
	 * @throws   
	 */  
	public abstract boolean updateBill(BillInvest billInvest, InvestMember investMember) throws Exception;
	
	/**
	 * 支付成功之后，产生记录
	 * @Title: createBill   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param investList
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年10月28日 下午2:50:02
	 * @return: boolean      
	 * @throws
	 */
	public abstract boolean createBill(InvestList investList)throws Exception;

}
