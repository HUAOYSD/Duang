package org.duang.service.impl;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.annotation.ServiceLog;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.BillLoanDao;
import org.duang.dao.BindCardDao;
import org.duang.dao.InvestListDao;
import org.duang.dao.InvestMemberDao;
import org.duang.dao.LoanListDao;
import org.duang.dao.LoanMemberDao;
import org.duang.dao.ScaleDao;
import org.duang.dao.ScaleLoanListDao;
import org.duang.entity.BillLoan;
import org.duang.entity.BindCard;
import org.duang.entity.InvestList;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanList;
import org.duang.entity.LoanMember;
import org.duang.entity.Scale;
import org.duang.entity.ScaleLoanList;
import org.duang.enums.invest.Status;
import org.duang.enums.loan.Apply;
import org.duang.enums.loan.LoanStatus;
import org.duang.service.ScaleService;
import org.duang.util.DataUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

/**   
 * 理财标业务接口实现类
 * @ClassName:  SysInvestMemberServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月26日 下午3:25:24      
 */  
@ServiceLog(ModelName="理财标管理")
@Service(value="scaleserviceimpl")
public class ScaleServiceImpl implements ScaleService{

	private ScaleDao dao;

	@Resource
	public void setDao(ScaleDao dao) {
		this.dao = dao;
	}
	
	public ScaleServiceImpl(){
		LoggerUtils.info("注入ScaleServiceImpl服务层", this.getClass());
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
	public List<Scale> queryAllEntity(Order order) throws Exception {
		return dao.queryAllEntity(order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<Scale> queryAllEntity(PageUtil<Scale> page, Order order) throws Exception{
		return dao.queryAllEntity(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<Scale> queryEntity(String field, Object value, PageUtil<Scale> page, Order order) throws Exception{
		return dao.queryEntity(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<Scale> queryEntity(List<String> properties, List<Object> values, PageUtil<Scale> page) throws Exception{
		return dao.queryEntity(properties, values, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public Scale findById(Serializable id) throws Exception{
		return dao.findById(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(Scale t) throws Exception{
		return dao.saveEntity(t);
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(Scale t) throws Exception{
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
	public Scale findEntity(String property, Object value) throws Exception{
		return dao.findEntity(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public Scale findEntity(Map<String, Object> params) throws Exception{
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
	public List<Scale> queryByHQL(String hql,String counthql, PageUtil<Scale> page, Object... params) throws Exception{
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
	public List<Scale> queryBySQL(String sql,String countsql, PageUtil<Scale> page, boolean convert, Object... params) throws Exception{
		return dao.queryBySQL(sql, countsql, page, convert, params);
	}

	
	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(Scale t) throws Exception {
		return dao.deleteEntity(t);
	}

	/**
	 * 查询标的状态  //0新建标，1流标，2可投标，3已完成
	 * <p>Title: findScaleState</p>   
	 * <p>Description: </p>  
	 * @author LiYonghui
	 * @date 2016年11月11日 上午11:11:09
	 * @param id
	 * @return
	 * @throws Exception   
	 * @see org.duang.service.ScaleService#findScaleState(java.lang.String)
	 */
	@Override
	public int findScaleState(String id) throws Exception {
		Scale scale = dao.findById(id);
		if(scale != null){
			return scale.getStatus();
		}
		return -1;
	}

	private LoanListDao loanListDao;
	@Resource()
	public void setLoanListDao(LoanListDao loanListDao) {
		this.loanListDao = loanListDao;
	}
	private BindCardDao bindCardDao;
	@Resource()
	public void setBindCardDao(BindCardDao bindCardDao) {
		this.bindCardDao = bindCardDao;
	}
	private BillLoanDao billLoanDao;
	@Resource()
	public void setBillLoanDao(BillLoanDao billLoanDao) {
		this.billLoanDao = billLoanDao;
	}
	@Override
	public boolean fullScaleLoanMoney(String scaleId, double money) throws Exception {
		//满标进行放款
		//查询标的借贷列表
		boolean success = false;
		String sql = "select * from loan_list ll  left join scale_loan_list sll on ll.id=sll.loan_list where sll.scale='"+scaleId
    			+"' and apply_state=2 and loan_state=1";
    	List<LoanList> loanLists = loanListDao.queryBySQL(sql, null, null,true);
    	for(LoanList loanList : loanLists){
    		loanList.setYetMoney(loanList.getGetMoney());
    		loanList.setLoanState(3);
    		//1.修改借贷列表
    		success = loanListDao.updateEntity(loanList);
    		if(success){
    			//2.生成放款记录
    			//获取BindCard
    			BindCard bindCard = bindCardDao.findEntity("memberInfo.id", loanList.getMemberInfo().getId());
    			BillLoan billLoan = new BillLoan(DataUtils.randomUUID());
    			billLoan.setBindCard(bindCard);
    			//状态，1操作中，2成功，3失败
    			billLoan.setBillStatus(2);
    			billLoan.setLoanList(loanList);
    			billLoan.setMemberInfo(loanList.getMemberInfo());
    			billLoan.setOptTime(new Date());
    			billLoan.setStatus(LoanStatus.S1.getVal());
    			billLoan.setMoney(+money);
    			billLoan.setDoneMoney(money);
    			//方式，1线下，2Android，3IOS，4平台系统
    			billLoan.setStyle(4);
    			billLoan.setRemark("放款");
    			success = billLoanDao.saveEntity(billLoan);
    			if(!success){
    				LoggerUtils.error("\t\n-------------------------name:"+loanList.getMemberInfo().getRealName()+
									  "\t\n------------------------phone:"+loanList.getMemberInfo().getPhone()+
									  "\t\n---------------------nickName:"+loanList.getMemberInfo().getNickname()+
									  "\t\n---------------------放款操作中，保存放款记录失败", this.getClass());
    			}
    		}else{
    			LoggerUtils.error("\t\n-------------------------name:"+loanList.getMemberInfo().getRealName()+
    							  "\t\n------------------------phone:"+loanList.getMemberInfo().getPhone()+
    							  "\t\n---------------------nickName:"+loanList.getMemberInfo().getNickname()+
    							  "\t\n---------------------放款操作中，更新借贷列表失败", this.getClass());
    		}
    	}
    	
    	List<InvestList> investLists = investListDao.queryEntity("scale.id", scaleId, null, null);
    	for(InvestList investList : investLists){
    		investList.setStatus(Status.S2.getVal());
    		investList.setCalcBeginDate(new Date());
    		investList.setCalcEndDate(getDate(investList.getCalcBeginDate(),investList.getDays()));
    		//1.修改投标列表状态
    		success = investListDao.updateEntity(investList);
    	}
    	
		return success;
	}

	/**
	 * 获取指定日期的day天前的日期
	 * @Title: getDate   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param date
	 * @param: @param day
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年11月23日 下午6:44:41
	 * @return: Date      
	 * @throws
	 */
	private Date getDate(Date date,int day){
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.add(Calendar.DATE, day);
		  return cal.getTime();
	}
	
	private InvestListDao investListDao;
	@Resource
	public void setInvestListDao(InvestListDao investListDao) {
		this.investListDao = investListDao;
	}
	private InvestMemberDao investMemberDao;
	@Resource
	public void setInvestMemberDao(InvestMemberDao investMemberDao) {
		this.investMemberDao = investMemberDao;
	}
	private ScaleLoanListDao scaleLoanListDao;
	@Resource
	public void setScaleLoanListDao(ScaleLoanListDao scaleLoanListDao) {
		this.scaleLoanListDao = scaleLoanListDao;
	}
	private LoanMemberDao loanMemberDao;
	@Resource
	public void setLoanMemberDao(LoanMemberDao loanMemberDao) {
		this.loanMemberDao = loanMemberDao;
	}
	/**
	 * 流标赎回操作
	 * @Title: fullScaleLoanMoney   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param scaleId
	 * @param: @param money
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月23日 下午5:00:41
	 * @return: boolean      
	 * @throws
	 */
	public boolean failScaleBackMoney(String scaleId, double money) throws Exception {
		boolean success = false;
		//1.修改标状态
		Scale scale = dao.findById(scaleId);
		scale.setStatus(org.duang.enums.loan.Scale.S2.getVal());
		success = dao.updateEntity(scale);
		if(success){
			//修改投资记录
			List<InvestList> investLists = investListDao.queryEntity("scale.id", scaleId, null, null);
			for(InvestList investList : investLists){
				investList.setBackIncome(0);
				investList.setBackMoney(investList.getMoney());
				investList.setTotalMoney(investList.getMoney());
				investList.setStatus(org.duang.enums.invest.Status.S9.getVal());
				investList.setBackDate(new Date());
				success = investListDao.updateEntity(investList);
				//修改投标人的账户信息
				InvestMember investMember = investMemberDao.findEntity("memberInfo.id",investList.getMemberInfo().getId());
				if(investMember != null){
					investMember.setInvesting(investMember.getInvesting()-investList.getMoney());
					investMemberDao.updateEntity(investMember);
				}
			}
			//修改借贷人的账户信息
			List<ScaleLoanList> scaleLoanLists = scaleLoanListDao.queryEntity("scale.id",scaleId, null, null);
			for(ScaleLoanList scaleLoanList : scaleLoanLists){
				LoanList loanList = scaleLoanList.getLoanList();
				loanList.setApplyState(Apply.A4.getVal());
				success = loanListDao.updateEntity(loanList);
				LoanMember loanMember = loanMemberDao.findEntity("memberInfo.id", loanList.getMemberInfo().getId());
				loanMember.setBackMoney(loanMember.getCurMoney()-loanList.getMoney());
				loanMember.setLendMoney(loanMember.getLendMoney()-loanList.getMoney());
				loanMemberDao.updateEntity(loanMember);
			}
		}else{
			LoggerUtils.error("\t\n--------------流标申请，异步返回数据处理，更新标状态错误", this.getClass());
		}
		return success;
	}

}
