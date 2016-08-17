package org.duang.common.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.duang.util.DateUtils;

/**   
 * Struts2 Date类型转换
 * @ClassName:  DateConverter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author 白攀
 * @date 2016年8月10日 上午11:06:28      
 */  
public class DateConverter extends StrutsTypeConverter {

	private final static String[] DATE_FOMARTS = new String[]{"yyyy-MM-dd HH:mm:ss",
		"yyyy/MM/dd hh:mm:ss",
		"yyyy-MM-dd",
	"yyyy/MM/dd"};

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Date date = null;  
		String dateString = null;  
		if (values != null && values.length > 0) {  
			dateString = values[0];  
			if (dateString != null) {  
				for (String string : DATE_FOMARTS) {
					SimpleDateFormat format = new SimpleDateFormat(string);
					try {  
						date = format.parse(dateString);  
						break;
					} catch (Exception e) {  
						continue;
					}  
				}
			}  
		}  
		return date; 
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map arg0, Object arg1) {
		Date date = (Date) arg0;  
		return DateUtils.date2Str(date);
	}

}
