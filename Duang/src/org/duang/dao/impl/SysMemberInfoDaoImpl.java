package org.duang.dao.impl; 

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.duang.common.logger.LoggerUtils;
import org.duang.dao.SysMemberInfoDao;
import org.duang.dao.base.BaseDao;
import org.duang.entity.SysMemberInfo;
import org.duang.util.PageUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**   
 * 公共用户信息的dao实现类
 * @ClassName:  SysMemberInfoDaoImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月26日 下午3:23:04      
 */  
@Repository("sysmemberinfodaoimpl")
public class SysMemberInfoDaoImpl extends BaseDao<SysMemberInfo> implements SysMemberInfoDao{


	public SysMemberInfoDaoImpl(){
		LoggerUtils.info("注入SysMemberInfoDaoImpl层", this.getClass());
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
	 * 查询全部
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysMemberInfo> queryAllEntity(Order order) throws Exception {
		return super.queryByCriteria(super.getCriteria(), order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysMemberInfo> queryAllEntity(PageUtil<SysMemberInfo> page, Order order) throws Exception{
		return super.queryAll(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysMemberInfo> queryEntity(String field, Object value, PageUtil<SysMemberInfo> page, Order order) throws Exception{
		return super.query(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<SysMemberInfo> queryEntity(List<String> properties, List<Object> values, PageUtil<SysMemberInfo> page) throws Exception{
		DetachedCriteria detachedCriteria = super.fillDtCriteria(properties, values);
		return super.queryByDetachedCriteria(detachedCriteria, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public SysMemberInfo findById(Serializable id) throws Exception{
		return super.find(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(SysMemberInfo t) throws Exception{
		super.save(t);
		return true;
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(SysMemberInfo t) throws Exception{
		super.update(t);
		return true;
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(SysMemberInfo t) throws Exception{
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
}

