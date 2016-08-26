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
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.BindCard;
import org.duang.entity.MemberInfo;
import org.duang.enums.IDCard;
import org.duang.enums.If;
import org.duang.service.BindCardService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 绑定银行卡Action
 * 
 * @ClassName: BindCardAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年8月15日 下午1:55:48
 */

@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "bindcard")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "bindList", type = "dispatcher", location = "WEB-INF/page/sys/bindcard/bindCardInfoList.jsp"),
			@Result(name = "uploadBindCardImg", type = "dispatcher", location = "WEB-INF/page/sys/bindcard/uploadBindCardImg.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class BindCardAction extends BaseAction<BindCard> {
	private static final long serialVersionUID = 1L;
	
	private BindCardService bindCardService;
	@Resource(name = "bindcardserviceimpl")
	public void setService(BindCardService bindCardService) {
		this.bindCardService = bindCardService;
	}

	/**
	 * 页面跳转 ---客户管理页面
	 * @Title: gotoMemberInfoList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月11日 下午3:37:56
	 * @return: String      
	 * @throws
	 */
	public String gotoBindInfoList(){
		return "bindList";
	}
	
	public void queryAllBindedMember() {
		try {
			condsUtils.addProperties(true, "memberInfo");
			condsUtils.concatValue(new String[] { "infoAlias", "as" });
			condsUtils.addProperties(false, "infoAlias.isdelete");
			condsUtils.addValues(false, "0");
			@SuppressWarnings("rawtypes")
			List list = bindCardService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("绑定银行卡ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("绑定银行卡ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 跳转到上传绑定银行卡前后照的页面  存放路径：resources/file/basic/memberInfoId/bindcard/
	 * @Title: gotoUploadBindCardImg
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月15日 下午16:18:08
	 * @return: String
	 * @throws
	 */
	public String showBindCardImage() {
		try{
			String type =  getRequest().getParameter("type");
			//只作为显示，不显示提交图片表单
			getRequest().setAttribute("only_show",If.If1.getVal());
			//展示银行卡前照还是后照标示
			getRequest().setAttribute("type", getRequest().getParameter("type"));
			//查询兑现
			entity = bindCardService.findById(entity.getId());
			//返回身份证前照和后照的具体路径
			if(IDCard.IDCARD1.getVal()==Integer.parseInt(type) && DataUtils.notEmpty(entity.getPhotoImg1())){
				getRequest().setAttribute("path", "/resources/file/basic/"+entity.getId()+"/bindcard/"+entity.getPhotoImg1());
			}else if(IDCard.IDCARD2.getVal()==Integer.parseInt(type) &&  DataUtils.notEmpty(entity.getPhotoImg2())){
				getRequest().setAttribute("path", "/resources/file/basic/"+entity.getId()+"/bindcard/"+entity.getPhotoImg2());
			}else {
				getRequest().setAttribute("path", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("跳转到上传绑定银行卡前后照的页面错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("跳转到上传绑定银行卡前后照的页面错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "uploadBindCardImg";
	}
	
	
	
	/**
	 * 封装银行绑定信息   当参数list封装的是一个数组时，list.get[0]是BindCard对象，list.get[1]是MemberInfo对象
	 * 这种现象只有MemberInfo对象的参数作为查询条件时发生
	 * @Title: fillDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月15日 下午3:54:10
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(@SuppressWarnings("rawtypes") List list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] array = (Object[]) list.get(i);
				BindCard bindCard = (BindCard) array[1];
				//客户的基本信息
				map.put("id", bindCard.getId());
				map.put("name", bindCard.getName());
				map.put("idcard", bindCard.getIdcard());
				map.put("phone", bindCard.getPhone());
				map.put("bankNo", bindCard.getBankNo());
				map.put("openBank", bindCard.getOpenBank());
				map.put("type", bindCard.getType());
				map.put("photoImg1", bindCard.getPhotoImg1());
				map.put("photoImg2", bindCard.getPhotoImg2());
				map.put("optTime", DateUtils.getTimeStamp(bindCard.getOptTime()));
				MemberInfo memberInfo = (MemberInfo) array[0];
				map.put("memberInfoId", memberInfo.getId());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装绑定银行卡错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装绑定银行卡错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 封装银行绑定信息    list封装的是BindCard对象集
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月15日 下午3:54:10
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<BindCard> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (BindCard bindCard : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", bindCard.getId());
				map.put("name", bindCard.getName());
				map.put("idcard", bindCard.getIdcard());
				map.put("phone", bindCard.getPhone());
				map.put("bankNo", bindCard.getBankNo());
				map.put("openBank", bindCard.getOpenBank());
				map.put("type", bindCard.getType());
				map.put("photoImg1", bindCard.getPhotoImg1());
				map.put("photoImg2", bindCard.getPhotoImg2());
				map.put("optTime", DateUtils.getTimeStamp(bindCard.getOptTime()));
				map.put("memberInfoId", bindCard.getMemberInfo().getId());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装绑定银行卡错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装绑定银行卡错误：" + e.getLocalizedMessage(), this.getClass());
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
			if (DataUtils.notEmpty(entity.getPhone())) {
				condsUtils.addProperties(false, "phone");
				condsUtils.concatValue(new String[] { entity.getPhone(), "like" });
			}
			//查询数据
			List<BindCard> list = bindCardService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
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
			LoggerUtils.error("条件查询绑定银行卡ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("条件查询绑定银行卡ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			//输出json
			printJsonResult();
		}
	}
}
