package org.duang.service.impl;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.annotation.ServiceLog;
import org.duang.common.CondsUtils;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.LoanListRateDao;
import org.duang.dao.LoanMemberRepayDateDao;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanList;
import org.duang.entity.LoanMemberRepayDate;
import org.duang.enums.loan.RepayState;
import org.duang.enums.loan.RepayStatus;
import org.duang.service.LoanMemberRepayDateService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

/**
 * 还款日期
 * @ClassName:  LoanMemberRepayDateServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年12月15日 下午5:52:35
 */
@ServiceLog(ModelName="还款日期")
@Service(value="loanmemberrepaydateserviceimpl")
public class LoanMemberRepayDateServiceImpl implements LoanMemberRepayDateService{

	private LoanMemberRepayDateDao dao;

	@Resource(name="loanmemberrepaydatedaoimpl")
	public void setDao(LoanMemberRepayDateDao dao) {
		this.dao = dao;
	}
	
	private LoanListRateDao loanListRateDao;

	@Resource(name="loanlistratedaoimpl")
	public void setLoanListRateDao(LoanListRateDao loanListRateDao) {
		this.loanListRateDao = loanListRateDao;
	}
	
	public LoanMemberRepayDateServiceImpl(){
		LoggerUtils.info("注入LoanMemberRepayDateServiceImpl服务层", this.getClass());
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
	public List<LoanMemberRepayDate> queryAllEntity(Order order) throws Exception {
		return dao.queryAllEntity(order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<LoanMemberRepayDate> queryAllEntity(PageUtil<LoanMemberRepayDate> page, Order order) throws Exception{
		return dao.queryAllEntity(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<LoanMemberRepayDate> queryEntity(String field, Object value, PageUtil<LoanMemberRepayDate> page, Order order) throws Exception{
		return dao.queryEntity(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<LoanMemberRepayDate> queryEntity(List<String> properties, List<Object> values, PageUtil<LoanMemberRepayDate> page) throws Exception{
		return dao.queryEntity(properties, values, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public LoanMemberRepayDate findById(Serializable id) throws Exception{
		return dao.findById(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(LoanMemberRepayDate t) throws Exception{
		return dao.saveEntity(t);
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(LoanMemberRepayDate t) throws Exception{
		return dao.updateEntity(t);
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(InvestMember t) throws Exception{
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
	public LoanMemberRepayDate findEntity(String property, Object value) throws Exception{
		return dao.findEntity(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public LoanMemberRepayDate findEntity(Map<String, Object> params) throws Exception{
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
	public List<LoanMemberRepayDate> queryByHQL(String hql,String counthql, PageUtil<LoanMemberRepayDate> page, Object... params) throws Exception{
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
	public List<LoanMemberRepayDate> queryBySQL(String sql,String countsql, PageUtil<LoanMemberRepayDate> page, boolean convert, Object... params) throws Exception{
		return dao.queryBySQL(sql, countsql, page, convert, params);
	}

	@Override
	public boolean deleteEntity(LoanMemberRepayDate t) throws Exception {
		return dao.deleteEntity(t);
	}
	
	/**
	 * 获取本次的还款日期
	 * <p>Title: getThisRepayDate</p>   
	 * <p>Description: </p>  
	 * @author LiYonghui
	 * @date 2016年12月16日 上午10:36:09
	 * @return
	 * @throws Exception   
	 * @see org.duang.service.LoanMemberRepayDateService#getThisRepayDate()
	 */
	@Override
	public Map<String, Object> getThisLoanRepayDate(LoanList loanList) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//上期账单还款情况信息
		String  b_msg="上期无账单";
		String msg="本期无账单";
		//最后还款日期
		Date date = null;
		//逾期金额
		double overDueSum = 0;
		//本期还款金额
		double sum = 0;
		CondsUtils condsUtils = new CondsUtils();
		condsUtils.addProperties(true, "loanListId","status","state","order");
		condsUtils.addValues(true, loanList.getId(),RepayStatus.STU1.getVal(),RepayState.STA0.getVal(),Order.asc("repayIndex"));
		List<LoanMemberRepayDate> loanMemberRepayDates = queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
		if(DataUtils.notEmpty(loanMemberRepayDates)){
			for(int i=0;i<loanMemberRepayDates.size();i++){
				LoanMemberRepayDate loanMemberRepayDate = loanMemberRepayDates.get(i);
				//还款日
				Date repayDate = loanMemberRepayDate.getRepayDate();
				//逾期
				if(repayDate.getTime() < DateUtils.getDate(DateUtils.date2Str(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd").getTime()){
					b_msg="上期账单已逾期";
					//剩余应还金额
					double exprSum = loanList.getReturnMoney()-loanList.getYetReturnMoney();
					//逾期金额
					overDueSum += getOverDueSum(loanMemberRepayDate,exprSum);
					sum+=loanMemberRepayDate.getRepaySum();
				}else {
					date = repayDate;
					sum+=loanMemberRepayDate.getRepaySum();
					break;
				}
			}
		}
		//如果date == null 说明无贷款。
		map.put("date", date);
		//上期还款状态信息
		map.put("b_msg", b_msg);
		//本期还款状态信息
		map.put("msg", msg);
		//逾期金额
		map.put("overDueSum", overDueSum);
		//本应还金额
		map.put("sum", sum);
		return map;
	}
	
	private double getOverDueSum(LoanMemberRepayDate loanMemberRepayDate,double exprSum) throws Exception{
		//计算逾期天数
		long days = 0;
		//如果逾期还款，即在下一期还款之前还款。
		if(loanMemberRepayDate.getReRepayDate() != null){
			//逾期天数
			days = (loanMemberRepayDate.getReRepayDate().getTime()-loanMemberRepayDate.getRepayDate().getTime())/1000/(24*3600);
		}else{
			//否则是系统时间与应还日期的时间差
			days = (new Date().getTime() - loanMemberRepayDate.getRepayDate().getTime())/1000/(24*3600);
		}
		days = days>30?30:days;
		double overRate = loanListRateDao.getLoanListRate().getOverRate();
		return DataUtils.str2double(String.valueOf(exprSum*overRate*days), 6);
	}
	
}
