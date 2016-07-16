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
 * @date 2016-2-29 下午2:33:18 
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

	//	public BaseController(){
	//		this.clazz=CastToClass.getSuperGenericClass(this.getClass());
	//	}

	/** 
	 * 将jsonObject输出到页面，自动转为json
	 * @Title: printJsonResult 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-3-6 下午4:31:26
	 */ 
	protected void printJsonResult(PrintWriter out){
		try {
			System.out.println("develop");
//			if (out != null) {
//				out.print(jsonObject);
//				out.close();
//			}
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
	 * @date 2016-3-6 下午4:31:26
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

}

