package org.duang.test;

import java.util.Random;

import org.junit.Before;

public class Test {


	/**   
	 * 
	 * @Title: main   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param args  
	 * @author 白攀    
	 * @date 2016年9月18日 上午11:25:17
	 * @return: void      
	 * @throws   
	 */  
	public static void main(String[] args) {
		String str="中文";
    	System.out.println(str.getBytes().length);
	}

	@Before
	public void sfds(){
	}

	@org.junit.Test
	public void fds() {
		String str="中文abc123.。";
    	System.out.println(str.getBytes().length);
    	for (int i = 1; i < 600; i++) {
			System.out.println(new Random().nextInt(10));
		}
	}
	
	



}
