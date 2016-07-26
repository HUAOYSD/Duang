package org.duang.action.sys;
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
import org.duang.entity.SysPower;
import org.duang.service.SysPowerService;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 系统用户权限Action类
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


	/**   
	 * 角色分配权限时所用的树形多选框
	 * @Title: getPowerTreeCheckbox   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年7月26日 上午11:22:53
	 * @return: void      
	 * @throws   
	 */  
	public void getPowerTreeCheckbox() {
		JSONArray jsonArray = new JSONArray();
		try {
			//得到最顶级权限
			List<SysPower> list = service.queryEntity("parentId", "0", null, Order.asc("sortIndex"));
			if(list != null && list.size() >0) {
				for(SysPower top :list) {
					Map<String,Object> map = new HashMap<String,Object> ();
					map.put("id", top.getId());
					map.put("text", top.getName());
					//map.put("state", "closed");
					map.put("checkbox", true);
					map.put("children", makeTreePowerCheckbox(top.getId()));
					jsonArray.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("系统权限ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("系统权限ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult(jsonArray);
		}
	}


	/**   
	 * 递归得到复选框权限树
	 * @Title: makeTreePowerCheckbox   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param powerId
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月26日 下午12:57:09
	 * @return: JSONArray      
	 * @throws   
	 */  
	public JSONArray makeTreePowerCheckbox(String powerId) throws Exception {
		List<SysPower> list = service.queryEntity("parentId", powerId, null, Order.asc("sortIndex"));
		JSONArray jsonArray = new JSONArray();
		if(list != null && list.size() > 0) {
			for(SysPower power : list) {
				Map<String,Object> map = new HashMap<String,Object> ();
				map.put("id", power.getId());
				map.put("text", power.getName());
				//map.put("state", "closed");
				map.put("children", makeTreePowerCheckbox(power.getId()));
				jsonArray.add(map);
			}
		}
		return jsonArray;
	}

}
