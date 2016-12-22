package org.duang.service;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.Scale;
import org.duang.entity.ScaleLoanList;


/**   
 * 借贷记录与理财标关联业务接口
 * @ClassName:  ScaleLoanListService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年8月17日 下午3:36:24      
 */  
public interface ScaleLoanListService extends CommonInterface<ScaleLoanList>{

	/**   
	 * 匹配借贷记录到理财标
	 * @Title: matchScaleLoanRecords   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param scaleId
	 * @param: @param loanListIds
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年8月17日 下午3:14:19
	 * @return: boolean      
	 * @throws   
	 */  
	public abstract boolean matchScaleLoanRecords(Scale scale, String[] loanListIds) throws Exception;
	
}
