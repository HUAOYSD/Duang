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
import org.duang.entity.Ad;
import org.duang.enums.If;
import org.duang.service.AdService;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————广告Action
 * @ClassName:  AdAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_ad")
public class AdAction extends BaseAction<Ad>{
	
	private AdService adService;
	@Resource
	public void setService(AdService adService) {
		this.adService = adService;
	}

	/**   
	 * 获取首页广告
	 * @Title: getHomeAd   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午3:54:30
	 * @return: void      
	 * @throws   
	 */  
	public void getHomeAd(){
		boolean success = false;
		try {
			condsUtils.addProperties(true, "isUse");
			condsUtils.addValues(true, If.If1.getVal());
			String num = getRequest().getParameter("num");
			getPageUtil().setPageRecords(Integer.valueOf(num));
			List<Ad> list = adService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(),getPageUtil());
			if (list != null && list.size() > 0) {
				success = true;
				jsonObject.put("result", fillDataObjectList(list));
			} else {
				msg = "未找到可使用广告！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("AdAction——getHomeAd方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("AdAction——getHomeAd方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
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
	private List<Map<String, Object>> fillDataObjectList(List<Ad> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (Ad ad : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", ad.getId());
				map.put("createTime", DateUtils.date2Str(ad.getCreateTime()));
				map.put("isUse", ad.getIsUse());
				map.put("linkAddress", ad.getLinkAddress());
				map.put("imageAddress", ad.getImageAddress());
				map.put("name", ad.getName());
				map.put("remark", ad.getRemark());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("广告封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("广告封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
}
