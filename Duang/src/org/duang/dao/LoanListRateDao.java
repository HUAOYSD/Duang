package org.duang.dao; 

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.LoanListRate;
/**
 * 借贷信息的基数
 * @ClassName:  LoanListRateDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年12月20日 上午10:28:35
 */
public interface LoanListRateDao extends CommonInterface<LoanListRate>  {

	/**
	 * 获取借贷基数
	 * @Title: getLoanListRate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @param: @throws Exception
	 * @author LiYonghui
	 * @date 2016年12月20日 下午4:19:51
	 * @return: LoanListRate
	 * @throws
	 */
	public abstract LoanListRate getLoanListRate() throws Exception;
}

