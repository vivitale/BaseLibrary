package talex.zsw.baselibrary.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称: BaseProject
 * 作用: 中文和Unicode，16进制转为区位码
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-05-0005 17:25 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class StringUnicode
{
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		String chinaString = "测试";
		String unicode = convertToUnicode(chinaString);
		System.out.println(unicode);
		System.out.println(unicodeToChinese(unicode));
		String _x16 = convertTo16Code(chinaString, "UTF-8").toLowerCase();
		System.out.println(_x16);
		String[] sby = convertToBitCode(_x16);
		StringBuffer subf = new StringBuffer();
		subf.append("byte[] bytes = {");
		byte[] bytes = new byte[sby.length];
		for (int i = 0; i < sby.length; i++)
		{
			int icode = Integer.decode(sby[i]).intValue();
			bytes[i] = (byte) icode;
			subf.append("(byte)").append(sby[i]).append(",");
			// System.out.println(sby[i]);
		}
		subf.deleteCharAt(subf.length() - 1);
		subf.append("};");
		System.out.println(subf.toString());
		System.out.println(new String(bytes, "UTF-8"));
	}

	private final static String hexString = "0123456789ABCDEF";

	/**
	 * 转16进制，如果是中文，encoding-->>UTF-8
	 */
	public static String convertTo16Code(String str, String encoding)
		throws UnsupportedEncodingException
	{
		byte[] bytes = str.getBytes(encoding);
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++)
		{
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/**
	 * 将汉字转Unicode
	 *
	 * @param s 汉字
	 * @return String sb.toString();
	 */
	public static String convertToUnicode(String s)
	{
		if (s == null)
		{
			return s;
		}
		char[] chars = s.toCharArray();
		char c;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++)
		{
			c = chars[i];
			if (c > 0xff)
			{
				sb.append("\\u").append(Integer.toHexString(c));
			}
			else
			{
				sb.append(c);
			}
		}
		return sb.toString();
	}


	/**
	 * 将16进制转为区位码
	 */
	public static String[] convertToBitCode(String str_16)
	{
		String[] result = null;
		if (str_16 == null || str_16 == "" || str_16.length() % 2 != 0)
		{
			return result;
		}
		result = new String[str_16.length() / 2];
		for (int i = 0; i < str_16.length() / 2; i++)
		{
			result[i] = "0x" + str_16.charAt(i * 2) + str_16.charAt(i * 2 + 1);
		}
		return result;
	}

	/**
	 *
	 */
	public static String unicodeToChinese(String unicodeStr)
	{
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1)
		{
			end = unicodeStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1)
			{
				charStr = unicodeStr.substring(start + 2, unicodeStr.length());
			}
			else
			{
				charStr = unicodeStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16);
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	public static boolean isChinese(char c)
	{
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
			|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
			|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
			|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
			|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
			|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)
		{
			return true;
		}
		return false;
	}

	public static boolean isMessyCode(String str)
	{
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(str);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++)
		{
			char c = ch[i];
			if (!Character.isLetterOrDigit(c))
			{
				if (!isChinese(c))
				{
					count = count + 1;
					System.out.print(c);
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
