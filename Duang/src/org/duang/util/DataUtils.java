package org.duang.util; 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/** 
 * 数据工具类
 * @ClassName: DataUtils 
 * @Description: TODO 
 * @author 白攀 
 * @date 2016-7-17 下午3:28:12 
 */
public class DataUtils {
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s.trim()) && !"null".equals(s.trim());
	}

	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s.trim()) || "null".equals(s.trim());
	}

	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}


	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			return null;
		}
	}


	/** 
	 * string转换成int
	 * @Title: str2int 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param string
	 * @return
	 * @return int    返回类型 
	 * @author 白攀
	 * @date 2016-7-17 下午3:52:49
	 */ 
	public static int str2int(String string){
		try {
			return Integer.parseInt(string);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	/**
	 * 获取一个UUID
	 * randomUUID(这里用一句话描述这个方法的作用)
	 * @Title: randomUUID
	 * @Description: TODO
	 * @param @return    
	 * @return String    返回类型
	 * @author 白攀 
	 * @date 2016-7-17 下午3:46:49
	 * @throws
	 */
	public static String randomUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}


	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println(UUID.randomUUID().toString());
			System.out.println(UUID.randomUUID().toString().length());
		}
	}
}

