package org.duang.action.provider;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.service.MemberInfoService;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————交易数据Action
 * @ClassName:  TransactionAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_transaction")
public class TransactionAction extends BaseAction<Object>{

private static final long serialVersionUID = 1L;
	
	private MemberInfoService sysMemberInfoService;
	@Resource(name="sysmemberinfoserviceimpl")
	public void setService(MemberInfoService sysMemberInfoService) {
		this.sysMemberInfoService = sysMemberInfoService;
	}
	
	/**   
	 * 信用借贷获获取数据
	 * @Title: getLoanAndInvestData   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月7日 下午4:44:27
	 * @return: void      
	 * @throws 
	 */  
	public void getLoanAndInvestData(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = "";
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token).getMainField(token))){
				Map<String, Object> map = sysMemberInfoService.queryLoanAndInvestInfo(id);
				jsonObject.put("result", map);
				success = true;
			}else{
				msg = "token不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("TransactionAction——getLoanAndInvestData方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("TransactionAction——getLoanAndInvestData方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 信用借款
	 * @Title: getHonorLoanAndInvestData   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月2日 上午9:51:52
	 * @return: void      
	 * @throws
	 */
	public void getHonorLoanAndInvestData(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = "";
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token).getMainField(token))){
				Map<String, Object> map = sysMemberInfoService.queryLoanAndInvestInfo(id);
				jsonObject.put("result", map);
				success = true;
			}else{
				msg = "token不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("TransactionAction——getLoanAndInvestData方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("TransactionAction——getLoanAndInvestData方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
}
