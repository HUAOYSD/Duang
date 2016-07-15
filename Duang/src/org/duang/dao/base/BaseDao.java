package org.duang.dao.base;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.duang.util.PageUtil;
import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<M> {

	/**
	 * 统计
	 * @Title: count  
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param conds
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-27 下午3:22:18
	 */
	public int count() throws Exception;

	/**
	 * 统计
	 * @Title: count 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param conds
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-27 下午3:22:18
	 */
	public int count(String sql) throws Exception;

	/**
	 * 统计
	 * @Title: count 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param conds
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-27 下午3:22:18
	 */
	public int count(String property,Object value) throws Exception;

	/**
	 * 统计
	 * @Title: count 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param conds
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-27 下午3:22:18
	 */
	public int count(Map<String, Object> conds) throws Exception;

	/**
	 * 保存
	 * @Title: save 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param obj
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2014-3-24 下午5:29:27
	 */
	public void save(M entity) throws Exception;

	/**
	 * 更新
	 * @Title: update 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param obj
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2014-3-24 下午5:29:53
	 */
	public void update(M entity) throws Exception;

	/**
	 * 根据条件更新
	 * data是要更新的属性及值的值对集合
	 * conds是更新条件的值对集合
	 * @Title: update 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param data
	 * @param conds
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:55:12
	 */
	public int update(Map<String, Object> data,Map<String, Object> conds) throws Exception;

	/**
	 * 根据条件更新
	 * data是更新参数如：set a = b,c = d
	 * property和value是条件
	 * @Title: update 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param data
	 * @param property
	 * @param value
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:56:49
	 */
	public int update(Map<String, Object> data,String property,Object value) throws Exception;

	/**
	 * 删除
	 * @Title: delete 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param obj
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-24 下午5:35:09
	 */
	public void delete(M entity) throws Exception;

	/**
	 * 删除根据id
	 * @Title: delete 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param id
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:50:05
	 */
	public void delete(Serializable Id) throws Exception;

	/**
	 * 根据条件删除
	 * @Title: delete 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param conds
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:53:05
	 */
	public int delete(Map<String, Object> conds) throws Exception;

	/**
	 * 根据条件删除
	 * @Title: delete 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param conds
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:53:05
	 */
	public int delete(String property,Object value) throws Exception;

	/**
	 * 查询一条记录
	 * @Title: find 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param className
	 * @param id
	 * @return
	 * @return T    返回类型 
	 * @author 白攀
	 * @date 2014-3-24 下午5:35:41
	 */
	public M find(Serializable Id) throws Exception;

	/**
	 * 查询一条记录
	 * @Title: find 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param className
	 * @param id
	 * @return
	 * @return T    返回类型 
	 * @author 白攀
	 * @date 2014-3-24 下午5:35:41
	 */
	public M find(Map<String, Object> param) throws Exception;

	/**
	 * 查询一条记录
	 * @Title: find 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param className
	 * @param id
	 * @return
	 * @return T    返回类型 
	 * @author 白攀
	 * @date 2014-3-24 下午5:35:41
	 */
	public M find(String property,Object value) throws Exception;

	/**
	 * 根据条件查询
	 * @Title: query 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param obj
	 * @param param
	 * @return
	 * @return List<M>    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:39:27
	 */
	public List<M> query(Map<String, Object> param) throws Exception;

	/**
	 * @Title: query 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param sql
	 * @param start
	 * @param size
	 * @param params
	 * @return
	 * @return List<M>    返回类型 
	 * @author 白攀
	 * @date 2014-4-4 上午10:53:42
	 */
	public List<M> query(String sql,PageUtil<M> page,Object...params) throws Exception;

	/**
	 * 分页
	 * @Title: query 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param param
	 * @param startIndex
	 * @param pageSize
	 * @return
	 * @return List<M>    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午9:30:12
	 */
	public List<M> query(Map<String, Object> param,PageUtil<M> page) throws Exception;

	/**
	 * 根据条件查询
	 * @Title: query 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param property
	 * @param value
	 * @return
	 * @return List<M>    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:48:31
	 */
	public List<M> query(String property,Object value) throws Exception;

	/**
	 * 分页
	 * @Title: query 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param property
	 * @param value
	 * @param startIndex
	 * @param pageSize
	 * @return
	 * @return List<M>    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午9:29:39
	 */
	public List<M> query(String property,Object value,PageUtil<M> page) throws Exception;

	/**
	 * 查询所有
	 * @Title: queryAll 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return
	 * @return List<M>    返回类型 
	 * @author 白攀
	 * @date 2014-4-2 下午1:42:52
	 */
	public List<M> queryAll() throws Exception;

	/**
	 * 分页
	 * @Title: query 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param property
	 * @param value
	 * @param startIndex
	 * @param pageSize
	 * @return
	 * @return List<M>    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午9:29:39
	 */
	public List<M> queryAll(PageUtil<M> page) throws Exception;

	/**
	 * 根据sql执行
	 * @Title: queryBySQL 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param SQL
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:59:15
	 */
	public List<M> queryBySQL(String SQL) throws Exception;

	/**
	 * @Title: queryBySQL 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param SQL
	 * @param params
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-3-31 下午4:30:11
	 */
	public List<M> queryBySQL(String SQL,Object ...params) throws Exception;

	/**
	 * 执行hql
	 * @Title: queryByHql 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param HQL
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:59:25
	 */
	public List<M> queryByHQL(String HQL) throws Exception;

	/**
	 * @Title: queryByHQL 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param HQL
	 * @param params
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-3-31 下午4:29:43
	 */
	public List<M> queryByHQL(String HQL,Object ...params) throws Exception;

	/** 根据查询数据集合
	 * @Title: queryByDetacheadCriteria 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param dt
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-4-25 下午05:11:49
	 */ 
	public abstract List<M> queryByDetachedCriteria(DetachedCriteria dt) throws Exception;

	/** 根据查询数据集合（带分页）
	 * @Title: queryByDetacheadCriteria 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param dt
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-4-25 下午05:11:49
	 */ 
	public abstract List<M> queryByDetachedCriteria(DetachedCriteria dt,PageUtil<M> page) throws Exception;

	/** 
	 * 根据detachedCriteria统计记录数
	 * @Title: countByDetachedCriteria 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param dt
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-4-29 上午10:24:26
	 */ 
	public abstract int countByDetachedCriteria(DetachedCriteria dt) throws Exception;

	/** 
	 * 为detachedCriteria填充条件
	 * @Title: fillDtCriteriaByTerms 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param dt              detachedCriteria对象
	 * @param propertys       条件集合
	 * @param values          条件值集合
	 * @return DetachedCriteria    有了添加的dt
	 * @author 白攀
	 * @date 2014-4-29 上午10:05:51
	 */ 
	public abstract DetachedCriteria fillDtCriteria(List<String> properties,List<Object> values) throws Exception;

	/** 
	 * @Title: isExist 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-3-31 上午10:37:10
	 * @param id
	 * @return 
	 * @see com.dao.BaseDao#isExist(java.io.Serializable) 
	 */ 
	public boolean isExist(Serializable id) throws Exception;

	/**
	 * 判断指定字段为xx的记录是否存在
	 * @Title: isExist 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param property
	 * @param value
	 * @return
	 * @return boolean    返回类型 
	 * @author 白攀
	 * @date 2014-4-11 上午11:56:07
	 */
	public boolean isExist(String property,Object value) throws Exception;

	/**	
	 *  执行sql
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param sql
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-31 上午11:59:10
	 */
	public int executeBySql(String sql) throws Exception;

	/**
	 * 执行hql
	 * @Title: executeByHql 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param hql
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-31 上午11:59:47
	 */
	public int executeByHql(String hql) throws Exception;

	/**
	 * 占位符方式执行hql语句
	 * @Title: executeByHql 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param hql
	 * @param params
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-3-31 下午2:18:29
	 */
	public int executeByHql(String hql,Object ... params) throws Exception;

	/**
	 * 占位符方式执行sql语句
	 * @Title: executeBySql 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param sql
	 * @param params
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-4-4 下午3:35:18
	 */
	public int executeBySql(String sql, Object ...params) throws Exception;

	/** 
	 * 占位符方式执行hql语句
	 * @Title: executeByHql 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param hql
	 * @param params
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-8-25 上午8:51:28
	 */ 
	public int executeByHql(String hql,  List<Object> params) throws Exception;

	/** 
	 * 计算count数量根据sql语句和占位符方式
	 * @Title: countBySql 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param sql
	 * @param params
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-8-25 上午8:51:41
	 */ 
	public int countBySql(String sql, Object ...params) throws Exception;

	/** 
	 * 计算count数量根据hql语句和占位符方式
	 * @Title: countBySql 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param sql
	 * @param params
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2014-8-25 上午8:51:41
	 */ 
	public int countByHql(String hql, Object ...params) throws Exception;
	
	/**
	 * 根据字段获取字段sum值
	 * getSum(这里用一句话描述这个方法的作用)
	 * @Title: getSum
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getSum(String field) throws Exception;
	
	/**
	 * 根据字段获取字段sum值
	 * getSum(这里用一句话描述这个方法的作用)
	 * @Title: getSum
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getSum(String field,String property,Object value) throws Exception;
	
	/**
	 * 根据字段获取字段sum值
	 * getSum(这里用一句话描述这个方法的作用)
	 * @Title: getSum
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getSum(String field,Map<String, Object> conds) throws Exception;
	
	/**
	 * 根据字段获取字段sum值
	 * getSum(这里用一句话描述这个方法的作用)
	 * @Title: getSum
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getSum(String field,DetachedCriteria dt) throws Exception;
	
	/**
	 * 根据字段获取字段max值
	 * getMax(这里用一句话描述这个方法的作用)
	 * @Title: getMax
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getMax(String field) throws Exception;
	
	/**
	 * 根据字段获取字段max值
	 * getMax(这里用一句话描述这个方法的作用)
	 * @Title: getMax
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getMax(String field,String property,Object value) throws Exception;
	
	/**
	 * 根据字段获取字段max值
	 * getMax(这里用一句话描述这个方法的作用)
	 * @Title: getMax
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getMax(String field,Map<String, Object> conds) throws Exception;
	
	/**
	 * 根据字段获取字段max值
	 * getMax(这里用一句话描述这个方法的作用)
	 * @Title: getMax
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getMax(String field,DetachedCriteria dt) throws Exception;
	
	/**
	 * 根据字段获取字段min值
	 * getMin(这里用一句话描述这个方法的作用)
	 * @Title: getMin
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getMin(String field) throws Exception;
	
	/**
	 * 根据字段获取字段min值
	 * getMin(这里用一句话描述这个方法的作用)
	 * @Title: getMin
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getMin(String field,String property,Object value) throws Exception;
	
	/**
	 * 根据字段获取字段min值
	 * getMin(这里用一句话描述这个方法的作用)
	 * @Title: getMin
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getMin(String field,Map<String, Object> conds) throws Exception;
	
	/**
	 * 根据字段获取字段min值
	 * getMin(这里用一句话描述这个方法的作用)
	 * @Title: getMin
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getMin(String field,DetachedCriteria dt) throws Exception;
	
	/**
	 * 根据字段获取字段avg值
	 * getAvg(这里用一句话描述这个方法的作用)
	 * @Title: getAvg
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getAvg(String field) throws Exception;
	
	/**
	 * 根据字段获取字段avg值
	 * getAvg(这里用一句话描述这个方法的作用)
	 * @Title: getAvg
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getAvg(String field,String property,Object value) throws Exception;
	
	/**
	 * 根据字段获取字段avg值
	 * getAvg(这里用一句话描述这个方法的作用)
	 * @Title: getAvg
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getAvg(String field,Map<String, Object> conds) throws Exception;
	
	/**
	 * 根据字段获取字段avg值
	 * getAvg(这里用一句话描述这个方法的作用)
	 * @Title: getAvg
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getAvg(String field,DetachedCriteria dt) throws Exception;
	
	/**
	 * 根据字段获取字段getCountDistinct值
	 * getCountDistinct(这里用一句话描述这个方法的作用)
	 * @Title: getCountDistinct
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getCountDistinct(String field) throws Exception;
	
	/**
	 * 根据字段获取字段getCountDistinct值
	 * getCountDistinct(这里用一句话描述这个方法的作用)
	 * @Title: getCountDistinct
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getCountDistinct(String field,String property,Object value) throws Exception;
	
	/**
	 * 根据字段获取字段getCountDistinct值
	 * getCountDistinct(这里用一句话描述这个方法的作用)
	 * @Title: getCountDistinct
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getCountDistinct(String field,Map<String, Object> conds) throws Exception;
	
	/**
	 * 根据字段获取字段getCountDistinct值
	 * getCountDistinct(这里用一句话描述这个方法的作用)
	 * @Title: getCountDistinct
	 * @Description: TODO
	 * @param @param field
	 * @param @return
	 * @param @throws Exception    
	 * @return int    返回类型
	 * @author 白攀 
	 * @date 2016-2-29 上午11:31:12
	 * @throws
	 */
	public int getCountDistinct(String field,DetachedCriteria dt) throws Exception;
}
