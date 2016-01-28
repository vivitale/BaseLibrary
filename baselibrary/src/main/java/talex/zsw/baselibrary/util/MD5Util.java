package talex.zsw.baselibrary.util;

import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 *
 * @author tfq
 * @datetime 2011-10-13
 */
public class MD5Util
{
	public static String digest(String src)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance( "MD5" );
			digest.update( src.getBytes( "utf-8" ) );
			return toHex( digest.digest() );
		} catch(Exception e)
		{
			return null;
		}
	}

	public static String toHex(byte raw[])
	{
		if(raw == null)
		{
			return null;
		}
		StringBuilder hex = new StringBuilder( 2 * raw.length );
		byte abyte0[];
		int j = (abyte0 = raw).length;
		for(int i = 0; i < j; i++)
		{
			byte b = abyte0[i];
			hex.append( "0123456789ABCDEF".charAt( (b & 240) >> 4 ) )
				.append( "0123456789ABCDEF".charAt( b & 15 ) );
		}
		return hex.toString();
	}

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr)
	{
		MessageDigest md5 = null;
		try
		{
			md5 = MessageDigest.getInstance( "MD5" );
		} catch(Exception e)
		{
			System.out.println( e.toString() );
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for(int i = 0; i < charArray.length; i++)
		{
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest( byteArray );
		StringBuffer hexValue = new StringBuffer();
		for(int i = 0; i < md5Bytes.length; i++)
		{
			int val = ((int) md5Bytes[i]) & 0xff;
			if(val < 16)
			{
				hexValue.append( "0" );
			}
			hexValue.append( Integer.toHexString( val ) );
		}
		return hexValue.toString();
	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr)
	{

		char[] a = inStr.toCharArray();
		for(int i = 0; i < a.length; i++)
		{
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String( a );
		return s;
	}

	// 测试主函数
	public static void main(String args[])
	{
		String s = new String( "tangfuqiang" );
		System.out.println( "原始：" + s );
		System.out.println( "MD5后：" + string2MD5( s ) );
		System.out.println( "加密的：" + convertMD5( s ) );
		System.out.println( "解密的：" + convertMD5( convertMD5( s ) ) );
	}
}
