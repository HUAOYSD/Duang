package org.duang.util; 

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 日期工具类
 * @ClassName: DateUtil 
 * @Description: TODO 
 * @author 白攀 
 * @date 2016-7-17 上午11:52:40 
 */
public class DateUtils {
	/**
	 * 时间范围：年
	 */
	public static final int YEAR = 1;

	/**
	 * 时间范围：季度
	 */
	public static final int QUARTER = 2;

	/**
	 * 时间范围：月
	 */
	public static final int MONTH = 3;

	/**
	 * 时间范围：旬
	 */
	public static final int TENDAYS = 4;

	/**
	 * 时间范围：周
	 */
	public static final int WEEK = 5;

	/**
	 * 时间范围：日
	 */
	public static final int DAY = 6;

	/* 基准时间 */
	private Date fiducialDate = null;

	private Calendar cal = null;

	private DateUtils(Date fiducialDate) {
		if (fiducialDate != null) {
			this.fiducialDate = fiducialDate;
		} else {
			this.fiducialDate = new Date(System.currentTimeMillis());
		}
		this.cal = Calendar.getInstance();
		this.cal.setTime(this.fiducialDate);
		this.cal.set(Calendar.HOUR_OF_DAY, 0);
		this.cal.set(Calendar.MINUTE, 0);
		this.cal.set(Calendar.SECOND, 0);
		this.cal.set(Calendar.MILLISECOND, 0);
		this.fiducialDate = this.cal.getTime();
	}

	/**
	 * 获取DateUtils实例 date为空就是当前时间
	 * @return Date
	 */
	public static DateUtils getInstance(Date date) {
		return new DateUtils(date);
	}

	/**
	 * 获取DateHelper实例, 使用当前时间作为基准时间
	 * @return Date
	 */
	public static DateUtils getInstance() {
		return new DateUtils(null);
	}

	/**
	 * 获取年的第一天
	 * @param offset	偏移量
	 * @return Date
	 */
	public Date getFirstDayOfYear(int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + offset);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取年的最后一天
	 * @param offset	偏移量
	 * @return Date
	 */
	public Date getLastDayOfYear(int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + offset);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return cal.getTime();
	}

	/**
	 * 获取季度的第一天
	 * @param offset	偏移量
	 * @return Date
	 */
	public Date getFirstDayOfQuarter(int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.MONTH, offset * 3);
		int mon = cal.get(Calendar.MONTH);
		if (mon >= Calendar.JANUARY && mon <= Calendar.MARCH) {
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (mon >= Calendar.APRIL && mon <= Calendar.JUNE) {
			cal.set(Calendar.MONTH, Calendar.APRIL);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (mon >= Calendar.JULY && mon <= Calendar.SEPTEMBER) {
			cal.set(Calendar.MONTH, Calendar.JULY);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (mon >= Calendar.OCTOBER && mon <= Calendar.DECEMBER) {
			cal.set(Calendar.MONTH, Calendar.OCTOBER);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		return cal.getTime();
	}

	/**
	 * 获取季度的最后一天
	 * @param offset	偏移量
	 * @return Date
	 */
	public Date getLastDayOfQuarter(int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.MONTH, offset * 3);
		int mon = cal.get(Calendar.MONTH);
		if (mon >= Calendar.JANUARY && mon <= Calendar.MARCH) {
			cal.set(Calendar.MONTH, Calendar.MARCH);
			cal.set(Calendar.DAY_OF_MONTH, 31);
		}
		if (mon >= Calendar.APRIL && mon <= Calendar.JUNE) {
			cal.set(Calendar.MONTH, Calendar.JUNE);
			cal.set(Calendar.DAY_OF_MONTH, 30);
		}
		if (mon >= Calendar.JULY && mon <= Calendar.SEPTEMBER) {
			cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
			cal.set(Calendar.DAY_OF_MONTH, 30);
		}
		if (mon >= Calendar.OCTOBER && mon <= Calendar.DECEMBER) {
			cal.set(Calendar.MONTH, Calendar.DECEMBER);
			cal.set(Calendar.DAY_OF_MONTH, 31);
		}
		return cal.getTime();
	}

	/**
	 * 获取月的最后一天
	 * @param offset  偏移量
	 * @return Date
	 */
	public Date getFirstDayOfMonth(int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.MONTH, offset);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取月的最后一天
	 * @param offset  偏移量
	 * @return Date
	 */
	public Date getLastDayOfMonth(int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.MONTH, offset + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 获取旬的第一天
	 * @param offset  偏移量
	 * @return Date
	 */
	public Date getFirstDayOfTendays(int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day >= 21) {
			day = 21;
		} else if (day >= 11) {
			day = 11;
		} else {
			day = 1;
		}
		if (offset > 0) {
			day = day + 10 * offset;
			int monOffset = day / 30;
			day = day % 30;
			cal.add(Calendar.MONTH, monOffset);
			cal.set(Calendar.DAY_OF_MONTH, day);
		} else {
			int monOffset = 10 * offset / 30;
			int dayOffset = 10 * offset % 30;
			if ((day + dayOffset) > 0) {
				day = day + dayOffset;
			} else {
				monOffset = monOffset - 1;
				day = day - dayOffset - 10;
			}
			cal.add(Calendar.MONTH, monOffset);
			cal.set(Calendar.DAY_OF_MONTH, day);
		}
		return cal.getTime();
	}

	/**
	 * 获取旬的最后一天
	 * @param offset	 偏移量
	 *           
	 * @return Date
	 */
	public Date getLastDayOfTendays(int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		Date date = getFirstDayOfTendays(offset + 1);
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 获取周的第一天(MONDAY)
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date getFirstDayOfWeek(int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.DAY_OF_MONTH, offset * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 获取周的最后一天(SUNDAY)
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date getLastDayOfWeek(int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.DAY_OF_MONTH, offset * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DAY_OF_MONTH, 6);
		return cal.getTime();
	}


	/**
	 * 根据日历的规则，为基准时间添加指定日历字段的时间量
	 * @param field	日历字段, 使用Calendar类定义的日历字段常量
	 * @param offset	 偏移量
	 * @return Date
	 */
	public Date add(int field, int offset) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		cal.add(field, offset);
		return cal.getTime();
	}

	/**
	 * 根据日历的规则，为基准时间添加指定日历字段的单个时间单元
	 * @param field	 日历字段, 使用Calendar类定义的日历字段常量
	 * @param up 指定日历字段的值的滚动方向。true:向上滚动 / false:向下滚动
	 * @return Date
	 */
	public Date roll(int field, boolean up) {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		cal.setTime(this.fiducialDate);
		cal.roll(field, up);
		return cal.getTime();
	}

	/**
	 * 把字符串转换为日期
	 * @param dateStr	 日期字符串
	 * @param format	日期格式
	 * @return Date
	 */
	public static Date str2Date(String dateStr, String format) {
		Date date = null;
		if (dateStr != null && (!dateStr.equals(""))) {
			DateFormat df = new SimpleDateFormat(format);
			try {
				date = df.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 把字符串转换为日期，日期的格式为yyyy-MM-dd HH:ss
	 * @param dateStr	日期字符串
	 * @return Date
	 */
	public static Date str2Date(String dateStr) {
		Date date = null;
		if (dateStr != null && (!dateStr.equals(""))) {
			if (dateStr.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
				dateStr = dateStr + " 00:00";
			} else if (dateStr.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}")) {
				dateStr = dateStr + ":00";
			} else {
				System.out.println(dateStr + " 格式不正确");
				return null;
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss");
			try {
				date = df.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 把日期转换为字符串
	 * @param date	日期实例
	 * @param format	日期格式
	 * @return Date
	 */
	public static String date2Str(Date date, String format) {
		return (date == null) ? "" : new SimpleDateFormat(format).format(date);
	}

	/**
	 * 把日期转换为字符串（默认：yyyy-MM-dd hh:mm:ss）
	 * @param date	日期实例
	 * @param format	日期格式
	 * @return Date
	 */
	public static String date2Str(Date date) {
		return (date == null) ? "" : new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
	}

	/**
	 * 取得当前日期 年-月-日
	 * @return Date
	 */
	public static String getCurrentDate() {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(Calendar.getInstance().getTime());
	}

	/**
	 * 取得当前日期 年-月-日
	 * @return Date
	 */
	public static String getCurrentDate(String format) {
		DateFormat f = new SimpleDateFormat(format);
		return f.format(Calendar.getInstance().getTime());
	}

	/**
	 * 取当前日期的字符串形式,"XXXX年XX月XX日"
	 * @return java.lang.String
	 */
	public static String getPrintDate() {
		String printDate = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		printDate += calendar.get(Calendar.YEAR) + "年";
		printDate += (calendar.get(Calendar.MONTH) + 1) + "月";
		printDate += calendar.get(Calendar.DATE) + "日";
		return printDate;
	}

	/**
	 * 将指定的日期字符串转化为日期对象
	 * @param dateStr 日期字符串
	 * @return java.util.Date
	 */
	public static Date getDate(String dateStr, String format) {
		if (dateStr == null) {
			return new Date();
		}
		if (format == null) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(dateStr);
			return date;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**   
	 * 获取时间戳值
	 * @Title: getTimeStamp   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param date
	 * @param: @return  
	 * @author 白攀    
	 * @date 2016年7月27日 上午11:06:18
	 * @return: long      
	 * @throws   
	 */  
	public static long getTimeStamp(Date date){
		if (date!=null) {
			return date.getTime();
		}else{
			return 0;
		}
	}
	

	/**
	 * 从指定Timestamp中得到相应的日期的字符串形式 日期"XXXX-XX-XX"
	 * @param dateTime
	 * @return 、String
	 */
	public static String getDateFromDateTime(Timestamp dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dateTime).toString();
	}

	/**
	 * 得到当前时间 return java.sql.Timestamp
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getNowTimestamp() {
		long curTime = System.currentTimeMillis();
		return new Timestamp(curTime);
	}

	/**
	 * 获取昨天这个时候
	 * getYesterday(这里用一句话描述这个方法的作用)
	 * @Title: getYesterday
	 * @Description: TODO
	 * @param @return    
	 * @return Date    返回类型
	 * @author 白攀 
	 * @date 2016-7-17 上午11:59:15
	 * @throws
	 */
	public Date getYesterday() {
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		long time = this.fiducialDate.getTime() - 60 * 60 * 24 * 1000;
		return new Date(time);
	}


	/**
	 * 获取明天这个时候
	 * getTomorrow(这里用一句话描述这个方法的作用)
	 * @Title: getTomorrow
	 * @Description: TODO
	 * @param @return    
	 * @return Date    返回类型
	 * @author 白攀 
	 * @date 2016-7-17 上午11:59:32
	 * @throws
	 */
	public Date getTomorrow(){
		if (this.fiducialDate==null || cal==null) {
			getInstance();
		}
		long time = this.fiducialDate.getTime() + 60 * 60 * 24 * 1000;
		return new Date(time);
	}


	public static void main(String[] args) {
		DateUtils dateHelper = DateUtils.getInstance();
		/* Year */
		for (int i = -5; i <= 5; i++) {
			System.out.println("FirstDayOfYear(" + i + ") = "
					+ dateHelper.getFirstDayOfYear(i));
			System.out.println("LastDayOfYear(" + i + ") = "
					+ dateHelper.getLastDayOfYear(i));
		}

		/* Quarter */
		for (int i = -5; i <= 5; i++) {
			System.out.println("FirstDayOfQuarter(" + i + ") = "
					+ dateHelper.getFirstDayOfQuarter(i));
			System.out.println("LastDayOfQuarter(" + i + ") = "
					+ dateHelper.getLastDayOfQuarter(i));
		}

		/* Month */
		for (int i = -5; i <= 5; i++) {
			System.out.println("FirstDayOfMonth(" + i + ") = "
					+ dateHelper.getFirstDayOfMonth(i));
			System.out.println("LastDayOfMonth(" + i + ") = "
					+ dateHelper.getLastDayOfMonth(i));
		}

		/* Week */
		for (int i = -5; i <= 5; i++) {
			System.out.println("FirstDayOfWeek(" + i + ") = "
					+ dateHelper.getFirstDayOfWeek(i));
			System.out.println("LastDayOfWeek(" + i + ") = "
					+ dateHelper.getLastDayOfWeek(i));
		}

		/* Tendays */
		for (int i = -5; i <= 5; i++) {
			System.out.println("FirstDayOfTendays(" + i + ") = "
					+ dateHelper.getFirstDayOfTendays(i));
			System.out.println("LastDayOfTendays(" + i + ") = "
					+ dateHelper.getLastDayOfTendays(i));
		}
	}

}

