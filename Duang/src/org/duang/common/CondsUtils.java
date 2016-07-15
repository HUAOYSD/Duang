package org.duang.common;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个条件列表全局类
 * @author 白攀
 */
public class CondsUtils {

	//条件名字
	public List<String> propertys=new ArrayList<String>();
	//条件值
	public List<Object> values=new ArrayList<Object>();

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
	public List<String> addProperties(String... arg){
		//每次给它清一遍
		propertys.clear();
		if (arg!=null && arg.length > 0) {
			for (String temp : arg) {
				propertys.add(temp);
			}
		}
		return propertys;
	}


	/**
	 * 添加条件值
	 * @param arg 所有的条件值如：obj1,obj2...
	 * @return 条件名字集合
	 */
	public List<Object> addValues(Object... arg){
		//每次给它清一遍
		values.clear();
		if (arg!=null && arg.length > 0) {
			for (Object temp : arg) {
				values.add(temp);
			}
		}
		return values;
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
