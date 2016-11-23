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
import org.duang.common.system.SessionTools;
import org.duang.entity.NewsInformation;
import org.duang.enums.If;
import org.duang.service.NewsInformationService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**
 * 接口开发————新闻资讯Action  
 * @ClassName:  NewsInformationAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年10月13日 下午4:13:57
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "news")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/news/news.jsp"),
			@Result(name = ResultPath.ADD, type = "dispatcher", location = "WEB-INF/page/sys/news/addNews.jsp"),
			@Result(name = "bingnewsimg", type = "dispatcher", location = "WEB-INF/page/sys/news/bindNewsImg.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })

public class NewsInformationAction extends BaseAction<NewsInformation>{
	private static final long serialVersionUID = 1L;
	private NewsInformationService service;
	@Resource
	public void setService(NewsInformationService service) {
		this.service = service;
	}

	/**
	 * 页面跳转 ---列表页面
	 * @Title: gotolist   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月5日 上午11:27:34
	 * @return: String      
	 * @throws
	 */
	public String gotolist(){
		return "list";
	}
	
	
	/**
	 * 弹窗
	 * @Title: openDialog   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午2:20:05
	 * @return: void      
	 * @throws
	 */
	public String openDialog(){
		try {
			String path = getRequest().getParameter("path");
			if(ResultPath.ADD.equals(path)) {
				return ResultPath.ADD;
			}else if(ResultPath.EDIT.equals(path)){
				return ResultPath.EDIT;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("新闻资讯Action弹窗错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("新闻资讯Action弹窗错误："+e.getLocalizedMessage(), this.getClass());
		}
		return ResultPath.LIST;
	}
	
	/**
	 * 查询新闻资讯
	 * @Title: queryNews   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月13日 下午5:21:42
	 * @return: void      
	 * @throws
	 */
	public void queryNews(){
		try {
			List<NewsInformation> list = new ArrayList<NewsInformation>();
			String sql = "select ni.id,ni.title,ni.content,ni.img,ni.createtime,ni.state,mi.`name` createuser from news_information ni left join sys_user mi on mi.id=ni.createuser "
					   + " ORDER BY ni.createtime DESC";
			list = service.queryBySQL(sql,null,null, true);
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("新闻资讯ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("新闻资讯ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 新闻资讯
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年10月13日 下午4:53:32
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<NewsInformation> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (NewsInformation news : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", news.getId());
				map.put("createtime", DateUtils.date2Str(news.getCreatetime()));
				map.put("content", news.getContent());
				map.put("img", news.getImg());
				map.put("createuser", news.getCreateuser());
				map.put("state", news.getState());
				map.put("title", news.getTitle());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("新闻资讯封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("新闻资讯封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * @Title: add   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月17日 上午10:31:07
	 * @return: void      
	 * @throws
	 */
	public void add(){
		try {
			boolean result = false;
			if(entity != null){
				entity.setId(DataUtils.randomUUID());
				entity.setCreatetime(new Date());
				entity.setCreateuser(SessionTools.getSessionSysUser().getId());
				entity.setState(If.If1.getVal());
				result = service.saveEntity(entity);
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
			LoggerUtils.error("新闻资讯ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("新闻资讯ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("result", false);
			jsonObject.put("msg", "添加失败！");
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 启用或者禁用
	 * @Title: updateIsUse   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月17日 上午11:11:18
	 * @return: void      
	 * @throws
	 */
	public void updateIsUse(){
		try {
			boolean result = false;
			if(entity != null && DataUtils.notEmpty(entity.getId())){
				entity = service.findById(entity.getId());
				if(entity.getState()==If.If0.getVal()){
					entity.setState(If.If1.getVal());
				}else{
					entity.setState(If.If0.getVal());
				}
				result = service.updateEntity(entity);
				if(!result){
					jsonObject.put("result", false);
					jsonObject.put("msg", "操作失败！");
				}else{
					jsonObject.put("result", true);
					jsonObject.put("msg", "操作成功！");
				}
			}else{
				jsonObject.put("result", false);
				jsonObject.put("msg", "操作的对象为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("新闻资讯ACTION删除错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("新闻资讯ACTION删除错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("result", false);
			jsonObject.put("msg", "删除失败！");
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 存放路径：resources/file/basic/ad/
	 * @Title: showAdImage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年9月5日 下午16:18:08
	 * @return: String
	 * @throws
	 */
	public String showNewsImage() {
		try{
			//如果type==upload说明是上传，否则为查看
			getRequest().setAttribute("type", getRequest().getParameter("type"));
			//查询兑现
			entity = service.findById(entity.getId());
			//返回身份证前照和后照的具体路径
			if(DataUtils.notEmpty(entity.getImg())){
				getRequest().setAttribute("path", "/resources/file/basic/news/"+entity.getImg());
			}else {
				getRequest().setAttribute("path", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("跳转到图片页面错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("跳转到图片页面错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "bingnewsimg";
	}
	
	/**
	 * 根据id查询
	 * @Title: findById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 上午11:37:20
	 * @return: void      
	 * @throws
	 */
	public void findById(){
		try {
			if(entity != null && DataUtils.notEmpty(entity.getId())){
				entity = service.findById(entity.getId());
			}else{
				jsonObject.put("result", false);
				jsonObject.put("msg", "实体类为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("新闻资讯ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("新闻资讯ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
}
