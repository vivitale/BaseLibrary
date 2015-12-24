package talex.zsw.baselibrary.util;


import java.text.DecimalFormat;

public class MathUtils
{

	/**
	 * 保留一位小数
	 */
	public static String doubleToStringWith1(double f)
	{
		DecimalFormat df = new DecimalFormat("#.0");
		if (f == 0)
		{
			return "0.0";
		}
		if (f < 1)
		{
			return "0" + df.format(f);
		}
		return df.format(f);
	}

	/**
	 * 保留两位小数
	 */
	public static String doubleToStringWith2(double f)
	{
		DecimalFormat df = new DecimalFormat("#.00");
		if (f == 0)
		{
			return "0.00";
		}
		if (f < 1)
		{
			return "0" + df.format(f);
		}
		return df.format(f);
	}

	/**
	 * 保留两位小数
	 */
	public static String StringToStringWith2(String string)
	{
		if (StringUtils.isBlank(string))
		{
			return "0.00";
		}
		double f = Double.valueOf(string);
		DecimalFormat df = new DecimalFormat("#.00");
		if (f == 0)
		{
			return "0.00";
		}
		if (f < 1)
		{
			return "0" + df.format(f);
		}
		return df.format(f);
	}

	/**
	 * 删除String前面的0
	 */
	public static String DeleteZero(String string)
	{
		while (string.length() > 1 && string.charAt(0) == '0')
		{
			string = string.substring(1);
		}
		return string;
	}

	/**
	 * 加法
	 */
	public static String getSum(String number1, String number2)
	{
		double num1 = 0;
		double num2 = 0;
		if (!StringUtils.isBlank(number1))
		{
			num1 = Double.valueOf(DeleteZero(number1));
		}
		if (!StringUtils.isBlank(number2))
		{
			num2 = Double.valueOf(DeleteZero(number2));
		}
		double sum = num1 + num2;
		return doubleToStringWith2(sum);
	}

	/**
	 * 减法
	 */
	public static String getSub(String number1, String number2)
	{
		double num1 = 0;
		double num2 = 0;
		if (!StringUtils.isBlank(number1))
		{
			num1 = Double.valueOf(DeleteZero(number1));
		}
		if (!StringUtils.isBlank(number2))
		{
			num2 = Double.valueOf(number2);
		}
		double sub = num1 - num2;
		return doubleToStringWith2(sub);
	}

	/**
	 * 乘法
	 */
	public static String getMul(String number1, String number2)
	{
		double num1 = 0;
		double num2 = 0;
		if (!StringUtils.isBlank(number1))
		{
			num1 = Double.valueOf(DeleteZero(number1));
		}
		if (!StringUtils.isBlank(number2))
		{
			num2 = Double.valueOf(number2);
		}
		double sub = num1 * num2;
		return doubleToStringWith2(sub);
	}

	/**
	 * 除法 number1/number2
	 */
	public static String getDiv(String number1, String number2)
	{
		double num1 = 0;
		double num2 = 0;
		if (!StringUtils.isBlank(number1))
		{
			num1 = Double.valueOf(DeleteZero(number1));
		}
		if (!StringUtils.isBlank(number2))
		{
			num2 = Double.valueOf(number2);
		}
		double sub = num1 / num2;
		return doubleToStringWith2(sub);
	}
}
