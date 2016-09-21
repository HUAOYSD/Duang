package org.duang.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.annotation.ServiceLog;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.MemberInfoDao;
import org.duang.entity.MemberInfo;
import org.duang.service.MemberInfoService;
import org.duang.util.DataUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

/**   
 * 公共用户信息业务接口实现类
 * @ClassName:  SysMemberInfoServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月26日 下午3:25:24      
 */  
@ServiceLog(ModelName="功能用户信息管理")
@Service(value="sysmemberinfoserviceimpl")
public class MemberInfoServiceImpl implements MemberInfoService{

	private MemberInfoDao dao;

	@Resource(name="sysmemberinfodaoimpl")
	public void setDao(MemberInfoDao dao) {
		this.dao = dao;
	}

	public MemberInfoServiceImpl(){
		LoggerUtils.info("注入sysmemberinfoserviceimpl服务层", this.getClass());
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
	public List<MemberInfo> queryAllEntity(Order order) throws Exception {
		return dao.queryAllEntity(order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<MemberInfo> queryAllEntity(PageUtil<MemberInfo> page, Order order) throws Exception{
		return dao.queryAllEntity(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<MemberInfo> queryEntity(String field, Object value, PageUtil<MemberInfo> page, Order order) throws Exception{
		return dao.queryEntity(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<MemberInfo> queryEntity(List<String> properties, List<Object> values, PageUtil<MemberInfo> page) throws Exception{
		return dao.queryEntity(properties, values, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public MemberInfo findById(Serializable id) throws Exception{
		return dao.findById(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(MemberInfo t) throws Exception{
		return dao.saveEntity(t);
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(MemberInfo t) throws Exception{
		return dao.updateEntity(t);
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(MemberInfo t) throws Exception{
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
	public MemberInfo findEntity(String property, Object value) throws Exception{
		return dao.findEntity(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public MemberInfo findEntity(Map<String, Object> params) throws Exception{
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
	public List<MemberInfo> queryByHQL(String hql,String counthql, PageUtil<MemberInfo> page, Object... params) throws Exception{
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
	public List<MemberInfo> queryBySQL(String sql,String countsql, PageUtil<MemberInfo> page, boolean convert, Object... params) throws Exception{
		return dao.queryBySQL(sql, countsql, page, convert, params);
	}

	
	/**
	 * 获总投资、借贷数据
	 * <p>Title: queryLoanAndInvestInfo</p>   
	 * <p>Description: </p>  
	 * @author LiYonghui
	 * @date 2016年9月13日 上午11:16:19
	 * @return   
	 * @see org.duang.service.MemberInfoService#queryLoanAndInvestInfo()
	 */
	public Map<String, Object> queryLoanAndInvestInfo(String id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String loanCarSQL ="select sum(loan.money) as carLoan  from apply_loan_car lcar left join loan_list loan on loan.id = lcar.loan_list_id where loan.loan_type = 2 and loan.member_info="+id;
		String loanHouseSQL ="select sum(loan.money) as houseLoan from apply_loan_house lh left join loan_list loan on loan.id = lh.loan_list_id where loan.loan_type = 2 and loan.member_info="+id;
		String creditLoanSQL ="select sum(ll.money) as creditLoan from loan_list ll where ll.loan_type=3 and loan.member_info="+id;
		String investCarSQL ="select sum(il.money) as carInvest from invest_list il left join scale sce on sce.id=il.scale_id left join product prc on prc.id=sce.product_id where prc.category=1 and il.member_info="+id;
		String investHouseSQL ="select sum(il.money) as houseInvest from invest_list il left join scale sce on sce.id=il.scale_id left join product prc on prc.id=sce.product_id where prc.category=2 and il.member_info="+id;
		String creditInvestSQL ="select sum(il.money) as creditInvest from invest_list il left join scale sce on sce.id=il.scale_id left join product prc on prc.id=sce.product_id where prc.category=3 and il.member_info="+id;
	
		List<MemberInfo> loanCarlist = dao.queryBySQL(loanCarSQL, null, null, false);
		List<MemberInfo> loanHouselist = dao.queryBySQL(loanHouseSQL, null, null, false);
		List<MemberInfo> creditLoanlist = dao.queryBySQL(creditLoanSQL, null, null, false);
		List<MemberInfo> investCarlist = dao.queryBySQL(investCarSQL, null, null, false);
		List<MemberInfo> investHouselist = dao.queryBySQL(investHouseSQL, null, null, false);
		List<MemberInfo> creditInvestlist = dao.queryBySQL(creditInvestSQL, null, null, false);
		
		map.put("carLoan",loanCarlist==null?0:loanCarlist.get(0));
		map.put("houseLoan",loanCarlist==null?0:loanHouselist.get(0));
		map.put("creditLoan",loanCarlist==null?0:creditLoanlist.get(0));
		map.put("carInvest",loanCarlist==null?0:investCarlist.get(0));
		map.put("houseInvest",loanCarlist==null?0:investHouselist.get(0));
		map.put("creditInvest",loanCarlist==null?0:creditInvestlist.get(0));
		return map;
	}
	
	/**   
	 * 查询用户推荐关系
	 * @Title: queryLevelMemberInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 白攀    
	 * @date 2016年9月21日 上午10:41:36
	 * @return: List<Map<String,Object>>      
	 * @throws   
	 */  
	public List<Map<String, Object>> queryLevelMemberInfo(String where, PageUtil<MemberInfo> page) throws Exception{
		List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
		String sql = "SELECT CUSMEMBERID, REAL_NAME FROM MEMBER_INFO WHERE ISDELETE = 0 " + where + " ORDER BY CREATETIME DESC";
		String countSql = "SELECT COUNT(*) FROM MEMBER_INFO WHERE ISDELETE = 0" + where;
		List<?> list = dao.queryBySQL(sql, countSql, page, false);
		if (DataUtils.notEmpty(list)) {
			for (Object object : list) {
				if (object instanceof Object[]) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("firtname", ((Object[])object)[1].toString());
					if (((Object[])object)[0] == null) {
						map.put("secondname", "--");
						map.put("thirdname", "--");
					}else {
						List<?> templist = dao.queryBySQL("SELECT CUSMEMBERID, REAL_NAME FROM MEMBER_INFO WHERE ID = '"+((Object[])object)[0].toString()+"'", null, null, false);
						map.put("secondname", ((Object[])templist.get(0))[1].toString());
						if (((Object[])templist.get(0))[0] == null) {
							map.put("thirdname", "--");
						}else {
							templist = dao.queryBySQL("SELECT REAL_NAME FROM MEMBER_INFO WHERE ID = '"+((Object[])templist.get(0))[0].toString()+"'", null, null, false);
							map.put("thirdname", templist.get(0).toString());
						}
					}
					listmap.add(map);
				}
			}
		}
		return listmap;
	};

}
