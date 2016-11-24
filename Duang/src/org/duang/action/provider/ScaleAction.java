package org.duang.action.provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.CondsUtils;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.MemberInfo;
import org.duang.entity.Product;
import org.duang.entity.Scale;
import org.duang.enums.invest.Status;
import org.duang.service.InvestListService;
import org.duang.service.MemberInfoService;
import org.duang.service.ScaleService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
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
				sql.append(" SELECT  SCALE.* FROM SCALE  ");
				sql.append(" INNER JOIN PRODUCT ON PRODUCT.ID = SCALE.PRODUCT_ID ");
				sql.append(" WHERE PRODUCT.CATEGORY = 2 AND SCALE.STATUS <> 1 AND  SCALE.STATUS <> 0 ");
				sql.append(" ORDER BY begin_time desc ");
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
				sql.append(" SELECT  SCALE.* FROM SCALE  ");
				sql.append(" INNER JOIN PRODUCT ON PRODUCT.ID = SCALE.PRODUCT_ID ");
				sql.append(" WHERE PRODUCT.CATEGORY = 3 AND SCALE.STATUS <> 1 AND  SCALE.STATUS <> 0 ");
				sql.append(" ORDER BY begin_time desc ");
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
				sql.append(" SELECT  SCALE.* FROM SCALE  ");
				sql.append(" INNER JOIN PRODUCT ON PRODUCT.ID = SCALE.PRODUCT_ID ");
				sql.append(" WHERE PRODUCT.CATEGORY = 1 AND SCALE.STATUS <> 1 AND  SCALE.STATUS <> 0 ");
				sql.append(" ORDER BY begin_time desc ");
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
			String money = getRequest().getParameter("money");//投资成功后需要用到
			String memeberId ="";
			if(DataUtils.notEmpty(id) && DataUtils.notEmpty(token) && DataUtils.notEmpty((memeberId = MemberCollection.getInstance(token,memberInfoService).getMainField(token)))){
				Scale scale = scaleService.findById(id);
				MemberInfo memberInfo = memberInfoService.findById(memeberId);
				List<Map<String,Object>> scaleListMap = new ArrayList<Map<String,Object>>();
				//购买人数
				int numbers = 0;
				if (DataUtils.isEmpty(money)) {//表详情页面
					CondsUtils condsUtils = new CondsUtils();
					condsUtils.addProperties(true, "scale.id", "status");
					condsUtils.addValues(true, id, new Object[]{new Object[]{Status.S2.getVal(), Status.S3.getVal()}, "in"});
					numbers = investListService.count(condsUtils.getPropertys(), condsUtils.getValues());
				}
				Map<String,Object> resultMap = new HashMap<String,Object>();
				if(scale != null && memberInfo != null){
					Product product = scale.getProduct();
					resultMap.put("productName", product.getName());
					resultMap.put("days", product.getDays());
					resultMap.put("proCategory", product.getCategory());

					//					resultMap.put("prodescript", product.getProductDescribe());//介绍
					//					resultMap.put("riskcontrol", product.getRiskControl());//风险控制
					//					resultMap.put("details", product.getDetails());//更多详情

					resultMap.put("money", DataUtils.str2double(money, 6));
					resultMap.put("totalMoney",scale.getTotalMoney());
					resultMap.put("id", id);
					resultMap.put("name", scale.getName());
					resultMap.put("yetMoney", scale.getYetMoney());
				    //DecimalFormat df = new DecimalFormat("0.00");
					//resultMap.put("residueMoney", df.format(scale.getResidueMoney()/10000D));
					resultMap.put("residueMoney", scale.getResidueMoney());
					resultMap.put("revenue", scale.getRevenue()*100);
					resultMap.put("revenueAdd", scale.getRevenueAdd()*100);
					resultMap.put("calStyle", scale.getReturnStyle());
					resultMap.put("buyName", memberInfo.getRealName());
					resultMap.put("numbers", numbers);
					resultMap.put("status", scale.getStatus());
					resultMap.put("min", ReadProperties.getStringValue(ReadProperties.initPrperties("sumapayURL.properties"), "minInvestMoney"));
				}

				scaleListMap.add(resultMap);

				success = true;
				jsonObject.put("result", scaleListMap);
				if (scaleListMap == null || scaleListMap.size() == 0) {
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
					resultMap.put("revenue", s.getRevenue()*100);
					resultMap.put("revenueAdd", s.getRevenueAdd()*100);
					resultMap.put("timeLimit", s.getTimeLimit());
					resultMap.put("beginTime", DateUtils.getTimeStamp(s.getBeginTime()));
					resultMap.put("endTime", DateUtils.getTimeStamp(s.getEndTime()));
					resultMap.put("maxLimit", s.getMaxLimit());
					resultMap.put("totalMoney", s.getTotalMoney());
					resultMap.put("residueMoney", s.getResidueMoney());
					resultMap.put("yetMoney", s.getYetMoney());
					resultMap.put("returnStyle", "到期一次还本付息");
					//resultMap.put("returnStyle", s.getReturnStyle());
					resultMap.put("tags", s.getTags());
					resultMap.put("useTicket", s.getUseTicket());
					resultMap.put("transfer", s.getTransfer());
					resultMap.put("status", s.getStatus());
					//标的购买人数
					int investMemberNum = investListService.count("scale.id", s.getId());
					resultMap.put("investMemberNum", investMemberNum);
					resultMap.put("minMoney", ReadProperties.getStringValue(ReadProperties.initPrperties("sumapayURL.properties"), "minInvestMoney"));
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
					if(er_maxMoney>0){
						msg="超出单笔限额 "+(buyMoney-entity.getMaxLimit())+"元";
					}else if(er_residueMoney>0){
						msg="库存不足";
					}
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
