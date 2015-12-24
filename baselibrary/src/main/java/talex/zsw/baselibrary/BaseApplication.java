package talex.zsw.baselibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 项目名称: BaseLibrary
 * 作用: 基本的Application,项目的Application继承自该类,调用setImg(int res)方法来设置基本图片
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-09-25-0025 15:16 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseApplication extends Application
{
	private List<Activity> activityList = new LinkedList();

	@Override
	public void onCreate()
	{
		super.onCreate();
	}

	/**
	 * 设置基本图片
	 *
	 * @param defaultImg 基本图片RES
	 */
	public void setImg(int defaultImg, int errorImg)
	{
		initImageLoader(getApplicationContext(), defaultImg, errorImg);
	}

	public static void initImageLoader(Context context, int defaultImg, int errorImg)
	{
		// 使用DisplayImageOptions.Builder()创建DisplayImageOptions
		DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(defaultImg)            // 设置图片下载期间显示的图片
			.showImageForEmptyUri(errorImg)    // 设置图片Uri为空或是错误的时候显示的图片
			.showImageOnFail(errorImg)        // 设置图片加载或解码过程中发生错误显示的图片
			.cacheOnDisc(true)                            // 设置下载的图片是否缓存在SD卡中
			.cacheInMemory(true)                        // 设置下载的图片是否缓存在内存
			.bitmapConfig(Bitmap.Config.RGB_565)        // 设置图片的解码类型
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();// 创建配置过得DisplayImageOption对象

		File cacheDir = StorageUtils.getCacheDirectory(context);

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
			context).threadPriority(Thread.NORM_PRIORITY - 2)
			.denyCacheImageMultipleSizesInMemory()
			.discCacheFileNameGenerator(new Md5FileNameGenerator())
			.tasksProcessingOrder(QueueProcessingType.LIFO)
			.memoryCache(new FIFOLimitedMemoryCache(2 * 1024 * 1024))
			.memoryCacheSize(2 * 1024 * 1024)
			.diskCache(new UnlimitedDiskCache(cacheDir))
			.diskCacheSize(50 * 1024 * 1024)
			.defaultDisplayImageOptions(options).writeDebugLogs().build();
		ImageLoader.getInstance().init(config);
	}

	public boolean isShow()
	{
		if (android.os.Build.VERSION.SDK_INT >= 15)
		{
			return true;
		}
		return false;
	}


	public void showToast(String text)
	{
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
			.show();
	}

	public void exit()
	{
		Iterator i$ = this.activityList.iterator();
		while (i$.hasNext())
		{
			Activity activity = (Activity) i$.next();
			activity.finish();
		}
		System.exit(0);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public void addActivity(Activity activity)
	{
		this.activityList.add(activity);
	}
}
