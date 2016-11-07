package org.duang.action.provider;
import java.util.ArrayList;
import java.util.Date;
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
import org.duang.common.system.MemberCollection;
import org.duang.entity.FriendsNews;
import org.duang.entity.FriendsNewsImg;
import org.duang.service.FriendsNewsImgService;
import org.duang.service.FriendsNewsService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**
 * 接口开发————财友动态Action  
 * @ClassName:  FriendsNewsAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年10月13日 下午5:13:57
 */
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_friendsnews")
public class FriendsNewsAction extends BaseAction<FriendsNews>{
	
	private FriendsNewsService service;
	@Resource
	public void setService(FriendsNewsService service) {
		this.service = service;
	}

	private FriendsNewsImgService friendsNewsImgService;
	@Resource
	public void setFriendsNewsImgService(FriendsNewsImgService friendsNewsImgService) {
		this.friendsNewsImgService = friendsNewsImgService;
	}
	
	/**
	 * 查询财友动态
	 * @Title: queryFriendsNews   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月13日 下午5:22:02
	 * @return: void      
	 * @throws
	 */
	public void queryFriendsNews(){
		boolean success = false;
		try {
			//每页显示条数
			String num = getRequest().getParameter("num");
			//第几页
			String count = getRequest().getParameter("count");
			String token = getRequest().getParameter("token");
			//登录人id
			String id = "";
			if(DataUtils.notEmpty(num) && DataUtils.notEmpty(count) && DataUtils.notEmpty(token) && 
					DataUtils.notEmpty(id = MemberCollection.getInstance(token).getMainField(token))){
				int countNumber = DataUtils.str2int(count), numNumber = DataUtils.str2int(num);
				String sql = "SELECT fn.id, fn.content, fn.createtime, mi.real_name, mi.nickname, mi.user_img "+
							 " FROM friends_news fn LEFT JOIN member_info mi ON mi.id = fn.member_id WHERE fn.member_id IN ( "+
							 " SELECT target FROM friends f WHERE f.self = '"+id+"' ) ORDER BY fn.createtime "+
							 " LIMIT "+ ((countNumber - 1) * numNumber)  +", "+ (countNumber * numNumber);
				List<?> list = service.queryBySQL(sql, null, null, false);
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
	private List<Map<String, Object>> fillDataObjectList(List<?> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for(int i = 0;i<list.size(); i++){
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] objArray = (Object[])list.get(i); 
				map.put("id", objArray[0]);
				map.put("content", objArray[1]);
				map.put("createtime", DateUtils.date2Str((Date)objArray[2], "yyyy-MM-dd HH:mm:ss"));
				map.put("real_name", objArray[3]);
				map.put("nickname", objArray[4]);
				map.put("user_img", objArray[5]);
				String sql = "SELECT * from friends_news_img where friends_news_id='"+objArray[0]+"' order by order_index";
				List<FriendsNewsImg> friendsNewsImgList = friendsNewsImgService.queryBySQL(sql, null, null, true);
				List<String> imgList = new ArrayList<String>();
				for(int j = 0;j<friendsNewsImgList.size(); j++){
					FriendsNewsImg img = friendsNewsImgList.get(j);
					imgList.add(img.getImgPath());
				}
				map.put("imgs", imgList);
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
