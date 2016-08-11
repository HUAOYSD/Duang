package org.duang.action.sys;

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
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.SessionTools;
import org.duang.entity.CustomerManager;
import org.duang.entity.LoanMember;
import org.duang.entity.MemberInfo;
import org.duang.enums.IDCard;
import org.duang.enums.If;
import org.duang.service.LoanMemberService;
import org.duang.service.MemberInfoService;
import org.duang.util.ConstantCode;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.duang.util.MD5Utils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 借贷用户Action
 * 
 * @ClassName: LoanMemberAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年8月10日 下午5:55:48
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "loanmember")
@ParentPackage("sys")
@Results(value = { 
		@Result(name = ResultPath.LIST, type = "dispatcher", location = "WEB-INF/page/sys/loanmember/loanMemberList.jsp"),
		@Result(name = "addLoanMember", type = "dispatcher", location = "WEB-INF/page/sys/loanmember/addLoanMember.jsp"),
		@Result(name = "editLoanMember", type = "dispatcher", location = "WEB-INF/page/sys/loanmember/editLoanMember.jsp"),
		@Result(name = "uploadLoanMemberImg", type = "dispatcher", location = "WEB-INF/page/sys/loanmember/uploadLoanMemberImg.jsp"),
		@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") 
		})
public class LoanMemberAction extends BaseAction<LoanMember> {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private LoanMemberService loanMemberService;

	@Resource(name = "loanmemberserviceimpl")
	public void setService(LoanMemberService loanMemberService) {
		this.loanMemberService = loanMemberService;
	}

	private MemberInfoService sysMemberInfoService;
	@Resource(name="sysmemberinfoserviceimpl")
	public void setService(MemberInfoService sysMemberInfoService) {
		this.sysMemberInfoService = sysMemberInfoService;
	}
	
	/**
	 * 跳转到借贷客户页面
	 * 
	 * @Title: investMemberList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年7月30日 下午11:18:08
	 * @return: String
	 * @throws
	 */
	public String loanMemberList() {
		return ResultPath.LIST;
	}
	/**
	 * 跳转到上传理财客户图片的页面
	 * 
	 * @Title: touUpload
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年7月30日 下午11:18:08
	 * @return: String
	 * @throws
	 */
	public String touUpload() {
		try{
			String type =  getRequest().getParameter("type");
			getRequest().setAttribute("type", getRequest().getParameter("type"));
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
		return "uploadInvestMemberImg";
	}
	
	/**
	 * 仅仅展示上传的图片，不能上传图片
	 * @Title: showUserImage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年7月30日 下午11:18:08
	 * @return: String
	 * @throws
	 */
	public String showUserImage() {
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
		return "uploadInvestMemberImg";
	}
	
	/**
	 * 查询所有的借贷客户，并用json方式返回
	 * 
	 * @Title: queryAllLoanMember
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月10日 下午5:27:37
	 * @return: void
	 * @throws
	 */
	public void queryAllLoanMember() {
		try {
			condsUtils.addProperties(true, "memberInfo");
			condsUtils.concatValue(new String[] { "infoAlias", "as" });
			condsUtils.addProperties(false, "infoAlias.isdelete");
			condsUtils.addValues(false, "0");
			@SuppressWarnings("rawtypes")
			List list = loanMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = loanMemberService.count(condsUtils.getPropertys(), condsUtils.getValues());
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", count);
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷客户类型ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("借贷客户类型ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}

	/**
	 * 查询所有的借贷客户，并用json方式返回
	 * 
	 * @Title: queryLoanMemberByParameter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月10日 下午5:27:37
	 * @return: void
	 * @throws
	 */
	public void queryLoanMemberByParameter() {
		try {
			condsUtils.addProperties(true, "memberInfo");
			condsUtils.concatValue(new String[] { "infoAlias", "as" });
			if (DataUtils.notEmpty(entity.getMemberInfo().getLoginName())) {
				condsUtils.addProperties(false, "infoAlias.loginName");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getLoginName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getMemberInfo().getRealName())) {
				condsUtils.addProperties(false, "infoAlias.realName");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getRealName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getMemberInfo().getPhone())) {
				condsUtils.addProperties(false, "infoAlias.phone");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getPhone(), "like" });
			}
			if (DataUtils.notEmpty(entity.getMemberInfo().getType()) && !ConstantCode.NOSELECTED1.equals(entity.getMemberInfo().getType())) {
				condsUtils.addProperties(false, "infoAlias.type");
				condsUtils.addValues(false, entity.getMemberInfo().getType());
			}

			@SuppressWarnings("rawtypes")
			List list = loanMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = loanMemberService.count(condsUtils.getPropertys(), condsUtils.getValues());
			if (list != null && list.size() > 0) {
				jsonObject.put("result", true);
				jsonObject.put("rows", fillDataObjectArray(list));
				jsonObject.put("total", count);
			} else {
				jsonObject.put("rows", new JSONArray());
				jsonObject.put("total", 0);
				jsonObject.put("result", false);
				jsonObject.put("msg", "没有符合条件的数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷客户类型ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("借贷客户类型ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}

	/**
	 * 在用条件查询的时候，查询出来为List<Object[]>，所以需要进行封装
	 * 
	 * @Title: fillDataObjectArray
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param list
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月3日 下午3:29:33
	 * @return: List<Map<String,Object>>
	 * @throws
	 */
	private List<Map<String, Object>> fillDataObjectArray(@SuppressWarnings("rawtypes") List list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] array = (Object[]) list.get(i);
				LoanMember lm = (LoanMember) array[1];
				map.put("id", lm.getId());
				map.put("backMoney", lm.getBackMoney());
				map.put("expectMoney", lm.getExpectMoney());
				map.put("lendMoney", lm.getLendMoney());
				map.put("residueMoney", lm.getResidueMoney());
				MemberInfo memberInfo = (MemberInfo) array[0];
				map.put("memberInfoId", memberInfo.getId());
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
				map.put("type", memberInfo.getType());
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
				listMap.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("封装借贷用户错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装借贷用户错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}

	/**
	 * 跳转到添加借贷
	 * @Title: addLoanMember   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月10日 下午5:38:06
	 * @return: String      
	 * @throws
	 */
	public String addLoanMember() {
		return "addLoanMember";
	}

	/**
	 * 跳转到edit页面
	 * 
	 * @Title: eidtLoanMember
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月10日 下午5:45:07
	 * @return: String
	 * @throws
	 */
	public String eidtLoanMember() {
		return "editLoanMember";
	}

	/**
	 * 根据id获得一个理财用户对象
	 * 
	 * @Title: getInvestMemberInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月4日 下午2:45:44
	 * @return: String
	 * @throws
	 */
	public void getLoanMemberInfo() {
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId())) {
				System.out.println(entity.getId());
				LoanMember lm = loanMemberService.findById(entity.getId());
				if (lm != null) {
					jsonObject.put("id", lm.getId());
					jsonObject.put("backMoney", lm.getBackMoney());
					jsonObject.put("expectMoney", lm.getExpectMoney());
					jsonObject.put("lendMoney", lm.getLendMoney());
					jsonObject.put("residueMoney", lm.getResidueMoney());
					CustomerManager customerManager = lm.getCustomerManager();
					jsonObject.put("customerManager.id", customerManager.getId());
					jsonObject.put("customerManagerId", customerManager.getId());
					jsonObject.put("managerName", customerManager.getName());
					
					MemberInfo memberInfo = lm.getMemberInfo();
					jsonObject.put("memberInfoId", memberInfo.getId());
					jsonObject.put("memberInfo.memberInfoId", memberInfo.getId());
					jsonObject.put("memberInfo.loginName", memberInfo.getLoginName());
					jsonObject.put("memberInfo.realName", memberInfo.getRealName());
					jsonObject.put("memberInfo.nickname", memberInfo.getNickname());
					jsonObject.put("memberInfo.email", memberInfo.getEmail());
					jsonObject.put("memberInfo.age", memberInfo.getAge());
					jsonObject.put("memberInfo.sex", memberInfo.getSex());
					jsonObject.put("memberInfo.phone", memberInfo.getPhone());
					jsonObject.put("memberInfo.isDelete", memberInfo.getIsdelete());
					jsonObject.put("memberInfo.createTime", DateUtils.getTimeStamp(memberInfo.getCreateTime()));
					jsonObject.put("memberInfo.modifyTime", DateUtils.getTimeStamp(memberInfo.getModifyTime()));
					jsonObject.put("memberInfo.createuser", memberInfo.getCreateuser());
					jsonObject.put("memberInfo.modifyuser", memberInfo.getModifyuser());
					jsonObject.put("memberInfo.userImg", memberInfo.getUserImg());
					jsonObject.put("memberInfo.isEliteAccount", memberInfo.getIsEliteAccount());
					jsonObject.put("memberInfo.type", memberInfo.getType());
					jsonObject.put("memberInfo.level", memberInfo.getLevel());
					jsonObject.put("memberInfo.price", memberInfo.getPrice());
					jsonObject.put("memberInfo.password", memberInfo.getPassword());
					jsonObject.put("memberInfo.payPassword", memberInfo.getPayPassword());
					jsonObject.put("memberInfo.handPassword", memberInfo.getHandPassword());
					jsonObject.put("memberInfo.isFreeze", memberInfo.getIsFreeze());
					jsonObject.put("memberInfo.idCard", memberInfo.getIdCard());
					jsonObject.put("memberInfo.miDescribe", memberInfo.getMiDescribe());
					jsonObject.put("memberInfo.idCardImg1", memberInfo.getIdCardImg1());
					jsonObject.put("memberInfo.idCardImg2", memberInfo.getIdCardImg2());
					jsonObject.put("memberInfo.myQr", memberInfo.getMyQr());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("借贷用户ACTION，方法getLoanMemberInfo错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("借贷用户ACTION，方法getLoanMemberInfo错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}

	/**
	 * 保存借贷用户
	 * @Title: saveLoanMember
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月10日 下午5:43:24
	 * @return: void
	 * @throws
	 */
	public void saveLoanMember() {
		if (entity != null) {
			try {
				// 判断是否存在相同总名称的数据，如果存在则取消添加
				if (loanMemberService.count("memberInfo.loginName", entity.getMemberInfo().getLoginName()) > 0) {
					jsonObject.put("result", false);
					jsonObject.put("msg", "添加失败，已经存在相同名称的用户！");
					printJsonResult();
				} else {
					entity.setId(DataUtils.randomUUID());
					entity.getMemberInfo().setId(DataUtils.randomUUID());
					entity.getMemberInfo().setCreateTime(new Date());
					entity.getMemberInfo().setModifyTime(new Date());
					entity.getMemberInfo().setCreateuser(SessionTools.getSessionSysUser().getId());
					entity.getMemberInfo().setModifyuser(SessionTools.getSessionSysUser().getId());
					if(DataUtils.notEmpty(entity.getMemberInfo().getPassword())){
						entity.getMemberInfo().setPassword(MD5Utils.md5(entity.getMemberInfo().getLoginName()));
					}else{
						entity.getMemberInfo().setPassword(MD5Utils.md5(entity.getMemberInfo().getPassword()));
					}
					boolean issuccess = loanMemberService.saveEntity(entity);
					if (issuccess) {
						jsonObject.put("result", true);
						jsonObject.put("msg", "添加借贷用户成功");
						printJsonResult();
					} else {
						jsonObject.put("result", false);
						jsonObject.put("msg", "添加借贷用户失败，请联系管理员");
						printJsonResult();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("借贷用户ACTION增加错误：" + e.getMessage(), this.getClass());
				LoggerUtils.error("借贷用户ACTION增加错误：" + e.getLocalizedMessage(), this.getClass());
			}
		}
	}

	/**
	 * 更新借贷用户
	 * @Title: updateloanMember
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月10日 下午5:43:56
	 * @return: void
	 * @throws
	 */
	public void updateloanMember() {
		if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(entity.getMemberInfo().getId())) {
			try {
				LoanMember im = loanMemberService.findById(entity.getId());
				entity.getMemberInfo().setModifyTime(new Date());
				entity.getMemberInfo().setModifyuser(SessionTools.getSessionSysUser().getId());
				entity.getMemberInfo().setCreateTime(im.getMemberInfo().getCreateTime());
				entity.getMemberInfo().setCreateuser(im.getMemberInfo().getCreateuser());
				entity.getMemberInfo().setHandPassword(im.getMemberInfo().getHandPassword());
				entity.getMemberInfo().setIsdelete(im.getMemberInfo().getIsdelete());
				entity.getMemberInfo().setIsFreeze(im.getMemberInfo().getIsFreeze());
				boolean issuccess = loanMemberService.updateEntity(entity);
				if (issuccess) {
					jsonObject.put("result", true);
					jsonObject.put("msg", "修改借贷用户成功");
					printJsonResult();
				} else {
					jsonObject.put("result", false);
					jsonObject.put("msg", "修改借贷用户失败，请联系管理员");
					printJsonResult();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("借贷用户ACTION修改错误：" + e.getMessage(), this.getClass());
				LoggerUtils.error("借贷用户ACTION修改错误：" + e.getLocalizedMessage(), this.getClass());
			} finally {
				printJsonResult();
			}
		}
	}

}
