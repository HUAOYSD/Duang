package org.duang.service.impl;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.annotation.ServiceLog;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.BillLoanDao;
import org.duang.dao.BindCardDao;
import org.duang.dao.InvestMemberDao;
import org.duang.dao.LoanListDao;
import org.duang.dao.LoanMemberDao;
import org.duang.dao.LoanMemberRepayDateDao;
import org.duang.dao.MemberInfoDao;
import org.duang.entity.BillLoan;
import org.duang.entity.BindCard;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanList;
import org.duang.entity.LoanMember;
import org.duang.entity.MemberInfo;
import org.duang.enums.billloan.BillStatus;
import org.duang.enums.billloan.UseType;
import org.duang.enums.loan.ReturnStatus;
import org.duang.service.LoanListService;
import org.duang.util.DataUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

/**   
 * 借贷列表业务接口实现类
 * @ClassName:  LoanListServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年8月10日 下午5:19:26      
 */  
@ServiceLog(ModelName="借贷记录管理")
@Service(value="loanlistserviceimpl")
public class LoanListServiceImpl implements LoanListService{

	private LoanListDao dao;

	@Resource
	public void setDao(LoanListDao dao) {
		this.dao = dao;
	}

	private MemberInfoDao memberInfoDao;
	@Resource
	public void setMemberInfoDao(MemberInfoDao memberInfoDao) {
		this.memberInfoDao = memberInfoDao;
	}
	
	private BindCardDao bindCardDao;
	@Resource
	public void setBindCardDao(BindCardDao bindCardDao) {
		this.bindCardDao = bindCardDao;
	}
	
	private LoanMemberDao loanMemberDao;
	@Resource
	public void setLoanMemberDao(LoanMemberDao loanMemberDao) {
		this.loanMemberDao = loanMemberDao;
	}
	
	private InvestMemberDao investMemberDao;
	@Resource
	public void setInvestMemberDao(InvestMemberDao investMemberDao) {
		this.investMemberDao = investMemberDao;
	}
	
	private BillLoanDao billLoanDao;
	@Resource
	public void setBillLoanDao(BillLoanDao billLoanDao) {
		this.billLoanDao = billLoanDao;
	}
	
	private LoanListDao loanListDao;
	@Resource
	public void setLoanListDao(LoanListDao loanListDao) {
		this.loanListDao = loanListDao;
	}
	
	private LoanMemberRepayDateDao loanMemberRepayDateDao;
	@Resource
	public void setLoanMemberRepayDateDao(LoanMemberRepayDateDao loanMemberRepayDateDao) {
		this.loanMemberRepayDateDao = loanMemberRepayDateDao;
	}
	
	public LoanListServiceImpl(){
		LoggerUtils.info("注入LoanListServiceImpl服务层", this.getClass());
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
	public List<LoanList> queryAllEntity(Order order) throws Exception {
		return dao.queryAllEntity(order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<LoanList> queryAllEntity(PageUtil<LoanList> page, Order order) throws Exception{
		return dao.queryAllEntity(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<LoanList> queryEntity(String field, Object value, PageUtil<LoanList> page, Order order) throws Exception{
		return dao.queryEntity(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<LoanList> queryEntity(List<String> properties, List<Object> values, PageUtil<LoanList> page) throws Exception{
		return dao.queryEntity(properties, values, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public LoanList findById(Serializable id) throws Exception{
		return dao.findById(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(LoanList t) throws Exception{
		return dao.saveEntity(t);
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(LoanList t) throws Exception{
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
	public LoanList findEntity(String property, Object value) throws Exception{
		return dao.findEntity(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public LoanList findEntity(Map<String, Object> params) throws Exception{
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
	public List<LoanList> queryByHQL(String hql,String counthql, PageUtil<LoanList> page, Object... params) throws Exception{
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
	public List<LoanList> queryBySQL(String sql,String countsql, PageUtil<LoanList> page, boolean convert, Object... params) throws Exception{
		return dao.queryBySQL(sql, countsql, page, convert, params);
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(LoanList t) throws Exception {
		return dao.deleteEntity(t);
	}

	/**
	 * 还款成功后操作
	 * <p>Title: memberInfoRepay</p>   
	 * <p>Description: </p>  
	 * @author LiYonghui
	 * @date 2016年12月12日 下午4:20:03
	 * @param sum
	 * @param memberInfoId
	 * @param scaleId
	 * @param loanList
	 * @param style
	 * @return
	 * @throws Exception   
	 * @see org.duang.service.LoanListService#memberInfoRepay(double, java.lang.String, java.lang.String, org.duang.entity.LoanList, int)
	 */
	@Override
	public boolean memberInfoRepay(double sum, String memberInfoId, String scaleId, LoanList loanList, int style) throws Exception {
		boolean success = false;
		LoggerUtils.info("\t\n---------------用户还款成功，修改数据参数", this.getClass());
		//基本账户信息
		MemberInfo memberInfo = memberInfoDao.findById(memberInfoId);
		//绑定的银行卡
		BindCard bindCard = bindCardDao.findEntity("memberInfo.id", memberInfoId);
		//借贷账户
		LoanMember loanMember = loanMemberDao.findEntity("memberInfo.id", memberInfoId);
		//1.记录还款
		BillLoan billLoan = new BillLoan(DataUtils.randomUUID());
		billLoan.setBillStatus(BillStatus.BS2.getVal());
		if(bindCard != null){
			billLoan.setBindCard(bindCard);
		}
		billLoan.setDoneMoney(loanMember.getResidueMoney()-sum);
		billLoan.setLoanList(loanList);
		billLoan.setMemberInfo(memberInfo);
		billLoan.setMoney(-sum);
		billLoan.setOptTime(new Date());
		billLoan.setRemark("还款");
		billLoan.setStatus(UseType.UT2.getVal());
		billLoan.setStyle(style);
		success = billLoanDao.saveEntity(billLoan);
		if(success){
			//2.修改理财账户金额（账户余额）
			InvestMember investMember = investMemberDao.findEntity("memberInfo.id",memberInfoId);
			investMember.setBalance(investMember.getBalance()-sum);
			success = investMemberDao.updateEntity(investMember);
			if(success){
				
				//3.修改每期的还款状态
				if(success){
					Map<String,Object> repayMap = loanMemberRepayDateDao.updateLoanMemberRepayDateByRepay(sum, loanList, memberInfoId);
					loanList.setAgoMoney(loanList.getAgoMoney()+(double)repayMap.get("overSum"));
				}
				//4.修改借贷账户的金额
				//总还款
				loanMember.setBackMoney(loanMember.getBackMoney()+sum);
				//当前借款
				loanMember.setCurMoney(loanMember.getCurMoney()-sum);
				//剩余还款
				loanMember.setResidueMoney(loanMember.getResidueMoney()-sum);
				success = loanMemberDao.updateEntity(loanMember);
				//5.修改借贷记录为已完成
				loanList.setYetReturnMoney(loanList.getYetReturnMoney()+sum);
				if(success && loanList.getYetReturnMoney()==loanList.getReturnMoney()){
					loanList.setReturnStatus(ReturnStatus.B4.getVal());
				}
				success = loanListDao.updateEntity(loanList);
			}
		}
		return success;
	}
}
