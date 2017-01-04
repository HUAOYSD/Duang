package org.duang.dao.impl; 

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.common.CondsUtils;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.LoanListRateDao;
import org.duang.dao.LoanMemberRepayDateDao;
import org.duang.dao.base.BaseDao;
import org.duang.entity.LoanList;
import org.duang.entity.LoanMemberRepayDate;
import org.duang.entity.Scale;
import org.duang.enums.If;
import org.duang.enums.loan.BackStyle;
import org.duang.enums.loan.RepayState;
import org.duang.enums.loan.RepayStatus;
import org.duang.enums.scale.SingleOrSet;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
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

	private LoanListRateDao loanListRateDao;

	@Resource(name="loanlistratedaoimpl")
	public void setLoanListRateDao(LoanListRateDao loanListRateDao) {
		this.loanListRateDao = loanListRateDao;
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
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loanListId", t.getLoanListId());
		map.put("repayDate", DateUtils.getDate(DateUtils.date2Str(t.getRepayDate(), "yyyy-MM-dd"), "yyyy-MM-dd"));
		if(find(map)==null){
			super.save(t);
		}
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
    		    		loanList.getId(), c.getTime(), i, RepayState.STA0.getVal(), RepayStatus.STU1.getVal(), DataUtils.str2double(String.valueOf(loanList.getReturnMoney()/loanList.getDays()*30), 6) );
		    	saveEntity(loanMemberRepayDate);
		    	LoggerUtils.info("\t\n-------------------LoanListId:"+loanList.getId()+"\t标名称："+scale.getName()
		    		+ "\t还款日期:"+c.getTime(), this.getClass());
			}
		    
		}else if(scale.getSingleOrSet().equals(SingleOrSet.S1.getVal())){
		    c.add(Calendar.DATE, +loanList.getDays());
		    LoanMemberRepayDate loanMemberRepayDate = new LoanMemberRepayDate(DataUtils.randomUUID(), 
		    		loanList.getId(), c.getTime(), 0, RepayState.STA0.getVal(), RepayStatus.STU1.getVal(), DataUtils.str2double(String.valueOf(loanList.getReturnMoney()), 6) );
	    	saveEntity(loanMemberRepayDate);
	    	LoggerUtils.info("\t\n-------------------LoanListId:"+loanList.getId()+"\t标名称："+scale.getName()
	    		+ "\t还款日期:"+c.getTime(), this.getClass());
		}
	}

	
	/**
	 * 获取本次的还款日期
	 * <p>Title: getThisRepayDate</p>   
	 * <p>Description: </p>  
	 * @author LiYonghui
	 * @date 2016年12月16日 上午10:36:09
	 * @return
	 * @throws Exception   
	 * @see org.duang..LoanMemberRepayDateDao#getThisRepayDate()
	 */
	@Override
	public Map<String, Object> updateLoanMemberRepayDateByRepay(double sum, LoanList loanList, String memberInfoId) throws Exception {
		boolean success = false;
		double overSum = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		CondsUtils condsUtils = new CondsUtils();
		condsUtils.addProperties(true, "loanListId","status","state","order");
		condsUtils.addValues(true, loanList.getId(),RepayStatus.STU1.getVal(),RepayState.STA0.getVal(),Order.asc("repayIndex"));
		List<LoanMemberRepayDate> loanMemberRepayDates = queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
		int count = count("loanListId", loanList.getId());
		boolean isBreak = false;
		//定额本息
		if(count > 1){
			for(int i=0;i<loanMemberRepayDates.size();i++){
				LoanMemberRepayDate loanMemberRepayDate = loanMemberRepayDates.get(i);
				loanMemberRepayDate = updateLoanMemberRepayDate(loanMemberRepayDate,loanList,BackStyle.B1.getVal());
				success = updateEntity(loanMemberRepayDate);
				overSum +=loanMemberRepayDate.getOverSum();
				//还款日
				Date repayDate = loanMemberRepayDate.getRepayDate();
				//如果系统日期在还款日以后，就让跳出，说明这个就是下期的还款日了
				if(!(repayDate.getTime() < DateUtils.getDate(DateUtils.date2Str(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd").getTime())){
					isBreak = true;
				}
				if(isBreak){
					break;
				}
			}
		}else if(count ==1){ //到期一次性还清
			LoanMemberRepayDate loanMemberRepayDate = loanMemberRepayDates.get(0);
			loanMemberRepayDate = updateLoanMemberRepayDate(loanMemberRepayDate,loanList,BackStyle.B2.getVal());
			success = updateEntity(loanMemberRepayDate);
			overSum +=loanMemberRepayDate.getOverSum();
		}
		if(success){
			map.put("overSum", overSum);
			map.put("success", success);
		}
		return map;
	}
	
	/**
	 * 修改实体参数
	 * @param loanMemberRepayDate
	 * @param loanList
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private LoanMemberRepayDate updateLoanMemberRepayDate(LoanMemberRepayDate loanMemberRepayDate,LoanList loanList,int type) throws Exception{
		//还款日
		Date repayDate = loanMemberRepayDate.getRepayDate();
		//改为已还款
		loanMemberRepayDate.setState(RepayState.STA1.getVal());
		//还款日期
		loanMemberRepayDate.setReRepayDate(DateUtils.str2Date(DateUtils.getCurrentDate("yyyy-MM-dd"),"yyyy-MM-dd"));
		//逾期
		if(repayDate.getTime() < DateUtils.getDate(DateUtils.date2Str(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd").getTime()){
			//计算逾期天数
			int days = getOverDays(loanMemberRepayDate,type);
			loanMemberRepayDate.setIsOver(If.If1.getVal());
			loanMemberRepayDate.setOverDays(Integer.parseInt(String.valueOf(days)));
			//剩余应还金额
			double exprSum = loanList.getReturnMoney()-loanList.getYetReturnMoney();
			double overRate = loanListRateDao.getLoanListRate().getOverRate();
			loanMemberRepayDate.setOverSum(DataUtils.str2double(String.valueOf(exprSum*overRate*days), 6));
			loanMemberRepayDate.setRepayRealSum(loanMemberRepayDate.getRepaySum()+loanMemberRepayDate.getOverSum());
		}else{
			//本期金额
			loanMemberRepayDate.setIsOver(If.If0.getVal());
			loanMemberRepayDate.setRepayRealSum(loanMemberRepayDate.getRepaySum());
		}
		return loanMemberRepayDate;
	}
	
	/**
	 * 预期天数,计算天数，
	 * @param loanMemberRepayDate
	 * @param type   还款方式   如果是1，则表示按月还款，如果>30就按30天算，多余的放在下期天数中，   如果是2，则表示一次性还清
	 * @return
	 */
	private int getOverDays(LoanMemberRepayDate loanMemberRepayDate,int type){
		//计算逾期天数
		long days = 0;
		//如果逾期还款，即在下一期还款之前还款。
		if(loanMemberRepayDate.getReRepayDate() != null){
			//逾期天数
			days = (loanMemberRepayDate.getReRepayDate().getTime()-loanMemberRepayDate.getRepayDate().getTime())/1000/(24*3600);
		}else{
			//否则是系统时间与应还日期的时间差
			days = (new Date().getTime()-loanMemberRepayDate.getRepayDate().getTime())/1000/(24*3600);
		}
		if(type == BackStyle.B1.getVal()){
			return Integer.parseInt(String.valueOf(days))>30?30:Integer.parseInt(String.valueOf(days));
		}else{
			return Integer.parseInt(String.valueOf(days));
		}
	}
	
}

