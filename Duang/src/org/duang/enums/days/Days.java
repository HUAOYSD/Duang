package org.duang.enums.days;

/**   
 * 天数的枚举类
 * @ClassName:  Days   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月31日 上午11:22:24      
 */  
public enum Days {
	day15 {
		@Override
		public int getVal() {
			return 15;
		}

	},
	day30 {
		@Override
		public int getVal() {
			return 30;
		}
		
	},
	day60 {
		@Override
		public int getVal() {
			return 60;
		}
		
	},
	day90 {
		@Override
		public int getVal() {
			return 90;
		}
		
	},
	day180 {
		@Override
		public int getVal() {
			return 180;
		}
		
	},
	day270 {
		@Override
		public int getVal() {
			return 270;
		}
		
	},
	day360 {
		@Override
		public int getVal() {
			return 360;
		}
		
	};
	
	/**
	 * 枚举获取value
	 * @Title: getVal   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月31日 下午2:47:08
	 * @return: int      
	 * @throws
	 */
	public abstract int getVal();
	
	
}
