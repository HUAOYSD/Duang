package org.duang.action.sys;
import java.util.List;

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
import org.duang.entity.SysUser;
import org.duang.service.SysUserService;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**
 * 系统用户控制类 
 * @ClassName: EnglishAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 白攀
 * @date 2016-7-17 下午2:07:35 
 */ 
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="sysseraction")
@ParentPackage("sys")
@Results(value={
		@Result(name="success",type="dispatcher",location="WEB-INF/page/test/list.jsp"),
		@Result(name="info",type="dispatcher",location="WEB-INF/page/test/info.jsp"),
		@Result(name="error",type="dispatcher",location="error.jsp")
})
public class SysUserAction extends BaseAction<SysUser>{

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 8766958805913002975L;

	private SysUserService sysUserService;

	@Resource(name="sysuserserviceimpl")
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}


	/**   
	 * 查询全部或根据条件查询
	 * @Title: queryList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月18日 上午10:13:44
	 * @return: String 返回路径      
	 * @throws   
	 */  
	public String queryList(){
		//如果有分页的话，用的是easyuiui,可以直接,这不演示了
		//List<SysUser> list = sysUserService.queryEntity("name", "张三", super.getPageUtil());
		String searchName = super.getRequest().getParameter("search_name");
		String searchEmail = super.getRequest().getParameter("search_email");
		if (DataUtils.notEmpty(searchName)) {//示例like
			super.condsUtils.addProperties(false, "name");
			super.condsUtils.addValues(false, searchName);
		}
		if (DataUtils.notEmpty(searchEmail)) {//标准eq
			super.condsUtils.addProperties(false, "email");
			super.condsUtils.concatValue(new Object[]{searchEmail,"like"});
		}
		List<SysUser> list = null;
		try {
			//null表示不分页
			list = sysUserService.queryEntity(super.condsUtils.getPropertys(), super.condsUtils.getValues(), null);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统用户ACTION查询错误："+e.getMessage());
			LoggerUtils.error("系统用户ACTION查询错误："+e.getLocalizedMessage());
			super.msg = "系统用户ACTION查询错误："+e.getMessage();
			return ERROR;
		}
		super.getRequest().setAttribute("sysuserlist", list);
		return SUCCESS;
	}


	/**   
	 * 根据id查找
	 * @Title: findSysUser   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月18日 上午10:43:24
	 * @return: String      
	 * @throws   
	 */  
	public String findSysUser() {
		String id = super.getRequest().getParameter("id");
		if (DataUtils.notEmpty(id)) {
			try {
				super.entity = sysUserService.findById(id);
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("系统用户ACTION查询错误："+e.getMessage());
				LoggerUtils.error("系统用户ACTION查询错误："+e.getLocalizedMessage());
				super.msg = "系统用户ACTION查询错误："+e.getMessage();
				return ERROR;
			}
		}
		return ResultPath.INFO;
	}


	/**   
	 * 增加
	 * @Title: saveSysUser   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月18日 上午10:55:49
	 * @return: String      
	 * @throws   
	 */  
	public String saveSysUser(){
		List<SysUser> list = null;
		if (entity!=null && DataUtils.notEmpty(entity.getName())) {
			try {
				boolean issuccess = sysUserService.saveEntity(entity);
				if (issuccess) {
					list = sysUserService.queryEntity(super.condsUtils.getPropertys(), super.condsUtils.getValues(), null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("系统用户ACTION增加错误："+e.getMessage());
				LoggerUtils.error("系统用户ACTION增加错误："+e.getLocalizedMessage());
				super.msg = "系统用户ACTION增加错误："+e.getMessage();
				return ERROR;
			}
		}
		super.getRequest().setAttribute("sysuserlist", list);
		return SUCCESS;
	}



	/**   
	 * 删除
	 * @Title: deleteSysUser   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月18日 上午10:59:45
	 * @return: String      
	 * @throws   
	 */  
	public String deleteSysUser(){
		List<SysUser> list = null;
		if (entity!=null && DataUtils.notEmpty(entity.getId())) {
			try {
				boolean issuccess = sysUserService.deleteEntity(entity);
				if (issuccess) {
					list = sysUserService.queryEntity(super.condsUtils.getPropertys(), super.condsUtils.getValues(), null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("系统用户ACTION增加错误："+e.getMessage());
				LoggerUtils.error("系统用户ACTION增加错误："+e.getLocalizedMessage());
				super.msg = "系统用户ACTION增加错误："+e.getMessage();
				return ERROR;
			}
		}
		super.getRequest().setAttribute("sysuserlist", list);
		return SUCCESS;
	}
}
