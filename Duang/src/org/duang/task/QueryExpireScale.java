package org.duang.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.duang.common.logger.LoggerUtils;
import org.duang.entity.InvestList;
import org.duang.entity.MemberInfo;
import org.duang.entity.RequestFlow;
import org.duang.entity.Scale;
import org.duang.enums.ResultCode;
import org.duang.enums.scale.SingleOrSet;
import org.duang.service.InvestListService;
import org.duang.service.RequestFlowService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.MD5Utils;
import org.duang.util.ReadProperties;
import org.duang.util.SSLClient;
import org.json.JSONObject;

public class QueryExpireScale {

	// 判断作业是否执行的旗标
	private boolean isRunning = false;

	// 定义任务执行体
	public void run() {
		if (!isRunning) {
			LoggerUtils.info("\t\n-----------------------------开始调度本息到账",this.getClass());
			ransomExpireScale();
		}
	}

	private InvestListService investListService;
	@Resource
	public void setService(InvestListService investListService) {
		this.investListService = investListService;
	}

	private RequestFlowService requestFlowService;
	@Resource
	public void setRequestFlowService(RequestFlowService requestFlowService) {
		this.requestFlowService = requestFlowService;
	}
	
	/**
	 * 查询昨天是否有到期的投标用户（为什么是昨天呢，因为是每天的凌晨2点才开始执行，所以是昨天）
	 * 
	 * @Title: queryTodayExpireInvestList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @param: @throws Exception
	 * @author LiYonghui
	 * @date 2016年11月9日 上午11:44:34
	 * @return: List<Scale>
	 * @throws
	 */
	private List<InvestList> queryTodayExpireInvestList() throws Exception {
		String sql = "SELECT *  from invest_list il where DATE_FORMAT(INTERVAL il.days DAY + il.open_date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')";
		List<InvestList> list = investListService.queryBySQL(sql, null, null, true);
		return list;
	}
	
	/**
	 * 获取 分账列表，对应记录一笔用户的进账明细；记录一条或多条商户的进账明细。
	 * 
	 * @Title: getSubledgerList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param memberInfoId
	 * @param: @return
	 * @param: @throws Exception
	 * @author LiYonghui
	 * @date 2016年11月9日 下午2:31:42
	 * @return: String 返回拼接好的参数类型 a=b&c=d
	 * @throws
	 */
	private String getSubledgerList(String memberInfoId) throws Exception {
		if (DataUtils.notEmpty(memberInfoId)) {
			StringBuffer paramBuffer = new StringBuffer("[{");
			 paramBuffer.append("\"roleType\"")
	         .append(":")
	         .append("\"0\"")
	         .append(",")
	         .append("\"roleCode\"")
	         .append(":")
	         .append("\""+memberInfoId+"\"")
	         .append(",")
	         .append("\"inOrOut\"")
	         .append(":")
	         .append("\"0\"")
	         .append(",")
	         .append("\"sum\"")
	         .append(":")
	         .append("\""+1000+"\"")
			 .append("}")
			 .append("]");
			 return paramBuffer.toString();
		}else{
			return "";
		}
		
	}

	/**
	 * 本息到账
	 */
	private void ransomExpireScale() {
		try {
			// 1.查询到期的标
			List<InvestList> investLists = queryTodayExpireInvestList();
			if (DataUtils.notEmpty(investLists)) {
				for (InvestList investList : investLists) {
					returnPrinInteMessage(investList);
				}
			} else {
				LoggerUtils.info("\t\n-------------------------" + DateUtils.getCurrentDate() + "  无本息到账的理财账户", this.getClass());
			}
		} catch (Exception e) {

		}
	}

	public void returnPrinInteMessage(InvestList investList) throws Exception {
		Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
		//访问地址
		String urlStr ="";
		// 异步通知地址
		String noticeUrl="";
		// 商户号
		String merchantCode = ReadProperties.getStringValue(properties, "merchantCode");
		// 秘钥
		String akey = ReadProperties.getStringValue(properties, "akey");
		// 手续费
		String fee = ReadProperties.getStringValue(properties, "fee");
		
		// 生成一个流水号
		String requestId = DataUtils.randomUUID();
		// 计算到账金额（本金+利息）
		double prinIntSum = investList.getTotalMoney();
		// 用户信息
		MemberInfo memberInfo = investList.getMemberInfo();
		// 标信息
		Scale scale = investList.getScale();
		String singleOrSet = scale.getSingleOrSet();
		//本息到账报文方式(普通项目、协议投资项目)
		if(singleOrSet.equals(SingleOrSet.S1.getVal())){
			urlStr = ReadProperties.getStringValue(properties, "TransactionForFTURL");
			noticeUrl = ReadProperties.getStringValue(properties, "TransactionForFTURL_noticeUrl");
		}else if(singleOrSet.equals(SingleOrSet.S2.getVal())){
			//本息到账报文方式(集合理财项目)
			urlStr = ReadProperties.getStringValue(properties, "CollectiveFinanceURL");
			noticeUrl = ReadProperties.getStringValue(properties, "CollectiveFinanceURL_noticeUrl");
		}
		// 获取分账列表
		String subledgerList = getSubledgerList(memberInfo.getId());
		if(DataUtils.notEmpty(subledgerList)){
			// 数字签名字符串
			// 规范请求流水号(requestId)+商户编号(merchantCode)+项目编号(projectCode)+本息到账金额(sum)+手续费收取方式(payType)+
			// 分账列表(subledgerList)+异步通知地址(noticeUrl)+主账户类型(mainAccountType)+主账户编码(mainAccountCode)
			StringBuffer signatureBuffer = new StringBuffer();
			signatureBuffer.append(requestId).append(merchantCode).append(scale.getId()).append(prinIntSum).append(fee).append(subledgerList).append(noticeUrl);
			LoggerUtils.info("------------本息到账报文方式 数字签名字符串：" + signatureBuffer.toString(), this.getClass());
			// 加密后的数字签名
			String signature_sign = MD5Utils.hmacSign(signatureBuffer.toString(), akey);
			LoggerUtils.info("------------本息到账报文方式 签名加密：" + signature_sign.toString(), this.getClass());
			// 封装map参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("requestId", requestId);
			map.put("merchantCode", merchantCode);
			map.put("projectCode",scale.getId());
			map.put("sum", String.valueOf(prinIntSum));
			map.put("payType", fee);
			map.put("subledgerList",subledgerList);
			map.put("noticeUrl", String.valueOf(prinIntSum));
			map.put("mainAccountType", String.valueOf(prinIntSum));
			map.put("mainAccountCode", String.valueOf(prinIntSum));
			map.put("signature", signature_sign);
			
			//保存请求requestid
			RequestFlow requestFlow = new RequestFlow(DataUtils.randomUUID(), requestId, memberInfo.getId(), new Date(), "本息到账", "");
			requestFlowService.saveEntity(requestFlow);
			
			// 获取转换的参数
			JSONObject jsonObjectData = (JSONObject) SSLClient.getJsonObjectByUrl(urlStr,map,"GBK");
			//将赎回request保存
			investList.setCbRequestid(requestId);
			investListService.updateEntity(investList);
			
			// result 查询结果 00000代表成功
			String result = jsonObjectData.get("result").toString();
			if(result.equals(ResultCode.SUCCESS.getVal())){
				LoggerUtils.info("\t\n-------------------本息到账 成功！", this.getClass());
			}else if(result.equals(ResultCode.Doing.getVal())){
				LoggerUtils.info("\t\n-------------------本息到账 正在处理！", this.getClass());
			}else{
				LoggerUtils.info("\t\n-------------------本息到账 失败！，原因："+DataUtils.ISO2UTF8(jsonObjectData.getString(result)), this.getClass());
			}
			LoggerUtils.info("\t\n-------------------姓名："+memberInfo.getRealName()+"\t 电话："+memberInfo.getPhone(), this.getClass());
		}else{
			LoggerUtils.info("\t\n-------------------本息到账失败，原因：分账列表为空，姓名："+memberInfo.getRealName()+"\t 电话："+memberInfo.getPhone(), this.getClass());
		}
	}

}
