package org.duang.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.annotation.ServiceLog;
import org.duang.common.logger.LoggerUtils;
import org.duang.config.Basic;
import org.duang.dao.LoanListDao;
import org.duang.dao.ScaleDao;
import org.duang.dao.ScaleLoanListDao;
import org.duang.dao.StockDao;
import org.duang.entity.LoanList;
import org.duang.entity.Scale;
import org.duang.entity.ScaleLoanList;
import org.duang.entity.Stock;
import org.duang.enums.If;
import org.duang.enums.stock.Status;
import org.duang.service.ScaleLoanListService;
import org.duang.util.DataUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

/**   
 * 借贷记录与理财标关联业务接口实现类
 * @ClassName:  ScaleLoanListServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年8月17日 下午3:37:07      
 */  
@ServiceLog(ModelName="借贷记录与理财标关联管理")
@Service(value="scaleloanlistserviceimpl")
public class ScaleLoanListServiceImpl implements ScaleLoanListService{

	private ScaleLoanListDao dao;
	private LoanListDao loanListDao;
	private StockDao stockDao;
	private ScaleDao scaleDao;
	@Resource
	public void setDao(ScaleLoanListDao dao) {
		this.dao = dao;
	}
	@Resource
	public void setLoanListDao(LoanListDao loanListDao) {
		this.loanListDao = loanListDao;
	}
	@Resource
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Resource
	public void setScaleDao(ScaleDao scaleDao) {
		this.scaleDao = scaleDao;
	}
	
	public ScaleLoanListServiceImpl(){
		LoggerUtils.info("注入ScaleLoanListServiceImpl服务层", this.getClass());
	}

	/**   
	 * 匹配借贷记录到理财标
	 * @Title: matchScaleLoanRecords   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param scaleId
	 * @param: @param loanListIds
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年8月17日 下午3:14:19
	 * @return: boolean      
	 * @throws   
	 */  
	public boolean matchScaleLoanRecords(String scaleId, String[] loanListIds) throws Exception{
		if (loanListIds == null || loanListIds.length == 0) {
			return false;
		}
		double money = 0;
		//1、获取所有借贷记录
		String hql = " FROM LoanList ";
		for (int j = 0; j < loanListIds.length; j++) {
			if (j == 0) {
				hql += " WHERE id = '" + loanListIds[j] + "'";
			}else {
				hql += " OR id = '" + loanListIds[j] + "'";
			}
		}
		List<LoanList> loanLists = loanListDao.queryByHQL(hql,null, null);

		//2、找到理财标对象scale
		Scale scale = scaleDao.findById(scaleId);
		if (scale == null) {
			return false;
		}
		
		//3、放入表scale_loan_list
		List<ScaleLoanList> scaleLoanLists = new LinkedList<ScaleLoanList>();
		for (LoanList loanList : loanLists) {
			money += loanList.getMoney();//这个就是申请的借款金额
			scaleLoanLists.add(new ScaleLoanList(DataUtils.randomUUID(), scale, loanList));
		}
		for (ScaleLoanList scaleLoanList : scaleLoanLists) {
			dao.saveEntity(scaleLoanList);
		}
		
		//4、计算理财标总额度
		scale.setTotalMoney(money);
		scale.setStatus(org.duang.enums.scale.Status.S1.getVal());
		scaleDao.updateEntity(scale);

		//5、分配库存
		List<Stock> stocks = new ArrayList<Stock>();
		for (LoanList loanList : loanLists) {
			if (loanList.getMoney()!=0) {
				if (loanList.getMoney() % Basic.INTERVAL_MONEY == 0) {
					double count = loanList.getMoney() / Basic.INTERVAL_MONEY;
					for (double i = 1; i <= count; i++) {
						stocks.add(new Stock(DataUtils.randomUUID(), scale, loanList, null, null, Basic.INTERVAL_MONEY, 0, new Date(), null, 0, Status.S0.getVal(), If.If0.getVal()));
					}
				}else {
					throw new Exception("借贷金额不为基础金额"+Basic.INTERVAL_MONEY+"元倍数");
				}
			}
		}

		//6、保存库存
		for (Stock stock : stocks) {
			stockDao.saveEntity(stock);
		}

		//7、改变借贷记录的状态
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("isSell", org.duang.enums.loan.Scale.S2.getVal());
		for (String id : loanListIds) {
			loanListDao.updateEntity(datas, "id", id);
		}
		return true;
		
		
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
	public List<ScaleLoanList> queryAllEntity(Order order) throws Exception {
		return dao.queryAllEntity(order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<ScaleLoanList> queryAllEntity(PageUtil<ScaleLoanList> page, Order order) throws Exception{
		return dao.queryAllEntity(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<ScaleLoanList> queryEntity(String field, Object value, PageUtil<ScaleLoanList> page, Order order) throws Exception{
		return dao.queryEntity(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<ScaleLoanList> queryEntity(List<String> properties, List<Object> values, PageUtil<ScaleLoanList> page) throws Exception{
		return dao.queryEntity(properties, values, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public ScaleLoanList findById(Serializable id) throws Exception{
		return dao.findById(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(ScaleLoanList t) throws Exception{
		return dao.saveEntity(t);
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(ScaleLoanList t) throws Exception{
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
	public ScaleLoanList findEntity(String property, Object value) throws Exception{
		return dao.findEntity(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public ScaleLoanList findEntity(Map<String, Object> params) throws Exception{
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
	public List<ScaleLoanList> queryByHQL(String hql,String counthql, PageUtil<ScaleLoanList> page, Object... params) throws Exception{
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
	public List<ScaleLoanList> queryBySQL(String sql,String countsql, PageUtil<ScaleLoanList> page, boolean convert, Object... params) throws Exception{
		return dao.queryBySQL(sql, countsql, page, convert, params);
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(ScaleLoanList t) throws Exception {
		return dao.deleteEntity(t);
	}
}
