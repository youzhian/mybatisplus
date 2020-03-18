/*
 * 功能概要：日期工具
 * 
 * [变更履历]
 * 2015年5月18日 新谓来 姚宇华 新建
 * 
 */
package com.wofang.mybatisplus.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 功能概要：日期工具<br>
 *
 * @author 新谓来 姚宇华
 *
 */
public final class DateUtil {

	/**
	 * 
	 * 构造函数<br>
	 * 
	 */
	private DateUtil() {

	}

	/**
	 * 
	 * 创建金融交易附件文件夹<br>
	 * 
	 * @return 结果
	 */
	public static String creataFinancialTradeFolder() {
		return createTimeStamp() + (int) (10 + Math.random() * (99 - 10 + 1));
	}

	/**
	 * 
	 * 给文件名加时间戳<br>
	 * 
	 * @param file 文件名
	 * @return 结果
	 */
	public static String addTimeStamp(String file) {
		return createTimeStamp() + (int) (10 + Math.random() * (99 - 10 + 1)) + file;
	}

	/**
	 * 
	 * 创建时间戳至秒<br>
	 * 默认Format格式：yyyyMMddHHmmss <br>
	 * 
	 * @return 结果
	 */
	public static String createTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(System.currentTimeMillis());
	}

	/**
	 * 
	 * 创建时间戳至秒<br>
	 * 默认Format格式：yyyy-MM-dd HH:mm:ss <br>
	 * 
	 * @add 2016-12-22 <br>
	 * @return 结果
	 */
	public static String createTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(System.currentTimeMillis());
	}

	/**
	 * 
	 * 获取系统日期<br>
	 * 
	 * @param format 时间格式
	 * @return 结果
	 */
	public static String getSysDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(System.currentTimeMillis());
	}

	/**
	 * 
	 * 获取系统日期<br>
	 * 
	 * @return 结果
	 */
	public static String getSysDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(System.currentTimeMillis());
	}

	/**
	 * 
	 * 创建时间戳至毫秒<br>
	 * 
	 * @return 结果
	 */
	public static String createTimeStampToMill() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(System.currentTimeMillis());
	}

	/**
	 * [获取当前时间(Timestamp格式)]<br>
	 * 
	 * @return 当前时间
	 */
	public static Timestamp now_Timestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * [获取当前时间(util.Date格式)]<br>
	 * 
	 * @return 当前时间
	 */
	public static Date now_Date() {
		return new Date();
	}

	/**
	 * [获取两个日期之间的分钟差]<br>
	 * 
	 * @param beginDate 开始日
	 * @param endDate 结束日
	 * @return 小时
	 * @throws Exception 异常
	 */
	public static long minuteBetween(String beginDate, String endDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(beginDate.replace('/', '-')));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(endDate.replace('/', '-')));
		long time2 = cal.getTimeInMillis();
		long seconds = (long) (time2 - time1) / 1000;

		return seconds;
	}

	/**
	 * [获取两个日期之间的小时差]<br>
	 * 
	 * @param beginDate 开始日
	 * @param endDate 结束日
	 * @return 小时
	 * @throws Exception 异常
	 */
	public static float hoursBetween(String beginDate, String endDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(beginDate.replace('/', '-')));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(endDate.replace('/', '-')));
		long time2 = cal.getTimeInMillis();
		float between_days = (float) (time2 - time1) / (float) (1000 * 3600);

		return between_days;
	}

	/**
	 * [获取两个日期之间的天数差]<br>
	 * 
	 * @param beginDate 开始日
	 * @param endDate 结束日
	 * @return 天数差
	 * @throws Exception 异常
	 */
	public static int daysBetween(String beginDate, String endDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(beginDate.replace('/', '-')));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(endDate.replace('/', '-')));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * [获取两个日期之间的天数差]<br>
	 * 
	 * @param beginDate 开始日
	 * @param endDate 结束日
	 * @return 天数差
	 * @throws Exception 异常
	 */
	public static int daysBetween(Date beginDate, Date endDate) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * [根据日期和天数间隔获取对应日期]<br>
	 * 
	 * @param beginDate 开始日
	 * @param days 间隔天数
	 * @return 日期
	 * @throws Exception 异常
	 */
	public static Date getDate(String beginDate, int days) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(beginDate.replace('/', '-')));
		long time1 = cal.getTimeInMillis();
		long time2 = time1 + (long) days * 1000 * 3600 * 24;

		return new Date(time2);
	}

	/**
	 * [根据日期和天数间隔获取对应日期]<br>
	 * 
	 * @param beginDate 开始日
	 * @param days 间隔天数
	 * @return 日期
	 * @throws Exception 异常
	 */
	public static Date getDate(Date beginDate, int days) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		long time1 = cal.getTimeInMillis();
		long time2 = time1 + (long) days * 1000 * 3600 * 24;

		return new Date(time2);
	}

	/**
	 * [把字符串转化为Date类型]<br>
	 * 
	 * @param dateStr 日期字符串
	 * @return 日期对象
	 * @throws Exception 异常
	 */
	public static Date getDateFromStr(String dateStr) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(dateStr.replace('/', '-'));
		return date;
	}

	/**
	 * [获取两个日期之间的月数]<br>
	 * 
	 * @param beginDate 开始日
	 * @param endDate 结束日
	 * @return 天数差
	 * @throws Exception 异常
	 */
	public static int getmonthsBetween(Date beginDate, Date endDate) throws Exception {

		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		int year_start = cal.get(Calendar.YEAR) - 1900;
		int month_start = cal.get(Calendar.MONTH);
		int day_start = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(endDate);
		int year_end = cal.get(Calendar.YEAR) - 1900;
		int month_end = cal.get(Calendar.MONTH);
		int day_end = cal.get(Calendar.DAY_OF_MONTH);

		/*
		 * 获取月数的方案 持有月数=（X-M）*12+（Y-N）+修正值 当Z+15-K≥1时，修正值是0 当Z+15-K<1时，修正值是-1
		 */
		int modified_value = 0;
		if (day_end - day_start + 15 < 1) {
			modified_value = -1;
		}

		return (year_end - year_start) * 12 + (month_end - month_start) + modified_value;

	}

	/**
	 * 
	 * [获取当前时间，格式yyyyMMddHHmmss]<br>
	 * 
	 * @return 返回值
	 */
	public static String getCurrentDateTimeStr() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String timeString = dataFormat.format(date);
		return timeString;
	}

	/**
	 * 
	 * 时间转换<br>
	 * 
	 * @param date date
	 * @return 结果
	 */
	public static String parseDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (null != date) {
			return sdf.format(date);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 时间转换<br>
	 * 
	 * @param date 日期
	 * @param format 格式
	 * @return 结果
	 * @exception Exception 异常
	 */
	public static Date string2Date(String date, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}

	/**
	 * 
	 * 时间转换<br>
	 * 
	 * @param date date
	 * @return 结果
	 */
	public static String parseDateStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null != date) {
			return sdf.format(date);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 时间转换<br>
	 * 
	 * @param date date
	 * @return 结果
	 */
	public static String formatYmdhm(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (null != date) {
			return sdf.format(date);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * [时间格式化]<br>
	 * 
	 * @param fmtDate date
	 * @return 结果
	 * @exception ParseException 日期转换异常
	 */
	public static String formatYmdhm(String fmtDate) throws ParseException {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (!StringUtils.isEmpty(fmtDate)) {
			return dataFormat.format(dataFormat.parse(fmtDate));
		} else {
			return null;
		}
	}

	/**
	 * 
	 * [时间格式化]<br>
	 * 
	 * @param fmtDate date
	 * @return 结果
	 */
	public static String getFormatDate(String fmtDate) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		String timeString = "";
		if (StringUtils.isEmpty(fmtDate)) {
			Date date = new Date();
			timeString = dataFormat.format(date);
		} else {
			timeString = dataFormat.format(fmtDate);
		}
		return timeString;

	}

	/**
	 * 日期加减计算<br>
	 * 
	 * @param date 日期
	 * @param day 天数
	 * @throws ParseException 异常
	 * @return 计算后的日期
	 * 
	 */
	public static String addDay(String date, int day) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date(f.parse(date).getTime() + new BigDecimal(24 * 3600 * 1000).multiply(new BigDecimal(day)).longValue());
		return f.format(d);
	}

	/**
	 * 日期减计算<br>
	 * 
	 * @param date 日期
	 * @param day 天数
	 * @throws ParseException 异常
	 * @return 计算后的日期
	 * 
	 */
	public static String subtractDay(String date, int day) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date(new BigDecimal(f.parse(date).getTime()).subtract(new BigDecimal(day * 24 * 3600 * 1000)).longValue());
		return f.format(d);
	}

	/**
	 * 获得开始到结束之间的所有日期yyyy/MM/dd
	 * 
	 * @param beginStr 开始日期
	 * @param endStr 结束日期
	 * @return dateList
	 * @throws ParseException 异常
	 */
	public static List<String> getDateList(String beginStr, String endStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		List<String> dateList = new ArrayList<String>();
		Date begin = sdf.parse(beginStr);
		Date end = sdf.parse(endStr);
		Calendar calb = Calendar.getInstance();
		calb.setTime(begin);
		Calendar cale = Calendar.getInstance();
		cale.setTime(end);
		while (calb.getTimeInMillis() <= cale.getTimeInMillis()) {
			dateList.add(sdf.format(calb.getTime()));
			calb.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dateList;
	}

	/**
	 * 
	 * [时间格式化]<br>
	 * 
	 * @param fmtDate date
	 * @return 结果
	 * @exception ParseException 日期转换异常
	 */
	public static Date fmtStringToDate(String fmtDate) throws ParseException {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date resultDate = dataFormat.parse(fmtDate);
		return resultDate;
	}

	/**
	 * String类型日期转Date
	 * 
	 * @param date date
	 * @return Date
	 * @throws ParseException 异常
	 */
	public static Date string2Date(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date resultDate = sdf.parse(date);
		return resultDate;
	}

	/**
	 * Date类型日期转String
	 * 
	 * @param date date
	 * @return 结果
	 * @throws ParseException 异常
	 */
	public static String date2String(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(date);
		return currentTime;
	}

	/**
	 * [根据日期和天数间隔获取对应日期]<br>
	 * 
	 * @param beginDate 开始日
	 * @param days 间隔天数
	 * @return 日期
	 * @throws ParseException 异常
	 */
	public static String getDiffDate(String beginDate, int days) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(beginDate.replace('/', '-')));
		long time1 = cal.getTimeInMillis();
		long time2 = time1 + (long) days * 1000 * 3600 * 24;
		// 进行时间转换
		String date = sdf.format(new Date(time2));
		return date;
	}

	/**
	 * [当前日期和传入日期相差的分钟] <br>
	 * Math.abs() <br>
	 * 
	 * @param dataTime 日期
	 * @return long 0
	 * @throws ParseException 异常
	 */
	public static long getMinutes(String dataTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(dataTime));
		long time1 = cal.get(Calendar.MINUTE);
		cal.setTime(new Date());
		long time2 = cal.get(Calendar.MINUTE);
		long minutes = time2 - time1;
		return Math.abs(minutes);
	}

	/**
	 * [当前日期和传入日期相差的秒数] <br>
	 * Math.abs() <br>
	 * 
	 * @param dataTime 日期
	 * @return long 0
	 * @throws ParseException 异常
	 */
	public static long getSeconds(String dataTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(dataTime));
		long time1 = cal.getTimeInMillis();
		cal.setTime(new Date());
		long time2 = cal.getTimeInMillis();
		long seconds = time2 - time1;
		return Math.abs(seconds) / 1000;
	}

	/**
	 * 
	 * [判断now日期是否在两个date之间]<br>
	 * 
	 * @param begin 开始日期
	 * @param end 结束日期
	 * @param now 当前时间
	 * @return 是否
	 * @throws ParseException 异常
	 */
	public static boolean isDateBetween(Date begin, Date end, String now) throws ParseException {
		Date nowDate = string2Date(now);
		return nowDate.compareTo(begin) >= 0 && nowDate.compareTo(end) <= 0;
	}

}
