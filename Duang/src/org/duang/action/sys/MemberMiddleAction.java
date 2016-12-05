package org.duang.action.sys;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.SessionTools;
import org.duang.entity.MemberInfo;
import org.duang.entity.MemberMiddle;
import org.duang.entity.SysUser;
import org.duang.enums.If;
import org.duang.service.MemberInfoService;
import org.duang.service.MemberMiddleService;
import org.duang.service.SysUserService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 中间人Action
 * @ClassName:  MemberMiddleAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年11月28日 上午11:25:36
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "memberMiddle")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/membermiddle/memberMiddle.jsp"),
			@Result(name = ResultPath.ADD, type = "dispatcher", location = "WEB-INF/page/sys/membermiddle/addMemberMiddle.jsp"),
			@Result(name = "selectUser", type = "dispatcher", location = "WEB-INF/page/sys/membermiddle/selectMemberMiddle.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class MemberMiddleAction extends BaseAction<MemberMiddle> {
	private static final long serialVersionUID = 1L;
	//启用
	private static final String TRUE_STATE="1";
	//禁用
	private static final String FALSE_STATE="2";
	private MemberMiddleService memberMiddleService;
	@Resource(name = "membermiddleserviceimpl")
	public void setMemberMiddleService(MemberMiddleService memberMiddleService) {
		this.memberMiddleService = memberMiddleService;
	}

	private MemberInfoService memberInfoService;
	@Resource(name = "sysmemberinfoserviceimpl")
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
	}
	/**
	 * 页面跳转 ---列表页面
	 * @Title: gotolist   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年11月28日 上午11:27:34
	 * @return: String      
	 * @throws
	 */
	public String gotolist(){
		return "list";
	}
	
	/**
	 * 查询所有
	 * @Title: queryAd   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午3:49:21
	 * @return: void      
	 * @throws
	 */
	public void queryAll() {
		try {
			List<MemberMiddle> list = new ArrayList<MemberMiddle>();
			condsUtils.addProperties(true, "state");
			//状态  1正常  2禁用
			condsUtils.addValues(true, TRUE_STATE);
			condsUtils.addProperties(false, "order");
			condsUtils.addValues(false, Order.desc("createTime"));
			list = memberMiddleService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			jsonObject.put("result", true);
			jsonObject.put("rows", fillDataObjectList(list));
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("中间人ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("中间人ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 查询账户的用户名和idCard
	 * @Title: queryNameIdCard   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月28日 下午7:04:14
	 * @return: void      
	 * @throws
	 */
	public void queryNameIdCard(){
		try {
			List<MemberMiddle> list = new ArrayList<MemberMiddle>();
			condsUtils.addProperties(true, "state");
			//状态  1正常  2禁用
			condsUtils.addValues(true, TRUE_STATE);
			condsUtils.addProperties(false, "order");
			condsUtils.addValues(false, Order.desc("createTime"));
			list = memberMiddleService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			jsonObject.put("result", true);
			jsonObject.put("rows", fillDataObjectList1(list));
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("中间人ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("中间人ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	private SysUserService sysUserService;
	@Resource(name = "sysuserserviceimpl")
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	/**
	 * 封装集合
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午2:53:00
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<MemberMiddle> list) {
		try {
			for (MemberMiddle memberMiddle : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", memberMiddle.getId());
				map.put("createTime", DateUtils.date2Str(memberMiddle.getCreateTime()));
				map.put("state", memberMiddle.getState());
				map.put("createUser", memberMiddle.getCreateUser());
				map.put("createUserName", sysUserService.findById(memberMiddle.getCreateUser()).getName());
				map.put("modifyUser", memberMiddle.getModifyUser());
				map.put("modifyUserName", DataUtils.notEmpty(memberMiddle.getModifyUser())?sysUserService.findById(memberMiddle.getModifyUser()).getName():"");
				
				map.put("idcard", memberMiddle.getIdcard());
				map.put("payType", memberMiddle.getPayType());
				map.put("userName", memberMiddle.getUserName());
				map.put("isAuth", memberMiddle.getIsAuth());
				map.put("totalSum", memberMiddle.getTotalSum());
				map.put("lastSum", memberMiddle.getLastSum());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("中间人封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("中间人封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 封装集合
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午2:53:00
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList1(List<MemberMiddle> list) {
		try {
			for (MemberMiddle memberMiddle : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", memberMiddle.getId());
				map.put("idcard", memberMiddle.getIdcard());
				map.put("userName", memberMiddle.getUserName());
				map.put("sum", 0);
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("中间人封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("中间人封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 弹窗
	 * @Title: openDialog   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午2:20:05
	 * @return: void      
	 * @throws
	 */
	public String openDialog(){
		try {
			String path = getRequest().getParameter("path");
			if(ResultPath.ADD.equals(path)) {
				return ResultPath.ADD;
			}else if("selectUser".equals(path)){
				getRequest().setAttribute("scaleId", getRequest().getParameter("scaleId"));;
				return "selectUser";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("中间人Action弹窗错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("中间人Action弹窗错误："+e.getLocalizedMessage(), this.getClass());
		}
		return ResultPath.LIST;
	}
	
	/**
	 * 保存中间人
	 * @Title: add   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午2:53:47
	 * @return: void      
	 * @throws
	 */
	public void add(){
		try {
			boolean result = false;
			if(entity != null){
				//查询
				MemberInfo memberInfo = memberInfoService.findEntity("idCard", entity.getIdcard());
				if(memberInfo!= null && memberInfo.getIsAuth()==If.If1.getVal()){
					entity.setId(DataUtils.randomUUID());
					entity.setCreateTime(new Date());
					entity.setCreateUser(((SysUser)getRequest().getSession().getAttribute(SessionTools.SYSUSER)).getId());
					entity.setState(TRUE_STATE);
					entity.setIsAuth(String.valueOf(If.If1.getVal()));
					entity.setTotalSum(0);
					entity.setLastSum(0);
					result = memberMiddleService.saveEntity(entity);
					if(!result){
						jsonObject.put("success", false);
						jsonObject.put("msg", "添加失败！");
					}else{
						jsonObject.put("success", true);
						jsonObject.put("msg", "添加成功！");
					}
				}else{
					jsonObject.put("success", false);
					jsonObject.put("msg", "该账户还未认证开户！");
				}
			}else{
				jsonObject.put("success", false);
				jsonObject.put("msg", "添加的对象为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("中间人ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("中间人ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
			jsonObject.put("msg", "添加失败！");
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 禁用
	 * @Title: add   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午2:53:47
	 * @return: void      
	 * @throws
	 */
	public void updateIsUse(){
		try {
			boolean result = false;
			if(entity != null && DataUtils.notEmpty(entity.getId())){
				entity = memberMiddleService.findById(entity.getId());
				entity.setState(FALSE_STATE);
				entity.setModifyTime(new Date());
				entity.setModifyUser(((SysUser)getRequest().getSession().getAttribute(SessionTools.SYSUSER)).getId());
				result = memberMiddleService.updateEntity(entity);
				if(!result){
					jsonObject.put("success", false);
					jsonObject.put("msg", "删除失败！");
				}else{
					jsonObject.put("success", true);
					jsonObject.put("msg", "删除成功！");
				}
			}else{
				jsonObject.put("success", false);
				jsonObject.put("msg", "删除的对象为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("中间人ACTION删除错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("中间人ACTION删除错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
			jsonObject.put("msg", "删除失败！");
		} finally {
			printJsonResult();
		}
	}
}
