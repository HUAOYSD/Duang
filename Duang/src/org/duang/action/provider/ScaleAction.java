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
import org.duang.entity.Product;
import org.duang.entity.Scale;
import org.duang.service.ScaleService;
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
				sql.append(" SELECT SCA.* FROM SCALE where id="+id);
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

}
