package talex.zsw.baselibrary.view.SwipeToLoadLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Aspsine on 2015/8/13.
 */
public class SwipeLoadMoreFooterLayout extends FrameLayout
	implements SwipeLoadMoreTrigger, SwipeTrigger
{

	public SwipeLoadMoreFooterLayout(Context context)
	{
		this( context, null );
	}

	public SwipeLoadMoreFooterLayout(Context context, AttributeSet attrs)
	{
		this( context, attrs, 0 );
	}

	public SwipeLoadMoreFooterLayout(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super( context, attrs, defStyleAttr );
	}

	@Override public void onLoadMore()
	{
	}

	@Override public void onPrepare()
	{
	}

	@Override public void onSwipe(int y, boolean isComplete)
	{
	}

	@Override public void onRelease()
	{

	}

	@Override public void complete()
	{

	}

	@Override public void onReset()
	{
	}
}
