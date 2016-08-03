package org.duang.action.sys;


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
import org.duang.entity.MemberInfo;
import org.duang.service.SysMemberInfoService;
import org.duang.util.ConstantCode;
import org.duang.util.DataUtils;
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
public class SysMemberInfoAction extends BaseAction<MemberInfo>{
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
		if (entity!=null && DataUtils.notEmpty(entity.getId())) {
			try {
				String sql = "update member_info SET is_freeze="+entity.getIsFreeze()+" where id="+entity.getId();
				boolean issuccess = sysMemberInfoService.executeSql(sql);
				if (issuccess) {
					jsonObject.put("result",true);
					if(ConstantCode.FREEZE.equals(entity.getIsFreeze())){
						jsonObject.put("msg","冻结成功");
					}else{
						jsonObject.put("msg","解冻成功");
					}
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
	
	public void deleteMemberInfo(){
		String id  = getRequest().getParameter("id");
		if (DataUtils.notEmpty(id)) {
			try {
				String sql = "update member_info SET isdelete=1 where id="+id;
				boolean issuccess = sysMemberInfoService.executeSql(sql);
				if (issuccess) {
					jsonObject.put("result",true);
					jsonObject.put("msg","删除成功");
					printJsonResult();
				}else{
					jsonObject.put("result",false);
					jsonObject.put("msg","删除失败，请联系管理员");
					printJsonResult();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("理财用户ACTION删除错误："+e.getMessage(), this.getClass());
				LoggerUtils.error("理财用户ACTION删除错误："+e.getLocalizedMessage(), this.getClass());
			}
		}
	}
}	
