package org.duang.action.provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.Award;
import org.duang.entity.AwardActivity;
import org.duang.entity.AwardActivityLevel;
import org.duang.entity.MemberAward;
import org.duang.entity.MemberInfo;
import org.duang.enums.If;
import org.duang.service.AwardActivityLevelService;
import org.duang.service.AwardActivityService;
import org.duang.service.MemberAwardService;
import org.duang.service.MemberInfoService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————抽奖Action
 * @ClassName:  AwardAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年11月14日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_award")
public class AwardAction extends BaseAction<Award>{

	private AwardActivityService awardActivityService;
	@Resource
	public void setAwardActivityService(AwardActivityService awardActivityService) {
		this.awardActivityService = awardActivityService;
	}
	
	private AwardActivityLevelService awardActivityLevelService;
	@Resource
	public void setAwardActivityLevleService(AwardActivityLevelService awardActivityLevelService) {
		this.awardActivityLevelService = awardActivityLevelService;
	}
	
	private MemberAwardService memberAwardService;
	@Resource
	public void setMemberAwardService(MemberAwardService memberAwardService) {
		this.memberAwardService = memberAwardService;
	}
	
	private MemberInfoService memberInfoService;
	@Resource
	public void setMemberInfoService(MemberInfoService memberInfoService) {
		this.memberInfoService = memberInfoService;
	}
	
	/**
	 * 体验金抽奖
	 * @Title: checkAwardToExpMoney   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月14日 下午5:49:12
	 * @return: void      
	 * @throws
	 */
	public void checkAwardToExpMoney(){
		boolean success = false;
		try {
			String memberInfo_id = getRequest().getParameter("memberId");
			String activityCode = getRequest().getParameter("activityCode");
			if(DataUtils.isEmpty(memberInfo_id) || DataUtils.isEmpty(activityCode)){
				msg = "缺少参数";
			}else {
				
				int lotsNum = -1;
				String awardActivityLevelId = "";
				//查询体验金活动
				AwardActivity awardActivity = awardActivityService.findEntity("code", activityCode);
				//减去积分
				MemberInfo memberInfo = memberInfoService.findById(memberInfo_id);
				Date enDate = awardActivity.getEndTime();
				//判断活动日期范围
				if(DateUtils.getTimeStamp(enDate) > DateUtils.getTimeStamp(new Date())){
					//判断可用积分是否大于抽奖中扣除的积分
					if(memberInfo.getUseableScore()>=awardActivity.getUseScore()){
						/**
						 * 判断抽奖次数，是否为可重复，0:不重复，1可重复抽奖
						 * 如果为0，则判断目前用户的抽奖次数
						 * 如果为1，则判断可重复的次数是否为0（无限次），否则判断目前用户的抽奖次数
						 */
						if(isDrawLots(awardActivity.getIsRepeat(),awardActivity.getRepeatNum(),memberInfo_id)){
							//更新活动的抽奖次数
							awardActivityService.executeSql("UPDATE award_activity SET nowNumber = "+(awardActivity.getNowNumber()+1)+" WHERE id = '"+awardActivity.getId()+"';");
							//用户的积分减少
							memberInfo.setUseableScore(memberInfo.getUseableScore()-awardActivity.getUseScore());
							memberInfoService.updateEntity(memberInfo);
							//根据活动获取抽奖级别
							List<AwardActivityLevel> awardActivityLevelList = awardActivityLevelService.queryEntity("awardAtivity.id", awardActivity.getId(),null,null);
							for(AwardActivityLevel awardActivityLevel : awardActivityLevelList){
								awardActivityLevelId = awardActivityLevel.getId();
								//中奖数字
						    	List<String> winCodes = Arrays.asList(awardActivityLevel.getWinCode().split(","));
						    	//判断是否是指定中奖用户
								if(DataUtils.notEmpty(awardActivityLevel.getUserId()) && memberInfo_id.equals(awardActivityLevel.getUserId())){
									msg="恭喜您！抽中"+awardActivityLevel.getOnceNum()+"个"+awardActivityLevel.getAward().getName();
									lotsNum = DataUtils.str2int(winCodes.get(0));
								}else{
									//奖品数量
									int awardNum = awardActivityLevel.getAwardNum();
									//中奖率
									int odds = awardActivityLevel.getOdds();
									//生成一个awardNum*odds以内的随机正整数，就是用户的抽奖数字
									Random rand =new Random();
							    	int randomNum = rand.nextInt(awardNum*odds);
							    	//比较是否和中奖数字中吻合
							    	Iterator<String> winCodeIter = winCodes.iterator(); 
							    	lotsNum = randomNum;
							    	while(winCodeIter.hasNext()){  
							    	    String winCode = winCodeIter.next();  
							    	    if(randomNum==DataUtils.str2int(winCode)){
							    	    	success=true;
							    	    	msg="恭喜您！抽中"+awardActivityLevel.getOnceNum()+"个"+awardActivityLevel.getAward().getName();
							    	    	break;
							    		}
							    	}
							    	if(success){
							    		break;
							    	}
								}
							}
					    	if(!success){
					    		success=true;
					    		msg="本次未抽中，继续加油哦";
					    	}
							jsonObject.put("lotsNum", lotsNum);
							jsonObject.put("awardActivityLevelId", awardActivityLevelId);
			    	    	jsonObject.put("awardActivityId", awardActivity.getId());
							
						}else{
							msg="抽奖机会已经用完";
						}
					}else{
						msg="积分不足";
					}
				}else{
					msg="活动已过期";
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("AwardAction——checkAwardToExpMoney方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("AwardAction——checkAwardToExpMoney方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 获取用户的抽奖次数
	 * @Title: getAwardDrawLotsCountById   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param memberId
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月15日 下午2:12:04
	 * @return: int      
	 * @throws
	 */
	private int getAwardDrawLotsCountById(String memberId) throws Exception{
		return memberAwardService.count("memberinfoId", memberId);
	}
	
	/**
	 * 判断用户是否还有抽奖的机会
	 *  判断抽奖次数，是否为可重复，0:不重复，1可重复抽奖
	 *  如果为0，则判断目前用户的抽奖次数
	 *  如果为1，则判断可重复的次数是否为0（无限次），否则判断目前用户的抽奖次数
	 * @Title: isDrawLots   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param repeat  是否可重复
	 * @param: @param repeatNum 重复次数 0代表无限次
	 * @param: @param memberId  用户id
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月15日 下午2:22:42
	 * @return: boolean      
	 * @throws
	 */
	private boolean isDrawLots(int repeat,int repeatNum,String memberId) throws Exception{
		int lotsCount = getAwardDrawLotsCountById(memberId);
		if(repeat==If.If0.getVal() && lotsCount<1){
			return true;
		}else if(repeat==If.If1.getVal() && repeatNum==If.If0.getVal()){
			return true;
		}else if(repeat==If.If1.getVal() && repeatNum!=If.If0.getVal() && lotsCount<repeatNum){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 抽奖刮开信息回调
	 * @Title: openAwardCallback   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年11月15日 下午3:16:33
	 * @return: void      
	 * @throws
	 */
	public void openAwardCallback(){
		try{
			String awardActivityLevelId = getRequest().getParameter("awardActivityLevelId");
			String awardActivityId = getRequest().getParameter("awardActivityId");
			String winCode = getRequest().getParameter("lotsNum");
			String memberId = getRequest().getParameter("memberId");
			String success = getRequest().getParameter("success");
			if(DataUtils.notEmpty(awardActivityLevelId)&&DataUtils.notEmpty(winCode)&&DataUtils.notEmpty(memberId)&&DataUtils.notEmpty(awardActivityId)){
				//根据活动获取抽奖级别
				AwardActivityLevel awardActivityLevel = awardActivityLevelService.findEntity("id", awardActivityLevelId);
				AwardActivity awardActivity = awardActivityService.findById(awardActivityId);
		    	ArrayList<String> winCodes = arrayToList(awardActivityLevel.getWinCode().split(","));
		    	Iterator<String> winCodeIters = winCodes.iterator();  
		    	if(success.equals("true")){
		    		//判断是否真的中奖
		    		boolean isTrue = false;
			    	while(winCodeIters.hasNext()){  
			    	    String winCodeIter = winCodeIters.next();  
			    	    if(DataUtils.str2int(winCode)==DataUtils.str2int(winCodeIter)){
			    	    	isTrue =true;
			    	    	//如果中奖，说明中奖，1.删除中奖的数字，2.修改中奖次数（活动的中奖次数和中奖级别的中奖次数）3.修改抽奖号码（去掉已经中奖的号码）
			    	    	winCodeIters.remove();
			    	    	awardActivityLevel.setWinNumber(awardActivityLevel.getWinNumber()+1);
			    	    	awardActivityLevel.setWinCode(ListToString(winCodes));
			    	    	awardActivityLevelService.updateEntity(awardActivityLevel);
			    	    	//修改活动中的中奖个数
							awardActivityService.executeSql("UPDATE award_activity SET winNumber = "+(awardActivity.getWinNumber()+1)+
									" WHERE id = '"+awardActivity.getId()+"';");
			    	    	//保存用户中奖记录
			    	    	MemberAward memberAward = new MemberAward(DataUtils.randomUUID(), memberId, 
			    	    			"用积分抽奖，中奖体验金，中奖", awardActivityLevel.getAward().getId(), 
			    	    			awardActivity.getUseScore(), new Date(),String.valueOf(If.If1.getVal()),winCode,awardActivityLevel.getOnceNum());
			    	    	memberAwardService.saveEntity(memberAward);
			    	    	break;
			    		}
			    	} 
			    	if(!isTrue){
			    		//保存用户中奖记录
		    	    	MemberAward memberAward = new MemberAward(DataUtils.randomUUID(), memberId, 
		    	    			"用积分抽奖，中奖体验金，未中奖", null, awardActivity.getUseScore(), new Date(),String.valueOf(If.If0.getVal()),winCode,awardActivityLevel.getOnceNum());
		    	    	memberAwardService.saveEntity(memberAward);
			    		
			    	}
		    	}else {
		    		LoggerUtils.warn("\t\n-----------memberId:"+memberId+",抽奖号码："+winCode+"没有中奖，回调的参数有误",this.getClass());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			LoggerUtils.error("AwardAction——openAwardCallback方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("AwardAction——openAwardCallback方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
	}
	
	/**
	 * 将数组，转换为List对象
	 * @Title: arrayToList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param array
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年11月15日 下午3:45:15
	 * @return: ArrayList<String>      
	 * @throws
	 */
	private ArrayList<String> arrayToList(String[] array){
		ArrayList<String> list = new ArrayList<String>();
		for(String num : array){
			list.add(num);
		}
		return list;
	}
	
	private String ListToString(ArrayList<String> list){
		StringBuffer stringBuffer = new StringBuffer();
		int i=0;
		for(String stepNum : list){
			stringBuffer.append(stepNum);
			if(i<list.size()-1){
				stringBuffer.append(",");
			}
			i++;
		}
		return stringBuffer.toString();
	}
}
