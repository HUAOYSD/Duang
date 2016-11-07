package org.duang.action.provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.duang.action.base.BaseAction;
import org.duang.common.logger.LoggerUtils;
import org.duang.entity.Ad;
import org.duang.enums.If;
import org.duang.service.AdService;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


/**   
 * 接口开发————理财转让Action
 * @ClassName:  AdAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月5日 上午10:54:16      
 */  
@SuppressWarnings("serial")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.NO)
@Namespaces({ @Namespace("/") })
@ParentPackage("provider")
@Action(value = "provider_makeover")
public class MakeOverAction extends BaseAction<Ad>{
	
	
	
}
