package org.duang.enums;


/**   
 * 选择的是理财用户还是借贷用户
 * @ClassName:  InvestOrLoan   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年8月12日 上午11:22:24      
 */  
public enum InvestOrLoan {

	Invest {
		@Override
		public int getVal() {
			return 1;
		}

	},
	Loan {
		@Override
		public int getVal() {
			return 0;
		}
		
	};
	
	/**
	 * 枚举获取value
	 * @Title: getVal   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author LiYonghui    
	 * @date 2016年8月12日 下午2:47:08
	 * @return: int      
	 * @throws
	 */
	public abstract int getVal();
	
}
