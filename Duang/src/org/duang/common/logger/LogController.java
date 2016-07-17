package org.duang.common.logger;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.duang.annotation.ServiceLog;
import org.duang.util.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;


/** 
 * 日志拦截器
 * @ClassName: PermissionsInterceptor 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 白攀
 * @date 2016-7-15 上午11:08:43 
 */ 
public class LogController {

	/** 
	 * 记录日志方法
	 * @Title: methodCacheHold 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param joinPoint
	 * @throws Throwable
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-16 下午1:37:08
	 */ 
	public Object methodCacheHold(ProceedingJoinPoint joinPoint) throws Throwable {
		String methoName = joinPoint.getSignature().getName(); 	// 方法名
		String[] contents = this.getMthodRemark(joinPoint);
		//msg = DateUtils.getCurrentDate("yyyy年MM月dd日 HH时mm分ss秒")+"执行方法'"+methoName+"',警告：此方法为不明方法";
		String msg = DateUtils.getCurrentDate("yyyy年MM月dd日 HH时mm分ss秒")+"执行方法——"+joinPoint.getTarget().getClass()+"."+methoName+"[方法序号："+contents[1]+"；类型："+contents[2]+"；所属模块："+contents[0]+"]";
		LoggerUtils.info(msg);
		return joinPoint.proceed();//当做继续的意思
	}


	/**
	 * 获取类的中文备注____用于记录用户的操作日志描述  
	 * getMthodRemark(这里用一句话描述这个方法的作用)
	 * @Title: getMthodRemark
	 * @Description: TODO
	 * @param @param joinPoint
	 * @param @return
	 * @param @throws Exception    
	 * @return String[]    返回类型
	 * @author 白攀 
	 * @date 2016-7-16 下午2:21:28
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public String[] getMthodRemark(ProceedingJoinPoint joinPoint) throws Exception {  
		Class<? extends Object> targetClass = joinPoint.getTarget().getClass();  
		String methodName = joinPoint.getSignature().getName();  
		Object[] arguments = joinPoint.getArgs();  
		// 组装日志数据
		String methode[] = {"","",""}; 
		// 获得操作模块名称
		// 获得service的所有注解
		Annotation[] annos = targetClass.getAnnotations();
		for(Annotation a : annos){
			// 如果 注解是ServiceLog
			if(a instanceof ServiceLog){
				// 获得操作模块名称
				methode[0] = ((ServiceLog) a).ModelName();
			}
		}
		Method[] method = targetClass.getMethods();  
		for (Method m : method) {  
			if (m.getName().equals(methodName)) {  
				Class[] tmpCs = m.getParameterTypes();  
				if (tmpCs.length == arguments.length) {  
					LogOprationUtil[] logOprationUtils = LogOprationUtil.values();
					for (LogOprationUtil temp : logOprationUtils) {
						if (temp.isStart(methodName)) {
							methode[1] = temp.getNum();
							methode[2] = temp.getType();
							break;
						}
					}
					break;
				}  
			}  
		}  
		methode[0] = ("").equals(methode[0]) ? "未知模块" : methode[0];
		methode[1] = ("").equals(methode[1]) ? "未知编号" : methode[1];
		methode[2] = ("").equals(methode[2]) ? "未知类型" : methode[2];
		return methode;  
	}




}
