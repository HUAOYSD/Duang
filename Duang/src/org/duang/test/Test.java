package org.duang.test;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Before;

public class Test {


	/**   
	 * 
	 * @Title: main   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param args  
	 * @author 5y    
	 * @date 2016年9月18日 上午11:25:17
	 * @return: void      
	 * @throws   
	 */  
	public static void main(String[] args) throws Exception{
		String result = "";
		result += "<xml name=\"sendOnce\" result=\"-2\">";
		//result += "<Item cid=\"501320930001\" sid=\"1001\" msgid=\"522005537348992700\" total=\"3\" price=\"0.1\" remain=\"0.700\" />";
		//result += "<Item cid=\"501320930001\" sid=\"1001\" msgid=\"522005537348992700\" total=\"1\" price=\"0.1\" remain=\"0.600\" />";
		result += "</xml>";
		Document document = DocumentHelper.parseText(result);
		Element root = document.getRootElement();
		System.out.println(root.attributeValue("result"));
		for(Iterator<?> i = root.elementIterator(); i.hasNext();){ 
			Element item = (Element) i.next(); 
			System.out.print(item.attributeValue("total", "")+"\t\t\t"); 
			System.out.print(item.attributeValue("price", "")+"\t\t\t"); 
			System.out.println(item.attributeValue("remain", "")+"\t\t\t"); 
		}
	}

	@Before
	public void sfds(){
	}

	@org.junit.Test
	public void fds() {
		//String str="中文abc123.。";
		//System.out.println(str.getBytes().length);
//		for (int i = 1; i < 500; i++) {
//			System.out.println(DataUtils.sixNumber());
//		}
		int i = 2;
		double ii = 2.2;
		System.out.println(i * ii);
	}





}
