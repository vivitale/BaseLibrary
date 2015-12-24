package talex.zsw.baselibrary.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ListView;

public class RefreshLayout extends SwipeRefreshLayout
{

	private final int mTouchSlop;
	private ListView mListView;
	private OnLoadListener mOnLoadListener;

	private float firstTouchY;
	private float lastTouchY;

	private boolean isLoading = false;

	public RefreshLayout(Context context)
	{
		this(context, null);
	}

	public RefreshLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	//set the child view of RefreshLayout,ListView
	public void setChildView(ListView mListView)
	{
		this.mListView = mListView;
	}

	@Override
	public boolean dispatchTouchEvent(@NonNull MotionEvent event)
	{
		final int action = event.getAction();
		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
				firstTouchY = event.getRawY();
				break;

			case MotionEvent.ACTION_UP:
				lastTouchY = event.getRawY();
				if (canLoadMore())
				{
					loadData();
				}
				break;
			default:
				break;
		}

		return super.dispatchTouchEvent(event);
	}

	private boolean canLoadMore()
	{
		return isBottom() && !isLoading && isPullingUp();
	}

	private boolean isBottom()
	{
		if (mListView.getCount() > 0)
		{
			if (mListView.getLastVisiblePosition() == mListView.getAdapter().getCount() - 1 &&
				mListView.getChildAt(mListView.getChildCount() - 1).getBottom() <=
					mListView.getHeight())
			{
				return true;
			}
		}
		return false;
	}

	private boolean isPullingUp()
	{
		return (firstTouchY - lastTouchY) >= mTouchSlop;
	}

	private void loadData()
	{
		if (mOnLoadListener != null)
		{
			setLoading(true);
		}
	}

	public void setLoading(boolean loading)
	{
		if (mListView == null) return;
		isLoading = loading;
		if (loading)
		{
			if (isRefreshing())
			{
				setRefreshing(false);
			}
			mListView.setSelection(mListView.getAdapter().getCount() - 1);
			mOnLoadListener.onLoad();
		}
		else
		{
			firstTouchY = 0;
			lastTouchY = 0;
		}
	}

	public void setOnLoadListener(OnLoadListener loadListener)
	{
		mOnLoadListener = loadListener;
	}

	public interface OnLoadListener
	{
		void onLoad();
	}
}