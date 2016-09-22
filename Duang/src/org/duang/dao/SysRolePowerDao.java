package org.duang.dao; 

import java.util.List;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.SysRolePower;

/**   
 * 系统用户权限多对多参照表dao接口
 * @ClassName:  SysRolePowerDao   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年7月25日 下午4:24:24      
 */  
public interface SysRolePowerDao extends CommonInterface<SysRolePower>  {

	/**   
	 * 添加角色权限列表
	 * @Title: saveRolePowers   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param rps
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年7月26日 下午2:49:30
	 * @return: boolean      
	 * @throws   
	 */  
	public abstract boolean saveRolePowers(List<SysRolePower> rps) throws Exception;
	
}

