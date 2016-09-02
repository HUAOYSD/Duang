package org.duang.action.sys;

import java.net.URLDecoder;
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
import org.duang.entity.InvestList;
import org.duang.entity.LoanList;
import org.duang.entity.MemberInfo;
import org.duang.entity.Scale;
import org.duang.entity.Stock;
import org.duang.enums.If;
import org.duang.enums.stock.Status;
import org.duang.service.StockService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**   
 * 借贷理财库存记录Action
 * @ClassName:  StockAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年8月10日 下午5:21:02      
 */  
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "stock")
@ParentPackage("sys")
@Results(value = { 
		@Result(name = ResultPath.LIST, type = "dispatcher", location = "WEB-INF/page/sys/stock/stockList.jsp"),
		@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") 
})
public class StockAction extends BaseAction<Stock> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private StockService service;

	@Resource()
	public void setService(StockService service) {
		this.service = service;
	}
	


	/**   
	 * 根据分页查询
	 * @Title: queryCustomerManagerByPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月9日 上午10:30:46
	 * @return: void      
	 * @throws   
	 */  
	public void queryByPage() {
		try {
			condsUtils.addProperties(true, "scale", "loanList", "investList", "loanAlias.memberInfo", "investAlis.memberInfo", "order");
			condsUtils.addValues(true, new Object[]{"scaleAlias","asleft"}, new Object[]{"loanAlias","asleft"}, new Object[]{"investAlis","asleft"}, new Object[]{"loanMemberAlis","asleft"}, new Object[]{"investMemberAlis","asleft"}, Order.desc("createTime"));
			if (DataUtils.notEmpty(getRequest().getParameter("loanname"))) {
				condsUtils.concat("loanMemberAlis.realName", URLDecoder.decode(getRequest().getParameter("loanname"),"UTF-8"));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("loanphone"))) {
				condsUtils.concat("loanMemberAlis.phone", URLDecoder.decode(getRequest().getParameter("loanphone"),"UTF-8"));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("investname"))) {
				condsUtils.concat("investMemberAlis.realName", URLDecoder.decode(getRequest().getParameter("investname"),"UTF-8"));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("investphone"))) {
				condsUtils.concat("investMemberAlis.phone", URLDecoder.decode(getRequest().getParameter("investphone"),"UTF-8"));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("status"))) {
				condsUtils.concat("status",Integer.parseInt(getRequest().getParameter("status")));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("istrans"))) {
				condsUtils.concat("isTurn", Integer.parseInt(getRequest().getParameter("istrans")));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("scalename"))) {
				condsUtils.concat("scaleAlias.name", new String[]{URLDecoder.decode(getRequest().getParameter("scalename"),"UTF-8"), "like"});
			}
			@SuppressWarnings("rawtypes")
			List list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			fillDatagridCons(list);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷理财库存记录ACTION方法queryByPage错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("借贷理财库存记录ACTION方法queryByPage错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("total", 0);
			jsonObject.put("currPage", 0);
			jsonObject.put("pageSize", 0);
			jsonObject.put("rows",new JSONArray());
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 填充List数据
	 * @Title: fillDatagridCons   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list  
	 * @author 白攀    
	 * @date 2016年7月25日 下午5:08:47
	 * @return: void      
	 * @throws   
	 */  
	private void fillDatagridCons(@SuppressWarnings("rawtypes") List list) throws Exception {
		if (list !=null && list.size() > 0) {
			for (Object obj : list) {
				if (obj instanceof Object[]) {
					InvestList investList = (InvestList)((Object[])obj)[0];
					MemberInfo investMember = (MemberInfo)((Object[])obj)[1];
					LoanList loanList = (LoanList)((Object[])obj)[2];
					MemberInfo loanMember = (MemberInfo)((Object[])obj)[3];
					Scale scale = (Scale)((Object[])obj)[4];
					Stock stock = (Stock)((Object[])obj)[5];
					Map<String,Object> resultMap = new HashMap<String,Object>();
					
					if(stock != null){
						resultMap.put("id", stock.getId());
						resultMap.put("money", stock.getMoney());
						resultMap.put("fetch", stock.getFetch());
						resultMap.put("difference", stock.getDifference());
						resultMap.put("status", Status.valueOf("S"+stock.getStatus()).toString());
						resultMap.put("isTurn", If.valueOf("If"+stock.getIsTurn()).toString());
						resultMap.put("createTime", DateUtils.getTimeStamp(stock.getCreateTime()));
						resultMap.put("fetchTime", DateUtils.getTimeStamp(stock.getFetchTime()));
					}
					if(loanMember != null){
						resultMap.put("loanPersonName", loanMember.getRealName());
						resultMap.put("loanPersonPhone", loanMember.getPhone());
					}
					if(scale != null){
						resultMap.put("scaleName", scale.getName());
					}
					if(loanList != null){
						resultMap.put("pactNumberLoan", loanList.getPactNumber());
					}
					
					if(investList!=null){
						resultMap.put("pactNumberInvest", investList.getPactNumber());
					}
					if(investMember !=null){
						resultMap.put("investPersonName", investMember.getRealName());
						resultMap.put("investPersonPhone", investMember.getPhone());
					}
					
					listMap.add(resultMap);
				}
			}
			jsonObject.put("total", getPageUtil().getCountRecords());
			jsonObject.put("currPage", getPageUtil().getCurrentPageNum());
			jsonObject.put("pageSize", getPageUtil().getPageRecords());
			jsonObject.put("rows", listMap);
		}else {
			jsonObject.put("total", 0);
			jsonObject.put("currPage", 0);
			jsonObject.put("pageSize", 0);
			jsonObject.put("rows",new JSONArray());
		}
	}


	/**   
	 * 页面跳转
	 * @Title: openDialog   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年8月11日 下午5:30:38
	 * @return: String      
	 * @throws   
	 */  
	public String openDialog() {
		try {
			@SuppressWarnings("unused")
			String path = getRequest().getParameter("path");
			//System.out.println(path);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷理财库存记录ACTION方法openDialog错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("借贷理财库存记录ACTION方法openDialog错误："+e.getLocalizedMessage(), this.getClass());
		}
		return ResultPath.LIST;
	}


}
