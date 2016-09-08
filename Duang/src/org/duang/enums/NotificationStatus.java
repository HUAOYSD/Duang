package org.duang.enums;

/**   
 * 公告状态枚举类
 * @ClassName:  NotificationStatus   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author LiYonghui
 * @date 2016年9月7日 上午11:22:24      
 */  
public enum NotificationStatus {
	status1 {
		@Override
		public int getVal() {
			return 1;
		}
	},
	status2 {
		@Override
		public int getVal() {
			return 2;
		}
	},
	status3 {
		@Override
		public int getVal() {
			return 3;
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
