package org.duang.action.provider;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.common.system.MemberCollection;
import org.duang.enums.UploadFile;
import org.duang.util.DataUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————上传借款申请资料Action
 * @ClassName:  ApplyUploadAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_applyUpload")
public class ApplyUploadAction extends BaseAction<Object>{

	

	/**   
	 * 上传借贷申请信息
	 * @Title: uploadApplyInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param:   
	 * @author 5y    
	 * @date 2016年9月8日 下午2:49:21
	 * @return: void      
	 * @throws   
	 */  
	public void uploadApplyInfo(){
		boolean success = false;
		try {
			String token = getRequest().getParameter("token");
			String id = "";
			//判断参数是否为空
			if(DataUtils.notEmpty(token) && DataUtils.notEmpty(id = MemberCollection.getInstance(token).getMainField(token))){
				Map<String,Object>  pathMap = new HashMap<String,Object>();
				pathMap.put("salarycheck", UploadFile.PATH.getVal(UploadFile.SALARY.getVal(id)));
				pathMap.put("personcheck", UploadFile.PATH.getVal(UploadFile.IDCARD.getVal(id)));
				jsonObject.put("result", pathMap);
				success = true;
			}else{
				msg = "token无效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ApplyUploadAction——uploadApplyInfo方法错误：" + e.getMessage(), this.getClass());
			LoggerUtils.error("ApplyUploadAction——uploadApplyInfo方法错误：" + e.getLocalizedMessage(), this.getClass());
			msg = "服务器维护，请稍后再试";
		}
		jsonObject.put("msg", msg);
		jsonObject.put("success", success);
		printJsonResult();
	}
	
}
