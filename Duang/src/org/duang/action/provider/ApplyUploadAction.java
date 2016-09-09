package org.duang.action.provider;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————上传借款申请资料Action
 * @ClassName:  ApplyUploadAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
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
	 * @author 白攀    
	 * @date 2016年9月8日 下午2:49:21
	 * @return: void      
	 * @throws   
	 */  
	public void uploadApplyInfo(){
		//文件命名规则是时间（20160815093055）+memberid
		//存的申请资料图片我们先放到一个临时目录，在保存申请借贷订单的时候，再移动到指定位置
	}
	
}
