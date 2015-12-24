package talex.zsw.baselibrary.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 项目名称: PuJiang
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-16-0016 14:39 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressWarnings("ALL")
public class ListSort<T>
{
	/**
	 * @param list   要排序的集合
	 * @param method 要排序的实体的属性所对应的get方法
	 * @param sort   desc 为正序
	 */
	public void Sort(List<T> list, final String method, final String sort)
	{
		// 用内部类实现排序
		Collections.sort(list, new Comparator<T>()
		{

			public int compare(T a, T b)
			{
				int ret = 0;
				try
				{
					// 获取m1的方法名
					Method m1 = a.getClass().getMethod(method, null);
					// 获取m2的方法名
					Method m2 = b.getClass().getMethod(method, null);

					if (sort != null && "desc".equals(sort))
					{

						ret = m2.invoke(((T) b), null).toString()
							.compareTo(m1.invoke(((T) a), null).toString());
					}
					else
					{
						// 正序排序
						ret = m1.invoke(((T) a), null).toString()
							.compareTo(m2.invoke(((T) b), null).toString());
					}
				} catch (NoSuchMethodException ne)
				{
					System.out.println(ne.toString());
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e)
				{
					e.printStackTrace();
				}
				return ret;
			}
		});
	}
}
