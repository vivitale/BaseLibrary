package talex.zsw.baselibrary.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * 项目名称: Executor
 * 作用: 获取GPS
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/2/26 22:00 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressWarnings("MissingPermission") public class Gps
{
	private Location location = null;
	private LocationManager locationManager = null;
	private Context context = null;

	/**
	 * 初始化
	 *
	 * @param ctx
	 */
	public Gps(Context ctx)
	{
		context = ctx;
		locationManager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );
		location = locationManager.getLastKnownLocation( getProvider() );
		locationManager
			.requestLocationUpdates( LocationManager.GPS_PROVIDER, 2000, 10, locationListener );
	}


	// 获取Location Provider
	public String getProvider()
	{
		// 构建位置查询条件
		Criteria criteria = new Criteria();
		// 查询精度：高
		criteria.setAccuracy( Criteria.ACCURACY_FINE );
		// 是否查询海拨：否
		criteria.setAltitudeRequired( false );
		// 是否查询方位角 : 否
		criteria.setBearingRequired( false );
		// 是否允许付费：是
		criteria.setCostAllowed( true );
		// 电量要求：低
		criteria.setPowerRequirement( Criteria.POWER_LOW );
		// 返回最合适的符合条件的provider，第2个参数为true说明 , 如果只有一个provider是有效的,则返回当前provider
		return locationManager.getBestProvider( criteria, true );
	}

	private LocationListener locationListener = new LocationListener()
	{
		// 位置发生改变后调用
		public void onLocationChanged(Location l)
		{
			if(l != null)
			{
				location = l;
			}
		}

		// provider 被用户关闭后调用
		public void onProviderDisabled(String provider)
		{
			location = null;
		}

		// provider 被用户开启后调用
		public void onProviderEnabled(String provider)
		{
			Location l = locationManager.getLastKnownLocation( provider );
			if(l != null)
			{
				location = l;
			}
		}

		// provider 状态变化时调用
		public void onStatusChanged(String provider, int status, Bundle extras)
		{
		}
	};

	public Location getLocation()
	{
		return location;
	}

	public void closeLocation()
	{
		if(locationManager != null)
		{
			if(locationListener != null)
			{
				locationManager.removeUpdates( locationListener );
				locationListener = null;
			}
			locationManager = null;
		}
	}
}
