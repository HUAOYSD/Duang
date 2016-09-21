package org.duang.action.sys;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.InvestMember;
import org.duang.entity.LoanMember;
import org.duang.entity.MemberInfo;
import org.duang.enums.IDCard;
import org.duang.enums.If;
import org.duang.service.MemberInfoService;
import org.duang.util.ConstantCode;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.hibernate.criterion.Order;
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
@Action(value="memberinfo")
@ParentPackage("sys")
@Results(value={
		@Result(name="levelmember", type="dispatcher", location="WEB-INF/page/sys/memberinfo/levelMemberInfoList.jsp"),
		@Result(name="memberInfoList", type="dispatcher", location="WEB-INF/page/sys/memberinfo/memberInfoList.jsp"),
		@Result(name="uploadMemberInfoImg", type="dispatcher", location="WEB-INF/page/sys/memberinfo/uploadMemberInfoImg.jsp"),
		@Result(name="showMemberInfoInvestOrLoan", type="dispatcher", location="WEB-INF/page/sys/memberinfo/investOrLoanInfo.jsp"),
		@Result(name=com.opensymphony.xwork2.Action.ERROR, type="dispatcher", location="error.jsp")
})
public class MemberInfoAction extends BaseAction<MemberInfo>{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private MemberInfoService sysMemberInfoService;
	@Resource(name="sysmemberinfoserviceimpl")
	public void setService(MemberInfoService sysMemberInfoService) {
		this.sysMemberInfoService = sysMemberInfoService;
	}
	
	/**
	 * 页面跳转 ---客户管理页面
	 * @Title: gotoMemberInfoList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月11日 下午3:37:56
	 * @return: String      
	 * @throws
	 */
	public String gotoMemberInfoList(){
		return "memberInfoList";
	}
	
	
	/**   
	 * 去查询用户的推荐关系列表
	 * @Title: gotoLevelMebmer   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年9月21日 上午10:01:37
	 * @return: String      
	 * @throws   
	 */  
	public String gotoLevelMebmer(){
		return "levelmember";
	}
	
	
	/**   
	 * 查询用户的推荐关系
	 * @Title: queryLevelMebmer   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 白攀    
	 * @date 2016年9月21日 上午9:57:27
	 * @return: void      
	 * @throws   
	 */  
	public void queryLevelMebmer(){
		try {
			String where = "";
			if (entity!=null) {
				if (DataUtils.notEmpty(entity.getLoginName())) {
					where += " AND LOGIN_NAME LIKE '%"+entity.getLoginName()+"%' ";
				}
				if (DataUtils.notEmpty(entity.getRealName())) {
					where += " AND REAL_NAME LIKE '%"+entity.getRealName()+"%' ";
				}
				if (DataUtils.notEmpty(entity.getPhone())) {
					where += " AND PHONE = '"+entity.getPhone()+"' ";
				}
				if (DataUtils.notEmpty(entity.getId())) {
					where += " AND ID_CARD = '"+entity.getId()+"' ";
				}
			}
			List<Map<String, Object>> list = sysMemberInfoService.queryLevelMemberInfo(where, getPageUtil());
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", list);
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户管理ACTION查询queryLevelMebmer方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("客户管理ACTION查询queryLevelMebmer方法错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
	
	public void queryAllMember() {
		try {
			condsUtils.addProperties(false, "isdelete", "order");
			condsUtils.addValues(false, "0", Order.desc("createTime"));
			List<MemberInfo> list = sysMemberInfoService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户管理ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("客户管理ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
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
				//封装理财用户信息
				Set<InvestMember> investMembers =  memberInfo.getInvestMembers();
				for(InvestMember investMember : investMembers){
					map.put("investMember_id", investMember.getId());
					map.put("isContract", investMember.getIsContract());
					map.put("balance", investMember.getBalance());
					map.put("investing", investMember.getInvesting());
					map.put("totalIncome", investMember.getInvesting());
					map.put("totalMoney", investMember.getTotalMoney());
					map.put("useableScore", investMember.getUseableScore());
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
	
	/**
	 * @Title: freezeMemberInfo   
	 * @Description: TODO(冻结或者解冻操作)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月2日 下午3:51:03
	 * @return: void      
	 * @throws
	 */
	public void freezeMemberInfo(){
		if (entity!=null && DataUtils.notEmpty(entity.getId())) {
			try {
				String sql = "update member_info SET is_freeze="+entity.getIsFreeze()+" where id='"+entity.getId()+"'";
				boolean issuccess = sysMemberInfoService.executeSql(sql);
				if (issuccess) {
					jsonObject.put("result",true);
					if(ConstantCode.FREEZE.equals(entity.getIsFreeze())){
						jsonObject.put("msg","冻结成功");
					}else{
						jsonObject.put("msg","解冻成功");
					}
					printJsonResult();
				}else{
					jsonObject.put("result",false);
					jsonObject.put("msg","解冻或者冻结理财用户失败，请联系管理员");
					printJsonResult();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("理财用户ACTION冻结或者解冻操作错误："+e.getMessage(), this.getClass());
				LoggerUtils.error("理财用户ACTION冻结或者解冻操作错误："+e.getLocalizedMessage(), this.getClass());
			}
		}
	}
	
	public void deleteMemberInfo(){
		String id  = getRequest().getParameter("id");
		if (DataUtils.notEmpty(id)) {
			try {
				String sql = "update member_info SET isdelete=1 where id= '"+id+"'";
				boolean issuccess = sysMemberInfoService.executeSql(sql);
				if (issuccess) {
					jsonObject.put("result",true);
					jsonObject.put("msg","删除成功");
					printJsonResult();
				}else{
					jsonObject.put("result",false);
					jsonObject.put("msg","删除失败，请联系管理员");
					printJsonResult();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("理财用户ACTION删除错误："+e.getMessage(), this.getClass());
				LoggerUtils.error("理财用户ACTION删除错误："+e.getLocalizedMessage(), this.getClass());
			}
		}
	}
	
	/**
	 * 跳转到客户图片的页面
	 * 
	 * @Title: touUpload
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月11日 下午16:18:08
	 * @return: String
	 * @throws
	 */
	public String gotoUploadImg() {
		try{
			String type =  getRequest().getParameter("type");
			getRequest().setAttribute("type", getRequest().getParameter("type"));
			MemberInfo memberInfo = sysMemberInfoService.findById(entity.getId());
			getRequest().setAttribute("memberInfo", memberInfo);
			//返回身份证前照和后照的具体路径
			if(IDCard.IDCARD1.getVal()==Integer.parseInt(type) && DataUtils.notEmpty(memberInfo.getIdCardImg1())){
				getRequest().setAttribute("path", "/resources/file/basic/"+memberInfo.getId()+"/idcard/"+memberInfo.getIdCardImg1());
			}else if(IDCard.IDCARD2.getVal()==Integer.parseInt(type) &&  DataUtils.notEmpty(memberInfo.getIdCardImg2())){
				getRequest().setAttribute("path", "/resources/file/basic/"+memberInfo.getId()+"/idcard/"+memberInfo.getIdCardImg2());
			}else {
				getRequest().setAttribute("path", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("跳转到上传文件页面错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("跳转到上传文件页面错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "uploadMemberInfoImg";
	}
	
	/**
	 * 仅仅展示上传的图片，不能上传图片
	 * @Title: showUserImage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月11日 下午16:18:08
	 * @return: String
	 * @throws
	 */
	public String showMemberInfoImage() {
		try{
			String type =  getRequest().getParameter("type");
			getRequest().setAttribute("type", getRequest().getParameter("type"));
			getRequest().setAttribute("only_show",If.If1.getVal());
			MemberInfo memberInfo = sysMemberInfoService.findById(entity.getId());
			getRequest().setAttribute("memberInfo", memberInfo);
			//返回身份证前照和后照的具体路径
			if(IDCard.IDCARD1.getVal()==Integer.parseInt(type) && DataUtils.notEmpty(memberInfo.getIdCardImg1())){
				getRequest().setAttribute("path", "/resources/file/basic/"+memberInfo.getId()+"/idcard/"+memberInfo.getIdCardImg1());
			}else if(IDCard.IDCARD2.getVal()==Integer.parseInt(type) &&  DataUtils.notEmpty(memberInfo.getIdCardImg1())){
				getRequest().setAttribute("path", "/resources/file/basic/"+memberInfo.getId()+"/idcard/"+memberInfo.getIdCardImg2());
			}else {
				getRequest().setAttribute("path", "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("跳转到上传文件页面错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("跳转到上传文件页面错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "uploadMemberInfoImg";
	}
	
	
	/**
	 * 根据条件查询客户
	 * @Title: queryInvestMember
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月11日 下午2:27:37
	 * @return: void
	 * @throws
	 */
	public void queryMemberInfoByParameter() {
		try {
			if (DataUtils.notEmpty(entity.getLoginName())) {
				condsUtils.addProperties(false, "loginName");
				condsUtils.concatValue(new String[] { entity.getLoginName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getRealName())) {
				condsUtils.addProperties(false, "realName");
				condsUtils.concatValue(new String[] { entity.getRealName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getPhone())) {
				condsUtils.addProperties(false, "phone");
				condsUtils.concatValue(new String[] { entity.getPhone(), "like" });
			}
			List<MemberInfo> list = sysMemberInfoService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", getPageUtil().getCountRecords());
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("客户ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}
}	
