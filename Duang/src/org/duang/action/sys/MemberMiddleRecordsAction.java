package org.duang.action.sys;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.MemberMiddleRecords;
import org.duang.service.MemberMiddleRecordsService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 中间人放款记录Action
 * @ClassName:  MemberMiddleRecordsAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年12月5日 上午11:25:36
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "middleRecords")
@ParentPackage("sys")
@Results(value = { })
public class MemberMiddleRecordsAction extends BaseAction<MemberMiddleRecords> {
	private static final long serialVersionUID = 1L;
	
	private MemberMiddleRecordsService memberMiddleRecordsService;
	@Resource
	public void setService(MemberMiddleRecordsService memberMiddleRecordsService) {
		this.memberMiddleRecordsService = memberMiddleRecordsService;
	}
	
	/**
	 * 根据标放款总金额
	 * @Title: queryByScaleId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月6日 下午4:49:06
	 * @return: void      
	 * @throws
	 */
	public void getTotalSumByScaleId() {
		try {
			String scaleId = getRequest().getParameter("scaleId");
			String sql="select SUM(money) from member_middle_records where scale_id='"+scaleId+"'";
			List<?> sumMoney = memberMiddleRecordsService.queryBySQL(sql, null, null, false);
			if(sumMoney.get(0)!=null){
				jsonObject.put("sum", sumMoney.get(0));
			}else{
				jsonObject.put("sum", 0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("根据标放款总金额 MemberMiddleRecordsAction查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("根据标放款总金额 MemberMiddleRecordsAction查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
}
