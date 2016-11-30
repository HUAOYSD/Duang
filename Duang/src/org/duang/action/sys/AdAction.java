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
import org.duang.entity.Ad;
import org.duang.enums.If;
import org.duang.enums.UploadFile;
import org.duang.service.AdService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.ImageString;
import org.duang.util.ReadProperties;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 广告Action
 * @ClassName:  AdAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年9月5日 上午11:25:36
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "ad")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/ad/ad.jsp"),
			@Result(name = ResultPath.ADD, type = "dispatcher", location = "WEB-INF/page/sys/ad/addAd.jsp"),
			@Result(name = ResultPath.EDIT, type = "dispatcher", location = "WEB-INF/page/sys/ad/editAd.jsp"),
			@Result(name = "bingadimg", type = "dispatcher", location = "WEB-INF/page/sys/ad/bindAdImg.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class AdAction extends BaseAction<Ad> {
	private static final long serialVersionUID = 1L;
	
	private AdService adService;
	@Resource(name = "adserviceimpl")
	public void setService(AdService adService) {
		this.adService = adService;
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
	 * 查询所有
	 * @Title: queryAd   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午3:49:21
	 * @return: void      
	 * @throws
	 */
	public void queryAd() {
		try {
			List<Ad> list = new ArrayList<Ad>();
			if(entity != null && DataUtils.notEmpty(entity.getName())){
				condsUtils.addProperties(true, "name");
				condsUtils.concatValue(new String[] { entity.getName(), "like" });
				condsUtils.addProperties(false, "order");
				condsUtils.addValues(false, Order.desc("createTime"));
				list = adService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			}else{
				list = adService.queryAllEntity(Order.desc("createTime"));
			}
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
			LoggerUtils.error("广告ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("广告ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
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
				entity = adService.findById(entity.getId());
			}else{
				jsonObject.put("result", false);
				jsonObject.put("msg", "实体类为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("广告ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("广告ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * @Title: getAdInfoById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午5:11:54
	 * @return: void      
	 * @throws
	 */
	public void getAdInfoById() {
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId())) {
				Ad ad = adService.findById(entity.getId());
				if (ad != null) {
					jsonObject.put("id", ad.getId());
					jsonObject.put("createTime", DateUtils.date2Str(ad.getCreateTime()));
					jsonObject.put("isUse", ad.getIsUse());
					jsonObject.put("linkAddress", ad.getLinkAddress());
					jsonObject.put("imageAddress", ad.getImageAddress());
					jsonObject.put("name", ad.getName());
					jsonObject.put("remark", ad.getRemark());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("广告ACTION，方法getAdInfoById错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("广告ACTION，方法getAdInfoById错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
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
			LoggerUtils.error("广告Action弹窗错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("广告Action弹窗错误："+e.getLocalizedMessage(), this.getClass());
		}
		return ResultPath.LIST;
	}
	
	/**
	 * 保存广告
	 * @Title: add   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午2:53:47
	 * @return: void      
	 * @throws
	 */
	public void add(){
		try {
			boolean result = false;
			if(entity != null){
				entity.setId(DataUtils.randomUUID());
				entity.setCreateTime(new Date());
				entity.setIsUse(If.If1.getVal());
				result = adService.saveEntity(entity);
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
			LoggerUtils.error("广告ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("广告ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("result", false);
			jsonObject.put("msg", "添加失败！");
		} finally {
			printJsonResult();
		}
	}
	
	
	/**
	 * 修改广告
	 * @Title: add   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午2:53:47
	 * @return: void      
	 * @throws
	 */
	public void update(){
		try {
			boolean result = false;
			if(entity != null){
				Ad ad = adService.findById(entity.getId());
				ad.setLinkAddress(entity.getLinkAddress());
				ad.setName(entity.getName());
				ad.setRemark(entity.getRemark());
				result = adService.updateEntity(ad);
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
			LoggerUtils.error("广告ACTION修改错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("广告ACTION修改错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("result", false);
			jsonObject.put("msg", "修改失败！");
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 启用或者禁用
	 * @Title: add   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月5日 下午2:53:47
	 * @return: void      
	 * @throws
	 */
	public void updateIsUse(){
		try {
			boolean result = false;
			if(entity != null && DataUtils.notEmpty(entity.getId())){
				entity = adService.findById(entity.getId());
				if(entity.getIsUse()==If.If0.getVal()){
					entity.setIsUse(If.If1.getVal());
				}else{
					entity.setIsUse(If.If0.getVal());
				}
				result = adService.updateEntity(entity);
				if(!result){
					jsonObject.put("result", false);
					jsonObject.put("msg", "删除失败！");
				}else{
					jsonObject.put("result", true);
					jsonObject.put("msg", "删除成功！");
				}
			}else{
				jsonObject.put("result", false);
				jsonObject.put("msg", "删除的对象为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("广告ACTION删除错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("广告ACTION删除错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("result", false);
			jsonObject.put("msg", "删除失败！");
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 跳转到上传广告图片的的页面  存放路径：resources/file/basic/ad/
	 * @Title: showAdImage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年9月5日 下午16:18:08
	 * @return: String
	 * @throws
	 */
	public String showAdImage() {
		try{
			//如果type==upload说明是上传，否则为查看
			getRequest().setAttribute("type", getRequest().getParameter("type"));
			//查询兑现
			entity = adService.findById(entity.getId());
			//返回身份证前照和后照的具体路径
			if(DataUtils.notEmpty(entity.getImageAddress())){
				getRequest().setAttribute("path", "/resources/file/basic/ad/"+entity.getImageAddress());
			}else {
				getRequest().setAttribute("path", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("跳转到上传广告图片页面错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("跳转到上传广告图片页面错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "bingadimg";
	}
	
	/**
	 * 上传广告轮播图片
	 * @Title: uploadAdImg   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月30日 下午2:15:14
	 * @return: void      
	 * @throws
	 */
	public void uploadAdImg(){
		boolean success=false;
		try{
			Ad ad = adService.findById(getRequest().getParameter("id"));
			if (ad != null) {
				//基本根路径
				String temPath = getRequest().getSession().getServletContext().getRealPath("/");
				//详细路径
				String suffPath = UploadFile.PATH.getVal(UploadFile.AD.getVal())+"\\";
				//文件名称
				String fileName = DataUtils.randomUUID()+".jpg";
				String fullpath = DataUtils.fileUploadPath(temPath, suffPath, fileName);
				success = ImageString.generateImage(entity.getImageAddress(), fullpath);
				//备份
				String backupPath = ReadProperties.getStringValue(ReadProperties.initPrperties("backupdb.properties"), "fileBasicPath");
				ImageString.generateImage(entity.getImageAddress(), DataUtils.fileUploadPath(backupPath, suffPath, fileName));
				
				LoggerUtils.info("广告上传:"+fileName+"----------------上传广告："+success, this.getClass());
				if(success){
					ad.setImageAddress(fileName);
					success = adService.updateEntity(ad);
				}
			}else{
				msg = "缺少参数,请补充";
			}
		}catch(Exception e){
			success = false;
			msg="上传失败";
			e.printStackTrace();
			LoggerUtils.error("AdAction uploadAdImg：" + e.getMessage(), this.getClass());
			LoggerUtils.error("AdAction uploadAdImg：" + e.getLocalizedMessage(), this.getClass());
		}    
		jsonObject.put("result", success);
		jsonObject.put("msg", msg);
		printJsonResult();
	}
	
}
