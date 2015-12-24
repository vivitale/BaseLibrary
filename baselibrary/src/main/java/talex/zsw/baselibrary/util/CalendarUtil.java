package talex.zsw.baselibrary.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import talex.zsw.baselibrary.util.klog.KLog;

/**
 * 时间帮助类
 */
public class CalendarUtil
{

	public static String STR_FOMATER_DATA_TIME = "yyyy-MM-dd HH:mm:ss";
	public static String STR_FOMATER_DATA = "yyyy-MM-dd";
	public static String STR_FOMATER_DATA2 = "yyyy-M-d";
	public static String STR_FOMATER_DATA3 = "yyyy-MM-dd F";
	public static String STR_FOMATER_DATA4 = "yyyy-MM-dd   EEEE";
	public static String STR_FOMATER_DATA5 = "MM/dd";

	/**
	 * 取得当前日期时间字符串
	 *
	 * @return
	 */
	public static String getDateTimeNow()
	{

		return getDateTimeString(Calendar.getInstance());
	}

	/**
	 * 取得当前日期时间字符串
	 *
	 * @return
	 */
	public static String getDateTimeNow(String format)
	{

		return getCalDateString(Calendar.getInstance(), format);
	}

	/**
	 * 取得日期时间字符串
	 *
	 * @param c
	 * @return
	 */
	public static String getDateTimeString(Calendar c)
	{
		String result = null;
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(
				STR_FOMATER_DATA_TIME, Locale.CHINA);
			if (null == c)
			{

				c = Calendar.getInstance();
			}
			Date date = c.getTime();
			result = dateFormat.format(date);
		} catch (Exception e)
		{
			KLog.e(e.toString());
		}
		return result;
	}

	/**
	 * 按格 式取得日期时间字符串
	 *
	 * @param c
	 * @return
	 */
	public static String getCalDateString(Calendar c, String format)
	{
		String result = null;
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(format,
				Locale.CHINA);
			if (null == c)
			{

				c = Calendar.getInstance();
			}
			Date date = c.getTime();
			result = dateFormat.format(date);
		} catch (Exception e)
		{
			KLog.e(e.toString());
		}
		return result;
	}

	/**
	 * 按格式取得日期时间字符串
	 *
	 * @param c
	 * @return
	 */
	public static Date getDate(String value, String format)
	{
		Date result = null;
		if (!StringUtils.isBlank(value))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(format,
				Locale.CHINA);
			try
			{
				result = dateFormat.parse(value);
			} catch (ParseException e)
			{
				result = Calendar.getInstance().getTime();
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 按格式取得日期时间字符串
	 *
	 * @param c
	 * @return
	 */
	public static Calendar getCalendar(String value, String format)
	{
		Calendar result = null;
		if (!StringUtils.isBlank(value))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(format,
				Locale.CHINA);

			try
			{
				Date date = dateFormat.parse(value);
				result = Calendar.getInstance();
				result.setTime(date);
			} catch (ParseException e)
			{

				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getDateStr(long millis)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return getDateTimeString(cal);
	}

	public static String changeFormat(String value, String oldFormat,
									  String newFormat)
	{
		Calendar calendar = getCalendar(value, oldFormat);
		String result = getCalDateString(calendar, newFormat);
		return result;
	}

	/**
	 * 按格式取得日期时间字符串
	 *
	 * @param c
	 * @return
	 */
	public static String getDateString(Date value, String format)
	{
		String result = null;
		if (null != value)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(format,
				Locale.CHINA);

			try
			{
				result = dateFormat.format(value);
			} catch (Exception e)
			{

				e.printStackTrace();
			}
		}
		return result;
	}

	// 给定一个日期型字符串，返回加减n天后的日期型字符串
	public static String nDaysAfterOneDateString(String basicDate, int n)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date tmpDate = null;
		try
		{
			tmpDate = df.parse(basicDate);
		} catch (Exception e)
		{
			// 日期型字符串格式错误
		}
		long nDay = (tmpDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n)
			* (24 * 60 * 60 * 1000);
		tmpDate.setTime(nDay);

		return df.format(tmpDate);
	}

	/**
	 * @param nowTime
	 * @return 将一个long型时间转换成 2013-03-03 形式的时间
	 */
	public static String refFormatDate(long nowTime)
	{
		SimpleDateFormat sdFormatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}

	/**
	 * 推迟的天数
	 */
	public static String getriqi(int day)
	{
		String dateString;
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		dateString = formatter.format(date);
		// System.out.println(dateString);
		return dateString;
	}

	/**
	 * 推迟的天数
	 */
	public static Date getriqi(Date date, int day)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		return date;
	}
}
