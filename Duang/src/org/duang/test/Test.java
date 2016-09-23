package org.duang.test;

import org.junit.Before;

public class Test {

	class ZiFuChuan{
		String string;
		public String getString() {
			return string;
		}
		public void setString(String string) {
			this.string = string;
		}
	}

	public void init(){
		final ZiFuChuan zfc = new ZiFuChuan();
		zfc.setString("shit");
		Thread thread = new Thread(){
			@Override
			public void run() {
				synchronized(this){
					zfc.setString("fuck");
					System.out.println("fuck");
				}
			}
		};
		thread.start();
		System.out.println(zfc.getString());
	}

	public static void main(String[] args) throws Exception{
		new Test().init();
		//		String result = "";
		//		result += "<xml name=\"sendOnce\" result=\"-2\">";
		//		//result += "<Item cid=\"501320930001\" sid=\"1001\" msgid=\"522005537348992700\" total=\"3\" price=\"0.1\" remain=\"0.700\" />";
		//		//result += "<Item cid=\"501320930001\" sid=\"1001\" msgid=\"522005537348992700\" total=\"1\" price=\"0.1\" remain=\"0.600\" />";
		//		result += "</xml>";
		//		Document document = DocumentHelper.parseText(result);
		//		Element root = document.getRootElement();
		//		System.out.println(root.attributeValue("result"));
		//		for(Iterator<?> i = root.elementIterator(); i.hasNext();){ 
		//			Element item = (Element) i.next(); 
		//			System.out.print(item.attributeValue("total", "")+"\t\t\t"); 
		//			System.out.print(item.attributeValue("price", "")+"\t\t\t"); 
		//			System.out.println(item.attributeValue("remain", "")+"\t\t\t"); 
		//		}
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
