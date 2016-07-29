package org.duang.service.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.annotation.ServiceLog;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.SysPowerDao;
import org.duang.dao.SysRolePowerDao;
import org.duang.entity.SysPower;
import org.duang.service.SysPowerService;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

/**   
 * 系统权限业务接口实现类
 * @ClassName:  SysPowerServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年7月25日 下午3:25:24      
 */  
@ServiceLog(ModelName="系统权限服务管理")
@Service(value="syspowerserviceimpl")
public class SysPowerServiceImpl implements SysPowerService{

	private SysPowerDao dao;
	private SysRolePowerDao rolePowerDao;

	@Resource(name="syspowerdao")
	public void setDao(SysPowerDao dao) {
		this.dao = dao;
	}
	@Resource
	public void setRolePowerDao(SysRolePowerDao rolePowerDao) {
		this.rolePowerDao = rolePowerDao;
	}


	public SysPowerServiceImpl(){
		LoggerUtils.info("注入SysPowerServiceImpl服务层", this.getClass());
	}

	/**
	 * 计数总数全部
	 * @return 			    计数值
	 */
	public int count() throws Exception{
		return dao.count();
	}

	/**
	 * 计数总数全部
	 * @param properties  字段属性
	 * @param value		    字段属性的值
	 * @return 			    计数值
	 */
	public int count(String properties, Object value) throws Exception{
		return dao.count(properties, value);
	}


	/**
	 * 计数总数全部
	 * @param properties  条件名字集合  
	 * @param values      条件值集合
	 * @return 			    计数值
	 */
	public int count(List<String> properties,List<Object> values) throws Exception{
		return dao.count(properties, values);
	}

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
	public List<SysPower> queryPowerByUserAndParent(String userid, String parentid) throws Exception{
		return dao.queryPowerByUserAndParent(userid, parentid);
	}
	

	/**
	 * 查询全部
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysPower> queryAllEntity(Order order) throws Exception {
		return dao.queryAllEntity(order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysPower> queryAllEntity(PageUtil<SysPower> page, Order order) throws Exception{
		return dao.queryAllEntity(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysPower> queryEntity(String field, Object value, PageUtil<SysPower> page, Order order) throws Exception{
		return dao.queryEntity(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysPower> queryEntity(List<String> properties, List<Object> values, PageUtil<SysPower> page) throws Exception{
		return dao.queryEntity(properties, values, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public SysPower findById(Serializable id) throws Exception{
		return dao.findById(id);
	}

	
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
	public List<SysPower> queryTopPowerByUser(String userid) throws Exception{
		return dao.queryTopPowerByUser(userid);
	}
	

	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(SysPower t) throws Exception{
		return dao.saveEntity(t);
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(SysPower t) throws Exception{
		return dao.updateEntity(t);
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(SysPower t) throws Exception{
		return dao.deleteEntity(t);
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(Serializable id) throws Exception{
		return dao.deleteEntity(id);
	}


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
	public boolean deletePower(List<SysPower> list) throws Exception{
		if (list != null && list.size() > 0) {
			for (SysPower sysPower : list) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("sysPower.id", sysPower.getId());
				if (rolePowerDao.deleteEntity(map)) {
					if (!dao.deleteEntity(sysPower.getId())) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 通过map条件对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(Map<String, Object> map) throws Exception{
		return dao.deleteEntity(map);
	}


	/**
	 * 根据sql语句执行sql代码
	 * @param sql  sql语句
	 * @return     是否执行成功
	 */
	public boolean executeSql(String sql) throws Exception{
		return dao.deleteEntity(sql);
	}


	/**
	 * 根据sql语句执行sql代码
	 * @param sql  sql语句
	 * @return     是否执行成功
	 */
	public boolean executeSql(String sql,Object... params) throws Exception{
		return dao.executeSql(sql,params);
	}


	/**
	 * 根据sql语句集合执行sql代码
	 * @param sqls  sql语句集合
	 * @return      是否执行成功
	 */
	public boolean executeSql(List<String> sqls) throws Exception{
		return dao.executeSql(sqls);
	}


}
