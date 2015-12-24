package talex.zsw.baselibrary.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称: BaseProject
 * 作用: 正则表达式判断
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-04-0004 14:08 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressWarnings("ALL") public class MatcherUtils
{
	//用于获取字符串中的版本
	public static Matcher appVersionMatcher = Pattern.compile("(\\d+(\\.{1}[\\d_]+)+)").matcher("");

	public static String getAppVersion(String version)
	{
		if (!StringUtils.isBlank(version))
		{
			synchronized (appVersionMatcher)
			{
				appVersionMatcher.reset(version);
				if (appVersionMatcher.find())
				{
					return appVersionMatcher.group();
				}
			}
		}
		return "";
	}

	//获取字符串中的数字
	private static Matcher numberMatcher = Pattern.compile("[\\d万]+").matcher("");

	public static String getNumberFromText(String text)
	{
		if (!StringUtils.isBlank(text))
		{
			synchronized (numberMatcher)
			{
				numberMatcher.reset(text);
				if (numberMatcher.find())
				{
					return numberMatcher.group();
				}
			}
		}
		return "";
	}

	//获取字符串中的日期2012-10-22或2012/10/22
	private static Matcher dateMatcher1 =
		Pattern.compile("\\d{4}[/-]{1}\\d{2}[/-]{1}\\d{2}").matcher("");

	public static String getDate1FromText(String text)
	{
		if (!StringUtils.isBlank(text))
		{
			synchronized (dateMatcher1)
			{
				dateMatcher1.reset(text);
				if (dateMatcher1.find())
				{
					return dateMatcher1.group();
				}
			}
		}
		return "";
	}

	//获取字符串中的文件大小  2.3MB  2.5kb
	private static Matcher sizeMatcher = Pattern.compile("(?i)[\\d\\.MKBmkb]+").matcher("");

	public static String getAppSizeFromText(String text)
	{
		if (!StringUtils.isBlank(text))
		{
			text = text.replace(" ", "");
			synchronized (sizeMatcher)
			{
				sizeMatcher.reset(text);
				if (sizeMatcher.find())
				{
					return sizeMatcher.group();
				}
			}
		}
		return "";
	}
}
