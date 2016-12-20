package org.duang.dao; 

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.LoanList;
import org.duang.entity.LoanMemberRepayDate;
import org.duang.entity.Scale;

/**
 * 还款日和赎回日期
 * @ClassName:  LoanMemberRepayDateDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年12月15日 下午5:46:46
 */
public interface LoanMemberRepayDateDao extends CommonInterface<LoanMemberRepayDate>  {
	 
	/**
	 * 添加日期信息（借贷的还款日期）
	 * @Title: addRepayDate   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param scale
	 * @param: @param loanList
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年12月16日 上午9:36:13
	 * @return: void      
	 * @throws
	 */
	public abstract void addRepayLoanDate(Scale scale,LoanList loanList) throws Exception;
}

