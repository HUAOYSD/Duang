package org.duang.action.sys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.aspectj.apache.bcel.classfile.Constant;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.SessionTools;
import org.duang.entity.InvestMember;
import org.duang.entity.MemberInfo;
import org.duang.service.InvestMemberService;
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
	public String touUpload() {
		getRequest().setAttribute("type", getRequest().getParameter("type"));
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
			List list = investMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = investMemberService.count(condsUtils.getPropertys(), condsUtils.getValues());
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
			if (DataUtils.notEmpty(entity.getMemberInfo().getName())) {
				condsUtils.addProperties(false, "infoAlias.name");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getMemberInfo().getRealName())) {
				condsUtils.addProperties(false, "infoAlias.realName");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getRealName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getMemberInfo().getPhone())) {
				condsUtils.addProperties(false, "infoAlias.phone");
				condsUtils.concatValue(new String[] { entity.getMemberInfo().getPhone(), "like" });
			}
			if (DataUtils.notEmpty(entity.getMemberInfo().getType()) && !ConstantCode.NOSELECTED.equals(entity.getMemberInfo().getType())) {
				condsUtils.addProperties(false, "infoAlias.type");
				condsUtils.addValues(false, entity.getMemberInfo().getType());
			}
			if (DataUtils.notEmpty(entity.getCustManagerId())) {
				condsUtils.addProperties(false, "custManagerId");
				condsUtils.addValues(false, entity.getCustManagerId());
			}
			if (DataUtils.notEmpty(entity.getManagerName())) {
				condsUtils.addProperties(false, "managerName");
				condsUtils.concatValue(new String[] { entity.getManagerName(), "like" });
			}
			if (DataUtils.notEmpty(entity.getIsContract()) && !ConstantCode.NOSELECTED.equals(entity.getIsContract())) {
				condsUtils.addProperties(false, "isContract");
				condsUtils.addValues(false, entity.getIsContract());
			}

			@SuppressWarnings("rawtypes")
			List list = investMemberService.queryEntity(condsUtils.getPropertys(), condsUtils.getValues(), getPageUtil());
			int count = investMemberService.count(condsUtils.getPropertys(), condsUtils.getValues());
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
	private List<Map<String, Object>> fillDataObjectArray(List list) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object[] array = (Object[]) list.get(i);
				InvestMember im = (InvestMember) array[1];
				map.put("id", im.getId());
				map.put("idcard", im.getIdcard());
				map.put("bankCard", im.getBankCard());
				map.put("bank", im.getBank());
				map.put("userImage", im.getUserImage());
				map.put("idcardImg1", im.getIdcardImg1());
				map.put("idcardImg2", im.getIdcardImg2());
				map.put("custManagerId", im.getCustManagerId());
				map.put("managerName", im.getManagerName());
				map.put("isContract", im.getIsContract());
				map.put("investMoney", im.getInvestMoney());
				map.put("investingMoney", im.getInvestingMoney());
				map.put("useableMoney", im.getUseableMoney());
				map.put("accountTotalMoney", im.getAccountTotalMoney());
				map.put("freezeMoney", im.getFreezeMoney());
				map.put("unfreezeMoney", im.getUnfreezeMoney());
				map.put("useableScore", im.getUseableScore());
				map.put("allowOnline", im.getAllowOnline());
				MemberInfo memberInfo = (MemberInfo) array[0];
				map.put("memberInfoId", memberInfo.getId());
				map.put("name", memberInfo.getName());
				map.put("realName", memberInfo.getRealName());
				map.put("nickname", memberInfo.getNickname());
				map.put("email", memberInfo.getEmail());
				map.put("age", memberInfo.getAge());
				map.put("sex", memberInfo.getSex());
				map.put("phone", memberInfo.getPhone());
				map.put("describe", memberInfo.getDescribe());
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
				map.put("handPassword", memberInfo.getHandPassword());
				map.put("isFreeze", memberInfo.getIsFreeze());
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
					jsonObject.put("idcard", im.getIdcard());
					jsonObject.put("bankCard", im.getBankCard());
					jsonObject.put("bank", im.getBank());
					jsonObject.put("userImage", im.getUserImage());
					jsonObject.put("idcardImg1", im.getIdcardImg1());
					jsonObject.put("idcardImg2", im.getIdcardImg2());
					jsonObject.put("custManagerId", im.getCustManagerId());
					jsonObject.put("managerName", im.getManagerName());
					jsonObject.put("isContract", im.getIsContract());
					jsonObject.put("investMoney", im.getInvestMoney());
					jsonObject.put("investingMoney", im.getInvestingMoney());
					jsonObject.put("useableMoney", im.getUseableMoney());
					jsonObject.put("accountTotalMoney", im.getAccountTotalMoney());
					jsonObject.put("freezeMoney", im.getFreezeMoney());
					jsonObject.put("unfreezeMoney", im.getUnfreezeMoney());
					jsonObject.put("useableScore", im.getUseableScore());
					jsonObject.put("allowOnline", im.getAllowOnline());
					MemberInfo memberInfo = im.getMemberInfo();
					jsonObject.put("memberInfo.id", memberInfo.getId());
					jsonObject.put("memberInfo.name", memberInfo.getName());
					jsonObject.put("memberInfo.realName", memberInfo.getRealName());
					jsonObject.put("memberInfo.nickname", memberInfo.getNickname());
					jsonObject.put("memberInfo.email", memberInfo.getEmail());
					jsonObject.put("memberInfo.age", memberInfo.getAge());
					jsonObject.put("memberInfo.sex", memberInfo.getSex());
					jsonObject.put("memberInfo.phone", memberInfo.getPhone());
					jsonObject.put("memberInfo.describe", memberInfo.getDescribe());
					jsonObject.put("memberInfo.userImg", memberInfo.getUserImg());
					jsonObject.put("memberInfo.isEliteAccount", memberInfo.getIsEliteAccount());
					jsonObject.put("memberInfo.type", memberInfo.getType());
					jsonObject.put("memberInfo.level", memberInfo.getLevel());
					jsonObject.put("memberInfo.price", memberInfo.getPrice());
					jsonObject.put("memberInfo.password", memberInfo.getPassword());
					jsonObject.put("memberInfo.isdelete", memberInfo.getIsdelete());
					jsonObject.put("memberInfo.createTime", DateUtils.date2Str(memberInfo.getCreateTime()));
					jsonObject.put("memberInfo.modifyTime", DateUtils.date2Str(memberInfo.getModifyTime()));
					jsonObject.put("memberInfo.createuser", memberInfo.getCreateuser());
					jsonObject.put("memberInfo.modifyuser", memberInfo.getModifyuser());
					jsonObject.put("memberInfo.handPassword", memberInfo.getHandPassword());
					jsonObject.put("memberInfo.isFreeze", memberInfo.getIsFreeze());
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
				if (investMemberService.count("memberInfo.name", entity.getMemberInfo().getName()) > 0) {
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
					entity.getMemberInfo().setIsFreeze(ConstantCode.UNFREEZE);
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

}
