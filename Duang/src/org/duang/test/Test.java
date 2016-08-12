package org.duang.test;

import org.apache.log4j.Logger;
import org.duang.enums.If;
import org.junit.Before;

public class Test {

	private static final Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		logger.debug(" This is debug!!!");
		logger.info(" This is info!!!");
		logger.warn(" This is warn!!!");
		logger.error(" This is error!!!");
		logger.fatal(" This is fatal!!!");
		
		If if1 = If.valueOf("E1");
		System.out.println(if1);
	}

	@Before
	public void sfds(){
	}

	@org.junit.Test
	public void fds(){
		If if1 = If.valueOf("E1");
		System.out.println(if1);
	}



}
