package talex.zsw.baselibrary.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 项目名称: BaseLibrary
 * 作用: 通用的Bitmap处理类
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-09-10-0010 9:27 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressWarnings("ALL") public class BitmapUtil
{

	/**
	 * 下载图片
	 *
	 * @param url 图片地址
	 */
	public static Bitmap getBitmapByUrl(String url)
	{
		Bitmap bitmap = null;
		try
		{
			URL url2 = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
			httpURLConnection.setReadTimeout(3000);
			int code = httpURLConnection.getResponseCode();
			if (code == 200)
			{
				bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * bitmap转为base64
	 *
	 * @param bitmap 图片
	 * @return
	 */
	public static String bitmapToBase64(Bitmap bitmap)
	{

		String result = null;
		ByteArrayOutputStream baos = null;
		try
		{
			if (bitmap != null)
			{
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (baos != null)
				{
					baos.flush();
					baos.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * base64转为bitmap
	 *
	 * @param base64Data base64的图片字符串
	 * @return
	 */
	public static Bitmap base64ToBitmap(String base64Data)
	{
		byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}


	/**
	 * 将Bytes转换成 Bitmap
	 */
	public static Bitmap getPicFromBytes(byte[] bytes,
										 BitmapFactory.Options opts)
	{
		if (bytes != null)
		{
			if (opts != null)
			{
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
					opts);
			}
			else
			{
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			}
		}
		return null;
	}

	/**
	 * 将bitmap转换成byte[]
	 */
	public static byte[] Bitmap2Bytes(Bitmap bm)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * 缩放图片
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h)
	{
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) w / width);
		float scaleHeight = ((float) h / height);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
			matrix, true);
		return newBmp;
	}

	/**
	 * 将byte写入文件
	 */
	public static File getFileFromBytes(byte[] b, String outputFile)
	{
		BufferedOutputStream stream = null;
		File file = null;
		try
		{
			file = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (stream != null)
			{
				try
				{
					stream.close();
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 调用系统方法裁剪图片
	 *
	 * @param uri     图片地址
	 * @param aspectX 宽高比例
	 * @param aspectY 宽高比例
	 * @param outputX 图片的宽高
	 * @param outputY 图片的宽高
	 */
	private void startPhotoZoom(Context context, Uri uri, int aspectX, int aspectY, int outputX,
								int outputY)
	{
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");

		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("return-data", true);
//		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}

	/**
	 * 将图片变成圆角
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels)
	{
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
			bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(
			android.graphics.PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	/**
	 * 获取圆形头像
	 */
	public Bitmap getRoundedCornerBitmap(Bitmap bitmap)
	{
		Bitmap outBitmap =
			Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(outBitmap);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPX = bitmap.getWidth() / 2;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return outBitmap;
	}

	/**
	 * 从网络获取图片
	 */
	public Bitmap getWebPicture(String urlStr)
	{
		Bitmap bitmap = null;
		try
		{
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.connect();
			InputStream is = conn.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((len = is.read(buffer)) != -1)
			{
				bos.write(buffer, 0, len);
			}
			byte[] data = bos.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			bos.close();
			is.close();
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 将View转换成Bitmp
	 */
	public static Bitmap convertViewToBitmap(View view)
	{
		view.measure(View.MeasureSpec
				.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
			View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return bitmap;
	}


	/**
	 * 将一个image,做成毛玻璃再设置给view
	 *
	 * @param image
	 * @param view
	 * @param context
	 */
	public static void applyBlur(final ImageView image, final View view, final Context context)
	{
		image.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
		{
			@Override
			public boolean onPreDraw()
			{
				image.getViewTreeObserver().removeOnPreDrawListener(this);
				image.buildDrawingCache();

				Bitmap bmp = image.getDrawingCache();
				blur(bmp, view, true, context);
				return true;
			}
		});
	}

	public static void blur(Bitmap bkg, View view, boolean scale, Context context)
	{
		try
		{
			long startMs = System.currentTimeMillis();
			float scaleFactor = 1;
			float radius = 20;
			if (scale)
			{
				scaleFactor = 8;
				radius = 2;
			}

			Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth() / scaleFactor),
				(int) (view.getMeasuredHeight() / scaleFactor), Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(overlay);
			canvas.translate(-view.getLeft() / scaleFactor, -view.getTop() / scaleFactor);
			canvas.scale(1 / scaleFactor, 1 / scaleFactor);
			Paint paint = new Paint();
			paint.setFlags(Paint.FILTER_BITMAP_FLAG);
			canvas.drawBitmap(bkg, 0, 0, paint);

			overlay = FastBlur.doBlur(overlay, (int) radius, true);
			view.setBackgroundDrawable(new BitmapDrawable(context.getResources(), overlay));
		} catch (Exception ignored)
		{

		}
	}

	/**
	 * Drawable转换成Bitmap
	 */
	public static Bitmap drawableToBitamp(Drawable drawable)
	{
		Bitmap bitmap;
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		if (w <= 0)
		{
			w = 10;
		}
		if (h <= 0)
		{
			h = 10;
		}
		System.out.println("Drawable转Bitmap");
		Bitmap.Config config =
			drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		bitmap = Bitmap.createBitmap(w, h, config);
		//注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * 毛玻璃效果
	 *
	 * @param context    上下文
	 * @param sentBitmap 要添加毛玻璃效果的图片
	 * @param radius     毛玻璃的程度
	 * @return 毛玻璃图片
	 */
	@SuppressLint("NewApi")
	public static Bitmap fastblur(Context context, Bitmap sentBitmap, int radius)
	{
		if (Build.VERSION.SDK_INT > 16)
		{
			Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

			final RenderScript rs = RenderScript.create(context);
			final Allocation input =
				Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
					Allocation.USAGE_SCRIPT);
			final Allocation output = Allocation.createTyped(rs, input.getType());
			final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
			script.setRadius(radius /* e.g. 3.f */);
			script.setInput(input);
			script.forEach(output);
			output.copyTo(bitmap);
			return bitmap;
		}
		else
		{
			Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

			if (radius < 1)
			{
				return (null);
			}

			int w = bitmap.getWidth();
			int h = bitmap.getHeight();

			int[] pix = new int[w * h];
//        Log.e("pix", w + " " + h + " " + pix.length);
			bitmap.getPixels(pix, 0, w, 0, 0, w, h);

			int wm = w - 1;
			int hm = h - 1;
			int wh = w * h;
			int div = radius + radius + 1;

			int r[] = new int[wh];
			int g[] = new int[wh];
			int b[] = new int[wh];
			int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
			int vmin[] = new int[Math.max(w, h)];

			int divsum = (div + 1) >> 1;
			divsum *= divsum;
			int temp = 256 * divsum;
			int dv[] = new int[temp];
			for (i = 0; i < temp; i++)
			{
				dv[i] = (i / divsum);
			}

			yw = yi = 0;

			int[][] stack = new int[div][3];
			int stackpointer;
			int stackstart;
			int[] sir;
			int rbs;
			int r1 = radius + 1;
			int routsum, goutsum, boutsum;
			int rinsum, ginsum, binsum;

			for (y = 0; y < h; y++)
			{
				rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
				for (i = -radius; i <= radius; i++)
				{
					p = pix[yi + Math.min(wm, Math.max(i, 0))];
					sir = stack[i + radius];
					sir[0] = (p & 0xff0000) >> 16;
					sir[1] = (p & 0x00ff00) >> 8;
					sir[2] = (p & 0x0000ff);
					rbs = r1 - Math.abs(i);
					rsum += sir[0] * rbs;
					gsum += sir[1] * rbs;
					bsum += sir[2] * rbs;
					if (i > 0)
					{
						rinsum += sir[0];
						ginsum += sir[1];
						binsum += sir[2];
					}
					else
					{
						routsum += sir[0];
						goutsum += sir[1];
						boutsum += sir[2];
					}
				}
				stackpointer = radius;

				for (x = 0; x < w; x++)
				{

					r[yi] = dv[rsum];
					g[yi] = dv[gsum];
					b[yi] = dv[bsum];

					rsum -= routsum;
					gsum -= goutsum;
					bsum -= boutsum;

					stackstart = stackpointer - radius + div;
					sir = stack[stackstart % div];

					routsum -= sir[0];
					goutsum -= sir[1];
					boutsum -= sir[2];

					if (y == 0)
					{
						vmin[x] = Math.min(x + radius + 1, wm);
					}
					p = pix[yw + vmin[x]];

					sir[0] = (p & 0xff0000) >> 16;
					sir[1] = (p & 0x00ff00) >> 8;
					sir[2] = (p & 0x0000ff);

					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];

					rsum += rinsum;
					gsum += ginsum;
					bsum += binsum;

					stackpointer = (stackpointer + 1) % div;
					sir = stack[(stackpointer) % div];

					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];

					rinsum -= sir[0];
					ginsum -= sir[1];
					binsum -= sir[2];

					yi++;
				}
				yw += w;
			}
			for (x = 0; x < w; x++)
			{
				rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
				yp = -radius * w;
				for (i = -radius; i <= radius; i++)
				{
					yi = Math.max(0, yp) + x;

					sir = stack[i + radius];

					sir[0] = r[yi];
					sir[1] = g[yi];
					sir[2] = b[yi];

					rbs = r1 - Math.abs(i);

					rsum += r[yi] * rbs;
					gsum += g[yi] * rbs;
					bsum += b[yi] * rbs;

					if (i > 0)
					{
						rinsum += sir[0];
						ginsum += sir[1];
						binsum += sir[2];
					}
					else
					{
						routsum += sir[0];
						goutsum += sir[1];
						boutsum += sir[2];
					}

					if (i < hm)
					{
						yp += w;
					}
				}
				yi = x;
				stackpointer = radius;
				for (y = 0; y < h; y++)
				{
					// Preserve alpha channel: ( 0xff000000 & pix[yi] )
					pix[yi] =
						(0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

					rsum -= routsum;
					gsum -= goutsum;
					bsum -= boutsum;

					stackstart = stackpointer - radius + div;
					sir = stack[stackstart % div];

					routsum -= sir[0];
					goutsum -= sir[1];
					boutsum -= sir[2];

					if (x == 0)
					{
						vmin[y] = Math.min(y + r1, hm) * w;
					}
					p = x + vmin[y];

					sir[0] = r[p];
					sir[1] = g[p];
					sir[2] = b[p];

					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];

					rsum += rinsum;
					gsum += ginsum;
					bsum += binsum;

					stackpointer = (stackpointer + 1) % div;
					sir = stack[stackpointer];

					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];

					rinsum -= sir[0];
					ginsum -= sir[1];
					binsum -= sir[2];

					yi += w;
				}
			}
//        Log.e("pix", w + " " + h + " " + pix.length);
			bitmap.setPixels(pix, 0, w, 0, 0, w, h);
			return (bitmap);
		}
	}
}
