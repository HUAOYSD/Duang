package org.duang.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.annotation.ServiceLog;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.SysPowerDao;
import org.duang.dao.SysRoleDao;
import org.duang.dao.SysRolePowerDao;
import org.duang.entity.SysPower;
import org.duang.entity.SysRole;
import org.duang.entity.SysRolePower;
import org.duang.service.SysRoleService;
import org.duang.util.DataUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;



/**   
 * 系统角色业务接口实现类
 * @ClassName:  SysRoleServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年7月25日 下午2:56:21      
 */  
@ServiceLog(ModelName="系统角色服务管理")
@Service(value="sysroleserviceimpl")
public class SysRoleServiceImpl implements SysRoleService{

	private SysRoleDao dao;
	private SysPowerDao powerDao;
	private SysRolePowerDao rolePowerDao;
	@Resource(name="sysroledao")
	public void setDao(SysRoleDao dao) {
		this.dao = dao;
	}
	@Resource(name="sysrolepowerdao")
	public void setRolePowerDao(SysRolePowerDao rolePowerDao) {
		this.rolePowerDao = rolePowerDao;
	}
	@Resource
	public void setPowerDao(SysPowerDao powerDao) {
		this.powerDao = powerDao;
	}


	public SysRoleServiceImpl(){
		LoggerUtils.info("注入SysRoleServiceImpl服务层", this.getClass());
	}

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
	public List<SysRolePower> getRolePowerInfoByRole(String roleid) throws Exception{
		List<SysRolePower> list = null;
		if (DataUtils.notEmpty(roleid)) {
			SysRole role = dao.findById(roleid);
			//得到该角色下的RolePower集合
			if (role != null) {
				list = rolePowerDao.queryEntity("sysRole", role, null, null);
			}
		}
		return list;
	}


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
	public boolean updateRoleToPower(String roleId, String[] powerIds) throws Exception {
		if (DataUtils.notEmpty(roleId)) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sysRole.id", roleId);
			if (rolePowerDao.deleteEntity(map)) {
				//2、添加
				if (powerIds != null && powerIds.length > 0) {
					List<SysRolePower> rps = new ArrayList<SysRolePower>();
					//插入新的关系到中间表
					for(String id : powerIds) {
						SysPower power = powerDao.findById(id);
						if (power != null) {
							rps.add(new SysRolePower(DataUtils.randomUUID(), power, new SysRole(roleId, null, null)));
						}
					}
					rolePowerDao.saveRolePowers(rps);
				}
			}
			return true;
		}else {
			return false;
		}
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
	 * 查询全部
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysRole> queryAllEntity(Order order) throws Exception {
		return dao.queryAllEntity(order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysRole> queryAllEntity(PageUtil<SysRole> page, Order order) throws Exception{
		return dao.queryAllEntity(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysRole> queryEntity(String field, Object value, PageUtil<SysRole> page, Order order) throws Exception{
		return dao.queryEntity(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysRole> queryEntity(List<String> properties, List<Object> values, PageUtil<SysRole> page) throws Exception{
		return dao.queryEntity(properties, values, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public SysRole findById(Serializable id) throws Exception{
		return dao.findById(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(SysRole t) throws Exception{
		return dao.saveEntity(t);
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(SysRole t) throws Exception{
		return dao.updateEntity(t);
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(SysRole t) throws Exception{
		return dao.deleteEntity(t);
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(Serializable id) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sysRole.id", id);
		if (rolePowerDao.deleteEntity(map)) {
			if (dao.deleteEntity(id)) {
				return true;
			}
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
		return dao.executeSql(sql);
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


	/**
	 * 通过属性与条件值删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(String property, Object val) throws Exception{
		return dao.deleteEntity(property, val);
	}


	/**
	 * 根据datas数据集和条件属性与值来修改实体数据
	 * @param datas
	 * @param property
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public boolean updateEntity(Map<String, Object> datas, String property, Object value) throws Exception{
		return dao.updateEntity(datas, property, value);
	}


	/**
	 * 根据datas数据集和conds条件集来修改实体数据
	 * @param datas
	 * @param conds
	 * @return
	 * @throws Exception
	 */
	public boolean updateEntity(Map<String, Object> datas, Map<String, Object> conds) throws Exception{
		return dao.updateEntity(datas, conds);
	}


	/**
	 * 通过属性与值获取操作实体类
	 * @param property 属性
	 * @param value 值
	 * @return
	 * @throws Exception
	 */
	public SysRole findEntity(String property, Object value) throws Exception{
		return dao.findEntity(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public SysRole findEntity(Map<String, Object> params) throws Exception{
		return dao.findEntity(params);
	}


	/**
	 * 根据Hql语句查询
	 * @param hql hql语句
	 * @param page 是否分页          null表示不分页
	 * @param params 写法只有一种: "张三，男，24岁"
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> queryByHQL(String hql,String counthql, PageUtil<SysRole> page, Object... params) throws Exception{
		return dao.queryByHQL(hql,counthql, page, params);
	}


	/**
	 * 根据Sql语句查询
	 * @param sql   sql语句
	 * @param page  是否分页          null表示不分页
	 * @param params 写法有俩；A:"张三，男，24岁"、   B:"Object[]{'name','张三'}，Object[]{'sex','男'}"
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> queryBySQL(String sql,String countsql, PageUtil<SysRole> page, boolean convert, Object... params) throws Exception{
		return dao.queryBySQL(sql, countsql, page, convert, params);
	}

}
