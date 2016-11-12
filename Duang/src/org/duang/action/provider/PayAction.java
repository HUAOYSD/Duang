package org.duang.action.provider;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
import org.duang.enums.ResultCode;
import org.duang.enums.billinvest.BillStatus;
import org.duang.enums.billinvest.UseType;
import org.duang.service.BillInvestService;
import org.duang.service.InvestMemberService;
import org.duang.service.MemberInfoService;
import org.duang.util.DataUtils;
import org.duang.util.MD5Utils;
import org.duang.util.ReadProperties;
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
	
	private MemberInfoService memberInfoService;
	@Resource
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
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
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(platform) && DataUtils.notEmpty((id = MemberCollection.getInstance(token,memberInfoService).getMainField(token)))) {
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
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty((id = MemberCollection.getInstance(token,memberInfoService).getMainField(token)))) {
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
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String p_fetchMany = getRequest().getParameter("p_fetchMany");
			String bankid = getRequest().getParameter("p_bankid");
			String platform = getRequest().getParameter("p_platform");
			String pk = DataUtils.randomUUID();
			String id = "";
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(platform) && DataUtils.notEmpty((id = MemberCollection.getInstance(token,memberInfoService).getMainField(token)))) {
				double fetchManyVal = DataUtils.str2double(p_fetchMany, 6);
				if (fetchManyVal > 0) {
					entity = new BillInvest(pk, new MemberInfo(id), null, new BindCard(bankid), UseType.UT2.getVal(), fetchManyVal, 0, 0, BillStatus.BS1.getVal(), new Date(), "提现", DataUtils.str2int(platform));
					if (service.saveEntity(entity)) {
						jsonObject.put("id", pk);
						success = true;
					}else {
						msg = "添加失败";
					}
				}else{
					msg = "提现金额需为正数";
				}
			}else{
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("PayAction——withdrawals方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("PayAction——withdrawals方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
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
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String pkid = getRequest().getParameter("p_id");
			String id = "";
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty((id = MemberCollection.getInstance(token,memberInfoService).getMainField(token)))) {
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
						entity.setBalance(balance - entity.getMoney());
						entity.setAsset(asset - entity.getMoney());
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
			LoggerUtils.error("PayAction——withdrawalsCallBack方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("PayAction——withdrawalsCallBack方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
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

	/**
	 * 丰付充值回调
	 * @Title: depositFFCallBack   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月3日 下午4:08:14
	 * @return: void      
	 * @throws
	 */
	public void depositFFCallBack(){
		LoggerUtils.info("\t\n--------------------------充值回调-----------------------------------\t\n", this.getClass());
		try{
			//读取配置文件
			Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
			
			String requestId = getRequest().getParameter("requestId");
			String noticeType = getRequest().getParameter("noticeType");
			String result = getRequest().getParameter("result");
			//充值金额
			String sum = getRequest().getParameter("sum");
			String userIdIdentity = getRequest().getParameter("userIdIdentity");
			
			//未结金额
			String unsettledBalance = getRequest().getParameter("unsettledBalance");
			//账户总额
			String userBalance = getRequest().getParameter("userBalance");
			//可用余额
			String withdrawableBalance = getRequest().getParameter("withdrawableBalance");
			//冻结余额
			String frozenBalance = getRequest().getParameter("frozenBalance");
			String payType = getRequest().getParameter("payType");
			String mainAccountType = getRequest().getParameter("mainAccountType");
			String mainAccountCode = getRequest().getParameter("mainAccountCode");
			String bankAccount = getRequest().getParameter("bankAccount");
			String bankName = getRequest().getParameter("bankName");
			String name = getRequest().getParameter("name");
			String signature = getRequest().getParameter("signature");
			LoggerUtils.info("\t\n------------------------充值回调开始-------------------------------------\t\n", this.getClass());
			StringBuffer backDataBuffer = new StringBuffer("/t/n---------------------------充值回调  字符串");
			backDataBuffer.append("/t/n----requestId:"+requestId)
						  .append("/t/n----noticeType:"+noticeType)
						  .append("/t/n----result:"+result)
						  .append("/t/n----sum:"+sum)
						  .append("/t/n----userIdIdentity:"+userIdIdentity)
						  .append("/t/n----unsettledBalance:"+unsettledBalance)
						  .append("/t/n----userBalance:"+userBalance)
						  .append("/t/n----withdrawableBalance:"+withdrawableBalance)
						  .append("/t/n----frozenBalance:"+frozenBalance)
						  .append("/t/n----payType:"+payType)
						  .append("/t/n----mainAccountType:"+mainAccountType)
						  .append("/t/n----mainAccountCode"+mainAccountCode)
						  .append("/t/n----bankAccount:"+bankAccount)
						  .append("/t/n----bankName:"+bankName)
						  .append("/t/n----name:"+name)
						  .append("/t/n----signature:"+signature);
			
			LoggerUtils.info(backDataBuffer.toString(), this.getClass());
			
			StringBuffer signatureStr = new StringBuffer();
			signatureStr.append(requestId);
			signatureStr.append(result);
			signatureStr.append(sum);
			signatureStr.append(userIdIdentity);
			signatureStr.append(userBalance);
			//获取返回数据的加密数据用于与签名校验
			String dataSign = MD5Utils.hmacSign(signatureStr.toString(), ReadProperties.getStringValue(properties, "akey"));
			LoggerUtils.info("/t/n---------------------------充值回调  本地加密后的签名:"+dataSign, this.getClass());
			if(dataSign.equals(signature)){
				if(result.equals(ResultCode.SUCCESS.getVal())){
					//修改数据库
					updateMemberInfoBalance(userIdIdentity,withdrawableBalance,userBalance,frozenBalance,unsettledBalance);
				}else{
					LoggerUtils.error(name+"充值,错误，原因："+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)), this.getClass());
				}
			}else{
				LoggerUtils.error(name+"充值,原因：秘钥校验错误", this.getClass());
			}
			LoggerUtils.info("\t\n------------------------充值回调结束-------------------------------------\t\n", this.getClass());
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("PayAction——depositFFCallBack方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("PayAction——depositFFCallBack方法错误：" + e.getLocalizedMessage(), this.getClass());
		}
	}
	
	/**
	 * 丰付提现，回调
	 * @Title: withdrawalsFFCallBack   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月3日 下午3:19:56
	 * @return: void      
	 * @throws
	 */
	public void withdrawalsFFCallBack(){
		try{
			 //下面注释的代码是丰付返回的数据，只是没有用到，所以给注释了，如果需要使用，请消除注释
			//读取配置文件  
			Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
			String requestId = getRequest().getParameter("requestId");
			String noticeType = getRequest().getParameter("noticeType");
			String result = getRequest().getParameter("result");
			String sum = getRequest().getParameter("sum");
			String userIdIdentity = getRequest().getParameter("userIdIdentity");
			String failReason = getRequest().getParameter("failReason");
			String userBalance = getRequest().getParameter("userBalance");
			String withdrawableBalance = getRequest().getParameter("withdrawableBalance");
			String frozenBalance = getRequest().getParameter("frozenBalance");
			String payType = getRequest().getParameter("payType");
			String mainAccountType = getRequest().getParameter("mainAccountType");
			String mainAccountCode = getRequest().getParameter("mainAccountCode");
			String bankAccount = getRequest().getParameter("bankAccount");
			String bankName = getRequest().getParameter("bankName");
			String name = getRequest().getParameter("name");
			String requestTime = getRequest().getParameter("requestTime");
			String dealTime = getRequest().getParameter("dealTime");
			String signature = getRequest().getParameter("signature");
			
			StringBuffer signatureStr = new StringBuffer();
			signatureStr.append(requestId);
			signatureStr.append(result);
			signatureStr.append(sum);
			signatureStr.append(userIdIdentity);
			signatureStr.append(userBalance);
			LoggerUtils.info("\t\n--------------------------提现回调-----------------------------------\t\n", this.getClass());
			StringBuffer backDataBuffer = new StringBuffer("\t\n---------------------------提现回调字符串");
			backDataBuffer.append("\t\n----requestId:"+requestId)
						  .append("\t\n----noticeType:"+noticeType)
						  .append("\t\n----result:"+result)
						  .append("\t\n----sum:"+sum)
						  .append("\t\n----userIdIdentity:"+userIdIdentity)
						  .append("\t\n----failReason:"+failReason)
						  .append("\t\n----userBalance:"+userBalance)
						  .append("\t\n----withdrawableBalance:"+withdrawableBalance)
						  .append("\t\n----frozenBalance:"+frozenBalance)
						  .append("\t\n----payType:"+payType)
						  .append("\t\n----mainAccountType:"+mainAccountType)
						  .append("\t\n----mainAccountCode"+mainAccountCode)
						  .append("\t\n----bankAccount:"+bankAccount)
						  .append("\t\n----bankName:"+bankName)
						  .append("\t\n----name:"+name)
						  .append("\t\n----requestTime:"+requestTime)
						  .append("\t\n----dealTime:"+dealTime)
						  .append("\t\n----signature:"+signature);
			
			LoggerUtils.info(backDataBuffer.toString(), this.getClass());
			
			//获取返回数据的加密数据用于与签名校验
			String dataSign = MD5Utils.hmacSign(signatureStr.toString(), ReadProperties.getStringValue(properties, "akey"));
			LoggerUtils.info("\t\n---------------------------提现回调本地加密签名："+dataSign, this.getClass());
			if(dataSign.equals(signature)){
				if(result.equals(ResultCode.SUCCESS.getVal())){
					//修改数据库
					updateMemberInfoBalance(userIdIdentity,withdrawableBalance,userBalance,frozenBalance);
				}else{
					LoggerUtils.error(name+"提现,错误，原因："+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result))+"---"+failReason, this.getClass());
				}
			}else{
				LoggerUtils.error(name+"提现,原因：秘钥校验错误", this.getClass());
			}
		
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("PayAction——depositFFCallBack方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("PayAction——depositFFCallBack方法错误：" + e.getLocalizedMessage(), this.getClass());
		}
	}

	/**
	 * 提现，修改理财用户的数据
	 * @Title: updateMemberInfoBalance   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberInfoId   用户id
	 * @param: @param balance    余额
	 * @param: @param totalMoney 总金额
	 * @param: @param frozenBalance 投资中的金额
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月3日 下午3:59:47
	 * @return: void      
	 * @throws
	 */
	private void  updateMemberInfoBalance(String memberInfoId,String balance,String totalMoney,String frozenBalance) throws Exception{
		LoggerUtils.info("\t\n------------------------提现修改本地用户金额-------------------------------------\t\n", this.getClass());
		InvestMember investMember = investMemberService.findEntity("memberInfo.id", memberInfoId);
		if(investMember != null){
			LoggerUtils.info("\t\n--------姓名："+investMember.getMemberInfo().getRealName()+"  电话："+investMember.getMemberInfo().getPhone(), this.getClass());
			LoggerUtils.info("\t\n--------余额："+DataUtils.str2double(balance,6), this.getClass());
			LoggerUtils.info("\t\n--------理财："+DataUtils.str2double(frozenBalance,6), this.getClass());
			LoggerUtils.info("\t\n--------总资产："+DataUtils.str2double(totalMoney,6), this.getClass());
			investMember.setBalance(DataUtils.str2double(balance,6));
			investMember.setInvesting(DataUtils.str2double(frozenBalance,6));
			investMember.setTotalMoney(DataUtils.str2double(totalMoney,6));
			investMemberService.updateEntity(investMember);
		}else{
			LoggerUtils.error("提现 修改本地用户金额数据错误,原因：未查找到该用户 memberId="+memberInfoId, this.getClass());
		}
	}
	
	/**
	 * 充值回调修改金额，修改理财用户的数据
	 * @Title: updateMemberInfoBalance   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberInfoId   用户id
	 * @param: @param balance    余额
	 * @param: @param totalMoney 总金额
	 * @param: @param frozenBalance 投资中的金额
	 * @param: @param unsettledBalance 未结金额
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月3日 下午3:59:47
	 * @return: void      
	 * @throws
	 */
	private void  updateMemberInfoBalance(String memberInfoId,String balance,String totalMoney,String frozenBalance,String unsettledBalance) throws Exception{
		LoggerUtils.info("\t\n------------------------充值修改本地用户金额-------------------------------------\t\n", this.getClass());
		InvestMember investMember = investMemberService.findEntity("memberInfo.id", memberInfoId);
		if(investMember != null){
			LoggerUtils.info("\t\n--------姓名："+investMember.getMemberInfo().getRealName()+"  电话："+investMember.getMemberInfo().getPhone(), this.getClass());
			LoggerUtils.info("\t\n--------余额："+DataUtils.str2double(balance,6), this.getClass());
			LoggerUtils.info("\t\n--------理财："+DataUtils.str2double(frozenBalance,6), this.getClass());
			LoggerUtils.info("\t\n--------总资产："+DataUtils.str2double(totalMoney,6), this.getClass());
			LoggerUtils.info("\t\n--------未结金额："+DataUtils.str2double(unsettledBalance,6), this.getClass());
			investMember.setBalance(DataUtils.str2double(balance,6));
			investMember.setInvesting(DataUtils.str2double(frozenBalance,6));
			investMember.setTotalMoney(DataUtils.str2double(totalMoney,6));
			investMember.setUnsettledBalance(DataUtils.str2double(unsettledBalance,6));
			investMemberService.updateEntity(investMember);
		}else{
			LoggerUtils.error("充值 修改本地用户金额数据错误,原因：未查找到该用户 memberId="+memberInfoId, this.getClass());
		}
	}
}
