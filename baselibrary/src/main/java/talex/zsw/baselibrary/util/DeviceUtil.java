package talex.zsw.baselibrary.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 项目包名: talex.zsw.baselibrary.util
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/6/8 17:34 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DeviceUtil{
	public DeviceUtil() {
	}

	public static PackageInfo getAppInfo(Context context) {
		PackageInfo info = null;

		try {
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException var3) {
			var3.printStackTrace();
		}

		return info;
	}

	public static int getAndroidSDKVersion() {
		int version = 0;

		try {
			version = Integer.valueOf(Build.VERSION.SDK_INT).intValue();
		} catch (NumberFormatException var2) {
			Log.e("getAndroidSDKVersion", var2.toString());
		}

		return version;
	}

	@SuppressLint({"NewApi"})
	public static void hideNativeBar(Activity context) {
		if(getAndroidSDKVersion() >= 11) {
			context.getWindow().getDecorView().setSystemUiVisibility(2);
		}

	}

	public static TelephonyManager getDeviceManager(Activity context) {
		TelephonyManager tm = (TelephonyManager)context.getSystemService("phone");
		return tm;
	}
}
