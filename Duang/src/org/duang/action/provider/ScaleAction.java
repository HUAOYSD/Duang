package org.duang.action.provider;
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
import org.duang.entity.Product;
import org.duang.entity.Scale;
import org.duang.service.InvestListService;
import org.duang.service.MemberInfoService;
import org.duang.service.ScaleService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
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
}
