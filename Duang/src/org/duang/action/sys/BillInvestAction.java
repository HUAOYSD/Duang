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
import org.duang.entity.BindCard;
import org.duang.entity.InvestList;
import org.duang.entity.InvestMember;
import org.duang.entity.InvestTicket;
import org.duang.entity.BillInvest;
import org.duang.entity.MemberInfo;
import org.duang.enums.If;
import org.duang.enums.Platform;
import org.duang.enums.invest.Status;
import org.duang.enums.invest.TurnStatus;
import org.duang.enums.invest.UseTicket;
import org.duang.service.BillInvestService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 理财资金记录Action
 * 
 * @ClassName: BillInvestAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年8月23日 下午2:55:48
 */

@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "billinvest")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/billinvest/billInvestList.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class BillInvestAction extends BaseAction<BillInvest> {
	private static final long serialVersionUID = 1L;
	
	private BillInvestService billInvestService;
	@Resource(name = "billinvestserviceimpl")
	public void setService(BillInvestService billInvestService) {
		this.billInvestService = billInvestService;
	}

	/**
	 * 页面跳转 ---列表
	 * @Title: gotoMemeberTicketRecordList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午3:37:56
	 * @return: String      
	 * @throws
	 */
	public String gotoList(){
		return "list";
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
	public void queryAllBillInvest() {
		try {
			List<BillInvest> list = billInvestService.queryAllEntity(Order.desc("optTime"));
			int count = billInvestService.count();
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
			LoggerUtils.error("理财资金记录ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财资金记录ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 封装理财券使用记录信息    list封装的是BillInvest对象集
	 * @Title: fillDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午3:54:10
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<BillInvest> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (BillInvest billInvest : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", billInvest.getId());
				map.put("useType", billInvest.getUseType());
				map.put("money", billInvest.getMoney());
				map.put("balance", billInvest.getBalance());
				map.put("asset", billInvest.getAsset());
				map.put("status", billInvest.getStatus());
				map.put("remark", billInvest.getRemark());
				map.put("style", billInvest.getStyle());
				map.put("optTime", DateUtils.date2Str(billInvest.getOptTime()));
				
				InvestList pk = billInvest.getInvestList();//理财记录，只有消费、手续费、赎回、收益、手续费的时候用到这个字段
				map.put("pkId", pk.getId());
				map.put("money", pk.getMoney());
				map.put("yetMoney", pk.getYetMoney());
				map.put("spaceMoney", pk.getSpaceMoney());
				map.put("backIncome", pk.getBackIncome());
				map.put("backMoney", pk.getBackMoney());
				map.put("useTicket", UseTicket.valueOf("UT"+pk.getUseTicket()).toString());
				map.put("expectIncome", pk.getExpectIncome());
				map.put("totalMoney", pk.getTotalMoney());
				map.put("income", pk.getIncome());
				map.put("ticketBonus", pk.getTicketBonus());
				map.put("status", Status.valueOf("S"+pk.getStatus()).toString());
				map.put("openDate", DateUtils.getTimeStamp(pk.getOpenDate()));
				map.put("backDate", DateUtils.getTimeStamp(pk.getBackDate()));
				map.put("calcBeginDate", DateUtils.getTimeStamp(pk.getBackDate()));
				map.put("calcEndDate", DateUtils.getTimeStamp(pk.getBackDate()));
				map.put("pactNumber", pk.getPactNumber());
				map.put("investStyle", Platform.valueOf("P"+pk.getInvestStyle()).toString());
				map.put("poundageTurn", pk.getPoundageTurn());
				map.put("poundagePrivilege", pk.getPoundagePrivilege());
				map.put("isTurn", If.valueOf("If"+pk.getIsTurn()).toString());
				map.put("turnStatus", TurnStatus.valueOf("TS"+pk.getTurnStatus()).toString());
				
				MemberInfo memberInfo = billInvest.getMemberInfo();
				map.put("realName", memberInfo.getRealName());
				map.put("nickname", memberInfo.getNickname());
				map.put("email", memberInfo.getEmail());
				map.put("sex", memberInfo.getSex());
				map.put("phone", memberInfo.getPhone());
				
				BindCard bindCard = billInvest.getBindCard();
				map.put("idcard", bindCard.getIdcard());
				map.put("bankNo", bindCard.getBankNo());
				map.put("openBank", bindCard.getOpenBank());
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
				BillInvest mtr = (BillInvest) array[1];
				
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
	 * 根据条件查询记录
	 * @Title: queryByPar   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午12:15:05
	 * @return: void      
	 * @throws
	 */
	public void queryByPar() {
		try {
			/*condsUtils.addProperties(true, "investTicket");
			condsUtils.concatValue(new String[] { "infoAlias", "as" });
			if(DataUtils.notEmpty(entity.getInvestTicket().getName())){
				condsUtils.addProperties(false, "infoAlias.name");
				condsUtils.concatValue(new String[] { entity.getInvestTicket().getName(), "like" });
			}
			if (DataUtils.notEmpty(getRequest().getParameter("useTime"))) {
				condsUtils.concat("useTime", new Object[]{DateUtils.str2Date(getRequest().getParameter("useTime")+" 00:00:00", "yyyy-MM-dd hh:mm:ss"), DateUtils.str2Date(getRequest().getParameter("useTime")+" 59:59:59", "yyyy-MM-dd hh:mm:ss"), "between"});
			}*/
			
			@SuppressWarnings("rawtypes")
			List list = billInvestService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = billInvestService.count(condsUtils.getPropertys(), condsUtils.getValues());
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", count);
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
