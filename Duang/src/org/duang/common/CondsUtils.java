package org.duang.common;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个条件列表全局类
 * @author 5y
 */
public class CondsUtils {

	//条件名字
	private List<String> propertys=new ArrayList<String>();
	//条件值
	private List<Object> values=new ArrayList<Object>();

	/**
	 * 清除properties和values
	 */
	public void clear(){
		propertys.clear();
		values.clear();
	}

	/**
	 * 添加条件名字
	 * @param arg 所有的条件如：term1,term2...
	 * @return 条件名字集合
	 */
	public List<String> addProperties(boolean isclear,String... arg){
		if (isclear) {
			//每次给它清一遍
			propertys.clear();
		}
		if (arg!=null && arg.length > 0) {
			for (String temp : arg) {
				propertys.add(temp);
			}
		}
		return propertys;
	}


	/**
	 * 添加条件值
	 * 注意：该方法不能填充like，gt等条件值，如有需要请使用 {@link org.duang.common.CondsUtils} concatValue方法
	 * @param arg 所有的条件值如：obj1,obj2...
	 * @return 条件名字集合
	 */
	public List<Object> addValues(boolean isclear, Object... arg){
		if (isclear) {
			//每次给它清一遍
			values.clear();
		}
		if (arg!=null && arg.length > 0) {
			for (Object temp : arg) {
				values.add(temp);
			}
		}
		return values;
	}


	/**   
	 * 直接追加值给value
	 * @Title: concatValue   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param arg
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月18日 下午9:54:43
	 * @return: List<Object>      
	 * @throws   
	 */  
	public List<Object> concatValue(Object arg){
		values.add(arg);
		return values;
	}


	/**   
	 * 直接追加值给propertys
	 * @Title: concatProperty   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param arg
	 * @param: @return  
	 * @author 5y    
	 * @date 2016年7月18日 下午9:54:43
	 * @return: List<Object>      
	 * @throws   
	 */  
	public List<String> concatProperty(String arg){
		propertys.add(arg);
		return propertys;
	}


	/**   
	 * 添加一对值键
	 * @Title: concat   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param property
	 * @param: @param val  
	 * @author 5y    
	 * @date 2016年8月10日 下午2:18:06
	 * @return: void      
	 * @throws   
	 */  
	public void concat(String property, Object val){
		propertys.add(property);
		values.add(val);
	}


	/**
	 * 获取条件名字
	 * @return
	 */
	public List<String> getPropertys() {
		return propertys;
	}


	/**
	 * 获取条件值
	 * @return
	 */
	public List<Object> getValues() {
		return values;
	}
}
