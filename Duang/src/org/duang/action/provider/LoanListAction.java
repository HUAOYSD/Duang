package org.duang.action.provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.LoanList;
import org.duang.enums.loan.Apply;
import org.duang.enums.loan.BackMoney;
import org.duang.enums.loan.BackStyle;
import org.duang.enums.loan.LoanMode;
import org.duang.enums.loan.Poundage;
import org.duang.enums.loan.Scale;
import org.duang.enums.loan.TakeMoney;
import org.duang.service.LoanListService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.PageUtil;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————借贷记录Action
 * @ClassName:  LoanListAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月9日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_loanlist")
public class LoanListAction extends BaseAction<LoanList>{
	
	
	private LoanListService service;
	@Resource
	public void setService(LoanListService service) {
		this.service = service;
	}

//	/**   
//	 * 查询申请中的借款记录
//	 * @Title: queryAppling   
//	 * @Description: TODO(这里用一句话描述这个方法的作用)   
//	 * @param:   
//	 * @author 5y    
//	 * @date 2016年10月9日 上午11:08:50
//	 * @return: void      
//	 * @throws   
//	 */  
//	public void queryAppling(){
//	}
//	
//	
//	/**   
//	 * 查询申请完成的借款记录
//	 * @Title: queryApplied   
//	 * @Description: TODO(这里用一句话描述这个方法的作用)   
//	 * @param:   
//	 * @author 5y    
//	 * @date 2016年10月9日 上午11:08:50
//	 * @return: void      
//	 * @throws   
//	 */  
//	public void queryApplied(){
//	}
	
	
	/**   
	 * 查询借款信息
	 * @Title: queryLoanList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月9日 下午4:25:24
	 * @return: void      
	 * @throws   
	 */  
	public void queryLoanList(){
		boolean success = false;
		JSONArray jsonArray = new JSONArray();
		try {
			String token = getRequest().getParameter("token");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance().getMainField(token))) {
				int num = DataUtils.str2int(getRequest().getParameter("num"));
				int count = DataUtils.str2int(getRequest().getParameter("count"));
				if (num != 0 && count != 0) {
					List<LoanList> loanLists = service.queryEntity("memberInfo.id", id, setPageUtil(new PageUtil<LoanList>(num, count)), Order.desc("createTime"));
					success = true;
					if (DataUtils.notEmpty(loanLists)) {
						for (LoanList loanlist : loanLists) {
							jsonArray.add(fillData(new JSONObject(), loanlist));
						}
					}else{
						msg = "还没有借款中的记录";
					}
				}else {
					msg = "参数无效";
				}
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		jsonObject.put("result", jsonArray);
		printJsonResult();
	}
	
	/**
	 * 根据审核状况查询借贷列表
	 * @Title: queryInApplyLoan   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月30日 下午2:44:32
	 * @return: void      
	 * @throws
	 */
	public void queryLoanListByApplyState(){
		boolean success = false;
		JSONArray jsonArray = new JSONArray();
		try {
			String token = getRequest().getParameter("token");
			String applyState = getRequest().getParameter("applyState");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance().getMainField(token))&& DataUtils.notEmpty(applyState)) {
				//单次获取标个数
				int num = DataUtils.str2int(getRequest().getParameter("num"));
				//下拉次数（首次打开次数累计1次）
				int count = DataUtils.str2int(getRequest().getParameter("count"));
				if (num != 0 && count != 0) {
					condsUtils.addProperties(true, "memberInfo.id");
					condsUtils.concatValue(id);
					condsUtils.addProperties(false, "applyState");
					condsUtils.addValues(false, DataUtils.str2int(applyState));
					condsUtils.addProperties(false, "order");
					condsUtils.addValues(false, Order.desc("createTime"));
					List<LoanList> loanLists = service.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), setPageUtil(new PageUtil<LoanList>(num, count)));
					success = true;
					if (DataUtils.notEmpty(loanLists)) {
						for (LoanList loanlist : loanLists) {
							jsonArray.add(fillData(new JSONObject(), loanlist));
						}
					}else{
						msg = "你没有借贷记录";
					}
				}else {
					msg = "参数无效";
				}
			}else {
				msg = "登录失效或者参数为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		jsonObject.put("result", jsonArray);
		printJsonResult();
	}
	
	
	/**   
	 * 查找单个借贷记录详情
	 * @Title: findLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月9日 下午4:30:13
	 * @return: void      
	 * @throws   
	 */  
	public void findLoanInfo(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance().getMainField(token))) {
				String loanid = DES.decryptDES(getRequest().getParameter("p_id"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", loanid);
				map.put("memberInfo.id", id);
				entity = service.findEntity(map);
				success = true;
				if (entity == null) {
					jsonObject.put("result", fillData(jsonObject, entity));
				}else{
					msg = "未找到记录";
				}
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("FriendsAction——addFriends方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 填充数据
	 * @Title: fillData   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param loanlist  
	 * @author 5y    
	 * @date 2016年9月14日 上午9:50:08
	 * @return: void      
	 * @throws   
	 */  
	private JSONObject fillData(JSONObject jsonObject, LoanList loanlist) {
		jsonObject.put("memberid", loanlist.getMemberInfo().getId());
		jsonObject.put("membername", loanlist.getMemberInfo().getRealName());
		jsonObject.put("pactNumber", loanlist.getPactNumber());
		jsonObject.put("isSell", Scale.valueOf("S"+loanlist.getIsSell()).toString());
		jsonObject.put("poundageState", Poundage.valueOf("P"+loanlist.getIsSell()).toString());
		jsonObject.put("money", loanlist.getMoney());
		jsonObject.put("realMoney", loanlist.getRealMoney());
		jsonObject.put("poundage", loanlist.getPoundage());
		jsonObject.put("getMoney", loanlist.getGetMoney());
		jsonObject.put("yetMoney", loanlist.getYetMoney());
		jsonObject.put("returnMoney", loanlist.getReturnMoney());
		jsonObject.put("agoMoney", loanlist.getAgoMoney());
		jsonObject.put("yetReturnMoney", loanlist.getYetReturnMoney());
		jsonObject.put("returnStatus", BackMoney.valueOf("B"+loanlist.getReturnStatus()).toString());
		jsonObject.put("loanState", TakeMoney.valueOf("T"+loanlist.getLoanState()).toString());
		jsonObject.put("applyStateint", Apply.valueOf("A"+loanlist.getApplyState()).toString());
		jsonObject.put("loanType", LoanMode.valueOf("M"+loanlist.getLoanType()).toString());
		jsonObject.put("loanUse", loanlist.getLoanUse());
		jsonObject.put("loanInterest", loanlist.getLoanInterest());
		jsonObject.put("createTime", DateUtils.date2Str(loanlist.getCreateTime(), "yyyy-MM-dd"));
		jsonObject.put("signDate", DateUtils.date2Str(loanlist.getSignDate(), "yyyy-MM-dd"));
		jsonObject.put("beginReturnDate", DateUtils.date2Str(loanlist.getBeginReturnDate(), "yyyy-MM-dd"));
		jsonObject.put("endReturnDate", DateUtils.date2Str(loanlist.getEndReturnDate(), "yyyy-MM-dd"));
		jsonObject.put("doneReturnDate", DateUtils.date2Str(loanlist.getDoneReturnDate(), "yyyy-MM-dd"));
		jsonObject.put("backStyle", BackStyle.valueOf("B"+loanlist.getBackStyle()).toString());
		jsonObject.put("days", loanlist.getDays());
		jsonObject.put("passTime", DateUtils.date2Str(loanlist.getPassTime(), "yyyy-MM-dd"));
		return jsonObject;
	}
	
	
}
