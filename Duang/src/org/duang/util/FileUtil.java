package org.duang.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.duang.common.logger.LoggerUtils;

/**
 * File工具类
 * ClassName: FileUtil 
 * date: 2016年7月15日 下午3:19:21 
 * @author 5y
 */
public class FileUtil {

	/**
	 * 获取文件文本内容
	 * @Title: getUrlCallBackInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param fileurl
	 * @return
	 * @return String 返回类型
	 * @author 5y
	 * @date 2016-7-17 上午10:46:54
	 */
	public static String getUrlCallBackInfo(String fileurl, String charset) {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(fileurl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			InputStream bis = url.openStream();
			StringBuffer s = new StringBuffer();
			if (charset == null || "".equals(charset)) {
				charset = "utf-8";
			}
			String rLine = null;
			BufferedReader bReader = new BufferedReader(new InputStreamReader(bis, charset));
			PrintWriter pw = null;
			FileOutputStream fo = new FileOutputStream("../index.html");
			OutputStreamWriter writer = new OutputStreamWriter(fo, "utf-8");
			pw = new PrintWriter(writer);
			while ((rLine = bReader.readLine()) != null) {
				String tmp_rLine = rLine;
				int str_len = tmp_rLine.length();
				if (str_len > 0) {
					s.append(tmp_rLine);
					pw.println(tmp_rLine);
					pw.flush();
				}
				tmp_rLine = null;
			}
			bis.close();
			pw.close();
			return s.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}


	/**
	 * 将字符串输出到文件中
	 * @Title: wireStringToFile 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 5y
	 * @date 2016-7-17 上午11:07:18
	 */
	public static String  wireStringToFile(String content,String filePath,String fileName){
		if(filePath==null || filePath.equals("")){
			return null;
		}
		BufferedWriter out=null;
		try {
			File uploadFile = new File(filePath);
			if (!uploadFile.exists()) { // 如果文件的路径不存在就创建路径
				uploadFile.mkdirs();
			}
			String file=filePath+ File.separator +fileName;
			out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
			out.write(content);
			out.flush();
			return file;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * 文件上传方法
	 * @author peng
	 * @param file 上传的文件
	 * @param uploadPath 上传的文件路径
	 * @param fileName 双传的文件名称
	 * @author 5y
	 */
	public static void uploadFile(File file,String uploadPath,String fileName){
		try {
			File uploadFile=new File(uploadPath);
			if(!uploadFile.exists()){ //如果文件的路径不存在就创建路径
				uploadFile.mkdirs();
			}
			InputStream bis=new FileInputStream(file);
			OutputStream out=new FileOutputStream(uploadFile+File.separator+fileName);
			byte[] buffer = new byte[2048];
			int temp=0;
			while((temp=bis.read(buffer))!=-1){
				out.write(buffer, 0, temp);
			}
			bis.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 获取文件后缀
	 * @param fileName 文件的名称
	 * @author 5y
	 * @return 文件的后缀名(即格式名称)
	 */
	public static String getSuffix(String fileName){
		if(fileName==null || "".equals(fileName)){
			return "";
		}
		if(fileName.contains(".")){
			String[] temp=fileName.split("\\.");
			return temp[temp.length-1].toLowerCase();
		}
		return null;
	}

	/**
	 * 获取文件的前缀
	 * @Title: getPrefix 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return String    返回类型 
	 * @date 2016-7-17 上午9:58:03
	 * @author 5y
	 */
	public static String getPrefix(String fileName){
		if(fileName==null || "".equals(fileName)){
			return "";
		}
		if(fileName.contains(".")){
			String[] temp=fileName.split("\\.");
			return temp[0].toLowerCase();
		}
		return null;
	}


	/**
	 * 操作properties文件
	 * 将map中的值存在属性文件中
	 * @param map 
	 * @param outFile  生成的目标属性文件
	 */
	public static void storePropertiesToFile(Map<String,Object> map,File outFile){
		try {
			if(!map.isEmpty()){
				if(!outFile.exists()){ //如果目标文件不存在则创建
					outFile.getParentFile().mkdirs();
					outFile.createNewFile();
				}
				//outFile.createNewFile();
				OutputStream out=new FileOutputStream(outFile);
				Properties properties=new Properties();
				Set<String> keys=map.keySet();
				for(String key:keys){
					properties.setProperty(key, map.get(key).toString());
				}
				properties.store(out, "这是是提示");
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 操作properties文件
	 * 获取属性文件中制定的键值
	 * @param key  键名
	 * @param filePath 属性文件的路径
	 * @return
	 */
	public static Object getPropertyValueByKey(String key,String filePath){
		Object value=null;
		try {
			InputStream in=new FileInputStream(filePath);
			Properties properties=new Properties();
			properties.load(in);
			value=properties.getProperty(key);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	} 


	/**
	 * 操作properties文件
	 * 获取属性文件中制定的键值
	 * @param key  键名
	 * @param filePath 属性文件的路径
	 * @return
	 */
	public static Properties getProperties(String filePath){
		Properties properties=null;
		try {
			InputStream in=new FileInputStream(filePath);
			properties=new Properties();
			properties.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	} 


	/**
	 * 将对象写入指定的文件中
	 * @param fileName指定的文件
	 * @param object 对象
	 */
	public static void writeObject(String fileName,Object object){
		File file=new File(fileName);
		FileUtil.writeObject(file, object);
	}


	/**
	 * 将对象写入指定的文件中
	 * @param fileName指定的文件
	 * @param object 对象
	 */
	public static void writeObject(File file,Object object){
		try {
			if(!file.exists()){
				file.getParentFile().mkdirs();
			}
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(object);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 读取指定文件下的对象
	 * @param file 指定的文件
	 * @return
	 */
	public static Object readObject(File file){
		Object obj=null;
		try {
			ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
			obj=objectInputStream.readObject();
			objectInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 读取指定文件下的对象
	 * @param fileName 指定的文件
	 * @return
	 */
	public static Object readObject(String fileName){
		Object obj=null;
		try {
			File file=new File(fileName);
			ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
			obj=objectInputStream.readObject();
			objectInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}


	/**
	 * 获取文件大小
	 * @Title: getFileSizes 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param f
	 * @return
	 * @throws Exception
	 * @return long    返回类型 
	 * @author 5y
	 * @date 2016-7-17 下午2:52:09
	 */
	@SuppressWarnings("resource")
	public static long getFileSizes(File f) {
		try {
			long length = 0;
			if (f.exists()) {
				FileInputStream fis = null;
				fis = new FileInputStream(f);
				length = fis.available();
			} else {
				f.createNewFile();
				LoggerUtils.info("文件不存在", FileUtil.class);
			}
			return length;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}



	@SuppressWarnings("unchecked")
	public static void main(String[] arg0){
		//        	File file=new File("D:\\iReportTemplate\\test.jrxml");
		//        	System.err.println(file.getParentFile());
		//        	Map<String, Object> map=new HashMap<String, Object>();        	
		//        	map.put("name", "haopeng");
		//        	map.put("xingming", "5y");
		//        	File file=new File("D:\\gg\\test.properties");
		//System.out.println(FileUtil.getPropertyValueByKey("xingming", "D:\\gg\\test.properties"));
		//FileUtil.writeObject("D:\\gg\\test.properties", map);
		Map<String,Object> map=(Map<String, Object>) FileUtil.readObject("D:\\gg\\test.properties");
		System.out.println(map);
	}


}