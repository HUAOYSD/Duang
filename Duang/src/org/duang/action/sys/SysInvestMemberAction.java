package org.duang.action.sys;

import java.util.ArrayList;
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
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.InvestMember;
import org.duang.entity.MemberInfo;
import org.duang.service.SysInvestMemberService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
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
		@Result(name="addInvestMember", type="dispatcher", location="WEB-INF/page/sys/investmember/addInvestMember.jsp"),
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
		try {
			condsUtils.addProperties(true, "memberInfo");
			condsUtils.concatValue(new String[]{"infoAlias","as"});
			condsUtils.addProperties(false, "infoAlias.isdelete");
			condsUtils.addValues(false, "0");
			List list = investMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = investMemberService.count(condsUtils.getPropertys(), condsUtils.getValues());
			if(list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", count);
			}else{
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
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
		try {
			condsUtils.addProperties(true, "memberInfo");
			condsUtils.concatValue(new String[]{"infoAlias","as"});
			if(DataUtils.notEmpty(entity.getMemberInfo().getName())){
				condsUtils.addProperties(false, "infoAlias.name");
				condsUtils.addValues(false, entity.getMemberInfo().getName());
			}
			if(DataUtils.notEmpty(entity.getMemberInfo().getRealName())){
				condsUtils.addProperties(false, "infoAlias.realName");
				condsUtils.addValues(false, entity.getMemberInfo().getRealName());
			}
			if(DataUtils.notEmpty(entity.getMemberInfo().getPhone())){
				condsUtils.addProperties(false, "infoAlias.phone");
				condsUtils.addValues(false, entity.getMemberInfo().getPhone());
			}
			if(DataUtils.notEmpty(entity.getMemberInfo().getType())){
				condsUtils.addProperties(false, "infoAlias.type");
				condsUtils.addValues(false, entity.getMemberInfo().getType());
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
	
			
			List list = investMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = investMemberService.count(condsUtils.getPropertys(), condsUtils.getValues());
			if(list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", count);
			}else{
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
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
	 * 在用条件查询的时候，查询出来为List<Object[]>，所以需要进行封装
	 * @Title: fillDataObjectArray   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月3日 下午3:29:33
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	private List<Map<String,Object>> fillDataObjectArray(List list){
		List<Map<String,Object>> listMap =  new ArrayList<Map<String,Object>>();
		try{
			for(int i=0;i<list.size();i++){
				Map<String,Object> map = new HashMap<String,Object>();
				Object[] array= (Object[])list.get(i);
				InvestMember im = (InvestMember)array[1];
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
				MemberInfo memberInfo =  (MemberInfo)array[0];
				map.put("memberInfoId",memberInfo.getId());
				map.put("name",memberInfo.getName());
				map.put("realName",memberInfo.getRealName());
				map.put("nickName",memberInfo.getNickname());
				map.put("email",memberInfo.getEmail());
				map.put("age",memberInfo.getAge());
				map.put("sex",memberInfo.getSex());
				map.put("phone",memberInfo.getPhone());
				map.put("describe",memberInfo.getDescribe());
				map.put("isDelete",memberInfo.getIsdelete());
				map.put("createTime", DateUtils.getTimeStamp(memberInfo.getCreateTime()));
				map.put("modifyTime", DateUtils.getTimeStamp(memberInfo.getModifyTime()));
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
	
	/**
	 * 查询全部的时候List<InvestMember>进行封装
	 * @Title: fillDataObject   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月3日 下午3:30:51
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
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
				map.put("name",memberInfo.getName());
				map.put("realName",memberInfo.getRealName());
				map.put("nickName",memberInfo.getNickname());
				map.put("email",memberInfo.getEmail());
				map.put("age",memberInfo.getAge());
				map.put("sex",memberInfo.getSex());
				map.put("phone",memberInfo.getPhone());
				map.put("describe",memberInfo.getDescribe());
				map.put("isDelete",memberInfo.getIsdelete());
				map.put("createTime",DateUtils.getTimeStamp(memberInfo.getCreateTime()));
				map.put("modifyTime",DateUtils.getTimeStamp(memberInfo.getModifyTime()));
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
 
	public String addInvestMember(){
		return "addInvestMember";
	}
	
	public void saveInvestMember(){
		System.out.println("asdfasdf");
		if (entity!=null) {
			try {
				//判断是否存在相同总名称的数据，如果存在则取消添加
				if(investMemberService.count("memberInfo.name",entity.getMemberInfo().getName())>0){
					jsonObject.put("result",false);
					jsonObject.put("msg","添加失败，已经存在相同名称的产品！");
					printJsonResult();
				}else{
					entity.setId(DataUtils.randomUUID());
					boolean issuccess = investMemberService.saveEntity(entity);
					if (issuccess) {
						jsonObject.put("result",true);
						jsonObject.put("msg","添加理财产品成功");
						printJsonResult();
					}else{
						jsonObject.put("result",false);
						jsonObject.put("msg","添加理财产品失败，请联系管理员");
						printJsonResult();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("理财产品ACTION增加错误："+e.getMessage(), this.getClass());
				LoggerUtils.error("理财产品ACTION增加错误："+e.getLocalizedMessage(), this.getClass());
			}
		}
	}
}	
