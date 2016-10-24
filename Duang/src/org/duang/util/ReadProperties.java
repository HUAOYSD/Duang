package org.duang.util;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {  
	
	/**
	 * 初始化
	 * @Title: initPrperties   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param filePath
	 * @param: @throws IOException  
	 * @author LiYonghui    
	 * @date 2016年10月21日 上午11:48:26
	 * @return: void      
	 * @throws
	 */
	public static Properties initPrperties(String filePath) throws IOException{
		Properties props = null;
		if(props==null){
			props = new Properties(); 
			props.load(ReadProperties.class.getClassLoader().getResourceAsStream(filePath));
		}
		return props;
	}
	
	/**
	 * 获取指定文件和key的value
	 * @Title: getStringValue   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param filePath 文件地址
	 * @param: @param key      key
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年10月18日 上午11:13:31
	 * @return: String      
	 * @throws
	 */
	public static String getStringValue(Properties props,String key){
        return props.getProperty(key);
	}
	
   /* public static void main(String[] args) throws Exception{  
        //String filename = "com/lyh/xml/read.properties";  
        String filename = "WEB-INF/config/read.properties"; 
        System.out.println(getStringValue(filename,"name")); 
          
    }  */
}  