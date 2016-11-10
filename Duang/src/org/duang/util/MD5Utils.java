package org.duang.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


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
	
	public static String hmacSign(String aValue, String aKey) {
		byte k_ipad[] = new byte[64];  
		byte k_opad[] = new byte[64];  
		byte keyb[];  
		byte value[];  
		try {  
		     keyb = aKey.getBytes("utf-8");  
		     value = aValue.getBytes("utf-8");  
		} catch (UnsupportedEncodingException e) {  
		     keyb = aKey.getBytes();  
		     value = aValue.getBytes();  
		}  
		Arrays.fill(k_ipad, keyb.length, 64, (byte) 54); 
		Arrays.fill(k_opad, keyb.length, 64, (byte) 92); 
		for (int i = 0; i < keyb.length; i++) {  
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);  
		    k_opad[i] = (byte) (keyb[i] ^ 0x5c);  
		}  
		MessageDigest md = null;  
		try {  
		   	md = MessageDigest.getInstance("MD5");  
		} catch (NoSuchAlgorithmException e) {  
		    return null;  
		}  
		md.update(k_ipad);  
		md.update(value);  
		byte dg[] = md.digest();  
		md.reset();  
		md.update(k_opad);  
		md.update(dg, 0, 16);  
		dg = md.digest();  
		return toHex(dg);  
	} 

	public static String toHex(byte input[]) {  
		if (input == null)  
			return null;  
		StringBuffer output = new StringBuffer(input.length * 2);  
		for (int i = 0; i < input.length; i++) {  
		  	int current = input[i] & 255;  
		 	if (current < 16)  
		      output.append("0");  
		    output.append(Integer.toString(current, 16)); 
		}  
		return output.toString();  
	}  
	
	public static void main(String[] args) {
		String idString="PFT0000723ff3e951ec425c9809f94ab838905ffbm000004d46b080bffae4c88b5baf3877226c0a41韩玉昆51070919840824824813996523694http://219.143.6.68:8080/Duang/provider_wallet!depositFFCallBack.dohttp://219.143.6.68:8080/Duang/provider_member!realNameAuthCallback.do";
		System.out.println(hmacSign(idString,"RaeWUYmPbjfLAQ8dU9itzMA78eULQuHF"));
	}
}