package org.duang.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

public class ImageString {
	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imgFile
	 * @return
	 */
	public static String getImageStr(String imgFile) throws Exception {//
		// String imgFile = "d:\\111.jpg";// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		return Base64.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * 使用Base64解密 生成图片
	 * @Title: generateImageBase64   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param imgStr
	 * @param: @param imgFile
	 * @param: @return
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月17日 下午3:35:33
	 * @return: boolean      
	 * @throws
	 */
	public static boolean generateImageBase64(String imgStr, String imgFile) throws Exception {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		try {
			// Base64解码
			byte[] b = Base64.decode(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = imgFile;// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 不用Base64加密，文件字符串流生成文件
	 * @Title: generateImage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param imgStr
	 * @param: @param imgFile
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年11月17日 下午3:34:49
	 * @return: boolean      
	 * @throws
	 */
	public static boolean generateImage(String imgStr, String imgFile) {
		byte[] data = image2byte(imgStr);
		if (data.length < 3 || imgFile.equals("")){
			return false;
		}
		try {
			FileImageOutputStream imageOutput = new FileImageOutputStream(new File(imgFile));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
			System.out.println("生成图片成功,路径：" + imgFile);
			return true;
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * 将图片字符串生成字节数组
	 * @Title: image2byte   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param path
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年11月17日 下午3:39:25
	 * @return: byte[]      
	 * @throws
	 */
	private static byte[] image2byte(String path) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}

}
