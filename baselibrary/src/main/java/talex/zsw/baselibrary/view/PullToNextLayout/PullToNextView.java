package talex.zsw.baselibrary.view.PullToNextLayout;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import talex.zsw.baselibrary.R;

/**
 * @author tale email:vvtale@gmail.com
 * @ClassName: PullToNextView 
 * @Description: 通过Fragment来实现滑动到下一个视图的布局
 * @date 2015年4月15日 上午9:53:58  
 */
public class PullToNextView extends LinearLayout
{
	private static final int ANIMATION_DURATION = 500;// 动画持续时间
	/** last y */
	private int mLastMotionY;
	private View mHeaderView;// 顶部视图
	private View mFootView;// 底部视图
	private ScrollView mScrollView;
	private WebView mWebView;

	private boolean isHashPrevious = true;
	private boolean isHashNext = true;
	private int mHeadViewHeight;
	private ViewGroup contentView;
	private TextView headPromptTV;
	private TextView footPromptTV;
	private String[] states;

	/** @Fields mPullStateE : 当前的滑动状态 */
	private PullStateE mPullStateE = PullStateE.PULL_STATE_NONE;

	public PullToNextView(Context context)
	{
		super( context );
		init();
	}

	public PullToNextView(Context context, AttributeSet attrs)
	{
		super( context, attrs );
		init();
	}

	public PullToNextView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super( context, attrs, defStyleAttr );
		init();
	}

	// Android L 下可以用的属性
	// @TargetApi(Build.VERSION_CODES.LOLLIPOP)
	// public PullToNextView(Context context, AttributeSet attrs,
	// int defStyleAttr, int defStyleRes)
	// {
	// super(context, attrs, defStyleAttr, defStyleRes);
	// init();
	// }

	/** 初始化 */
	private void init()
	{
		setOrientation( LinearLayout.VERTICAL );// 需要设置成vertical
		states = getResources().getStringArray( R.array.pullToNextViewStates );
		hiddenHeaderView();
	}

	/**
	 * @param child 要计算大小的{@link View}
	 */
	private void measureView(View child)
	{
		ViewGroup.LayoutParams params = child.getLayoutParams();
		if(params == null)
		{
			params = new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT );
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec( 0, 0, params.width );
		int lpHeight = params.height;
		int childHeightSpec;
		if(lpHeight > 0)
		{
			childHeightSpec = MeasureSpec.makeMeasureSpec( lpHeight, MeasureSpec.EXACTLY );
		}
		else
		{
			childHeightSpec = MeasureSpec.makeMeasureSpec( 0, MeasureSpec.UNSPECIFIED );
		}
		child.measure( childWidthSpec, childHeightSpec );
	}

	@Override protected void onFinishInflate()
	{
		super.onFinishInflate();
		hiddenFootView();
		initContentAdapterView( this );
	}

	public void setContentView(ViewGroup view)
	{
		// hiddenHeaderView();
		contentView = view;
		// 添加了内容控件.
		ViewGroup.LayoutParams params =
			new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT );
		addView( view, params );
		// 添加了脚部控件
		hiddenFootView();
		initContentAdapterView( contentView );
	}

	private void hiddenFootView()
	{
		mFootView = LayoutInflater.from( getContext() )
			.inflate( R.layout.widget_pulltonext_head_foot, null );
		measureView( mFootView );
		mHeadViewHeight = mFootView.getMeasuredHeight();

		ViewGroup.LayoutParams footParams =
			new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, mHeadViewHeight );

		footPromptTV = (TextView) mFootView.findViewById( R.id.promptTV );

		addView( mFootView, footParams );
	}

	/**
	 * 隐藏了头部布局
	 */
	private void hiddenHeaderView()
	{
		mHeaderView = LayoutInflater.from( getContext() )
			.inflate( R.layout.widget_pulltonext_head_foot, null );
		measureView( mHeaderView );
		headPromptTV = (TextView) mHeaderView.findViewById( R.id.promptTV );

		mHeadViewHeight = mHeaderView.getMeasuredHeight();
		addView( mHeaderView );
		setHeaderTopMargin( -mHeadViewHeight );
	}

	/**
	 * 设置header view 的topMargin的值
	 *
	 * @param topMargin ，为0时，说明header view 刚好完全显示出来； 为-mHeaderViewHeight时，说明完全隐藏了
	 */
	private void setHeaderTopMargin(int topMargin)
	{
		LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
		params.topMargin = topMargin;
		mHeaderView.setLayoutParams( params );
		invalidate();
	}

	/**
	 * 初始化内容区域的Fragment
	 *
	 * @param fm       FragmentManager
	 * @param position 要显示的Fragment的Tag:position
	 */
	public void initContentView(final FragmentManager fm, final int position)
	{
		if(contentView != null)
		{
			contentView.postDelayed( new Runnable()
			{
				@Override @SuppressWarnings("unused") public void run()
				{
					Fragment fragment = fm.findFragmentByTag( "position" + position );
					FrameLayout frameLayout = (FrameLayout) contentView;
					ViewGroup v = (ViewGroup) frameLayout.getRootView();
					if(fragment != null)
					{
						a( fragment.getView() );
					}
				}
			}, 20 );
		}
	}

	/**
	 * @param o 根据Object的不同(ScrollView或者WebView),去掉其滑动到底部或顶部
	 *          继续滑动时会出现渐变的蓝色颜色快
	 */
	public void a(Object o)
	{
		if(o instanceof ViewGroup)
		{
			ViewGroup vp = (ViewGroup) o;
			if(o instanceof ScrollView)
			{
				mScrollView = (ScrollView) o;
				mScrollView.setOverScrollMode( OVER_SCROLL_NEVER );
			}
			else if(o instanceof WebView)
			{
				mWebView = (WebView) o;
				mWebView.setOverScrollMode( OVER_SCROLL_NEVER );
			}
			for(int i = 0; i < vp.getChildCount(); i++)
			{
				a( vp.getChildAt( i ) );
			}
		}
	}

	/**
	 * @param contentView 去掉contentView中ScrollView滑动到底部或顶部继续滑动时会出现渐变的蓝色颜色快
	 */
	public void initContentAdapterView(ViewGroup contentView)
	{
		if(contentView == null)
		{
			contentView = this;
		}
		int count = contentView.getChildCount();
		View view = null;
		for(int i = 0; i < count; ++i)
		{
			view = contentView.getChildAt( i );
			if(view instanceof ScrollView)
			{
				// finish later
				mScrollView = (ScrollView) view;
				mScrollView.setOverScrollMode( OVER_SCROLL_NEVER );
			}
		}
	}

	boolean isBlock = false;
	int downy;

	@Override public boolean onInterceptTouchEvent(MotionEvent e)
	{
		int y = (int) e.getRawY();
		switch(e.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				// 首先拦截down事件,记录y坐标
				mLastMotionY = y;
				downy = y;
				break;
			case MotionEvent.ACTION_MOVE:
				// deltaY > 0 是向下运动,< 0是向上运动
				int deltaY = y - downy;
				return isRefreshViewScroll( deltaY );
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				isBlock = false;
				break;
		}
		return super.onInterceptTouchEvent( e );
	}

	/*
	 * 如果在onInterceptTouchEvent()方法中没有拦截(即onInterceptTouchEvent()方法中 return
	 * false)则由PullToRefreshView 的子View来处理;否则由下面的方法来处理(即由PullToRefreshView自己来处理)
	 */
	@Override public boolean onTouchEvent(MotionEvent event)
	{
		// if (mLock) {
		// return true;
		// }
		int y = (int) event.getRawY();
		switch(event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_MOVE:
				int deltaY = y - mLastMotionY;
				changingHeaderViewTopMargin( deltaY );
				mLastMotionY = y;
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				int topMargin = getHeaderTopMargin();
				if(topMargin < -3 * mHeadViewHeight)
				{
					if(isHashNext)
					{
						pullToNextI.next();
					}
					else
					{
						moveTo( -mHeadViewHeight, ANIMATION_DURATION );
					}
				}
				else if(topMargin > mHeadViewHeight)
				{
					if(isHashPrevious)
					{
						pullToNextI.previous();
					}
					else
					{
						moveTo( -mHeadViewHeight, ANIMATION_DURATION );
					}
				}
				else
				{
					moveTo( -mHeadViewHeight, ANIMATION_DURATION );
				}
				break;
		}
		return super.onTouchEvent( event );
	}

	public void moveTo(final float i, int duration)
	{
		int topMargin = getHeaderTopMargin();
		ValueAnimator animator = ValueAnimator.ofFloat( topMargin, i );
		animator.setDuration( duration );
		animator.setInterpolator( new DecelerateInterpolator() );
		mPullStateE = PullStateE.PULL_STATE_NONE;
		animator.addUpdateListener( new ValueAnimator.AnimatorUpdateListener()
		{

			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			public void onAnimationUpdate(ValueAnimator valueAnimator)
			{

				float temp = (Float) valueAnimator.getAnimatedValue();

				if(temp == i)
				{
					mPullStateE = PullStateE.PULL_STATE_NONE;
				}
				setHeaderTopMargin( (int) temp );
			}
		} );
		animator.start();
	}

	public void reset(FragmentManager fm, int position)
	{
		setHeaderTopMargin( -mHeadViewHeight );
		initContentView( fm, position );
	}

	/**
	 * @param deltaY 滑动变量
	 * @return 根据滑动变量以及View的位置, 判断当前滑动状态是否改变
	 */
	@SuppressWarnings({"unused", "deprecation"}) private boolean isRefreshViewScroll(int deltaY)
	{
		if(PullStateE.PULL_STATE_REFRESH == mPullStateE)
		{
			return false;
		}
		LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
		// 对于ScrollView
		if(mScrollView != null)
		{
			// 子View滑动到最顶端
			View child = mScrollView.getChildAt( 0 );
			if(deltaY > 12 && mScrollView.getScrollY() == 0)
			{
				// 向下
				mPullStateE = PullStateE.PULL_STATE_DOWN;
				return true;
			}
			else if(deltaY < -12 &&

				child.getMeasuredHeight() <= contentView.getHeight() + mScrollView.getScrollY())
			{
				// 向上
				mPullStateE = PullStateE.PULL_STATE_UP;
				return true;
			}
		}
		else if(mWebView != null)
		{
			if(deltaY > 12 && mWebView.getScrollY() == 0)
			{
				// 向下
				mPullStateE = PullStateE.PULL_STATE_DOWN;

				return true;
			}
			else if(deltaY < -12 &&
				(int) (mWebView.getContentHeight() * mWebView.getScale()) - mWebView.getHeight() -
					mWebView.getScrollY() == 0)
			{
				// 向上
				mPullStateE = PullStateE.PULL_STATE_UP;
				return true;
			}
		}
		else
		{
			return true;
		}
		return false;
	}

	/**
	 * 修改Header view top margin的值
	 *
	 * @param deltaY
	 */
	private int changingHeaderViewTopMargin(int deltaY)
	{
		LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
		float newTopMargin = params.topMargin + deltaY * 0.5f;

		if(mPullStateE == PullStateE.PULL_STATE_UP)
		{
			newTopMargin = Math.min( newTopMargin, -mHeadViewHeight );
		}
		else if(PullStateE.PULL_STATE_DOWN == mPullStateE)
		{
			newTopMargin = Math.max( newTopMargin, -mHeadViewHeight );
		}
		params.topMargin = (int) newTopMargin;
		mHeaderView.setLayoutParams( params );

		if(newTopMargin < -mHeadViewHeight)
		{
			// 下一个
			if(!isHashNext)
			{
				footPromptTV.setText( states[0] );
			}
			else if(newTopMargin < -3 * mHeadViewHeight)
			{
				footPromptTV.setText( states[1] );
			}
			else
			{
				footPromptTV.setText( states[2] );
			}
		}
		else if(newTopMargin > -mHeadViewHeight)
		{
			// 前一个
			if(!isHashPrevious)
			{
				headPromptTV.setText( states[3] );
			}
			else if(newTopMargin > mHeadViewHeight)
			{
				headPromptTV.setText( states[4] );
			}
			else
			{
				headPromptTV.setText( states[5] );
			}
		}
		else
		{
			moveTo( -mHeadViewHeight, ANIMATION_DURATION );
		}
		invalidate();
		return params.topMargin;
	}

	/**
	 * 一定要按照以下格式
	 * ---------------
	 * 没有更多信息
	 * 放手查看下一个
	 * 上拉查看下一个
	 * 已经是第一个了
	 * 放手查看前一个
	 * 下拉查看前一个
	 */
	public void setStates(String[] states)
	{
		this.states = states;
	}

	/**
	 * 获取当前header view 的topMargin
	 */
	private int getHeaderTopMargin()
	{
		LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
		return params.topMargin;
	}

	private PullToNextI pullToNextI;

	public void setPullToNextI(PullToNextI pullToNextI)
	{
		this.pullToNextI = pullToNextI;
	}

	protected interface PullToNextI
	{
		public void previous();

		public void next();
	}

	enum PullStateE
	{
		PULL_STATE_NONE, PULL_STATE_DOWN, PULL_STATE_UP, PULL_STATE_REFRESH
	}

	public void setHashPrevious(boolean isHashPrevious)
	{
		this.isHashPrevious = isHashPrevious;
	}

	public void setHashNext(boolean isHashNext)
	{
		this.isHashNext = isHashNext;
	}
}
