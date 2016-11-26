package org.duang.action.sys;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.BillInvest;
import org.duang.entity.LoanList;
import org.duang.entity.Product;
import org.duang.entity.Scale;
import org.duang.enums.Has;
import org.duang.enums.If;
import org.duang.enums.May;
import org.duang.enums.ResultCode;
import org.duang.enums.product.Category;
import org.duang.enums.scale.Status;
import org.duang.service.BillInvestService;
import org.duang.service.LoanListService;
import org.duang.service.ScaleService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.MD5Utils;
import org.duang.util.ReadProperties;
import org.duang.util.SSLClient;
import org.hibernate.criterion.Order;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 理财标Action
 * @ClassName: ScaleAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年7月26日 下午1:55:48
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "scale")
@ParentPackage("sys")
@Results(value = { 
		@Result(name = ResultPath.LIST, type = "dispatcher", location = "WEB-INF/page/sys/scale/scaleList.jsp"),
		@Result(name = ResultPath.ADD, type = "dispatcher", location = "WEB-INF/page/sys/scale/addScale.jsp"),
		@Result(name = ResultPath.EDIT, type = "dispatcher", location = "WEB-INF/page/sys/scale/editScale.jsp"),
		@Result(name = ResultPath.INFO, type = "dispatcher", location = "WEB-INF/page/sys/scale/infoScale.jsp"),
		@Result(name = "loanlistinfo", type = "dispatcher", location = "WEB-INF/page/sys/loanlist/loanlistinfo.jsp"),
		@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") 
})
public class ScaleAction extends BaseAction<Scale> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Properties properties;
	private ScaleService service;

	@Resource
	public void setService(ScaleService service) {
		this.service = service;
	}
	private LoanListService loanListService;
	@Resource
	public void setLoanListService(LoanListService loanListService) {
		this.loanListService = loanListService;
	}
	private BillInvestService billInvestService;
	@Resource
	public void setBillInvestService(BillInvestService billInvestService) {
		this.billInvestService = billInvestService;
	}
	
	/**   
	 * 根据分页查询理财标
	 * @Title: queryScaleByPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年8月9日 上午10:30:46
	 * @return: void      
	 * @throws   
	 */  
	public void queryScaleByPage() {
		try {
			condsUtils.addProperties(true, "product", "order");
			condsUtils.addValues(true, new Object[]{"myAlias","as"}, Order.desc("beginTime"));
			if (DataUtils.notEmpty(getRequest().getParameter("productName"))) {
				condsUtils.concat("myAlias.nameZh", new Object[]{URLDecoder.decode(getRequest().getParameter("productName"),"UTF-8"),"like"});
			}
			if (DataUtils.notEmpty(getRequest().getParameter("productNew"))) {
				condsUtils.concat("myAlias.isNewProduct", DataUtils.str2int(getRequest().getParameter("productNew")));
			}
			if (DataUtils.notEmpty(getRequest().getParameter("productTj"))) {
				condsUtils.concat("myAlias.isRecommend", DataUtils.str2int(getRequest().getParameter("productTj")));
			}
			if (DataUtils.notEmpty(entity.getName())) {
				condsUtils.concat("name", new Object[]{URLDecoder.decode(entity.getName(),"UTF-8"),"like"});
			}
			if (DataUtils.notEmpty(getRequest().getParameter("useTicket"))) {
				condsUtils.concat("useTicket", entity.getUseTicket());
			}
			if (DataUtils.notEmpty(getRequest().getParameter("transfer"))) {
				condsUtils.concat("transfer", entity.getTransfer());
			}
			if (DataUtils.notEmpty(getRequest().getParameter("isTurn"))) {
				condsUtils.concat("isTurn", entity.getIsTurn());
			}
			if (DataUtils.notEmpty(getRequest().getParameter("status"))) {
				condsUtils.concat("status", entity.getStatus());
			}
			if (DataUtils.notEmpty(entity.getTags())) {
				condsUtils.concat("tags", new Object[]{URLDecoder.decode(entity.getTags(),"UTF-8"),"like"});
			}
			if (DataUtils.notEmpty(getRequest().getParameter("beginDate1")) && DataUtils.notEmpty(getRequest().getParameter("beginDate2"))) {
				condsUtils.concat("beginTime", new Object[]{DateUtils.str2Date(getRequest().getParameter("beginDate1"), "yyyy-MM-dd"), DateUtils.str2Date(getRequest().getParameter("beginDate2"), "yyyy-MM-dd"), "between"});
			}
			@SuppressWarnings("rawtypes")
			List list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			fillDatagridCons(list);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财标ACTION方法queryScaleByPage错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财标ACTION方法queryScaleByPage错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("total", 0);
			jsonObject.put("currPage", 0);
			jsonObject.put("pageSize", 0);
			jsonObject.put("rows",new JSONArray());
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 填充List数据
	 * @Title: fillDatagridCons   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list  
	 * @author 5y    
	 * @date 2016年7月25日 下午5:08:47
	 * @return: void      
	 * @throws   
	 */  
	private void fillDatagridCons(@SuppressWarnings("rawtypes") List list) throws Exception {
		if (list !=null && list.size() > 0) {
			for(Object temp : list) {
				if (temp instanceof Object[]) {
					Map<String,Object> resultMap = new HashMap<String,Object>();
					Scale s = (Scale)((Object[])temp)[1];
					Product p = (Product)((Object[])temp)[0];
					if (s != null) {
						resultMap.put("id", s.getId());
						resultMap.put("name", s.getName());
						resultMap.put("beginTime", DateUtils.getTimeStamp(s.getBeginTime()));
						resultMap.put("endTime", DateUtils.getTimeStamp(s.getEndTime()));
						resultMap.put("calcBeginTime", DateUtils.getTimeStamp(s.getCalcBeginTime()));
						resultMap.put("calcEndTime", DateUtils.getTimeStamp(s.getCalcEndTime()));
						resultMap.put("turnDate", DateUtils.getTimeStamp(s.getTurnDate()));
						resultMap.put("timeLimit", s.getTimeLimit());
						resultMap.put("revenue", s.getRevenue());
						resultMap.put("revenueAdd", s.getRevenueAdd());
						resultMap.put("maxLimit", s.getMaxLimit());
						resultMap.put("singleOrSet", s.getSingleOrSet());
						resultMap.put("minLimit", s.getMinLimit());
						resultMap.put("returnStyle", s.getReturnStyle());
						resultMap.put("tags", s.getTags());
						resultMap.put("useTicket", May.valueOf("May"+s.getUseTicket()).toString());
						resultMap.put("transfer", May.valueOf("May"+s.getTransfer()).toString());
						resultMap.put("totalMoney", s.getTotalMoney());
						resultMap.put("residueMoney", s.getResidueMoney());
						resultMap.put("yetMoney", s.getYetMoney());
						resultMap.put("scoreBonus", Has.valueOf("Has"+s.getScoreBonus()).toString());
						resultMap.put("onesScore", s.getOnesScore());
						resultMap.put("status", Status.valueOf("S"+s.getStatus()).toString());
						resultMap.put("isTurn", If.valueOf("If"+s.getIsTurn()).toString());
					}
					if (p != null) {
						resultMap.put("productName", p.getNameZh());
						resultMap.put("productCategory", Category.valueOf("C"+p.getCategory()).toString());
						resultMap.put("productNew", If.valueOf("If"+p.getIsNewProduct()).toString());
						resultMap.put("productRecommend", If.valueOf("If"+p.getIsRecommend()).toString());
					}
					listMap.add(resultMap);
				}
			}
			jsonObject.put("total", getPageUtil().getCountRecords());
			jsonObject.put("currPage", getPageUtil().getCurrentPageNum());
			jsonObject.put("pageSize", getPageUtil().getPageRecords());
			jsonObject.put("rows", listMap);
		}else {
			jsonObject.put("total", 0);
			jsonObject.put("currPage", 0);
			jsonObject.put("pageSize", 0);
			jsonObject.put("rows",new JSONArray());
		}
	}


	/**   
	 * 新增
	 * @Title: saveScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年8月9日 上午11:05:01
	 * @return: void      
	 * @throws   
	 */  
	public void saveScale() {
		try {
			if (entity != null) {
				entity.setId(DataUtils.randomUUID());
				if (service.saveEntity(entity)) {
					jsonObject.put("success", true);
				}else{
					jsonObject.put("success", false);
				}
			}else {
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财标ACTION方法saveScale错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财标ACTION方法saveScale错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 更新
	 * @Title: updateScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年8月9日 上午11:39:48
	 * @return: void      
	 * @throws   
	 */  
	public void updateScale() {
		try {
			if (entity.getInvestList()!=null && DataUtils.isEmpty(entity.getInvestList().getId())) {
				entity.setInvestList(null);
			}
			if (service.updateEntity(entity)) {
				jsonObject.put("success", true);
			}else{
				jsonObject.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财标ACTION方法updateScale错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财标ACTION方法updateScale错误："+e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
		} finally {
			printJsonResult();
		}
	}


	/**   
	 * 页面跳转
	 * @Title: openDialog   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年8月9日 下午2:49:25
	 * @return: String      
	 * @throws   
	 */  
	public String openDialog() {
		try {
			String path = getRequest().getParameter("path");
			if(ResultPath.ADD.equals(path)) {
				return ResultPath.ADD;
			} else if(ResultPath.EDIT.equals(path)) {
				if (entity != null && DataUtils.notEmpty(entity.getId())) {
					entity = service.findById(entity.getId());
				}
				return ResultPath.EDIT;
			} else if (ResultPath.INFO.equals(path)) {
				if (entity != null && DataUtils.notEmpty(entity.getId())) {
					entity = service.findById(entity.getId());
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("productName", entity.getProduct().getName());
					map.put("product.id", entity.getProduct().getId());
					map.put("name", entity.getName());
					map.put("beginTime", DateUtils.date2Str(entity.getBeginTime(), "yyyy-MM-dd"));
					map.put("endTime", DateUtils.date2Str(entity.getEndTime(), "yyyy-MM-dd"));
					map.put("calcBeginTime", DateUtils.date2Str(entity.getCalcBeginTime(), "yyyy-MM-dd"));
					map.put("calcEndTime", DateUtils.date2Str(entity.getCalcEndTime(), "yyyy-MM-dd"));
					map.put("turnDate", DateUtils.date2Str(entity.getTurnDate(), "yyyy-MM-dd"));
					map.put("timeLimit", entity.getTimeLimit());
					map.put("revenue", entity.getRevenue());
					map.put("revenueAdd", entity.getRevenueAdd());
					map.put("maxLimit", entity.getMaxLimit());
					map.put("returnStyle", entity.getReturnStyle());
					map.put("tags", entity.getTags());
					map.put("useTicket", May.valueOf("May"+entity.getUseTicket()).toString());
					map.put("transfer", May.valueOf("May"+entity.getTransfer()).toString());
					map.put("totalMoney", entity.getTotalMoney());
					map.put("residueMoney", entity.getResidueMoney());
					map.put("yetMoney", entity.getYetMoney());
					map.put("scoreBonus", Has.valueOf("Has"+entity.getScoreBonus()).toString());
					map.put("onesScore", entity.getOnesScore());
					map.put("singleOrSet", entity.getSingleOrSet());
					map.put("minLimit", entity.getMinLimit());
					map.put("status", Status.valueOf("S"+entity.getStatus()).toString());
					map.put("isTurn", If.valueOf("If"+entity.getIsTurn()).toString());
					getRequest().setAttribute("info", map);
				}
				return ResultPath.INFO;
			}else if ("loanlistinfo".equals(path)) {
				getRequest().setAttribute("scaleid", getRequest().getParameter("scaleid"));
				return "loanlistinfo";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财标ACTION方法getScaleInfo错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财标ACTION方法getScaleInfo错误："+e.getLocalizedMessage(), this.getClass());
		}
		return ResultPath.LIST;
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
	 * 满标放款操作
	 * @Title: loanFullScaleToUser   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月24日 下午4:40:33
	 * @return: void      
	 * @throws
	 */
	public void loanFullScaleToUser(){
		try{
			String id = getRequest().getParameter("id");
			Scale scale = service.findById(id);
			//2.查询满标中借贷列表
			List<LoanList> loanLists = queryLoanListByScale(id);
			//3.进行
			for(LoanList loanList : loanLists){
				fullScaleLoanMoney(loanList,scale);
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("理财标 ACTION 方法loanFullScaleToUser错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财标 ACTION 方法loanFullScaleToUser错误："+e.getLocalizedMessage(), this.getClass());
		}
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
    	memberInfoId = "da5d2a3f259c4a2994ba22ab8b9d2d5f";
    	//List<BillInvest> billInvests = billInvestService.queryEntity("memberInfo.id", memberInfoId, null, Order.desc("optTime"));
    	StringBuffer paramBuffer = new StringBuffer("[{");
    	//if(DataUtils.notEmpty(billInvests)){
    		//BillInvest billInvest = billInvests.get(0);
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
             .append("\"500\"")
    		 .append("}")
    		 .append("]");
    		 
    	//}
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
    private double queryLoanListMoney(String scaleId) throws Exception{
    	String sql = "select sum(get_money-yet_money) from loan_list ll  left join scale_loan_list sll on ll.id=sll.loan_list where sll.scale='"+scaleId
    			+"' and apply_state=2 and loan_state=1";
    	List<?> loanLists = loanListService.queryBySQL(sql, null, null,false);
    	return Double.parseDouble(loanLists.get(0).toString());
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
    	LoggerUtils.info("\t\n------------满标放款  "+scale.getName()+"  退回"+loanList.getMemberInfo().getRealName()+"的投资金额", this.getClass());
    	//生成一个流水号
		String requestId = DataUtils.randomUUID();
		//放款金额
		double prinMoney = 500;
		//获取分账列表
		String subledgerList = getSubledgerList(loanList.getMemberInfo().getId());
		Map<String , String> keyMap = getURLCodeAkey("fullScaleURL","fullScaleCallbackURL");
		//数字签名字符串
		//规范请求流水号(requestId)+商户编号(merchantCode)+项目编号(projectCode)+本息到账金额(sum)+手续费收取方式(payType)+
		//分账列表(subledgerList)+异步通知地址(noticeUrl)+主账户类型(mainAccountType)+主账户编码(mainAccountCode)
		StringBuffer signatureBuffer = new StringBuffer();
		signatureBuffer.append(requestId).append(keyMap.get("merchantCode")).append(scale.getId()).append(prinMoney).append(keyMap.get("fee"))
					   .append(subledgerList).append(keyMap.get("noticeURL"));
		LoggerUtils.info("\t\n------------满标放款 数字签名字符串："+signatureBuffer.toString(), this.getClass());
		//加密后的数字签名
		String signature_sign=MD5Utils.hmacSign(signatureBuffer.toString(), keyMap.get("akey"));
		LoggerUtils.info("\t\n------------满标放款 签名加密："+signature_sign.toString(), this.getClass());
		//封装map参数
		Map<String,String> map = new HashMap<String, String>();
		map.put("requestId",requestId);
		map.put("merchantCode",keyMap.get("merchantCode"));
		map.put("projectCode",scale.getId());
		map.put("sum",String.valueOf(prinMoney));
		map.put("payType",keyMap.get("fee"));
		map.put("subledgerList",subledgerList);
		map.put("noticeUrl",keyMap.get("noticeURL"));
		map.put("signature",signature_sign);
		//获取转换的参数
		JSONObject jsonObjectData = SSLClient.getJsonObjectByUrl(keyMap.get("url"),map,"GBK");
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
				LoggerUtils.info("\t\n------------放款金额："+jsonObjectData.get("sum"), this.getClass());
				LoggerUtils.info("\t\n------------数字签名："+jsonObjectData.get("signature"), this.getClass());
			}else{
				LoggerUtils.info("\t\n------------签名不一致", this.getClass());
			}
		}else{
			LoggerUtils.info("\t\n------------满标放款失败 ，原因"+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, back_result)), this.getClass());
		}
    }
	
	
	
	/**
	 * 流标赎回异步回调
	 * @Title: failScaleFFCallback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月11日 下午5:18:20
	 * @return: void      
	 * @throws
	 */
	public void failScaleFFCallback(){
		try{
			//读取配置文件中
			Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
			String requestId = getRequest().getParameter("requestId");
			String result = getRequest().getParameter("result");
			if(result.equals(ResultCode.SUCCESS.getVal())){
				//本息到账金额
				String sum = getRequest().getParameter("sum");
				//项目编号
				String projectCode = getRequest().getParameter("projectCode");
				//项目总余额
				String projectSum = getRequest().getParameter("projectSum");
				String signature = getRequest().getParameter("signature");
				
				//返回数据进行签名拼接
				StringBuffer back_data_str = new StringBuffer();
				back_data_str.append(requestId).append(projectCode).append(result);
				LoggerUtils.info("\t\n------------返回数据签名字符串拼接："+back_data_str, this.getClass());
				//返回数据加密后的签名
				String back_data_sign = MD5Utils.hmacSign(back_data_str.toString(), ReadProperties.getStringValue(properties, "akey"));
				LoggerUtils.info("\t\n------------返回数据签名字符串加密："+back_data_sign, this.getClass());		
				if(back_data_sign.equals(signature)){
					service.failScaleBackMoney(projectCode, DataUtils.str2double(sum, 6));
				}else{
					LoggerUtils.error("\t\n------------流标赎回失败 ，原因签名不一致", this.getClass());
				}
			}else{
				LoggerUtils.error("\t\n------------流标赎回失败 ，原因"+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)), this.getClass());
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("MemberAction realNameAuthCallback：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction realNameAuthCallback：" + e.getLocalizedMessage(), this.getClass());
		}

	}
	
	/**
	 * 满标放款处理结果异步返回
	 * @Title: fullLoanMoneyCallback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月22日 下午1:52:11
	 * @return: void      
	 * @throws
	 */
	public void fullLoanMoneyCallback(){
		LoggerUtils.info("\t\n---------------------------------------满标放款异步回调处理", this.getClass());
		String projectCode = "";
		try{
			//读取配置文件中
			Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
			String result = getRequest().getParameter("result");
			if(result.equals(ResultCode.SUCCESS.getVal())){
				String requestId = getRequest().getParameter("requestId");
				//本息到账金额
				String sum = getRequest().getParameter("sum");
				//项目编号
				projectCode = getRequest().getParameter("projectCode");
				//项目还款账户余额
				String mainAccountCode = getRequest().getParameter("mainAccountCode");
				//主账户编码
				String mainAccountType = getRequest().getParameter("mainAccountType");
				//手续费收取方式
				String payType = getRequest().getParameter("payType");
	
				String signature = getRequest().getParameter("signature");
				
				//返回数据进行签名拼接
				StringBuffer back_data_str = new StringBuffer();
				back_data_str.append(requestId).append(projectCode).append(result);
				LoggerUtils.info("\t\n------------请求编号："+requestId+"  返回数据签名字符串拼接："+back_data_str, this.getClass());
				//返回数据加密后的签名
				String back_data_sign = MD5Utils.hmacSign(back_data_str.toString(), ReadProperties.getStringValue(properties, "akey"));
				LoggerUtils.info("\t\n------------请求编号："+requestId+"  返回数据签名字符串加密："+back_data_sign, this.getClass());		
				if(back_data_sign.equals(signature)){
					service.fullScaleLoanMoney(projectCode, DataUtils.str2double(sum, 6));
				}else{
					LoggerUtils.info("\t\n------------满标放款 签名不一致 ，请求编号："+requestId, this.getClass());
				}
			}else{
				LoggerUtils.info("满标放款失败，标的id:"+projectCode+"原因"+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)), this.getClass());
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("MemberAction realNameAuthCallback：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction realNameAuthCallback：" + e.getLocalizedMessage(), this.getClass());
		}
	}
	
}
