package org.duang.action.provider;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.duang.entity.InvestList;
import org.duang.entity.MemberInfo;
import org.duang.entity.Product;
import org.duang.entity.RequestFlow;
import org.duang.entity.Scale;
import org.duang.enums.If;
import org.duang.enums.Platform;
import org.duang.enums.ResultCode;
import org.duang.enums.invest.Status;
import org.duang.enums.invest.TurnStatus;
import org.duang.enums.invest.UseTicket;
import org.duang.enums.product.Category;
import org.duang.service.InvestListService;
import org.duang.service.MemberInfoService;
import org.duang.service.RequestFlowService;
import org.duang.service.ScaleService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.MD5Utils;
import org.duang.util.ReadProperties;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————投资记录Action
 * @ClassName:  InvestListAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月9日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_investlist")
public class InvestListAction extends BaseAction<InvestList>{

	private InvestListService investListService;
	private ScaleService scaleService;
	@Resource
	public void setService(InvestListService investListService) {
		this.investListService = investListService;
	}
	@Resource
	public void setScaleService(ScaleService scaleService) {
		this.scaleService = scaleService;
	}
	private MemberInfoService memberInfoService;
	@Resource
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
	}
	
	private RequestFlowService requestFlowService;
	@Resource
	public void setRequestFlowService(RequestFlowService requestFlowService) {
		this.requestFlowService = requestFlowService;
	}
	/**   
	 * 获取首页推荐标信息
	 * @Title: getHomeRecommendScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年10月9日 下午4:56:10
	 * @return: void      
	 * @throws   
	 */  
	public void getHomeRecommendScale(){
		boolean success = false;
		try {
			condsUtils.addProperties(true, "product", "myAlias.isRecommend", "myAlias.isSell", "status","status", "order");
			condsUtils.addValues(true, new Object[]{"myAlias","as"}, 1, 1, new Object[]{0, "ne"}, new Object[]{1, "ne"}, Order.asc("myAlias.days"));
			List<Scale> list = scaleService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
			if (DataUtils.notEmpty(list)) {
				for (int i = 0; i < 5; i++) {
					try {
						Scale scale = list.get(i);
						Map<String, Object> map = new HashMap<String, Object>();
						//时长、起投、预期收益率、附加预期收益率、已投金额、剩余可投金额、标总额、产品类型、产品名称、标名称
						Product product = scale.getProduct();
						map.put("id", scale.getId());
						map.put("day", product.getDays());
						map.put("min", scale.getMinLimit());
						map.put("singleOrSet", scale.getSingleOrSet());
						map.put("revenue", scale.getRevenue()*100);
						map.put("revenueAdd", scale.getRevenueAdd()*100);
						map.put("yetmoney", scale.getYetMoney());
						map.put("residuemoney", scale.getResidueMoney());
						map.put("totalmoney", scale.getTotalMoney());
						map.put("productcategory", Category.valueOf("C"+product.getCategory()).toString());
						map.put("productname", product.getNameZh());
						map.put("scalename", scale.getName());
						listMap.add(map);
					} catch (Exception e) {
						break;
					}
				}
			}else {
				msg = "暂无推荐";
			}
			jsonObject.put("result", listMap);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("InvestListAction——getHomeRecommendScale方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("InvestListAction——getHomeRecommendScale方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 增加投资记录
	 * @Title: addInvestList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 上午10:55:47
	 * @return: void      
	 * @throws  
	 */  
	public synchronized void addInvestList(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String p_scaleId = DES.decryptDES(getRequest().getParameter("p_scaleId"));
			String p_money = DES.decryptDES(getRequest().getParameter("p_money"));
			String p_useTicket = DES.decryptDES(getRequest().getParameter("p_useTicket"));
			String p_ticketBonus = DES.decryptDES(getRequest().getParameter("p_ticketBonus"));
			String p_totalMoney = DES.decryptDES(getRequest().getParameter("p_totalMoney"));
			String investStyle = DES.decryptDES(getRequest().getParameter("investStyle"));
			
			/*String p_scaleId = "d3f2efe1b4b24b4fa32927dd72859911";
			String p_money = "5000";
			String p_useTicket = "1";
			String p_ticketBonus = "20";
			String p_totalMoney = "5020";
			String investStyle = "3";*/
			
			String id = "";
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,memberInfoService).getMainField(token))){
				double money = DataUtils.str2double(p_money, 6);
				if (DataUtils.isEmpty(p_scaleId)) {
					msg = "理财标主键传值失败";
				}else if (money <= 0) {
					msg = "投资金额需大于0";
				}else if (!("1".equals(p_useTicket) || "2".equals(p_useTicket))) {
					msg = "理财券使用类型出错";
				}else{
					//封装理财信息
					InvestList investList = new InvestList();
					investList.setId(DataUtils.randomUUID());
					investList.setMoney(money);
					investList.setUseTicket(DataUtils.str2int(p_useTicket));
					investList.setTicketBonus(Double.valueOf(p_ticketBonus));
					investList.setTotalMoney(Double.parseDouble(p_totalMoney));
					investList.setInvestStyle(Integer.parseInt(investStyle));
					investList.setMemberInfo(new MemberInfo(id));
					//根据理财标和投资本金计算本金和预期收益和
					//查找理财标
					Scale scale = scaleService.findById(p_scaleId);
					//理财天数
					int day = scale.getProduct().getDays();
					//收益
					double income = money * (scale.getRevenue() + scale.getRevenueAdd()) / 365D * day;
					income = DataUtils.str2double(income+"", 6);
					investList.setScale(scale);
					investList.setIncome(income);
					investList.setStatus(Status.S2.getVal());
					investList.setOpenDate(new Date());
					String pactNumber = DateUtils.date2Str(new Date(), "MMDDhhmmss") + DataUtils.sixNumber();
					investList.setPactNumber(pactNumber);
					investList.setDays(day);
					success = investListService.saveEntity(investList);
					jsonObject.put("name", scale.getName());
					jsonObject.put("productName", scale.getProduct().getName());
					jsonObject.put("pactNumber", getRequest().getAttribute("contractNo"));
					jsonObject.put("memberName", getRequest().getAttribute("memberName"));
					jsonObject.put("calcBeginTime", DateUtils.date2Str(scale.getCalcBeginTime()));
					jsonObject.put("money", money);
					jsonObject.put("profitMoney", income);
					jsonObject.put("memberIdcard", getRequest().getAttribute("memberIdcard"));
				}
			}else{
				msg = "token失效！登录失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("InvestListAction——addInvestList方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("InvestListAction——addInvestList方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	/**   
	 * 查询理财记录列表
	 * @Title: queryInvestList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午4:25:24
	 * @return: void      
	 * @throws   
	 */  
	public void queryInvestList(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			//下拉次数
			String count = getRequest().getParameter("count");
			//单次获取标个数
			String num = getRequest().getParameter("num");
			//判断参数是否为空
			String id = "";
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,memberInfoService).getMainField(token))){
				//初始化下滑次数
				int currentPageNum = 1;
				//初始化获取的条数
				int countRecords = 20;
				if(DataUtils.notEmpty(count)){
					currentPageNum = Integer.parseInt(count);
				}
				if(DataUtils.notEmpty(count)){
					countRecords = Integer.parseInt(num);
				}
				getPageUtil().setCountRecords(countRecords);
				getPageUtil().setCurrentPageNum(currentPageNum);
				condsUtils.addProperties(true, "memberInfo","memberAlias.id", "scale", "order");
				condsUtils.addValues(true, new Object[]{"memberAlias","as"},id,new Object[]{"scaleAlias","as"}, Order.desc("openDate"));
				@SuppressWarnings("rawtypes")
				List list = investListService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
				success = true;
				if (list == null || list.size()==0) {
					msg = "已经到最后了";
					jsonObject.put("result", listMap);
				}else{
					jsonObject.put("result", fillDatagridCons(list));
				} 
			}else{
				msg = "token失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("InvestListAction——queryInvestList方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("InvestListAction——queryInvestList方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	/**
	 * 根据id获取信息
	 * @Title: findInvestById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年10月17日 下午4:11:25
	 * @return: InvestList      
	 * @throws
	 */
	public InvestList findInvestById(String id) throws Exception{
		InvestList invest = investListService.findById(id);
		return invest;
	}

	/**   
	 * 根据id查询理财记录详情
	 * @Title: findInvestInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 上午11:33:23
	 * @return: void      
	 * @throws   
	 */  
	public void findInvestInfo(){
		boolean success = false;
		try {
			//判断参数是否为空
			if(entity != null && DataUtils.notEmpty(entity.getId())){
				InvestList invest = investListService.findById(DES.decryptDES(entity.getId()));
				success = true;
				if (invest == null) {
					msg = "数据过期，请刷新重试";
					jsonObject.put("result", null);
				}else{
					jsonObject.put("result", fillDatagridObejct(invest));
				} 
			}else{
				msg = "参数为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("InvestListAction——findInvestInfo方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("InvestListAction——findInvestInfo方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	/**
	 * @Title: fillDatagridCons   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年9月12日 下午2:32:10
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDatagridCons(@SuppressWarnings("rawtypes") List list) throws Exception {
		if (list !=null && list.size() > 0) {
			for(Object temp : list) {
				if (temp instanceof Object[]) {
					Map<String,Object> resultMap = new HashMap<String,Object>();
					InvestList pk = (InvestList)((Object[])temp)[2];
					MemberInfo fk = (MemberInfo)((Object[])temp)[0];
					Scale fk2 = (Scale)((Object[])temp)[1];
					if (pk != null) {
						resultMap.put("id", pk.getId());
						resultMap.put("money", pk.getMoney());
						resultMap.put("backIncome", pk.getBackIncome());
						resultMap.put("backMoney", pk.getBackMoney());
						resultMap.put("useTicket", UseTicket.valueOf("UT"+pk.getUseTicket()).toString());
						resultMap.put("totalMoney", pk.getTotalMoney());
						resultMap.put("income", pk.getIncome());
						resultMap.put("ticketBonus", pk.getTicketBonus());
						resultMap.put("status", Status.valueOf("S"+pk.getStatus()).toString());
						resultMap.put("openDate", DateUtils.date2Str(pk.getOpenDate()));
						resultMap.put("backDate", DateUtils.date2Str(pk.getBackDate()));
						resultMap.put("calcBegin", DateUtils.date2Str(pk.getBackDate()));
						resultMap.put("calcEnd", DateUtils.date2Str(pk.getBackDate()));
						resultMap.put("pactNumber", pk.getPactNumber());
						resultMap.put("investStyle", Platform.valueOf("P"+pk.getInvestStyle()).toString());
						resultMap.put("poundageTurn", pk.getPoundageTurn());
						resultMap.put("poundagePrivilege", pk.getPoundagePrivilege());
						resultMap.put("isTurn", If.valueOf("If"+pk.getIsTurn()).toString());
						resultMap.put("turnStatus", TurnStatus.valueOf("TS"+pk.getTurnStatus()).toString());
						resultMap.put("currentTime", DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
					}
					if (fk != null) {
						resultMap.put("memberName", fk.getRealName());
						resultMap.put("memberNickName", fk.getNickname());
						resultMap.put("memberPhone", fk.getPhone());
						resultMap.put("memberIdcard", fk.getIdCard());
					}
					if (fk2 != null) {
						resultMap.put("name", fk2.getName());
						resultMap.put("scaleId", fk2.getId());
						resultMap.put("revenue", fk2.getRevenue());
						resultMap.put("revenueAdd", fk2.getRevenueAdd());
						resultMap.put("productName", fk2.getProduct().getName());
						resultMap.put("proCategory", fk2.getProduct().getCategory());
						resultMap.put("days", fk2.getProduct().getDays());
						//标的购买人数
						int investMemberNum = investListService.count("scale.id", fk2.getId());
						resultMap.put("investMemberNum", investMemberNum);
						resultMap.put("investing", pk.getMoney()*(fk2.getRevenueAdd()+fk2.getRevenue())/365D*fk2.getProduct().getDays());
						resultMap.put("minMoney", fk2.getMinLimit());
						resultMap.put("singleOrSet", fk2.getSingleOrSet());
					}
					listMap.add(resultMap);
				}
			}
		}
		return listMap;
	}

	/**
	 * 封装对象
	 * @Title: fillDatagridObject   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param invest
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年9月12日 下午2:48:52
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private Map<String, Object> fillDatagridObejct(InvestList invest) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(invest !=  null){
			InvestList pk = invest;
			MemberInfo fk = invest.getMemberInfo();
			Scale fk2 = invest.getScale();
			Product product = fk2.getProduct();
			if (pk != null) {
				resultMap.put("prodescript", product.getProductDescribe());//介绍
				resultMap.put("riskcontrol", product.getRiskControl());//风险控制
				resultMap.put("details", product.getDetails());//更多详情
				resultMap.put("id", pk.getId());
				resultMap.put("money", pk.getMoney());
				resultMap.put("backIncome", pk.getBackIncome());
				resultMap.put("backMoney", pk.getBackMoney());
				resultMap.put("useTicket", UseTicket.valueOf("UT"+pk.getUseTicket()).toString());
				resultMap.put("totalMoney", pk.getTotalMoney());
				resultMap.put("income", pk.getIncome());
				resultMap.put("ticketBonus", pk.getTicketBonus());
				resultMap.put("status", Status.valueOf("S"+pk.getStatus()).toString());
				resultMap.put("pactNumber", pk.getPactNumber());
				resultMap.put("investStyle", Platform.valueOf("P"+pk.getInvestStyle()).toString());
				resultMap.put("poundageTurn", pk.getPoundageTurn());
				resultMap.put("poundagePrivilege", pk.getPoundagePrivilege());
				resultMap.put("isTurn", If.valueOf("If"+pk.getIsTurn()).toString());
				resultMap.put("turnStatus", TurnStatus.valueOf("TS"+pk.getTurnStatus()).toString());

			}
			if (fk != null) {
				resultMap.put("memberName", fk.getRealName());
				resultMap.put("memberNickName", fk.getNickname());
				resultMap.put("memberPhone", fk.getPhone());
				resultMap.put("memberIdcard", fk.getIdCard());
				resultMap.put("memberEmail", fk.getEmail());
				resultMap.put("memberSex", fk.getSex());
			}
			if (fk2 != null) {
				resultMap.put("name", fk2.getName());
				resultMap.put("calcBeginTime", DateUtils.date2Str(fk2.getCalcBeginTime()));
				resultMap.put("calcEndTime", DateUtils.date2Str(fk2.getCalcEndTime()));
				resultMap.put("productName", fk2.getProduct().getName());
				resultMap.put("productDetails", fk2.getProduct().getDetails());
				int days = (int) ((new Date().getTime() - pk.getCalcBeginDate().getTime()) / (3600000L*24));
				double profit = (fk2.getRevenue()+fk2.getRevenueAdd())*pk.getMoney() / 365D * days;
				resultMap.put("minMoney", fk2.getMinLimit());
				resultMap.put("singleOrSet", fk2.getSingleOrSet());
				
				resultMap.put("profitMoney", profit);
				resultMap.put("proCategory", fk2.getProduct().getCategory());
				
				resultMap.put("openDate", DateUtils.date2Str(pk.getOpenDate(),"yyyy-MM-dd"));
				resultMap.put("backDate", DateUtils.date2Str(pk.getBackDate(),"yyyy-MM-dd"));
				resultMap.put("calcBegin", DateUtils.date2Str(pk.getCalcBeginDate(),"yyyy-MM-dd"));
				resultMap.put("calcEnd", DateUtils.date2Str(pk.getCalcEndDate(),"yyyy-MM-dd"));
				resultMap.put("days", product.getDays());
			}
			resultMap.put("systemtime", DateUtils.getCurrentDate("yyyy-MM-dd hh:mm:ss"));
		}
		return resultMap;
	}
	
	/**
	 * 丰付投标回调
	 * @Title: investFFCallback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月3日 下午7:32:03
	 * @return: void      
	 * @throws
	 */
	public synchronized void investFFCallback(){
		boolean success = false;
		try{
			String requestId = getRequest().getParameter("requestId");
			entity = investListService.findEntity("requestid", requestId);
			if (entity==null) {
				//读取配置文件中
				Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
				
				String result = getRequest().getParameter("result");
				//投标金额
				String sum = getRequest().getParameter("sum");
				//第三方用户标识
				String userIdIdentity = getRequest().getParameter("userIdIdentity");
				
				//项目编号
				String projectCode = getRequest().getParameter("projectCode");
				//项目投资金额
				String investmentSum = getRequest().getParameter("investmentSum");
				//红包金额
				String giftSum = getRequest().getParameter("giftSum");
				//项目总额
				String projectSum = getRequest().getParameter("projectSum");
				//剩余可投金额
				String remainInvestmentSum = getRequest().getParameter("remainInvestmentSum");
				
				String signature = getRequest().getParameter("signature");
				
				StringBuffer backStringBuffer = new StringBuffer("\t\n---------------------------投标回调字符串：");
				backStringBuffer.append("\t\n----requestId:"+requestId)
								.append("\t\n----result:"+result)
								.append("\t\n----sum:"+sum)
								.append("\t\n----userIdIdentity:"+userIdIdentity)
								.append("\t\n----projectCode:"+projectCode)
								.append("\t\n----investmentSum:"+investmentSum)
								.append("\t\n----giftSum:"+giftSum)
								.append("\t\n----projectSum:"+projectSum)
								.append("\t\n----remainInvestmentSum:"+remainInvestmentSum)
								.append("\t\n----signature:"+signature);
				
				LoggerUtils.info(backStringBuffer.toString(), this.getClass());
				
				StringBuffer signatureStr = new StringBuffer();
				signatureStr.append(requestId);
				signatureStr.append(result);
				signatureStr.append(sum);
				signatureStr.append(userIdIdentity);
				//获取返回数据的加密数据用于与签名校验
				String dataSign = MD5Utils.hmacSign(signatureStr.toString(), ReadProperties.getStringValue(properties, "akey"));
				LoggerUtils.info("\t\n---------------------------投标回调 本地加密签名："+dataSign, this.getClass());
				String resultStr ="失败";
				if(signature.equals(dataSign)){
					//请求成功
					if(result.equals(ResultCode.SUCCESS.getVal())){
						success = investListService.investFFCallback(projectCode, userIdIdentity, DataUtils.str2double(sum, 6), 3, DataUtils.str2double(giftSum, 6), requestId);
						if(success){
							resultStr = "成功";
						}
					}else{
						LoggerUtils.error("\t\n---------------------------投标回调 流程号："+requestId+"------"+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)),this.getClass());
					}
				}else {
					//签名不匹配
					LoggerUtils.error("\t\n---------------------------投标回调 流程号："+requestId+","+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)),this.getClass());
				}
				RequestFlow requestFlow = new RequestFlow(DataUtils.randomUUID(), requestId, userIdIdentity, new Date(),"投标",resultStr);
				requestFlowService.saveEntity(requestFlow);
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("MemberAction realNameAuthCallback：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction realNameAuthCallback：" + e.getLocalizedMessage(), this.getClass());
			msg = "投资回调失败";
		}
		jsonObject.put("success", success);
		jsonObject.put("msg", msg);
		printJsonResult();
	}
}
