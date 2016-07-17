package org.duang.util;

import java.util.ArrayList;
import java.util.List;

/** 
 * 页码工具类
 * @ClassName: PageUtil 
 * @Description: TODO 
 * @author 白攀 
 * @date 2016-7-17 下午1:52:53 
 */
public class PageUtil<T>{

	private int countRecords;//记录总条数
	private int countPages;//总页码数
	private int pageRecords = 20;//每页记录数 默认为20条
	private List<T> itemList;//业务List集合
	private int currentPageNum = 1;//当前的页码 默认为第1页
	private int prevPageNum;//上一页码
	private int nextPageNum;//下一页码
	private int pageNums = 5;//每页显示页码的个数 默认为5个eg:1 ...7,8,9,10
	private List<Integer> listPages = new ArrayList<Integer>();//页码列表
	private int recordStartIndex;//记录的开始位置

	public PageUtil(int currentPageNum, Integer countRecords, Integer pageRecords) throws Exception{
		//		String path = getClass().getClassLoader().getResource("/").getPath();
		//		SAXParserFactory spf = SAXParserFactory.newInstance();
		//		try{
		//			SAXParser saxParser = spf.newSAXParser();
		//			MyDefaultHandler handle = new MyDefaultHandler();
		//			saxParser.parse(path + "paginationconfig.xml", handle);
		//			if (pageRecords != 0)
		//				pageRecords = handle.getPageSize();
		//		} catch (ParserConfigurationException e){
		//			e.printStackTrace();
		//		} catch (SAXException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
		setCountRecords(countRecords.intValue()); 
		if ((pageRecords != null) && (pageRecords.intValue() > 0)) {
			setPageRecords(pageRecords.intValue()); 
		}
		setCountPages();
		setCurrentPageNum(currentPageNum);
		setRecordStartIndex();
		setPrevPageNum();
		setNextPageNum();
	}


	public PageUtil() {
		//		String path = getClass().getClassLoader().getResource("/").getPath();
		//		SAXParserFactory spf = SAXParserFactory.newInstance();
		//		try{
		//			SAXParser saxParser = spf.newSAXParser();
		//			MyDefaultHandler handle = new MyDefaultHandler();
		//			saxParser.parse(path + "paginationconfig.xml", handle);
		//			if (pageRecords != 0)
		//				pageRecords = handle.getPageSize();
		//		} catch (ParserConfigurationException e){
		//			e.printStackTrace();
		//		} catch (SAXException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
	}


	public PageUtil(int pageRecords, int currentPageNum){
		//		String path = getClass().getClassLoader().getResource("/").getPath();
		//		SAXParserFactory spf = SAXParserFactory.newInstance();
		//		try{
		//			SAXParser saxParser = spf.newSAXParser();
		//			MyDefaultHandler handle = new MyDefaultHandler();
		//			saxParser.parse(path + "paginationconfig.xml", handle);
		//			if (pageRecords != 0)
		//				pageRecords = handle.getPageSize();
		//		} catch (ParserConfigurationException e){
		//			e.printStackTrace();
		//		} catch (SAXException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
		//pageRecords = pageRecords;
		this.pageRecords = pageRecords;
		this.currentPageNum = currentPageNum;
	}

	public PageUtil(int currentPageNum){
		//		String path = getClass().getClassLoader().getResource("/").getPath();
		//		SAXParserFactory spf = SAXParserFactory.newInstance();
		//		try {
		//			SAXParser saxParser = spf.newSAXParser();
		//			MyDefaultHandler handle = new MyDefaultHandler();
		//			saxParser.parse(path + "paginationconfig.xml", handle);
		//			if (pageRecords != 0)
		//				pageRecords = handle.getPageSize();
		//		} catch (ParserConfigurationException e) {
		//			e.printStackTrace();
		//		} catch (SAXException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
		this.currentPageNum = currentPageNum;
	}

	public int getCountRecords() {
		return this.countRecords;
	}
	public void setCountRecords(int countRecords) {
		this.countRecords = countRecords;
	}
	public int getCountPages() {
		return this.countPages;
	}
	public int getPageNums() {
		return pageNums;
	}
	public void setPageNums(int pageNums) {
		this.pageNums = pageNums;
	}
	public void setCountPages(int countPages) {
		this.countPages = countPages;
	}
	public void setCountPages(){
		this.countPages = ((int)Math.ceil(this.countRecords / pageRecords));
	}
	public void setCountPages(boolean hanlder, int zongyeshu){
		if (hanlder)
			this.countPages = zongyeshu;
	}
	public int getPageRecords(){
		return pageRecords;
	}

	public void setPageRecords(int pageRecords) throws Exception {
		//		Properties prop = new Properties();
		//		InputStream in = Object.class.getResourceAsStream("/pageSize.properties");
		//		prop.load(in);
		//		pageRecords = Integer.parseInt(prop.getProperty("valueSize"));
		this.pageRecords = pageRecords;
	}

	public int getCurrentPageNum() {
		return this.currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum){
		if ((currentPageNum > 0) && (currentPageNum <= this.countPages))
			this.currentPageNum = currentPageNum;
		else if (currentPageNum > this.countPages)
			this.currentPageNum = this.countPages;
		else
			this.currentPageNum = 1;
	}

	public int getPrevPageNum() {
		return this.prevPageNum;
	}

	public void setPrevPageNum(){
		if ((this.countPages > 1) && (this.currentPageNum > 1))
			this.prevPageNum = (this.currentPageNum - 1);
	}

	public int getNextPageNum(){
		return this.nextPageNum;
	}

	public void setNextPageNum(){
		if (this.currentPageNum == this.countPages) {
			this.nextPageNum = this.countPages;
		}
		if ((this.currentPageNum > 0) && (this.currentPageNum < this.countPages))
			this.nextPageNum = (this.currentPageNum + 1);
	}

	public List<Integer> getListPages(){
		return this.listPages;
	}

	public int getRecordStartIndex(){
		return this.recordStartIndex;
	}

	public void setRecordStartIndex(){
		this.recordStartIndex = ((this.currentPageNum - 1) * pageRecords);
	}

	public List<T> getItemList(){
		return this.itemList;
	}

	public void setItemList(List<T> itemList){
		this.itemList = itemList;
	}


	/**
	 * 注释的是以前搞得
	 * 新的是现改的
	 * setListPages(这里用一句话描述这个方法的作用)
	 * @Title: setListPages
	 * @Description: TODO
	 * @param     
	 * @return void    返回类型
	 * @author 白攀 
	 * @date 2016-7-17 下午3:55:03
	 * @throws
	 */
	public void setListPages(){
		//		int start = 1;
		//		int num = this.currentPageNum + this.pageNums > this.countPages ? this.countPages : this.currentPageNum + this.pageNums - 1;
		//		int endNum = this.currentPageNum + this.pageNums / 2;
		//		if ((this.currentPageNum - 1 > this.pageNums / 2) && (endNum < num))
		//			start = this.currentPageNum - this.pageNums / 2;
		//		else if ((this.currentPageNum + 3 >= this.countPages) && 
		//				(this.countPages - this.pageNums > 0)) {
		//			start = this.countPages - this.pageNums;
		//		}
		//		int temp = this.pageNums / 2 - (this.currentPageNum - 1);
		//		if ((temp > 0) && (temp + endNum <= num))
		//			num = temp + endNum;
		//		else if (endNum < num) {
		//			num = endNum;
		//		}
		//		for (int i = start; i <= num; i++)
		//			this.listPages.add(Integer.valueOf(i));
		int start=1;
		int num=(this.currentPageNum+this.pageNums)>this.countPages ? this.countPages : (this.currentPageNum+this.pageNums)-1;
		int endNum=this.currentPageNum+(pageNums/2);
		if(this.currentPageNum-1>(pageNums/2) && endNum<num){
			start=this.currentPageNum-(pageNums/2);
		}
		int temp=(pageNums/2)-(this.currentPageNum-1);
		if(temp>0 && (temp+endNum)<=num){
			num=temp+endNum;
		}else if(endNum<num){
			num=endNum;
		}
		for(int i=start;i<=num;i++){
			this.listPages.add(i);
		}
	}

	public String toString(){
		setListPages();
		StringBuffer pageHtml = new StringBuffer();
		pageHtml.append("<div class=\"pagin\">");
		pageHtml.append("<div class=\"message\">共<i class=\"blue\">" + getCountRecords() + "</i>条记录，当前显示第&nbsp;<i class=\"blue\">" + getCurrentPageNum() + "&nbsp;</i>页</div>");
		pageHtml.append("<ul class=\"paginList\">");

		if (getPrevPageNum() > 0) {
			pageHtml.append("<li class=\"paginItem\"><a href=\"javascript:void(0);\" onclick=\"pageTo('" + getPrevPageNum() + "')\"><span class=\"pagepre\"></span></a></li>");
		}
		for (Integer i : getListPages()) {
			String className = "paginItem";
			if (i.intValue() == getCurrentPageNum()) {
				className = "'paginItem current'";
				pageHtml.append("<li class=" + className + "><a href=\"javascript:void(0);\" >" + i + "</a></li>");
			} else {
				pageHtml.append("<li class=" + className + "><a href=\"javascript:void(0);\" onclick=\"pageTo('" + i + "')\">" + i + "</a></li>");
			}
		}

		if ((!getListPages().contains(Integer.valueOf(getCountPages()))) && (getCountPages() > 0)) {
			pageHtml.append("<li class=\"paginItem more\"><a href=\"javascript:void(0);\">...</a></li>");
			pageHtml.append("<li class=\"paginItem\"><a href=\"javascript:void(0);\" onclick=\"pageTo('" + getCountPages() + "')\">" + getCountPages() + "</a></li>");
		}

		if (getNextPageNum() > getCurrentPageNum()) {
			pageHtml.append("<li class=\"paginItem\"><a href=\"javascript:void(0);\" onclick=\"pageTo('" + getNextPageNum() + "')\"><span class=\"pagenxt\"></span></a></li>");
		}
		pageHtml.append("</ul>");
		pageHtml.append("</div>");
		return pageHtml.toString();
	}


	public static void main(String[] args) {
		PageUtilTest pageUtil=new PageUtilTest(1,52,7);
		System.err.println("总页码："+pageUtil.getCountPages()+"  当前页码："+pageUtil.getCurrentPageNum()+" 上一页："+pageUtil.getPrevPageNum()+"  下一页："+pageUtil.getNextPageNum()+" 记录的开始位置"+pageUtil.getRecordStartIndex());
		for(Integer i:pageUtil.getListPages()){
			System.err.println(i);
		}
	}

	//	public static void main(String[] args) throws Exception {
	//		SAXParserFactory spf = SAXParserFactory.newInstance();
	//		SAXParser saxParser = spf.newSAXParser();
	//		MyDefaultHandler handle = new MyDefaultHandler();
	//		saxParser.parse("paginationconfig.xml", handle);
	//		if (pageRecords != 0)
	//			pageRecords = handle.getPageSize();
	//	}
}