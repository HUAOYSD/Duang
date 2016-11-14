package org.duang.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.duang.common.logger.LoggerUtils;
import org.duang.service.SMSService;
import org.duang.util.DataUtils;

/**   
 * 短信签名值
 * @ClassName:  SMS   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年9月6日 上午10:20:34      
 */  
public class SMSUtils {

	private SMSService service;
	@Resource
	public void setService(SMSService service) {
		this.service = service;
	}


	private SMSUtils(){
	}

	private static SMSUtils instance;
	public static SMSUtils getInstance(){
		if (instance == null) {
			instance = new SMSUtils();
		}
		return instance;
	}


	/**   
	 * IOS端发短信的签名码
	 * @Fields IOS_SIGNATURE : TODO(用一句话描述这个变量表示什么)   
	 */   
	public final static String IOS_SIGNATURE = "bc11aa9516624184876ebec2d35e7a92";


	/**   
	 * Android端发短信的签名码
	 * @Fields ANDORID_SIGNATURE : TODO(用一句话描述这个变量表示什么)   
	 */   
	public final static String ANDORID_SIGNATURE = "037ab5277ca547cb84af7baf394bf8aa";


	/** 
	 * 系统端发短信的签名码  
	 * @Fields SYS_SIGNATURE : TODO(用一句话描述这个变量表示什么)   
	 */   
	public final static String SYS_SIGNATURE = "4f71662713284c90b2ca97dc7138b82e";


	/**
	 * 短信参数
	 */
	private final static String URL = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
	private final static String ACTION = "sendOnce";
	private final static String AC = "1001@501320930001";
	private final static String AUTHKEY = "76478EF2A59AC9271F2164183444E15B";
	private final static String CGID = "52";

	/**
	 * 群发最小限制
	 */
	private final static int CROWD_MIN = 2;
	/**
	 * 群发最大限制
	 */
	private final static int CROWD_MAX = 100;
	/**
	 * 单条字符限制
	 */
	private final static int MAXLENGTH = 1000;


	/**   
	 * 发送内容手机号一对一短信
	 * @Title: sendSMS   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param content
	 * @param: @param mobile
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月19日 上午10:48:55
	 * @return: boolean      
	 * @throws   
	 */  
	public synchronized boolean sendSMS(final String content, final String mobile, final int platform) throws Exception{
		if (DataUtils.isEmpty(content) || DataUtils.isEmpty(mobile) || content.getBytes().length > MAXLENGTH) {
			return false;
		}
		Thread thread = new Thread(){
			@Override
			public void run(){
				synchronized (this){
					try {
						org.duang.entity.SMS sms = new org.duang.entity.SMS(DataUtils.randomUUID(), mobile, content, new Date(), 0, platform, 0, 0, 0);
						CloseableHttpClient httpclient = HttpClientBuilder.create().build();
						HttpPost post = new HttpPost(URL);
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("action", ACTION));
						params.add(new BasicNameValuePair("ac", AC));
						params.add(new BasicNameValuePair("authkey", AUTHKEY));
						params.add(new BasicNameValuePair("cgid", CGID));
						System.out.println("aaaaaaa");
						String c = new String(content.getBytes(),"gbk");
						params.add(new BasicNameValuePair("c", c));
						params.add(new BasicNameValuePair("m", mobile));
						post.setEntity(new UrlEncodedFormEntity(params, "gbk"));
						HttpResponse httpResponse = httpclient.execute(post);
						String result = null;
						if (httpResponse.getStatusLine().getStatusCode() == 200){
							result = EntityUtils.toString(httpResponse.getEntity());
							LoggerUtils.info("短信返回信息：", SMSUtils.class);
							LoggerUtils.info(result, SMSUtils.class);
						}
						handleResult(result, sms);
						service.saveEntity(sms);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
		return true;

	}


	/**   
	 * 发送内容手机号一对多短信
	 * @Title: sendSMS   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param content
	 * @param: @param mobiles
	 * @param: @return
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月19日 上午11:05:16
	 * @return: boolean      
	 * @throws   
	 */  
	public synchronized boolean sendSMS(String content, List<String> mobiles, int platform) throws Exception{
		if (DataUtils.isEmpty(content) || DataUtils.isEmpty(mobiles) || mobiles.size() < CROWD_MIN || mobiles.size() > CROWD_MAX || content.getBytes().length > MAXLENGTH) {
			return false;
		}
		for (int i = 0; i < mobiles.size(); i++) {
			this.sendSMS(content, mobiles.get(i), platform);
		}
		return true;
		/*CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", ACTION));
		params.add(new BasicNameValuePair("ac", AC));
		params.add(new BasicNameValuePair("authkey", AUTHKEY));
		params.add(new BasicNameValuePair("cgid", CGID));
		content = new String(content.getBytes(), "UTF-8");
		String c = "";
		String m = "";
		for (int i = 0; i < mobiles.size(); i++) {
			m += mobiles.get(i);
			c += content;
			if (i != (mobiles.size() - 1)) {
				m += ",";
				c += "{|}";
			}
		}
		params.add(new BasicNameValuePair("c", c));
		params.add(new BasicNameValuePair("m", m));
		post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		HttpResponse httpResponse = httpclient.execute(post);
		if (httpResponse.getStatusLine().getStatusCode() == 200){
			String result = EntityUtils.toString(httpResponse.getEntity());
			LoggerUtils.info("短信返回信息：", SMS.class);
			LoggerUtils.info(result, SMS.class);
		}*/
	}


	/**   
	 * 处理短信发送结果
	 * @Title: handleResult   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param result
	 * @param: @param sms
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月19日 下午3:16:44
	 * @return: void      
	 * @throws   
	 */  
	private void handleResult(String result, org.duang.entity.SMS sms) throws Exception{
		if (DataUtils.isEmpty(result)) {
			return;
		}
		Document document = DocumentHelper.parseText(result);
		Element root = document.getRootElement();
		int status = DataUtils.str2int(root.attributeValue("result"));
		for(Iterator<?> i = root.elementIterator(); i.hasNext();){ 
			Element item = (Element) i.next(); 
			if (item != null) {
				int total = DataUtils.str2int(item.attributeValue("total"));
				double price = DataUtils.str2double(item.attributeValue("price"), 6);
				//double remain = DataUtils.str2double(item.attributeValue("remain"), 6);
				sms.setMesNum(total);
				sms.setPrice(price);
				sms.setMoney(price * total);
			}
		}
		sms.setState(status == 1 ? 1 : 0);
	}

}
