package org.duang.action.provider;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import org.duang.entity.InvestList;
import org.duang.entity.LoanList;
import org.duang.entity.RequestFlow;
import org.duang.entity.Scale;
import org.duang.entity.ScaleLoanList;
import org.duang.enums.ResultCode;
import org.duang.enums.loan.ReturnStatus;
import org.duang.enums.scale.SingleOrSet;
import org.duang.service.InvestListService;
import org.duang.service.InvestMemberService;
import org.duang.service.LoanListService;
import org.duang.service.MemberInfoService;
import org.duang.service.RequestFlowService;
import org.duang.service.ScaleLoanListService;
import org.duang.util.DataUtils;
import org.duang.util.MD5Utils;
import org.duang.util.ReadProperties;
import org.duang.util.SSLClient;
import org.json.JSONException;
import org.json.JSONObject;
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


	private InvestMemberService investMemberService;
	@Resource
	public void setInvestMemberService(InvestMemberService investMemberService) {
		this.investMemberService = investMemberService;
	}

	private MemberInfoService memberInfoService;
	@Resource
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
	}
	
	private ScaleLoanListService scaleLoanListService;
	@Resource
	public void setScaleLoanListService(ScaleLoanListService scaleLoanListService){
		this.scaleLoanListService = scaleLoanListService;
	}
	
	private LoanListService loanListService;
	@Resource
	public void setLoanListService(LoanListService loanListService){
		this.loanListService = loanListService;
	}
	
	private InvestListService investListService;
	@Resource
	public void setInvestListService(InvestListService investListService){
		this.investListService = investListService;
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
	 *//*  
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
	}*/


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
	/*public void depositCallBack(){
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
	}*/


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
	/*public void withdrawals(){
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
*/

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
	/*public void withdrawalsCallBack(){
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
*/

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

	@Resource
	private RequestFlowService requestFlowService;

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
	public synchronized void depositFFCallBack(){
		LoggerUtils.info("\t\n--------------------------充值回调-----------------------------------\t\n", this.getClass());
		try{

			String requestId = getRequest().getParameter("requestId");
			if (DataUtils.isEmpty(requestId) || requestFlowService.findEntity("requestId", requestId) != null) {
				return;
			}
			//读取配置文件
			Properties properties = ReadProperties.initPrperties("sumapayURL.properties");


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
					investMemberService.depositFFCallBackUpdateInvest(userIdIdentity,DataUtils.str2double(withdrawableBalance, 6),
							DataUtils.str2double(userBalance, 6),DataUtils.str2double(frozenBalance, 6),DataUtils.str2double(unsettledBalance, 6),
							DataUtils.str2double(sum, 6),bankAccount,bankName);
				}else{
					LoggerUtils.error(name+"充值,错误，原因："+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)), this.getClass());
				}
			}else{
				LoggerUtils.error(name+"充值,原因：秘钥校验错误", this.getClass());
			}

			RequestFlow requestFlow = new RequestFlow(DataUtils.randomUUID(), requestId, userIdIdentity, new Date());
			requestFlowService.saveEntity(requestFlow);

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
	public synchronized void withdrawalsFFCallBack(){
		try{
			//下面注释的代码是丰付返回的数据，只是没有用到，所以给注释了，如果需要使用，请消除注释
			//读取配置文件  
			String requestId = getRequest().getParameter("requestId");
			if (DataUtils.isEmpty(requestId) || requestFlowService.findEntity("requestId", requestId) != null) {
				return;
			}

			Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
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
					investMemberService.withdrawalsFFCallBackUpdateInvest(userIdIdentity,DataUtils.str2double(withdrawableBalance, 6),
							DataUtils.str2double(userBalance, 6),DataUtils.str2double(frozenBalance, 6),DataUtils.str2double(sum, 6),bankAccount);
				}else{
					LoggerUtils.error(name+"提现,错误，原因："+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result))+"---"+failReason, this.getClass());
				}
			}else{
				LoggerUtils.error(name+"提现,原因：秘钥校验错误", this.getClass());
			}
			RequestFlow requestFlow = new RequestFlow(DataUtils.randomUUID(), requestId, userIdIdentity, new Date());
			requestFlowService.saveEntity(requestFlow);
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("PayAction——depositFFCallBack方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("PayAction——depositFFCallBack方法错误：" + e.getLocalizedMessage(), this.getClass());
		}
	}

	
	/**
	 * 普通标   个人用户撤标请求
	 * @Title: sithdrawSingleScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月9日 上午9:44:47
	 * @return: void      
	 * @throws
	 */
	public void withdrawSingleScale() {
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String scaleId = getRequest().getParameter("scaleId");  
			String memberInfoId="";
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(memberInfoId = MemberCollection.getInstance(token,memberInfoService).getMainField(token))) {
				Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
				//请求受理成功，正在处理，需要主动查询
				String urlStr = ReadProperties.getStringValue(properties, "withdrawSingleScaleURL");
				//请求受理成功，正在处理，需要主动查询
				String noticeUrl = ReadProperties.getStringValue(properties, "withdrawSingleScaleURL_noticeUrl");
				//商户号
				String merchantCode = ReadProperties.getStringValue(properties, "merchantCode");
				//秘钥
				String akey = ReadProperties.getStringValue(properties, "akey");
				//生成一个流水号
				String requestId = DataUtils.randomUUID();
				//投标时的requestid
				Map<String,Object> findInvestListmap = new HashMap<String,Object>();
				findInvestListmap.put("scale.id", scaleId);
				findInvestListmap.put("memberInfo.id", memberInfoId);
				InvestList investList = investListService.findEntity(findInvestListmap);
				String bidRequestId = investList.getRequestid();
				//投标金额
				String sum = new DecimalFormat("#.00").format(investList.getMoney());
				String withdrawalFund = sum;
				//数字签名字符串
				StringBuffer signatureBuffer = new StringBuffer();
				signatureBuffer.append(requestId);
				signatureBuffer.append(merchantCode);
				signatureBuffer.append(scaleId);
				signatureBuffer.append(bidRequestId);
				signatureBuffer.append(sum);
				signatureBuffer.append(withdrawalFund);
				signatureBuffer.append(noticeUrl);

				StringBuffer sendStringBuffer = new StringBuffer("\t\n---------------------------普通标 个人用户撤标  send2FF的字符串：");
				sendStringBuffer.append("\t\n----requestId:"+requestId)
				.append("\t\n----merchantCode:"+merchantCode)
				.append("\t\n----projectCode:"+scaleId)
				.append("\t\n----bidRequestId:"+bidRequestId)
				.append("\t\n----sum:"+sum)
				.append("\t\n----withdrawalFund:"+withdrawalFund)
				.append("\t\n----noticeUrl:"+noticeUrl);
				LoggerUtils.info("\t\n--------字符串："+sendStringBuffer.toString(), this.getClass());
				//加密后的数字签名
				String signature_sign=MD5Utils.hmacSign(signatureBuffer.toString(), akey);
				LoggerUtils.info("-------------普通标 个人用户撤标  加密后的数字签名：signature_sign:"+signature_sign,this.getClass());
				//封装map参数
				Map<String,String> map = new HashMap<String, String>();
				map.put("requestId",requestId);
				map.put("merchantCode",merchantCode);
				map.put("projectCode",scaleId);
				map.put("bidRequestId",bidRequestId);
				map.put("sum",sum);
				map.put("withdrawalFund",withdrawalFund);
				map.put("noticeUrl",noticeUrl);
				map.put("signature",signature_sign);
				//获取转换的参数
				JSONObject jsonObjectData = SSLClient.getJsonObjectByUrl(urlStr,map,"GBK");
				//result 查询结果  00000代表成功
				String resultCallbace = jsonObjectData.get("result").toString();
				if(resultCallbace.equals(ResultCode.SUCCESS.getVal())){
					
				}else{
					jsonObject.put("result", false);
					jsonObject.put("msg", DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, resultCallbace)));
					LoggerUtils.error("流程号："+requestId+"------普通标 个人用户撤标 失败,原因 错误码"+resultCallbace+"===="+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, resultCallbace)),this.getClass());
				}
				
				success=true;
			}else{
				jsonObject.put("msg", "数据丢失，请重新登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——queryMemberAccount方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——queryMemberAccount方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		} finally {
			jsonObject.put("success", success);
			printJsonResult();
		}
	}

	/**
	 * @throws JSONException 
	 * 	个人用户 撤普通标，成功后的处理
	 * @Title: withdrawSingScaleSuccess   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param jsonObjectData  
	 * @author LiYonghui    
	 * @date 2016年12月9日 上午10:50:01
	 * @return: void      
	 * @throws
	 */
	private  void  withdrawSingScaleSuccess(JSONObject jsonObjectData,String akey) throws JSONException{
		String requestId = jsonObjectData.get("requestId").toString();
		String projectCode = jsonObjectData.get("projectCode").toString();
		String bidRequestId = jsonObjectData.get("bidRequestId").toString();
		String result = jsonObjectData.get("result").toString();
		String projectSum = jsonObjectData.get("projectSum").toString();
		String remainInvestmentSum = jsonObjectData.get("remainInvestmentSum").toString();
		String investmentSum = jsonObjectData.get("investmentSum").toString();
		String withdrawalFund = jsonObjectData.get("withdrawalFund").toString();
		String signature = jsonObjectData.get("signature").toString();
		
		if(!DataUtils.notEmpty(projectSum) && !projectSum.equals("null")){
			projectSum="0";
		}
		if(!DataUtils.notEmpty(remainInvestmentSum) && !remainInvestmentSum.equals("null")){
			remainInvestmentSum="0";
		}
		if(!DataUtils.notEmpty(investmentSum) && !investmentSum.equals("null")){
			investmentSum="0";
		}
		StringBuffer backDataStringBuffer = new StringBuffer("\t\n---------------------------普通标 个人用户撤标  BackData的字符串：");
		backDataStringBuffer.append("\t\n----requestId:"+requestId)
		.append("\t\n----projectCode:"+projectCode)
		.append("\t\n----bidRequestId:"+bidRequestId)
		.append("\t\n----result:"+result)
		.append("\t\n----projectSum:"+projectSum)
		.append("\t\n----remainInvestmentSum:"+remainInvestmentSum)
		.append("\t\n----investmentSum:"+investmentSum)
		.append("\t\n----withdrawalFund:"+withdrawalFund)
		.append("\t\n----signature:"+signature);
		LoggerUtils.info(backDataStringBuffer.toString(), this.getClass());
		StringBuffer back_signatureBuffer = new StringBuffer(requestId+projectCode+bidRequestId+withdrawalFund+result);
		String back_signature_sign = MD5Utils.hmacSign(back_signatureBuffer.toString(), akey);
		LoggerUtils.info("\t\n-------------普通标 个人用户撤标   返回的参数信息-加密签名:"+back_signature_sign.toString(),this.getClass());
		if(back_signature_sign.equals(signature)){
			
		}else{
			jsonObject.put("result", false);
			jsonObject.put("msg", "签名不一致");
		}
	}

	private  Map<String, String> getParamValBykey(Properties properties,String urlKey,String noticeUrlKey, String successReturnUrl, String failReturnUrl) throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		//请求受理成功，正在处理，需要主动查询
		String urlStr = ReadProperties.getStringValue(properties, urlKey);
		//请求受理成功，正在处理，需要主动查询
		String noticeUrl = ReadProperties.getStringValue(properties, noticeUrlKey);
		//商户号
		String merchantCode = ReadProperties.getStringValue(properties, "merchantCode");
		//秘钥
		String akey = ReadProperties.getStringValue(properties, "akey");
		//个人用户存管账户还款(集合理财项目)成功跳转地址
		if(DataUtils.notEmpty(successReturnUrl)){
			String successReturnUrl_v  = ReadProperties.getStringValue(properties, successReturnUrl);
			map.put("successReturnUrl", successReturnUrl_v);
		}
		//个人用户存管账户还款(集合理财项目)失败跳转地址
		if(DataUtils.notEmpty(failReturnUrl)){
			String failReturnUrl_v  = ReadProperties.getStringValue(properties, failReturnUrl);
			map.put("failReturnUrl", failReturnUrl_v);
		}
		map.put("urlStr", urlStr);
		map.put("noticeUrl", noticeUrl);
		map.put("merchantCode", merchantCode);
		map.put("akey", akey);
		return map;
	}
	
	/**
	 * 个人用户存管账户还款
	 * @Title: memberInfoRepaySetScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月9日 上午9:44:47
	 * @return: void      
	 * @throws
	 */
	public void memberInfoRepay() {
		boolean success = false;
		//放款URL
		String repayUrl="";
		String noticeUrl="";
		String successReturnUrl="";
		String failReturnUrl="";
		try {
			String token = getRequest().getParameter("token");
			//还款金额
			String sum = getRequest().getParameter("money");  
			String memberInfoId="";
			Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(memberInfoId = MemberCollection.getInstance(token,memberInfoService).getMainField(token))) {
				//生成一个流水号
				String requestId = DataUtils.randomUUID();
				//查找贷款记录
				Map<String, Object> loanListParamMap = new HashMap<String, Object>();
				loanListParamMap.put("memberInfo.id", memberInfoId);
				loanListParamMap.put("returnStatus", ReturnStatus.B2.getVal());
				LoanList loanList = loanListService.findEntity(loanListParamMap);
				
				//项目编号
				ScaleLoanList scaleLoanList = scaleLoanListService.findEntity("loanList.id",loanList.getId());
				Scale scale = scaleLoanList.getScale();
				String projectCode  = scale.getId();
				if(scale.getSingleOrSet()==SingleOrSet.S1.getVal()){
					repayUrl="memberInfoRepaySetScale";
					noticeUrl="memberInfoRepaySetScale_noticeUrl";
					successReturnUrl="memberInfoRepaySetScale_success";
					failReturnUrl="memberInfoRepaySetScale_fail";
				}else if(scale.getSingleOrSet()==SingleOrSet.S2.getVal()){
					repayUrl="memberInfoRepaySetScale";
					noticeUrl="memberInfoRepaySetScale_noticeUrl";
					successReturnUrl="memberInfoRepaySetScale_success";
					failReturnUrl="memberInfoRepaySetScale_fail";
				}
				Map<String, String> keyMap = getParamValBykey(properties,repayUrl,noticeUrl,successReturnUrl,failReturnUrl);
				//还款金额
				sum = new DecimalFormat("#.00").format(sum);
				//数字签名字符串
				StringBuffer signatureBuffer = new StringBuffer();
				signatureBuffer.append(requestId);
				signatureBuffer.append(keyMap.get("merchantCode"));
				signatureBuffer.append(memberInfoId);
				signatureBuffer.append(projectCode);
				signatureBuffer.append(sum);
				signatureBuffer.append(keyMap.get(successReturnUrl));
				signatureBuffer.append(keyMap.get(failReturnUrl));

				StringBuffer sendStringBuffer = new StringBuffer("\t\n---------------------------个人用户存管账户还款 send2FF的字符串：");
				sendStringBuffer.append("\t\n----requestId:"+requestId)
				.append("\t\n----merchantCode:"+keyMap.get("merchantCode"))
				.append("\t\n----projectCode:"+projectCode)
				.append("\t\n----userIdIdentity :"+memberInfoId)
				.append("\t\n----sum:"+sum)
				.append("\t\n----successReturnUrl:"+keyMap.get("successReturnUrl"))
				.append("\t\n----failReturnUrl:"+keyMap.get("failReturnUrl"))
				.append("\t\n----noticeUrl:"+keyMap.get("noticeUrl"));
				LoggerUtils.info("\t\n--------字符串："+sendStringBuffer.toString(), this.getClass());
				
				//加密后的数字签名
				String signature_sign=MD5Utils.hmacSign(signatureBuffer.toString(), keyMap.get("akey"));
				LoggerUtils.info("-------------个人用户存管账户还款  加密后的数字签名：signature_sign:"+signature_sign,this.getClass());
				//封装map参数
				Map<String,String> map = new HashMap<String, String>();
				map.put("requestId",requestId);
				map.put("merchantCode",keyMap.get("merchantCode"));
				map.put("projectCode",projectCode);
				map.put("userIdIdentity",memberInfoId);
				map.put("sum",sum);
				map.put("successReturnUrl",keyMap.get("successReturnUrl"));
				map.put("failReturnUrl",keyMap.get("failReturnUrl"));
				map.put("noticeUrl",keyMap.get("noticeUrl"));
				map.put("signature",signature_sign);
				//获取转换的参数
				JSONObject jsonObjectData = SSLClient.getJsonObjectByUrl(keyMap.get("urlStr"),map,"GBK");
				//result 查询结果  00000代表成功
				String resultCallbace = jsonObjectData.get("result").toString();
				if(resultCallbace.equals(ResultCode.SUCCESS.getVal())){
					LoggerUtils.info("\t\n----------------------------------"+memberInfoId+"还款处理结果成功", this.getClass());
					success = true;
					msg = "还款成功";
				}else if(resultCallbace.equals(ResultCode.Doing.getVal())){
					success = true;
					msg="正在处理";
				}else{
					success=false;
					msg = DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, resultCallbace));
					LoggerUtils.error("流程号："+requestId+"------个人用户存管账户还款 失败,原因 错误码"+resultCallbace+"===="+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, resultCallbace)),this.getClass());
				}
			}else{
				msg = "数据丢失，请重新登录";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("PayAction——memberInfoRepay方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("PayAction——memberInfoRepay方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		} finally {
			jsonObject.put("success", success);
			jsonObject.put("msg", msg);
			printJsonResult();
		}
	}
	
	/**
	 * 还款异步通知
	 * @Title: memberInfoRepaySetScale_noticeUrl   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月13日 下午3:09:06
	 * @return: void      
	 * @throws
	 */
	public void memberInfoRepaySetScale_noticeUrl(){
		LoggerUtils.info("\t\n----------------------------------还款异步返回 memberInfoRepaySetScale_noticeUrl 结果", this.getClass());
		boolean success = false;
		Properties  properties;
		String result="";
		try{
			String requestId = getRequest().getParameter("requestId");
			String userIdIdentity = getRequest().getParameter("userIdIdentity");
			String projectCode = getRequest().getParameter("projectCode");
			result = getRequest().getParameter("result");
			String userBalance = getRequest().getParameter("userBalance");
			String withdrawableBalance = getRequest().getParameter("withdrawableBalance");
			String frozenBalance = getRequest().getParameter("frozenBalance");
			String unsettledBalance = getRequest().getParameter("unsettledBalance");
			String sum = getRequest().getParameter("sum");
			//String giftSum = getRequest().getParameter("giftSum");
			String signature = getRequest().getParameter("signature");
			
			if(!DataUtils.notEmpty(userBalance) && !userBalance.equals("null")){
				userBalance="0";
			}
			if(!DataUtils.notEmpty(withdrawableBalance) && !withdrawableBalance.equals("null")){
				withdrawableBalance="0";
			}
			if(!DataUtils.notEmpty(frozenBalance) && !frozenBalance.equals("null")){
				frozenBalance="0";
			}
			if(!DataUtils.notEmpty(unsettledBalance) && !unsettledBalance.equals("null")){
				unsettledBalance="0";
			}
			properties= ReadProperties.initPrperties("sumapayURL.properties");
			StringBuffer back_signatureBuffer = new StringBuffer(requestId+result+userIdIdentity+userBalance);
			String back_signature_sign = MD5Utils.hmacSign(back_signatureBuffer.toString(), ReadProperties.getStringValue(properties, "akey"));
			LoggerUtils.info("\t\n-------------还款异步返回   返回的参数信息-加密签名:"+back_signature_sign.toString(),this.getClass());
			if(back_signature_sign.equals(signature)){
				Map<String, Object> loanListParamMap = new HashMap<String, Object>();
				loanListParamMap.put("memberInfo.id", userIdIdentity);
				loanListParamMap.put("returnStatus", ReturnStatus.B2.getVal());
				LoanList loanList = loanListService.findEntity(loanListParamMap);
				success = loanListService.memberInfoRepay(DataUtils.str2double(sum, 6), userIdIdentity, projectCode, loanList, 4);
			}else{
				LoggerUtils.error("\t\n-------还款异步返回失败：" + DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)), this.getClass());
				msg="签名不一致";
			}
		}catch(Exception e){
			success = false;
			msg = "服务器维护，请稍后再试";
			e.printStackTrace();
			LoggerUtils.error("PayAction——memberInfoRepaySetScale_noticeUrl方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("PayAction——memberInfoRepaySetScale_noticeUrl方法错误：" + e.getLocalizedMessage(), this.getClass());
		}
		LoggerUtils.info("\t\n-------还款异步返回结果"+success+"："+msg, this.getClass());
	}
	
	/**
	 * 还款成功跳转地址
	 * @Title: memberInfoRepaySetScale_success   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月13日 下午3:08:20
	 * @return: void      
	 * @throws
	 */
	public void memberInfoRepaySetScale_success(){
		LoggerUtils.info("\t\n----------------------------------还款成功跳转返回memberInfoRepaySetScale_success结果开始处理", this.getClass());
	}
	/**
	 * 还款失败跳转地址
	 * @Title: memberInfoRepaySetScale_fail   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月13日 下午3:07:36
	 * @return: void      
	 * @throws
	 */
	public void memberInfoRepaySetScale_fail(){
		LoggerUtils.info("\t\n----------------------------------还款失败跳转地址 memberInfoRepaySetScale_fail 结果", this.getClass());
		try {
			String result = getRequest().getParameter("result");
			Properties  properties = ReadProperties.initPrperties("sumapayURL.properties");
			LoggerUtils.info("失败:"+result+"\t"+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)),this.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
