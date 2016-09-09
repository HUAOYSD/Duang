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
import org.duang.entity.ApplyLoanInfo;
import org.duang.entity.LoanList;
import org.duang.service.ApplyLoanInfoService;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 普通和急速模式贷款的申请信息 action
 * @ClassName:  ApplyLoanInfoAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月22日 上午10:50:51
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "applyloaninfo")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/applyloaninfo/applyLoanInfoList.jsp"),
			@Result(name = "showApplyLoanInfo", type = "dispatcher", location = "WEB-INF/page/sys/loanlist/applyLoanInfo.jsp"),
			@Result(name = "showDatumsAndAssetInfo", type = "dispatcher", location = "WEB-INF/page/sys/loanlist/showDatumsAndAssetInfo.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class ApplyLoanInfoAction extends BaseAction<ApplyLoanInfo> {
	private static final long serialVersionUID = 1L;
	
	private ApplyLoanInfoService applyLoanInfoService;
	@Resource(name = "applyloaninfoserviceimpl")
	public void setService(ApplyLoanInfoService applyLoanInfoService) {
		this.applyLoanInfoService = applyLoanInfoService;
	}

	/**
	 * 跳转到详细列表页面
	 * @Title: gotoList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月22日 上午10:35:08
	 * @return: String      
	 * @throws
	 */
	public String gotoList(){
		return "list";
	}
	
	public void queryAllInfo() {
		try {
			condsUtils.addProperties(true, "loanList");
			condsUtils.concatValue(new String[] { "infoAlias", "as" });
			if(DataUtils.notEmpty(entity.getLoanList().getId())){
				condsUtils.addProperties(false, "infoAlias.id");
				condsUtils.concatValue(new String[] { entity.getLoanList().getId()});
			}
			@SuppressWarnings("rawtypes")
			List list = applyLoanInfoService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
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
			LoggerUtils.error("普通和急速模式贷款的申请信息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("普通和急速模式贷款的申请信息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	
	/**
	 * 封装银行绑定信息   当参数list封装的是一个数组时，list.get[0]是ApplyLoanInfo对象，list.get[1]是MemberInfo对象
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
				ApplyLoanInfo applyLoanInfo = (ApplyLoanInfo) array[1];
				//客户的基本信息
				map.put("id", applyLoanInfo.getId());
				map.put("name", applyLoanInfo.getName());
				map.put("money", applyLoanInfo.getMoney());
				map.put("timeLimit", applyLoanInfo.getTimeLimit());
				map.put("sex", applyLoanInfo.getSex());
				map.put("phone", applyLoanInfo.getPhone());
				map.put("idcard", applyLoanInfo.getIdcard());
				map.put("email", applyLoanInfo.getEmail());
				map.put("nativeAddress", applyLoanInfo.getNativeAddress());
				map.put("nativeInfo", applyLoanInfo.getNativeInfo());
				
				map.put("address", applyLoanInfo.getAddress());
				map.put("liveStyle", applyLoanInfo.getLiveStyle());
				map.put("education", applyLoanInfo.getEducation());
				map.put("marriage", applyLoanInfo.getMarriage());
				map.put("house", applyLoanInfo.getHouse());
				
				map.put("hasCredit", applyLoanInfo.getHasCredit());
				map.put("creditCard", applyLoanInfo.getCreditCard());
				map.put("industry", applyLoanInfo.getIndustry());
				map.put("jobStyle", applyLoanInfo.getJobStyle());
				map.put("job", applyLoanInfo.getJob());
				
				map.put("jobCity", applyLoanInfo.getJobCity());
				map.put("company", applyLoanInfo.getCompany());
				map.put("publicTel", applyLoanInfo.getPublicTel());
				map.put("salaryFromBank", applyLoanInfo.getSalaryFromBank());
				map.put("yearIncome", applyLoanInfo.getYearIncome());
				
				map.put("use", applyLoanInfo.getUse());
				map.put("monthBack", applyLoanInfo.getMonthBack());
				map.put("urgencyPerson", applyLoanInfo.getUrgencyPerson());
				map.put("urgencyPhone", applyLoanInfo.getUrgencyPhone());
				map.put("datums", applyLoanInfo.getDatums());
				map.put("assetCertificates", applyLoanInfo.getAssetCertificates());
				
				LoanList loanList = (LoanList) array[0];
				map.put("loanListId", loanList.getId());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装普通和急速模式贷款的申请信息错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装普通和急速模式贷款的申请信息错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 根据参数查询
	 * @Title: queryByParameter   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月22日 上午11:16:21
	 * @return: void      
	 * @throws
	 */
	public void queryByParameter() {
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
			List<ApplyLoanInfo> list = applyLoanInfoService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			//封装json
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				//fillDataObjectArray方法用于重新组合数据集，让其能够符合页面展示
				//jsonObject.put("rows", fillDataObjectList(list));
				jsonObject.put("total",  getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("条件查询普通和急速模式贷款的申请信息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("条件查询普通和急速模式贷款的申请信息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			//输出json
			printJsonResult();
		}
	}
	
	/**
	 * 展示详细信息
	 * @Title: showApplyLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月22日 下午3:02:50
	 * @return: String      
	 * @throws
	 */
	public String showApplyLoanInfo() {
		try {
			entity = applyLoanInfoService.findEntity("loanList.id", entity.getLoanList().getId());
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("普通和急速模式贷款的申请信息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("普通和急速模式贷款的申请信息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "showApplyLoanInfo";
	}
	
	/**
	 * 个人资料，或者个人收入
	 * @Title: showApplyLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月22日 下午3:02:50
	 * @return: String      
	 * @throws
	 */
	public String showDatumsAndAsset() {
		try {
			entity = applyLoanInfoService.findEntity("loanList.id", entity.getLoanList().getId());
			if(entity!=null){
				//个人资料
				if("datums".equals(getRequest().getParameter("type"))){
					getRequest().setAttribute("path", entity.getDatums());
				}else if("asset".equals(getRequest().getParameter("type"))){
					getRequest().setAttribute("path", entity.getAssetCertificates());
				}
			}else{
				getRequest().setAttribute("path", "");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("普通和急速模式贷款的申请信息ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("普通和急速模式贷款的申请信息ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "showDatumsAndAssetInfo";
	}
}
