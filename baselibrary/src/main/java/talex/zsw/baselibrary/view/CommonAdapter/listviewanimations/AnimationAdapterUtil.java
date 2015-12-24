package talex.zsw.baselibrary.view.CommonAdapter.listviewanimations;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.nineoldandroids.animation.Animator;

import talex.zsw.baselibrary.view.CommonAdapter.listviewanimations.swinginadapters.ResourceAnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.listviewanimations.swinginadapters.SingleAnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.listviewanimations.swinginadapters.prepared.AlphaInAnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.listviewanimations.swinginadapters.prepared.SwingRightInAnimationAdapter;


public class AnimationAdapterUtil
{
	public static final int SLIDE_IN_BOTTOM = 0x1000;
	public static final int SCALE_IN = 0x1001;
	public static final int SLIDE_IN_LEFT = 0x1002;
	public static final int SLIDE_IN_RIGHT = 0x1003;

	/**
	 * @param view    ListView或者GridView
	 * @param adapter ListView或者GridView的Adapter
	 * @param type    动画效果
	 *                0x1000:从底部进入   0x100:缩放  0x100:从左边进入  0x100:从右边进入
	 */
	public static BaseAdapterDecorator getAnimationAdapter(AbsListView view,
														   BaseAdapter adapter, int type)
	{
		BaseAdapterDecorator AnimationAdapter = null;
		AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
		switch (type)
		{
			case SLIDE_IN_BOTTOM:
				AnimationAdapter = new SwingBottomInAnimationAdapter(alphaAdapter);
				break;
			case SCALE_IN:
				AnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
				break;
			case SLIDE_IN_LEFT:
				AnimationAdapter = new SwingLeftInAnimationAdapter(alphaAdapter);
				break;
			case SLIDE_IN_RIGHT:
				AnimationAdapter = new SwingRightInAnimationAdapter(alphaAdapter);
				break;
			default:
				break;
		}
		AnimationAdapter.setAbsListView(view);
		return AnimationAdapter;
	}

	public static SingleAnimationAdapter getSingleAnimationAdapter(
		AbsListView view, BaseAdapter adapter, final Animator animator)
	{
		SingleAnimationAdapter singleAnimationAdapter = new SingleAnimationAdapter(
			adapter)
		{
			@Override
			protected long getAnimationDelayMillis()
			{
				return DEFAULTANIMATIONDELAYMILLIS;
			}

			@Override
			protected long getAnimationDurationMillis()
			{
				return DEFAULTANIMATIONDURATIONMILLIS;
			}

			@Override
			protected Animator getAnimator(ViewGroup parent, View view)
			{
				return animator;
			}
		};
		singleAnimationAdapter.setAbsListView(view);
		return singleAnimationAdapter;
	}

	@SuppressWarnings("rawtypes")
	public static ResourceAnimationAdapter getSingleAnimationAdapter(
		Context context, AbsListView view, BaseAdapter adapter,
		final Animator[] animators)
	{
		ResourceAnimationAdapter resourceAnimationAdapter = new ResourceAnimationAdapter(
			adapter, context)
		{
			@Override
			protected long getAnimationDelayMillis()
			{
				return DEFAULTANIMATIONDELAYMILLIS;
			}

			@Override
			protected long getAnimationDurationMillis()
			{
				return DEFAULTANIMATIONDURATIONMILLIS;
			}

			@Override
			public Animator[] getAnimators(ViewGroup parent, View view)
			{
				for (int i = 0; i < animators.length; i++)
				{
					animators[i].setTarget(view);
				}
				return animators;
			}

			@Override
			protected int getAnimationResourceId()
			{
				return 0;
			}
		};
		resourceAnimationAdapter.setAbsListView(view);
		return resourceAnimationAdapter;
	}
}
