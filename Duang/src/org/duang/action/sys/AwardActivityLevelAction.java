package org.duang.action.sys;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import org.duang.entity.AwardActivity;
import org.duang.entity.AwardActivityLevel;
import org.duang.entity.MemberInfo;
import org.duang.service.AwardActivityLevelService;
import org.duang.service.MemberInfoService;
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
@Action(value = "awardActivityLevel")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/award/awardActivityLevel.jsp"),
			@Result(name = ResultPath.ADD, type = "dispatcher", location = "WEB-INF/page/sys/award/addAwardActivityLevel.jsp"),
			@Result(name = ResultPath.EDIT, type = "dispatcher", location = "WEB-INF/page/sys/award/editAwardActivityLevel.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class AwardActivityLevelAction extends BaseAction<AwardActivityLevel> {
	private static final long serialVersionUID = 1L;
	
	private AwardActivityLevelService awardActivityLevelService;
	@Resource
	public void setAwardActivityLevelService(AwardActivityLevelService awardActivityLevelService) {
		this.awardActivityLevelService = awardActivityLevelService;
	}

	private MemberInfoService memberInfoService;
	@Resource
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
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
		getRequest().setAttribute("activityId", getRequest().getParameter("activityId"));
		return "list";
	}
	
	/**
	 * 根据活动id查询奖品等级
	 * @Title: queryByActivityId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月14日 下午5:03:40
	 * @return: void      
	 * @throws
	 */
	public void queryByActivityId() {
		try {
			String activityId = getRequest().getParameter("activityId");
			List<AwardActivityLevel> list = new ArrayList<AwardActivityLevel>();
			list = awardActivityLevelService.queryEntity("awardAtivity.id", activityId, null, null);
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total", awardActivityLevelService.count());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "还没有发起任何的活动！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品活动 queryByActivityId---查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品活动 queryByActivityId---查询错误：" + e.getLocalizedMessage(), this.getClass());
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
	private List<Map<String, Object>> fillDataObjectList(List<AwardActivityLevel> list) {
		LoggerUtils.info("\t\n-------------奖品等级的数量："+list.size(), this.getClass());
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (AwardActivityLevel awardActivityLevel : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", awardActivityLevel.getId());
				//等级名称
				map.put("level_title", awardActivityLevel.getTitle());
				//奖品数
				map.put("awardNum", awardActivityLevel.getAwardNum());
				//中奖率
				map.put("odds", awardActivityLevel.getOdds());
				//已中奖次数
				map.put("winNumber", awardActivityLevel.getWinNumber());
				//单次中奖的奖品个数
				map.put("onceNum", awardActivityLevel.getOnceNum());
				//中奖号码
				map.put("winCode", awardActivityLevel.getAllWinCode());
				//活动名称
				AwardActivity awardActivity = awardActivityLevel.getAwardAtivity();
				if(awardActivity!=null){
					//活动名称
					map.put("activityTitle", awardActivity.getTitle());
					map.put("awardActivityId", awardActivity.getId());
				}
				Award award= awardActivityLevel.getAward();
				if(award != null){
					//
					map.put("awardName", award.getName());
					map.put("awardDescription", award.getDescription());
					map.put("awardId",award.getId());
				}
				if(DataUtils.notEmpty(awardActivityLevel.getUserId())){
					MemberInfo memberInfo = memberInfoService.findById(awardActivityLevel.getUserId());
					if(memberInfo!=null){
						map.put("userName",memberInfo.getRealName()+"("+memberInfo.getPhone()+")");
					}
				}
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品等级封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品等级活动错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 保存奖品等级
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
				entity.setWinNumber(0);
				entity.setWinCode(getWinCode(entity.getAwardNum(),entity.getOdds(),entity.getAwardAtivity().getId()));
				entity.setAllWinCode(entity.getWinCode());
				result = awardActivityLevelService.saveEntity(entity);
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
			LoggerUtils.error("奖品等级ACTION保存：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品等级ACTION保存：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
			jsonObject.put("msg", "添加失败！");
		} finally {
			printJsonResult();
		}
	}
	
	
	/**
	 * 删除奖品等级
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月14日 下午5:31:44
	 * @return: void      
	 * @throws
	 */
	public void delete(){
		try {
			boolean result = false;
			if(entity != null){
				result = awardActivityLevelService.deleteEntity(entity.getId());
				if(!result){
					jsonObject.put("success", false);
					jsonObject.put("msg", "删除失败！");
				}else{
					jsonObject.put("success", true);
					jsonObject.put("msg", "删除成功！");
				}
			}else{
				jsonObject.put("success", false);
				jsonObject.put("msg", "删除的对象为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("奖品等级ACTION删除：" + e.getMessage(), this.getClass());
			LoggerUtils.error("奖品等级ACTION删除：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("success", false);
			jsonObject.put("msg", "删除失败！");
		} finally {
			printJsonResult();
		}
	}
	
	
	/**
	 * @throws Exception 
	 * 生成中奖数
	 * @Title: getWinCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param awardNum
	 * @param: @param odd
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年11月17日 下午4:08:28
	 * @return: String      
	 * @throws
	 */
	private String getWinCode(int awardNum ,int odd,String activityId) throws Exception{
		//本次活动，已经生成的带奖品的所有数字集合
		List<String> allCode = getAllWinCode(activityId);
		StringBuffer winCodeBuffer = new StringBuffer();
		int i =1;
		while (i<awardNum) {
			Random random = new Random();
			int randomWinCode = random.nextInt(odd)+(odd*(i-1));
			if(allCode.indexOf(String.valueOf(randomWinCode))<0){
				winCodeBuffer.append(randomWinCode);
				if(i<awardNum-1)
				{
					winCodeBuffer.append(",");
				}				
				i++;
			}
		}
		return winCodeBuffer.toString();
	}
	
	/**
	 * 获取本次活动中的不同等级的所有中奖号码
	 * @Title: getAllWinCode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param activityId
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月18日 上午10:37:23
	 * @return: List<String>      
	 * @throws
	 */
	private List<String>  getAllWinCode(String activityId) throws Exception{
		StringBuffer allCode = new StringBuffer();
		List<AwardActivityLevel> awardActivityLevels = awardActivityLevelService.queryEntity("awardAtivity.id", activityId, null, null);
		if(DataUtils.notEmpty(awardActivityLevels)){
			for(AwardActivityLevel awardActivityLevel : awardActivityLevels){
				String winCode = awardActivityLevel.getAllWinCode();
				allCode.append(winCode).append(",");
			}
		}
		List<String> codeList = Arrays.asList(allCode.toString().split(","));
		return codeList;
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
				result = awardActivityLevelService.updateEntity(entity);
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
				getRequest().setAttribute("awardActivityId", getRequest().getParameter("activityId"));
				return ResultPath.ADD;
			} else if(ResultPath.EDIT.equals(path)) {
				if (entity != null && DataUtils.notEmpty(entity.getId())) {
					entity = awardActivityLevelService.findById(entity.getId());
				}
				return ResultPath.EDIT;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("openDialog错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("openDialog错误："+e.getLocalizedMessage(), this.getClass());
		}
		return ResultPath.LIST;
	}
	
	
}
