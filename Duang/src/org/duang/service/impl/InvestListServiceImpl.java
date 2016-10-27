package org.duang.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.annotation.ServiceLog;
import org.duang.common.CondsUtils;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.InvestListDao;
import org.duang.dao.ScaleDao;
import org.duang.dao.StockDao;
import org.duang.entity.InvestList;
import org.duang.entity.Scale;
import org.duang.entity.Stock;
import org.duang.enums.stock.Status;
import org.duang.service.InvestListService;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

/**   
 * 理财记录业务接口实现类
 * @ClassName:  InvestListServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年8月10日 下午5:19:26      
 */  
@ServiceLog(ModelName="理财记录管理")
@Service(value="investlistserviceimpl")
public class InvestListServiceImpl implements InvestListService{

	private InvestListDao dao;

	@Resource
	public void setDao(InvestListDao dao) {
		this.dao = dao;
	}

	public InvestListServiceImpl(){
		LoggerUtils.info("注入InvestListServiceImpl服务层", this.getClass());
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
	public List<InvestList> queryAllEntity(Order order) throws Exception {
		return dao.queryAllEntity(order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<InvestList> queryAllEntity(PageUtil<InvestList> page, Order order) throws Exception{
		return dao.queryAllEntity(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<InvestList> queryEntity(String field, Object value, PageUtil<InvestList> page, Order order) throws Exception{
		return dao.queryEntity(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<InvestList> queryEntity(List<String> properties, List<Object> values, PageUtil<InvestList> page) throws Exception{
		return dao.queryEntity(properties, values, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public InvestList findById(Serializable id) throws Exception{
		return dao.findById(id);
	}


	private StockDao stockDao;
	private ScaleDao scaleDao;
	@Resource
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Resource
	public void setScaleDao(ScaleDao scaleDao) {
		this.scaleDao = scaleDao;
	}

	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public synchronized boolean saveEntity(InvestList t) throws Exception{
		if (t!=null) {
			//1、匹配库存
			CondsUtils condsUtils = new CondsUtils();
			condsUtils.addProperties(true, "investList", "scale.id");
			condsUtils.addProperties(true, null, t.getScale().getId());
			List<Stock> stocks = stockDao.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);//获取该理财标库存中还未分配理财标的记录
			if (stocks!=null) {
				//将集合打乱顺序，随机为该投资人分配借贷人
				Collections.shuffle(stocks);
				List<Stock> mystock = new ArrayList<Stock>();//存放该理财记录的库存记录的集合
				double tempMoney = 0;
				//......
				for (Stock stock : stocks) {
					if (tempMoney + stock.getMoney() < t.getMoney()) {
						stock.setFetch(stock.getMoney());
						stock.setFetchTime(new Date());
						stock.setDifference(0);
						stock.setStatus(Status.S2.getVal());
						stock.setInvestList(t);
						mystock.add(stock);
					}else {
						break;
					}
				}
				//2、更改分配到的库存的状态
				for (Stock stock : mystock) {
					stockDao.updateEntity(stock);
				}
				//3、更正理财标
				Scale scale = scaleDao.findById(t.getScale().getId());
				scale.setResidueMoney(scale.getResidueMoney() - t.getMoney());
				scale.setYetMoney(scale.getYetMoney() + t.getMoney());
				//scale.setStatus(0);
				//.......
				scaleDao.updateEntity(scale);
				//4、产生理财订单
				//4.1、先获取到此人，最后一次billinvest记录，该条记录含有此人最新的余额、总资产数据，新记录的这俩数据，通过这个数据做加减法即可得到
				//BillInvest invest = new BillInvest(DataUtils.randomUUID(), t.getMemberInfo(), t, null, UseType.UT3.getVal(), t.getMoney(), balance, asset, status, optTime, remark, style);
			
				//5、改变资产，改变invest_member中的值，获取值的方式和上面的第4步骤差不多
				//6、增加理财记录
				dao.saveEntity(t);
				//7、生成合同
				Thread thread = new Thread(){
					@Override
					public void run(){
						synchronized (this) {
							//这个loanMembers可以再第1步骤，匹配库存的时候获取到的
							//Contract.getInstance().createContract(investMember, loanMembers, investList, contractNo, fullPath);
						}
					}
				};
				thread.start();
				return true;
			}
			//为推荐人增加推荐奖金（暂留...）
			//理财券使用记录（暂留...）
			//理财券花销状态（暂留...）
			//积分累计（暂留...）
			//这几个暂时不写
			
			
			
			//***
			//1.不是在理财成功后再回调增加方法
		}
		return false;
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(InvestList t) throws Exception{
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
	public InvestList findEntity(String property, Object value) throws Exception{
		return dao.findEntity(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public InvestList findEntity(Map<String, Object> params) throws Exception{
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
	public List<InvestList> queryByHQL(String hql,String counthql, PageUtil<InvestList> page, Object... params) throws Exception{
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
	public List<InvestList> queryBySQL(String sql,String countsql, PageUtil<InvestList> page, boolean convert, Object... params) throws Exception{
		return dao.queryBySQL(sql, countsql, page, convert, params);
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(InvestList t) throws Exception {
		return dao.deleteEntity(t);
	}
}
