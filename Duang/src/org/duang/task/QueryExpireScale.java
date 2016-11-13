package org.duang.task;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.duang.action.provider.InvestListAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.BillInvest;
import org.duang.entity.InvestList;
import org.duang.entity.Scale;
import org.duang.enums.ResultCode;
import org.duang.service.BillInvestService;
import org.duang.service.InvestListService;
import org.duang.service.ScaleService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.MD5Utils;
import org.duang.util.ReadProperties;
import org.duang.util.SSLClient;
import org.hibernate.criterion.Order;
import org.json.JSONObject;

public class QueryExpireScale{

	//判断作业是否执行的旗标  
    private boolean isRunning = false;  
    //定义任务执行体  
    public void run()    
    {  
        if (!isRunning)  
        {  
            System.out.println("开始调度自动打卡");  
        }  
    }  

    private ScaleService scaleService;
    @Resource(name = "scaleserviceimpl")
	public void setScaleService(ScaleService scaleService) {
		this.scaleService = scaleService;
	}
   
    private BillInvestService billInvestService;
	@Resource
	public void setBillInvestService(BillInvestService billInvestService) {
		this.billInvestService = billInvestService;
	}
	private InvestListService investListService;
	@Resource
	public void setService(InvestListService investListService) {
		this.investListService = investListService;
	}
    /**
     * 查询昨天是否有到期的标（为什么是昨天呢，因为是每天的凌晨2点才开始执行，所以是昨天）
     * @Title: queryTodayExpireScale   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return
     * @param: @throws Exception  
     * @author LiYonghui    
     * @date 2016年11月9日 上午11:44:34
     * @return: List<Scale>      
     * @throws
     */
    private List<Scale> queryTodayExpireScale() throws Exception{
    	//获取昨天的日期
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, -1);
    	//查询到期的标
    	List<Scale> scaleList = scaleService.queryEntity("endTime", cal.getTime(), null, null);
    	return scaleList;
    }
    
    /**
     * 获取 分账列表，对应记录一笔用户的进账明细；记录一条或多条商户的进账明细。
     * @Title: getSubledgerList   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param memberInfoId
     * @param: @return
     * @param: @throws Exception  
     * @author LiYonghui    
     * @date 2016年11月9日 下午2:31:42
     * @return: String      返回拼接好的参数类型   a=b&c=d 
     * @throws
     */
    private String getSubledgerList(String memberInfoId) throws Exception{
    	List<BillInvest> billInvests = billInvestService.queryEntity("memberInfo.id", memberInfoId, null, Order.desc("optTime"));
    	StringBuffer paramBuffer = new StringBuffer("{");
    	if(DataUtils.notEmpty(billInvests)){
    		BillInvest billInvest = billInvests.get(0);
    		 paramBuffer.append("roleType")
             .append(":")
             .append(URLEncoder.encode("0", "gbk"))
             .append(",")
             .append("roleCode")
             .append(":")
             .append(URLEncoder.encode(memberInfoId, "gbk"))
             .append(",")
             .append("inOrOut")
             .append(":")
             .append(URLEncoder.encode("0", "gbk"))
             .append(",")
             .append("sum")
             .append(":")
             .append(URLEncoder.encode(String.valueOf(billInvest.getMoney()), "gbk"))
    		 .append("}");
    		 
    	}
    	return paramBuffer.toString();
    }
    
    /**
     * 本息到账
     */
    private void ransomExpireScale(){
    	try{
    	//1.查询到期的标
    	List<Scale> scales = queryTodayExpireScale();
    	if(DataUtils.notEmpty(scales)){
    		Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
    		//本息到账url
    		String urlStr = ReadProperties.getStringValue(properties, "prinInteMessageURL");
    		//商户号
    		String merchantCode = ReadProperties.getStringValue(properties, "merchantCode");
    		//秘钥
    		String akey = ReadProperties.getStringValue(properties, "akey");
    		//手续费
    		String fee = ReadProperties.getStringValue(properties, "fee");
    		for (Scale scale : scales) {
    			List<InvestList> invests = queryInvestListByScale(scale.getId());
    			for(InvestList investList : invests){
    				returnPrinInteMessage(urlStr,merchantCode,akey,fee,scale,investList);
    			}
			}
    		
    	}
    	}catch(Exception e){
    		
    	}
    }
    
    
    private List<InvestList> queryInvestListByScale(String scaleId) throws Exception{
    	String sql = "select * from invest_list where scale_id='"+scaleId+"' and status=2 and isTurn=0";
    	List<InvestList> investLists = investListService.queryBySQL(sql, null, null,true);
    	return investLists;
    }
    
    public void returnPrinInteMessage(String urlStr,String merchantCode,String akey,String fee,Scale scale,InvestList investList) throws Exception{
		//生成一个流水号
		String requestId = DataUtils.randomUUID();
		//计算到账金额（本金+利息）
		double prinMoney = investList.getMoney(); //本金
		double total_revenue = scale.getRevenue()+scale.getRevenueAdd();//收益率
		double incoming = total_revenue/365D*investList.getDays();//收益金额
		double prinIntSum = prinMoney+incoming;//本息金额
		//获取分账列表
		String subledgerList = getSubledgerList(investList.getMemberInfo().getId());
		//异步通知地址
		String noticeUrl = "";
		
		//数字签名字符串
		//规范请求流水号(requestId)+商户编号(merchantCode)+项目编号(projectCode)+本息到账金额(sum)+手续费收取方式(payType)+
		//分账列表(subledgerList)+异步通知地址(noticeUrl)+主账户类型(mainAccountType)+主账户编码(mainAccountCode)
		StringBuffer signatureBuffer = new StringBuffer();
		signatureBuffer.append(requestId).append(merchantCode).append(scale.getId()).append(prinIntSum).append(fee)
					   .append(subledgerList).append(noticeUrl);
		LoggerUtils.info("------------查询银行卡信息 数字签名字符串："+signatureBuffer.toString(), this.getClass());
		//加密后的数字签名
		String signature_sign=MD5Utils.hmacSign(signatureBuffer.toString(), akey);
		LoggerUtils.info("------------查询银行卡信息 签名加密："+signature_sign.toString(), this.getClass());
		//封装map参数
		Map<String,String> map = new HashMap<String, String>();
		map.put("requestId",requestId);
		map.put("merchantCode",merchantCode);
		//map.put("userIdIdentity",userIdIdentity);
		//map.put("queryType",queryType);
		map.put("signature",signature_sign);
		//获取转换的参数
		//JSONObject jsonObjectData = SSLClient.getJsonObjectByUrl(urlStr,map,"GBK");
		//result 查询结果  00000代表成功
		//String resultCallbace = jsonObjectData.get("result").toString();
		
    }
    
}
