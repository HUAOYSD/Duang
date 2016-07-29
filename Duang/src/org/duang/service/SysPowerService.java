package org.duang.service;

import java.util.List;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.SysPower;


/**   
 * 系统权限业务接口   
 * @ClassName:  EnglishService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年7月17日 下午12:20:17      
 */  
public interface SysPowerService extends CommonInterface<SysPower>{


	/**   
	 * 根据他（含）的所有子权限，一起删除，并且删除SysRolePower
	 * @Title: deletePower   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 白攀    
	 * @date 2016年7月26日 下午4:54:50
	 * @return: boolean      
	 * @throws   
	 */  
	public abstract boolean deletePower(List<SysPower> list) throws Exception;
	
	
	/**   
	 * 根据用户id和父id获取拥有的权限
	 * @Title: queryPowerByUserAndParent   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param userid
	 * @param: @param parentid
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 白攀    
	 * @date 2016年7月28日 下午9:56:52
	 * @return: List<SysPower>      
	 * @throws   
	 */  
	public abstract List<SysPower> queryPowerByUserAndParent(String userid, String parentid) throws Exception;

	
	
	/**   
	 * 根据用户查询顶级权限
	 * @Title: queryTopPowerByUser   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param userid
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 白攀    
	 * @date 2016年7月29日 下午3:41:27
	 * @return: List<SysPower>      
	 * @throws   
	 */  
	public abstract List<SysPower> queryTopPowerByUser(String userid) throws Exception;
}
