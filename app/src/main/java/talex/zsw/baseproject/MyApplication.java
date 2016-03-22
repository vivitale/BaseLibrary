package talex.zsw.baseproject;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import talex.zsw.baselibrary.BaseApplication;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-10-0010 0:26 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MyApplication extends BaseApplication
{
	private static RequestQueue sRequestQueue;
	private static Context sContext;

	@Override public void onCreate()
	{
		super.onCreate();
		setImg( R.mipmap.ic_launcher, R.mipmap.ic_launcher );
		setStrictMode();
		sContext = getApplicationContext();
	}

	private void setStrictMode()
	{
		if(BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)
		{
			StrictMode.enableDefaults();
		}
	}

	public static RequestQueue getRequestQueue()
	{
		if(sRequestQueue == null)
		{
			sRequestQueue = Volley.newRequestQueue( sContext );
		}
		return sRequestQueue;
	}
}
