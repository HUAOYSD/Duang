package org.duang.service.impl;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.annotation.ServiceLog;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.BillInvestDao;
import org.duang.entity.BillInvest;
import org.duang.service.BillInvestService;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

/**   
 * 理财资金记录业务接口实现类
 * @ClassName:  BillInvestServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月23日 下午2:19:26      
 */  
@ServiceLog(ModelName="理财资金记录")
@Service(value="billinvestserviceimpl")
public class BillInvestServiceImpl implements BillInvestService{

	private BillInvestDao dao;

	@Resource(name="billinvestdaoimpl")
	public void setDao(BillInvestDao dao) {
		this.dao = dao;
	}

	public BillInvestServiceImpl(){
		LoggerUtils.info("注入BillInvestServiceImpl服务层", this.getClass());
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
	public List<BillInvest> queryAllEntity(Order order) throws Exception {
		return dao.queryAllEntity(order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<BillInvest> queryAllEntity(PageUtil<BillInvest> page, Order order) throws Exception{
		return dao.queryAllEntity(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<BillInvest> queryEntity(String field, Object value, PageUtil<BillInvest> page, Order order) throws Exception{
		return dao.queryEntity(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<BillInvest> queryEntity(List<String> properties, List<Object> values, PageUtil<BillInvest> page) throws Exception{
		return dao.queryEntity(properties, values, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public BillInvest findById(Serializable id) throws Exception{
		return dao.findById(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(BillInvest t) throws Exception{
		return dao.saveEntity(t);
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(BillInvest t) throws Exception{
		return dao.updateEntity(t);
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
	public BillInvest findEntity(String property, Object value) throws Exception{
		return dao.findEntity(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public BillInvest findEntity(Map<String, Object> params) throws Exception{
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
	public List<BillInvest> queryByHQL(String hql,String counthql, PageUtil<BillInvest> page, Object... params) throws Exception{
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
	public List<BillInvest> queryBySQL(String sql,String countsql, PageUtil<BillInvest> page, boolean convert, Object... params) throws Exception{
		return dao.queryBySQL(sql, countsql, page, convert, params);
	}

	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(BillInvest t) throws Exception {
		return dao.deleteEntity(t);
	}
	

	/**   
	 * 查询累计投资次数与金额
	 * @Title: findCostInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberid
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月12日 下午2:48:36
	 * @return: List<BillInvest>      
	 * @throws   
	 */  
	public List<BillInvest> findCostInfo(String memberid) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT SUM(MONEY) AS TM, COUNT(MONEY) AS TN FROM BILL_INVEST WHERE MEMBER_INFO = '"+memberid+"' AND USE_TYPE = 3 AND STATUS = 2");
		List<BillInvest> list = dao.queryBySQL(sb.toString(), null, null, false);
		return list;
	}
	
	
	/**   
	 * 获取和我差不多投资额度的会员
	 * 投资额度和该会员投资额度相差不过5万的前20人
	 * @Title: queryFairlysMemberCostInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberid
	 * @param: @param tm
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月12日 下午2:51:36
	 * @return: List<BillInvest>      
	 * @throws   
	 */  
	public List<BillInvest> queryFairlysMemberCostInfo(String memberid, double tm) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		sb.append("COUNT(MONEY) AS TN,");
		sb.append("SUM(MONEY) AS TM,");
		sb.append("BILL_INVEST.MEMBER_INFO,");
		sb.append("MEMBER_INFO.NICKNAME,");
		sb.append("MEMBER_INFO.REAL_NAME ");
		sb.append("FROM BILL_INVEST ");
		sb.append("INNER JOIN MEMBER_INFO ON MEMBER_INFO.ID = BILL_INVEST.MEMBER_INFO ");
		sb.append("WHERE MEMBER_INFO <> '"+memberid+"' ");
		sb.append("AND USE_TYPE = 3 AND STATUS = 2 ");
		sb.append("GROUP BY MEMBER_INFO ");
		sb.append("HAVING TM BETWEEN "+(tm-50000)+" AND "+(tm+50000)+" ");
		sb.append("LIMIT 0, 20 ");
		List<BillInvest> list = dao.queryBySQL(sb.toString(), null, null, false);
		return list;
	}
}
