package talex.zsw.baselibrary.view.SwipeToLoadLayout.footer;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import talex.zsw.baselibrary.R;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeLoadMoreTrigger;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeTrigger;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.drawable.google.GoogleCircleProgressView;


public class GoogleHookLoadMoreFooterView extends FrameLayout
	implements SwipeTrigger, SwipeLoadMoreTrigger
{

	private GoogleCircleProgressView progressView;

	private int mTriggerOffset;
	private int mFinalOffset;

	public GoogleHookLoadMoreFooterView(Context context)
	{
		this( context, null );
	}

	public GoogleHookLoadMoreFooterView(Context context, AttributeSet attrs)
	{
		this( context, attrs, 0 );
	}

	public GoogleHookLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super( context, attrs, defStyleAttr );
		mTriggerOffset = context.getResources()
			.getDimensionPixelOffset( R.dimen.load_more_trigger_offset_google );
		mFinalOffset =
			context.getResources().getDimensionPixelOffset( R.dimen.load_more_final_offset_google );
	}

	@Override protected void onFinishInflate()
	{
		super.onFinishInflate();
		progressView = (GoogleCircleProgressView) findViewById( R.id.googleProgress );
		progressView.setColorSchemeResources( R.color.google_blue, R.color.google_red,
			R.color.google_yellow, R.color.google_green );
		progressView.setStartEndTrim( 0, (float) 0.75 );
	}

	@Override public void onLoadMore()
	{
		progressView.start();
	}

	@Override public void onPrepare()
	{
		progressView.setStartEndTrim( 0, (float) 0.75 );
	}

	@Override public void onSwipe(int y, boolean isComplete)
	{
		float alpha = -y / (float) mTriggerOffset;
		Log.i( "onSwipe", "alpha= " + alpha );
		ViewCompat.setAlpha( progressView, alpha );
		if(!isComplete)
		{
			progressView.setProgressRotation( -y * (1f) / (float) mFinalOffset );
		}
	}

	@Override public void onRelease()
	{
	}

	@Override public void complete()
	{
		progressView.stop();
	}

	@Override public void onReset()
	{
		ViewCompat.setAlpha( progressView, 1f );
	}
}
