package org.duang.service;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.InvestList;
import org.duang.entity.InvestMember;
import org.duang.entity.MemberInfo;

/**   
 * 理财客户业务接口
 * @ClassName:  SysInvestMemberService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月25日 下午4:27:27      
 */ 
public interface InvestMemberService extends CommonInterface<InvestMember>{
	
	/**
	 * 修改理财用户的余额
	 * @Title: modifyInvestMembers   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param investList
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月1日 下午3:22:04
	 * @return: MemberInfo      
	 * @throws
	 */
	public abstract MemberInfo modifyInvestMembersBalance(InvestList investList) throws Exception; 
	
	
	/**
	 * 充值过后修改用户的账户金额信息
	 * @Title: depositFFCallBackUpdateInvest   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param userIdIdentity
	 * @param withdrawableBalance
	 * @param userBalance
	 * @param frozenBalance
	 * @param unsettledBalance
	 * @param bankAccount
	 * @return
	 * @throws Exception
	 */
	public abstract boolean depositFFCallBackUpdateInvest(String userIdIdentity,double withdrawableBalance,double userBalance,
			double frozenBalance,double unsettledBalance,double sum, String bankAccount,String bankName) throws Exception;
	
	/**
	 * 提现后修改用户的账户金额信息
	 * @Title: depositFFCallBackUpdateInvest   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param userIdIdentity
	 * @param withdrawableBalance
	 * @param userBalance
	 * @param frozenBalance
	 * @param unsettledBalance
	 * @param bankAccount
	 * @return
	 * @throws Exception
	 */
	public abstract boolean withdrawalsFFCallBackUpdateInvest(String userIdIdentity,double withdrawableBalance,double userBalance,
			double frozenBalance,double sum, String bankAccount) throws Exception;
	
}
