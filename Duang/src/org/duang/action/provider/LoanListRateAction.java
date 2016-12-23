package org.duang.action.provider;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.LoanListRate;
import org.duang.service.LoanListRateService;
import org.duang.util.DataUtils;
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
@ParentPackage("provider")
@Action(value = "provider_rate")
public class LoanListRateAction extends BaseAction<LoanListRate> {
	private static final long serialVersionUID = 1L;
	private LoanListRateService loanListRateService;
	@Resource(name = "loanlistrateserviceimpl")
	public void setLoanListRateService(LoanListRateService loanListRateService) {
		this.loanListRateService = loanListRateService;
	}
	
	
	public void getFastLoanMaxSumRate(){
		boolean success = false;
		try {
				List<LoanListRate> list = loanListRateService.queryAllEntity(null);
				if(DataUtils.notEmpty(list)){
					jsonObject.put("maxSum", list.get(0).getFastLoanMaxSum());
				}else{
					jsonObject.put("maxSum", 0);
				}
				success=true;
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("LoanListRateAction——getFastLoanMaxSumRate方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("LoanListRateAction——getFastLoanMaxSumRate方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
}
