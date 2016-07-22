package org.duang.action.sys;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.opensymphony.xwork2.ActionSupport;

/**   
 * 专用于跳转的action
 * @ClassName:  JumpAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年7月18日 下午10:05:36      
 */  
@Scope(value="singleton", proxyMode=ScopedProxyMode.NO)
@InterceptorRef("defaultStack")
@Namespaces(@Namespace("/"))
@Action(value="jumpaction", results={
		@Result(name="testAdd",type="dispatcher",location="WEB-INF/page/test/add.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR,type="dispatcher",location="error.jsp")
})
public class JumpAction extends ActionSupport{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1528753327964698159L;

	/**   
	 * 去测试的添加页面
	 * @Title: toTestAdd   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月18日 下午10:06:46
	 * @return: String      
	 * @throws   
	 */  
	public String toTestAdd(){
		return "testAdd";
	}

}
