package org.duang.enums.loan;

import org.duang.util.DataUtils;

/**   
 * 贷款记录中申请模式的枚举类
 * @ClassName:  Scale   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年8月11日 上午10:12:28      
 */  
public enum LoanStatus {
	S0 {
		@Override
		public int getVal() {
			return 0;
		}

		@Override
		public String getDesc() {
			return "未知";
		}

		@Override
		public Object setDesc(String desc) {
			return this;
		}

		@Override
		public Object setDefaultDesc() {
			return this;
		}

		@Override
		public String toString() {
			return "未知";
		}
	},
	S1 {
		private String desc = "放款";
		
		@Override
		public int getVal() {
			return 1;
		}

		@Override
		public String getDesc() {
			return desc;
		}

		@Override
		public Object setDesc(String desc) {
			if (DataUtils.notEmpty(desc)) {
				this.desc = desc;
				return this;
			}else {
				return setDefaultDesc();
			}
		}

		@Override
		public Object setDefaultDesc() {
			this.desc = "放款";
			return this;
		}
		
		@Override
		public String toString() {
			return "放款";
		}

	},
	S2 {
		private String desc = "还款";
		
		@Override
		public int getVal() {
			return 2;
		}

		@Override
		public String getDesc() {
			return desc;
		}

		@Override
		public Object setDesc(String desc) {
			if (DataUtils.notEmpty(desc)) {
				this.desc = desc;
				return this;
			}else {
				return setDefaultDesc();
			}
		}

		@Override
		public Object setDefaultDesc() {
			this.desc = "还款";
			return this;
		}

		@Override
		public String toString() {
			return "还款";
		}
		
	},
	S3 {
		private String desc = "增加逾期费";
		
		@Override
		public int getVal() {
			return 3;
		}

		@Override
		public String getDesc() {
			return desc;
		}

		@Override
		public Object setDesc(String desc) {
			if (DataUtils.notEmpty(desc)) {
				this.desc = desc;
				return this;
			}else {
				return setDefaultDesc();
			}
		}

		@Override
		public Object setDefaultDesc() {
			this.desc = "增加逾期费";
			return this;
		}
		
		@Override
		public String toString() {
			return "增加逾期费";
		}

	},
	S4 {
		private String desc = "还逾期费";
		
		@Override
		public int getVal() {
			return 4;
		}

		@Override
		public String getDesc() {
			return desc;
		}

		@Override
		public Object setDesc(String desc) {
			if (DataUtils.notEmpty(desc)) {
				this.desc = desc;
				return this;
			}else {
				return setDefaultDesc();
			}
		}

		@Override
		public Object setDefaultDesc() {
			this.desc = "还逾期费";
			return this;
		}
		
		@Override
		public String toString() {
			return "还逾期费";
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
	
	
	/**   
	 * 获取枚举描述
	 * @Title: getDesc   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年8月9日 上午11:24:24
	 * @return: String      
	 * @throws   
	 */  
	public abstract String getDesc();
	
	
	/**   
	 * 设置描述文字，null代表默认值
	 * @Title: setDesc   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param desc  
	 * @author 5y    
	 * @date 2016年8月9日 上午11:29:14
	 * @return: void      
	 * @throws   
	 */  
	public abstract Object setDesc(String desc);
	
	
	/**   
	 * 设置desc为默认描述文字
	 * @Title: setDefaultDesc   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年8月9日 上午11:33:56
	 * @return: Object      
	 * @throws   
	 */  
	public abstract Object setDefaultDesc();
	
}
