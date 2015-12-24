package talex.zsw.baselibrary.view.CommonAdapter.recyclerviewanimators;

import android.support.v7.widget.RecyclerView;
import android.view.animation.OvershootInterpolator;

import talex.zsw.baselibrary.view.CommonAdapter.recyclerviewanimators.adapters.AlphaInAnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.recyclerviewanimators.adapters.AnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.recyclerviewanimators.adapters.ScaleInAnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.recyclerviewanimators.adapters.SlideInBottomAnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.recyclerviewanimators.adapters.SlideInLeftAnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.recyclerviewanimators.adapters.SlideInRightAnimationAdapter;


public class AnimationAdapterUtil
{
	public static final int SLIDE_IN_BOTTOM = 0x1000;
	public static final int SCALE_IN = 0x1001;
	public static final int SLIDE_IN_LEFT = 0x1002;
	public static final int SLIDE_IN_RIGHT = 0x1003;

	/**
	 * @param adapter   RecyclerView的Adapter
	 * @param type      动画效果
	 *                  0x1000:从底部进入   0x100:缩放  0x100:从左边进入  0x100:从右边进入
	 * @param overshoot 回弹效果
	 */
	public static AnimationAdapter getAnimationAdapter(RecyclerView.Adapter adapter, int type,
													   boolean overshoot)
	{
		AnimationAdapter mAnimationAdapter = null;
		AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
		switch (type)
		{
			case SLIDE_IN_BOTTOM:
				mAnimationAdapter = new SlideInBottomAnimationAdapter(alphaAdapter);
				if (overshoot)
				{
					mAnimationAdapter.setInterpolator(new OvershootInterpolator());
				}
				break;
			case SCALE_IN:
				mAnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
				if (overshoot)
				{
					mAnimationAdapter.setInterpolator(new OvershootInterpolator());
				}
				break;
			case SLIDE_IN_LEFT:
				mAnimationAdapter = new SlideInLeftAnimationAdapter(alphaAdapter);
				if (overshoot)
				{
					mAnimationAdapter.setInterpolator(new OvershootInterpolator());
				}
				break;
			case SLIDE_IN_RIGHT:
				mAnimationAdapter = new SlideInRightAnimationAdapter(alphaAdapter);
				if (overshoot)
				{
					mAnimationAdapter.setInterpolator(new OvershootInterpolator());
				}
				break;
		}
		return mAnimationAdapter;
	}
}
