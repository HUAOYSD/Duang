package org.duang.service.impl;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.duang.annotation.ServiceLog;
import org.duang.common.CondsUtils;
import org.duang.common.ContractFactory;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.BillInvestDao;
import org.duang.dao.ContractDao;
import org.duang.dao.InvestListDao;
import org.duang.dao.InvestMemberDao;
import org.duang.dao.LoanMemberDao;
import org.duang.dao.MemberInfoDao;
import org.duang.dao.ScaleDao;
import org.duang.dao.StockDao;
import org.duang.entity.BillInvest;
import org.duang.entity.Contract;
import org.duang.entity.InvestList;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanMember;
import org.duang.entity.MemberInfo;
import org.duang.entity.Scale;
import org.duang.entity.Stock;
import org.duang.enums.If;
import org.duang.enums.UploadFile;
import org.duang.enums.billinvest.BillStatus;
import org.duang.enums.billinvest.UseType;
import org.duang.enums.invest.Status;
import org.duang.service.ContractService;
import org.duang.service.InvestListService;
import org.duang.util.DataUtils;
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
	@Resource
	private ScaleDao scaleDao;
	@Resource
	private MemberInfoDao memberInfoDao;
	private StockDao stockDao;
	@Resource
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	@Resource
	private BillInvestDao billInvestDao;
	@Resource
	private ContractService contractService;
	@Resource
	private LoanMemberDao loanMemberDao;
	@Resource
	private InvestMemberDao investMemberDao;
	@Resource
	private ContractDao contractDao;

	/**
	 * 丰付投标回调
	 * @Title: investFFCallback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月3日 下午7:32:03
	 * @return: void      
	 * @throws
	 */
	public synchronized boolean investFFCallback(String scaleid, String memberinfoid, double money, int investStyle, double giftSum, String requestid) throws Exception{
		Scale scale = scaleDao.findById(scaleid);
		MemberInfo memberInfo = memberInfoDao.findById(memberinfoid);
		double income = money * (scale.getRevenue() + scale.getRevenueAdd()) / 365D * scale.getProduct().getDays();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int sevenactivity = 0;
		if (System.currentTimeMillis() <= sf.parse("2016-11-18 23:59:59").getTime() ) {
			income += scale.getProduct().getDays() / 365D * money * (scale.getRevenue() + scale.getRevenueAdd()) / 365D * 7D;
			sevenactivity = 1;
		}
		income = DataUtils.str2double(income+"", 6);
		//增加理财记录信息
		Calendar calendar = Calendar.getInstance();
		Date today = sf.parse(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE)+" 00:30:00");
		Date backdate = new Date(today.getTime() + 24L*3600L*1000L);
		Date endDate = new Date(today.getTime() + 24L*3600L*1000L*scale.getProduct().getDays());
		InvestList investList = new InvestList(DataUtils.randomUUID(), scale, null, memberInfo, money, 0, 0, 1, 0, 0, money+income, income, 0, Status.S3.getVal(), new Date(), null, backdate, endDate, null, investStyle, 0, 0, giftSum, scale.getProduct().getDays(), null, null, null, null, null,sevenactivity,requestid);

		//理财标更新
		scale.setResidueMoney(scale.getResidueMoney()-money);//剩余可投金额
		scale.setYetMoney(scale.getYetMoney() + money);//已经投金额
		if(scale.getResidueMoney()<=0){
			scale.setStatus(org.duang.enums.scale.Status.S3.getVal());
		}else{
			scale.setStatus(org.duang.enums.scale.Status.S2.getVal());
		}
		boolean success = scaleDao.updateEntity(scale);

		//合同
		String contractNo = String.valueOf(contractService.getContractIndexByYear()+1);
		String pactNumber = DataUtils.getContractNo(DataUtils.str2int(contractNo));
		investList.setPactNumber(pactNumber);

		//1、匹配库存
		//2、更改分配到的库存的状态
		//根据投资列表id查找理财标库存中未被分配的记录
		CondsUtils condsUtils = new CondsUtils();
		condsUtils.addProperties(true, "scale.id","status");
		condsUtils.addValues(true, scaleid, 0);
		List<Stock> stocks = stockDao.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);//获取该理财标库存中还未分配理财标的记录
		List<Map<String, String>> loanMembers = new ArrayList<Map<String,String>>();
		if (DataUtils.notEmpty(stocks)) {
			//将集合打乱顺序，随机为该投资人分配借贷人
			Collections.shuffle(stocks);
			List<Stock> mystock = new ArrayList<Stock>();//存放该理财记录的库存记录的集合
			if (money <= 500) {//测试时候，购买少量金额
				Stock stock = stocks.get(0);
				stock.setFetch(money);
				stock.setFetchTime(new Date());
				stock.setDifference(stock.getMoney() - money);
				stock.setStatus(Status.S2.getVal());
				stock.setInvestList(investList);
				mystock.add(stock);

				MemberInfo loanmemberinfo = stock.getLoanList().getMemberInfo();
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", loanmemberinfo.getRealName());
				map.put("idcard", loanmemberinfo.getIdCard());
				map.put("money", String.valueOf(money));
				loanMembers.add(map);
			}else {//正常仪式购买
				double tempMoney = 0;
				for (Stock stock : stocks) {
					if (tempMoney + stock.getMoney() < investList.getMoney()) {
						stock.setFetch(stock.getMoney());
						stock.setFetchTime(new Date());
						stock.setDifference(0);
						stock.setStatus(Status.S2.getVal());
						stock.setInvestList(investList);
						mystock.add(stock);
						//获取每个库存中借贷人loanMember的信息
						MemberInfo loanmemberinfo = stock.getLoanList().getMemberInfo();
						Map<String, String> map = new HashMap<String, String>();
						map.put("name", loanmemberinfo.getRealName());
						map.put("idcard", loanmemberinfo.getIdCard());
						LoanMember loanMember = loanMemberDao.findEntity("memberInfo.id", loanmemberinfo.getId());
						if(loanMember!=null){
							map.put("money", String.valueOf(loanMember.getLendMoney()));
						}else{
							map.put("money", "0");
						}
						loanMembers.add(map);
						success = true;
					}else {
						break;
					}
				}
			}
			//更改分配到的库存的状态
			for (Stock stock : mystock) {
				success = stockDao.updateEntity(stock);
			}
			if (success) {
				//3、产生理财订单
				//3.1、先获取到此人，最后一次billinvest记录，该条记录含有此人最新的余额、总资产数据	，新记录的这俩数据，通过这个数据做加减法即可得到
				List<BillInvest> billInvestsList = billInvestDao.queryEntity("memberInfo.id", investList.getMemberInfo().getId(), null, Order.desc("optTime"));
				if(DataUtils.isEmpty(billInvestsList)){//首次产生流水,其实不会
					BillInvest nextBillInvest = new BillInvest();
					nextBillInvest.setId(DataUtils.randomUUID());
					nextBillInvest.setMemberInfo(memberInfo);
					nextBillInvest.setInvestList(investList);
					nextBillInvest.setUseType(UseType.UT3.getVal());
					nextBillInvest.setMoney(money);
					nextBillInvest.setBalance(0);
					nextBillInvest.setAsset(money);
					nextBillInvest.setBindCard(null);
					nextBillInvest.setOptTime(new Date());
					nextBillInvest.setRemark("购买理财");
					nextBillInvest.setStyle(investStyle);
					nextBillInvest.setStatus(BillStatus.BS2.getVal());
					success = billInvestDao.saveEntity(nextBillInvest);
				}else{
					BillInvest nextBillInvest = new BillInvest();
					nextBillInvest.setId(DataUtils.randomUUID());
					nextBillInvest.setMemberInfo(memberInfo);
					nextBillInvest.setInvestList(investList);
					nextBillInvest.setUseType(UseType.UT3.getVal());
					nextBillInvest.setMoney(money);
					nextBillInvest.setBalance(billInvestsList.get(0).getBalance() - money);
					nextBillInvest.setAsset(billInvestsList.get(0).getAsset());
					nextBillInvest.setBindCard(null);
					nextBillInvest.setOptTime(new Date());
					nextBillInvest.setRemark("购买理财");
					nextBillInvest.setStyle(investStyle);
					nextBillInvest.setStatus(BillStatus.BS2.getVal());
					success = billInvestDao.saveEntity(nextBillInvest);
				}
				
				
				//4、改变资产，改变invest_member中的值，获取值的方式和上面的第4步骤差不多
				InvestMember investMember = investMemberDao.findEntity("memberInfo.id", investList.getMemberInfo().getId());
				investMember.setBalance(investMember.getBalance() - money);
				investMember.setInvesting(investMember.getInvesting() + money);
				investMember.setTotalMoney(investMember.getBalance() + investMember.getInvesting());
				investMember.setCurrentIncome(income);
				//investMember.setUnsettledBalance();
				
				//ServletActionContext.getRequest().setAttribute("memberName", memberInfo.getRealName());
				//ServletActionContext.getRequest().setAttribute("memberIdcard", memberInfo.getIdCard());
				//5、增加理财记录
				success = dao.saveEntity(investList);
				//6、生成合同
				//保存合同路径
				//获取合同的编号
				String temPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
				String fullPath = temPath+UploadFile.PATH.getVal(UploadFile.CONTRACT.getVal());
				LoggerUtils.info("-----------------合同编号："+contractNo+"生成合同的地址："+fullPath,this.getClass());
				//生成合同
				ContractFactory contractFactory = ContractFactory.getInstance(memberInfo, loanMembers, investList, pactNumber, fullPath);
				contractFactory.start();
				//保存合同信息
				Contract contract = new Contract();
				contract.setId(pactNumber);
				contract.setName(contractFactory.getContractNo()+".pdf");
				contract.setConPath(UploadFile.PATH.getVal(UploadFile.CONTRACT.getVal())+"\\"+contractFactory.getContractNo()+".pdf");
				contract.setCreateTime(new Date());
				contract.setState(If.If1.getVal());
				success = contractDao.saveEntity(contract);
			}
		}
		return success;
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


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(InvestList investList) throws Exception{
		boolean  success = false;
		dao.saveEntity(investList);
		return success;
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


	/**
	 * 提前赎回成功后的回调
	 * @param id
	 * @return
	 */
	public boolean ransomed(String id, double income) throws Exception {
		//
		//改变理财标状态
		InvestList investList = dao.findById(id);
		investList.setBackIncome(income);
		investList.setTotalMoney(investList.getMoney() + income);
		investList.setIncome(income);
		investList.setStatus(Status.S8.getVal());
		//增加理财交易流水
		return true;
	}

	/**
	 * 集合、普通项目本息到账返回处理方法
	 * <p>Title: transactionForFTCallback</p>   
	 * <p>Description: </p>  
	 * @author LiYonghui
	 * @date 2016年12月15日 下午4:23:43
	 * @param scaleid
	 * @param memberinfoid
	 * @param money
	 * @param investStyle
	 * @param giftSum
	 * @param requestid
	 * @return
	 * @throws Exception   
	 * @see org.duang.service.InvestListService#transactionForFTCallback(java.lang.String, java.lang.String, double, int, double, java.lang.String)
	 */
	@Override
	public boolean transactionForFTCallback(InvestList investList,double sum) throws Exception {
		boolean success = false;
		investList.setBackIncome(investList.getIncome());
		investList.setBackMoney(investList.getTotalMoney()-investList.getIncome());
		investList.setBackDate(new Date());
		investList.setStatus(Status.S5.getVal());
		success = dao.updateEntity(investList);
		String memberInfoId = investList.getMemberInfo().getId();
		InvestMember investMember = investMemberDao.findEntity("memberInfo.id", memberInfoId);
		investMember.setBalance(investMember.getBalance()+sum);
		investMember.setInvesting(investMember.getInvesting()-investList.getMoney());
		investMember.setTotalIncome(investMember.getTotalIncome()+investList.getIncome());
		investMember.setTotalMoney(investMember.getBalance()+investMember.getInvesting());
		success = investMemberDao.updateEntity(investMember);
		return success;
	}

}
