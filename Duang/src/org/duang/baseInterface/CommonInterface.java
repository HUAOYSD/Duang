package org.duang.baseInterface;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;


/**
 * 通用的接口(同时适用于Service层和dao层)
 * @date 2016-7-15 下午2:55：45
 * @author 5y
 */
public interface CommonInterface<T> {
	
	/**
	 * 计数总数全部
	 * @return 			    计数值
	 */
	public abstract int count() throws Exception;
	
	
	/**
	 * 计数COUNT
	 * @param properties  字段属性
	 * @param value		    字段属性的值
	 * @return 			    计数值
	 */
	public abstract int count(String properties, Object value) throws Exception;
	
	
	/**
	 * 计数COUNT
	 * @param properties  条件名字集合  
	 * @param values      条件值集合
	 * @return 			    计数值
	 */
	public abstract int count(List<String> properties,List<Object> values) throws Exception;
	
	
	/**
	 * 查询全部
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public abstract List<T> queryAllEntity(Order order) throws Exception;


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public abstract List<T> queryAllEntity(PageUtil<T> page, Order order) throws Exception;

	
	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public abstract List<T> queryEntity(String field, Object value, PageUtil<T> page, Order order) throws Exception;
	
	
	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public abstract List<T> queryEntity(List<String> properties, List<Object> values, PageUtil<T> page) throws Exception;
	
	
	/**
	 * 根据Hql语句查询
	 * @param hql hql语句
	 * @param page 是否分页          null表示不分页
	 * @param params 写法只有一种: "张三，男，24岁"
	 * @return
	 * @throws Exception
	 */
	public abstract List<T> queryByHQL(String hql, String countHql, PageUtil<T> page, Object... params) throws Exception;
	
	
	/**
	 * 根据Sql语句查询
	 * @param sql   sql语句
	 * @param countSql 计数语句
	 * @param convert 代表是否转换为业务实体
	 * @param page  是否分页          null表示不分页
	 * @param params 写法有俩；A:"张三，男，24岁"、   B:"Object[]{'name','张三'}，Object[]{'sex','男'}"
	 * @return
	 * @throws Exception
	 */
	public abstract List<T> queryBySQL(String sql, String countSql, PageUtil<T> page, boolean convert, Object... params) throws Exception;


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public abstract T findById(Serializable id) throws Exception;
	
	
	/**
	 * 通过属性与值获取操作实体类
	 * @param property 属性
	 * @param value 值
	 * @return
	 * @throws Exception
	 */
	public abstract T findEntity(String property, Object value) throws Exception;
	
	
	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public abstract T findEntity(Map<String, Object> params) throws Exception;


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public abstract boolean saveEntity(T t) throws Exception;


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public abstract boolean updateEntity(T t) throws Exception;
	
	
	/**
	 * 根据datas数据集和条件属性与值来修改实体数据
	 * @param datas 数据集
	 * @param property 条件
	 * @param value 值
	 * @return
	 * @throws Exception
	 */
	public abstract boolean updateEntity(Map<String, Object> datas, String property, Object value) throws Exception;

	
	/**
	 * 根据datas数据集和conds条件集来修改实体数据
	 * @param datas  数据集
	 * @param conds  条件集
	 * @return
	 * @throws Exception
	 */
	public abstract boolean updateEntity(Map<String, Object> datas, Map<String, Object> conds) throws Exception;


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public abstract boolean deleteEntity(T t) throws Exception;


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public abstract boolean deleteEntity(Serializable id) throws Exception;
	
	
	/**
	 * 通过属性与条件值删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public abstract boolean deleteEntity(String property, Object val) throws Exception;
	
	
	/**
	 * 通过map条件对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public abstract boolean deleteEntity(Map<String, Object> map) throws Exception;


	/**
	 * 根据sql语句执行sql代码
	 * @param sql  sql语句
	 * @return     是否执行成功
	 */
	public abstract boolean executeSql(String sql) throws Exception;
	
	
	/**
	 * 根据sql语句执行sql代码
	 * @param sql  sql语句
	 * @return     是否执行成功
	 */
	public abstract boolean executeSql(String sql,Object... param) throws Exception;


	/**
	 * 根据sql语句集合执行sql代码
	 * @param sqls  sql语句集合
	 * @return      是否执行成功
	 */
	public abstract boolean executeSql(List<String> sqls) throws Exception;
}
