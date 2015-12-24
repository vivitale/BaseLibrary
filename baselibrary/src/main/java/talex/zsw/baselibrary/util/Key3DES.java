package talex.zsw.baselibrary.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 项目名称: BaseProject
 * 作用: 3DES加密
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-05-0005 17:27 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class Key3DES
{
	private static final String Algorithm = "DESede"; // 定义 加密算法,可用

	/**
	 * DES,DESede,Blowfish
	 *
	 * @param keybyte 为加密密钥，长度为24字节
	 * @param src     src为被加密的数据缓冲区（源）
	 */
	public static byte[] encryptMode(byte[] keybyte, byte[] src)
	{
		try
		{
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1)
		{
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2)
		{
			e2.printStackTrace();
		} catch (java.lang.Exception e3)
		{
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * @param keybyte 加密密钥，长度为24字节
	 * @param src     src为加密后的缓冲区
	 */
	public static byte[] decryptMode(byte[] keybyte, byte[] src)
	{
		try
		{
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1)
		{
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2)
		{
			e2.printStackTrace();
		} catch (java.lang.Exception e3)
		{
			e3.printStackTrace();
		}
		return null;
	}

	// 转换成十六进制字符串
	public static String byte2hex(byte[] b)
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
			{
				hs = hs + "0" + stmp;
			}
			else
			{
				hs = hs + stmp;
			}
			if (n < b.length - 1)
			{
				hs = hs + ":";
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * 返回十六进制
	 */
	public static String getContent(byte[] bytes)
	{
		String string = "";
		for (int j = 0; j < bytes.length; j++)
		{
			int x = bytes[j];
			if (x < 0)
			{
				x = x + 256;
			}
			if (x <= 10)
			{
				string += "0";
			}
			string += Integer.toHexString(x);
		}
		return string;
	}


	public static void main(String[] args)
	{
		// 24字节的密钥 其实是3个密钥K1,K2,K3 在要求不严格的情况下允许 K1=K3
		final byte[] keyBytes = {0x6D, 0x01, 0x00, 0x00, (byte) 0x85, 0x08,
			0x05, 0x20, (byte) 0xE6, 0x65, (byte) 0xF3, 0x10, (byte) 0x82,
			0x00, 0x08, 0x35, 0x6D, 0x01, 0x00, 0x00, (byte) 0x85, 0x08,
			0x05, 0x20
		};
		String szSrc = "测试文字";
		System.out.println("加密前的字符串:" + szSrc);
		byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());
		System.out.println("加密后的字符串:" + new String(encoded));
		// System.out.println(getContent(encoded));
		byte[] srcBytes = decryptMode(keyBytes, encoded);
		System.out.println("解密后的字符串:" + (new String(srcBytes)));
		// System.out.println(getContent(srcBytes));
	}
}
