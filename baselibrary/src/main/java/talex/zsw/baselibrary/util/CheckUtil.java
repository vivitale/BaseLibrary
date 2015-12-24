package talex.zsw.baselibrary.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil
{

	/**
	 * 身份证校验
	 *
	 * @return
	 */
	public static boolean validateIDCard(String sCard)
	{
		if (sCard.equals(""))
		{
			return true;
		}
		else
		{
			if ((sCard.length()) == 15)
			{
				if (checkNum(sCard))
				{
					int month = Integer.valueOf(sCard.substring(8, 10));
					int day = (Integer.valueOf(sCard.substring(10, 12)));
					if (month < 1 || month > 12 || day < 1 || day > 31)
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				else
				{
					return false;
				}
			}
			else if (sCard.length() == 18)
			{
				if (checkNum(sCard))
				{
					int year = Integer.valueOf(sCard.substring(6, 10));
					int month = Integer.valueOf(sCard.substring(10, 12));
					int day = (Integer.valueOf(sCard.substring(12, 14)));
					if (year < 1900 || month < 1 || month > 12 || day < 1
						|| day > 31)
					{
						return false;
					}
					else
					{
						return is18IdCard(sCard);
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
	}

	public static boolean is18IdCard(String arrIdCard)
	{
		int sigma = 0;
		Integer[] a = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
		String[] w = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
		for (int i = 0; i < 17; i++)
		{
			int ai = Integer.parseInt(arrIdCard.substring(i, i + 1));
			int wi = a[i];
			sigma += ai * wi;
		}
		int number = sigma % 11;
		String check_number = w[number];
		// return check_number;
		// System.out.println(check_number);
		if (!arrIdCard.substring(17).equals(check_number))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * 手机号码校验
	 *
	 * @param phone
	 * @return
	 */
	public static boolean validateMoblie(String phone)
	{
		int l = phone.length();
		boolean rs = false;
		switch (l)
		{
			case 7:
				if (matchingText("^(13[0-9]|15[0-9]|18[7|8|9|6|5])\\d{4}$",
					phone))
				{
					rs = true;
				}
				break;
			case 11:
				if (matchingText("^(13[0-9]|15[0-9]|18[0-9]|14[0-9])\\d{4,8}$",
					phone))
				{
					rs = true;
				}
				break;
			default:
				rs = false;
				break;
		}
		return rs;
	}

	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles)
	{
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186
		 * 电信：133、153、180、189、（1349卫通）
		 * 网络：177、170
		 * 总结起来就是第一位必定为1，第二位必定为3或5或7或8，其他位置的可以为0-9
		 */
		String telRegex =
			"[1][1234567890]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (StringUtils.isBlank(mobiles))
		{
			return false;
		}
		else
		{
			return mobiles.matches(telRegex);
		}
	}

	private static boolean matchingText(String expression, String text)
	{
		Pattern p = Pattern.compile(expression); // 正则表达式
		Matcher m = p.matcher(text); // 操作的字符串
		boolean b = m.matches();
		return b;
	}

	/**
	 * 验证身份证号码 是否为数字，最后一位除外
	 *
	 * @param sCard2
	 * @return
	 */
	private static boolean checkNum(String sCard2)
	{
		for (int i = 0, len = sCard2.length() - 1; i < len; i++)
		{
			if ((sCard2.charAt(i) > '9' || sCard2.charAt(i) < '0'))
			{
				return false;
			}
		}
		return true;
	}

	// 根据身份证号码判断男女
	public static String getIdcardSex(String idCardString)
	{
		String sex = null;
		if (!StringUtils.isBlank(idCardString))
		{
			sex = idCardString.substring(16, 17);
			if (Integer.parseInt(sex) % 2 == 0)
			{
				sex = "女";
			}
			else
			{
				sex = "男";
			}
		}
		return sex;
	}

	public static boolean isEmailByRegex(String email)
	{
		String regex = "^[A-Za-z0-9]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,3}$";
		return email.matches(regex);
	}

	/**
	 * 验证输入的邮箱格式是否符合
	 *
	 * @param email
	 * @return 是否合法
	 */
	public static boolean isEmail(String email)
	{
		// 正则表达式
		/*
		 * String regex =
		 * "^[A-Za-z0-9]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,3}$";
		 * return email.matches(regex);
		 */

		// 不适用正则
		if (email == null || "".equals(email))
		{
			return false;
		}
		if (!containsOneWord('@', email) || !containsOneWord('.', email))
		{
			return false;
		}
		String prefix = email.substring(0, email.indexOf("@"));
		String middle = email.substring(email.indexOf("@") + 1,
			email.indexOf("."));
		String subfix = email.substring(email.indexOf(".") + 1);
		System.out.println("prefix=" + prefix + "  middle=" + middle
			+ "  subfix=" + subfix);
		if (prefix == null || prefix.length() > 40 || prefix.length() == 0)
		{
			return false;
		}
		if (!isAllWords(prefix))
		{
			return false;
		}
		if (middle == null || middle.length() > 40 || middle.length() == 0)
		{
			return false;
		}
		if (!isAllWordsAndNo(middle))
		{
			return false;
		}
		if (subfix == null || subfix.length() > 3 || subfix.length() < 2)
		{
			return false;
		}
		if (!isAllWords(subfix))
		{
			return false;
		}
		return true;
	}

	// 判断字符串只包含指定的一个字符c

	private static boolean containsOneWord(char c, String word)
	{
		char[] array = word.toCharArray();
		int count = 0;
		for (Character ch : array)
		{
			if (c == ch)
			{
				count++;
			}
		}
		return count == 1;
	}

	// 检查一个字符串是否全部是字母

	private static boolean isAllWords(String prefix)
	{
		char[] array = prefix.toCharArray();
		for (Character ch : array)
		{
			if (ch < 'A' || ch > 'z' || (ch < 'a' && ch > 'Z'))
			{
				return false;
			}
		}
		return true;
	}

	// 检查一个字符串是否包含字母和数字

	private static boolean isAllWordsAndNo(String middle)
	{
		char[] array = middle.toCharArray();
		for (Character ch : array)
		{
			if (ch < '0' || ch > 'z')
			{
				return false;
			}
			else if (ch > '9' && ch < 'A')
			{
				return false;
			}
			else if (ch > 'Z' && ch < 'a')
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断一个字符串是否是数字
	 */
	public static boolean isNum(String str)
	{
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}
