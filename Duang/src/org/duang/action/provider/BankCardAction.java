package org.duang.action.provider;
import java.util.ArrayList;
import java.util.Date;
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
import org.duang.entity.BindCard;
import org.duang.entity.InvestMember;
import org.duang.entity.MemberInfo;
import org.duang.service.BindCardService;
import org.duang.service.InvestMemberService;
import org.duang.util.DES;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————银行卡Action
 * @ClassName:  BankCardAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_bankcard")
public class BankCardAction extends BaseAction<BindCard>{
	
	private BindCardService bindCardService;
	@Resource
	public void setMemberInfoService(BindCardService bindCardService) {
		this.bindCardService = bindCardService;
	}
	
	InvestMemberService investMemberService ;
	@Resource
	public void setInvestMemberService(InvestMemberService investMemberService) {
		this.investMemberService = investMemberService;
	}
	
	/**   
	 * 查询银行卡列表
	 * @Title: queryCardList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午3:04:04
	 * @return: void      
	 * @throws   
	 */  
	public void queryCardList(){
		boolean success = false;
		try {
			String memberid = getRequest().getParameter("memberid");
			if (DataUtils.notEmpty(memberid)) {
				condsUtils.addProperties(true, "memberInfo");
				condsUtils.concatValue(new String[] { "infoAlias", "as" });
				condsUtils.addProperties(false, "infoAlias.id");
				condsUtils.addValues(false, memberid);
				@SuppressWarnings("rawtypes")
				List list = bindCardService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
				success = true;
				jsonObject.put("result", fillDataObjectArray(list));
			}else{
				msg = "密码不能为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——queryCardList方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——queryCardList方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 增加绑定银行卡
	 * @Title: insertBindCard   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午3:36:34
	 * @return: void      
	 * @throws   
	 */  
	public void insertBindCard(){
		boolean success = false;
		try {
			String memberid = getRequest().getParameter("memberid");
			String p_name = getRequest().getParameter("p_name");
			String p_idcard = getRequest().getParameter("p_idcard");
			String p_phone = getRequest().getParameter("p_phone");
			String p_bankNo = getRequest().getParameter("p_bankNo");
			String p_openBank = getRequest().getParameter("p_openBank");
			String p_type = getRequest().getParameter("p_type");
			//判断参数是否为空
			if(DataUtils.notEmpty(memberid) && DataUtils.notEmpty(p_name)&& DataUtils.notEmpty(p_idcard)&& 
			DataUtils.notEmpty(p_phone)&& DataUtils.notEmpty(p_bankNo)&& DataUtils.notEmpty(p_openBank)&& DataUtils.notEmpty(p_type)){
				MemberInfo memberInfo = new MemberInfo();
				memberInfo.setId(memberid);
				BindCard bindCard = new BindCard();
				bindCard.setId(DataUtils.randomUUID());
				bindCard.setBankNo(DES.decryptDES(p_bankNo));
				bindCard.setIdcard(DES.decryptDES(p_idcard));
				bindCard.setMemberInfo(memberInfo);
				bindCard.setName(DES.decryptDES(p_name));
				bindCard.setOpenBank(DES.decryptDES(p_openBank));
				bindCard.setOptTime(new Date());
				bindCard.setPhone(DES.decryptDES(p_phone));
				bindCard.setType(Integer.valueOf(DES.decryptDES(p_type)));
				success = bindCardService.saveEntity(bindCard);
				if(!success){
					msg = "用户未实名";
				}
			}else{
				msg = "参数不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——insertBindCard方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——insertBindCard方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	
	/**   
	 * 解绑银行卡
	 * @Title: unwrapCard   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月5日 下午3:45:08
	 * @return: void      
	 * @throws   
	 */  
	public void unwrapCard(){
		boolean success = false;
		try {
			String memberid = getRequest().getParameter("memberid");
			String id = getRequest().getParameter("id");
			if (DataUtils.notEmpty(id) && DataUtils.notEmpty(memberid)){
				//查询余额和投资中金额是否为空,判断
				boolean isNullBalance = true;
				condsUtils.addProperties(true, "memberInfo");
				condsUtils.concatValue(new String[] { "infoAlias", "as" });
				condsUtils.addProperties(false, "infoAlias.id");
				condsUtils.concatValue(new String[] { memberid});
				List<InvestMember> list = investMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), null);
				for(InvestMember investMember : list){
					if(investMember.getBalance()==0 && investMember.getInvesting()==0){
						isNullBalance = false;
						break;
					}
				}
				if(isNullBalance){
					success = bindCardService.deleteEntity("id", id);
					if(!success){
						msg = "解绑错误，连接超时";
					}
				}else{
					msg = "解绑前请提现所有资产余额";
				}
			}else{
				msg = "参数为空";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("MemberAction——unwrapCard方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("MemberAction——unwrapCard方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	/**
	 * 封装银行绑定信息   当参数list封装的是一个数组时，list.get[0]是BindCard对象，list.get[1]是MemberInfo对象
	 * 这种现象只有MemberInfo对象的参数作为查询条件时发生
	 * @Title: fillDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月7日 下午3:54:10
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(@SuppressWarnings("rawtypes") List list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] array = (Object[]) list.get(i);
				BindCard bindCard = (BindCard) array[1];
				//客户的基本信息
				map.put("id", bindCard.getId());
				map.put("name", bindCard.getName());
				map.put("idcard", bindCard.getIdcard());
				map.put("phone", bindCard.getPhone());
				map.put("bankNo", bindCard.getBankNo());
				map.put("openBank", bindCard.getOpenBank());
				map.put("type", bindCard.getType());
				map.put("photoImg1", bindCard.getPhotoImg1());
				map.put("photoImg2", bindCard.getPhotoImg2());
				map.put("optTime", DateUtils.getTimeStamp(bindCard.getOptTime()));
				MemberInfo memberInfo = (MemberInfo) array[0];
				map.put("memberInfoId", memberInfo.getId());
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装绑定银行卡错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装绑定银行卡错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
}
