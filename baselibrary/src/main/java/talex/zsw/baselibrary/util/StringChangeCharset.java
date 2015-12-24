package talex.zsw.baselibrary.util;

import java.io.UnsupportedEncodingException;

/**
 * 项目名称: BaseProject
 * 作用: 转换字符串的编码
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-05-0005 17:23 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class StringChangeCharset
{
	/** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
	public static final String US_ASCII = "US-ASCII";

	/** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
	public static final String ISO_8859_1 = "ISO-8859-1";

	/** 8 位 UCS 转换格式 */
	public static final String UTF_8 = "UTF-8";

	/** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
	public static final String UTF_16BE = "UTF-16BE";

	/** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
	public static final String UTF_16LE = "UTF-16LE";

	/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
	public static final String UTF_16 = "UTF-16";

	/** 中文超大字符集 */
	public static final String GBK = "GBK";

	/**
	 * 将字符编码转换成US-ASCII码
	 */
	public String toASCII(String str) throws UnsupportedEncodingException
	{
		return this.changeCharset(str, US_ASCII);
	}

	/**
	 * 将字符编码转换成ISO-8859-1码
	 */
	public String toISO_8859_1(String str) throws UnsupportedEncodingException
	{
		return this.changeCharset(str, ISO_8859_1);
	}

	/**
	 * 将字符编码转换成UTF-8码
	 */
	public String toUTF_8(String str) throws UnsupportedEncodingException
	{
		return this.changeCharset(str, UTF_8);
	}

	/**
	 * 将字符编码转换成UTF-16BE码
	 */
	public String toUTF_16BE(String str) throws UnsupportedEncodingException
	{
		return this.changeCharset(str, UTF_16BE);
	}

	/**
	 * 将字符编码转换成UTF-16LE码
	 */
	public String toUTF_16LE(String str) throws UnsupportedEncodingException
	{
		return this.changeCharset(str, UTF_16LE);
	}

	/**
	 * 将字符编码转换成UTF-16码
	 */
	public String toUTF_16(String str) throws UnsupportedEncodingException
	{
		return this.changeCharset(str, UTF_16);
	}

	/**
	 * 将字符编码转换成GBK码
	 */
	public String toGBK(String str) throws UnsupportedEncodingException
	{
		return this.changeCharset(str, GBK);
	}

	/**
	 * 字符串编码转换的实现方法
	 *
	 * @param str        待转换编码的字符串
	 * @param newCharset 目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String changeCharset(String str, String newCharset)
		throws UnsupportedEncodingException
	{
		if (str != null)
		{
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 字符串编码转换的实现方法
	 *
	 * @param str        待转换编码的字符串
	 * @param oldCharset 原编码
	 * @param newCharset 目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String changeCharset(String str, String oldCharset, String newCharset)
		throws UnsupportedEncodingException
	{
		if (str != null)
		{
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * int到byte[]
	 *
	 * @param i
	 * @return
	 */
	public static byte[] intToByteArray(int i)
	{
		byte[] result = new byte[4];
		// 由高位到低位
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
		return result;
	}

	/**
	 * byte[]转int
	 *
	 * @param bytes
	 * @return
	 */
	public static int byteArrayToInt(byte[] bytes)
	{
		int value = 0;
		// 由高位到低位
		for (int i = 0; i < 4; i++)
		{
			int shift = (4 - 1 - i) * 8;
			value += (bytes[i] & 0x000000FF) << shift;// 往高位游
		}
		return value;
	}

	public static void main(String[] args) throws UnsupportedEncodingException
	{
		StringChangeCharset test = new StringChangeCharset();
		String str = "This is a 中文的 String!";
		System.out.println("str: " + str);
		String gbk = test.toGBK(str);
		System.out.println("转换成GBK码: " + gbk);
		System.out.println();
		String ascii = test.toASCII(str);
		System.out.println("转换成US-ASCII码: " + ascii);
		gbk = test.changeCharset(ascii, StringChangeCharset.US_ASCII,
			StringChangeCharset.GBK);
		System.out.println("再把ASCII码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String iso88591 = test.toISO_8859_1(str);
		System.out.println("转换成ISO-8859-1码: " + iso88591);
		gbk = test.changeCharset(iso88591, StringChangeCharset.ISO_8859_1,
			StringChangeCharset.GBK);
		System.out.println("再把ISO-8859-1码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf8 = test.toUTF_8(str);
		System.out.println("转换成UTF-8码: " + utf8);
		gbk = test.changeCharset(utf8, StringChangeCharset.UTF_8, StringChangeCharset.GBK);
		System.out.println("再把UTF-8码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf16be = test.toUTF_16BE(str);
		System.out.println("转换成UTF-16BE码:" + utf16be);
		gbk = test.changeCharset(utf16be, StringChangeCharset.UTF_16BE,
			StringChangeCharset.GBK);
		System.out.println("再把UTF-16BE码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf16le = test.toUTF_16LE(str);
		System.out.println("转换成UTF-16LE码:" + utf16le);
		gbk = test.changeCharset(utf16le, StringChangeCharset.UTF_16LE,
			StringChangeCharset.GBK);
		System.out.println("再把UTF-16LE码的字符串转换成GBK码: " + gbk);
		System.out.println();
		String utf16 = test.toUTF_16(str);
		System.out.println("转换成UTF-16码:" + utf16);
		gbk = test.changeCharset(utf16, StringChangeCharset.UTF_16LE,
			StringChangeCharset.GBK);
		System.out.println("再把UTF-16码的字符串转换成GBK码: " + gbk);
		String s = new String("中文".getBytes("UTF-8"), "UTF-8");
		System.out.println(s);
	}
}
