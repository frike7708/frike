package com.zhongan.dwe.common.utils;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils{
	/** 年-月-日  */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/** 年-月-日  时:分:秒 */
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/** 月-日  */
	public static final String DATE_MD_FORMAT = "MM-dd";
	
	/** 月-日 时:分  */
	public static final String DATE_MDHM_FORMAT = "MM-dd HH:mm";
	
	/** 时:分  */
	public static final String DATE_HM_FORMAT = "HH:mm";
	
	public static Timestamp[] todayPeriod() {
		Timestamp[] timeMap = new Timestamp[2];
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		Timestamp startTime = new Timestamp(now.getTimeInMillis());
		now.set(Calendar.HOUR_OF_DAY, 23);
		now.set(Calendar.MINUTE, 59);
		now.set(Calendar.SECOND, 59);
		now.set(Calendar.MILLISECOND, 999);
		Timestamp endTime = new Timestamp(now.getTimeInMillis());
		timeMap[0] = startTime;
		timeMap[1] = endTime;
		return timeMap;
	}

	public static Timestamp[] yesterdayPeriod() {
		Timestamp[] timeMap = new Timestamp[2];
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 1);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		Timestamp startTime = new Timestamp(now.getTimeInMillis());
		now.set(Calendar.HOUR_OF_DAY, 23);
		now.set(Calendar.MINUTE, 59);
		now.set(Calendar.SECOND, 59);
		now.set(Calendar.MILLISECOND, 999);
		Timestamp endTime = new Timestamp(now.getTimeInMillis());
		timeMap[0] = startTime;
		timeMap[1] = endTime;
		return timeMap;
	}
	
	/**
	 * 将日期格式化为指定格式的日期字符串
	 * @author	sjq
	 * @param 	date 	日期
	 * @param 	format 	格式化模型,如："yyyy-MM-dd HH:mm:ss"
	 * @return 	格式化后的日期字符串
	 */
	public static String formatDate(Date date, String format)
	{
		if (date == null)
		{
			return "";
		}
		DateFormat df = new SimpleDateFormat(format);
		String dateString = df.format(date);
		return dateString;
	}

	/**
	 * 将日期字符串转换成指定格式的日期
	 * @author	sjq
	 * @param 	dateString 		日期字符串
	 * @param 	format 			格式化模型,如："yyyy-MM-dd HH:mm:ss"
	 * @return 	date 			指定格式的日期
	 */
	public static Date parseDate(String dateString, String format)
	{
		Date date = null;
		try 
		{
			DateFormat df = new SimpleDateFormat(format);
			date = df.parse(dateString);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取两个日期间相差的天数
	 * @author 	sjq
	 * @param 	calendar1		日期1
	 * @param 	calendar2		日期2
	 * @return 	两个日期间相差的天数
	 */
	public static int getDaysBetween(Calendar calendar1, Calendar calendar2) 
	{
		if (calendar1.after(calendar2))
		{
			Calendar calendar = calendar1;
			calendar1 = calendar2;
			calendar2 = calendar;
		}
		int days = calendar2.get(Calendar.DAY_OF_YEAR) - calendar1.get(Calendar.DAY_OF_YEAR);
		int y2 = calendar2.get(Calendar.YEAR);
		if(calendar1.get(Calendar.YEAR) != y2)
		{
			calendar1 = (Calendar) calendar1.clone();
			do
			{
				days += calendar1.getActualMaximum(6);
				calendar1.add(1, 1);
			}
			while (calendar1.get(1) != y2);
		}
		return days;
	}
	
	
	/**
	 * 毫秒值转换成    yyyy-MM-dd HH:mm:ss 日期格式
	 * @param timeMillis
	 * @return
	 */
	public static String formatTimeMillis(String timeMillis){
		String result = "";
		try{
			DateFormat formatter = new SimpleDateFormat(DATETIME_FORMAT);
	        long now = Long.valueOf(timeMillis);
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(now);
	        result = formatter.format(calendar.getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}