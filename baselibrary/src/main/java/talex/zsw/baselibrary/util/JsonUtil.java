package talex.zsw.baselibrary.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * 项目名称: BaseProject
 * 作用: Json解析,拼装类
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-09-0009 14:02 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class JsonUtil
{
	/**
	 * 将json解析成指定泛型并返回
	 *
	 * @param string json数据
	 * @param <T>    指定泛型
	 */
	public static <T> T getObject(String string)
	{
		Type responseType = new TypeToken<T>()
		{
		}.getType();
		return GsonParser.create().fromJson(string, responseType);
	}

	/**
	 * @param object 指定类型
	 * @param <T>    指定泛型
	 * @return 将指定类变成Json型数据返回
	 */
	public static <T> String getJsonString(T object)
	{
		Gson gson = new Gson();
		String data = gson.toJson(object);
		if (StringUtils.isBlank(data))
		{
			data = "";
		}
		return data;
	}
}
