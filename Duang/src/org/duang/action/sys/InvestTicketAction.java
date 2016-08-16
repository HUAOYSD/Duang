package org.duang.action.sys;
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
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.InvestTicket;
import org.duang.service.InvestTicketService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 理财券管理Action
 * 
 * @ClassName: InvestTicketAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年8月16日 下午4:55:48
 */

@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "investticket")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "investticketlist", type = "dispatcher", location = "WEB-INF/page/sys/investticket/investTicketList.jsp"),
			@Result(name = "uploadBindCardImg", type = "dispatcher", location = "WEB-INF/page/sys/bindcard/uploadBindCardImg.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class InvestTicketAction extends BaseAction<InvestTicket> {
	private static final long serialVersionUID = 1L;
	
	private InvestTicketService investTicketService;
	@Resource(name = "investticketserviceimpl")
	public void setService(InvestTicketService investTicketService) {
		this.investTicketService = investTicketService;
	}

	/**
	 * 页面跳转 ---跳转到理财券的管理页面
	 * @Title: gotoInvestTicketList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午4:37:56
	 * @return: String      
	 * @throws
	 */
	public String gotoInvestTicketList(){
		return "investticketlist";
	}
	
	public void queryAllInvestTicket() {
		try {
			List<InvestTicket> list = investTicketService.queryAllEntity(Order.asc("createTime"));
			int count = investTicketService.count();
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", count);
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财券ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财券ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	/**
	 * 封装理财券信息    list封装的是InvestTicket对象集
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午3:54:10
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<InvestTicket> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (InvestTicket investTicket : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("investTicketId", investTicket.getId());
				map.put("name", investTicket.getName());
				map.put("remark", investTicket.getRemark());
				map.put("describe", investTicket.getDescribe());
				map.put("money", investTicket.getMoney());
				map.put("beginTime", DateUtils.date2Str(investTicket.getBeginTime()));
				map.put("endTime", DateUtils.date2Str(investTicket.getEndTime()));
				map.put("createTime", DateUtils.date2Str(investTicket.getCreateTime()));
				map.put("minMoney", investTicket.getMinMoney());
				map.put("productIds", investTicket.getProductIds());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装理财券信息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装理财券信息错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 根据参数查询绑定的银行卡信息
	 * @Title: queryBandCardByParameter   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月15日 下午3:24:25
	 * @return: void      
	 * @throws
	 */
	public void queryBandCardByParameter() {
		try {
			//封装参数
			if (DataUtils.notEmpty(entity.getName())) {
				condsUtils.addProperties(false, "name");
				condsUtils.concatValue(new String[] { entity.getName(), "like" });
			}
			//查询数据
			List<InvestTicket> list = investTicketService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = investTicketService.count(condsUtils.getPropertys(), condsUtils.getValues());
			//封装json
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				//fillDataObjectArray方法用于重新组合数据集，让其能够符合页面展示
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", count);
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("根据条件查询理财券ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("根据条件查询理财券ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			//输出json
			printJsonResult();
		}
	}
}
