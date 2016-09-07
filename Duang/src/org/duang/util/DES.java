package org.duang.util;
import java.security.SecureRandom;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import org.duang.common.logger.LoggerUtils;
import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * DES加密解密
 */
public class DES {
	
	private DES(){
	}
	
	/** 
	 * DES算法密钥 
	 */  
	private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };  

	/** 
	 * 数据加密
	 * @param data 
	 * @return 加密后的数据 
	 */  
	public static String encryptBasedDes(String data) throws Exception {  
		String encryptedData = null;  
		try {  
			// DES算法要求有一个可信任的随机数源  
			SecureRandom sr = new SecureRandom();  
			DESKeySpec deskey = new DESKeySpec(DES_KEY);  
			//创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
			SecretKey key = keyFactory.generateSecret(deskey);  
			//加密对象  
			Cipher cipher = Cipher.getInstance("DES");  
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);  
			//加密，并把字节数组编码成字符串  
			encryptedData = new BASE64Encoder().encode(cipher.doFinal(data.getBytes()));  
		} catch (Exception e) {  
			LoggerUtils.error("DES加密错误", DES.class);
			throw new RuntimeException("加密错误，错误信息：", e);  
		}  
		return encryptedData;  
	}  


	/** 
	 * 数据解密
	 * @return 解密后的数据 
	 */  
	public static String decryptBasedDes(String cryptData) throws Exception{  
		String decryptedData = null;  
		try {  
			// DES算法要求有一个可信任的随机数源  
			SecureRandom sr = new SecureRandom();  
			DESKeySpec deskey = new DESKeySpec(DES_KEY);  
			//创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
			SecretKey key = keyFactory.generateSecret(deskey);  
			//解密对象  
			Cipher cipher = Cipher.getInstance("DES");  
			cipher.init(Cipher.DECRYPT_MODE, key, sr);  
			//把字符串解码为字节数组，并解密  
			decryptedData = new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(cryptData)));  
		} catch (Exception e) {  
			LoggerUtils.error("DES解密错误", DES.class);
			throw new RuntimeException("解密错误，错误信息：", e);  
		}  
		return decryptedData;  
	}  


	/*public static void main(String args[])  throws Exception{
		String str="华澳融信huao123.,!@#$%^&*()[]{}";  
		//加密  
		String string = encryptBasedDes(str);  
		System.out.println(string);  
		//解密  
		String s2=decryptBasedDes(string);  
		System.err.println(s2);  
	}*/


}