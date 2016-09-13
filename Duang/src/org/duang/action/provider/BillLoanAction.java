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
import org.duang.entity.BillLoan;
import org.duang.enums.billloan.BillStatus;
import org.duang.enums.billloan.UseType;
import org.duang.service.BillLoanService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————借贷账单记录Action
 * @ClassName:  BillLoanAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月9日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_billloan")
public class BillLoanAction extends BaseAction<BillLoan>{
	
	private BillLoanService service;
	
	@Resource
	public void setService(BillLoanService service) {
		this.service = service;
	}


	/**   
	 * 查询账单记录
	 * @Title: queryBillList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
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
				List<BillLoan> list = service.queryEntity("memberInfo.id", id, null, Order.desc("optTime"));
				if (DataUtils.notEmpty(list)) {
					for (BillLoan bill : list) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", bill.getId());
						map.put("useType", UseType.valueOf("UT"+bill.getStatus()).toString());
						map.put("money", bill.getMoney());
						map.put("doneMoney", bill.getDoneMoney());
						map.put("status", BillStatus.valueOf("BS"+bill.getBillStatus()).toString());
						map.put("optTime", DateUtils.date2Str(bill.getOptTime()));
						map.put("remark", bill.getRemark());
						listMap.add(map);
					}
				}else {
					msg = "无记录";
				}
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
		jsonObject.put("result", listMap);
		printJsonResult();
	}
	
}
