package org.duang.common.logger;
import org.apache.log4j.Logger;


/**
 * 定义一个Logger全局类
 * @author 5y
 */
public class LoggerUtils {

	private static Logger logger = null;

	private LoggerUtils(){

	}

	/**   
	 * 获取Logger对象
	 * @Title: getLogger   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param clazz
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月25日 上午11:17:13
	 * @return: Logger      
	 * @throws   
	 */  
	private static Logger getLogger(Class<?> clazz){
		if (clazz != null) {
			logger = Logger.getLogger(clazz);
		} else {
			logger = Logger.getLogger(LoggerUtils.class);
		}
		return logger;
	}


	/**
	 * debug记录
	 * debug(这里用一句话描述这个方法的作用)
	 * @Title: debug
	 * @Description: TODO
	 * @param @param message    
	 * @return void    返回类型
	 * @author 5y 
	 * @date 2016-7-16下午4:52:13
	 * @throws
	 */
	public static void debug(String message, Class<?> clazz){
		getLogger(clazz).debug(message);
	}


	/**
	 * info记录
	 * info(这里用一句话描述这个方法的作用)
	 * @Title: info
	 * @Description: TODO
	 * @param @param message    
	 * @return void    返回类型
	 * @author 5y 
	 * @date 2016-7-16 下午4:52:13
	 * @throws
	 */
	public static void info(String message, Class<?> clazz){
		getLogger(clazz).info(message);
	}


	/**
	 * warn记录
	 * warn(这里用一句话描述这个方法的作用)
	 * @Title: warn
	 * @Description: TODO
	 * @param @param message    
	 * @return void    返回类型
	 * @author 5y 
	 * @date 2016-7-16 下午4:52:13
	 * @throws
	 */
	public static void warn(String message, Class<?> clazz){
		getLogger(clazz).warn(message);
	}


	/**
	 * error记录
	 * error(这里用一句话描述这个方法的作用)
	 * @Title: error
	 * @Description: TODO
	 * @param @param message    
	 * @return void    返回类型
	 * @author 5y 
	 * @date 2016-7-16 下午4:52:13
	 * @throws
	 */
	public static void error(String message, Class<?> clazz){
		getLogger(clazz).error(message);
	}


	/**
	 * fatal记录
	 * fatal(这里用一句话描述这个方法的作用)
	 * @Title: fatal
	 * @Description: TODO
	 * @param @param message    
	 * @return void    返回类型
	 * @author 5y 
	 * @date 2016-7-16 下午4:52:13
	 * @throws
	 */
	public static void fatal(String message, Class<?> clazz){
		getLogger(clazz).fatal(message);
	}
}
