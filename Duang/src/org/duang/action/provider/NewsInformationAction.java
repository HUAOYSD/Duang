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
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.NewsInformation;
import org.duang.service.NewsInformationService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**
 * 接口开发————新闻资讯Action  
 * @ClassName:  NewsInformationAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年10月13日 下午4:13:57
 */
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_news")
public class NewsInformationAction extends BaseAction<NewsInformation>{
	
	private NewsInformationService service;
	@Resource
	public void setService(NewsInformationService service) {
		this.service = service;
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
		boolean success = false;
		try {
			//每页显示条数
			String num = getRequest().getParameter("num");
			//第几页
			String count = getRequest().getParameter("count");
			if(DataUtils.notEmpty(num) && DataUtils.notEmpty(count)){
				condsUtils.addProperties(false, "order");
				condsUtils.addValues(false, Order.desc("createtime"));
				List<NewsInformation> list = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), setPageUtil(new PageUtil<NewsInformation>(Integer.parseInt(num), Integer.parseInt(count))));
				success = true;
				jsonObject.put("result", fillDataObjectList(list));
			}else {
				msg="参数无效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("NewsInformationAction——queryNews方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("NewsInformationAction——queryNews方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
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
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("新闻资讯封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("新闻资讯封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
}
