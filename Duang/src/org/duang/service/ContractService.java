package org.duang.service;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.Contract;

/**
 * 合同业务层接口
 * @ClassName:  ContractService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年9月18日 上午10:30:58
 */
public interface ContractService extends CommonInterface<Contract>{
	/**
	 * 获取今年的合同数目
	 * @Title: getContractIndexByYear   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年10月28日 下午3:09:20
	 * @return: int      
	 * @throws
	 */
	public abstract int getContractIndexByYear() throws Exception;
}
