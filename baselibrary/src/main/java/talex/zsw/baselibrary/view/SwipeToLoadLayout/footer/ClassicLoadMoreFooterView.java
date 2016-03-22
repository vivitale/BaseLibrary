package talex.zsw.baselibrary.view.SwipeToLoadLayout.footer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import talex.zsw.baselibrary.R;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeLoadMoreFooterLayout;


/**
 * Created by Aspsine on 2015/9/2.
 */
public class ClassicLoadMoreFooterView extends SwipeLoadMoreFooterLayout
{
	private TextView tvLoadMore;
	private ImageView ivSuccess;
	private ProgressBar progressBar;

	private int mFooterHeight;

	public ClassicLoadMoreFooterView(Context context)
	{
		this( context, null );
	}

	public ClassicLoadMoreFooterView(Context context, AttributeSet attrs)
	{
		this( context, attrs, 0 );
	}

	public ClassicLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super( context, attrs, defStyleAttr );
		mFooterHeight =
			getResources().getDimensionPixelOffset( R.dimen.load_more_footer_height_classic );
	}

	@Override protected void onFinishInflate()
	{
		super.onFinishInflate();
		tvLoadMore = (TextView) findViewById( R.id.tvLoadMore );
		ivSuccess = (ImageView) findViewById( R.id.ivSuccess );
		progressBar = (ProgressBar) findViewById( R.id.progressbar );
	}

	@Override public void onPrepare()
	{
		ivSuccess.setVisibility( GONE );
	}

	@Override public void onSwipe(int y, boolean isComplete)
	{
		if(!isComplete)
		{
			ivSuccess.setVisibility( GONE );
			progressBar.setVisibility( GONE );
			if(-y >= mFooterHeight)
			{
				tvLoadMore.setText( "松开加载更多..." );
			}
			else
			{
				tvLoadMore.setText( "下拉加载更多..." );
			}
		}
	}

	@Override public void onLoadMore()
	{
		tvLoadMore.setText( "加载更多" );
		progressBar.setVisibility( VISIBLE );
	}

	@Override public void onRelease()
	{

	}

	@Override public void complete()
	{
		progressBar.setVisibility( GONE );
		ivSuccess.setVisibility( VISIBLE );
	}

	@Override public void onReset()
	{
		ivSuccess.setVisibility( GONE );
	}
}
