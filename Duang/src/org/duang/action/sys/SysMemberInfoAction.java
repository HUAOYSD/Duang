package org.duang.action.sys;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
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
import org.duang.entity.SysMemberInfo;
import org.duang.service.SysMemberInfoService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
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
@Action(value="memberinfo")
@ParentPackage("sys")
@Results(value={
		@Result(name=ResultPath.LIST, type="dispatcher", location="WEB-INF/page/sys/investmember/investMemberList.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher", location="error.jsp")
})
public class SysMemberInfoAction extends BaseAction<SysMemberInfo>{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private SysMemberInfoService sysMemberInfoService;
	@Resource(name="sysmemberinfoserviceimpl")
	public void setService(SysMemberInfoService sysMemberInfoService) {
		this.sysMemberInfoService = sysMemberInfoService;
	}
	
	/**
	 * @Title: freezeMemberInfo   
	 * @Description: TODO(冻结或者解冻操作)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月2日 下午3:51:03
	 * @return: void      
	 * @throws
	 */
	public void freezeMemberInfo(){
		if (entity!=null && DataUtils.notEmpty(entity.getMemberInfo_id())) {
			try {
				String is_freeze = entity.getIs_freeze();
				entity = sysMemberInfoService.findById(entity.getMemberInfo_id());
				entity.setIs_freeze(is_freeze);
				boolean issuccess = sysMemberInfoService.updateEntity(entity);
				if (issuccess) {
					jsonObject.put("result",true);
					jsonObject.put("msg","解冻或者冻结理财用户失败成功");
					printJsonResult();
				}else{
					jsonObject.put("result",false);
					jsonObject.put("msg","解冻或者冻结理财用户失败，请联系管理员");
					printJsonResult();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("理财用户ACTION冻结或者解冻操作错误："+e.getMessage(), this.getClass());
				LoggerUtils.error("理财用户ACTION冻结或者解冻操作错误："+e.getLocalizedMessage(), this.getClass());
			}
		}
	}
}	
