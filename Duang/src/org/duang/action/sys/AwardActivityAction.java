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
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.AwardActivity;
import org.duang.service.AwardActivityService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
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
@Action(value = "awardActivity")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/award/awardActivityList.jsp"),
			@Result(name = ResultPath.ADD, type = "dispatcher", location = "WEB-INF/page/sys/award/addAwardActivity.jsp"),
			@Result(name = ResultPath.EDIT, type = "dispatcher", location = "WEB-INF/page/sys/award/editAwardActivity.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class AwardActivityAction extends BaseAction<AwardActivity> {
	private static final long serialVersionUID = 1L;
	
	private AwardActivityService awardActivityService;
	@Resource
	public void setAwardActivityService(AwardActivityService awardActivityService) {
		this.awardActivityService = awardActivityService;
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
	public void queryAll() {
		try {
			LoggerUtils.info("\t\n-----------查询所有的活动--------------------", this.getClass());
			List<AwardActivity> list = new ArrayList<AwardActivity>();
			list = awardActivityService.queryAllEntity(null);
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", awardActivityService.count());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "还没有发起任何的活动！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品活动 queryAll---查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品活动 queryAll---查询错误：" + e.getLocalizedMessage(), this.getClass());
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
	private List<Map<String, Object>> fillDataObjectList(List<AwardActivity> list) {
		LoggerUtils.info("\t\n-------------活动的数量："+list.size(), this.getClass());
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (AwardActivity awardActivity : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", awardActivity.getId());
				map.put("activity_title", awardActivity.getTitle());
				map.put("repeat", awardActivity.getIsRepeat());
				map.put("repeatNum", awardActivity.getRepeatNum());
				map.put("startTime", DateUtils.getTimeStamp(awardActivity.getStartTime()));
				map.put("endTime", DateUtils.getTimeStamp(awardActivity.getEndTime()));
				map.put("createTime", DateUtils.getTimeStamp(awardActivity.getCreateTime()));
				map.put("nowNumber", awardActivity.getNowNumber());
				map.put("winNumber", awardActivity.getWinNumber());
				map.put("code", awardActivity.getCode());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品活动封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品活动错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 保存活动
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
				entity.setCreateTime(new Date());
				entity.setNowNumber(0);
				entity.setWinNumber(0);
				result = awardActivityService.saveEntity(entity);
				if(!result){
					jsonObject.put("success", false);
					jsonObject.put("msg", "添加失败！");
				}else{
					jsonObject.put("success", true);
					jsonObject.put("msg", "添加成功！");
				}
			}else{
				jsonObject.put("success", false);
				jsonObject.put("msg", "添加的对象为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品ACTION查询保存：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品ACTION查询保存：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
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
				result = awardActivityService.updateEntity(entity);
				if(!result){
					jsonObject.put("success", false);
					jsonObject.put("msg", "修改失败！");
				}else{
					jsonObject.put("success", true);
					jsonObject.put("msg", "修改成功！");
				}
			}else{
				jsonObject.put("success", false);
				jsonObject.put("msg", "修改的对象为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品ACTION修改错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品ACTION修改错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
			jsonObject.put("msg", "修改失败！");
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
					entity = awardActivityService.findById(entity.getId());
				}
				return ResultPath.EDIT;
			} else if ("loanlistinfo".equals(path)) {
				getRequest().setAttribute("scaleid", getRequest().getParameter("scaleid"));
				return "loanlistinfo";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("openDialog错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("openDialog错误："+e.getLocalizedMessage(), this.getClass());
		}
		return ResultPath.LIST;
	}
	
	
}
