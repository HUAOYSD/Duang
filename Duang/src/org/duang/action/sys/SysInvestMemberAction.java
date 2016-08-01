package org.duang.action.sys;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.SysInvestMember;
import org.duang.entity.SysInvestProduct;
import org.duang.entity.SysMemberInfo;
import org.duang.service.SysInvestMemberService;
import org.duang.service.SysMemberInfoService;
import org.duang.util.DataUtils;
import org.duang.util.PageUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 理财用户Action
 * @ClassName:  SysInvestMemberAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月26日 下午1:55:48
 */
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="investmember")
@ParentPackage("sys")
@Results(value={
		@Result(name=ResultPath.LIST, type="dispatcher", location="WEB-INF/page/sys/investmember/investMemberList.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher", location="error.jsp")
})
public class SysInvestMemberAction extends BaseAction<SysInvestMember>{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private SysInvestMemberService investMemberService;
	@Resource(name="sysinvestmemberserviceimpl")
	public void setService(SysInvestMemberService investMemberService) {
		this.investMemberService = investMemberService;
	}
	private SysMemberInfoService memeberInfoService;
	@Resource(name="sysmemberinfoserviceimpl")
	public void setService(SysMemberInfoService memeberInfoService) {
		this.memeberInfoService = memeberInfoService;
	}
	/**
	 * 跳转到理财客户页面
	 * @Title: investMemberList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年7月30日 下午11:18:08
	 * @return: String      
	 * @throws
	 */
	public String investMemberList(){
		return ResultPath.LIST;
	}
	
	/**
	 * 查询所有的客户，并用json方式返回
	 * @Title: queryInvestMember   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年7月30日 下午2:27:37
	 * @return: void      
	 * @throws
	 */
	public void  queryInvestMember(){
		List<SysInvestMember> list = null;
		try {
		/*	String name = super.getRequest().getParameter("name");
			String real_name = super.getRequest().getParameter("real_name");
			String phone = super.getRequest().getParameter("phone");
			String cust_manager_id = super.getRequest().getParameter("cust_manager_id");
			String manager_name = super.getRequest().getParameter("manager_name");
			
			String type = super.getRequest().getParameter("type");
			String is_contract = super.getRequest().getParameter("is_contract");
			
			PageUtil<SysInvestMember> page = new PageUtil<SysInvestMember>();
			String pageRecords = super.getRequest().getParameter("rows");
			String currentPageNum = super.getRequest().getParameter("page");
			
			if(DataUtils.notEmpty(pageRecords)){
				page.setPageRecords(Integer.parseInt(pageRecords));
			}
			if(DataUtils.notEmpty(currentPageNum)){
				page.setCurrentPageNum(Integer.parseInt(currentPageNum));
			}
			SysMemberInfo memberInfo = new SysMemberInfo();
			memberInfo.setIsdelete(0);
			memberInfo.setName(name);
			memberInfo.setType(type);
			memberInfo.setReal_name(real_name);
			memberInfo.setPhone(phone);
			if(memberInfo!=null){
				condsUtils.addProperties(false, "memberinfo_id");
				condsUtils.addValues(false,memberInfo);
			}
			if(DataUtils.notEmpty(cust_manager_id)){
				condsUtils.addProperties(false, "cust_manager_id");
				condsUtils.addValues(false,cust_manager_id);
			}
			if(DataUtils.notEmpty(manager_name)){
				condsUtils.addProperties(false, "manager_name");
				condsUtils.addValues(false,manager_name);
			}
			if(DataUtils.notEmpty(is_contract)){
				condsUtils.addProperties(false, "is_contract");
				condsUtils.addValues(false,is_contract);
			}*/
			list = investMemberService.queryAllEntity(null);
			int count = investMemberService.count();
			if(list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObject(list));
				jsonObject.put("total", count);
				jsonObject.put("currPage", getPageUtil().getCurrentPageNum());
				jsonObject.put("pageSize",getPageUtil().getPageRecords());
			}else{
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("currPage", 1);
				jsonObject.put("pageSize",0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财客户类型ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财客户类型ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
		}finally{
			printJsonResult();
		}
	}
	
	public List<Map<String,Object>> fillDataObject(List<SysInvestMember> list){
		List<Map<String,Object>> listMap =  new ArrayList<Map<String,Object>>();
		try{
			for(SysInvestMember im : list){
				Map<String,Object> map = new HashMap<String,Object>();
				/*map.put("bank", im.getBank());
				map.put("bank_card", im.getBank_card());
				map.put("cust_manager_id()", im.getCust_manager_id());
				map.put("id", im.getId());
				map.put("idCard()", im.getIdCard());
				map.put("idcard_img1()", im.getIdcard_img1());
				map.put("idcard_img2()", im.getIdcard_img2());
				map.put("is_contract()", im.getIs_contract());
				map.put("manager_name()", im.getManager_name());
				map.put("user_image()", im.getUser_image());*/
				listMap.add(getObjectProValue(map,im));
				SysMemberInfo memberInfo = memeberInfoService.findById(im.getMemberinfo_ids());
				listMap.add(getObjectProValue(map,memberInfo));
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("封装理财用户错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("封装理财用户错误："+e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	public Map<String,Object> getObjectProValue(Map<String,Object> map,Object obj) throws  Exception{
		Field[] field = obj.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
	    for (int j = 0; j < field.length; j++) { // 遍历所有属性
            String name = field[j].getName(); // 获取属性的名字
            if(name.equals("serialVersionUID")){
            	continue;
            }
            String bName = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set
            Method m = obj.getClass().getMethod("get" + bName);
            map.put(name, m.invoke(obj));
	    }   
	    return map;
	} 
}	
