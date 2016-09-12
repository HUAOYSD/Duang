package org.duang.service.impl;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.duang.annotation.ServiceLog;
import org.duang.common.logger.LoggerUtils;
import org.duang.dao.FriendsDao;
import org.duang.entity.Friends;
import org.duang.entity.MemberInfo;
import org.duang.enums.friends.FriendStatus;
import org.duang.service.FriendsService;
import org.duang.util.DataUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

/**   
 * 好友业务接口实现类
 * @ClassName:  FriendsServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月23日 下午2:19:26      
 */  
@ServiceLog(ModelName="好友")
@Service(value="friendsserviceimpl")
public class FriendsServiceImpl implements FriendsService{

	private FriendsDao dao;

	@Resource(name="friendsdaoimpl")
	public void setDao(FriendsDao dao) {
		this.dao = dao;
	}

	public FriendsServiceImpl(){
		LoggerUtils.info("注入FriendsServiceImpl服务层", this.getClass());
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
	public List<Friends> queryAllEntity(Order order) throws Exception {
		return dao.queryAllEntity(order);
	}


	/**
	 * 查询全部(可分页)
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<Friends> queryAllEntity(PageUtil<Friends> page, Order order) throws Exception{
		return dao.queryAllEntity(page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param field  	    字段名
	 * @param value       字段值
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<Friends> queryEntity(String field, Object value, PageUtil<Friends> page, Order order) throws Exception{
		return dao.queryEntity(field, value, page, order);
	}


	/**
	 * 根据条件获取该操作实体类的泛型集合(可分页)
	 * @param properties  条件名字 集合     properties=null表示查询全部
	 * @param values      条件值集合
	 * @param page        是否分页          null表示不分页
	 * @return 			    返回操作实体类的泛型集合
	 */
	public List<Friends> queryEntity(List<String> properties, List<Object> values, PageUtil<Friends> page) throws Exception{
		return dao.queryEntity(properties, values, page);
	}


	/**
	 * 通过id获取操作实体类
	 * @param id ID值
	 * @return   返回的类对象
	 */
	public Friends findById(Serializable id) throws Exception{
		return dao.findById(id);
	}


	/**
	 * 通过实体对象增加实体数据
	 * @param t  实体对象
	 * @return   是否增加成功
	 */
	public boolean saveEntity(Friends t) throws Exception{
		return dao.saveEntity(t);
	}


	/**
	 * 通过实体对象修改实体数据
	 * @param t  实体对象
	 * @return   是否修改成功
	 */
	public boolean updateEntity(Friends t) throws Exception{
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
	public Friends findEntity(String property, Object value) throws Exception{
		return dao.findEntity(property, value);
	}


	/**
	 * 通过属性与值的集合获取操作实体类
	 * @param params 属性与值的集合
	 * @return
	 * @throws Exception
	 */
	public Friends findEntity(Map<String, Object> params) throws Exception{
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
	public List<Friends> queryByHQL(String hql,String counthql, PageUtil<Friends> page, Object... params) throws Exception{
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
	public List<Friends> queryBySQL(String sql,String countsql, PageUtil<Friends> page, boolean convert, Object... params) throws Exception{
		return dao.queryBySQL(sql, countsql, page, convert, params);
	}


	/**
	 * 通过实体对象删除实体数据
	 * @param t  实体对象
	 * @return   是否删除成功
	 */
	public boolean deleteEntity(Friends t) throws Exception {
		return dao.deleteEntity(t);
	}


	/**   
	 * 增加财友
	 * @Title: addFriend   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param selfid 自己的id
	 * @param: @param targetid 如果对方事先有关注你，需要更新对方的关注你的状态为互相关注
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 白攀    
	 * @date 2016年9月12日 上午10:20:28
	 * @return: boolean      
	 * @throws   
	 */  
	public boolean addFriend(String selfid, String targetid) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberInfoBySelf.id", targetid);
		map.put("memberInfoByTarget.id", selfid);
		Friends friends = dao.findEntity(map);
		int together = friends == null ? FriendStatus.FS1.getVal() : FriendStatus.FS2.getVal();
		Friends newfriends = new Friends(DataUtils.randomUUID(), new MemberInfo(targetid), new MemberInfo(selfid), together, new Date());
		if(friends == null){
			if (dao.saveEntity(newfriends)) {
				return true;
			}
		}else {
			friends.setTogether(FriendStatus.FS2.getVal());
			if (dao.saveEntity(newfriends) && dao.updateEntity(friends)) {
				return true;
			}
		}
		return false;
	}


	/**   
	 * 取消关注财友
	 * @Title: cancelFriend   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param selfid
	 * @param: @param targetid
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 白攀    
	 * @date 2016年9月12日 上午10:23:04
	 * @return: boolean      
	 * @throws   
	 */  
	public boolean cancelFriend(String selfid, String targetid) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberInfoBySelf.id", targetid);
		map.put("memberInfoByTarget.id", selfid);
		Friends friends = dao.findEntity(map);
		map.clear();
		map.put("memberInfoBySelf.id", selfid);
		map.put("memberInfoByTarget.id", targetid);
		if(friends == null){
			if (dao.deleteEntity(map)) {
				return true;
			}
		}else{
			friends.setTogether(FriendStatus.FS1.getVal());
			if (dao.deleteEntity(map) && dao.updateEntity(friends)) {
				return true;
			}
		}
		return false;
	}
}
