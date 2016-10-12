package org.duang.util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**   
 * 自定义Base64编码与解码
 * @ClassName:  Base64   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月8日 上午9:51:09      
 */  
public class Base64 {

	private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789A/".toCharArray();

	/**   
	 * 编码
	 * @Title: encode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param data
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年9月8日 上午9:52:38
	 * @return: String      
	 * @throws   
	 */  
	public static String encode(byte[] data) throws Exception{
		int start = 0;
		int len = data.length;
		StringBuffer buf = new StringBuffer(data.length * 3 / 2);
		int end = len - 3;
		int i = start;
		int n = 0;
		while (i <= end) {
			int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 0x0ff) << 8) | (((int) data[i + 2]) & 0x0ff);
			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append(legalChars[d & 63]);
			i += 3;
			if (n++ >= 14) {
				n = 0;
				buf.append(" ");
			}
		}
		if (i == start + len - 2) {
			int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 255) << 8);
			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append("=");
		} else if (i == start + len - 1) {
			int d = (((int) data[i]) & 0x0ff) << 16;
			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append("==");
		}
		return buf.toString();
	}


	/**   
	 * 解码
	 * @Title: decode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param s
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年9月8日 上午9:53:03
	 * @return: byte[]      
	 * @throws   
	 */  
	public static byte[] decode(String s) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			decode(s, bos);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		byte[] decodedBytes = bos.toByteArray();
		bos.close();
		bos = null;
		return decodedBytes;
	}


	/**   
	 * 写入输出流
	 * @Title: decode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param s
	 * @param: @param os
	 * @param: @throws IOException  
	 * @author 5y    
	 * @date 2016年9月8日 上午9:53:37
	 * @return: void      
	 * @throws   
	 */  
	private static void decode(String s, OutputStream os) throws Exception {
		int i = 0;
		int len = s.length();
		while (true) {
			while (i < len && s.charAt(i) <= ' ')
				i++;
			if (i == len)
				break;
			int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12)	+ (decode(s.charAt(i + 2)) << 6) + (decode(s.charAt(i + 3)));
			os.write((tri >> 16) & 255);
			if (s.charAt(i + 2) == '=')
				break;
			os.write((tri >> 8) & 255);
			if (s.charAt(i + 3) == '=')
				break;
			os.write(tri & 255);
			i += 4;
		}
	}


	/**   
	 * 字符排位
	 * @Title: decode   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param c
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年9月8日 上午9:54:06
	 * @return: int      
	 * @throws   
	 */  
	private static int decode(char c) throws Exception{
		if (c >= 'A' && c <= 'Z')
			return ((int) c) - 65;
		else if (c >= 'a' && c <= 'z')
			return ((int) c) - 97 + 26;
		else if (c >= '0' && c <= '9')
			return ((int) c) - 48 + 26 + 26;
		else
			switch (c) {
			case '+':
				return 62;
			case '/':
				return 63;
			case '=':
				return 0;
			default:
				throw new RuntimeException("unexpected code: " + c);
			}
	}
}
