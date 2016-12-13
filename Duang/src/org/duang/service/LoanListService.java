package org.duang.service;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.LoanList;

/**   
 * 借贷列表业务接口
 * @ClassName:  LoanListService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年8月10日 下午5:18:52      
 */  
public interface LoanListService extends CommonInterface<LoanList>{
	
	/**
	 * 还款成功后操作
	 * @Title: memberInfoRepay   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param sum
	 * @param: @param memberInfoId
	 * @param: @param scaleId
	 * @param: @param loanList
	 * @param: @param style
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年12月12日 下午4:20:38
	 * @return: boolean      
	 * @throws
	 */
	public abstract boolean memberInfoRepay(double sum,String memberInfoId,String scaleId,LoanList loanList,int style) throws Exception;
}	
