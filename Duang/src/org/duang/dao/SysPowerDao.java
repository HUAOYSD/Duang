package org.duang.dao; 

import java.util.List;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.SysPower;

/**   
 * 系统权限dao接口
 * @ClassName:  SysPowerDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年7月25日 下午3:22:35      
 */  
public interface SysPowerDao extends CommonInterface<SysPower>  {

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

