package org.duang.action.sys;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.CondsUtils;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.InvestMember;
import org.duang.entity.MemberInfo;
import org.duang.service.SysInvestMemberService;
import org.duang.service.SysMemberInfoService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.PageUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 理财用户Action
 * @ClassName:  SysInvestMemberAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年7月26日 下午1:55:48
 */
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Namespaces({@Namespace("/")})
@Action(value="investmember")
@ParentPackage("sys")
@Results(value={
		@Result(name=ResultPath.LIST, type="dispatcher", location="WEB-INF/page/sys/investmember/investMemberList.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher", location="error.jsp")
})
public class SysInvestMemberAction extends BaseAction<InvestMember>{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private SysInvestMemberService investMemberService;
	@Resource(name="sysinvestmemberserviceimpl")
	public void setService(SysInvestMemberService investMemberService) {
		this.investMemberService = investMemberService;
	}
	/**
	 * 跳转到理财客户页面
	 * @Title: investMemberList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年7月30日 下午11:18:08
	 * @return: String      
	 * @throws
	 */
	public String investMemberList(){
		return ResultPath.LIST;
	}
	
	/**
	 * 查询所有的客户，并用json方式返回
	 * @Title: queryInvestMember   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年7月30日 下午2:27:37
	 * @return: void      
	 * @throws
	 */
	public void  queryAllInvestMember(){
		List<InvestMember> list = null;
		try {
			list = investMemberService.queryAllEntity(getPageUtil(), null);
			int count = investMemberService.count();
			if(list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObject(list));
				jsonObject.put("total", count);
			}else{
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财客户类型ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财客户类型ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
		}finally{
			printJsonResult();
		}
	}
	
	/**
	 * 查询所有的客户，并用json方式返回
	 * @Title: queryInvestMember   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年7月30日 下午2:27:37
	 * @return: void      
	 * @throws
	 */
	public void  queryInvestMemberByParameter(){
		List<InvestMember> list = null;
		try {
			if(DataUtils.notEmpty(entity.getMemberInfo().getName())){
				condsUtils.addProperties(true,"memberInfo", "memberInfo.name");
				condsUtils.addValues(true, new String[]{"memberInfo","as"},entity.getMemberInfo().getName());
			}
			if(DataUtils.notEmpty(entity.getMemberInfo().getRealName())){
				condsUtils.addProperties(false,"memberInfo", "memberInfo.realName");
				condsUtils.addValues(false, new String[]{"memberInfo","as"}, entity.getMemberInfo().getRealName());
			}
			if(DataUtils.notEmpty(entity.getMemberInfo().getPhone())){
				condsUtils.addProperties(false,"memberInfo", "memberInfo.phone");
				condsUtils.addValues(false, new String[]{"memberInfo","as"},entity.getMemberInfo().getPhone());
			}
			if(DataUtils.notEmpty(entity.getMemberInfo().getType())){
				condsUtils.addProperties(false,"memberInfo", "memberInfo.type");
				condsUtils.addValues(false,new String[]{"memberInfo","as"}, entity.getMemberInfo().getType());
			}
			if(DataUtils.notEmpty(entity.getCustManagerId())){
				condsUtils.addProperties(false, "custManagerId");
				condsUtils.addValues(false, entity.getCustManagerId());
			}
			if(DataUtils.notEmpty(entity.getManagerName())){
				condsUtils.addProperties(false, "managerName");
				condsUtils.addValues(false, entity.getManagerName());
			}
			if(DataUtils.notEmpty(entity.getIsContract())){
				condsUtils.addProperties(false, "isContract");
				condsUtils.addValues(false,entity.getIsContract());
			}
			list = investMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = investMemberService.count(condsUtils.getPropertys(), condsUtils.getValues());
			if(list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObject(list));
				jsonObject.put("total", count);
			}else{
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财客户类型ACTION查询错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("理财客户类型ACTION查询错误："+e.getLocalizedMessage(), this.getClass());
		}finally{
			printJsonResult();
		}
	}
	
	private List<Map<String,Object>> fillDataObject(List<InvestMember> list){
		List<Map<String,Object>> listMap =  new ArrayList<Map<String,Object>>();
		try{
			for(InvestMember im : list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id",im.getId());
				map.put("idCard",im.getIdcard());
				map.put("bankCard",im.getBankCard());
				map.put("bank",im.getBank());
				map.put("userImage",im.getUserImage());
				map.put("idcardImg1",im.getIdcardImg1());
				map.put("idcardImg2",im.getIdcardImg2());
				map.put("custManagerId",im.getCustManagerId());
				map.put("managerName",im.getManagerName());
				map.put("isContract",im.getIsContract());
				map.put("investMoney",im.getInvestMoney());
				map.put("investingMoney",im.getInvestingMoney());
				map.put("useableMoney",im.getUseableMoney());
				map.put("accountTotalMoney",im.getAccountTotalMoney());
				map.put("freezeMoney",im.getFreezeMoney());
				map.put("unfreezeMoney",im.getUnfreezeMoney());
				map.put("useableScore",im.getUseableScore());
				map.put("allowOnline",im.getAllowOnline());
				MemberInfo memberInfo = im.getMemberInfo();
				map.put("memberInfoId",memberInfo.getId());
				map.put("name",memberInfo.getId());
				map.put("realName",memberInfo.getRealName());
				map.put("nickName",memberInfo.getNickname());
				map.put("email",memberInfo.getEmail());
				map.put("age",memberInfo.getAge());
				map.put("sex",memberInfo.getSex());
				map.put("phone",memberInfo.getPhone());
				map.put("describe",memberInfo.getDescribe());
				map.put("isDelete",memberInfo.getIsdelete());
				map.put("createTime",memberInfo.getCreateTime());
				map.put("modifyTime",memberInfo.getModifyTime());
				map.put("createuser",memberInfo.getCreateuser());
				map.put("modifyuser",memberInfo.getModifyuser());
				map.put("userImg",memberInfo.getUserImg());
				map.put("isEliteAccount",memberInfo.getIsEliteAccount());
				map.put("type",memberInfo.getType());
				map.put("level",memberInfo.getLevel());
				map.put("price",memberInfo.getPrice());
				map.put("password",memberInfo.getPassword());
				map.put("handPassword",memberInfo.getHandPassword());
				map.put("isFreeze",memberInfo.getIsFreeze());
				listMap.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
			LoggerUtils.error("封装理财用户错误："+e.getMessage(), this.getClass());
			LoggerUtils.error("封装理财用户错误："+e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}
 
}	
