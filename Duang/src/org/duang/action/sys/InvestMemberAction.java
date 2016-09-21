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
import org.duang.entity.InvestMember;
import org.duang.entity.MemberInfo;
import org.duang.enums.IDCard;
import org.duang.enums.If;
import org.duang.enums.InvestOrLoan;
import org.duang.service.InvestMemberService;
import org.duang.service.MemberInfoService;
import org.duang.util.ConstantCode;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 理财用户Action
 * 
 * @ClassName: SysInvestMemberAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年7月26日 下午1:55:48
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "investmember")
@ParentPackage("sys")
@Results(value = { 
		@Result(name = ResultPath.LIST, type = "dispatcher", location = "WEB-INF/page/sys/investmember/investMemberList.jsp"),
		@Result(name = "addInvestMember", type = "dispatcher", location = "WEB-INF/page/sys/investmember/addInvestMember.jsp"),
		@Result(name = "editInvestMember", type = "dispatcher", location = "WEB-INF/page/sys/investmember/editInvestMember.jsp"),
		@Result(name = "uploadInvestMemberImg", type = "dispatcher", location = "WEB-INF/page/sys/investmember/uploadInvestMemberImg.jsp"),
		@Result(name = "showMemberInfoInvest", type="dispatcher", location="WEB-INF/page/sys/memberinfo/investOrLoanInfo.jsp"),
		@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") 
		})
public class InvestMemberAction extends BaseAction<InvestMember> {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private InvestMemberService investMemberService;

	@Resource(name = "sysinvestmemberserviceimpl")
	public void setService(InvestMemberService investMemberService) {
		this.investMemberService = investMemberService;
	}

	private MemberInfoService sysMemberInfoService;
	@Resource(name="sysmemberinfoserviceimpl")
	public void setService(MemberInfoService sysMemberInfoService) {
		this.sysMemberInfoService = sysMemberInfoService;
	}
	
	/**
	 * 跳转到理财客户页面
	 * 
	 * @Title: investMemberList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年7月30日 下午11:18:08
	 * @return: String
	 * @throws
	 */
	public String investMemberList() {
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
	 * 查询所有的客户，并用json方式返回
	 * 
	 * @Title: queryInvestMember
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年7月30日 下午2:27:37
	 * @return: void
	 * @throws
	 */
	public void queryAllInvestMember() {
		try {
			condsUtils.addProperties(true, "memberInfo");
			condsUtils.concatValue(new String[] { "infoAlias", "as" });
			condsUtils.addProperties(false, "infoAlias.isdelete");
			condsUtils.addValues(false, "0");
			@SuppressWarnings("rawtypes")
			List list = investMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
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
			LoggerUtils.error("理财客户类型ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财客户类型ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}

	/**
	 * 查询所有的客户，并用json方式返回
	 * 
	 * @Title: queryInvestMember
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年7月30日 下午2:27:37
	 * @return: void
	 * @throws
	 */
	public void queryInvestMemberByParameter() {
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
			if (ConstantCode.NOSELECTED!=entity.getIsContract()) {
				condsUtils.addProperties(false, "isContract");
				condsUtils.addValues(false, entity.getIsContract());
			}
			@SuppressWarnings("rawtypes")
			List list = investMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
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
			LoggerUtils.error("理财客户类型ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财客户类型ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
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
				InvestMember im = (InvestMember) array[1];
				map.put("id", im.getId());
				map.put("isContract", im.getIsContract());
				map.put("balance", im.getBalance());
				map.put("investing", im.getInvesting());
				map.put("totalIncome", im.getInvesting());
				map.put("totalMoney", im.getTotalMoney());
				map.put("useableScore", im.getUseableScore());
				map.put("currentIncome", im.getCurrentIncome());
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
				map.put("registerStyle", memberInfo.getRegisterStyle());
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
			LoggerUtils.error("封装理财用户错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("封装理财用户错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return listMap;
	}

	public String addInvestMember() {
		return "addInvestMember";
	}

	/**
	 * 跳转到edit页面
	 * 
	 * @Title: eidtInvestMember
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月4日 下午2:45:07
	 * @return: String
	 * @throws
	 */
	public String eidtInvestMember() {
		return "editInvestMember";
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
	public void getInvestMemberInfo() {
		try {
			if (entity != null && DataUtils.notEmpty(entity.getId())) {
				System.out.println(entity.getId());
				InvestMember im = investMemberService.findById(entity.getId());
				if (im != null) {
					jsonObject.put("id", im.getId());
					jsonObject.put("isContract", im.getIsContract());
					jsonObject.put("balance", im.getBalance());
					jsonObject.put("investing", im.getInvesting());
					jsonObject.put("totalIncome", im.getInvesting());
					jsonObject.put("totalMoney", im.getTotalMoney());
					jsonObject.put("useableScore", im.getUseableScore());
					jsonObject.put("currentIncome", im.getCurrentIncome());
					MemberInfo memberInfo = im.getMemberInfo();
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
					jsonObject.put("memberInfo.registerStyle", memberInfo.getRegisterStyle());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财用户ACTION，方法getInvestMemberInfo错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财用户ACTION，方法getInvestMemberInfo错误：" + e.getLocalizedMessage(), this.getClass());
		} finally {
			printJsonResult();
		}
	}

	/**
	 * 保存理财用户
	 * 
	 * @Title: saveInvestMember
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月4日 下午4:43:24
	 * @return: void
	 * @throws
	 */
	public void saveInvestMember() {
		if (entity != null) {
			try {
				// 判断是否存在相同总名称的数据，如果存在则取消添加
				if (investMemberService.count("memberInfo.loginName", entity.getMemberInfo().getLoginName()) > 0) {
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
					entity.getMemberInfo().setIsdelete(ConstantCode.UNDELETE);
					entity.getMemberInfo().setHandPassword(ConstantCode.DEFAULT);
					boolean issuccess = investMemberService.saveEntity(entity);
					if (issuccess) {
						jsonObject.put("result", true);
						jsonObject.put("msg", "添加理财用户成功");
						printJsonResult();
					} else {
						jsonObject.put("result", false);
						jsonObject.put("msg", "添加理财用户失败，请联系管理员");
						printJsonResult();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("理财用户ACTION增加错误：" + e.getMessage(), this.getClass());
				LoggerUtils.error("理财用户ACTION增加错误：" + e.getLocalizedMessage(), this.getClass());
			}
		}
	}

	/**
	 * 更新理财用户
	 * 
	 * @Title: updateInvestMember
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月4日 下午4:43:56
	 * @return: void
	 * @throws
	 */
	public void updateInvestMember() {
		if (entity != null && DataUtils.notEmpty(entity.getId()) && DataUtils.notEmpty(entity.getMemberInfo().getId())) {
			try {
				InvestMember im = investMemberService.findById(entity.getId());
				entity.getMemberInfo().setModifyTime(new Date());
				entity.getMemberInfo().setModifyuser(SessionTools.getSessionSysUser().getId());
				entity.getMemberInfo().setCreateTime(im.getMemberInfo().getCreateTime());
				entity.getMemberInfo().setCreateuser(im.getMemberInfo().getCreateuser());
				entity.getMemberInfo().setHandPassword(im.getMemberInfo().getHandPassword());
				entity.getMemberInfo().setIsdelete(im.getMemberInfo().getIsdelete());
				entity.getMemberInfo().setIsFreeze(im.getMemberInfo().getIsFreeze());
				boolean issuccess = investMemberService.updateEntity(entity);
				if (issuccess) {
					jsonObject.put("result", true);
					jsonObject.put("msg", "修改理财用户成功");
					printJsonResult();
				} else {
					jsonObject.put("result", false);
					jsonObject.put("msg", "修改理财用户失败，请联系管理员");
					printJsonResult();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LoggerUtils.error("理财用户ACTION修改错误：" + e.getMessage(), this.getClass());
				LoggerUtils.error("理财用户ACTION修改错误：" + e.getLocalizedMessage(), this.getClass());
			} finally {
				printJsonResult();
			}
		}
	}

	
	/**
	 * 跳转到理财信息页面上
	 * @Title: showInvestMemberInfoById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月12日 下午2:27:37
	 * @return: void
	 * @throws
	 */
	public String showInvestMemberInfoById() {
		try {
			entity = investMemberService.findEntity("id", entity.getId());
			getRequest().setAttribute("selectInvest", InvestOrLoan.Invest.getVal());
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("客户ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("客户ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} 
		return "showMemberInfoInvest";
	}
	
	/**
	 * 根据id查询理财用户
	 * @Title: findInvestMemberInfoById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月12日 下午2:27:37
	 * @return: void
	 * @throws
	 */
	public void findInvestMemberInfoById() {
		try {
			entity = investMemberService.findEntity("id", entity.getId());
			if (entity!=null) {
				jsonObject.put("result", true);
				jsonObject.put("msg", "此用户为理财用户");
				printJsonResult();
			} else {
				jsonObject.put("result", false);
				jsonObject.put("msg", "此用户不是理财用户");
				printJsonResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("理财客户ACTION查询错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("理财客户ACTION查询错误：" + e.getLocalizedMessage(), this.getClass());
		} 
	}
	
}
