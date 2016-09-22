package org.duang.service;

import java.util.List;

import org.duang.baseInterface.CommonInterface;
import org.duang.entity.SysRole;
import org.duang.entity.SysRolePower;

/**   
 * 系统角色业务接口
 * @ClassName:  SysRoleService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年7月25日 下午2:55:17      
 */  
public interface SysRoleService extends CommonInterface<SysRole>{


	/**   
	 * 更新角色的权限
	 * @Title: updateRoleToPower   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param roleId
	 * @param: @param powerIds
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月26日 下午2:35:52
	 * @return: boolean      
	 * @throws   
	 */  
	public abstract boolean updateRoleToPower(String roleId, String[] powerIds) throws Exception;
	
	
	
	/**   
	 * 根据角色id得到角色所拥有权限信息
	 * @Title: getRolePowerInfoByRole   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param roleid
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年7月26日 下午3:02:24
	 * @return: boolean      
	 * @throws   
	 */  
	public abstract List<SysRolePower> getRolePowerInfoByRole(String roleid) throws Exception;


}
