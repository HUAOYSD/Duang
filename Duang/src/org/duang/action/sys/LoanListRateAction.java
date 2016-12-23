package org.duang.action.sys;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.LoanListRate;
import org.duang.service.LoanListRateService;
import org.duang.util.DataUtils;
import org.duang.util.ReadProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 基数 action
 * @ClassName:  LoanListRateAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年9月19日 上午10:50:51
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "loanlistrate")
@ParentPackage("sys")
@Results(value = { 
			@Result(name = "list", type = "dispatcher", location = "WEB-INF/page/sys/loanlist/loanListRate.jsp"),
			@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class LoanListRateAction extends BaseAction<LoanListRate> {
	private static final long serialVersionUID = 1L;
	
	private LoanListRateService loanListRateService;
	@Resource(name = "loanlistrateserviceimpl")
	public void setLoanListRateService(LoanListRateService loanListRateService) {
		this.loanListRateService = loanListRateService;
	}
	/**
	 * 展示详细信息
	 * @Title: list   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年12月20日 下午3:02:50
	 * @return: String      
	 * @throws
	 */
	public String list() {
		return "list";
	}
	/**
	 * @Title: query   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月20日 下午5:02:54
	 * @return: void      
	 * @throws
	 */
	public void query() {
		try {
			List<LoanListRate> list = new ArrayList<LoanListRate>();
			list = loanListRateService.queryAllEntity(null);
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectList(list));
			} 
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("查询借贷金额基数 query错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("查询借贷金额基数 query错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	public void edit(){
		boolean success = false;
		try {
			Properties  props = ReadProperties.initPrperties("sign.properties");
			String name = getRequest().getParameter("name");
			double value= DataUtils.str2double(getRequest().getParameter("value"),6);
			String sql = " update loan_list_rate set ";
			if(name.equals(DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "platformRate")))){
				sql+=" platform_rate =";
			}else if(name.equals(DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "overRate")))){
				sql+=" over_rate= ";
			}else if(name.equals(DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "interest")))){
				sql+=" loan_rate = ";
			}else if(name.equals(DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "handRate")))){
				sql+=" hand_rate = ";
			}else if(name.equals(DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "fastLoanMaxSum")))){
				sql+=" fast_loan_max_sum = ";
			}
			sql+=value;
			success = loanListRateService.executeSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("修改借贷金额基数 edit错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("修改借贷金额基数 edit错误：" + e.getLocalizedMessage(), this.getClass());
		}finally {
			jsonObject.put("result", success);
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
	 * @date 2016年12月20日 下午5:06:44
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<LoanListRate> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
				if(DataUtils.notEmpty(list)){
					Properties  props = ReadProperties.initPrperties("sign.properties");
					String loanRate = DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "loanRate"));
				    LoanListRate loanListRate =  list.get(0);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "platformRate")));
					map.put("value", loanListRate.getPlatformRate());
					map.put("group", loanRate);
					map.put("editor", "text");
					listMap.add(map);
					map = new HashMap<String, Object>();
					map.put("name", DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "overRate")));
					map.put("value", loanListRate.getOverRate());
					map.put("group", loanRate);
					map.put("editor", "text");
					listMap.add(map);
					map = new HashMap<String, Object>();
					map.put("name", DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "interest")));
					map.put("value", loanListRate.getLoanRate());
					map.put("group", loanRate);
					map.put("editor", "text");
					listMap.add(map);
					map = new HashMap<String, Object>();
					map.put("name", DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "handRate")));
					map.put("value", loanListRate.getHandRate());
					map.put("group", loanRate);
					map.put("editor", "text");
					listMap.add(map);	
					
					map = new HashMap<String, Object>();
					map.put("name", DataUtils.ISO2UTF8(ReadProperties.getStringValue(props, "fastLoanMaxSum")));
					map.put("value", loanListRate.getFastLoanMaxSum());
					map.put("group", loanRate);
					map.put("editor", "text");
					listMap.add(map);	
				}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷基数封装错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("借贷基数封装错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
}
