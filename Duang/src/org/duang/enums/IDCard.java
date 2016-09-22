package org.duang.enums;

/**   
 * 身份证正反面照枚举
 * @ClassName:  IDCard   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月10日 下午4:11:39      
 */  
public enum IDCard {
	IDCARD0 {
		@Override
		public int getVal() {
			return 1;
		}

		@Override
		public String toString() {
			return "未知";
		}

	},
	IDCARD1 {
		@Override
		public int getVal() {
			return 1;
		}

		@Override
		public String toString() {
			return "身份证前照";
		}

	},
	IDCARD2 {
		@Override
		public int getVal() {
			return 2;
		}

		@Override
		public String toString() {
			return "身份证后照";
		}
	};
	
	
	/**   
	 * 获取枚举值
	 * @Title: getVal   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年8月9日 上午11:24:09
	 * @return: int      
	 * @throws   
	 */  
	public abstract int getVal();
}
