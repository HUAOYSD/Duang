package org.duang.action.provider;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.duang.entity.MemberInfo;
import org.duang.entity.Sign;
import org.duang.service.MemberInfoService;
import org.duang.service.SignService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.PageUtil;
import org.duang.util.ReadProperties;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 签到
 * @ClassName:  SignAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年10月21日 上午9:58:51
 */
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_sign")
public class SignAction extends BaseAction<Sign>{

	private String sign_properties = "config/sign.properties"; 
	
	private SignService signService;
	@Resource(name = "signserviceimpl")
	public void setService(SignService signService) {
		this.signService = signService;
	}
	private MemberInfoService sysMemberInfoService;
	@Resource(name="sysmemberinfoserviceimpl")
	public void setService(MemberInfoService sysMemberInfoService) {
		this.sysMemberInfoService = sysMemberInfoService;
	}
	
	/**
	 * 获得近30天的签到记录
	 * @Title: get30Sign   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月21日 下午3:13:26
	 * @return: void      
	 * @throws
	 */
	public void query30Sign(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String memberId="";
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(memberId = MemberCollection.getInstance().getMainField(token))){
				PageUtil<Sign> page = getPageUtil();
				page.setPageRecords(30);
				List<Sign> signList = signService.queryEntity("memberInfo.id", memberId, page, Order.desc("signDate"));
				success = true;
				jsonObject.put("result", fillDataObjectList(signList));
				if (signList == null || signList.size() == 0) {
					msg = "没有查到符合条件的数据";
				}
			}else{
				msg = "用户token为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("SignAction——query30Sign方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("SignAction——query30Sign方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 获得近30天的签到记录
	 * @Title: get30Sign   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月21日 下午3:13:26
	 * @return: void      
	 * @throws
	 */
	public void querySign(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String memberId = null;
			if (DataUtils.notEmpty(token) && DataUtils.notEmpty(memberId = MemberCollection.getInstance().getMainField(token))) {
				int num = DataUtils.str2int(getRequest().getParameter("num"));
				int count = DataUtils.str2int(getRequest().getParameter("count"));
				if (num != 0 && count != 0) {
					List<Sign> signList = signService.queryEntity("memberInfo.id", memberId, setPageUtil(new PageUtil<Sign>(num, count)), Order.desc("signDate"));
					success = true;
					jsonObject.put("result", fillDataObjectList(signList));
					if (signList == null || signList.size() == 0) {
						msg = "没有查到符合条件的数据";
					}
				}else {
					msg = "参数无效";
				}
			}else {
				msg = "登录失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("signAction——querySign方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("signAction——querySign方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 封装对象
	 * @Title: fillDataObjectList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年10月21日 下午3:24:27
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectList(List<Sign> list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (Sign sign : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", sign.getId());
				map.put("totalScore", sign.getMemberInfo().getUseableScore());
				map.put("score", sign.getScore());
				map.put("signDate", DateUtils.date2Str(sign.getSignDate(),"yyyy-MM-dd"));
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装积分错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装积分错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
	
	/**
	 * 添加签到
	 * @Title: addSign   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月21日 上午10:54:29
	 * @return: void      
	 * @throws
	 */
	public void addSign(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String memberId="";
			//连续签到次数
			int signCount = 1;
			//添加的积分数
			int score = 0;
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(memberId = MemberCollection.getInstance().getMainField(token))){
				ReadProperties.initPrperties(sign_properties);
				//获取是否连续签到打开
				String isSerial = ReadProperties.getStringValue("isSerial");
				//获取连续签到所加的积分是多少
				int serialScore = DataUtils.str2int(ReadProperties.getStringValue("serialScore"));
				//获取基本签到的加积分数
				int baseScore = DataUtils.str2int(ReadProperties.getStringValue("baseScore"));
				
				//判断昨天是否签到了
				if(isContinuousSign(memberId)){//签到
					//计算连续第几次签到
					Sign yesterdaySign = getYesterdaySign(memberId);
					signCount = yesterdaySign.getSignCount()+1;
					//判断是否打开了连续签到奖励
					if(isSerial.equals("true")){
						score = serialScore;
					}else {
						score = baseScore;
					}
				}else{ //昨天未签到
					score = baseScore;
				}
				Sign sign = new Sign();
				sign.setId(DataUtils.randomUUID());
				sign.setSignCount(signCount);
				sign.setSignDate(DataUtils.str2Date(DateUtils.getCurrentDate("yyyy-MM-dd")));
				sign.setScore(score);
				MemberInfo memberInfo = new MemberInfo(memberId);
				sign.setMemberInfo(memberInfo);
				success = signService.saveEntity(sign);
				if(success){
					//修改总积分
					sysMemberInfoService.updateMemberInfoScore(memberId, score);
				}else{
					msg = "服务器超时，请稍后重试";
				}
			}else{
				msg = "用户token为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("SignAction——addSign方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("SignAction——addSign方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 判断昨天是否签到了 签到了true,没有签到为false
	 * @Title: isContinuousSign   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberId
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年10月21日 上午10:26:42
	 * @return: boolean      
	 * @throws
	 */
	private boolean isContinuousSign(String memberId) throws Exception{
		boolean result = false;
		if(DataUtils.notEmpty(memberId)){
			if(getYesterdaySign(memberId) != null){
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * 获得昨天的登录人的签到记录
	 * @Title: getYesterdaySign   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberId
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年10月21日 上午10:41:17
	 * @return: Sign      
	 * @throws
	 */
	private Sign getYesterdaySign(String memberId) throws Exception{
		Sign  sign = null;
		if(DataUtils.notEmpty(memberId)){
			//昨天的日期
			String yesterdayStr = getYesterday();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("signDate", DateUtils.str2Date(yesterdayStr));
			map.put("memberInfo.id", memberId);
			sign = signService.findEntity(map);
		}
		return sign;
	}
	
	/**
	 * 获取昨天的日期
	 * @Title: getYesterday   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年10月21日 上午10:24:00
	 * @return: String      
	 * @throws
	 */
	private String getYesterday(){
		Calendar   cal   =   Calendar.getInstance();
		cal.add(Calendar.DATE,   -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}
}
