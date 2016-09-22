package org.duang.enums.stock;
import org.duang.util.DataUtils;


/**   
 * 库存状态的枚举类
 * @ClassName:  useTicket   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年8月12日 下午4:18:33      
 */  
public enum Status {
	S0 {
		private String desc = "仅入库";

		@Override
		public int getVal() {
			return 0;
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
			this.desc = "仅入库";
			return this;
		}

		@Override
		public String toString() {
			return "仅入库";
		}

	},
	S1 {
		private String desc = "分配到理财标";

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
			this.desc = "分配到理财标";
			return this;
		}

		@Override
		public String toString() {
			return "分配到理财标";
		}

	},
	S2 {
		private String desc = "成功";

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
			this.desc = "成功";
			return this;
		}

		@Override
		public String toString() {
			return "成功";
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
