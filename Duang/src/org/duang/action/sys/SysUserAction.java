package org.duang.action.sys;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 单词控制类 
 * @ClassName: EnglishAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 白攀
 * @date 2014-8-21 下午2:07:35 
 *  
 */ 
@Scope(value="prototype",proxyMode=ScopedProxyMode.NO)
@Controller
@RequestMapping("/english")
public class SysUserAction{
	
}
