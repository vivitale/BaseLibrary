package talex.zsw.baselibrary.util;

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

		return getDateTimeString( Calendar.getInstance() );
	}

	/**
	 * 取得当前日期时间字符串
	 *
	 * @return
	 */
	public static String getDateTimeNow(String format)
	{

		return getCalDateString( Calendar.getInstance(), format );
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
			SimpleDateFormat dateFormat =
				new SimpleDateFormat( STR_FOMATER_DATA_TIME, Locale.CHINA );
			if(null == c)
			{

				c = Calendar.getInstance();
			}
			Date date = c.getTime();
			result = dateFormat.format( date );
		} catch(Exception e)
		{
			KLog.e( e.toString() );
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
			SimpleDateFormat dateFormat = new SimpleDateFormat( format, Locale.CHINA );
			if(null == c)
			{

				c = Calendar.getInstance();
			}
			Date date = c.getTime();
			result = dateFormat.format( date );
		} catch(Exception e)
		{
			KLog.e( e.toString() );
		}
		return result;
	}

	/**
	 * 按格式取得日期时间字符串
	 *
	 */
	public static Date getDate(String value, String format)
	{
		Date result = null;
		if(!StringUtils.isBlank( value ))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat( format, Locale.CHINA );
			try
			{
				result = dateFormat.parse( value );
			} catch(ParseException e)
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
	 */
	public static Calendar getCalendar(String value, String format)
	{
		Calendar result = null;
		if(!StringUtils.isBlank( value ))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat( format, Locale.CHINA );

			try
			{
				Date date = dateFormat.parse( value );
				result = Calendar.getInstance();
				result.setTime( date );
			} catch(ParseException e)
			{

				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getDateStr(long millis)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( millis );
		return getDateTimeString( cal );
	}

	public static String changeFormat(String value, String oldFormat, String newFormat)
	{
		Calendar calendar = getCalendar( value, oldFormat );
		String result = getCalDateString( calendar, newFormat );
		return result;
	}

	/**
	 * 按格式取得日期时间字符串
	 *
	 */
	public static String getDateString(Date value, String format)
	{
		String result = null;
		if(null != value)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat( format, Locale.CHINA );

			try
			{
				result = dateFormat.format( value );
			} catch(Exception e)
			{

				e.printStackTrace();
			}
		}
		return result;
	}

	// 给定一个日期型字符串，返回加减n天后的日期型字符串
	public static String nDaysAfterOneDateString(String basicDate, int n)
	{
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		Date tmpDate = null;
		try
		{
			tmpDate = df.parse( basicDate );
		} catch(Exception e)
		{
			// 日期型字符串格式错误
		}
		long nDay = (tmpDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n) * (24 * 60 * 60 * 1000);
		tmpDate.setTime( nDay );

		return df.format( tmpDate );
	}

	/**
	 * @param nowTime
	 * @return 将一个long型时间转换成 2013-03-03 形式的时间
	 */
	public static String refFormatDate(long nowTime)
	{
		SimpleDateFormat sdFormatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		String retStrFormatNowDate = sdFormatter.format( nowTime );
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
		calendar.setTime( date );
		calendar.add( calendar.DATE, day );// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
		dateString = formatter.format( date );
		// System.out.println(dateString);
		return dateString;
	}

	/**
	 * 推迟的天数
	 */
	public static Date getriqi(Date date, int day)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime( date );
		calendar.add( calendar.DATE, day );// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		return date;
	}

	/**
	 * 判断时间是否在时间段内
	 *
	 * @param strDate      当前时间 yyyy-MM-dd HH:mm:ss
	 * @param strDateBegin 开始时间 yyyy-MM-dd HH:mm:ss
	 * @param strDateEnd   结束时间 yyyy-MM-dd HH:mm:ss
	 */
	public static boolean isInDate(String strDate, String strDateBegin, String strDateEnd)
	{
		// 截取当前时间时分秒
		int strDateY = Integer.parseInt( strDate.substring( 0, 4 ) );
		int strDateM = Integer.parseInt( strDate.substring( 5, 7 ) );
		int strDateD = Integer.parseInt( strDate.substring( 8, 10 ) );
		// 截取开始时间时分秒
		int strDateBeginY = Integer.parseInt( strDateBegin.substring( 0, 4 ) );
		int strDateBeginM = Integer.parseInt( strDateBegin.substring( 5, 7 ) );
		int strDateBeginD = Integer.parseInt( strDateBegin.substring( 8, 10 ) );
		// 截取结束时间时分秒
		int strDateEndY = Integer.parseInt( strDateEnd.substring( 0, 4 ) );
		int strDateEndM = Integer.parseInt( strDateEnd.substring( 5, 7 ) );
		int strDateEndD = Integer.parseInt( strDateEnd.substring( 8, 10 ) );

		Date date = new Date( strDateY, strDateM, strDateD, 12, 0 );
		Date dateBegin = new Date( strDateBeginY, strDateBeginM, strDateBeginD, 0, 0 );
		Date dateEnd = new Date( strDateEndY, strDateEndM, strDateEndD, 23, 59 );

		if(date.getTime() > dateBegin.getTime() && date.getTime() < dateEnd.getTime())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 *字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate,String bdate) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}

}
