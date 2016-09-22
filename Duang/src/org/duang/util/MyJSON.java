package org.duang.util;
//import org.apache.commons.httpclient.util.DateUtil;
//import net.sf.json.JSONObject;
//import net.sf.json.JSONSerializer; 
//import net.sf.json.JsonConfig;
//import net.sf.json.processors.JsonBeanProcessor;
//import net.sf.json.processors.JsonBeanProcessorMatcher;
//import net.sf.json.processors.JsonValueProcessor;
//import net.sf.json.util.PropertyFilter;

/**
 * Json自定义转换  有bug暂不用，有需要再参考
 * ClassName: MyJSON 
 * date: 2016年7月15日 下午3:10:03 
 * @author 5y
 */
public class MyJSON {
	//
	//	/**
	//	 * 根据参数指定， 不转换参数中的属性
	//	 * @param obj
	//	 * @param propertyNames        //"hibernateLazyInitializer"
	//	 * @return
	//	 */
	//	public static String toJsonStr(Object obj,final String...propertyNames){
	//		JsonConfig config=new JsonConfig();
	//		config.setJsonPropertyFilter(new PropertyFilter(){public boolean apply(Object arg0, String arg1, Object arg2) {
	//			for (String str : propertyNames) {
	//				//System.out.println(arg1+'\t'+str);
	//				if (arg1.equals(str)) {
	//					return true;//不执行json转换
	//				}
	//			}
	//			return false;
	//		}});
	//		MyJSON j = new MyJSON();
	//		config.registerJsonValueProcessor(Date.class, j.new DateJsonValueProcessor("yyyy-MM-dd"));
	//		config.registerJsonBeanProcessor(org.hibernate.proxy.HibernateProxy.class, j.new HibernateJsonBeanProcessor());
	//		config.setJsonBeanProcessorMatcher(j.new HibernateJsonBeanProcessorMatcher());
	//		String msg=JSONSerializer.toJSON(obj,config).toString();
	//		//String msg=JSONArray.fromObject(obj,config).toString();
	//		return msg;
	//	}
	//
	//
	//	/**
	//	 * 不转换空值
	//	 * @param obj
	//	 * @return
	//	 */
	//	public static String toJsonStrNotNull(Object obj){		
	//		JsonConfig config=new JsonConfig();
	//		config.setJsonPropertyFilter(new PropertyFilter(){public boolean apply(Object arg0, String arg1, Object arg2) {	
	//			//			System.out.println("arg0::::"+arg0);
	//			//			System.out.println("arg1::::"+arg1);
	//			//			System.out.println("arg2::::"+arg2);
	//			try{
	//				arg2.toString();
	//			}catch (Exception e) {
	//				//System.out.println("为空值JSON不转换");
	//				return true;
	//			}
	//			return false;
	//		}});
	//		String msg=JSONSerializer.toJSON(obj,config).toString();
	//		return msg;
	//	}
	//
	//	/**
	//	 * 转date时间的(网上找的,牛爆了)
	//	 */
	//	class DateJsonValueProcessor implements JsonValueProcessor{
	//
	//		private String format = "yyyy-MM-dd HH:mm:ss";
	//
	//		public DateJsonValueProcessor(){}
	//
	//		public DateJsonValueProcessor(String format){
	//			this.format = format;
	//		}
	//
	//		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
	//			String[] obj = {};
	//			if (value instanceof Date[]) {
	//				Date[] dates = (Date[]) value;
	//				obj = new String[dates.length];
	//				for (int i = 0; i < dates.length; i++) {
	//					obj[i] = DateUtil.formatDate(dates[i], format);
	//				}
	//			}
	//			return obj;
	//		}
	//
	//		public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {
	//			if (value instanceof Date) {
	//				String str = DateUtil.formatDate((Date) value, format);
	//				return str;
	//			}
	//			return value == null ? null : value.toString();
	//		}
	//
	//		public String getFormat() {
	//			return format;
	//		}
	//
	//		public void setFormat(String format) {
	//			this.format = format;
	//		}
	//	}
	//
	//	/**
	//	 * ???????????不知道嘛用
	//	 */
	//	class HibernateJsonBeanProcessor implements JsonBeanProcessor{
	//		public JSONObject processBean(Object arg0, JsonConfig arg1) {
	//			return new JSONObject();
	//		}
	//	}
	//
	//	class HibernateJsonBeanProcessorMatcher extends JsonBeanProcessorMatcher{
	//		@SuppressWarnings("unchecked")
	//		@Override
	//		public Object getMatch(Class target, Set set) {
	//			if (target.getName().contains("$$EnhancerByCGLIB$$")) {
	//				return org.hibernate.proxy.HibernateProxy.class;
	//			}
	//			return DEFAULT.getMatch(target, set);
	//		}
	//	}
}
