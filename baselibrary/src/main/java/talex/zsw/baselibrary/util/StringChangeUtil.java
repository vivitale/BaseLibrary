package talex.zsw.baselibrary.util;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称: BaseProject
 * 作用: 各种编码转换类
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-04-0004 14:23 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class StringChangeUtil
{
	/**
	 * @param string 原字符串
	 * @return 转换成URL字符串 %E8
	 */
	public static String URLEncoder(String string)
	{
		string = URLEncoder.encode(string);
		return string;
	}

	/**
	 * @param string 原字符串
	 * @return 将%E8转换成中文
	 */
	public static String URLDecoder(String string)
	{
		string = URLDecoder.decode(string);
		return string;
	}

	/**
	 * 把中文字符串转换为十六进制Unicode编码字符串
	 */
	public static String stringToUnicode(String s)
	{
		String str = "";
		for (int i = 0; i < s.length(); i++)
		{
			int ch = (int) s.charAt(i);
			if (ch > 255)
			{
				str += "\\u" + Integer.toHexString(ch);
			}
			else
			{
				str += "\\" + Integer.toHexString(ch);
			}
		}
		return str;
	}

	/**
	 * 把十六进制Unicode编码字符串转换为中文字符串
	 */
	public static String unicodeToString(String str)
	{
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find())
		{
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	/**
	 * 转化字符串为十六进制编码
	 */
	public static String fromStringToHex(String s)
	{
		String str = "";
		for (int i = 0; i < s.length(); i++)
		{
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/**
	 * 转化十六进制编码为字符串
	 */
	public static String formHexToString(String s)
	{
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++)
		{
			try
			{
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
					s.substring(i * 2, i * 2 + 2), 16));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * 16进制数字字符集
	 */
	private static String hexString = "0123456789ABCDEF";

	/**
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode(String str)
	{
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++)
		{
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/**
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decode(String bytes)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
			bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
				.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

}
