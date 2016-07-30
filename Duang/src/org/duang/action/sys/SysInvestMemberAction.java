package org.duang.action.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import org.duang.entity.SysPower;
import org.duang.entity.SysUser;
import org.duang.service.SysInvestMemberService;
import org.duang.service.SysInvestProService;
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
		@Result(name=ResultPath.LIST, type="dispatcher", location="WEB-INF/page/sys/invest/investMemberList.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher", location="error.jsp")
})
public class SysInvestMemberAction extends BaseAction<SysInvestMember>{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private SysInvestMemberService service;
	@Resource(name="sysinvestmemberserviceimpl")
	public void setService(SysInvestMemberService service) {
		this.service = service;
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
			
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财客户ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财客户ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
		}finally{
			printJsonResult();
		}
	}
	
}	
