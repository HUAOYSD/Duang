package org.duang.service;

import java.util.List;
import java.util.Map;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.LoanMember;

/**   
 * 借贷客户业务接口
 * @ClassName:  LoanMemberService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月10日 下午4:27:27      
 */ 
public interface LoanMemberService extends CommonInterface<LoanMember>{
	/**
	 * 根据memberinfoIds获取借贷用户
	 * @Title: getLoanMemberInfobyIds   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param loanMembers  232323,234123,23423
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月1日 下午3:26:53
	 * @return: List<Map<String,String>>      
	 * @throws
	 */
	public abstract List<Map<String, String>> getLoanMemberInfobyIds(String loanMembers) throws Exception;
}
