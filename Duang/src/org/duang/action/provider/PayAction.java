package org.duang.action.provider;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.BillInvest;
import org.duang.entity.BindCard;
import org.duang.entity.InvestMember;
import org.duang.entity.MemberInfo;
import org.duang.enums.billinvest.BillStatus;
import org.duang.enums.billinvest.UseType;
import org.duang.service.BillInvestService;
import org.duang.service.InvestMemberService;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————支付Action
 * @ClassName:  PayAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_wallet")
public class PayAction extends BaseAction<BillInvest>{


	private BillInvestService service;
	private InvestMemberService investMemberService;
	@Resource
	public void setService(BillInvestService service) {
		this.service = service;
	}
	@Resource
	public void setInvestMemberService(InvestMemberService investMemberService) {
		this.investMemberService = investMemberService;
	}

	/**   
	 * 充值 <成功回调> 
	 * @Title: deposit   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午3:39:54
	 * @return: void      
	 * @throws   
	 */  
	public void deposit(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String pushMany = getRequest().getParameter("p_pushMany");
			String bankid = getRequest().getParameter("p_bankid");
			String platform = getRequest().getParameter("p_platform");
			String pk = DataUtils.randomUUID();
			String id = "";
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(platform) && DataUtils.notEmpty((id = MemberCollection.getInstance().getMainField(token)))) {
				double pushManyVal = DataUtils.str2double(pushMany, 6);
				if (pushManyVal > 0) {
					entity = new BillInvest(pk, new MemberInfo(id), null, new BindCard(bankid), UseType.UT1.getVal(), pushManyVal, 0, 0, BillStatus.BS1.getVal(), new Date(), "充值", DataUtils.str2int(platform));
					if (service.saveEntity(entity)) {
						jsonObject.put("id", pk);
						success = true;
					}else {
						msg = "添加失败";
					}
				}else{
					msg = "充值金额需为正数";
				}
			}else{
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("PayAction——deposit方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("PayAction——deposit方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 充值成功回调
	 * @Title: depositCallBack   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月23日 上午11:23:30
	 * @return: void      
	 * @throws   
	 */  
	public void depositCallBack(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String pkid = getRequest().getParameter("p_id");
			String id = "";
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty((id = MemberCollection.getInstance().getMainField(token)))) {
				final InvestMember investMember = investMemberService.findEntity("memberInfo.id", id);
				String sql = "SELECT BALANCE,ASSET  FROM BILL_INVEST WHERE MEMBER_INFO = '"+id+"' AND STATUS = "+BillStatus.BS2.getVal()+" ORDER BY OPT_TIME DESC";
				List<?> list = service.queryBySQL(sql, null, null, false);
				if (investMember == null || DataUtils.isEmpty(list)) {
					msg = "记录未找到";
				}else {
					double balance = DataUtils.str2double(investMember.getBalance()+"", 6);
					double asset = DataUtils.str2double(investMember.getTotalMoney()+"", 6);
					if (balance == DataUtils.str2double(((Object[])list.get(0))[0].toString(), 6) && asset == DataUtils.str2double(((Object[])list.get(0))[1].toString(), 6)) {
						entity = service.findById(pkid);
						entity.setBalance(balance + entity.getMoney());
						entity.setAsset(asset + entity.getMoney());
						entity.setStatus(BillStatus.BS2.getVal());
						entity.setOptTime(new Date());
						investMember.setBalance(entity.getBalance());
						investMember.setTotalMoney(entity.getAsset());
						synchronized(this){
							if (service.updateBill(entity, investMember)) {
								success = true;
							}
						}
					}else {
						msg = "资金未同步";
					}
				}
			}else{
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("PayAction——deposit方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("PayAction——deposit方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 提现 <成功回调>
	 * @Title: withdrawals   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午3:40:45
	 * @return: void      
	 * @throws   
	 */  
	public void withdrawals(){
	}

	
	/**   
	 * 提现 <成功回调>
	 * @Title: withdrawalsCallBack   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月23日 下午3:22:13
	 * @return: void      
	 * @throws   
	 */  
	public void withdrawalsCallBack(){
		
	}

	
	/**   
	 * 支付————理财订单 <成功回调>
	 * @Title: payInvest   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午3:41:36
	 * @return: void      
	 * @throws   
	 */  
	public void payInvest(){

	}


	/**   
	 * 赎回————理财订单 <成功回调>
	 * @Title: redemptiveInvest   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午3:42:33
	 * @return: void      
	 * @throws   
	 */  
	public void redemptiveInvest(){

	}


}
