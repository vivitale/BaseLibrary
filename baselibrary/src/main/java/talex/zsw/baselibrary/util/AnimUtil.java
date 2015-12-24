package talex.zsw.baselibrary.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import talex.zsw.baselibrary.R;


public class AnimUtil
{
	/**
	 * 使View抖动
	 */
	public static void shakeView(Context context, View v)
	{
		final Animation shakeAn = AnimationUtils.loadAnimation(context,
			R.anim.empty_shake);
		v.startAnimation(shakeAn);
	}
}
