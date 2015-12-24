package talex.zsw.baselibrary.view.Volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 项目名称: MyApplication
 * 作用: 保持项目中唯一一个Volley的RequestQueue，使用单例模式来实现
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/6/24 15:26 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SingleRequestQueue
{
	private static RequestQueue mQueue;

	private SingleRequestQueue(Context context)
	{
		mQueue = Volley.newRequestQueue(context);
	}

	public static synchronized RequestQueue getRequestQueue(Context context)
	{
		if (mQueue == null)
		{
			new SingleRequestQueue(context.getApplicationContext());
		}
		return mQueue;
	}
}