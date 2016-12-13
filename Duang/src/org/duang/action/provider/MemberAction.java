package org.duang.action.provider;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.CondsUtils;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.InvestList;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanMember;
import org.duang.entity.MemberInfo;
import org.duang.entity.RequestFlow;
import org.duang.entity.SMSVerificationCode;
import org.duang.enums.If;
import org.duang.enums.ResultCode;
import org.duang.enums.UploadFile;
import org.duang.enums.invest.Status;
import org.duang.service.InvestListService;
import org.duang.service.MemberInfoService;
import org.duang.service.RequestFlowService;
import org.duang.service.SMSVerificationCodeService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.ImageString;
import org.duang.util.MD5Utils;
import org.duang.util.ReadProperties;
import org.duang.util.SSLClient;
import org.json.JSONArray;
import org.json.JSONObject;
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
	private InvestListService investListService;
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

	private SMSVerificationCodeService smsVerificationCodeService;
	@Resource
	public void setSmsVerificationCodeService(SMSVerificationCodeService smsVerificationCodeService) {
		this.smsVerificationCodeService = smsVerificationCodeService;
	}

	/**
	 * 忘记密码接口
	 * @Title: forgetLoginPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月8日 上午9:09:37
	 * @return: void      
	 * @throws
	 */
	public void forgetLoginPassword(){
		boolean success = false;
		try {
			String pwd = getRequest().getParameter("pwd");
			String vcode = getRequest().getParameter("vcode");
			String phone = getRequest().getParameter("phone");
			if (DataUtils.notEmpty(phone) && DataUtils.notEmpty(pwd) && DataUtils.notEmpty(vcode)) {
				//校验验证码
				SMSVerificationCode smsVerificationCode = smsVerificationCodeService.findEntity("phone", phone);
				//系统目前时间
				Date curDate = new Date();
				//有效期结束时间
				Date endDate = smsVerificationCode.getEndTime();
				if(endDate.getTime()-curDate.getTime()>=0){
					MemberInfo memberInfo = service.findEntity("phone", DES.decryptDES(phone));
					memberInfo.setPassword(pwd);
					success = service.updateEntity(memberInfo);
				}else{
					msg = "验证码已过期";
				}
			}else{
				msg = "参数不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——forgetLoginPassword方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——forgetLoginPassword方法错误：" + e.getLocalizedMessage(), this.getClass());
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
				String nickName = new String(DES.decryptDES(DESNickname).getBytes(),"utf-8");
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
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,service).getMainField(token))) {
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
	private void fillMemberInfo(MemberInfo memberInfo) throws Exception{
		jsonObject.put("time", DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		jsonObject.put("id", memberInfo.getId());
		jsonObject.put("name", memberInfo.getRealName());

		Set<InvestMember> investMembers = memberInfo.getInvestMembers();
		if(investMembers!=null && investMembers.size()>0){
			InvestMember investMember = investMembers.iterator().next();
			jsonObject.put("money", investMember.getTotalMoney());
			jsonObject.put("investing", investMember.getInvesting());
			jsonObject.put("balance", investMember.getBalance());
			
			
			//我的所有的收益中的理财标
			CondsUtils condsUtils = new CondsUtils();
			condsUtils.addProperties(true, "status", "memberInfo");
			condsUtils.addValues(true, new Object[]{new Object[]{Status.S2.getVal(), Status.S3.getVal()}, "in"}, memberInfo);
//			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Calendar calendar = Calendar.getInstance();
//			Date today = sf.parse(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE)+" 00:30:00");
//			Date date = new Date(today.getTime() + 24L*3600L*1000L);
			List<InvestList> list = investListService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
			double totalEarnings = 0;
			if (DataUtils.notEmpty(list)) {
				for (InvestList obj : list) {
					if (obj==null || obj.getCalcBeginDate() == null) {
						continue;
					}
					if (obj.getCalcBeginDate().getTime()<=System.currentTimeMillis()) {
						totalEarnings += obj.getMoney() * (obj.getScale().getRevenue() + obj.getScale().getRevenueAdd()) / 365D / obj.getDays();
					}
				}
				jsonObject.put("totalEarnings", totalEarnings);
			}else {
				jsonObject.put("currentEarnings", 0);
			}
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
	@Resource
	private RequestFlowService requestFlowService;
	/**
	 * 开户回调
	 * @Title: realNameCertification   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月2日 上午11:38:10
	 * @return: void      
	 * @throws
	 */
	public synchronized void openAccountCallback(){
		try{
			LoggerUtils.info("--------------------------开户回调开始", this.getClass());
			boolean success=false;
			//读取配置文件中
			String requestId = getRequest().getParameter("requestId");

			if (DataUtils.isEmpty(requestId) || requestFlowService.findEntity("requestId", requestId) != null) {
				return;
			}
			Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
			String result = getRequest().getParameter("result");
			String userIdIdentity = getRequest().getParameter("userIdIdentity");
			String name = getRequest().getParameter("name");
			String userId = getRequest().getParameter("userId");
			String payType = getRequest().getParameter("payType");
			String mobileNo = getRequest().getParameter("mobileNo");
			String signature = getRequest().getParameter("signature");

			StringBuffer backStringBuffer = new StringBuffer("---------------------------开户回调  字符串：");
			backStringBuffer.append("----requestId:"+requestId)
			.append("----result:"+result)
			.append("----userIdIdentity:"+userIdIdentity)
			.append("----name:"+name)
			.append("----userId:"+userId)
			.append("----payType:"+payType)
			.append("----mobileNo:"+mobileNo)
			.append("----signature:"+signature);

			LoggerUtils.info(backStringBuffer.toString(), this.getClass());
			if(result.equals(ResultCode.SUCCESS.getVal())){
				success = true;
			}else{
				LoggerUtils.error("----------开户回调 流程号："+requestId+"，原因："+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)),this.getClass());
			}
			if(success){
				//修改用户信息
				MemberInfo memberInfo = service.findEntity("id", userIdIdentity);
				memberInfo.setRealName(name);
				memberInfo.setPayType(payType);
				memberInfo.setUserId(userId);
				service.updateEntity(memberInfo);
			}

			RequestFlow requestFlow = new RequestFlow(DataUtils.randomUUID(), requestId, userIdIdentity, new Date());
			requestFlowService.saveEntity(requestFlow);

		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("MemberAction openAccountCallback：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction openAccountCallback：" + e.getLocalizedMessage(), this.getClass());
		}

	}

	/**
	 * 实名认证结果
	 * @Title: realNameCertification   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月2日 上午11:38:10
	 * @return: void      
	 * @throws
	 */
	public synchronized void realNameAuthCallback(){
		try{
			boolean success=false;
			//读取配置文件中
			String requestId = getRequest().getParameter("requestId");
			if (DataUtils.isEmpty(requestId) || requestFlowService.findEntity("requestId", requestId) != null) {
				return;
			}
			Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
			String result = getRequest().getParameter("result");
			String status = getRequest().getParameter("status");
			String userName = getRequest().getParameter("userName");
			String idNumber = getRequest().getParameter("idNumber");
			String payType = getRequest().getParameter("payType");
			String merBizRequestId = getRequest().getParameter("merBizRequestId");
			String signature = getRequest().getParameter("signature");

			StringBuffer backStringBuffer = new StringBuffer("---------------------------实名认证回调  字符串：");
			backStringBuffer.append("----requestId:"+requestId)
			.append("----result:"+result)
			.append("----status:"+status)
			.append("----userName:"+userName)
			.append("----idNumber:"+idNumber)
			.append("----payType:"+payType)
			.append("----merBizRequestId:"+merBizRequestId)
			.append("----signature:"+signature);

			LoggerUtils.info(backStringBuffer.toString(), this.getClass());

			StringBuffer signatureStr = new StringBuffer();
			signatureStr.append(requestId);
			signatureStr.append(merBizRequestId);
			signatureStr.append(result);
			signatureStr.append(status);
			signatureStr.append(userName);
			signatureStr.append(idNumber);
			signatureStr.append(payType);
			//获取返回数据的加密数据用于与签名校验
			String dataSign = MD5Utils.hmacSign(signatureStr.toString(), ReadProperties.getStringValue(properties, "akey"));
			LoggerUtils.info("----------实名认证回调 本地加密签名"+dataSign, this.getClass());
			if(signature.equals(dataSign)){
				//请求成功
				if(result.equals(ResultCode.SUCCESS.getVal())){
					//0表示身份证与姓名一致
					if(status.equals(String.valueOf(If.If0.getVal()))){
						success = true;
					}else{ //不一致
						LoggerUtils.error("----------实名认证回调  流程号："+requestId+",原因"+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)),this.getClass());
					}
				}else if(result.equals(ResultCode.Doing.getVal())){
					success = queryRealNameAuth(properties,userName,idNumber);
				}else{
					LoggerUtils.error("----------实名认证回调 流程号："+requestId+"，原因："+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)),this.getClass());
				}
			}else {
				//签名不匹配
				LoggerUtils.error("----------实名认证回调   流程号："+requestId+" ，原因："+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, result)),this.getClass());
			}

			if(success){
				realNameAuthMemberInfo(merBizRequestId,userName,idNumber,payType);
			}
			RequestFlow requestFlow = new RequestFlow(DataUtils.randomUUID(), requestId, null, new Date());
			requestFlowService.saveEntity(requestFlow);

		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("MemberAction realNameAuthCallback：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction realNameAuthCallback：" + e.getLocalizedMessage(), this.getClass());
		}

	}

	/**
	 * 实名认证更新数据库
	 * @Title: realNameAuthMemberInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param requestId
	 * @param: @param userName
	 * @param: @param idNumber
	 * @param: @param payType
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月3日 下午2:33:54
	 * @return: void      
	 * @throws
	 */
	private void realNameAuthMemberInfo(String merBizRequestId,String userName,String idNumber,String payType) throws Exception{
		//身份证与姓名一致,进行修改用户表
		MemberInfo memberInfo = service.findEntity("requestId", merBizRequestId);
		memberInfo.setIdCard(idNumber);
		memberInfo.setPayType(payType);
		memberInfo.setIsAuth(If.If1.getVal());
		service.updateEntity(memberInfo);
	}

	/**
	 * 判断是否实名认证
	 * @Title: queryRealNameAuth   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param properties
	 * @param: @param userName
	 * @param: @param idNumber
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月3日 下午2:33:10
	 * @return: boolean      
	 * @throws
	 */
	public boolean  queryRealNameAuth(Properties properties,String userName,String idNumber) throws Exception{
		boolean success = false;
		//请求受理成功，正在处理，需要主动查询
		String urlStr = ReadProperties.getStringValue(properties, "realNameAuthURL");
		//商户号
		String merchantCode = ReadProperties.getStringValue(properties, "merchantCode");
		//秘钥
		String akey = ReadProperties.getStringValue(properties, "akey");
		//生成一个流水号
		String self_requestId = DataUtils.randomUUID();
		//起始时间和截止时间
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -29);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
		String sDate = sdf.format(dBefore);    //格式化前一天
		String eDate = sdf.format(dNow); //格式化当前时间

		//数字签名字符串
		StringBuffer signatureBuffer = new StringBuffer();
		signatureBuffer.append(self_requestId+merchantCode+userName+idNumber+1+sDate+eDate);
		//加密后的数字签名
		String signature_sign=MD5Utils.hmacSign(signatureBuffer.toString(), akey);
		//封装map参数
		Map<String,String> map = new HashMap<String, String>();
		map.put("requestId",self_requestId);
		map.put("merchantCode",merchantCode);
		map.put("userName",userName);
		map.put("idNumber",idNumber);
		map.put("pageNo","1");
		map.put("sDate",DateUtils.getCurrentDate("yyyyMMdd"));
		map.put("eDate",DateUtils.getCurrentDate("yyyyMMdd"));
		map.put("signature",signature_sign);
		//获取转换的参数
		JSONObject jsonObject = SSLClient.getJsonObjectByUrl(urlStr,map,"GBK");
		//result 查询结果  00000代表成功
		String resultCallbace = jsonObject.get("result").toString();
		if(resultCallbace.equals(ResultCode.SUCCESS)){
			if(DataUtils.notEmpty(jsonObject.get("authList").toString())){
				JSONArray jsonArray = (JSONArray)jsonObject.get("authList");
				//如果length>0说明查出来人员，否则未查出用户
				if(jsonArray.length()>0){
					//有可能存在多个，获取第一个就可以了
					JSONObject authListObject = (JSONObject)jsonArray.get(0);
					String authResult = authListObject.get("authResult").toString();
					//authResult 等于0表示认证成功，1表示认证失败
					if(authResult.equals(String.valueOf(If.If0.getVal()))){
						success = true;
					}else{
						LoggerUtils.error("流程号："+self_requestId+"------"+"判断是否实名认证失败,原因："+authListObject.get("failReason").toString()+",认证时间："+authListObject.get("authTime").toString(),this.getClass());
					}
				}else{
					LoggerUtils.error("流程号："+self_requestId+"------"+"判断是否实名认证失败,原因：未查出用户信息",this.getClass());
				}
			}
		}else{
			LoggerUtils.error("流程号："+self_requestId+"------"+"判断是否实名认证失败,原因："+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, resultCallbace)),this.getClass());
		}
		return success;
	}

	/**
	 * 查询用户的账户信息
	 * @Title: queryMemberAccount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @author LiYonghui    
	 * @date 2016年11月5日 下午2:33:10
	 * @throws
	 */
	public void queryMemberAccount() {
		boolean success = false;
		try {
			String id = getRequest().getParameter("id");
			if (DataUtils.notEmpty(id)) {
				MemberInfo memberInfo = service.findById(id);
				if (memberInfo != null) {
					if(DataUtils.notEmpty(memberInfo.getRealName())&& DataUtils.notEmpty(memberInfo.getIdCard())){
						Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
						jsonObject = queryMemberAccount(properties,memberInfo.getId(),memberInfo.getRealName(),memberInfo.getIdCard());
						success=true;
					}else{
						jsonObject.put("balance", 0);
						jsonObject.put("withdrawAbleBalance", 0);
						jsonObject.put("frozenBalance", 0);
						success=true;
					}
				}else {
					jsonObject.put("msg", "未查到记录");
				}
			}else{
				jsonObject.put("msg", "数据丢失，请重新登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——queryMemberAccount方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——queryMemberAccount方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		} finally {
			jsonObject.put("success", success);
			printJsonResult();
		}
	}

	/**
	 * 查询用户的账户信息
	 * @Title: queryMemberAccount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: properties  读取文件信息
	 * @param: userIdIdentity  id
	 * @param: userName  姓名
	 * @param: idNumber  身份证信息
	 * @author LiYonghui    
	 * @date 2016年11月5日 下午2:33:10
	 * @throws
	 */
	private net.sf.json.JSONObject queryMemberAccount(Properties properties,String userIdIdentity, String userName,String idNumber) throws Exception{
		//请求受理成功，正在处理，需要主动查询
		String urlStr = ReadProperties.getStringValue(properties, "queryMemberAccountURL");
		//商户号
		String merchantCode = ReadProperties.getStringValue(properties, "merchantCode");
		//秘钥
		String akey = ReadProperties.getStringValue(properties, "akey");
		//生成一个流水号
		String requestId = DataUtils.randomUUID();

		//数字签名字符串
		StringBuffer signatureBuffer = new StringBuffer();
		signatureBuffer.append(requestId);
		signatureBuffer.append(merchantCode);
		signatureBuffer.append(userIdIdentity);
		signatureBuffer.append(userName);
		signatureBuffer.append(idNumber);

		StringBuffer sendStringBuffer = new StringBuffer("\t\n---------------------------查询账户信息  send2FF的字符串：");
		sendStringBuffer.append("\t\n----requestId:"+requestId)
		.append("\t\n----merchantCode:"+merchantCode)
		.append("\t\n----akey:"+akey)
		.append("\t\n----userIdIdentity:"+userIdIdentity)
		.append("\t\n----userName:"+userName)
		.append("\t\n----idNumber:"+idNumber)
		.append("\t\n----signature:"+signatureBuffer.toString());
		LoggerUtils.info(sendStringBuffer.toString(), this.getClass());
		//加密后的数字签名
		String signature_sign=MD5Utils.hmacSign(signatureBuffer.toString(), akey);
		LoggerUtils.info("-------------查询账户信息  加密后的数字签名：signature_sign:"+signature_sign,this.getClass());
		//封装map参数
		Map<String,String> map = new HashMap<String, String>();
		map.put("requestId",requestId);
		map.put("merchantCode",merchantCode);
		map.put("userIdIdentity",userIdIdentity);
		map.put("userName",userName);
		map.put("idNumber",idNumber);
		map.put("signature",signature_sign);
		//获取转换的参数
		JSONObject jsonObjectData = SSLClient.getJsonObjectByUrl(urlStr,map,"GBK");
		//result 查询结果  00000代表成功
		String resultCallbace = jsonObjectData.get("result").toString();
		if(resultCallbace.equals(ResultCode.SUCCESS.getVal())){
			String back_userIdIdentity = jsonObjectData.get("userIdIdentity").toString();
			String back_userName = jsonObjectData.get("userName").toString();
			String back_idNumber = jsonObjectData.get("idNumber").toString();
			String back_result = jsonObjectData.get("result").toString();
			String back_balance = jsonObjectData.get("balance").toString();
			if(!DataUtils.notEmpty(back_balance) && !back_balance.equals("null")){
				back_balance="0";
			}
			String back_withdrawAbleBalance = jsonObjectData.get("withdrawAbleBalance").toString();
			if(!DataUtils.notEmpty(back_withdrawAbleBalance) && !back_withdrawAbleBalance.equals("null")){
				back_withdrawAbleBalance="0";
			}
			String back_frozenBalance = jsonObjectData.get("frozenBalance").toString();
			if(!DataUtils.notEmpty(back_frozenBalance) && !back_frozenBalance.equals("null")){
				back_frozenBalance="0";
			}
			String back_signature = jsonObjectData.get("signature").toString();

			StringBuffer backDataStringBuffer = new StringBuffer("\t\n---------------------------查询账户信息  BackData的字符串：");
			backDataStringBuffer.append("\t\n----back_userIdIdentity:"+back_userIdIdentity)
			.append("\t\n----back_userName:"+back_userName)
			.append("\t\n----back_idNumber:"+back_idNumber)
			.append("\t\n----back_result:"+back_result)
			.append("\t\n----back_balance:"+back_balance)
			.append("\t\n----back_withdrawAbleBalance:"+back_withdrawAbleBalance)
			.append("\t\n----back_frozenBalance:"+back_frozenBalance)
			.append("\t\n----back_signature:"+back_signature);
			LoggerUtils.info(backDataStringBuffer.toString(), this.getClass());


			StringBuffer back_signatureBuffer = new StringBuffer(back_userIdIdentity+back_userName+back_idNumber+
					back_result+back_balance+back_withdrawAbleBalance+back_frozenBalance);
			String back_signature_sign = MD5Utils.hmacSign(back_signatureBuffer.toString(), akey);
			LoggerUtils.info("\t\n-------------查询账户信息   返回的参数信息-加密签名:"+back_signature_sign.toString(),this.getClass());

			if(back_signature_sign.equals(back_signature)){
				jsonObject.put("userIdIdentity", back_userIdIdentity);
				jsonObject.put("userName", back_userName);
				jsonObject.put("idNumber", back_idNumber);
				jsonObject.put("mobileNo", jsonObjectData.get("mobileNo"));
				jsonObject.put("email", jsonObjectData.get("email"));
				jsonObject.put("result", true);
				jsonObject.put("balance", back_balance);
				jsonObject.put("withdrawAbleBalance", back_withdrawAbleBalance);
				jsonObject.put("frozenBalance", back_frozenBalance);
				jsonObject.put("signature", back_signature);
			}else{
				jsonObject.put("result", false);
				jsonObject.put("msg", "签名不一致");
			}
		}else{
			jsonObject.put("result", false);
			jsonObject.put("msg", DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, resultCallbace)));
			LoggerUtils.error("流程号："+requestId+"------查询账户信息 失败,原因 错误码"+resultCallbace+"===="+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, resultCallbace)),this.getClass());
		}
		return jsonObject;
	}

	/**
	 * 查询用户的银行卡信息
	 * @Title: queryMemberAccount   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return
	 * @author LiYonghui    
	 * @date 2016年11月5日 下午2:33:10
	 * @throws
	 */
	public void queryMemberSignBankCard() {
		boolean success = false;
		try {
			String id = getRequest().getParameter("id");
			String queryType = getRequest().getParameter("queryType");
			if (DataUtils.notEmpty(id)) {
				Properties properties = ReadProperties.initPrperties("sumapayURL.properties");
				jsonObject = queryMemberSignBankCard(properties,id,queryType);
				if(jsonObject.get("result").equals(ResultCode.SUCCESS.getVal())){
					success=true;
				}
			}else{
				msg="数据丢失，请重新登录";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——queryMemberAccount方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——queryMemberAccount方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		} finally {
			jsonObject.put("msg", msg);
			jsonObject.put("success", success);
			printJsonResult();
		}
	}

	/**
	 * 查询用户的账户绑定银行卡信息
	 * @Title: queryMemberSignBankCard   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: properties  读取文件信息 
	 * @param: userIdIdentity  id
	 * @param: queryType  查询类型 0：全部    1：用户提现    2：一键充值    3：协议还款
	 * @author LiYonghui    
	 * @date 2016年11月5日 下午2:33:10
	 * @throws
	 */
	private net.sf.json.JSONObject queryMemberSignBankCard(Properties properties,String userIdIdentity, String queryType) throws Exception{
		//请求受理成功，正在处理，需要主动查询
		String urlStr = ReadProperties.getStringValue(properties, "queryMemberSignBankCardURL");
		//商户号
		String merchantCode = ReadProperties.getStringValue(properties, "merchantCode");
		//秘钥
		String akey = ReadProperties.getStringValue(properties, "akey");
		//生成一个流水号
		String requestId = DataUtils.randomUUID();
		//数字签名字符串
		StringBuffer signatureBuffer = new StringBuffer();
		signatureBuffer.append(requestId+merchantCode+userIdIdentity+queryType);
		LoggerUtils.info("------------查询银行卡信息 数字签名字符串："+signatureBuffer.toString(), this.getClass());
		//加密后的数字签名
		String signature_sign=MD5Utils.hmacSign(signatureBuffer.toString(), akey);
		LoggerUtils.info("------------查询银行卡信息 签名加密："+signature_sign.toString(), this.getClass());
		//封装map参数
		Map<String,String> map = new HashMap<String, String>();
		map.put("requestId",requestId);
		map.put("merchantCode",merchantCode);
		map.put("userIdIdentity",userIdIdentity);
		map.put("queryType",queryType);
		map.put("signature",signature_sign);

		StringBuffer sendDataStringBuffer = new StringBuffer("---------------------------查询账户绑定的银行卡  SendFF的字符串：");
		sendDataStringBuffer.append("----requestId:"+requestId)
		.append("----merchantCode:"+merchantCode)
		.append("----userIdIdentity:"+userIdIdentity)
		.append("----queryType:"+queryType)
		.append("----signature:"+signature_sign);
		LoggerUtils.info(sendDataStringBuffer.toString(), this.getClass());

		//获取转换的参数
		JSONObject jsonObjectData = SSLClient.getJsonObjectByUrl(urlStr,map,"GBK");
		//result 查询结果  00000代表成功
		String resultCallbace = jsonObjectData.get("result").toString();
		if(resultCallbace.equals(ResultCode.SUCCESS.getVal())){
			String back_requestId = jsonObjectData.get("requestId").toString();
			String back_userIdIdentity = jsonObjectData.get("userIdIdentity").toString();
			String back_result = jsonObjectData.get("result").toString();
			String back_signature = jsonObjectData.get("signature").toString();
			//本地签名拼接
			StringBuffer back_signatureBuffer = new StringBuffer(back_requestId+back_result+back_userIdIdentity);
			String back_signature_sign = MD5Utils.hmacSign(back_signatureBuffer.toString(), akey);

			if(back_signature_sign.equals(back_signature)){
				jsonObject.put("userIdIdentity", back_userIdIdentity);
				jsonObject.put("result", back_result);
				jsonObject.put("signature", back_signature);
				//用户提现卡列表
				if(queryType.equals("0") || queryType.equals("1")){
					if(DataUtils.notEmpty(jsonObjectData.get("withdrawBankList").toString())){
						JSONArray withdrawBankJSONArray = jsonObjectData.getJSONArray("withdrawBankList");
						if(withdrawBankJSONArray != null){
							List<Map<String,String>> withdrawBankList = new ArrayList<Map<String,String>>();
							for(int i=0;i<withdrawBankJSONArray.length();i++){
								Map<String,String> withdrawBank = new HashMap<String, String>();
								JSONObject withdrawBankObject = (JSONObject)withdrawBankJSONArray.get(i);
								withdrawBank.put("bankName", withdrawBankObject.getString("bankName"));
								withdrawBank.put("bankAccount", withdrawBankObject.getString("bankAccount"));
								withdrawBank.put("bindId", withdrawBankObject.getString("bindId"));
								withdrawBankList.add(withdrawBank);
							}
							jsonObject.put("withdrawBankList", withdrawBankList);
						}
					}
				}
				//一键充值卡列表
				else if(queryType.equals("0") || queryType.equals("2")){
					if(DataUtils.notEmpty(jsonObjectData.get("rechargeProtocolList").toString())){
						JSONArray rechargeProtocolJSONArray = jsonObjectData.getJSONArray("rechargeProtocolList");
						if(rechargeProtocolJSONArray != null){
							List<Map<String,String>> rechargeProtocolList = new ArrayList<Map<String,String>>();
							for(int i=0;i<rechargeProtocolJSONArray.length();i++){
								Map<String,String> rechargeProtocol = new HashMap<String, String>();
								JSONObject rechargeProtocolObject = (JSONObject)rechargeProtocolJSONArray.get(i);
								rechargeProtocol.put("bankName", rechargeProtocolObject.getString("bankName"));
								rechargeProtocol.put("bankAccount", rechargeProtocolObject.getString("bankAccount"));
								rechargeProtocol.put("protocolNo", rechargeProtocolObject.getString("protocolNo"));
								rechargeProtocolList.add(rechargeProtocol);
							}
							jsonObject.put("rechargeProtocolList", rechargeProtocolList);
						}
					}
				}
				//协议还款卡列表
				else if(queryType.equals("0") || queryType.equals("3")){
					if(DataUtils.notEmpty(jsonObjectData.get("repayProtocolList").toString())){
						JSONArray repayProtocolJSONArray = jsonObjectData.getJSONArray("repayProtocolList");
						if(repayProtocolJSONArray != null){
							List<Map<String,String>> repayProtocolList = new ArrayList<Map<String,String>>();
							for(int i=0;i<repayProtocolJSONArray.length();i++){
								Map<String,String> repayProtocol = new HashMap<String, String>();
								JSONObject repayProtocolObject = (JSONObject)repayProtocolJSONArray.get(i);
								repayProtocol.put("bankName", repayProtocolObject.getString("bankName"));
								repayProtocol.put("bankAccount", repayProtocolObject.getString("bankAccount"));
								repayProtocol.put("protocolNo", repayProtocolObject.getString("protocolNo"));
								repayProtocolList.add(repayProtocol);
							}
							jsonObject.put("repayProtocolList", repayProtocolList);
						}
					}
				}

				StringBuffer backDataStringBuffer = new StringBuffer("---------------------------查询账户绑定的银行卡  BackData的字符串：");
				backDataStringBuffer.append("----back_userIdIdentity:"+back_userIdIdentity)
				.append("----back_requestId:"+back_requestId)
				.append("----back_result:"+back_result)
				.append("----back_signature:"+back_signature);
				LoggerUtils.info(backDataStringBuffer.toString(), this.getClass());
				LoggerUtils.info("----------------------查询账户绑定的银行卡   本地加密签名"+back_signature_sign,this.getClass());

			}else{
				jsonObject.put("result", false);
				jsonObject.put("msg", "签名不一致");
			}
		}else{
			jsonObject.put("result", false);
			jsonObject.put("msg", DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, resultCallbace)));
			LoggerUtils.error("流程号："+requestId+"------"+"查询用户绑定的银行卡  结果失败,原因："+DataUtils.ISO2UTF8(ReadProperties.getStringValue(properties, resultCallbace)),this.getClass());
		}
		return jsonObject;
	}


	/**
	 * 上传头像
	 */
	public void uploadUseImg(){
		boolean success=false;
		try{
			String token = getRequest().getParameter("token");
			String imgdata = getRequest().getParameter("imgdata");
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(imgdata)) {
				String id = MemberCollection.getInstance(token, service).getMainField(token);
				if (DataUtils.notEmpty(id)) {
					MemberInfo memberInfo = service.findById(id);
					//基本根路径
					String temPath = getRequest().getSession().getServletContext().getRealPath("/");
					//详细路径
					String suffPath = UploadFile.PATH.getVal(UploadFile.HEAD.getVal(memberInfo.getId()))+"\\";
					//文件名称
					String fileName = DataUtils.randomUUID()+".jpg";
					String fullpath = DataUtils.fileUploadPath(temPath, suffPath, fileName);
					jsonObject.put("path", UploadFile.HEAD.appPath()+memberInfo.getId()+"/head/"+fileName);
					success = ImageString.generateImageBase64(imgdata, fullpath);
					//备份
					String backupPath = ReadProperties.getStringValue(ReadProperties.initPrperties("backupdb.properties"), "fileBasicPath");
					ImageString.generateImageBase64(imgdata, DataUtils.fileUploadPath(backupPath, suffPath, fileName));
					
					LoggerUtils.info("userId:"+id+"----------------上传头像："+success, this.getClass());
					if(success){
						memberInfo.setUserImg(fileName);
						success = service.updateEntity(memberInfo);
					}
				}else{
					msg = "登录失效";
				}
			}else{
				msg = "缺少参数,请补充";
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("MemberAction uploadUseImg：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction uploadUseImg：" + e.getLocalizedMessage(), this.getClass());
		}    
		jsonObject.put("success", success);
		jsonObject.put("success", msg);
		printJsonResult();
	}
	
	/**
	 * 上传身份证照
	 * @Title: uploadUseIDcard   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年12月7日 上午9:28:19
	 * @return: void      
	 * @throws
	 */
	public void uploadUserIDcardImg(){
		boolean success=false;
		try{
			String token = getRequest().getParameter("token");
			if (DataUtils.notEmpty(token)) {
				String memberInfoId = MemberCollection.getInstance(token, service).getMainField(token);
				//跟路径
				String temPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
				//详细路径
				String suffPath = UploadFile.PATH.getVal(UploadFile.IDCARD.getVal(memberInfoId))+"\\";
				//备份跟路径
				String backupPath = ReadProperties.getStringValue(ReadProperties.initPrperties("backupdb.properties"), "fileBasicPath");
				// 遍历图片数量
				ArrayList<String> imgDataList = new ArrayList<String>();
				imgDataList.add(entity.getImg_1());
				imgDataList.add(entity.getImg_2());
				imgDataList.add(entity.getImg_3());
				imgDataList.add(entity.getImg_4());
				imgDataList.add(entity.getImg_5());
				imgDataList.add(entity.getImg_6());
				imgDataList.add(entity.getImg_7());
				imgDataList.add(entity.getImg_8());
				imgDataList.add(entity.getImg_9());
				//拼接资料文件名字
				StringBuffer idcardData = new StringBuffer("");
				for (int i = 0; i < entity.getNum(); i++) {
					//文件名称
					String fileName = DataUtils.randomUUID()+".jpg";
					String fullpath = DataUtils.fileUploadPath(temPath, suffPath, fileName);
					success = ImageString.generateImage(imgDataList.get(i), fullpath);
					//备份路径
					ImageString.generateImage(imgDataList.get(i), DataUtils.fileUploadPath(backupPath, suffPath, fileName));
					LoggerUtils.info("userId:" + memberInfoId + "----------------上传身份证照片：" + success, this.getClass());
					if (success) {
						idcardData.append(fileName);
						if(i<entity.getNum()-1){
							idcardData.append(";");
						}
					}
				}
				MemberInfo memberInfo = service.findById(memberInfoId);
				memberInfo.setIdCardImg1(memberInfo.getIdCardImg1()+";"+idcardData);
				success = service.updateEntity(memberInfo);
			}else{
				msg = "缺少参数,请补充";
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("MemberAction uploadUserIDcardImg：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction uploadUserIDcardImg：" + e.getLocalizedMessage(), this.getClass());
		}    
		jsonObject.put("success", success);
		jsonObject.put("success", msg);
		printJsonResult();
	}
	
}
