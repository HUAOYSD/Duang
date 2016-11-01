package org.duang.action.provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanMember;
import org.duang.entity.MemberInfo;
import org.duang.service.MemberInfoService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————会员Action
 * @ClassName:  MemberAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_member")
public class MemberAction extends BaseAction<MemberInfo>{
	
	private MemberInfoService service;

	@Resource
	public void setService(MemberInfoService service) {
		this.service = service;
	}
	
	/**   
	 * 验证登录密码
	 * @Title: checkLoginPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void checkLoginPassword(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("pwd"))) {
				MemberInfo memberInfo = service.findEntity("id", entity.getId());
				if (memberInfo != null) {
					if (getRequest().getParameter("pwd").equals(memberInfo.getPassword())) {
						success = true;
					} else {
						msg = "登录密码错误";
					}
				} else {
					msg = "未查到该用户";
				}
			}else{
				msg = "密码不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——checkLoginPassword方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——checkLoginPassword方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 修改登录密码
	 * @Title: modifyLoginPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyLoginPassword(){
		boolean success = false;
		try {
			String pwd = getRequest().getParameter("pwd");
			String old_pwd = getRequest().getParameter("old_pwd");
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(pwd) && DataUtils.notEmpty(old_pwd)) {
				MemberInfo memberInfo = service.findEntity("id", entity.getId());
				if (memberInfo != null) {
					if(!memberInfo.getPassword().equals(old_pwd)){
						msg = "旧密码不匹配，请重新输入！";
					}else{
						memberInfo.setPassword(pwd);
						success = service.updateEntity(memberInfo);
						if(!success){
							msg = "修改登录密码错误，连接超时";
						}
					}
				} else {
					msg = "未查到该用户";
				}
			}else{
				msg = "密码不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——modifyLoginPassword方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——modifyLoginPassword方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 验证支付密码
	 * @Title: checkPayPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void checkPayPassword(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("pwd"))) {
				MemberInfo memberInfo = service.findEntity("id", entity.getId());
				if (memberInfo != null) {
					if (getRequest().getParameter("pwd").equals(memberInfo.getPayPassword())) {
						success = true;
					} else {
						msg = "支付密码错误";
					}
				} else {
					msg = "未查到该用户";
				}
			}else{
				msg = "密码为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——checkPayPassword方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——checkPayPassword方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 修改支付密码
	 * @Title: modifyPayPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyPayPassword(){
		boolean success = false;
		try {
			String pwd = getRequest().getParameter("pwd");
			String old_pwd = getRequest().getParameter("old_pwd");
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(pwd) && DataUtils.notEmpty(old_pwd)) {
				MemberInfo memberInfo = service.findEntity("id", entity.getId());
				if (memberInfo != null) {
					if(DataUtils.notEmpty(memberInfo.getPayPassword())){
						if(old_pwd.equals(memberInfo.getPayPassword())){
							memberInfo.setPayPassword(getRequest().getParameter("pwd"));
							success = service.updateEntity(memberInfo);
							if(!success){
								msg = "修改支付密码错误，连接超时";
							}
						}else{
							msg="旧支付密码不匹配，请重新输入";
						}
					}else {
						msg="未设置过支付密码，不能修改";
					}
				} else {
					msg = "未查到该用户";
				}
			}else{
				msg = "密码不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——modifyPayPassword方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——modifyPayPassword方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	/**
	 * 设置支付密码
	 * @Title: setPayPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月24日 下午2:51:16
	 * @return: void      
	 * @throws
	 */
	public void setPayPassword(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("pwd"))) {
				MemberInfo memberInfo = service.findEntity("id", entity.getId());
				if (memberInfo != null) {
					if(DataUtils.notEmpty(memberInfo.getPayPassword())){
						msg = "已设置过支付密码，不能重复设置";
					}else{
						memberInfo.setPayPassword(getRequest().getParameter("pwd"));
						success = service.updateEntity(memberInfo);
						if(!success){
							msg = "修改支付密码错误，连接超时";
						}
					}
					
				} else {
					msg = "未查到该用户";
				}
			}else{
				msg = "密码不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——setPayPassword方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——setPayPassword方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 修改用户名
	 * @Title: modifyNickName   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyNickName(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("nickname"))) {
				String DESNickname = getRequest().getParameter("nickname");
				String nickName = DES.decryptDES(DESNickname);
				MemberInfo memberInfoByNickName = service.findEntity("nickname", nickName);
				if(memberInfoByNickName !=null){
					success = false;
					msg = "该昵称已被使用，请重新填写";
				}else{
					MemberInfo memberInfo = service.findEntity("id", entity.getId());
					if (memberInfo != null) {
						memberInfo.setNickname(nickName);
						success = service.updateEntity(memberInfo);
						if(!success){
							msg = "修改失败，请检查网络";
						}
						
					} else {
						msg = "未查到该用户";
					}
				}
			}else{
				msg = "用户名不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——modifyNickName方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——modifyNickName方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**   
	 * 验证用户真实名字和身份证号
	 * @Title: modifyNameAndIdcard   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月14日 下午2:25:13
	 * @return: void      
	 * @throws   
	 */  
	public void modifyNameAndIdcard(){
		boolean success = false;
		try {
			if (entity != null && DataUtils.notEmpty(getRequest().getParameter("idcard")) && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(getRequest().getParameter("name"))) {
				String DESIdCard = getRequest().getParameter("idcard");
				String idcard = DES.decryptDES(DESIdCard);
				MemberInfo memberInfoByNickName = service.findEntity("idCard", idcard);
				if(memberInfoByNickName !=null){
					success = false;
					msg = "身份证信息已被占用，请检查";
				}else{
					MemberInfo memberInfo = service.findEntity("id", entity.getId());
					if (memberInfo != null) {
						String DESName = getRequest().getParameter("name");
						memberInfo.setRealName(DES.decryptDES(DESName));
						memberInfo.setIdCard(idcard);
						success = service.updateEntity(memberInfo);
						if(!success){
							msg = "身份证信息已被占用，请检查";
						}
					} else {
						msg = "未查到该用户";
					}
				}
			}else{
				msg = "用户名不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——modifyNameAndIdcard方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——modifyNameAndIdcard方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 根据id查找用户的具体信息
	 * @Title: findMemberById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年9月30日 下午3:25:03
	 * @return: void      
	 * @throws
	 */
	public void findMemberById() {
		boolean success = false;
		List<MemberInfo> list = new ArrayList<MemberInfo>();
		try {
			String token = getRequest().getParameter("token");
			String id = token;
			if (DataUtils.notEmpty(token)) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", id);
				MemberInfo memberInfo = service.findEntity(params);
				if (memberInfo != null) {
					list.add(memberInfo);
					jsonObject.put("result", fillDataObjectArray(list));
				}else {
					msg="未查到记录";
				}
				success = true;
			}else{
				msg="数据丢失，请重新登录";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——findMemberById方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——findMemberById方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		} finally {
			jsonObject.put("msg", msg);
			jsonObject.put("success", success);
			printJsonResult();
		}
	}
	
	/**
	 * 获取用户的金钱信息
	 * @Title: findMemberMoneyById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月21日 下午4:31:20
	 * @return: void      
	 * @throws
	 */
	public void findMemberMoneyById() {
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = "";
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance().getMainField(token))) {
				MemberInfo memberInfo = service.findById(id);
				if (memberInfo != null) {
					fillMemberInfo(memberInfo);
				}else {
					msg="未查到记录";
				}
				success = true;
			}else{
				msg="数据丢失，请重新登录";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——findMemberMoneyById方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——findMemberMoneyById方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		} finally {
			jsonObject.put("msg", msg);
			jsonObject.put("success", success);
			printJsonResult();
		}
	}
	
	/**
	 * 填充信息
	 * @Title: fillMemberInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param token  
	 * @author LiYonghui    
	 * @date 2016年10月21日 下午4:33:25
	 * @return: void      
	 * @throws
	 */
	private void fillMemberInfo(MemberInfo memberInfo){
		jsonObject.put("time", DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		jsonObject.put("id", memberInfo.getId());
		jsonObject.put("name", memberInfo.getRealName());

		Set<InvestMember> investMembers = memberInfo.getInvestMembers();
		if(investMembers!=null && investMembers.size()>0){
			InvestMember investMember = investMembers.iterator().next();
			jsonObject.put("money", investMember.getTotalMoney());
			jsonObject.put("investing", investMember.getInvesting());
			jsonObject.put("balance", investMember.getBalance());
			jsonObject.put("totalEarnings", investMember.getTotalIncome());
			jsonObject.put("currentEarnings", investMember.getCurrentIncome());
		}else {
			jsonObject.put("money", 0);
			jsonObject.put("investing", 0);
			jsonObject.put("balance", 0);
			jsonObject.put("totalEarnings", 0);
			jsonObject.put("currentEarnings", 0);
		}
		Set<LoanMember> loanMembers = memberInfo.getLoanMembers();
		if(loanMembers!=null && loanMembers.size()>0){
			LoanMember loanMember = loanMembers.iterator().next();
			jsonObject.put("loanMoney", loanMember.getLendMoney());
			jsonObject.put("overdue", loanMember.getExpectMoney());
			jsonObject.put("residue", loanMember.getResidueMoney());
			jsonObject.put("curMoney", loanMember.getCurMoney());
			jsonObject.put("backMoney", loanMember.getBackMoney());
		}else{
			jsonObject.put("loanMoney", 0);
			jsonObject.put("overdue", 0);
			jsonObject.put("residue", 0);
			jsonObject.put("curMoney", 0);
			jsonObject.put("backMoney", 0);
		}
	}
	
	/**
	 * 封装客户信息（理财客户、借贷客户）
	 * @Title: fillDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月11日 下午3:54:10
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(List<MemberInfo> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (MemberInfo memberInfo : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				//客户的基本信息
				map.put("id", memberInfo.getId());
				map.put("loginName", memberInfo.getLoginName());
				map.put("realName", memberInfo.getRealName());
				map.put("nickname", memberInfo.getNickname());
				map.put("email", memberInfo.getEmail());
				map.put("age", memberInfo.getAge());
				map.put("sex", memberInfo.getSex());
				map.put("phone", memberInfo.getPhone());
				map.put("isDelete", memberInfo.getIsdelete());
				map.put("createTime", DateUtils.getTimeStamp(memberInfo.getCreateTime()));
				map.put("modifyTime", DateUtils.getTimeStamp(memberInfo.getModifyTime()));
				map.put("createuser", memberInfo.getCreateuser());
				map.put("modifyuser", memberInfo.getModifyuser());
				map.put("userImg", memberInfo.getUserImg());
				map.put("isEliteAccount", memberInfo.getIsEliteAccount());
				map.put("level", memberInfo.getLevel());
				map.put("price", memberInfo.getPrice());
				map.put("password", memberInfo.getPassword());
				map.put("payPassword", memberInfo.getPayPassword());
				map.put("handPassword", memberInfo.getHandPassword());
				map.put("isFreeze", memberInfo.getIsFreeze());
				map.put("idCard", memberInfo.getIdCard());
				map.put("miDescribe", memberInfo.getMiDescribe());
				map.put("idCardImg1", memberInfo.getIdCardImg1());
				map.put("idCardImg2", memberInfo.getIdCardImg2());
				map.put("myQr", memberInfo.getMyQr());
				map.put("registerStyle", memberInfo.getRegisterStyle());
				map.put("useableScore", memberInfo.getUseableScore());
				//封装理财用户信息
				Set<InvestMember> investMembers =  memberInfo.getInvestMembers();
				for(InvestMember investMember : investMembers){
					map.put("investMember_id", investMember.getId());
					map.put("isContract", investMember.getIsContract());
					map.put("balance", investMember.getBalance());
					map.put("investing", investMember.getInvesting());
					map.put("totalIncome", investMember.getInvesting());
					map.put("totalMoney", investMember.getTotalMoney());
					map.put("currentIncome", investMember.getCurrentIncome());
				}
				//封装借贷客户信息
				Set<LoanMember> loanMembers =  memberInfo.getLoanMembers();
				for(LoanMember loanMember : loanMembers){
					map.put("loanMember_id", loanMember.getId());
					map.put("backMoney", loanMember.getBackMoney());
					map.put("expectMoney", loanMember.getExpectMoney());
					map.put("lendMoney", loanMember.getLendMoney());
					map.put("residueMoney", loanMember.getResidueMoney());
				}
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装理财用户错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装理财用户错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
}
