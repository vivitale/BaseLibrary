package talex.zsw.baselibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import talex.zsw.baselibrary.util.klog.KLog;

/**
 * 项目名称: BaseProject
 * 作用: 判断网络情况
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-06-0006 15:37 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ConnectionDetector
{
	private static final int CMNET = 3;
	private static final int CMWAP = 2;
	private static final int WIFI = 1;
	private Context _context;

	public ConnectionDetector(Context context)
	{
		this._context = context;
	}

	public boolean isConnectingToInternet()
	{
		ConnectivityManager connectivity = (ConnectivityManager) _context
			.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null)
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
			{
				for (int i = 0; i < info.length; i++)
				{
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap网络3：net网络
	 */
	public static int getAPNType(Context context)
	{
		int netType = -1;
		ConnectivityManager connMgr = (ConnectivityManager) context
			.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null)
		{
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE)
		{
			KLog.e("networkInfo.getExtraInfo() is " + networkInfo.getExtraInfo());
			if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet"))
			{
				netType = CMNET;
			}
			else
			{
				netType = CMWAP;
			}
		}
		else if (nType == ConnectivityManager.TYPE_WIFI)
		{
			netType = WIFI;
		}
		return netType;
	}
}
