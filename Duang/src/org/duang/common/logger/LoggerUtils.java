package org.duang.common.logger;
import org.apache.log4j.Logger;


/**
 * 定义一个Logger全局类
 * @author 白攀
 */
public class LoggerUtils {

	private static Logger logger = Logger.getLogger(LoggerUtils.class);
	
	
	/**
	 * debug记录
	 * debug(这里用一句话描述这个方法的作用)
	 * @Title: debug
	 * @Description: TODO
	 * @param @param message    
	 * @return void    返回类型
	 * @author 白攀 
	 * @date 2016-7-16下午4:52:13
	 * @throws
	 */
	public static void debug(String message){
		logger.debug(message);
	}
	

	/**
	 * info记录
	 * info(这里用一句话描述这个方法的作用)
	 * @Title: info
	 * @Description: TODO
	 * @param @param message    
	 * @return void    返回类型
	 * @author 白攀 
	 * @date 2016-7-16 下午4:52:13
	 * @throws
	 */
	public static void info(String message){
		logger.info(message);
	}
	
	
	/**
	 * warn记录
	 * warn(这里用一句话描述这个方法的作用)
	 * @Title: warn
	 * @Description: TODO
	 * @param @param message    
	 * @return void    返回类型
	 * @author 白攀 
	 * @date 2016-7-16 下午4:52:13
	 * @throws
	 */
	public static void warn(String message){
		logger.warn(message);
	}
	
	
	/**
	 * error记录
	 * error(这里用一句话描述这个方法的作用)
	 * @Title: error
	 * @Description: TODO
	 * @param @param message    
	 * @return void    返回类型
	 * @author 白攀 
	 * @date 2016-7-16 下午4:52:13
	 * @throws
	 */
	public static void error(String message){
		logger.error(message);
	}
}
