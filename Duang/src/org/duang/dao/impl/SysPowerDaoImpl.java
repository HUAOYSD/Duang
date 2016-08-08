package org.duang.dao.impl; 

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.duang.common.logger.LoggerUtils;
import org.duang.dao.SysPowerDao;
import org.duang.dao.base.BaseDao;
import org.duang.entity.SysPower;
import org.duang.util.DataUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**   
 * 系统权限dao实现类
 * @ClassName:  SysPowerDaoImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年7月25日 下午3:23:04      
 */  
@Repository("syspowerdao")
public class SysPowerDaoImpl extends BaseDao<SysPower> implements SysPowerDao{


	public SysPowerDaoImpl(){
		LoggerUtils.info("注入SysPowerDaoImpl层", this.getClass());
	}


	/**
	 * 计数总数全部
	 * @return 			    计数值
	 */
	public int count() throws Exception{
		return super.count();
	}


	/**
	 * 计数总数全部
	 * @param properties  字段属性
	 * @param value		    字段属性的值
	 * @return 			    计数值
	 */
	public int count(String properties, Object value) throws Exception{
		return super.count(properties, value);
	}


	/**
	 * 计数总数全部
	 * @param properties  条件名字集合  
	 * @param values      条件值集合
	 * @return 			    计数值
	 */
	public int count(List<String> properties,List<Object> values) throws Exception{
		DetachedCriteria detachedCriteria = super.fillDtCriteria(properties, values);
		return super.countByDetachedCriteria(detachedCriteria);
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
		if (DataUtils.notEmpty(userid)) {
			String sql = "SELECT SYS_POWER.* FROM SYS_POWER ";
			sql += "INNER JOIN SYS_ROLE_POWER ON SYS_ROLE_POWER.POWER_ID = SYS_POWER.ID ";
			sql += "INNER JOIN SYS_ROLE ON SYS_ROLE.ID = SYS_ROLE_POWER.ROLE_ID ";
			sql += "INNER JOIN SYS_USER ON SYS_USER.ROLE_ID = SYS_ROLE.ID ";
			sql += "WHERE SYS_POWER.PARENT_ID = ? AND SYS_USER.ID = ? ";
			sql += "ORDER BY SYS_POWER.SORT_INDEX ASC";
			return queryBySQL(sql, null, parentid, userid);
		}else {
			return null;
		}
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
	public List<SysPower> queryTopPowerByUser(String userid) throws Exception {
		if (DataUtils.notEmpty(userid)) {
			String sql = "SELECT * FROM SYS_POWER WHERE PARENT_ID = 'SYSPOWERS' ";
			sql += "HAVING ID IN ";
			sql += "(SELECT PARENT_ID FROM SYS_POWER  ";
			sql += "WHERE PARENT_ID <> 'SYSPOWERS' ";
			sql += "AND ID IN (SELECT POWER_ID FROM SYS_ROLE_POWER WHERE ROLE_ID = (SELECT ROLE_ID FROM SYS_USER WHERE ID = '"+userid+"')) ";
			sql += ") ";
			sql += "ORDER BY SYS_POWER.SORT_INDEX DESC";
			return queryBySQL(sql, null);
		}else {
			return null;
		}
	}


	/**
	 * 查询全部
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysPower> queryAllEntity(Order order) throws Exception {
		return super.queryByCriteria(super.getCriteria(), order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysPower> queryAllEntity(PageUtil<SysPower> page, Order order) throws Exception{
		return super.queryAll(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysPower> queryEntity(String field, Object value, PageUtil<SysPower> page, Order order) throws Exception{
		return super.query(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysPower> queryEntity(List<String> properties, List<Object> values, PageUtil<SysPower> page) throws Exception{
		DetachedCriteria detachedCriteria = super.fillDtCriteria(properties, values);
		return super.queryByDetachedCriteria(detachedCriteria, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public SysPower findById(Serializable id) throws Exception{
		return super.find(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(SysPower t) throws Exception{
		super.save(t);
		return true;
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(SysPower t) throws Exception{
		super.update(t);
		return true;
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(SysPower t) throws Exception{
		super.delete(t);
		return true;
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(Serializable id) throws Exception{
		super.delete(id);
		return true;
	}


	/**
	 * 通过map条件对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(Map<String, Object> map) throws Exception{
		super.delete(map);
		return true;
	}


	/**
	 * 根据sql语句执行sql代码
	 * @param sql  sql语句
	 * @return     是否执行成功
	 */
	public boolean executeSql(String sql) throws Exception{
		super.executeBySql(sql);
		return true;
	}


	/**
	 * 根据sql语句执行sql代码
	 * @param sql  sql语句
	 * @return     是否执行成功
	 */
	public boolean executeSql(String sql,Object... params) throws Exception{
		super.executeBySql(sql,params);
		return true;
	}


	/**
	 * 根据sql语句集合执行sql代码
	 * @param sqls  sql语句集合
	 * @return      是否执行成功
	 */
	public boolean executeSql(List<String> sqls) throws Exception{
		if (sqls!=null && sqls.size()>0) {
			for (String string : sqls) {
				super.executeBySql(string);
			}
		}
		return true;
	}


	/**
	 * 通过属性与条件值删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(String property, Object val) throws Exception{
		return super.delete(property, val) >= 1;
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
		return super.update(datas, property, value) >= 1;
	}


	/**
	 * 根据datas数据集和conds条件集来修改实体数据
	 * @param datas
	 * @param conds
	 * @return
	 * @throws Exception
	 */
	public boolean updateEntity(Map<String, Object> datas, Map<String, Object> conds) throws Exception{
		return super.update(datas, conds) >= 1;
	}


	/**
	 * 通过属性与值获取操作实体类
	 * @param property 属性
	 * @param value 值
	 * @return
	 * @throws Exception
	 */
	public SysPower findEntity(String property, Object value) throws Exception{
		return super.find(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public SysPower findEntity(Map<String, Object> params) throws Exception{
		return super.find(params);
	}


	/**
	 * 根据Hql语句查询
	 * @param hql hql语句
	 * @param page 是否分页          null表示不分页
	 * @param params 写法只有一种: "张三，男，24岁"
	 * @return
	 * @throws Exception
	 */
	public List<SysPower> queryByHQL(String hql, PageUtil<SysPower> page, Object... params) throws Exception{
		return super.queryByHQL(hql, page, params);
	}


	/**
	 * 根据Sql语句查询
	 * @param sql   sql语句
	 * @param page  是否分页          null表示不分页
	 * @param params 写法有俩；A:"张三，男，24岁"、   B:"Object[]{'name','张三'}，Object[]{'sex','男'}"
	 * @return
	 * @throws Exception
	 */
	public List<SysPower> queryBySQL(String sql, PageUtil<SysPower> page, Object... params) throws Exception{
		return super.queryBySQL(sql, page, params);
	}

}

