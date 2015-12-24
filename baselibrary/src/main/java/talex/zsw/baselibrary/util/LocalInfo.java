package talex.zsw.baselibrary.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import talex.zsw.baselibrary.util.klog.KLog;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-09-0009 13:37 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LocalInfo
{
	/**
	 * 获取本机IP
	 */
	@SuppressLint("LongLogTag") public static String getLocalIpAddress()
	{
		try
		{
			for (Enumeration<NetworkInterface> en = NetworkInterface
				.getNetworkInterfaces(); en.hasMoreElements(); )
			{
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
					.getInetAddresses(); enumIpAddr.hasMoreElements(); )
				{
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress())
					{
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException ex)
		{
			KLog.e( ex.toString());
		}
		return null;
	}

	/**
	 * 获取本机MAC
	 */
	public static String getLocalMacAddress(Context context)
	{
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	/**
	 * 获取包信息
	 *
	 * @param context 上下文
	 * @return PackageInfo 设定文件
	 */
	public static PackageInfo getAppInfo(Context context)
	{
		PackageInfo info = null;
		try
		{
			info = context.getPackageManager().getPackageInfo(
				context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return info;
	}
}
