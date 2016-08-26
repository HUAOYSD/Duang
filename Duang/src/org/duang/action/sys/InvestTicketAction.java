package org.duang.action.sys;
import java.util.ArrayList;
import java.util.Date;
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
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.InvestTicket;
import org.duang.enums.If;
import org.duang.service.InvestTicketService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 理财券管理Action
 * 
 * @ClassName: InvestTicketAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年8月16日 下午4:55:48
 */

@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "investticket")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "investticketlist", type = "dispatcher", location = "WEB-INF/page/sys/investticket/investTicketList.jsp"),
			@Result(name = "addinvestticketlist", type = "dispatcher", location = "WEB-INF/page/sys/investticket/addInvestTicket.jsp"),
			@Result(name="selectProToTicket", type="dispatcher", location="WEB-INF/page/sys/investporduct/investProToTicketList.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class InvestTicketAction extends BaseAction<InvestTicket> {
	private static final long serialVersionUID = 1L;
	
	private InvestTicketService investTicketService;
	@Resource(name = "investticketserviceimpl")
	public void setService(InvestTicketService investTicketService) {
		this.investTicketService = investTicketService;
	}

	/**
	 * 页面跳转 ---跳转到理财券的管理页面
	 * @Title: gotoInvestTicketList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午4:37:56
	 * @return: String      
	 * @throws
	 */
	public String gotoInvestTicketList(){
		return "investticketlist";
	}
	
	/**
	 * 页面跳转 ---跳转到添加理财券的管理页面
	 * @Title: gotoAddInvestTicket   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午4:37:56
	 * @return: String      
	 * @throws
	 */
	public String gotoAddInvestTicket(){
		return "addinvestticketlist";
	}
	
	/**
	 * 跳转到为理财券选择参与的理财产品
	 * @Title: selectProToTicket   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月17日 上午10:38:45
	 * @return: String      
	 * @throws
	 */
	public String gotoSelectProToTicket(){
		String id = getRequest().getParameter("id");
		getRequest().setAttribute("investTicketId", id);
		try{
			//获取已经绑定的理财产品
			entity = investTicketService.findById(id);
			getRequest().setAttribute("investProductIds", entity.getProductIds());
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("理财券ACTION跳转到为理财券选择参与的理财产品错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财券ACTION跳转到为理财券选择参与的理财产品错误："+e.getLocalizedMessage(), this.getClass());
		}
		return "selectProToTicket";
	}
	/**
	 * 页面跳转 ---跳转到修改理财券的管理页面
	 * @Title: gotoAddInvestTicket   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午4:37:56
	 * @return: String      
	 * @throws
	 */
	public String gotoUpdateInvestTicket(){
		String id = getRequest().getParameter("id");
		try{
			getRequest().setAttribute("id", id);
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("理财券ACTION修改错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财券ACTION修改错误："+e.getLocalizedMessage(), this.getClass());
		}
		return "addinvestticketlist";
	}
	
	/**
	 * 获取所有的理财券信息
	 * @Title: queryAllInvestTicket   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午4:53:21
	 * @return: void      
	 * @throws
	 */
	public void queryAllInvestTicket() {
		try {
			List<InvestTicket> list = investTicketService.queryAllEntity(getPageUtil(),Order.asc("createTime"));
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财券ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财券ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 根据Id获取理财券的对象
	 * @Title: getInvestTicketById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午4:54:18
	 * @return: void      
	 * @throws
	 */
	public void getInvestTicketById() {
		try {
			entity = investTicketService.findById(entity.getId());
			jsonObject.put("id", entity.getId());
			jsonObject.put("name", entity.getName());
			jsonObject.put("remark", entity.getRemark());
			jsonObject.put("describes", entity.getDescribes());
			jsonObject.put("money", entity.getMoney());
			jsonObject.put("beginTime", DateUtils.date2Str(entity.getBeginTime()));
			jsonObject.put("endTime", DateUtils.date2Str(entity.getEndTime()));
			jsonObject.put("createTime", DateUtils.date2Str(entity.getCreateTime()));
			jsonObject.put("minMoney", entity.getMinMoney());
			jsonObject.put("productIds", entity.getProductIds());
			jsonObject.put("state", entity.getState());
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财券ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财券ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 封装理财券信息    list封装的是InvestTicket对象集
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午3:54:10
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<InvestTicket> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (InvestTicket investTicket : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", investTicket.getId());
				map.put("name", investTicket.getName());
				map.put("remark", investTicket.getRemark());
				map.put("describes", investTicket.getDescribes());
				map.put("money", investTicket.getMoney());
				map.put("beginTime", DateUtils.date2Str(investTicket.getBeginTime()));
				map.put("endTime", DateUtils.date2Str(investTicket.getEndTime()));
				map.put("createTime", DateUtils.date2Str(investTicket.getCreateTime()));
				map.put("minMoney", investTicket.getMinMoney());
				map.put("productIds", investTicket.getProductIds());
				map.put("state", investTicket.getState());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装理财券信息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装理财券信息错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 根据参数查询绑定的银行卡信息
	 * @Title: queryBandCardByParameter   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月15日 下午3:24:25
	 * @return: void      
	 * @throws
	 */
	public void queryBandCardByParameter() {
		try {
			//封装参数
			if (DataUtils.notEmpty(entity.getName())) {
				condsUtils.addProperties(false, "name");
				condsUtils.concatValue(new String[] { entity.getName(), "like" });
			}
			//查询数据
			List<InvestTicket> list = investTicketService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			//封装json
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				//fillDataObjectArray方法用于重新组合数据集，让其能够符合页面展示
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("根据条件查询理财券ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("根据条件查询理财券ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			//输出json
			printJsonResult();
		}
	}
	
	
	/**
	 * 保存
	 * @Title: saveInvestTicket   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月16日 下午3:46:59
	 * @return: void      
	 * @throws
	 */
	public void saveInvestTicket() {
		if (entity != null) {
			try {
				boolean issuccess = false;
				//编辑
				if(DataUtils.notEmpty(entity.getId())){
					issuccess = investTicketService.updateEntity(entity);	
				}else{//添加
					// 判断是否存在相同总名称的数据，如果存在则取消添加
					if (investTicketService.count("name", entity.getName()) > 0) {
						jsonObject.put("result", false);
						jsonObject.put("msg", "添加失败，已经存在相同名称的理财券！");
						printJsonResult();
					} else {
						entity.setId(DataUtils.randomUUID());
						entity.setCreateTime(new Date());
						issuccess = investTicketService.saveEntity(entity);
					}
				}
				if (issuccess) {
					jsonObject.put("result", true);
					jsonObject.put("msg", "添加理财券成功");
					printJsonResult();
				} else {
					jsonObject.put("result", false);
					jsonObject.put("msg", "添加理财券失败，请联系管理员");
					printJsonResult();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("理财券ACTION增加错误：" + e.getMessage(), this.getClass());
				LoggerUtils.error("理财券ACTION增加错误：" + e.getLocalizedMessage(), this.getClass());
			}
		}
	}
	
	/**
	 * 删除理财券
	 * @Title: deleteInvestTicket   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月17日 上午9:21:38
	 * @return: void      
	 * @throws
	 */
	public void deleteInvestTicket() {
		try {
			boolean issuccess = false;
			if(DataUtils.notEmpty(entity.getId())){
				entity = investTicketService.findById(entity.getId());
				if(entity!=null){
					entity.setState(If.If1.getVal());
					issuccess = investTicketService.updateEntity(entity);
					if (issuccess) {
						print(true, "删除理财券成功");
					} else {
						print(false, "删除理财券失败，请联系管理员");
					}
				}else{
					print(false, "此理财券已经不存在，请刷新后重试");
				}
			}else{
				print(false, "未选中任何删除的理财券");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财券ACTION删除错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财券ACTION删除错误：" + e.getLocalizedMessage(), this.getClass());
			print(false, "发生未知错误，请联系管理员");
		}
	}
	
	/**
	 * 修改理财券参与的产品
	 * @Title: updateInvestTicketProduct   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月17日 上午9:21:38
	 * @return: void      
	 * @throws
	 */
	public void updateInvestTicketProduct() {
		try {
			boolean issuccess = false;
			if(DataUtils.notEmpty(entity.getId())){
				InvestTicket investTicket = investTicketService.findById(entity.getId());
				if(investTicket!=null){
					investTicket.setProductIds(entity.getProductIds());
					issuccess = investTicketService.updateEntity(investTicket);
					if (issuccess) {
						print(true, "绑定产品成功");
					} else {
						print(false, "绑定产品失败，请联系管理员");
					}
				}else{
					print(false, "此理财券已经不存在，请刷新后重试");
				}
			}else{
				print(false, "未选中任何的理财券");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财券ACTION绑定产品错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财券ACTION绑定产品错误：" + e.getLocalizedMessage(), this.getClass());
			print(false, "发生未知错误，请联系管理员");
		}
	}
	
	/**
	 * 返回信息到前台
	 * @Title: print   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param msg  
	 * @author LiYonghui    
	 * @date 2016年8月17日 上午10:10:25
	 * @return: void      
	 * @throws
	 */
	private void print(boolean result,String msg){
		jsonObject.put("result", result);
		jsonObject.put("msg", msg);
		printJsonResult();
	}
	
}
