package org.duang.task;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.duang.common.CondsUtils;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.BillInvest;
import org.duang.entity.LoanList;
import org.duang.entity.Scale;
import org.duang.enums.ResultCode;
import org.duang.service.BillInvestService;
import org.duang.service.LoanListService;
import org.duang.service.ScaleService;
import org.duang.util.DataUtils;
import org.duang.util.MD5Utils;
import org.duang.util.ReadProperties;
import org.duang.util.SSLClient;
import org.hibernate.criterion.Order;
import org.json.JSONObject;

public class QueryFullScale{
 
	private Properties properties;
	//判断作业是否执行的旗标  
    private boolean isRunning = false;
    
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
	private LoanListService loanListService;
	@Resource
	public void setLoanListService(LoanListService loanListService) {
		this.loanListService = loanListService;
	}
    //定义任务执行体  
    public void run()    
    {  
        if (!isRunning)  
        {  
        	try{
        		//1.查询满标
        		List<Scale> scales = queryTodayFullScale();
        		for(Scale scale : scales){
        			//2.查询满标中借贷列表
        			List<LoanList> loanLists = queryLoanListByScale(scale.getId());
        			//3.进行
        			for(LoanList loanList : loanLists){
        				fullScaleLoanMoney(loanList,scale);
        			}
        		}
        	}catch(Exception e){
        		LoggerUtils.error("QueryFullScale-----定时查询满标错误："+e.getMessage(), this.getClass());
        		e.printStackTrace();
        	}
        }  
    }  
    
	/**
	 * 查询满标
	 * @Title: queryTodayFullScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月11日 上午11:29:53
	 * @return: List<Scale>      
	 * @throws
	 */
    private List<Scale> queryTodayFullScale() throws Exception{
    	//获取昨天的日期
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, -1);
    	//查询到期的标
    	CondsUtils condsUtils = new CondsUtils();
    	condsUtils.addProperties(true, "endTime", "status");
		condsUtils.addValues(true, cal.getTime(), org.duang.enums.loan.Scale.S3.getVal());
		List<Scale> scaleList = scaleService.queryEntity("endTime", cal.getTime(), null, null);
    	return scaleList;
    }
    
    /**
     * 查询借贷列表
     * @Title: queryLoanListByScale   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param scaleId
     * @param: @return
     * @param: @throws Exception  
     * @author LiYonghui    
     * @date 2016年11月11日 下午2:07:29
     * @return: List<InvestList>      
     * @throws
     */
    private List<LoanList> queryLoanListByScale(String scaleId) throws Exception{
    	String sql = "select * from loan_list ll  left join scale_loan_list sll on ll.id=sll.loan_list where sll.scale='"+scaleId
    			+"' and apply_state=2 and loan_state=1";
    	List<LoanList> loanLists = loanListService.queryBySQL(sql, null, null,true);
    	return loanLists;
    }
    
    /**
     * 查询借贷列表
     * @Title: queryLoanListByScale   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param scaleId
     * @param: @return
     * @param: @throws Exception  
     * @author LiYonghui    
     * @date 2016年11月11日 下午2:07:29
     * @return: List<InvestList>      
     * @throws
     */
    private int queryLoanListMoney(String scaleId) throws Exception{
    	String sql = "select sum(get_money-yet_money) from loan_list ll  left join scale_loan_list sll on ll.id=sll.loan_list where sll.scale='"+scaleId
    			+"' and apply_state=2 and loan_state=1";
    	List<?> loanLists = loanListService.queryBySQL(sql, null, null,false);
    	return Integer.parseInt(loanLists.get(0).toString());
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
     * 获取 url，商户号，秘钥，手续费
     * 
     * @Title: getURLCodeAkey   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return
     * @param: @throws IOException  
     * @author LiYonghui    
     * @date 2016年11月11日 下午2:41:37
     * @return: Map<String,String>      
     * @throws
     */
    private Map<String, String> getURLCodeAkey(String urlKey,String noticeURLKey) throws IOException{
    	properties = ReadProperties.initPrperties("sumapayURL.properties");
		//本息到账url
		String urlStr = ReadProperties.getStringValue(properties, urlKey);
		//商户号
		String merchantCode = ReadProperties.getStringValue(properties, "merchantCode");
		//秘钥
		String akey = ReadProperties.getStringValue(properties, "akey");
		//手续费
		String fee = ReadProperties.getStringValue(properties, "fee");
		//noticeURL
		String noticeURL = ReadProperties.getStringValue(properties, noticeURLKey);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", urlStr);
		map.put("merchantCode", merchantCode);
		map.put("akey", akey);
		map.put("fee", fee);
		map.put("noticeURL", noticeURL);
		return map;
    }
    
    /**
     * 满标，进行放款
     * @throws Exception 
     * @Title: failScaleBackInvestorMoney   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param investLists  
     * @author LiYonghui    
     * @date 2016年11月11日 下午2:47:02
     * @return: void      
     * @throws
     */
    private void fullScaleLoanMoney(LoanList loanList,Scale scale) throws Exception{
    	LoggerUtils.info("\t\n------------流标赎回  "+scale.getName()+"  退回"+loanList.getMemberInfo().getRealName()+"的投资金额", this.getClass());
    	//生成一个流水号
		String requestId = DataUtils.randomUUID();
		//放款金额
		double prinMoney = queryLoanListMoney(scale.getId());
		//获取分账列表
		String subledgerList = getSubledgerList(loanList.getMemberInfo().getId());
		Map<String , String> keyMap = getURLCodeAkey("fullScaleURL","fullScaleCallbackURL");
		//数字签名字符串
		//规范请求流水号(requestId)+商户编号(merchantCode)+项目编号(projectCode)+本息到账金额(sum)+手续费收取方式(payType)+
		//分账列表(subledgerList)+异步通知地址(noticeUrl)+主账户类型(mainAccountType)+主账户编码(mainAccountCode)
		StringBuffer signatureBuffer = new StringBuffer();
		signatureBuffer.append(requestId).append(keyMap.get("merchantCode")).append(scale.getId()).append(prinMoney).append(keyMap.get("fee"))
					   .append(subledgerList).append(keyMap.get("noticeURL"));
		LoggerUtils.info("\t\n------------流标赎回 数字签名字符串："+signatureBuffer.toString(), this.getClass());
		//加密后的数字签名
		String signature_sign=MD5Utils.hmacSign(signatureBuffer.toString(), keyMap.get("akey"));
		LoggerUtils.info("\t\n------------流标赎回 签名加密："+signature_sign.toString(), this.getClass());
		//封装map参数
		Map<String,String> map = new HashMap<String, String>();
		map.put("requestId",requestId);
		map.put("merchantCode",map.get("merchantCode"));
		map.put("projectCode",scale.getId());
		map.put("sum",String.valueOf(prinMoney));
		map.put("payType",keyMap.get("fee"));
		map.put("subledgerList",subledgerList);
		map.put("noticeUrl",map.get("noticeUrl"));
		map.put("signature",signature_sign);
		//获取转换的参数
		JSONObject jsonObjectData = (JSONObject) SSLClient.getJsonObjectByUrl(keyMap.get("url"),map,"GBK");
		//result 查询结果  00000代表成功
		String back_result = jsonObjectData.get("result").toString();
		if(back_result.equals(ResultCode.SUCCESS.getVal())){
			LoggerUtils.info("\t\n------------满标放款受理成功", this.getClass());
			String back_projectCode = jsonObjectData.get("projectCode").toString();
			String back_requestId = jsonObjectData.get("requestId").toString();
			//返回的签名
			String back_signature = jsonObjectData.get("signature").toString();
			//返回数据进行签名拼接
			StringBuffer back_data_str = new StringBuffer();
			back_data_str.append(back_requestId).append(back_projectCode).append(back_result);
			LoggerUtils.info("\t\n------------返回数据签名字符串拼接："+back_data_str, this.getClass());
			//返回数据加密后的签名
			String back_data_sign = MD5Utils.hmacSign(back_data_str.toString(), keyMap.get("akey"));
			LoggerUtils.info("\t\n------------返回数据签名字符串加密："+back_data_sign, this.getClass());		
			
			if(back_data_sign.equals(back_signature)){
				LoggerUtils.info("\t\n------------请求流水号："+back_requestId, this.getClass());
				LoggerUtils.info("\t\n------------项目编号："+back_projectCode, this.getClass());
				LoggerUtils.info("\t\n------------处理结果："+back_result, this.getClass());
				LoggerUtils.info("\t\n------------手续费收取方式："+jsonObjectData.get("payType"), this.getClass());
				LoggerUtils.info("\t\n------------主账户类型："+jsonObjectData.get("mainAccountType"), this.getClass());
				LoggerUtils.info("\t\n------------主账户编码："+jsonObjectData.get("mainAccountCode"), this.getClass());
				LoggerUtils.info("\t\n------------本息到账金额："+jsonObjectData.get("sum"), this.getClass());
				LoggerUtils.info("\t\n------------请求时间："+jsonObjectData.get("requestTime"), this.getClass());
				LoggerUtils.info("\t\n------------数字签名："+jsonObjectData.get("signature"), this.getClass());
			}else{
				LoggerUtils.info("\t\n------------签名不一致", this.getClass());
			}
		}else{
			LoggerUtils.info("\t\n------------满标放款失败 ，原因"+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, back_result)), this.getClass());
		}
    }
    
}
