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
import org.duang.entity.MemberTicketRecord;
import org.duang.service.MemberTicketRecordService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 理财券使用记录Action
 * 
 * @ClassName: MemberTicketRecordAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年8月16日 下午1:55:48
 */

@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "memberticketrecord")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "recordList", type = "dispatcher", location = "WEB-INF/page/sys/memberticketrecord/memberTicketRecordList.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class MemberTicketRecordAction extends BaseAction<MemberTicketRecord> {
	private static final long serialVersionUID = 1L;
	
	private MemberTicketRecordService memberTicketRecordService;
	@Resource(name = "memberticketrecordserviceimpl")
	public void setService(MemberTicketRecordService memberTicketRecordService) {
		this.memberTicketRecordService = memberTicketRecordService;
	}

	/**
	 * 页面跳转 ---跳转到理财券使用情况列表
	 * @Title: gotoMemeberTicketRecordList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午3:37:56
	 * @return: String      
	 * @throws
	 */
	public String gotoMemeberTicketRecordList(){
		return "recordList";
	}
	
	/**
	 * 
	 * @Title: queryAllBindedMember   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月16日 上午10:21:32
	 * @return: void      
	 * @throws
	 */
	public void queryAllMemberTicketRecord() {
		try {
			List<MemberTicketRecord> list = memberTicketRecordService.queryAllEntity(getPageUtil(),Order.desc("useTime"));
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("绑定银行卡ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("绑定银行卡ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 封装理财券使用记录信息    list封装的是MemberTicketRecord对象集
	 * @Title: fillDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午3:54:10
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<MemberTicketRecord> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (MemberTicketRecord mtr : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", mtr.getId());
				map.put("useTime", DateUtils.date2Str(mtr.getUseTime()));
				
				InvestTicket investTicket = mtr.getInvestTicket();
				map.put("investTicketId", investTicket.getId());
				map.put("name", investTicket.getName());
				map.put("remark", investTicket.getRemark());
				map.put("describe", investTicket.getDescribes());
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
			LoggerUtils.error("封装理财券使用记录信息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装理财券使用记录信息错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 在用条件查询的时候，查询出来为List<Object[]>，所以需要进行封装
	 * 
	 * @Title: fillDataObjectArray
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param list
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月16日 下午3:29:33
	 * @return: List<Map<String,Object>>
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(@SuppressWarnings("rawtypes") List list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] array = (Object[]) list.get(i);
				MemberTicketRecord mtr = (MemberTicketRecord) array[1];
				map.put("id", mtr.getId());
				map.put("useTime", DateUtils.date2Str(mtr.getUseTime()));
				
				InvestTicket investTicket = (InvestTicket)array[0];
				map.put("investTicketId", investTicket.getId());
				map.put("name", investTicket.getName());
				map.put("remark", investTicket.getRemark());
				map.put("describe", investTicket.getDescribes());
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
			LoggerUtils.error("封装理财券使用记录信息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装理财券使用记录信息错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 根据条件查询理财券的消费记录
	 * @Title: queryMemberTicketRecordByPro   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午12:15:05
	 * @return: void      
	 * @throws
	 */
	public void queryMemberTicketRecordByPro() {
		try {
			condsUtils.addProperties(true, "investTicket");
			condsUtils.concatValue(new String[] { "infoAlias", "as" });
			if(DataUtils.notEmpty(entity.getInvestTicket().getName())){
				condsUtils.addProperties(false, "infoAlias.name");
				condsUtils.concatValue(new String[] { entity.getInvestTicket().getName(), "like" });
			}
			if (DataUtils.notEmpty(getRequest().getParameter("useTime"))) {
				condsUtils.concat("useTime", new Object[]{DateUtils.str2Date(getRequest().getParameter("useTime")+" 00:00:00", "yyyy-MM-dd hh:mm:ss"), DateUtils.str2Date(getRequest().getParameter("useTime")+" 59:59:59", "yyyy-MM-dd hh:mm:ss"), "between"});
			}
			
			@SuppressWarnings("rawtypes")
			List list = memberTicketRecordService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财券使用记录信息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财券使用记录信息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
}
