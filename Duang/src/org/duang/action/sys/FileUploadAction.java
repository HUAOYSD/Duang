package org.duang.action.sys;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.duang.action.base.BaseAction;
import org.duang.common.ResultPath;
import org.duang.entity.FileUpload;
import org.duang.util.ConstantCode;
import org.duang.util.DataUtils;
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
@Action(value = "fileUpload")
@ParentPackage("sys")
@Results(value = { 
		@Result(name = ResultPath.LIST, type = "dispatcher", location = "WEB-INF/page/sys/investmember/investMemberList.jsp"),
		@Result(name = com.opensymphony.xwork2.Action.ERROR, type = "dispatcher", location = "error.jsp") 
		})
public class FileUploadAction extends BaseAction<FileUpload> {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	/**
	 * 上传用户图像或者身份证照片
	 * @Title: uploadUserImage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author LiYonghui    
	 * @date 2016年8月9日 上午8:43:24
	 * @return: void      
	 * @throws
	 */
	public void uploadUserImage() {  
		String userId = getRequest().getParameter("id");
		if(DataUtils.notEmpty(userId)){
			String path = getRequest().getSession().getServletContext().getRealPath("/")+"WEB-INF\\file\\basic\\"+userId;
			String type = getRequest().getParameter("type");
			if(ConstantCode.upload_user_head.equals(type)){
				
			}else if(ConstantCode.upload_user_idcard_1.equals(type)){
				
			}else if(ConstantCode.upload_user_idcard_2.equals(type)){
				
			}
		}
    }

	@SuppressWarnings("finally")
	private boolean  upload(String path) {
		boolean isSuccess = false;
		try { 
	        if (entity.getFile() != null) {  
	            //文件的后缀  
	            String suffix = entity.getFileFileName().substring(entity.getFileFileName()  
	                    .lastIndexOf("."));  
	            if (entity.getFileFileName().lastIndexOf(".") == -1) {  
	            	jsonObject.put("result", false);
					jsonObject.put("msg", "上传失败");
	            }  
	            //上传以后,会重命名文件的名称,将其命名为全部是数字的文件名,防止可能出现的乱码.  
	            //当然, 只是为了防止出现乱码,一般不会出现乱码  
	            entity.setNewFileName(DataUtils.randomUUID()+suffix);   
	            File savefile = new File(new File(path), entity.getNewFileName());  
	            //如果保存的路径不存在,则新建  
	            if (!savefile.getParentFile().exists())
	            {  
	                savefile.getParentFile().mkdirs();  
	            }
                //复制文件  
                FileUtils.copyFile(entity.getFile(), savefile); 
                isSuccess =  true;
	        }
        } catch (IOException e) {  
                e.printStackTrace();  
                isSuccess =  false; 
        }finally {
    			return isSuccess;
    	}  
     }
}
