package org.duang.enums.scale;

 
/**   
 * 标的类型 1普通，2集合标
 * @ClassName:  useTicket   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 5y
 * @date 2016年8月12日 下午4:18:33      
 */  
public enum SingleOrSet {
	S1{
		@Override
		public String getVal() {
			return "1";
		}
	},
	S2{

		@Override
		public String getVal() {
			return "2";
		}
		
	};
	public abstract String getVal();
}
