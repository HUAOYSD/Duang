/**   
 * @Title: PageUtil.java 
 * @Package org.cms.system.update.util 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 白攀
 * @date 2013-12-30 下午2:18:20 
 * @version V5.0   
 */ 
package org.duang.util;

import java.util.ArrayList;
import java.util.List;


/** 
 * 分页参考
 * @ClassName: PageUtilTest 
 * @Description: TODO(分页工具包) 
 * @author 白攀
 * @date 2016-7-17 下午2:18:20 
 */
public class PageUtilTest {

	private int countRecords; //总记录数
	private int countPages; //总页码数
	private int pageRecords=20; //每页记录数 默认为20条


	//页码选项
	private int currentPageNum=1; //当前的页码 默认为第1页
	private int prevPageNum; //上一页
	private int nextPageNum; //下一页

	private int pageNums=5; //每页显示页码的个数 默认为5个
	private List<Integer> listPages=new ArrayList<Integer>(); //页码列表

	private int recordStartIndex; //记录的开始位置

	//	private int beginNum;	//导行的起始位置
	//	private int endNum;		//导航的结束位置
	private int firstNum=1;	//首页
	private int lastNum;	//尾页

	public PageUtilTest(){}
	/**
	 * @Title: EncodingRequestWrapper 
	 * @Description: 构造函数
	 * @param countRecords 总记录数
	 * @param pageRecords 每页记录数 默认为20
	 * @author 白攀
	 * @date 2016-7-17 下午2:37:38
	 */
	public PageUtilTest(int currentPageNum,Integer countRecords,Integer pageRecords){
		this.setCountRecords(countRecords);
		if(pageRecords!=null && pageRecords>0){
			this.setPageRecords(pageRecords);
		}
		this.setCountPages(); //计算总页数
		this.setCurrentPageNum(currentPageNum); //计算当前的页码
		this.setRecordStartIndex();//设置查询开始的位置
		this.setPrevPageNum(); //计算上一页
		this.setNextPageNum(); //计算下一页
		this.setListPages(); // 计算每一页的页码列表
		this.lastNum=this.getCountPages();	//计算起始位置
	}


	public int getCountRecords() {
		return countRecords;
	}
	public void setCountRecords(int countRecords) {
		this.countRecords = countRecords;
	}
	public int getCountPages() {
		return countPages;
	}


	public void setCountPages(int count) {
		this.countPages = count;
	}

	/**
	 * 计算总页数
	 * @Title: setCountPages 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param countPages
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-17 下午2:43:13
	 */
	private void setCountPages() {
		this.countPages=(int) Math.ceil(((double)this.countRecords)/((double)this.pageRecords));
	}


	public int getPageRecords() {
		return pageRecords;
	}
	public void setPageRecords(int pageRecords) {
		this.pageRecords = pageRecords;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}

	/**
	 * 设置当前的页码
	 * @Title: setCurrentPageNum 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param currentPageNum
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-17 下午3:12:34
	 */
	public void setCurrentPageNum(int currentPageNum) {
		if(currentPageNum>0 && currentPageNum<=countPages){
			this.currentPageNum = currentPageNum;
		}else if(currentPageNum>countPages){
			if(countPages == 0){
				this.currentPageNum = 1;
			}else{
				this.currentPageNum=countPages;
			}
		}
	}

	/** 
	 * 让Easy-UI来控制当前的页数
	 * @Title: setCurrentPageNum 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param currentPageNum
	 * @param easyui
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-17 下午03:49:28
	 */ 
	public void setCurrentPageNum(int currentPageNum,Boolean isEasyui){
		if (isEasyui) {
			this.currentPageNum = currentPageNum;
		}else {
			setCurrentPageNum(currentPageNum);
		}
	}


	public int getPrevPageNum() {
		return prevPageNum;
	}
	/**
	 * 设置上一页的页码
	 * @Title: setPrevPageNum 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-17 下午4:06:44
	 */
	public void setPrevPageNum() {
		if(this.countPages>1 && this.currentPageNum>1){
			this.prevPageNum=this.currentPageNum-1;
		}
	}
	public int getNextPageNum() {
		return nextPageNum;
	}
	/**
	 * 设置下一页页码
	 * @Title: setNextPageNum 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-17 下午3:16:30
	 */
	public void setNextPageNum() {
		if(this.currentPageNum==this.countPages){
			this.nextPageNum=this.countPages;
		}
		if(this.currentPageNum>0 && this.currentPageNum<this.countPages){
			this.nextPageNum=this.currentPageNum+1;
		}
	}


	public List<Integer> getListPages() {
		return listPages;
	}

	/**
	 * 计算页码的列表
	 * @Title: getListPages 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return
	 * @return List<Integer>    返回类型 
	 * @author 白攀
	 * @date 2016-7-17 下午3:27:21
	 */
	public void setListPages() {
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


	public int getRecordStartIndex() {
		return recordStartIndex;
	}

	/**
	 * 根据当前页码获取当前的记录的开始查询位置
	 * @Title: setRecordStartIndex 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-17 下午3:59:37
	 */
	private void setRecordStartIndex() {
		this.recordStartIndex=(this.currentPageNum-1)*this.pageRecords;
	}


	public static void main(String[] args) {
		//		PageUtil pageUtil=new PageUtil(17,225, 12);
		PageUtilTest pageUtil=new PageUtilTest(1,52,7);
		System.err.println("总页码："+pageUtil.getCountPages()+"  当前页码："+pageUtil.getCurrentPageNum()+" 上一页："+pageUtil.getPrevPageNum()+"  下一页："+pageUtil.getNextPageNum()+" 记录的开始位置"+pageUtil.getRecordStartIndex());
		for(Integer i:pageUtil.getListPages()){
			System.err.println(i);
		}
	}
	public int getFirstNum() {
		return firstNum;
	}
	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}
	public int getLastNum() {
		return lastNum;
	}
	public void setLastNum(int lastNum) {
		this.lastNum = lastNum;
	}
}
