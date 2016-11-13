package org.duang.service;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.Scale;

/**   
 * 标业务接口
 * @ClassName:  SysInvestMemberService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月25日 下午4:27:27      
 */ 
public interface ScaleService extends CommonInterface<Scale>{
 
	/**
	 * 查询标的状态  //0新建标，1流标，2可投标，3已完成
	 * @Title: findScaleState   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月11日 上午11:09:10
	 * @return: String      
	 * @throws
	 */
	public abstract int findScaleState(String id) throws Exception;
}
