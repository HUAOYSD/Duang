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
import org.duang.entity.SysPower;
import org.duang.service.SysPowerService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 系统用户角色Action类
 * @ClassName:  PowerAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年7月25日 下午2:50:11      
 */  
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="sysrole")
@ParentPackage("sys")
@Results(value={
		@Result(name=ResultPath.INFO,type="dispatcher",location="WEB-INF/page/test/info.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.SUCCESS,type="dispatcher",location="WEB-INF/page/test/list.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR,type="dispatcher",location="error.jsp")
})
public class SysPowerAction extends BaseAction<SysPower>{
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private SysPowerService service;
	@Resource(name="syspowerserviceimpl")
	public void setService(SysPowerService service) {
		this.service = service;
	}


}
