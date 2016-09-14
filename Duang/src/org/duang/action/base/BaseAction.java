package org.duang.action.base;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.avro.reflect.Nullable;
import org.apache.struts2.ServletActionContext;
import org.duang.common.CondsUtils;
import org.duang.common.logger.LoggerUtils;
import org.duang.util.CastToClass;
import org.duang.util.PageUtil;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**   
 *
 * @ClassName:  BaseAction   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年7月18日 上午9:48:13   
 * @param <T>   
 */  
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = 1L;

	/**   
	 * 需要操作的实体类
	 * @Fields entity : TODO(用一句话描述这个变量表示什么)   
	 */   
	protected T entity;
	/**   
	 * 该实体类的class属性
	 * @Fields clazz : TODO(用一句话描述这个变量表示什么)   
	 */   
	private Class<T> clazz;
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
	/**   
	 * 提示消息
	 * @Fields msg : TODO(用一句话描述这个变量表示什么)   
	 */   
	protected String msg;
	/**
	 * json对象   
	 * @Fields jsonObject : TODO(用一句话描述这个变量表示什么)   
	 */   
	protected JSONObject jsonObject;
	/**   
	 * 查询使用的条件类
	 * @Fields condsUtils : TODO(用一句话描述这个变量表示什么)   
	 */   
	protected CondsUtils condsUtils;
	/**   
	 * 分页对象
	 * @Fields pageutil : TODO(用一句话描述这个变量表示什么)   
	 */   
	private PageUtil<T> pageutil=null;
	/**   
	 * PrintWrite对象
	 * @Fields out : TODO(用一句话描述这个变量表示什么)   
	 */   
	private PrintWriter out;
	/**   
	 * listMap数据集
	 * @Fields listMap : TODO(用一句话描述这个变量表示什么)   
	 */   
	protected List<Map<String, Object>> listMap = null;


	/**
	 * 通过构造方法获取clazz
	 */
	public BaseAction(){
		//this.getClass获取的并不是BaseAction的class，而是其‘当前操作的子类’的class
		this.clazz = CastToClass.getSuperGenericClass(this.getClass());
	}


	/** 
	 * 通过ModelDriven获取实体对象
	 */
	public T getModel() {
		if (entity == null) {
			try {
				entity=(T)clazz.newInstance();
			} catch (Exception e) {
				LoggerUtils.error("通过ModelDriven获取实体对象出错了", this.clazz);
				LoggerUtils.error(e.getMessage(), this.clazz);
				LoggerUtils.error(e.getLocalizedMessage(), this.clazz);
			}
		}
		return entity;
	}

	/** 
	 * 将jsonObject输出到页面，自动转为json
	 * @Title: printJsonResult 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-15 下午4:31:26
	 */ 
	protected void printJsonResult(){
		try {
			if (out == null) {
				out = getResponse(null).getWriter();
			}
			if (out != null) {
				out.print(jsonObject);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ERROR-Msg："+e.getMessage(), this.clazz);
			LoggerUtils.error("ERROR-LocalizeMsg："+e.getLocalizedMessage(), this.clazz);
		}
	}
	
	
	/** 
	 * 将jsonArray输出到页面，自动转为json
	 * @Title: printJsonResult 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return void    返回类型 
	 * @author 白攀
	 * @date 2016-7-15 下午4:31:26
	 */ 
	protected void printJsonResult(JSONArray jsonArray){
		try {
			if (out == null) {
				out = getResponse(null).getWriter();
			}
			if (out != null) {
				out.print(jsonArray);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ERROR-Msg："+e.getMessage(), this.clazz);
			LoggerUtils.error("ERROR-LocalizeMsg："+e.getLocalizedMessage(), this.clazz);
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
	protected void printJsonResult(String jsonStr){
		try {
			if (out == null) {
				out = getResponse(null).getWriter();
			}
			if (out != null) {
				out.print(jsonStr);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.error("ERROR-Msg："+e.getMessage(), this.clazz);
			LoggerUtils.error("ERROR-LocalizeMsg："+e.getLocalizedMessage(), this.clazz);
		}
	}


	/**
	 * 获取HttpServletRequest
	 * @return
	 */
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取HttpServletReponse
	 * @return
	 */
	protected HttpServletResponse getResponse(@Nullable String mime){
		HttpServletResponse response = ServletActionContext.getResponse();
		if (mime == null) {
			response.setContentType("text/html; charset=UTF-8");
		}else {
			response.setContentType(mime);
		}
		return response;
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
	public PageUtil<T> getPageUtil() {
		if (pageutil==null) {
			pageutil = new PageUtil<T>(rows, page);	
		}
		return pageutil;
	}
	public PageUtil<T> setPageUtil(PageUtil<T> pageUtil) {
		this.pageutil = pageUtil;
		return pageutil;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	{
		jsonObject = new JSONObject();
		condsUtils = new CondsUtils();
		listMap = new ArrayList<Map<String, Object>>();
	}
}
