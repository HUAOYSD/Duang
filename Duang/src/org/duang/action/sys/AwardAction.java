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
import org.duang.entity.Award;
import org.duang.service.AwardService;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 奖品管理
 * @ClassName:  AwardAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年11月14日 下午5:01:31
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "award")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/award/award.jsp"),
			@Result(name = ResultPath.ADD, type = "dispatcher", location = "WEB-INF/page/sys/award/addAward.jsp"),
			@Result(name = ResultPath.EDIT, type = "dispatcher", location = "WEB-INF/page/sys/award/editAward.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class AwardAction extends BaseAction<Award> {
	private static final long serialVersionUID = 1L;
	
	private AwardService awardService;
	@Resource
	public void setAwardService(AwardService awardService) {
		this.awardService = awardService;
	}

	/**
	 * 页面跳转 ---列表页面
	 * @Title: gotolist   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年11月14日 上午11:27:34
	 * @return: String      
	 * @throws
	 */
	public String gotolist(){
		return "list";
	}
	
	/**
	 * 查询所有的奖品
	 * @Title: queryAward   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月14日 下午5:03:40
	 * @return: void      
	 * @throws
	 */
	public void queryAllAward() {
		try {
			LoggerUtils.info("\t\n-----------查询所有的奖品--------------------", this.getClass());
			List<Award> list = new ArrayList<Award>();
			list = awardService.queryAllEntity(null);
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "还没添加任何的奖品！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品queryAllAward-----queryAllAward查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品queryAllAward-----queryAllAward查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 封装集合
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午2:53:00
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<Award> list) {
		LoggerUtils.info("\t\n-------------奖品的数量："+list.size(), this.getClass());
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (Award award : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", award.getId());
				map.put("awardName", award.getName());
				map.put("state", award.getState());
				map.put("description", award.getDescription());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	
	
	/**
	 * 
	 * @Title: getAdInfoById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月14日 下午5:27:18
	 * @return: void      
	 * @throws
	 */
	public void getAdInfoById() {
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId())) {
				Award award = awardService.findById(entity.getId());
				if (award != null) {
					//客户的基本信息
					jsonObject.put("id", award.getId());
					jsonObject.put("awardName", award.getName());
					jsonObject.put("state", award.getState());
					jsonObject.put("description", award.getDescription());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品ACTION，方法getAdInfoById错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品ACTION，方法getAdInfoById错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 保存奖品
	 * @Title: save   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月14日 下午5:31:44
	 * @return: void      
	 * @throws
	 */
	public void save(){
		try {
			boolean result = false;
			if(entity != null){
				entity.setId(DataUtils.randomUUID());
				result = awardService.saveEntity(entity);
				if(!result){
					jsonObject.put("result", false);
					jsonObject.put("msg", "添加失败！");
				}else{
					jsonObject.put("result", true);
					jsonObject.put("msg", "添加成功！");
				}
			}else{
				jsonObject.put("result", false);
				jsonObject.put("msg", "添加的对象为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品ACTION查询保存：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品ACTION查询保存：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("result", false);
			jsonObject.put("msg", "添加失败！");
		} finally {
			printJsonResult();
		}
	}
	
	
	/**
	 * 修改奖品
	 * @Title: update   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月14日 下午2:53:47
	 * @return: void      
	 * @throws
	 */
	public void update(){
		try {
			boolean result = false;
			if(entity != null){
				result = awardService.updateEntity(entity);
				if(!result){
					jsonObject.put("result", false);
					jsonObject.put("msg", "修改失败！");
				}else{
					jsonObject.put("result", true);
					jsonObject.put("msg", "修改成功！");
				}
			}else{
				jsonObject.put("result", false);
				jsonObject.put("msg", "修改的对象为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品ACTION修改错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品ACTION修改错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("result", false);
			jsonObject.put("msg", "修改失败！");
		} finally {
			printJsonResult();
		}
	}
	
}
