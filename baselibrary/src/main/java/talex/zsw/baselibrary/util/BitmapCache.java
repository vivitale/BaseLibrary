package talex.zsw.baselibrary.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * 将缓存图片的大小设置为10M
 */
public class BitmapCache implements ImageCache
{
	private LruCache<String, Bitmap> mCache;

	public BitmapCache()
	{
		int maxSize = 10 * 1024 * 1024;
		mCache = new LruCache<String, Bitmap>(maxSize)
		{
			@Override
			protected int sizeOf(String key, Bitmap bitmap)
			{
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
		};
	}

	@Override
	public Bitmap getBitmap(String url)
	{
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap)
	{
		mCache.put(url, bitmap);
	}
}