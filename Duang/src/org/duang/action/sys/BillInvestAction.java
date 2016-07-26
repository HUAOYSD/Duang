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
import org.duang.entity.BillInvest;
import org.duang.entity.BindCard;
import org.duang.entity.MemberInfo;
import org.duang.entity.Scale;
import org.duang.service.BillInvestService;
import org.duang.util.ConstantCode;
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
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", list.size());
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
				Scale pk = billInvest.getInvestList().getScale();//理财记录，只有消费、手续费、赎回、收益、手续费的时候用到这个字段
				map.put("scaleName", pk.getName());
				MemberInfo memberInfo = billInvest.getMemberInfo();
				map.put("realName", memberInfo.getRealName());
				map.put("nickname", memberInfo.getNickname());
				map.put("email", memberInfo.getEmail());
				map.put("sex", memberInfo.getSex());
				map.put("phone", memberInfo.getPhone());
				
				BindCard bindCard = billInvest.getBindCard();
				map.put("bankNo", bindCard.getBankNo());
				map.put("openBank", bindCard.getOpenBank());
				map.put("type", bindCard.getType());
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
				BillInvest billInvest = (BillInvest) array[3];
				map.put("id", billInvest.getId());
				map.put("useType", billInvest.getUseType());
				map.put("money", billInvest.getMoney());
				map.put("balance", billInvest.getBalance());
				map.put("asset", billInvest.getAsset());
				map.put("status", billInvest.getStatus());
				map.put("remark", billInvest.getRemark());
				map.put("style", billInvest.getStyle());
				map.put("optTime", DateUtils.date2Str(billInvest.getOptTime()));
				Scale pk = billInvest.getInvestList().getScale();//理财记录，只有消费、手续费、赎回、收益、手续费的时候用到这个字段
				map.put("scaleName", pk.getName());
				MemberInfo memberInfo = billInvest.getMemberInfo();
				map.put("realName", memberInfo.getRealName());
				map.put("nickname", memberInfo.getNickname());
				map.put("email", memberInfo.getEmail());
				map.put("sex", memberInfo.getSex());
				map.put("phone", memberInfo.getPhone());
				
				BindCard bindCard = billInvest.getBindCard();
				map.put("bankNo", bindCard.getBankNo());
				map.put("openBank", bindCard.getOpenBank());
				map.put("type", bindCard.getType());
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
			condsUtils.addProperties(true, "memberInfo", "investList", "asil.scale", "order");
			condsUtils.addValues(true, new String[] { "mi", "as" }, new String[] { "asil", "as" }, new String[] { "asscale", "as" }, Order.desc("optTime"));
			if(DataUtils.notEmpty(entity.getMemberInfo().getRealName())){
				condsUtils.addProperties(false, "mi.realName");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getRealName(), "like" });
			}
			if(DataUtils.notEmpty(entity.getMemberInfo().getPhone())){
				condsUtils.addProperties(false, "mi.phone");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getPhone(), "like" });
			}
			
			if(DataUtils.notEmpty(entity.getInvestList().getScale().getName())){
				condsUtils.addProperties(false, "asscale.name");
				condsUtils.concatValue(new String[] { entity.getInvestList().getScale().getName(), "like" });
			}
			
			if(entity.getStatus()!=ConstantCode.NOSELECTED){
				condsUtils.addProperties(false, "status");
				condsUtils.addValues(false, entity.getStatus());
			}
			if(entity.getUseType()!=ConstantCode.NOSELECTED){
				condsUtils.addProperties(false, "useType");
				condsUtils.addValues(false, entity.getUseType());
			}
			
			if (DataUtils.notEmpty(getRequest().getParameter("optTime"))) {
				condsUtils.concat("optTime", new Object[]{DateUtils.str2Date(getRequest().getParameter("optTime")+" 00:00:00", "yyyy-MM-dd hh:mm:ss"), DateUtils.str2Date(getRequest().getParameter("optTime")+" 59:59:59", "yyyy-MM-dd hh:mm:ss"), "between"});
			}
			
			@SuppressWarnings("rawtypes")
			List list = billInvestService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
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
