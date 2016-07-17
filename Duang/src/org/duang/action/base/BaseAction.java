package org.duang.action.base; 
import java.io.PrintWriter;

import net.sf.json.JSONObject;

import org.duang.common.CondsUtils;
import org.duang.common.logger.LoggerUtils;


/** 
 * Action基类
 * @ClassName: BaseAction 
 * @Description: TODO 
 * @author Comsys-白攀 
 * @date 2016-7-15 下午2:33:18 
 */
public class BaseAction {

	//下一个地址
	protected String path;
	//提示消息
	protected String msg;
	//条件类
	protected CondsUtils condsUtils = new CondsUtils();
	//json对象
	protected JSONObject jsonObject = null;

	/**   
	 * easy列表页自带参数 每页个数
	 * @Fields rows : TODO(用一句话描述这个变量表示什么)   
	 */   
	protected int rows;
	/**   
	 * easy列表页自带参数 当前页数
	 * @Fields page : TODO(用一句话描述这个变量表示什么)   
	 */   
	protected int page;
	
	
	//	public BaseController(){
	//		this.clazz=CastToClass.getSuperGenericClass(this.getClass());
	//	}

	/** 
	 * 将jsonObject输出到页面，自动转为json
	 * @Title: printJsonResult 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-15 下午4:31:26
	 */ 
	protected void printJsonResult(PrintWriter out){
		try {
			if (out != null) {
				out.print(jsonObject);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ERROR-Msg："+e.getMessage());
			LoggerUtils.error("ERROR-LocalizeMsg："+e.getLocalizedMessage());
		}
	}


	/** 
	 * 直接输入json字符串到前台
	 * @Title: printJsonResult 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-15 下午4:31:26
	 */ 
	protected void printJsonResult(PrintWriter out, String jsonStr){
		try {
			if (out != null) {
				out.print(jsonStr);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ERROR-Msg："+e.getMessage());
			LoggerUtils.error("ERROR-LocalizeMsg："+e.getLocalizedMessage());
		}
	}


	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}

