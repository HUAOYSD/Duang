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
import org.duang.entity.Scale;
import org.duang.enums.ResultCode;
import org.duang.enums.invest.Status;
import org.duang.service.InvestListService;
import org.duang.service.MemberInfoService;
import org.duang.service.ScaleService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.MD5Utils;
import org.duang.util.ReadProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————标类Action
 * @ClassName:  ScaleAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_scale")
public class ScaleAction extends BaseAction<Scale>{

	private ScaleService scaleService;
	@Resource(name = "scaleserviceimpl")
	public void setScaleService(ScaleService scaleService) {
		this.scaleService = scaleService;
	}
	private MemberInfoService memberInfoService;
	@Resource
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
	}
	/**   
	 * 查询房标列表
	 * @Title: queryHouseScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月5日 下午4:38:04
	 * @return: void      
	 * @throws   
	 */  
	public void queryHouseScale(){
		boolean success = false;
		try {
			//每页显示条数
			String num = getRequest().getParameter("num");
			//第几页
			String count = getRequest().getParameter("count");
			int countNumber = DataUtils.str2int(count), numNumber = DataUtils.str2int(num);
			if (countNumber > 0 && numNumber > 0) {
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT SCA.* FROM SCALE SCA LEFT JOIN SCALE_LOAN_LIST SLL ON SLL.SCALE = SCA.ID");
				sql.append(" WHERE SLL.LOAN_LIST IN( SELECT LOAN_LIST_ID FROM APPLY_LOAN_HOUSE)");
				sql.append(" LIMIT "+ ((countNumber - 1) * numNumber)  +", "+ (countNumber * numNumber));
				List<Scale> list = scaleService.queryBySQL(sql.toString(), null, null, true);
				success = true;
				jsonObject.put("result", fillDatagridCons(list));
				if (list == null || list.size() == 0) {
					msg = "没有更多了";
				}
			}else {
				msg = "参数丢失";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ScaleAction——queryHouseScale方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ScaleAction——queryHouseScale方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 查询车标列表
	 * @Title: queryCarScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月5日 下午4:38:04
	 * @return: void      
	 * @throws   
	 */  
	public void queryCarScale(){
		boolean success = false;
		try {
			//每页显示条数
			String num = getRequest().getParameter("num");
			//第几页
			String count = getRequest().getParameter("count");
			int countNumber = DataUtils.str2int(count), numNumber = DataUtils.str2int(num);
			if (countNumber > 0 && numNumber > 0) {
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT SCA.* FROM SCALE SCA LEFT JOIN SCALE_LOAN_LIST SLL ON SLL.SCALE = SCA.ID");
				sql.append(" WHERE SLL.LOAN_LIST IN( SELECT LOAN_LIST_ID FROM APPLY_LOAN_CAR)");
				sql.append(" LIMIT "+ ((countNumber - 1) * numNumber)  +", "+ (countNumber * numNumber));
				List<Scale> list = scaleService.queryBySQL(sql.toString(), null, null, true);
				success = true;
				jsonObject.put("result", fillDatagridCons(list));
				if (list == null || list.size() == 0) {
					msg = "没有更多了";
				}
			}else {
				msg = "参数丢失";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ScaleAction——queryCarScale方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ScaleAction——queryCarScale方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}


	/**   
	 * 查询信标列表
	 * @Title: queryHouseScale   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月5日 下午4:38:04
	 * @return: void      
	 * @throws   
	 */  
	public void queryCreditScale(){
		boolean success = false;
		try {
			//每页显示条数
			String num = getRequest().getParameter("num");
			//第几页
			String count = getRequest().getParameter("count");
			int countNumber = DataUtils.str2int(count), numNumber = DataUtils.str2int(num);
			if (countNumber > 0 && numNumber > 0) {
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT SCA.* FROM SCALE SCA LEFT JOIN SCALE_LOAN_LIST SLL ON SLL.SCALE = SCA.ID");
				sql.append(" WHERE SLL.LOAN_LIST IN( SELECT LOAN_LIST_ID FROM APPLY_LOAN_INFO)");
				sql.append(" LIMIT "+ ((countNumber - 1) * numNumber)  +", "+ (countNumber * numNumber));
				List<Scale> list = scaleService.queryBySQL(sql.toString(), null, null, true);
				success = true;
				jsonObject.put("result", fillDatagridCons(list));
				if (list == null || list.size() == 0) {
					msg = "没有更多了";
				}
			}else {
				msg = "参数丢失";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ScaleAction——queryCreditScale方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ScaleAction——queryCreditScale方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	/**   
	 * 根据标id获取标详情
	 * @Title: findScaleInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月9日 下午4:38:04
	 * @return: void      
	 * @throws   
	 */  
	public void findScaleInfo(){
		boolean success = false;
		try {
			String id = getRequest().getParameter("id");
			if(DataUtils.notEmpty(id)){
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT SCALE.* FROM SCALE where id="+id);
				List<Scale> list = scaleService.queryBySQL(sql.toString(), null, null, true);
				success = true;
				jsonObject.put("result", fillDatagridCons(list));
				if (list == null || list.size() == 0) {
					msg = "没有更多了";
				}
			}else{
				msg = "参数为空";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ScaleAction——findScaleInfo方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ScaleAction——findScaleInfo方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	/**   
	 * 根据标id获取标详情（在投资详情页面）
	 * @Title: findScaleInfoByInvest   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月9日 下午4:38:04
	 * @return: void      
	 * @throws   
	 */  
	public void findScaleInfoByInvest(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = getRequest().getParameter("id");
			String memeberId ="";
			if(DataUtils.notEmpty(id) && DataUtils.notEmpty(token) && DataUtils.notEmpty((memeberId = MemberCollection.getInstance(token,memberInfoService).getMainField(token)))){
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT product.name_zh, product.days, product.category, il.money, il.total_money,");
				sql.append(" scale.id, scale.`name`, scale.yet_money, scale.residue_money, scale.revenue, scale.revenue_add, scale.return_style,");
				sql.append(" member_info.real_name, buyNum.numbers FROM scale LEFT JOIN invest_list il on il.scale_id=scale.id");
				sql.append(" LEFT JOIN product ON product.id = scale.product_id LEFT JOIN member_info ON member_info.id=il.member_info left JOIN (");
				sql.append(" select scale_id, count(*) numbers  from invest_list where invest_list.scale_id='"+id+"' and invest_list.`status` in(2,3) ");
				sql.append(" ) as buyNum  ON buyNum.scale_id=scale.id  where scale.id='"+id+"' and member_info.id='"+memeberId+"'");
				List<?> list = scaleService.queryBySQL(sql.toString(), null, null, false);
				success = true;
				jsonObject.put("result", fillDatagridArray(list));
				if (list == null || list.size() == 0) {
					msg = "没有更多了";
				}
			}else{
				msg = "参数为空";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ScaleAction——findScaleInfoByInvest方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ScaleAction——findScaleInfoByInvest方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	private List<Map<String, Object>> fillDatagridArray(List<?> list) throws Exception{
		if (list !=null && list.size() > 0) {
			for(int i=0; i<list.size(); i++) {
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put("productName", ((Object[])list.get(i))[0]);
				resultMap.put("days", ((Object[])list.get(i))[1]);
				resultMap.put("proCategory", ((Object[])list.get(i))[2]);
				resultMap.put("money", ((Object[])list.get(i))[3]);
				resultMap.put("totalMoney",((Object[])list.get(i))[4]);
				resultMap.put("id", ((Object[])list.get(i))[5]);
				resultMap.put("name", ((Object[])list.get(i))[6]);
				resultMap.put("yetMoney", ((Object[])list.get(i))[7]);
				resultMap.put("residueMoney", ((Object[])list.get(i))[8]);
				resultMap.put("revenue", ((Object[])list.get(i))[9]);
				resultMap.put("revenueAdd", ((Object[])list.get(i))[10]);
				resultMap.put("calStyle", ((Object[])list.get(i))[11]);
				resultMap.put("buyName", ((Object[])list.get(i))[12]);
				resultMap.put("numbers", ((Object[])list.get(i))[13]);
				resultMap.put("min", 500);
				listMap.add(resultMap);
			}
		}
		return listMap;
	}
	
	private InvestListService investListService;
	@Resource
	public void setService(InvestListService investListService) {
		this.investListService = investListService;
	}
	/**   
	 * 填充List数据
	 * @Title: fillDatagridCons   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list  
	 * @author 5y    
	 * @date 2016年9月7日 下午5:08:47
	 * @return: void  
	 * @throws   
	 */  
	private List<Map<String, Object>> fillDatagridCons(List<Scale> list) throws Exception {
		if (list !=null && list.size() > 0) {
			for(Scale s: list) {
				Map<String,Object> resultMap = new HashMap<String,Object>();
				if (s != null) {
					resultMap.put("id", s.getId());
					resultMap.put("scaleName", s.getName());
					resultMap.put("revenue", s.getRevenue());
					resultMap.put("revenueAdd", s.getRevenueAdd());
					resultMap.put("timeLimit", s.getTimeLimit());
					resultMap.put("beginTime", DateUtils.getTimeStamp(s.getBeginTime()));
					resultMap.put("endTime", DateUtils.getTimeStamp(s.getEndTime()));
					resultMap.put("maxLimit", s.getMaxLimit());
					resultMap.put("totalMoney", s.getTotalMoney());
					resultMap.put("residueMoney", s.getResidueMoney());
					resultMap.put("yetMoney", s.getYetMoney());
					resultMap.put("returnStyle", s.getReturnStyle());
					resultMap.put("tags", s.getTags());
					resultMap.put("useTicket", s.getUseTicket());
					resultMap.put("transfer", s.getTransfer());
					resultMap.put("status", s.getStatus());
					//标的购买人数
					int investMemberNum = investListService.count("scale.id", s.getId());
					resultMap.put("investMemberNum", investMemberNum);
				}
				Product p = s.getProduct();
				if (p != null) {
					resultMap.put("days", p.getDays());
				}
				listMap.add(resultMap);
			}
		}
		return listMap;
	}

	/**
	 * 检查购买是否超过限额
	 */
	public void checkMoneyLimit(){
		boolean success = false;
		try {
			String money = getRequest().getParameter("buyMoney");
			String scaleid = getRequest().getParameter("scaleid");//加密的哈
			if(DataUtils.notEmpty(money) && DataUtils.notEmpty(scaleid)){
				double buyMoney = DataUtils.str2double(money, 6);
				entity = scaleService.findById(DES.decryptDES(scaleid));
				if (buyMoney <= entity.getMaxLimit() && buyMoney <= entity.getResidueMoney()) {
					success = true;
					jsonObject.put("projectSum", entity.getTotalMoney());
					jsonObject.put("giftFlag", entity.getGiftFlag());
				}else{
					//超出限额金额
					double er_maxMoney = buyMoney-entity.getMaxLimit();
					//超出剩余可投金额
					double er_residueMoney = buyMoney-entity.getResidueMoney();
					double beyond_money = er_maxMoney>er_residueMoney?er_maxMoney:er_residueMoney;
					//超出金额
					jsonObject.put("beyond_money", beyond_money);
					msg="购买超额";
				}
			}else{
				msg = "缺少参数";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ScaleAction——checkMoneyLimit方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ScaleAction——checkMoneyLimit方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
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
			//本息到账金额
			String sum = getRequest().getParameter("sum");
			//项目编号
			String projectCode = getRequest().getParameter("projectCode");
			//项目还款账户余额
			String accountBalance = getRequest().getParameter("accountBalance");
			//请求时间
			String requestTime = getRequest().getParameter("requestTime");
			//处理时间
			String dealTime = getRequest().getParameter("dealTime");
			//手续费收取方式
			String payType = getRequest().getParameter("payType");
			
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
			if(signature.equals(dataSign)){
				//请求成功
				if(result.equals(ResultCode.SUCCESS.getVal())){
					//修改标等信息
					InvestList investList = new InvestList();
					investList.setId(DataUtils.randomUUID());
					investList.setMoney(DataUtils.str2double(sum, 6));
					investList.setUseTicket(1);
					investList.setTotalMoney(0);
					investList.setInvestStyle(4);
					investList.setGiftSum(DataUtils.str2double(giftSum, 6));
					investList.setMemberInfo(new MemberInfo(userIdIdentity));
					//根据理财标和投资本金计算本金和预期收益和
					//查找理财标并更新
					Scale scale = scaleService.findById(projectCode);
					//剩余可投金额
					scale.setResidueMoney(scale.getTotalMoney()-DataUtils.str2double(investmentSum, 6));
					if(scale.getResidueMoney()==0){
						//0新建标，1流标，2可投标，3已完成
						scale.setStatus(3);
					}
					//已经投金额
					scale.setYetMoney(DataUtils.str2double(investmentSum, 6));
					scaleService.updateEntity(scale);
					
					StringBuffer updateScaleBuffer = new StringBuffer("\t\n---------------------------标更新成功");
					updateScaleBuffer.append("\t\n-------------标总金额 scale.getTotalMoney():"+scale.getTotalMoney())
									 .append("\t\n-------------已投金额 scale.getYetMoney():"+scale.getYetMoney())
									 .append("\t\n-------------剩余金额scale.getResidueMoney():"+scale.getResidueMoney());
					
					LoggerUtils.info(updateScaleBuffer.toString(), this.getClass());
					//理财天数
					int day = scale.getProduct().getDays();
					//收益
					double income = DataUtils.str2double(sum, 6) * (scale.getRevenue() + scale.getRevenueAdd()) / 365D * day;
					income = DataUtils.str2double(income+"", 6);
					investList.setScale(scale);
					investList.setIncome(income);
					investList.setStatus(Status.S2.getVal());
					investList.setOpenDate(new Date());
					String pactNumber = DateUtils.date2Str(new Date(), "MMDDhhmmss") + DataUtils.sixNumber();
					investList.setPactNumber(pactNumber);
					investList.setDays(day);
					boolean saveEntity = investListService.saveEntity(investList);
					LoggerUtils.info("\t\n---------------------------标投标理财记录（investList）成功 ["+saveEntity+"]，更新的id："+investList.getId(), this.getClass());
				}else{
					LoggerUtils.error("\t\n---------------------------投标回调 流程号："+requestId+"------"+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)),this.getClass());
				}
			}else {
				//签名不匹配
				LoggerUtils.error("\t\n---------------------------投标回调 流程号："+requestId+","+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)),this.getClass());
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("MemberAction realNameAuthCallback：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction realNameAuthCallback：" + e.getLocalizedMessage(), this.getClass());
		}
		
	}
	
}
