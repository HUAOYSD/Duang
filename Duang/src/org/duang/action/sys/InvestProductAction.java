package org.duang.action.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.SessionTools;
import org.duang.entity.Product;
import org.duang.enums.product.Category;
import org.duang.service.ProductService;
import org.duang.util.ConstantCode;
import org.duang.util.DataUtils;
import org.hibernate.criterion.Order;
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
		@Result(name=ResultPath.LIST, type="dispatcher", location="WEB-INF/page/sys/investporduct/investProList.jsp"),
		@Result(name="investPromanage", type="dispatcher", location="WEB-INF/page/sys/investporduct/investProManage.jsp"),
		@Result(name="addInvestPro", type="dispatcher", location="WEB-INF/page/sys/investporduct/addInvestPro.jsp"),
		@Result(name="editInvestPro", type="dispatcher", location="WEB-INF/page/sys/investporduct/editInvestPro.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher", location="error.jsp")
})
public class InvestProductAction extends BaseAction<Product>{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;

	private ProductService service;
	@Resource(name="productserviceimpl")
	public void setService(ProductService service) {
		this.service = service;
	}


	/**   
	 * 获取可使用的产品给下拉框选择
	 * @Title: queryProductByCombobox   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年8月16日 下午2:57:54
	 * @return: void      
	 * @throws   
	 */  
	public void queryProductByCombobox() {
		String json = "";
		try {
			super.condsUtils.addProperties(true, "order", "isdelete", "isSell");
			super.condsUtils.addValues(true, Order.desc("createtime"), 0, 1);
			List<Product> list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
			for(Product temp : list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", temp.getId());
				map.put("text", temp.getNameZh()+"（"+(Category.valueOf("C"+temp.getCategory()).toString())+"）");
				listMap.add(map);
			}
			json = JSONArray.fromObject(listMap).toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			printJsonResult(json);
		}
	}


	/**
	 * 返回产品列表
	 * @Title: investProList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年7月27日 下午2:26:47
	 * @return: String      
	 * @throws
	 */
	public String  investProList(){
		return ResultPath.LIST;
	}

	public String addInvestPro(){
		return "addInvestPro";
	}

	/**
	 * 跳转到编辑页面
	 * @Title: editInvestPro   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年7月29日 下午1:57:03
	 * @return: String      
	 * @throws
	 */
	public String editInvestPro(){
		String id = getRequest().getParameter("id");
		try{
			getRequest().setAttribute("id", id);
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("理财产品ACTION修改错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财产品ACTION修改错误："+e.getLocalizedMessage(), this.getClass());
		}
		return "editInvestPro";
	}

	/**
	 * 保存或者更新理财产品
	 * @Title: saveInvestPro   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月2日 下午2:44:43
	 * @return: void      
	 * @throws
	 */
	public void saveInvestPro(){
		if (entity!=null && DataUtils.notEmpty(entity.getNameZh())) {
			try {
				if(!DataUtils.notEmpty(entity.getId())){//为空，说明为添加，否则为编辑
					//判断是否存在相同总名称的数据，如果存在则取消添加
					if(service.count("name",entity.getNameZh())>0){
						jsonObject.put("result",false);
						jsonObject.put("msg","添加失败，已经存在相同名称的产品！");
						printJsonResult();
					}else{
						entity.setId(DataUtils.randomUUID());
						entity.setIsdelete(0);
						entity.setCreatetime(new Date());
						entity.setModifytime(new Date());
						entity.setCreateuser(SessionTools.getSessionSysUser().getId());
						entity.setModifyuser(SessionTools.getSessionSysUser().getId());
						entity.setIsdelete(ConstantCode.UNDELETE_INT);
						boolean issuccess = service.saveEntity(entity);
						jsonObject = getJSONObject();
						if (issuccess) {
							jsonObject.put("result",true);
							jsonObject.put("msg","添加理财产品成功");
							printJsonResult();
						}else{
							jsonObject.put("result",false);
							jsonObject.put("msg","添加理财产品失败，请联系管理员");
							printJsonResult();
						}
					}
				}else{
					entity.setIsdelete(0);
					entity.setModifytime(new Date());
					entity.setModifyuser(SessionTools.getSessionSysUser().getId());
					boolean issuccess = service.updateEntity(entity);
					jsonObject = getJSONObject();
					if (issuccess) {
						jsonObject.put("result",true);
						jsonObject.put("msg","修改理财产品成功");
						printJsonResult();
					}else{
						jsonObject.put("result",false);
						jsonObject.put("msg","修改理财产品失败，请联系管理员");
						printJsonResult();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("理财产品ACTION增加错误："+e.getMessage(), this.getClass());
				LoggerUtils.error("理财产品ACTION增加错误："+e.getLocalizedMessage(), this.getClass());
			}
		}
	}

	/**
	 * 删除理财产品
	 * @Title: deleteInvestPro   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月2日 下午2:43:52
	 * @return: void      
	 * @throws
	 */
	public void deleteInvestPro(){
		if (entity !=null && DataUtils.notEmpty(entity.getId())) {
			try {
				entity = service.findById(entity.getId());
				entity.setIsdelete(1);
				boolean issuccess = service.updateEntity(entity);
				if (issuccess) {
					jsonObject.put("result",true);
					jsonObject.put("msg","删除理财产品成功");
					printJsonResult();
				}else{
					jsonObject.put("result",false);
					jsonObject.put("msg","删除理财产品失败，请联系管理员");
					printJsonResult();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("理财产品ACTION删除错误："+e.getMessage(), this.getClass());
				LoggerUtils.error("理财产品ACTION删除错误："+e.getLocalizedMessage(), this.getClass());
			}
		}
	}

	/**
	 * 获取第一页前50条数据
	 * @Title: getJSONObject   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年7月28日 下午2:59:12
	 * @return: JSONObject      
	 * @throws
	 */
	public JSONObject getJSONObject(){
		List<Product> list = null;
		try {
			condsUtils.addProperties(true, "isdelete");
			condsUtils.concatValue(new Object[]{1,"ne"});
			list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			if(list != null && list.size() > 0) {
				jsonObject.put("rows", fillDataObject(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			}else{
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财产品类型ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财产品类型ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
		}
		return jsonObject;
	}

	/**
	 * 返回产品管理
	 * @Title: investProManage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年7月27日 下午2:27:10
	 * @return: String      
	 * @throws
	 */
	public String  investProManage(){
		return "investPromanage";
	}



	/**
	 * 查询所有的产品，并用json方式返回
	 * @Title: queryInvestPro   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年7月27日 下午2:27:37
	 * @return: void      
	 * @throws
	 */
	public void  queryInvestPro(){
		List<Product> list = null;
		try {

			String name = super.getRequest().getParameter("name");
			String nameZh = super.getRequest().getParameter("nameZh");
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
			list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			if(list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObject(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			}else{
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("msg", "未查到符合条件的产品！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财产品类型ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财产品类型ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
		}finally{
			printJsonResult();
		}
	}

	/**
	 * 重新组合产品集合
	 * @Title: fillDataObject   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年7月27日 下午2:28:18
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	public List<Map<String,Object>> fillDataObject(List<Product> list){
		List<Map<String,Object>> listMapOj = new ArrayList<Map<String,Object>>();
		for(Product pro : list){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", pro.getId());
			map.put("category", pro.getCategory());
			map.put("name", pro.getName());
			map.put("nameZh", pro.getNameZh());
			map.put("nameDescribe", pro.getNameDescribe());
			map.put("createtime", pro.getCreatetime());
			map.put("createuser", pro.getCreateuser());
			map.put("details", pro.getDetails());
			map.put("isNewProduct", pro.getIsNewProduct());
			map.put("isRecommend", pro.getIsRecommend());

			map.put("isSell", pro.getIsSell());
			map.put("isdelete", pro.getIsdelete());
			map.put("modifytime", pro.getModifytime());
			map.put("modifyuser", pro.getModifyuser());

			map.put("productDescribe", pro.getProductDescribe());
			map.put("riskControl", pro.getRiskControl());
			map.put("title1", pro.getTitle1());
			map.put("title2", pro.getTitle2());
			map.put("yield", pro.getYield());
			map.put("yieldDescribe", pro.getYieldDescribe());
			listMapOj.add(map);
		}
		return listMapOj;
	}

	/**   
	 * 得到理财产品
	 * @Title: getInvestProInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年7月27日 下午5:02:21
	 * @return: void      
	 * @throws   
	 */  
	public void getInvestProInfo() {
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId())) {
				entity = service.findById(entity.getId());
				if(entity != null) {
					jsonObject.put("id", entity.getId());
					jsonObject.put("category", entity.getCategory());
					jsonObject.put("name", entity.getName());
					jsonObject.put("nameZh", entity.getNameZh());
					jsonObject.put("nameDescribe", entity.getNameDescribe());
					jsonObject.put("createtime", entity.getCreatetime());
					jsonObject.put("createuser", entity.getCreateuser());
					jsonObject.put("details", entity.getDetails());
					jsonObject.put("isNewProduct", entity.getIsNewProduct());
					jsonObject.put("isRecommend", entity.getIsRecommend());
					jsonObject.put("isSell", entity.getIsSell());
					jsonObject.put("isdelete", entity.getIsdelete());
					jsonObject.put("modifytime", entity.getModifytime());
					jsonObject.put("modifyuser", entity.getModifyuser());

					jsonObject.put("productDescribe", entity.getProductDescribe());
					jsonObject.put("riskControl", entity.getRiskControl());
					jsonObject.put("title1", entity.getTitle1());
					jsonObject.put("title2", entity.getTitle2());
					jsonObject.put("yield", entity.getYield());
					jsonObject.put("yieldDescribe", entity.getYieldDescribe());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财产品ACTION，方法getInvestProInfo错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财产品ACTION，方法getInvestProInfo错误："+e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
}	
