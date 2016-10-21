package org.duang.action.provider;
import java.util.Date;
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
import org.duang.entity.InvestList;
import org.duang.entity.MemberInfo;
import org.duang.entity.Product;
import org.duang.entity.Scale;
import org.duang.enums.If;
import org.duang.enums.Platform;
import org.duang.enums.invest.Status;
import org.duang.enums.invest.TurnStatus;
import org.duang.enums.invest.UseTicket;
import org.duang.enums.product.Category;
import org.duang.service.InvestListService;
import org.duang.service.ScaleService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
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
			condsUtils.addProperties(true, "product", "myAlias.isRecommend", "myAlias.isSell", "status", "order");
			condsUtils.addValues(true, new Object[]{"myAlias","as"}, 1, 1, new Object[]{1, 2, "or"}, Order.desc("beginTime"));
			List<Scale> list = scaleService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
			if (DataUtils.notEmpty(list)) {
				for(Scale scale : list){
					Map<String, Object> map = new HashMap<String, Object>();
					//时长、起投、预期收益率、附加预期收益率、已投金额、剩余可投金额、标总额、产品类型、产品名称、标名称
					Product product = scale.getProduct();
					map.put("id", scale.getId());
					map.put("day", product.getDays());
					map.put("min", 500);
					map.put("revenue", scale.getRevenue());
					map.put("revenueAdd", scale.getRevenueAdd());
					map.put("yetmoney", scale.getYetMoney());
					map.put("residuemoney", scale.getResidueMoney());
					map.put("totalmoney", scale.getTotalMoney());
					map.put("productcategory", Category.valueOf("C"+product.getCategory()).toString());
					map.put("productname", product.getNameZh());
					map.put("scalename", scale.getName());
					listMap.add(map);
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
			String p_scaleId = getRequest().getParameter("p_scaleId");
			String p_money = getRequest().getParameter("p_money");
			String p_useTicket = getRequest().getParameter("p_useTicket");

			String p_ticketBonus = getRequest().getParameter("p_ticketBonus");
			String p_totalMoney = getRequest().getParameter("p_totalMoney");

			String investStyle = getRequest().getParameter("investStyle");
			String id = "";
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance().getMainField(token))){

				InvestList investList = new InvestList();
				investList.setId(DataUtils.randomUUID());
				investList.setMoney(Double.parseDouble(DES.decryptDES(p_money)));
				investList.setUseTicket(Integer.parseInt(DES.decryptDES(p_useTicket)));
				investList.setTicketBonus(Double.valueOf(DES.decryptDES(p_ticketBonus)));
				investList.setTotalMoney(Double.parseDouble(DES.decryptDES(p_totalMoney)));
				investList.setInvestStyle(Integer.parseInt(DES.decryptDES(investStyle)));
				investList.setScale(new Scale(p_scaleId));
				investList.setMemberInfo(new MemberInfo(id));
				success = investListService.saveEntity(investList);

				double money = DataUtils.str2double(p_money, 6);
				if (DataUtils.isEmpty(p_scaleId)) {
					msg = "理财标主键传值失败";
				}else if (money <= 0) {
					msg = "投资金额需大于0";
				}else if (!("1".equals(p_useTicket) || "2".equals(p_useTicket))) {
					msg = "理财券使用类型出错";
				}
				//根据理财标和投资本金计算本金和预期收益和
				Scale scale = scaleService.findById(DES.decryptDES(p_scaleId));
				int day = scale.getProduct().getDays();
				double income = money * (scale.getRevenue() + scale.getRevenueAdd()) / 365D * day;
				income = DataUtils.str2double(income+"", 6);
				//封装实体
				entity = new InvestList(DataUtils.randomUUID());
				entity.setScale(scale);
				entity.setMemberInfo(new MemberInfo(id));
				entity.setMoney(money);
				entity.setUseTicket(DataUtils.str2int(p_useTicket));
				entity.setTotalMoney(income + money);
				entity.setIncome(income);
				entity.setStatus(Status.S2.getVal());
				entity.setOpenDate(new Date());
				String pactNumber = DateUtils.date2Str(new Date(), "MMDDhhmmss") + DataUtils.sixNumber();
				entity.setPactNumber(pactNumber);
				entity.setInvestStyle(DataUtils.str2int(investStyle));
				entity.setDays(day);
				success = investListService.saveEntity(entity);
				if(!success){
					msg = "服务器超时，请稍后重试";
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
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance().getMainField(token))){
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
						resultMap.put("currentTime", DateUtils.getCurrentDate("yyyy-MM-dd hh:mm:ss"));
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
				resultMap.put("profitMoney", (fk2.getRevenue()+fk2.getRevenueAdd())*pk.getMoney());
				resultMap.put("proCategory", fk2.getProduct().getCategory());

			}
		}
		return resultMap;
	}

}
