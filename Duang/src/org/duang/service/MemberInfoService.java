package org.duang.service;

import java.util.Map;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.MemberInfo;

/**   
 * 功能用户信息业务接口
 * @ClassName:  SysMemberInfoService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月25日 下午4:27:27      
 */ 
public interface MemberInfoService extends CommonInterface<MemberInfo>{
	
	/**   
	 * 获总投资、借贷数据
	 * @Title: queryFairlysMemberCostInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberid
	 * @param: @param tm
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui  
	 * @date 2016年9月12日 下午2:51:36
	 * @return: List<BillInvest>      
	 * @throws   
	 */  
	public abstract Map<String, Object> queryLoanAndInvestInfo(String id) throws Exception;

}
