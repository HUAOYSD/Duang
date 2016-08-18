package org.duang.enums;
import org.duang.util.DataUtils;

 
/**   
 * 平台的枚举类
 * @ClassName:  Platform   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年8月11日 上午10:07:21      
 */  
public enum Platform {
	P0 {
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
	P1 {
		private String desc = "线下";
		
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
			this.desc = "线下";
			return this;
		}
		
		@Override
		public String toString() {
			return "线下";
		}

	},
	P2 {
		private String desc = "Android";
		
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
			this.desc = "Android";
			return this;
		}

		@Override
		public String toString() {
			return "Android";
		}
		
	},
	P3 {
		private String desc = "IOS";
		
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
			this.desc = "IOS";
			return this;
		}
		
		@Override
		public String toString() {
			return "IOS";
		}

	},
	P4 {
		private String desc = "平台系统";
		
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
			this.desc = "平台系统";
			return this;
		}
		
		@Override
		public String toString() {
			return "平台系统";
		}

	};
	
	/**   
	 * 获取枚举值
	 * @Title: getVal   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return  
	 * @author 白攀    
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
	 * @author 白攀    
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
	 * @author 白攀    
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
	 * @author 白攀    
	 * @date 2016年8月9日 上午11:33:56
	 * @return: Object      
	 * @throws   
	 */  
	public abstract Object setDefaultDesc();
	
	
}
