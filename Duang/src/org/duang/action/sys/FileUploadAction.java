package org.duang.action.sys;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.Ad;
import org.duang.entity.BindCard;
import org.duang.entity.FileUpload;
import org.duang.entity.MemberInfo;
import org.duang.enums.UploadFile;
import org.duang.service.AdService;
import org.duang.service.BindCardService;
import org.duang.service.MemberInfoService;
import org.duang.util.ConstantCode;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * 
 * 理财用户Action 1.获取文件的新名称 2.上传路径 3.保存到数据库 4.上传
 * 
 * @ClassName: SysInvestMemberAction
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author LiYonghui
 * @date 2016年7月26日 下午1:55:48
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@Action(value = "fileUpload")
@ParentPackage("sys")
@Results(value = { @Result(name = ResultPath.LIST, type = "dispatcher", location = "WEB-INF/page/sys/investmember/investMemberList.jsp"), @Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") })
public class FileUploadAction extends BaseAction<FileUpload> {
	private static final long serialVersionUID = 1L;
	/**
	 * 客户基本信息
	 */
	private MemberInfoService sysMemberInfoService;

	@Resource(name = "sysmemberinfoserviceimpl")
	public void setService(MemberInfoService sysMemberInfoService) {
		this.sysMemberInfoService = sysMemberInfoService;
	}

	/**
	 * 绑定银行卡
	 */
	private BindCardService bindCardService;
	@Resource(name = "bindcardserviceimpl")
	public void setService(BindCardService bindCardService) {
		this.bindCardService = bindCardService;
	}
	
	/**
	 * 广告
	 */
	private AdService adService;
	@Resource(name = "adserviceimpl")
	public void setService(AdService adService) {
		this.adService = adService;
	}
	/**
	 * 上传用户图像或者身份证照片
	 * 
	 * @Title: uploadUserImage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月9日 上午8:43:24
	 * @return: void
	 * @throws
	 */
	public void uploadUserImage() {
		try {
			String userId = getRequest().getParameter("id");
			String deleteFilePath = null;
			if (DataUtils.notEmpty(userId)) {
				String type = getRequest().getParameter("type");
				// 1.获取文件名称
				reSetFileName();
				// 2.获取路径
				String exPath = null;
				MemberInfo memberInfo = sysMemberInfoService.findById(userId);
				if (ConstantCode.upload_user_head.equals(type)) {
					//路径
					exPath = UploadFile.HEAD.getVal(userId);
					deleteFilePath = memberInfo.getUserImg();
					memberInfo.setUserImg(entity.getNewFileName());
				} else if (ConstantCode.upload_user_idcard_1.equals(type)) {
					exPath = UploadFile.IDCARD.getVal(userId);
					deleteFilePath = memberInfo.getIdCardImg1();
					memberInfo.setIdCardImg1(entity.getNewFileName());
				} else if (ConstantCode.upload_user_idcard_2.equals(type)) {
					exPath = UploadFile.IDCARD.getVal(userId);
					deleteFilePath = memberInfo.getIdCardImg2();
					memberInfo.setIdCardImg2(entity.getNewFileName());
				}
				reSetFilePathByUserId(exPath);
				boolean result = sysMemberInfoService.updateEntity(memberInfo);
				if (result) {
					result = upload();
					jsonObject.put("result", true);
					jsonObject.put("msg", "上传成功！");
					if (DataUtils.notEmpty(deleteFilePath)) {
						//上传成功，则需要删除源文件
						deleteFile(deleteFilePath);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("上传用户头像或者身份证照片错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("上传用户头像或者身份证照片错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("result", false);
			jsonObject.put("msg", "上传失败！");
		} finally {
			printJsonResult();
		}
	}

	/**
	 * 上传绑定银行卡号前后照
	 * 
	 * @Title: uploadBindCardImg
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年8月15日 上午8:43:24
	 * @return: void
	 * @throws
	 */
	public void uploadBindCardImg() {
		try {
			String userId = getRequest().getParameter("id");
			String deleteFilePath = null;
			if (DataUtils.notEmpty(userId)) {
				String type = getRequest().getParameter("type");
				// 1.获取文件名称
				reSetFileName();
				// 2.获取路径
				String exPath = UploadFile.BANK.getVal(userId);;
				BindCard bindCard = bindCardService.findById(userId);
				if (ConstantCode.upload_user_idcard_1.equals(type)) {
					deleteFilePath = bindCard.getPhotoImg1();
					bindCard.setPhotoImg1(entity.getNewFileName());
				} else if (ConstantCode.upload_user_idcard_2.equals(type)) {
					deleteFilePath = bindCard.getPhotoImg2();
					bindCard.setPhotoImg2(entity.getNewFileName());
				}
				reSetFilePathByUserId(exPath);
				boolean result = bindCardService.updateEntity(bindCard);
				if (result) {
					result = upload();
					jsonObject.put("result", true);
					jsonObject.put("msg", "上传成功！");
					if (DataUtils.notEmpty(deleteFilePath)) {
						//上传成功，则需要删除源文件
						deleteFile(deleteFilePath);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("上传绑定银行前后照错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("上传绑定银行前后照错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("result", false);
			jsonObject.put("msg", "上传失败！");
		} finally {
			printJsonResult();
		}
	}
	
	/**
	 * 上传广告
	 * @Title: uploadAdImg
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @author LiYonghui
	 * @date 2016年9月5日 上午8:43:24
	 * @return: void
	 * @throws
	 */
	public void uploadAdImg() {
		try {
			String deleteFilePath = null;
				// 1.获取文件名称
				reSetFileName();
				Ad ad = adService.findById(getRequest().getParameter("id"));
				String exPath = UploadFile.AD.getVal();
				deleteFilePath = ad.getImageAddress();
				ad.setImageAddress(entity.getNewFileName());
				 
				reSetFilePathByUserId(exPath);
				boolean result = adService.updateEntity(ad);
				if (result) {
					result = upload();
					jsonObject.put("result", true);
					jsonObject.put("msg", "上传成功！");
					if (DataUtils.notEmpty(deleteFilePath)) {
						//上传成功，则需要删除源文件
						deleteFile(deleteFilePath);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("上传广告图片错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("上传广告图片错误：" + e.getLocalizedMessage(), this.getClass());
			jsonObject.put("result", false);
			jsonObject.put("msg", "上传失败！");
		} finally {
			printJsonResult();
		}
	}
	
	
	/**
	 * 重新命名文件名称,防止上传文件重名现象
	 * 
	 * @Title: getFileName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param userId
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月9日 上午9:54:00
	 * @return: boolean
	 * @throws
	 */
	private boolean reSetFileName() {
		boolean result = false;
		try {
			if (entity.getFile() != null) {
				// 文件的后缀
				String suffix = entity.getFileFileName().substring(entity.getFileFileName().lastIndexOf("."));
				if (entity.getFileFileName().lastIndexOf(".") == -1) {
					result = false;
				}
				// 上传以后,会重命名文件的名称,将其命名为全部是数字的文件名,防止可能出现的乱码.
				// 当然, 只是为了防止出现乱码,一般不会出现乱码
				entity.setNewFileName(DataUtils.randomUUID() + suffix);
				result = true;
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			LoggerUtils.error("上传文件，获取文件名称错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("上传文件，获取文件名称错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return result;
	}

	/**
	 * 根据userId获取上传路径，适用于上传用户的基本信息文件，流水文件不适用。
	 * 
	 * @Title: reSetFilePathByUserId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param userId
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月9日 上午9:57:00
	 * @return: boolean
	 * @throws
	 */
	private boolean reSetFilePathByUserId(String path) {
		boolean result = false;
		try {
			path = getRequest().getSession().getServletContext().getRealPath("/") + UploadFile.PATH.getVal(path);
			entity.setPath(path);
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			LoggerUtils.error("上传文件，获取文件名称错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("上传文件，获取文件名称错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return result;
	}
	
	/**
	 * 根据时间创建文件路径，适用于流水文件
	 * 
	 * @Title: reSetFilePathByTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月9日 上午10:11:16
	 * @return: boolean
	 * @throws
	 *//*
	private boolean reSetFilePathByTime() {
		boolean result = false;
		try {
			String date = DateUtils.getCurrentDate("yyyyMMdd");
			String path = getRequest().getSession().getServletContext().getRealPath("/") + "resources\\file\\flow\\" + date+"\\";
			entity.setPath(path);
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			LoggerUtils.error("上传文件，获取文件名称错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("上传文件，获取文件名称错误：" + e.getLocalizedMessage(), this.getClass());
		}
		return result;
	}
	  */
	/**
	 * 上传文件
	 * 
	 * @Title: upload
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @author LiYonghui
	 * @date 2016年8月9日 上午10:12:35
	 * @return: boolean
	 * @throws
	 */
	private boolean upload() {
		boolean result = false;
		try {
			if (entity.getFile() != null) {
				File savefile = new File(new File(entity.getPath()), entity.getNewFileName());
				// 如果保存的路径不存在,则新建
				if (!savefile.getParentFile().exists()) {
					savefile.getParentFile().mkdirs();
				}
				// 复制文件
				FileUtils.copyFile(entity.getFile(), savefile);
				result = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	private boolean deleteFile(String fileName) {
		fileName=entity.getPath()+fileName;
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
			if (file.exists() && file.isFile()) {
				if (file.delete()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
}
