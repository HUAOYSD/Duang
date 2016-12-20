package org.duang.dao.impl; 

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.duang.common.logger.LoggerUtils;
import org.duang.dao.LoanMemberRepayDateDao;
import org.duang.dao.base.BaseDao;
import org.duang.entity.LoanList;
import org.duang.entity.LoanMemberRepayDate;
import org.duang.entity.Scale;
import org.duang.enums.loan.RepayState;
import org.duang.enums.loan.RepayStatus;
import org.duang.enums.scale.SingleOrSet;
import org.duang.util.DataUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;


/**
 * 赎回日期和还款日期
 * @ClassName:  LoanMemberRepayDateDaoImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年12月15日 下午5:47:34
 */
@Repository("loanmemberrepaydatedaoimpl")
public class LoanMemberRepayDateDaoImpl extends BaseDao<LoanMemberRepayDate> implements LoanMemberRepayDateDao{


	public LoanMemberRepayDateDaoImpl(){
		LoggerUtils.info("注入LoanMemberRepayDateDaoImpl层", this.getClass());
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
	public List<LoanMemberRepayDate> queryAllEntity(Order order) throws Exception {
		return super.queryByCriteria(super.getCriteria(), order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<LoanMemberRepayDate> queryAllEntity(PageUtil<LoanMemberRepayDate> page, Order order) throws Exception{
		return super.queryAll(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<LoanMemberRepayDate> queryEntity(String field, Object value, PageUtil<LoanMemberRepayDate> page, Order order) throws Exception{
		return super.query(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<LoanMemberRepayDate> queryEntity(List<String> properties, List<Object> values, PageUtil<LoanMemberRepayDate> page) throws Exception{
		DetachedCriteria detachedCriteria = super.fillDtCriteria(properties, values);
		return super.queryByDetachedCriteria(detachedCriteria, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public LoanMemberRepayDate findById(Serializable id) throws Exception{
		return super.find(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(LoanMemberRepayDate t) throws Exception{
		super.save(t);
		return true;
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(LoanMemberRepayDate t) throws Exception{
		super.update(t);
		return true;
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(LoanMemberRepayDate t) throws Exception{
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
		return super.executeBySql(sql) >= 1;
	}


	/**
	 * 根据sql语句执行sql代码
	 * @param sql  sql语句
	 * @return     是否执行成功
	 */
	public boolean executeSql(String sql,Object... params) throws Exception{
		return super.executeBySql(sql,params) >= 1;
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
	public LoanMemberRepayDate findEntity(String property, Object value) throws Exception{
		return super.find(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public LoanMemberRepayDate findEntity(Map<String, Object> params) throws Exception{
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
	public List<LoanMemberRepayDate> queryByHQL(String hql, String countHql, PageUtil<LoanMemberRepayDate> page, Object... params) throws Exception{
		return super.queryByHQL(hql, countHql, page, params);
	}


	/**
	 * 根据Sql语句查询
	 * @param sql   sql语句
	 * @param page  是否分页          null表示不分页
	 * @param params 写法有俩；A:"张三，男，24岁"、   B:"Object[]{'name','张三'}，Object[]{'sex','男'}"
	 * @return
	 * @throws Exception
	 */
	public List<LoanMemberRepayDate> queryBySQL(String sql, String countSql, PageUtil<LoanMemberRepayDate> page, boolean convert, Object... params) throws Exception{
		return super.queryBySQL(sql, countSql, convert, page, params);
	}

	/**
	 * 添加日期信息（借贷的还款日期）
	 * <p>Title: addRepayDate</p>   
	 * <p>Description: </p>  
	 * @author LiYonghui
	 * @date 2016年12月16日 上午9:36:47
	 * @param scale
	 * @param loanList
	 * @throws Exception   
	 * @see org.duang.dao.LoanMemberRepayDateDao#addRepayDate(org.duang.entity.Scale, org.duang.entity.LoanList)
	 */
	@Override
	public void addRepayLoanDate(Scale scale, LoanList loanList) throws Exception {
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.DATE, -1);
    	if(scale.getSingleOrSet().equals(SingleOrSet.S2.getVal())){
			//计算还款日
			for(int i=0;i<loanList.getDays()/30;i++){
    		    c.add(Calendar.MONTH, +1);
    		    LoanMemberRepayDate loanMemberRepayDate = new LoanMemberRepayDate(DataUtils.randomUUID(), 
    		    		loanList.getId(), c.getTime(), i, RepayState.STA0.getVal(), RepayStatus.STU1.getVal());
		    	saveEntity(loanMemberRepayDate);
		    	LoggerUtils.info("\t\n-------------------LoanListId:"+loanList.getId()+"\t标名称："+scale.getName()
		    		+ "\t还款日期:"+c.getTime(), this.getClass());
			}
		    
		}else if(scale.getSingleOrSet().equals(SingleOrSet.S1.getVal())){
		    c.add(Calendar.DATE, +loanList.getDays());
		    LoanMemberRepayDate loanMemberRepayDate = new LoanMemberRepayDate(DataUtils.randomUUID(), 
		    		loanList.getId(), c.getTime(), 0, RepayState.STA0.getVal(), RepayStatus.STU1.getVal());
	    	saveEntity(loanMemberRepayDate);
	    	LoggerUtils.info("\t\n-------------------LoanListId:"+loanList.getId()+"\t标名称："+scale.getName()
	    		+ "\t还款日期:"+c.getTime(), this.getClass());
		}
	}

}

