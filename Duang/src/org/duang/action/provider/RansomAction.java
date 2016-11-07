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
import org.duang.common.system.MemberCollection;
import org.duang.entity.Ad;
import org.duang.entity.InvestList;
import org.duang.enums.invest.Status;
import org.duang.service.InvestListService;
import org.duang.service.MemberInfoService;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————理财提前赎回Action
 * @ClassName:  AdAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_ransom")
public class RansomAction extends BaseAction<Ad>{

	private InvestListService service;
	@Resource
	public void setService(InvestListService investListService) {
		this.service = investListService;
	}
	/**
	 * 客户基本信息
	 */
	private MemberInfoService sysMemberInfoService;

	@Resource(name = "sysmemberinfoserviceimpl")
	public void setService(MemberInfoService sysMemberInfoService) {
		this.sysMemberInfoService = sysMemberInfoService;
	}
	
	/**
	 * 获取可提前赎回的列表
	 * 参数：token
	 * 注意点：目前获取的可提前赎回列表为个人所有投资成功理财标，如果后期需要按标准查询在做更改
	 */
	public void queryRansomList(){
		boolean success = false;
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		try {
			String token = getRequest().getParameter("token");
			if (DataUtils.notEmpty(token)) {
				String id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token);
				if(DataUtils.notEmpty(id)){
					List<InvestList> list = service.queryEntity("status", Status.S2.getVal(), null, org.hibernate.criterion.Order.desc("openDate"));
					if (DataUtils.notEmpty(list)) {
						for (InvestList investList : list) {
							Map<String,	Object> map = new HashMap<String, Object>();
							map.put("investlistid", investList.getId());
							//理财记录提前赎回————收取交易金额的百分之一的手续费，从本金中扣除
							double poundage = investList.getMoney() * 0.1D;
							map.put("poundage", poundage);
							//112233
							//详细显示啥子些
						}
					}
					success = true;
				}else {
					msg = "登录无效";
				}
			}else {
				msg = "缺少token值";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("RansomAction——queryRansomList方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("RansomAction——queryRansomList方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		jsonObject.put("result", listmap);
		printJsonResult();
	}
	
	
	
	/**
	 * 提前赎回成功后的回调
	 */
	public void ransomedCallback(){
		boolean success = false;
		try {
			String id = getRequest().getParameter("id");//理财记录的id
			String income = getRequest().getParameter("income");//提前赎回的实际收益
			String poundage = getRequest().getParameter("poundage");//提前赎回的手续费
			if (DataUtils.notEmpty(id) && DataUtils.notEmpty(income) && DataUtils.notEmpty(poundage)) {
			}else {
				msg = "缺少理财记录id值";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("RansomAction——ransomedCallback方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("RansomAction——ransomedCallback方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

}
