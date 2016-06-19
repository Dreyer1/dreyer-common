package com.dreyer.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @description 日期操作工具类
 * @author Dreyer
 * @email hy.dreyer@qq.com
 * @date 2015年9月7日 下午8:45:25
 * @version 1.0
 */
public class DateUtil {

	/**
	 * 日期格式列表
	 */
	public static final String DATE_FORMAT_DATEONLY = "yyyy-MM-dd";
	public static final String DATE_FORMAT_DATEONLY_CN = "yyyy年MM月dd日";
	public static final String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_HMS = "HH:mm:ss";

	/**
	 * 格式化日期工具列表
	 */
	public static final SimpleDateFormat SDF_DATE_FORMAT = new SimpleDateFormat(
			DATE_FORMAT_DATEONLY);
	public static final SimpleDateFormat SDF_DATE_FORMAT_CN = new SimpleDateFormat(
			DATE_FORMAT_DATEONLY_CN);
	public static final SimpleDateFormat SDF_TIME_FORMAT = new SimpleDateFormat(
			DATE_FORMAT_DATETIME);
	public static final SimpleDateFormat SDF_HMS_FORMAT = new SimpleDateFormat(
			DATE_FORMAT_HMS);

	/**
	 * 字符转日期——yyyy-MM-dd格式的日期转Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String date) {

		return stringToDate(date, DATE_FORMAT_DATEONLY);
	}

	/**
	 * 字符转日期——yyyy-MM-dd HH:mm:ss格式的日期转Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date stringTimeToDate(String date) {

		return stringToDate(date, DATE_FORMAT_DATETIME);
	}

	/**
	 * 字符转日期——指定日期字符、日期格式转Date
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String date, String format) {
		if (!StringUtil.isEmpty(date) && !StringUtil.isEmpty(format)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 日期转字符——指定日期、格式转
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		if (!ObjectUtil.isEmpty(date) && !StringUtil.isEmpty(format)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		return null;
	}

	/**
	 * 日期转字符——yyyy-MM-dd格式的日期转为字符
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {

		return dateToString(date, DATE_FORMAT_DATEONLY);
	}

	/**
	 * 日期转字符——获取当前时间yyyy-MM-dd格式的日期字符
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString() {

		return dateToString(new Date());
	}

	/**
	 * 日期转字符——yyyy年MM月dd日格式的日期转为字符
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToCNString(Date date) {

		return dateToString(date, DATE_FORMAT_DATEONLY_CN);
	}

	/**
	 * 日期转字符——获取当前时间yyyy年MM月dd日格式的日期字符
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToCNString() {

		return dateToCNString(new Date());
	}

	/**
	 * 日期转字符——yyyy-MM-dd HH:mm:ss格式的日期转为字符
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToTimeString(Date date) {

		return dateToString(date, DATE_FORMAT_DATETIME);
	}

	/**
	 * 日期转字符——获取yyyy-MM-dd HH:mm:ss格式的日期字符
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToTimeString() {

		return dateToTimeString(new Date());
	}

	/**
	 * 获取当前时间毫秒数
	 * 
	 * @return
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 判断value值日期是否介于min（较早）、max（较迟）之间
	 * 
	 * @param min
	 *            较早的日期
	 * @param max
	 *            较迟的日期
	 * @param value
	 *            要判断的日期
	 * @param isContainsEqual
	 *            是否可以包含等于
	 * @return
	 */
	public static boolean isBetween(Date min, Date max, Date value,
			boolean isContainsEqual) {
		if (min == null || max == null || value == null) {
			return false;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(min);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(max);

		Calendar c3 = Calendar.getInstance();
		c3.setTime(value);

		if (isContainsEqual) {
			return (c3.after(c1) || c3.compareTo(c1) == 0)
					&& (c3.before(c2) || c3.compareTo(c2) == 0);
		}
		return (c3.after(c1) && c3.before(c2));
	}

	/**
	 * 判断value值日期是否介于min（较早）、max（较迟）之间
	 * 
	 * @param min
	 *            较早的日期
	 * @param max
	 *            较迟的日期
	 * @param value
	 *            要判断的日期
	 * @return
	 */
	public static boolean isBetween(Date min, Date max, Date value) {

		return isBetween(min, max, value, false);
	}

	/**
	 * 获取两个日期之间的间隔天数
	 * 
	 * @param min
	 *            较小的日期（距现在远）
	 * @param max
	 *            较大的日期（距现在近）
	 * @return
	 */
	public static int daysBetween(Date min, Date max) {
		Calendar minCalendar = Calendar.getInstance();
		minCalendar.setTime(min);

		Calendar maxCalendar = Calendar.getInstance();
		maxCalendar.setTime(max);

		long result = (maxCalendar.getTimeInMillis() - minCalendar
				.getTimeInMillis()) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(result));
	}

	/**
	 * 获取两个日期之间的间隔日期数据，格式为：yyyy-MM-dd，其中包括传入的开始、结束时间
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getBetweenDays(String beginDate, String endDate) {

		return getBetweenDays(beginDate, endDate, true);
	}

	/**
	 * 获取两个日期之间的间隔日期数据，格式为：yyyy-MM-dd
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param isContainSelf
	 *            是否包含开始、结束时间
	 * @return
	 */
	public static List<String> getBetweenDays(String beginDate, String endDate,
			boolean isContainSelf) {
		if (!StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate)) {
			List<String> result = new ArrayList<String>();
			Calendar begin = Calendar.getInstance();
			begin.setTime(stringToDate(beginDate));

			Calendar end = Calendar.getInstance();
			end.setTime(stringToDate(endDate));

			if (isContainSelf) {
				result.add(beginDate);
			}
			while (true) {
				begin.add(Calendar.DAY_OF_MONTH, 1);
				if (begin.getTimeInMillis() < end.getTimeInMillis()) {
					result.add(dateToString(begin.getTime()));
				} else {
					break;
				}
			}
			if (isContainSelf) {
				result.add(endDate);
			}
			return result;
		}
		return null;
	}

	/**
	 * 获取指定日期的起始时间点。
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date == null ? new Date() : date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期的的终止时间点.
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date == null ? new Date() : date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}

	/**
	 * 计算 second 秒后的时间
	 * 
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addSecond(Date date, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * 计算 minute 分钟后的时间
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 计算 hour 小时后的时间
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

	/**
	 * 计算 day 天后的时间
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	/**
	 * 计算year年后的时间
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addYear(Date date, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 365 * year);
		return calendar.getTime();
	}

	/**
	 * 判断输入日期是一个星期中的第几天(星期天为一个星期第一天)
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekIndex(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取星期几的中文描述
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekCNDesc(Date date) {
		String result = "";
		if (date != null) {
			int weekIndex = getWeekIndex(date);
			switch (weekIndex) {
			case 1:
				result = "星期日";
				break;
			case 2:
				result = "星期一";
				break;
			case 3:
				result = "星期二";
				break;
			case 4:
				result = "星期三";
				break;
			case 5:
				result = "星期四";
				break;
			case 6:
				result = "星期五";
				break;
			case 7:
				result = "星期六";
				break;
			default:
				result = "未知";
				break;
			}
		}
		return result;
	}

	/**
	 * 获取星期几的英文描述
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekENDesc(Date date) {
		String result = "";
		if (date != null) {
			int weekIndex = getWeekIndex(date);
			switch (weekIndex) {
			case 1:
				result = "Sun";
				break;
			case 2:
				result = "Mon";
				break;
			case 3:
				result = "Tue";
				break;
			case 4:
				result = "Wed";
				break;
			case 5:
				result = "Thu";
				break;
			case 6:
				result = "Fri";
				break;
			case 7:
				result = "Sat";
				break;
			default:
				result = "未知";
				break;
			}
		}
		return result;
	}

	/**
	 * 
	 * 获取指定时间所在的季度， 1： 第一季度 ；2 ：第二季度 ；3： 第三季度 ；4： 第四季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {
		int season = 0;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int month = c.get(Calendar.MONTH);
			switch (month) {
			case Calendar.JANUARY:
			case Calendar.FEBRUARY:
			case Calendar.MARCH:
				season = 1;
				break;
			case Calendar.APRIL:
			case Calendar.MAY:
			case Calendar.JUNE:
				season = 2;
				break;
			case Calendar.JULY:
			case Calendar.AUGUST:
			case Calendar.SEPTEMBER:
				season = 3;
				break;
			case Calendar.OCTOBER:
			case Calendar.NOVEMBER:
			case Calendar.DECEMBER:
				season = 4;
				break;
			default:
				break;
			}
		}
		return season;
	}
	
	/**
	 * 获取一串yyyyMMddHHmmssSSS日期格式的字符
	 * 
	 * @return
	 */
	public static String getDateRandom() {
		
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(getDateRandom());
	}
	

}
