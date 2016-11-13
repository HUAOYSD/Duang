package org.duang.action.provider;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.entity.FileUpload;
import org.duang.entity.MemberInfo;
import org.duang.enums.UploadFile;
import org.duang.service.MemberInfoService;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————上传
 * @ClassName:  UploadAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_upload")
public class UploadAction extends BaseAction<FileUpload>{
	
	/**
	 * 客户基本信息
	 */
	private MemberInfoService sysMemberInfoService;

	@Resource(name = "sysmemberinfoserviceimpl")
	public void setService(MemberInfoService sysMemberInfoService) {
		this.sysMemberInfoService = sysMemberInfoService;
	}
	
	/**
	 * 上传文件头像
	 * @Title: uploadMemberHeadImg   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年10月24日 下午6:05:12
	 * @return: void      
	 * @throws
	 */
	public void uploadMemberHeadImg(){
		boolean success = false;
		try {
			String userId = getRequest().getParameter("id");
			if (DataUtils.notEmpty(userId)) {
				// 1.获取文件名称
				reSetFileName();
				MemberInfo memberInfo = sysMemberInfoService.findById(userId);
				memberInfo.setUserImg(entity.getNewFileName());
				setUploadPathByType(userId);
				String temPath = getRequest().getSession().getServletContext().getRealPath("/");
				entity.setPath(temPath+UploadFile.PATH.getVal(UploadFile.HEAD.getVal(userId)));
				success = sysMemberInfoService.updateEntity(memberInfo);
				if (success) {
					success = upload();
					msg="设置成功";
					jsonObject.put("path", UploadFile.PATH.getVal(UploadFile.HEAD.getVal(userId))+entity.getNewFileName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("UploadAction——uploadMemberHeadImg方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("UploadAction——uploadMemberHeadImg方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}

	/**   
	 * ios上传
	 * @Title: uploadFileByIOS   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月26日 上午9:59:52
	 * @return: void      
	 * @throws   
	 */  
	public void uploadFileByIOS(){
		
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = "";
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))){
				int num = Integer.parseInt(getRequest().getParameter("num"));
				//现将文件放到缓冲区
				ServletFileUpload upload = initUpload(num);
				//获取文件
				@SuppressWarnings("unchecked")
				List<FileUpload> list = (List<FileUpload>)upload.parseRequest(getRequest());
				for(FileUpload fileUpload : list){
					entity = fileUpload;
				    //设置文件名称
					reSetFileName();
					//设置文件路径
					setUploadPathByType(id);
				    success = upload();
				    if(success){
						Map<String,Object>  pathMap = new HashMap<String,Object>();
						pathMap.put("house", UploadFile.PATH.getVal(UploadFile.HOUSE.getVal(id)));
						pathMap.put("idcard", UploadFile.PATH.getVal(UploadFile.IDCARD.getVal(id)));
						pathMap.put("car", UploadFile.PATH.getVal(UploadFile.CAR.getVal(id)));
						jsonObject.put("path", pathMap);
				    }
				}
			}else{
				msg = "token失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("UploadAction——uploadFileByIOS方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("UploadAction——uploadFileByIOS方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**   
	 * android上传
	 * @Title: uploadFileByAndroid   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月26日 上午9:59:52
	 * @return: void      
	 * @throws   
	 */  
	public void uploadFileByAndroid(){
		
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = "";
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token,sysMemberInfoService).getMainField(token))){
				int num = Integer.parseInt(getRequest().getParameter("num"));
				//现将文件放到缓冲区
				ServletFileUpload upload = initUpload(num);
				//获取文件
				@SuppressWarnings("unchecked")
				List<FileUpload> list = (List<FileUpload>)upload.parseRequest(getRequest());
				for(FileUpload fileUpload : list){
					entity = fileUpload;
				    //设置文件名称
					reSetFileName();
					//设置文件路径
					setUploadPathByType(id);
				    success = upload();
				    if(success){
						Map<String,Object>  pathMap = new HashMap<String,Object>();
						pathMap.put("house", UploadFile.PATH.getVal(UploadFile.HOUSE.getVal(id)));
						pathMap.put("idcard", UploadFile.PATH.getVal(UploadFile.IDCARD.getVal(id)));
						pathMap.put("car", UploadFile.PATH.getVal(UploadFile.CAR.getVal(id)));
						jsonObject.put("path", pathMap);
				    }
				}
			}else{
				msg = "token失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("UploadAction——uploadFileByAndroid方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("UploadAction——uploadFileByAndroid方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
	/**
	 * 初始化上传文件，将文件上传到缓冲区
	 * @Title: initUpload   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param num
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月26日 下午3:28:49
	 * @return: ServletFileUpload      
	 * @throws
	 */
	private ServletFileUpload initUpload(int num){
		//上传路径
		String temPath = getRequest().getSession().getServletContext().getRealPath("/")+UploadFile.TEM.getVal();
		
		//创建一个临时文件存放要上传的文件，第一个参数为上传文件大小，第二个参数为存放的临时目录  
		DiskFileItemFactory factory = new DiskFileItemFactory();  
		// 设置缓冲区大小为 5M  
		factory.setSizeThreshold(1024 * 1024 * 5 * num);  
		factory.setRepository(new File(temPath)); 
		// 创建一个文件上传的句柄  
		ServletFileUpload upload = new ServletFileUpload(factory);  
		return upload;
	}
	
	/**
	 * 获取上传路径path
	 * @Title: getUploadPathByType   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param id
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年9月26日 下午3:15:51
	 * @return: String      
	 * @throws
	 */
	private void setUploadPathByType(String id){
		String temPath = getRequest().getSession().getServletContext().getRealPath("/");
		String type = getRequest().getParameter("type");
		if(type.equals("idcard")){
			temPath = temPath+UploadFile.PATH.getVal(UploadFile.IDCARD.getVal(id));
		}else if(type.equals("house")){
			temPath = temPath+UploadFile.PATH.getVal(UploadFile.HOUSE.getVal(id));
		}else if(type.equals("car")){
			temPath = temPath+UploadFile.PATH.getVal(UploadFile.CAR.getVal(id));
		}
		entity.setPath(temPath);
		
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

}
