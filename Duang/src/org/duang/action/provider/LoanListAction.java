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
 * @author 白攀
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


	/**   
	 * 查询借贷记录列表
	 * @Title: queryLoanList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
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
					if (DataUtils.notEmpty(loanLists)) {
						for (LoanList loanlist : loanLists) {
							jsonArray.add(fillData(new JSONObject(), loanlist));
						}
						success = true;
					}else{
						msg = "你没有借贷记录";
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
	 * 查找单个借贷记录详情
	 * @Title: findLoanInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
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
				if (entity == null) {
					fillData(jsonObject, entity);
					success = true;
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
	 * @author 白攀    
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
