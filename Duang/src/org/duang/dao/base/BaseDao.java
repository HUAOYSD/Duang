package org.duang.dao.base;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.duang.common.logger.LoggerUtils;
import org.duang.util.PageUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.AggregateProjection;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

public class BaseDao<M> {
	private SessionFactory sessionFactory;
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LoggerUtils.info("BaseDao注入SessionFactory", this.getClass());
	}

	/** 
	 * 获取session
	 * @Title: getSession 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return
	 * @return Session    返回类型 
	 * @author 白攀
	 * @date 2014-8-21 下午2:00:36
	 */ 
	public Session getSession(){
		//		System.out.println(sessionFactory);
		//		System.out.println(sessionFactory.getCurrentSession());
		//		System.out.println(sessionFactory.openSession());
		//		return sessionFactory.openSession();
		return sessionFactory.getCurrentSession();
	}

	private Class<M> entityClass;


	@SuppressWarnings("unchecked")
	public BaseDao(){
		Type paramType = getClass().getGenericSuperclass();
		if(paramType instanceof ParameterizedType){
			ParameterizedType pty = (ParameterizedType)paramType;
			this.entityClass = (Class<M>)pty.getActualTypeArguments()[0];
		}else{
			this.entityClass = null;
		}
	}


	/* * * * * * * * * * * * * * * * * * * * * * * COUNT * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/**
	 * 统计
	 * @Title: count 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param conds
	 * @return
	 * @return Integer    返回类型 
	 * @author 白攀
	 * @date 2014-3-27 下午3:22:18
	 */
	public int count() throws Exception{
		Query q = this.getSession().createQuery("select count(*) from "+entityClass.getName());
		Number number=(Number) q.uniqueResult();
		return number.intValue();
	}


	/** 
	 * 统计
	 * @Title: count 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-4-3 下午3:53:09
	 * @param sql
	 * @return 
	 * @see com.dao.BaseDao#count(java.lang.String) 
	 */ 
	public int count(String sql) throws Exception{
		Query q = this.getSession().createSQLQuery(sql);
		Number number=(Number) q.uniqueResult();
		return number.intValue();
	}


	/**
	 * 统计
	 * @Title: count 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param conds
	 * @return
	 * @return Integer    返回类型 
	 * @author 白攀
	 * @date 2014-3-27 下午3:22:18
	 */
	public int count(String property,Object value) throws Exception{
		Map<String, Object> conds=new HashMap<String, Object>();
		conds.put(property, value);
		return this.count(conds);
	}


	/**
	 * 统计
	 * @Title: count 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param conds
	 * @return
	 * @return Integer    返回类型 
	 * @author 白攀
	 * @date 2014-3-27 下午3:22:18
	 */
	public int count(Map<String, Object> conds) throws Exception{
		if(conds==null || conds.isEmpty()){
			return 0;
		}
		StringBuilder sb=new StringBuilder();
		sb.append("select count(*) from "+entityClass.getName()+" where ");
		//设置条件参数
		Set<String> condKeys=conds.keySet();
		int i=0;
		for(String key:condKeys){
			String k=key.replaceAll("\\.", "")+"w";
			sb.append(key+"=:"+k);
			if(i<condKeys.size()-1){
				sb.append(" and ");
			}
			i++;
		}
		Query q = this.getSession().createQuery(sb.toString());
		for(String key:condKeys){
			String k=key.replaceAll("\\.", "")+"w";
			q.setParameter(k, conds.get(key));
		}
		Number number=(Number) q.uniqueResult();
		return number.intValue();
	}


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
	public int countByDetachedCriteria(DetachedCriteria dt) throws Exception{
		int count = 0;
		dt.setProjection(Projections.rowCount());
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		count = number.intValue();
		dt.setProjection(null);
		return count;
	}


	/** 
	 * @Title: countBySql 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-4-10 下午2:43:01
	 * @param sql
	 * @param params:“?”对应的值列表
	 * @return 
	 * @see com.dao.BaseDao#countBySql(java.lang.String, java.lang.Object[]) 
	 */ 
	public int countBySql(String sql, Object... params) throws Exception{
		Query q = this.getSession().createSQLQuery(sql).addEntity(entityClass);
		for(int i = 0 ; i < params.length ; i++){
			q.setParameter(i, params[i]);
		}
		Number number = (Number)q.uniqueResult();
		return number.intValue();
	}


	/** 
	 * @Title: countByHql 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-4-10 下午2:43:01
	 * @param hql
	 * @param params:“?”对应的值列表
	 * @return 
	 * @see com.dao.BaseDao#countByHql(java.lang.String, java.lang.Object[]) 
	 */ 
	public int countByHql(String hql, Object... params) throws Exception{
		Query q = this.getSession().createQuery(hql);
		for(int i = 0 ; i < params.length; i++){
			q.setParameter(i,params[i]);
		}
		Number number=(Number) q.uniqueResult();
		return number.intValue();
	}
	/* * * * * * * * * * * * * * * * * * * * * * * COUNT END * * * * * * * * * * * * * * * * * * * * * * * * * * * */




	/* * * * * * * * * * * * * * * * * * * * * * * FIND * * * * * * * * * * * * * * * * * * * * * * * * * * * */
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
	@SuppressWarnings("unchecked")
	public M find(Serializable id) throws Exception{
		return (M) this.getSession().get(entityClass, id);
	}


	/**
	 * 查询一条记录
	 * param条件全都只能是等值的如a=b不能是a like b之类的
	 * @Title: find 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param className
	 * @param id
	 * @return
	 * @return T    返回类型 
	 * @author 白攀
	 * @date 2014-3-24 下午5:35:41
	 */
	@SuppressWarnings("unchecked")
	public M find(Map<String, Object> param) throws Exception{
		if(param==null || param.isEmpty()){
			return null;
		}
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.allEq(param));
		criteria.setMaxResults(1);
		List<M> list= criteria.list();
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}


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
	public M find(String property,Object value) throws Exception{
		Map<String,Object> param=new HashMap<String, Object>();
		param.put(property, value);
		return find(param);
	}
	/* * * * * * * * * * * * * * * * * * * * * * * FIND END * * * * * * * * * * * * * * * * * * * * * * * * * */




	/* * * * * * * * * * * * * * * * * * * * * * * QUERY * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/** 
	 * @Title: query 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-4-4 上午10:54:05
	 * @param sql
	 * @param start
	 * @param size
	 * @param params
	 * @return 
	 * @see com.dao.BaseDao#query(java.lang.String, int, int, java.lang.Object[]) 
	 */ 
	@SuppressWarnings("unchecked")
	public List<M> query(String sql, PageUtil<M> page, Object... params) throws Exception{
		Query query = this.getSession().createSQLQuery(sql).addEntity(entityClass);
		if(params != null && "".equals(params)) {
			for(int i = 0 ; i < params.length ; i++){
				query.setParameter(i, params[i]);
			}
		}
		if (page != null) {
			int countRecords = countBySql(sql, params);
			//设置总条数
			page.setCountRecords(countRecords);
			//设置总页数（加判断是by zero）
			if(page.getPageRecords()!= 0){
				page.setCountPages(true,(countRecords%page.getPageRecords()==0)?countRecords/page.getPageRecords():countRecords/page.getPageRecords()+1);
			}
			int maxResult = page.getPageRecords();
			int firstResult = (page.getCurrentPageNum() - 1) * maxResult;
			query.setFirstResult(firstResult).setMaxResults(maxResult);
		}
		return query.list();
	}


	/**
	 * 分页查询
	 * param条件全都只能是等值的如a=b不能是a like b之类的
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
	@SuppressWarnings("unchecked")
	public List<M> query(Map<String, Object> param, PageUtil<M> page) throws Exception{
		if(param==null || param.isEmpty()){
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append("from "+entityClass.getName()+" where ");
		//设置条件参数
		Set<String> condKeys=param.keySet();
		int i=0;
		for(String key:condKeys){
			String k=key.replaceAll("\\.", "")+"w";
			sb.append(key+"=:"+k);
			if(i<condKeys.size()-1){
				sb.append(" and ");
			}
			i++;
		}
		Query q = this.getSession().createQuery(sb.toString());
		for(String key:condKeys){
			String k=key.replaceAll("\\.", "")+"w";
			q.setParameter(k, param.get(key));
		}
		if (page != null) {
			int countRecords = count(param);
			//设置总条数
			page.setCountRecords(countRecords);
			//设置总页数（加判断是by zero）
			if(page.getPageRecords()!= 0){
				page.setCountPages(true,(countRecords%page.getPageRecords()==0)?countRecords/page.getPageRecords():countRecords/page.getPageRecords()+1);
			}
			int maxResult = page.getPageRecords();
			int firstResult = (page.getCurrentPageNum() - 1) * maxResult;
			q.setFirstResult(firstResult).setMaxResults(maxResult);
		}
		return q.list();
	}


	/**
	 * 分页查询
	 * param条件全都只能是等值的如a=b不能是a like b之类的
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
	public List<M> query(String property,Object value,PageUtil<M> page) throws Exception{
		Map<String,Object> param=new HashMap<String, Object>();
		param.put(property, value);
		return query(param, page);
	}


	/**
	 * 根据条件查询
	 * param条件全都只能是等值的如a=b不能是a like b之类的
	 * @Title: query 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param obj
	 * @param param
	 * @return
	 * @return List<M>    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:39:27
	 */
	@SuppressWarnings("unchecked")
	public List<M> query(Map<String, Object> param) throws Exception{
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.allEq(param));
		return criteria.list();
	}


	/**
	 * 分页查询
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
	@SuppressWarnings("unchecked")
	public List<M> queryAll(PageUtil<M> page) throws Exception{
		Criteria criteria = this.getCriteria();
		if (page != null) {
			int countRecords = count();
			//设置总条数
			page.setCountRecords(countRecords);
			//设置总页数（加判断是by zero）
			if(page.getPageRecords()!= 0){
				page.setCountPages(true,(countRecords%page.getPageRecords()==0)?countRecords/page.getPageRecords():countRecords/page.getPageRecords()+1);
			}
			int maxResult = page.getPageRecords();
			int firstResult = (page.getCurrentPageNum() - 1) * maxResult;
			criteria.setFirstResult(firstResult).setMaxResults(maxResult);
		}
		return criteria.list();
	}
	
	
	/**
	 * 分页查询
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
	@SuppressWarnings("unchecked")
	public List<M> queryAll(PageUtil<M> page, Order order) throws Exception{
		Criteria criteria = this.getCriteria();
		if (order != null) {
			criteria.addOrder(order);
		}
		if (page != null) {
			int countRecords = count();
			//设置总条数
			page.setCountRecords(countRecords);
			//设置总页数（加判断是by zero）
			if(page.getPageRecords()!= 0){
				page.setCountPages(true,(countRecords%page.getPageRecords()==0)?countRecords/page.getPageRecords():countRecords/page.getPageRecords()+1);
			}
			int maxResult = page.getPageRecords();
			int firstResult = (page.getCurrentPageNum() - 1) * maxResult;
			criteria.setFirstResult(firstResult).setMaxResults(maxResult);
		}
		return criteria.list();
	}


	/**
	 * 根据条件查询
	 * param条件全都只能是等值的如a=b不能是a like b之类的
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
	public List<M> query(String property,Object value) throws Exception{
		Map<String,Object> param=new HashMap<String, Object>();
		param.put(property, value);
		return query(param);
	}


	/**
	 * 查询所有
	 * @Title: queryAll 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return
	 * @return List<M>    返回类型 
	 * @author 白攀
	 * @date 2014-4-2 下午1:42:52
	 */
	@SuppressWarnings("unchecked")
	public List<M> queryAll() throws Exception{
		Criteria criteria = this.getCriteria();
		return criteria.list();
	}


	/**
	 * 根据sql查询
	 * @Title: queryBySQL 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param SQL
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:59:15
	 */
	@SuppressWarnings("unchecked")
	public List<M> queryBySQL(String sql) throws Exception{
		Query q = this.getSession().createSQLQuery(sql).addEntity(entityClass);
		return q.list();
	}


	/** 
	 * 根据sql语句查询
	 * @Title: queryBySQL 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-3-31 下午4:31:21
	 * @param SQL
	 * @param params 的写法有俩；A:"张三，男，24岁"、   B:"Object[]{'name','张三'}，Object[]{'sex','男'}"
	 * @return 
	 * @see com.dao.BaseDao#queryBySQL(java.lang.String, java.lang.Object[]) 
	 */ 
	@SuppressWarnings("unchecked")
	public List<M> queryBySQL(String sql, Object... params) throws Exception{
		Query q = this.getSession().createSQLQuery(sql).addEntity(entityClass);
		int index = 0;
		for(int i = 0 ; i < params.length ; i++){
			if(params[i] instanceof Object[]){
				Object[] oarr = (Object[]) params[i];
				for(int j = 0;j < oarr.length;j++){
					q.setParameter(j+index, oarr[j]);
				}
				index = index + oarr.length;
			}else{
				q.setParameter(i, params[i]);
				index = index + 1;
			}
		}
		return q.list();
	}


	/**
	 * 执行hql查询
	 * @Title: queryByHql 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param HQL
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:59:25
	 */
	@SuppressWarnings("unchecked")
	public List<M> queryByHQL(String hql) throws Exception{
		Query q = this.getSession().createQuery(hql);
		return q.list();
	}


	/** 
	 * @Title: queryByHQL 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-3-31 下午4:31:21
	 * @param HQL
	 * @param params 的写法只有一种；A:"张三，男，24岁"、   没有：B:"Object[]{'name','张三'}，Object[]{'sex','男'}"
	 * @return 
	 * @see com.dao.BaseDao#queryByHQL(java.lang.String, java.lang.Object[]) 
	 */ 
	@SuppressWarnings("unchecked")
	public List<M> queryByHQL(String hql, Object... params) throws Exception{
		Query q = this.getSession().createQuery(hql);
		for(int i = 0 ; i < params.length ; i++){
			q.setParameter(i, params[i]);
		}
		return q.list();
	}

	/** 
	 * 根据criteria查询数据集合
	 * @Title: queryByDetacheadCriteria 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param dt
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-4-25 下午05:11:49
	 */ 
	@SuppressWarnings("unchecked")
	public List<M> queryByCriteria(Criteria criteria, Order order) throws Exception{
		if (order != null) {
			criteria.addOrder(order);
		}
		return criteria.list();
	}
	
	
	/**
	 * 分页查询
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
	@SuppressWarnings("unchecked")
	public List<M> query(String field, Object value, PageUtil<M> page, Order order) throws Exception{
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq(field, value));
		if (order != null) {
			criteria.addOrder(order);
		}
		if (page != null) {
			int countRecords = count();
			//设置总条数
			page.setCountRecords(countRecords);
			//设置总页数（加判断是by zero）
			if(page.getPageRecords()!= 0){
				page.setCountPages(true,(countRecords%page.getPageRecords()==0)?countRecords/page.getPageRecords():countRecords/page.getPageRecords()+1);
			}
			int maxResult = page.getPageRecords();
			int firstResult = (page.getCurrentPageNum() - 1) * maxResult;
			criteria.setFirstResult(firstResult).setMaxResults(maxResult);
		}
		return criteria.list();
	}

	

	/** 
	 * 根据离线criteria查询数据集合
	 * @Title: queryByDetacheadCriteria 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param dt
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-4-25 下午05:11:49
	 */ 
	@SuppressWarnings("unchecked")
	public List<M> queryByDetachedCriteria(DetachedCriteria dt) throws Exception{
		return dt.getExecutableCriteria(this.getSession()).list();
	}


	/** 
	 * 根据离线criteria查询数据集合（带分页）
	 * @Title: queryByDetacheadCriteria 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param dt
	 * @return
	 * @return List    返回类型 
	 * @author 白攀
	 * @date 2014-4-25 下午05:11:49
	 */ 
	@SuppressWarnings("unchecked")
	public List<M> queryByDetachedCriteria(DetachedCriteria dt,PageUtil<M> page) throws Exception{
		List<M> list = null;
		if (page != null) {
			int countRecords = countByDetachedCriteria(dt);
			//设置总条数
			page.setCountRecords(countRecords);
			//设置总页数（加判断是by zero）
			if(page.getPageRecords()!= 0){
				page.setCountPages(true,(countRecords%page.getPageRecords()==0)?countRecords/page.getPageRecords():countRecords/page.getPageRecords()+1);
			}
			int maxResult = page.getPageRecords();
			int firstResult = (page.getCurrentPageNum() - 1) * maxResult;
			list = dt.getExecutableCriteria(this.getSession()).setFirstResult(firstResult).setMaxResults(maxResult).list();
		}else {
			list = dt.getExecutableCriteria(this.getSession()).list();
		}
		return list;
	}	
	/* * * * * * * * * * * * * * * * * * * * * * * QUERY END * * * * * * * * * * * * * * * * * * * * * * * * * * * */



	/* * * * * * * * * * * * * * * * * * * * * * * SAVE * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/** 
	 * 实体如果有主键那么就执行更新操作，若没有主键则执行增加操作
	 * @Title: saveOrUpdate 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2014-8-25 上午9:14:56
	 */ 
	public void saveOrUpdate(M entity) throws Exception{
		this.getSession().saveOrUpdate(entity);
	}



	/** 
	 * 根据对象添加
	 * @Title: save 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-8-25 上午9:16:41
	 * @param entity 
	 * @see org.com.sh.dao.BaseDao#save(java.lang.Object) 
	 */ 
	public void save(M entity) throws Exception{
		this.getSession().save(entity);
	}


	/* * * * * * * * * * * * * * * * * * * * * * * SAVE END * * * * * * * * * * * * * * * * * * * * * * * * * * * */



	/* * * * * * * * * * * * * * * * * * * * * * * UPDATE * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/** 
	 * 根据对象修改
	 * @Title: update 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-8-25 上午9:16:52
	 * @param entity 
	 * @see org.com.sh.dao.BaseDao#update(java.lang.Object) 
	 */ 
	public void update(M entity) throws Exception{
		this.getSession().update(entity);
	}


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
	 * @return Integer    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:56:49
	 */
	public int update(Map<String, Object> data, String property, Object value) throws Exception{
		Map<String, Object> conds=new HashMap<String, Object>();
		conds.put(property, value);
		return this.update(data, conds);
	}


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
	 * @return Integer    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:55:12
	 */
	public int update(Map<String, Object> data, Map<String, Object> conds) throws Exception{
		if(data==null || data.isEmpty() || conds==null || conds.isEmpty()){
			return 0;
		}
		StringBuilder sb=new StringBuilder();
		sb.append("update "+entityClass.getName()+" set ");
		//设置更新数据参数
		Set<String> dataKeys=data.keySet();
		int i=0;
		for(String key:dataKeys){
			String k=key.replaceAll("\\.", "")+"d";
			sb.append(key+"=:"+k);
			if(i<dataKeys.size()-1){
				sb.append(",");
			}
			i++;
		}
		sb.append(" where ");
		//设置条件参数
		Set<String> condKeys=conds.keySet();
		i=0;
		for(String key:condKeys){
			String k=key.replaceAll("\\.", "")+"w";
			sb.append(key+"=:"+k);
			if(i<condKeys.size()-1){
				sb.append(" and ");
			}
			i++;
		}
		Query q = this.getSession().createQuery(sb.toString());
		for(String key:dataKeys){
			String k=key.replaceAll("\\.", "")+"d";
			q.setParameter(k, data.get(key));
		}
		for(String key:condKeys){
			String k=key.replaceAll("\\.", "")+"w";
			q.setParameter(k, conds.get(key));
		}
		return q.executeUpdate();
	}
	/* * * * * * * * * * * * * * * * * * * * * * * UPDATE END * * * * * * * * * * * * * * * * * * * * * * * * * * * */



	/* * * * * * * * * * * * * * * * * * * * * * * DELETE * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/** 
	 * 根据对象删除
	 * @Title: delete 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-8-25 上午9:17:08
	 * @param entity 
	 * @see org.com.sh.dao.BaseDao#delete(java.lang.Object) 
	 */ 
	public void delete(M entity) throws Exception{
		this.getSession().delete(entity);
	}


	/**
	 * 删除根据id
	 * @Title: delete 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param id
	 * @return
	 * @return Integer    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:50:05
	 */
	public void delete(Serializable Id) throws Exception{
		M m=this.find(Id);
		if(m!=null){
			this.getSession().delete(m);
		}
	}


	/**
	 * 根据条件删除
	 * @Title: delete 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param conds
	 * @return
	 * @return Integer    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:53:05
	 */
	public int delete(String property,Object value) throws Exception{
		Map<String, Object> conds=new HashMap<String, Object>();
		conds.put(property, value);
		return this.delete(conds);
	}


	/**
	 * 根据条件删除
	 * @Title: delete 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param entity
	 * @param conds
	 * @return
	 * @return Integer    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午8:53:05
	 */
	public int delete(Map<String, Object> conds) throws Exception{
		if(conds==null || conds.isEmpty()){
			return 0;
		}
		StringBuilder sb=new StringBuilder();
		sb.append("delete from "+entityClass.getName()+" where ");
		Set<String> keys=conds.keySet();
		int i=0;
		for(String key:keys){
			String k=key.replaceAll("\\.", "");
			sb.append(key+"=:"+k);
			if(i<keys.size()-1){
				sb.append(" and ");
				i++;
			}
		}
		Query q = this.getSession().createQuery(sb.toString());
		for(String key:keys){
			String k=key.replaceAll("\\.", "");
			q.setParameter(k, conds.get(key));
		} 
		return q.executeUpdate();
	}
	/* * * * * * * * * * * * * * * * * * * * * * * DELETE END * * * * * * * * * * * * * * * * * * * * * * * * * * * */



	/* * * * * * * * * * * * * * * * * * * * * * * OTHER * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	/** 
	 * @Title: isExist 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-3-31 上午10:37:10
	 * @param id
	 * @return 
	 * @see com.dao.BaseDao#isExist(java.io.Serializable) 
	 */ 
	public boolean isExist(Serializable id) throws Exception{
		return this.find(id) == null;
	}


	/** 
	 * @Title: isExist 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-4-11 上午11:56:50
	 * @param property
	 * @param value
	 * @return 
	 * @see com.dao.BaseDao#isExist(java.lang.String, java.lang.String) 
	 */ 
	public boolean isExist(String property, Object value) throws Exception{
		return this.count(property, value) > 0;
	}


	/** 
	 * 执行sql语句
	 * @Title: executeBySql 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-3-31 下午12:00:07
	 * @param sql
	 * @return 
	 * @see com.dao.BaseDao#executeBySql(java.lang.String) 
	 */ 
	public int executeBySql(String sql) throws Exception{
		Query q = this.getSession().createSQLQuery(sql).addEntity(entityClass);
		return q.executeUpdate();
	}


	/** 
	 * 执行sql语句
	 * @Title: executeBySql 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-3-31 下午12:00:07
	 * @param sql
	 * @param params 的写法只有一种；A:"张三，男，24岁"、   没有：B:"Object[]{'name','张三'}，Object[]{'sex','男'}"
	 * @return 
	 * @see com.dao.BaseDao#executeBySql(java.lang.String) 
	 */ 
	public int executeBySql(String sql, Object ...params) throws Exception{
		Query q = this.getSession().createSQLQuery(sql);
		for(int i = 0 ; i < params.length ; i++){
			q.setParameter(i, params[i]);
		}
		return q.executeUpdate();
	}


	/** 
	 * 执行hql语句
	 * @Title: executeByHql 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-3-31 下午12:00:07
	 * @param hql
	 * @return 
	 * @see com.dao.BaseDao#executeByHql(java.lang.String) 
	 */ 
	public int executeByHql(String hql) throws Exception{
		Query q = this.getSession().createQuery(hql);
		return q.executeUpdate();
	}


	/** 
	 * 执行hql语句
	 * @Title: executeByHql 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author 白攀
	 * @date 2014-3-31 下午2:19:00
	 * @param hql
	 * @param params 的写法只有一种；A:"张三，男，24岁"、   没有：B:"Object[]{'name','张三'}，Object[]{'sex','男'}"
	 * @return 
	 * @see com.dao.BaseDao#executeByHql(java.lang.String, java.lang.String[]) 
	 */ 
	public int executeByHql(String hql, Object... params) throws Exception{
		Query q = this.getSession().createQuery(hql);
		for(int i = 0 ; i < params.length; i++){
			q.setParameter(i,params[i]);
		}
		return q.executeUpdate();
	}


	/** 
	 * 执行hql语句
	 * @Title: executeByHql 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param hql
	 * @param params:“?”对应的值列表
	 * @return
	 * @return Integer    返回类型 
	 * @author 白攀
	 * @date 2014-5-27 上午9:40:23
	 */ 
	public int executeByHql(String hql, List<Object> params) throws Exception{
		Query q = this.getSession().createQuery(hql);
		for(int i = 0 ; i < params.size(); i++){
			q.setParameter(i,params.get(i));
		}
		return q.executeUpdate();
	}


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
	public int getSum(String field) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.sum(field);
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getSum(String field,String property,Object value) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.sum(field);
		dt.add(Restrictions.eq(property, value));
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getSum(String field,Map<String, Object> conds) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.sum(field);
		dt.add(Restrictions.allEq(conds));
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getSum(String field,DetachedCriteria dt) throws Exception{
		AggregateProjection projection = Projections.sum(field);
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getMax(String field) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.max(field);
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getMax(String field,String property,Object value) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.max(field);
		dt.add(Restrictions.eq(property, value));
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getMax(String field,Map<String, Object> conds) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.max(field);
		dt.add(Restrictions.allEq(conds));
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getMax(String field,DetachedCriteria dt) throws Exception{
		AggregateProjection projection = Projections.max(field);
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getMin(String field) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.min(field);
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getMin(String field,String property,Object value) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.min(field);
		dt.add(Restrictions.eq(property, value));
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getMin(String field,Map<String, Object> conds) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.min(field);
		dt.add(Restrictions.allEq(conds));
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getMin(String field,DetachedCriteria dt) throws Exception{
		AggregateProjection projection = Projections.min(field);
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getAvg(String field) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.avg(field);
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getAvg(String field,String property,Object value) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.avg(field);
		dt.add(Restrictions.eq(property, value));
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getAvg(String field,Map<String, Object> conds) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.avg(field);
		dt.add(Restrictions.allEq(conds));
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getAvg(String field,DetachedCriteria dt) throws Exception{
		AggregateProjection projection = Projections.avg(field);
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getCountDistinct(String field) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.countDistinct(field);
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getCountDistinct(String field,String property,Object value) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.countDistinct(field);
		dt.add(Restrictions.eq(property, value));
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getCountDistinct(String field,Map<String, Object> conds) throws Exception{
		DetachedCriteria dt = DetachedCriteria.forClass(entityClass);
		AggregateProjection projection = Projections.countDistinct(field);
		dt.add(Restrictions.allEq(conds));
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


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
	public int getCountDistinct(String field,DetachedCriteria dt) throws Exception{
		AggregateProjection projection = Projections.countDistinct(field);
		dt.setProjection(projection);
		Number number = (Number)dt.getExecutableCriteria(this.getSession()).uniqueResult();
		return number.intValue();
	}


	/**
	 * 获取当前的查询器
	 * @Title: getCriteria 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return
	 * @return Criteria    返回类型 
	 * @author 白攀
	 * @date 2014-3-25 上午11:38:40
	 */
	public Criteria getCriteria(){
		return this.getSession().createCriteria(entityClass);
	}


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
	public DetachedCriteria fillDtCriteria(List<String> properties,List<Object> values) throws Exception{
		DetachedCriteria dt = null;
		dt = DetachedCriteria.forClass(entityClass);
		if (values!=null&&values.size()>0) {
			for (int i = 0; i < properties.size(); i++) {
				//这是between，like,in,gt,lt等
				if (values.get(i) instanceof Object[]) {
					//eg:properties.add("address");
					//   values.add(new Object[]{"%北京%","like"});
					Object[] arg=(Object[])values.get(i);
					if("between".equalsIgnoreCase(arg[arg.length-1].toString())) {//between
						dt.add(Restrictions.between(properties.get(i), arg[0], arg[1]));
					}else if("like".equalsIgnoreCase(arg[arg.length-1].toString())) {//like
						dt.add(Restrictions.like(properties.get(i), arg[0].toString(),MatchMode.ANYWHERE));
					}else if("in".equalsIgnoreCase(arg[arg.length-1].toString())){//in
						dt.add(Restrictions.in(properties.get(i), (Object[])arg[0]));
					}else if("gt".equalsIgnoreCase(arg[arg.length-1].toString())){//gt大于
						dt.add(Restrictions.gt(properties.get(i), arg[0]));
					}else if("lt".equalsIgnoreCase(arg[arg.length-1].toString())){//lt小于
						dt.add(Restrictions.lt(properties.get(i), arg[0]));
					}else if("class".equalsIgnoreCase(arg[arg.length-1].toString())){//对象等于
						dt.add(Restrictions.eq(properties.get(i), arg[0]));
					}else if("ne".equalsIgnoreCase(arg[arg.length-1].toString())){//<>
						dt.add(Restrictions.ne(properties.get(i), arg[0]));
					}else if("as".equalsIgnoreCase(arg[arg.length-1].toString())){//createAlias
						dt.createAlias(properties.get(i), (String) arg[0], JoinType.INNER_JOIN);
					}
				}else {//默认,order,isnull等
					//eg:properties.add("order");
					//   values.add(Order.desc(id));
					if ("order".equalsIgnoreCase(properties.get(i))) {
						dt.addOrder((Order)values.get(i));
					}else if("isnull".equalsIgnoreCase(properties.get(i))){
						dt.add(Restrictions.isNull((String) values.get(i)));
					}else if("isnotnull".equalsIgnoreCase(properties.get(i))){
						dt.add(Restrictions.isNotNull((String) values.get(i)));
					}else {
						dt.add(Restrictions.eq(properties.get(i), values.get(i)));
					}
				}			
			}
		}
		return dt;
	}
	/* * * * * * * * * * * * * * * * * * * * * * * OTHER END * * * * * * * * * * * * * * * * * * * * * * * * * * * */

}
