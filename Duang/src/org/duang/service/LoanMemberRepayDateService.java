package org.duang.service;

import java.util.Map;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.LoanList;
import org.duang.entity.LoanMemberRepayDate;

/**
 * 还款日期
 * @ClassName:  LoanMemberRepayDateService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年12月15日 下午5:50:43
 */
public interface LoanMemberRepayDateService extends CommonInterface<LoanMemberRepayDate>{
	
	/**
	 * 获取本次的还款日期
	 * @Title: getThisRepayDate   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年12月16日 上午10:35:40
	 * @return: Date      
	 * @throws
	 */
	public abstract Map<String, Object> getThisLoanRepayDate(LoanList loanList) throws Exception;
	
}
