package org.duang.action.provider;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.entity.FileUpload;
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
		System.out.println(entity);
		org.apache.commons.fileupload.FileUpload fileUpload= null;
		File[] file = entity.getFile();
		if (file!=null) {
		}
		//创建一个临时文件存放要上传的文件，第一个参数为上传文件大小，第二个参数为存放的临时目录  
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024*5,new File("D:\\temp1"));  
		// 设置缓冲区大小为 5M  
		factory.setSizeThreshold(1024 * 1024 * 5);  
		// 创建一个文件上传的句柄  
		ServletFileUpload upload = new ServletFileUpload(factory);  
		//设置上传文件的整个大小和上传的单个文件大小  
		upload.setSizeMax(1024*1024*50);  
		upload.setFileSizeMax(1024*1024*5);  
		String[] fileExts = {"doc","zip","rar","jpg","txt", "png"};  
//		try { 
//		} catch (FileUploadBase.SizeLimitExceededException e) {   
//			System.out.println("整个请求的大小超过了规定的大小...");   
//		} catch (FileUploadBase.FileSizeLimitExceededException e) {   
//			System.out.println("请求中一个上传文件的大小超过了规定的大小...");   
//		} catch (FileUploadException e) {  
//			e.printStackTrace();   
//		}  
	}

}
