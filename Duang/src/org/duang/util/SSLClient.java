package org.duang.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;

public class SSLClient{
	
	
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
	
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}
	
	/**
	 * 远程访问https URL,获取JSONObject对象
	 * @Title: getJsonObjectByUrl   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param url
	 * @param: @throws Exception  
	 * @author LiYonghui    
	 * @date 2016年11月2日 下午5:51:04
	 * @return: void      
	 * @throws
	 */
	public static JSONObject getJsonObjectByUrl(String url,Map<String, String> map,String charset) throws Exception{
		OutputStream outputStream = null;
		URL reqURL = new URL(url); //创建URL对象
		//创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = {new TrustAnyTrustManager()};
		SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		//从上述SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		//创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
		HttpsURLConnection httpsConn = (HttpsURLConnection)reqURL.openConnection();
		httpsConn.setRequestMethod("POST");
		
		StringBuffer paramBuffer = new StringBuffer();
        if (map != null && !map.isEmpty()) {
           for (Map.Entry<String, String> entry : map.entrySet()) {
        	   paramBuffer.append(entry.getKey())
                      .append("=")
                      .append(URLEncoder.encode(entry.getValue(), "gbk"))
                      .append("&");
           }
           paramBuffer.deleteCharAt(paramBuffer.length() - 1);
        }
        byte[] paramData = paramBuffer.toString().getBytes();
		
		httpsConn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=GBK");
		httpsConn.setRequestProperty("Content-Length",String.valueOf(paramData.length));
		httpsConn.setUseCaches(false);
		httpsConn.setSSLSocketFactory(ssf);
		httpsConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		httpsConn.setDoOutput(true);
		//httpsConn.connect();
		outputStream = httpsConn.getOutputStream();
        outputStream.write(paramData);
		// 刷新、关闭
        outputStream.flush();
        outputStream.close();
        String result = null; 
		//取得该连接的输入流，以读取响应内容
		InputStream is = httpsConn.getInputStream();
		JSONObject jsonObject = null;
		if (is != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"gbk"));
            String line = "";
            while(null != (line=br.readLine())){
                result += line;
            }
            System.out.println(result);
			jsonObject = new JSONObject(result);
		}
		return jsonObject;	
	}
	/*public static void main(String[] args) {
		try {
			String requestId=DataUtils.randomUUID();
			String md5=requestId+"fbm000004韩玉昆51070919840824824812016100920161102";
			String md52=MD5Utils.hmacSign(md5, "RaeWUYmPbjfLAQ8dU9itzMA78eULQuHF");
			
			Map<String, String> map = new HashMap<String, String>();
	        map.put("requestId", requestId);
	        map.put("merchantCode", "fbm000004");
	        map.put("userName", "韩玉昆");
	        map.put("idNumber", "510709198408248248");
	        map.put("pageNo", "1");
	        map.put("sDate", "20161009");
	        map.put("eDate", "20161102");
	        map.put("signature", md52);
			
			JSONObject jon = getJsonObjectByUrl("https://fbtest.sumapay.com/main/UserForFT_authQuery",map,"gbk");
			JSONArray jSONArray = (JSONArray)jon.get("authList");
			JSONObject jos = (JSONObject) jSONArray.get(0);
			String s2 = new String(jos.get("userName").toString().getBytes("GBK"),"GBK");
			System.out.println(s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
