package org.duang.util;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

/**
 * DES加密解密
 */
public class DES {

	private DES(){
	}

	private static final byte[] IV = {9, 3, 1, 2, 7, 0, 8, 6};
	private static final String DES_KEY = "o!20&^/@";

	/**   
	 * 加密
	 * @Title: encryptDES   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param encryptString
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月8日 上午9:58:15
	 * @return: String      
	 * @throws   
	 */  
	public static String encryptDES(String encryptString) throws Exception {
		if(DataUtils.notEmpty(encryptString)){
			IvParameterSpec zeroIv = new IvParameterSpec(IV);
			SecretKeySpec key = new SecretKeySpec(DES_KEY.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
			return Base64.encode(encryptedData);
		}else{
			return null;
		}
	}


	/**   
	 * 解密
	 * @Title: decryptDES   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param decryptString
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月8日 上午9:58:36
	 * @return: String      
	 * @throws   
	 */  
	public static String decryptDES(String decryptString) throws Exception {
		if (DataUtils.isEmpty(decryptString)) {
			return "";
		}
		if (decryptString.startsWith("\"") && decryptString.endsWith("\"")) {
			decryptString = decryptString.substring(1, decryptString.length()-1);
		}
		byte[] byteMi = Base64.decode(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(IV);
		SecretKeySpec key = new SecretKeySpec(DES_KEY.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData);
	}
	
	/**
	 * 将二进制转换成16进制 
	 * @param buf 
	 * @return  String
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < buf.length; i++) {  
			String hex = Integer.toHexString(buf[i] & 0xFF);  
			if (hex.length() == 1) {  
				hex = '0' + hex;  
			}  
			sb.append(hex.toUpperCase());  
		}  
		return sb.toString();  
	}

	@Test
	public void Test(){

	}

	public static void main(String[] args) {
		try {
			//System.out.println(encryptDES("1590060455622"));
			//System.out.println(decryptDES("/TmnW/VByEOrQcuH4IprC9gmmuKCrkpcfOKA1W4b0gGYZD2NWA2b5RA"));
			System.out.println(decryptDES("\"fKd7bnlvQDkheG1r8sJITQ==\""));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/*  
	private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };  //DES算法密钥 


	public static void main(String args[])  throws Exception{
		System.out.println(new String(DES_KEY,"ISO-8859-1"));
		String str="华澳融信huao123.,!@#$%^&*()[]{}";  
		//加密  
		String string = encryptBasedDes(str);  
		System.out.println(string);  
		//解密  
		String s2=decryptBasedDes(string);  
		System.out.println(s2);  

		String base64 = new BASE64Encoder().encode("".getBytes());  
		System.out.println(base64);

		String base64jiema = new String(new BASE64Decoder().decodeBuffer("cS8hweL2a0miWNfFgEWmdUClZnzx5UmOywegAyUesJLbokLRKHxvNA=="));
		System.out.println(base64jiema);
	}


	 *//** 
	 * 数据加密
	 * @param data 
	 * @return 加密后的数据 
	 *//*  
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
			StringBuffer sfBuffer = new StringBuffer(new String(cipher.doFinal(data.getBytes())));
			System.out.println("加密结果（未Base64编码前的结果）\t"+sfBuffer.toString());
			//加密，并把字节数组编码成字符串  
			encryptedData = new BASE64Encoder().encode(cipher.doFinal(data.getBytes()));  
		} catch (Exception e) {  
			LoggerUtils.error("DES加密错误", DES.class);
			throw new RuntimeException("加密错误，错误信息：", e);  
		}  
		return encryptedData;  
	}  


	  *//** 
	  * 数据解密
	  * @return 解密后的数据 
	  *//*  
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
	}  */
}