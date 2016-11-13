package org.duang.dao; 

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.BillInvest;
import org.duang.entity.BindCard;
import org.duang.entity.InvestMember;
/**
 * 理财资金记录Dao接口 
 * @ClassName:  BillInvestDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月23日 上午11:49:03
 */
public interface BillInvestDao extends CommonInterface<BillInvest>  {
	/**
	 * 充值成功之后，产生记录
	 * @Title: depositFFCallBackCreateBill   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:investMember
	 * @param: money   充值金额
	 * @param: bindcard 充值的银行卡操作
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月13日 下午2:50:02
	 * @return: boolean      
	 * @throws
	 */
	public abstract boolean depositFFCallBackCreateBill(InvestMember investMember,double money,BindCard bindCard)throws Exception;
	
	/**
	 * 提现成功之后，产生记录
	 * @Title: withdrawalsFFCallBackCreateBill   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:investMember
	 * @param: money   提现金额
	 * @param: bindcard 提现的银行卡操作
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月13日 下午2:50:02
	 * @return: boolean      
	 * @throws
	 */
	public abstract boolean withdrawalsFFCallBackCreateBill(InvestMember investMember,double money,BindCard bindCard)throws Exception;
}

