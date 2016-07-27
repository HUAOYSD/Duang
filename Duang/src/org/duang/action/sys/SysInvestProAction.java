package org.duang.action.sys;

import java.util.ArrayList;
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
import org.duang.entity.SysInvestProduct;
import org.duang.entity.SysPower;
import org.duang.service.SysInvestProService;
import org.duang.util.DataUtils;
import org.duang.util.PageUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 理财产品Action
 * @ClassName:  SysInvestProAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月26日 下午1:55:48
 */
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="investpro")
@ParentPackage("sys")
@Results(value={
		@Result(name=ResultPath.LIST, type="dispatcher", location="WEB-INF/page/sys/invest/investProList.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher", location="error.jsp")
})
public class SysInvestProAction extends BaseAction<SysInvestProduct>{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private SysInvestProService service;
	@Resource(name="sysinvestproserviceimpl")
	public void setService(SysInvestProService service) {
		this.service = service;
	}
	
	public String  investProList(){
		return ResultPath.LIST;
	}
	
	public void  queryInvestPro(){
		List<SysInvestProduct> list = null;
		try {
			
			String name = super.getRequest().getParameter("name");
			String nameZh = super.getRequest().getParameter("nameZh");
			String min_deadline = super.getRequest().getParameter("min_deadline");
			
			PageUtil<SysInvestProduct> page = new PageUtil<SysInvestProduct>();
			String pageRecords = super.getRequest().getParameter("rows");
			String currentPageNum = super.getRequest().getParameter("page");
			if(DataUtils.notEmpty(pageRecords)){
				page.setPageRecords(Integer.parseInt(pageRecords));
			}
			if(DataUtils.notEmpty(currentPageNum)){
				page.setCurrentPageNum(Integer.parseInt(currentPageNum));
			}
			condsUtils.addProperties(true, "isdelete");
			condsUtils.concatValue(new Object[]{1,"ne"});
			if(DataUtils.notEmpty(name)){
				condsUtils.addProperties(false, "name");
				condsUtils.concatValue(new Object[]{"%"+name+"%","like"});
			}
			if(DataUtils.notEmpty(nameZh)){
				condsUtils.addProperties(false, "nameZh");
				condsUtils.concatValue(new Object[]{"%"+nameZh+"%","like"});
			}
			if(DataUtils.notEmpty(min_deadline)){
				condsUtils.addProperties(false, "min_deadline");
				condsUtils.addValues(false,Integer.parseInt(min_deadline));
			}
			list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), page);
			int count = service.count(condsUtils.getPropertys(), condsUtils.getValues());
			if(list != null && list.size() > 0) {
				jsonObject.put("rows", fillDataObject(list));
				jsonObject.put("total", count);
				jsonObject.put("currPage", getPageUtil().getCurrentPageNum());
				jsonObject.put("pageSize",getPageUtil().getPageRecords());
			}else{
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("currPage", 1);
				jsonObject.put("pageSize",0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财产品类型ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财产品类型ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
		}finally{
			printJsonResult();
		}
	}
	public List<Map<String,Object>> fillDataObject(List<SysInvestProduct> list){
		List<Map<String,Object>> listMapOj = new ArrayList<Map<String,Object>>();
		for(SysInvestProduct pro : list){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", pro.getId());
			map.put("name", pro.getName());
			map.put("nameZh", pro.getNameZh());
			map.put("name_describe", pro.getName_describe());
			map.put("charge_ratio", pro.getCharge_ratio());
			map.put("createtime", pro.getCreatetime());
			map.put("createuser", pro.getCreateuser());
			map.put("details", pro.getDetails());
			map.put("is_lottery", pro.getIs_lottery());
			map.put("is_new_product", pro.getIs_new_product());
			map.put("is_recommend", pro.getIs_recommend());
			
			map.put("is_red_envel", pro.getIs_red_envel());
			map.put("is_sell", pro.getIs_sell());
			map.put("isdelete", pro.getIsdelete());
			map.put("min_deadline", pro.getMin_deadline());
			map.put("min_money", pro.getMin_money());
			map.put("modifytime", pro.getModifytime());
			map.put("modifyuser", pro.getModifyuser());
			
			map.put("product_describe", pro.getProduct_describe());
			map.put("refund_type", pro.getRefund_type());
			map.put("risk_control", pro.getRisk_control());
			map.put("title1", pro.getTitle1());
			map.put("title2", pro.getTitle2());
			map.put("yield", pro.getYield());
			map.put("yield_describe", pro.getYield_describe());
			listMapOj.add(map);
		}
		return listMapOj;
	}
}	
