package org.duang.util; 

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/** 
 * 数据工具类
 * @ClassName: DataUtils 
 * @Description: TODO 
 * @author 5y 
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
	 * 检测List是否不为空
	 * @param list
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(List<?> list){
		return list!=null && list.size()>0;
	}


	/**
	 * 检测List是否为空
	 * @param list
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(List<?> list){
		return list==null || list.size()<1;
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
	 * @author 5y
	 * @date 2016-7-17 下午3:52:49
	 */ 
	public static int str2int(String string){
		try {
			java.math.BigDecimal b = new java.math.BigDecimal(string);
			java.math.BigDecimal one = new java.math.BigDecimal("1");
			return b.divide(one, 0, java.math.BigDecimal.ROUND_HALF_UP).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	/**   
	 * String转成double
	 * @Title: str2double   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param string
	 * @param: @param scale
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年9月12日 下午3:09:43
	 * @return: double      
	 * @throws   
	 */  
	public static double str2double(String string, int scale){
		try {
			if (DataUtils.isEmpty(string)) {
				return 0.0; 
			}else {
				java.math.BigDecimal b = new java.math.BigDecimal(string);
				java.math.BigDecimal one = new java.math.BigDecimal("1");
				return b.divide(one, scale, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
			}
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
	 * @author 5y 
	 * @date 2016-7-17 下午3:46:49
	 * @throws
	 */
	public static String randomUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}


	/**   
	 * 随机六位数字
	 * @Title: sixNumber   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年9月19日 上午11:48:33
	 * @return: String      
	 * @throws   
	 */  
	public static String sixNumber(){
		String string = "";
		Random random = new Random();
		for (int i = 1; i <= 6; i++) {
			string += random.nextInt(10);
		}
		return string;
	}

	/**   
	 * 随机六位数字
	 * @Title: sixNumber   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年9月19日 上午11:48:33
	 * @return: String      
	 * @throws   
	 */  
	public static String getContractNo(int index){
		StringBuffer contractNo = new StringBuffer("BJ-DK-");
		contractNo.append(DateUtils.getCurrentDate("yyyyMMddHHmmss"));
		contractNo.append("-"+String.format("%04d", index));
		return contractNo.toString();
	}

	/**
	 * 字符转换
	 * @Title: ISO2UTF8   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param str
	 * @param: @return
	 * @param: @throws UnsupportedEncodingException  
	 * @author LiYonghui    
	 * @date 2016年11月29日 下午6:53:01
	 * @return: String      
	 * @throws
	 */
	public static String ISO2UTF8(String str) throws UnsupportedEncodingException{
		if(notEmpty(str)){
			return new String(str.getBytes("ISO-8859-1"),"UTF-8");
		}else {
			return "";
		}

	}

	/**
	 * 文件上传路径
	 * @Title: fileUploadBackUpPath   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param basicPath
	 * @param: @param suffPath
	 * @param: @param fileName
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年11月29日 下午6:57:27
	 * @return: String      
	 * @throws
	 */
	public static String fileUploadPath(String basicPath,String suffPath,String fileName){
		String fullpath = basicPath+suffPath;
		// 如果保存的路径不存在,则新建
		File savefile = new File(new File(fullpath),fileName);
		if (!savefile.getParentFile().exists()) {
			savefile.getParentFile().mkdirs();
		}
		fullpath+=fileName;
		return fullpath;
	}
	
	/**
	  * 传入文件名以及字符串, 将字符串信息保存到文件中
	  *
	  * @param strFilename
	  * @param strBuffer
	  */
	  public static void textToFile(final String strFilename, final String strBuffer)
	  {
		  try
		  {
			  // 创建文件对象
			  File fileText = new File(strFilename);
			  // 向文件写入对象写入信息
			  FileWriter fileWriter = new FileWriter(fileText);
			  // 写文件
			  fileWriter.write(strBuffer);
			  // 关闭
			  fileWriter.close();
		  }
		  catch (IOException e)
		  {
			  //
			  e.printStackTrace();
		  }
	  }
	
}

