package org.duang.action.provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.BillInvest;
import org.duang.entity.InvestMember;
import org.duang.enums.billinvest.BillStatus;
import org.duang.enums.billinvest.UseType;
import org.duang.service.BillInvestService;
import org.duang.service.InvestMemberService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————理财账单记录Action
 * @ClassName:  BillInvestAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月9日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_billinvest")
public class BillInvestAction extends BaseAction<BillInvest>{

	private BillInvestService service;
	@Resource
	public void setService(BillInvestService service) {
		this.service = service;
	}

	private InvestMemberService investMemberService;
	@Resource(name = "sysinvestmemberserviceimpl")
	public void setService(InvestMemberService investMemberService) {
		this.investMemberService = investMemberService;
	}

	/**   
	 * 查询账单记录
	 * @Title: queryBillList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月12日 下午4:29:05
	 * @return: void      
	 * @throws   
	 */  
	public void queryBillList(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance().getMainField(token))) {
				List<BillInvest> list = service.queryEntity("memberInfo.id", id, null, Order.desc("optTime"));
				success = true;
				if (DataUtils.notEmpty(list)) {
					for (BillInvest bill : list) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", bill.getId());
						map.put("useType", UseType.valueOf("UT"+bill.getUseType()).toString());
						map.put("money", bill.getMoney());
						map.put("balance", bill.getBalance());
						map.put("asset", bill.getAsset());
						map.put("status", BillStatus.valueOf("BS"+bill.getStatus()).toString());
						map.put("optTime", DateUtils.date2Str(bill.getOptTime()));
						map.put("remark", bill.getRemark());
						listMap.add(map);
					}
				}else {
					msg = "无记录";
				}
				jsonObject.put("result", listMap);
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("BillInvestAction——queryBillList方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("BillInvestAction——queryBillList方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	/**
	 * 获取账户余额
	 * @Title: getbalance   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月10日 下午2:26:41
	 * @return: void      
	 * @throws
	 */
	public void getbalance(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance().getMainField(token))) {
				condsUtils.addProperties(true, "memberInfo");
				condsUtils.concatValue(new String[] { "infoAlias", "as" });
				condsUtils.addProperties(false, "infoAlias.id");
				condsUtils.concatValue(id);
				List<InvestMember> list = investMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
				success = true;
				if(list!=null && list.size()>0){
					jsonObject.put("balance", list.get(0).getBalance());
				}else {
					jsonObject.put("balance", 0);
				}
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("BillInvestAction——getbalance方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("BillInvestAction——getbalance方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
}
