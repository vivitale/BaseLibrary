/*
 * Copyright (c) 2016. Ted Park. All Rights Reserved
 */

package talex.zsw.baselibrary.view.TedPicker.util;

import android.graphics.Bitmap;


public class BitmapUtil
{
	/**
	 * Bitmap을 ratio에 맞춰서 max값 만큼 resize한다.
	 *
	 * @param src 원본
	 * @param max    원하는 크기의 값
	 * @return
	 */
	public static Bitmap resizeBitmap(Bitmap src, int max)
	{
		if(src == null)
		{
			return null;
		}

		int width = src.getWidth();
		int height = src.getHeight();
		float rate = 0.0f;

		if(width > height)
		{
			rate = max / (float) width;
			height = (int) (height * rate);
			width = max;
		}
		else
		{
			rate = max / (float) height;
			width = (int) (width * rate);
			height = max;
		}

		return Bitmap.createScaledBitmap( src, width, height, true );
	}

	/**
	 * Bitmap을 ratio에 맞춰서 max값 만큼 resize한다.
	 *
	 * @param src
	 * @param max
	 * @param isKeep 작은 크기인 경우 유지할건지 체크..
	 * @return
	 */
	public static Bitmap resize(Bitmap src, int max, boolean isKeep)
	{
		if(!isKeep)
		{
			return resizeBitmap( src, max );
		}

		int width = src.getWidth();
		int height = src.getHeight();
		float rate = 0.0f;

		if(width > height)
		{
			if(max > width)
			{
				rate = max / (float) width;
				height = (int) (height * rate);
				width = max;
			}
		}
		else
		{
			if(max > height)
			{
				rate = max / (float) height;
				width = (int) (width * rate);
				height = max;
			}
		}

		return Bitmap.createScaledBitmap( src, width, height, true );
	}

	/**
	 * Bitmap 이미지를 정사각형으로 만든다.
	 *
	 * @param src 원본
	 * @param max 사이즈
	 * @return
	 */
	public static Bitmap resizeSquare(Bitmap src, int max)
	{
		if(src == null)
		{
			return null;
		}

		return Bitmap.createScaledBitmap( src, max, max, true );
	}


	/**
	 * 按指定宽高截取中间一段图片
	 *
	 * @param src 图片
	 * @param w   宽
	 * @param h   高
	 */
	public static Bitmap cropCenterBitmap(Bitmap src, int w, int h)
	{
		if(src == null)
		{
			return null;
		}

		int width = src.getWidth();
		int height = src.getHeight();

		if(width < w && height < h)
		{
			return src;
		}

		int x = 0;
		int y = 0;

		if(width > w)
		{
			x = (width - w) / 2;
		}

		if(height > h)
		{
			y = (height - h) / 2;
		}

		int cw = w; // crop width
		int ch = h; // crop height

		if(w > width)
		{
			cw = width;
		}

		if(h > height)
		{
			ch = height;
		}

		return Bitmap.createBitmap( src, x, y, cw, ch );
	}
}