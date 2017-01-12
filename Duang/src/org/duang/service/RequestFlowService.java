package org.duang.service;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.RequestFlow;

/**
 * 请求流水业务层接口
 * @ClassName:  ApplyLoanInfoService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年11月13日 下午 18:30:58
 */
public interface RequestFlowService extends CommonInterface<RequestFlow>{
	public boolean executeObject(RequestFlow requestFlow) throws Exception;
}
