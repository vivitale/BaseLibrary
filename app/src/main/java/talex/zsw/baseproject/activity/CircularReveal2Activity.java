package talex.zsw.baseproject.activity;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

import talex.zsw.baselibrary.view.CircularReveal.animation.SupportAnimator;
import talex.zsw.baselibrary.view.CircularReveal.animation.ViewAnimationUtils;
import talex.zsw.baselibrary.view.CircularReveal.widget.CardViewPlus;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/1/6 10:30 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CircularReveal2Activity extends AppCompatActivity
	implements ViewTreeObserver.OnGlobalLayoutListener
{

	private CardViewPlus mContentView;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_circualreveal1 );

		mContentView = (CardViewPlus) findViewById( R.id.content );

		getViewTreeObserver().addOnGlobalLayoutListener( this );
	}

	protected View getRootView()
	{
		return mContentView;
	}

	protected ViewTreeObserver getViewTreeObserver()
	{
		return getRootView().getViewTreeObserver();
	}

	protected void startRevealTransition()
	{
		final Rect bounds = new Rect();
		getRootView().getHitRect( bounds );
		SupportAnimator animator = ViewAnimationUtils
			.createCircularReveal( getRootView(), bounds.right, bounds.bottom, 0,
				hypo( bounds.height(), bounds.width() ) );
		animator.setDuration( 1000 );
		animator.setInterpolator( new AccelerateDecelerateInterpolator() );
		animator.start();
	}

	private float hypo(float a, float b)
	{
		return (float) Math.sqrt( Math.pow( a, 2 ) + Math.pow( b, 2 ) );
	}

	@Override public void onGlobalLayout()
	{
		if(Build.VERSION.SDK_INT >= 16)
		{
			getViewTreeObserver().removeOnGlobalLayoutListener( this );
		}
		else
		{
			getViewTreeObserver().removeGlobalOnLayoutListener( this );
		}

		startRevealTransition();
	}
}
