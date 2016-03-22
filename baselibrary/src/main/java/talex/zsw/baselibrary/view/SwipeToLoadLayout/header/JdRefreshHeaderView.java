package talex.zsw.baselibrary.view.SwipeToLoadLayout.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import talex.zsw.baselibrary.R;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeRefreshTrigger;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeTrigger;


/**
 * Created by Aspsine on 2015/11/5.
 */
public class JdRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger
{

	private ImageView ivSpeed;

	private ImageView ivRefresh;

	private AnimationDrawable mAnimDrawable;

	private Animation mTwinkleAnim;

	private int mTriggerOffset;


	public JdRefreshHeaderView(Context context)
	{
		super( context );
	}

	public JdRefreshHeaderView(Context context, AttributeSet attrs)
	{
		super( context, attrs );
	}

	public JdRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super( context, attrs, defStyleAttr );
		mTriggerOffset =
			context.getResources().getDimensionPixelOffset( R.dimen.refresh_header_height_jd );
	}

	@Override protected void onFinishInflate()
	{
		super.onFinishInflate();
		ivRefresh = (ImageView) findViewById( R.id.ivRefresh );
		ivSpeed = (ImageView) findViewById( R.id.ivSpeed );
		mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
		mTwinkleAnim = AnimationUtils.loadAnimation( getContext(), R.anim.twinkle );
	}

	@Override public void onRefresh()
	{
		ivSpeed.setVisibility( VISIBLE );
		ivSpeed.startAnimation( mTwinkleAnim );
		if(!mAnimDrawable.isRunning())
		{
			mAnimDrawable.start();
		}
	}

	@Override public void onPrepare()
	{
		ivSpeed.clearAnimation();
		ivSpeed.setVisibility( GONE );
	}

	@Override public void onSwipe(int y, boolean isComplete)
	{
	}

	@Override public void onRelease()
	{
		if(!mAnimDrawable.isRunning())
		{
			mAnimDrawable.start();
		}
	}

	@Override public void complete()
	{
	}

	@Override public void onReset()
	{
		mAnimDrawable.stop();
		ivSpeed.clearAnimation();
		ivSpeed.setVisibility( GONE );
	}
}
