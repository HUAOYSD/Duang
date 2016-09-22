package org.duang.action.sys;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.duang.entity.Product;
import org.duang.entity.Scale;
import org.duang.enums.Has;
import org.duang.enums.If;
import org.duang.enums.May;
import org.duang.enums.product.Category;
import org.duang.enums.scale.Status;
import org.duang.service.ScaleService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
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

	private ScaleService service;

	@Resource
	public void setService(ScaleService service) {
		this.service = service;
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
}
