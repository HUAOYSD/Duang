package org.duang.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/** 
 * MD5加密
 * @ClassName: MD5Utils 
 * @Description: TODO 
 * @author 5y 
 * @date 2016-7-17 下午12:35:36 
 */
public class MD5Utils {

	/**
	 * 使用md5的算法进行加密
	 */
	public static String md51(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	/*public static void main(String[] args) {
		System.out.println(md5("777777"));
	}*/

}