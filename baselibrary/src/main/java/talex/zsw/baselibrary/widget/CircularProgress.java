package talex.zsw.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.util.Property;

import talex.zsw.baselibrary.R;

public class CircularProgress extends View
{
	/**
	 * 绘制圆弧起始位置角度的动画，这样该圆弧是打圈转的动画
	 */
	private static final Interpolator ANGLE_INTERPOLATOR = new LinearInterpolator();
	/**
	 * 绘制圆弧臂长的动画，该动画受 {@link #mModeAppearing} 控制
	 */
	private static final Interpolator SWEEP_INTERPOLATOR = new AccelerateDecelerateInterpolator();
	/**
	 * 圆弧起始位置动画的间隔，也就是多少毫秒圆弧转一圈，可以把该值扩大10倍来查看动画的慢动作
	 */
	private static final int ANGLE_ANIMATOR_DURATION = 2000;
	/**
	 * 圆弧臂长的动画间隔，也就是臂长从最小到最大值的变化时间，也可以把该值扩大10倍来查看动画的慢动作
	 */
	private static final int SWEEP_ANIMATOR_DURATION = 900;
	/**
	 * 圆弧的最下臂长是多少度,就是在最短的情况下的臂长弧度
	 */
	private static final int MIN_SWEEP_ANGLE = 45;
	/**
	 * 默认下的圆弧宽度
	 */
	private static final int DEFAULT_BORDER_WIDTH = 3;
	private final RectF fBounds = new RectF();
	/**
	 * 起始位置的动画对象
	 */
	private ObjectAnimator mObjectAnimatorSweep;
	/**
	 * 臂长的动画对象
	 */
	private ObjectAnimator mObjectAnimatorAngle;
	/**
	 * 控制臂长是逐渐增加还是逐渐减少
	 * 当 mModeAppearing 为 false 的时候，圆弧的起始点在增加，圆弧的终止点不变，弧长在逐渐减少；
	 * 当 mModeAppearing 为 true 的时候， 圆弧的起始点不变，圆弧的终止点变大，弧长在逐渐增加
	 */
	private boolean mModeAppearing = true;
	private Paint mPaint;
	/**
	 * 每次臂长增加 、减少 转换的时候， 圆弧起始位置的偏移量会增加 2 倍的最小臂长
	 */
	private float mCurrentGlobalAngleOffset;
	private float mCurrentGlobalAngle;
	private float mCurrentSweepAngle;
	/**
	 * 宽度
	 */
	private float mBorderWidth;
	private boolean mRunning;
	private int[] mColors;
	private int mCurrentColorIndex;
	private int mNextColorIndex;

	public CircularProgress(Context context)
	{
		this(context, null);
	}

	public CircularProgress(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public CircularProgress(Context context, AttributeSet attrs,
							int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);

		float density = context.getResources().getDisplayMetrics().density;// 获取屏幕密度
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircularProgress,
			defStyleAttr, 0);
		mBorderWidth = a.getDimension(R.styleable.CircularProgress_CP_BorderWidth,
			DEFAULT_BORDER_WIDTH * density);
		a.recycle();
		mColors = new int[4];
		mColors[0] = context.getResources().getColor(R.color.CircularProgress_1);
		mColors[1] = context.getResources().getColor(R.color.CircularProgress_2);
		mColors[2] = context.getResources().getColor(R.color.CircularProgress_3);
		mColors[3] = context.getResources().getColor(R.color.CircularProgress_4);
		mCurrentColorIndex = 0;
		mNextColorIndex = 1;

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeCap(Cap.ROUND);
		mPaint.setStrokeWidth(mBorderWidth);
		mPaint.setColor(mColors[mCurrentColorIndex]);

		setupAnimations();
	}

	private void start()
	{
		if (isRunning())
		{
			return;
		}
		mRunning = true;
		mObjectAnimatorAngle.start();
		mObjectAnimatorSweep.start();
		invalidate();
	}

	private void stop()
	{
		if (!isRunning())
		{
			return;
		}
		mRunning = false;
		mObjectAnimatorAngle.cancel();
		mObjectAnimatorSweep.cancel();
		invalidate();
	}

	private boolean isRunning()
	{
		return mRunning;
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility)
	{
		super.onVisibilityChanged(changedView, visibility);
		if (visibility == VISIBLE)
		{
			start();
		}
		else
		{
			stop();
		}
	}

	@Override
	protected void onAttachedToWindow()
	{
		start();
		super.onAttachedToWindow();
	}

	@Override
	protected void onDetachedFromWindow()
	{
		stop();
		super.onDetachedFromWindow();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		fBounds.left = mBorderWidth / 2f + .5f;
		fBounds.right = w - mBorderWidth / 2f - .5f;
		fBounds.top = mBorderWidth / 2f + .5f;
		fBounds.bottom = h - mBorderWidth / 2f - .5f;
	}

	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		float startAngle = mCurrentGlobalAngle - mCurrentGlobalAngleOffset;
		float sweepAngle = mCurrentSweepAngle;
		if (mModeAppearing)
		{
			mPaint.setColor(gradient(mColors[mCurrentColorIndex],
				mColors[mNextColorIndex], mCurrentSweepAngle
					/ (360 - MIN_SWEEP_ANGLE * 2)));
			sweepAngle += MIN_SWEEP_ANGLE;
		}
		else
		{
			startAngle = startAngle + sweepAngle;
			sweepAngle = 360 - sweepAngle - MIN_SWEEP_ANGLE;
		}
		canvas.drawArc(fBounds, startAngle, sweepAngle, false, mPaint);
	}

	private static int gradient(int color1, int color2, float p)
	{
		int r1 = (color1 & 0xff0000) >> 16;
		int g1 = (color1 & 0xff00) >> 8;
		int b1 = color1 & 0xff;
		int r2 = (color2 & 0xff0000) >> 16;
		int g2 = (color2 & 0xff00) >> 8;
		int b2 = color2 & 0xff;
		int newr = (int) (r2 * p + r1 * (1 - p));
		int newg = (int) (g2 * p + g1 * (1 - p));
		int newb = (int) (b2 * p + b1 * (1 - p));
		return Color.argb(255, newr, newg, newb);
	}

	private void toggleAppearingMode()
	{
		mModeAppearing = !mModeAppearing;
		if (mModeAppearing)
		{
			mCurrentColorIndex = ++mCurrentColorIndex % 4;
			mNextColorIndex = ++mNextColorIndex % 4;
			mCurrentGlobalAngleOffset = (mCurrentGlobalAngleOffset + MIN_SWEEP_ANGLE * 2) % 360;
		}
	}

	// ////////////////////////////////////////////////////////////////////////////
	// ////////////// Animation

	private Property<CircularProgress, Float>
		mAngleProperty = new Property<CircularProgress, Float>(
		Float.class, "angle")
	{
		@Override
		public Float get(CircularProgress object)
		{
			return object.getCurrentGlobalAngle();
		}

		@Override
		public void set(CircularProgress object, Float value)
		{
			object.setCurrentGlobalAngle(value);
		}
	};

	private Property<CircularProgress, Float>
		mSweepProperty = new Property<CircularProgress, Float>(
		Float.class, "arc")
	{
		@Override
		public Float get(CircularProgress object)
		{
			return object.getCurrentSweepAngle();
		}

		@Override
		public void set(CircularProgress object, Float value)
		{
			object.setCurrentSweepAngle(value);
		}
	};

	private void setupAnimations()
	{
		mObjectAnimatorAngle = ObjectAnimator.ofFloat(this, mAngleProperty,
			360f);
		mObjectAnimatorAngle.setInterpolator(ANGLE_INTERPOLATOR);
		mObjectAnimatorAngle.setDuration(ANGLE_ANIMATOR_DURATION);
		mObjectAnimatorAngle.setRepeatMode(ValueAnimator.RESTART);
		mObjectAnimatorAngle.setRepeatCount(ValueAnimator.INFINITE);

		mObjectAnimatorSweep = ObjectAnimator.ofFloat(this, mSweepProperty,
			360f - MIN_SWEEP_ANGLE * 2);
		mObjectAnimatorSweep.setInterpolator(SWEEP_INTERPOLATOR);
		mObjectAnimatorSweep.setDuration(SWEEP_ANIMATOR_DURATION);
		mObjectAnimatorSweep.setRepeatMode(ValueAnimator.RESTART);
		mObjectAnimatorSweep.setRepeatCount(ValueAnimator.INFINITE);
		mObjectAnimatorSweep.addListener(new Animator.AnimatorListener()
		{
			@Override
			public void onAnimationStart(Animator animation)
			{

			}

			@Override
			public void onAnimationEnd(Animator animation)
			{

			}

			@Override
			public void onAnimationCancel(Animator animation)
			{

			}

			@Override
			public void onAnimationRepeat(Animator animation)
			{
				toggleAppearingMode();
			}
		});
	}

	public void setCurrentGlobalAngle(float currentGlobalAngle)
	{
		mCurrentGlobalAngle = currentGlobalAngle;
		invalidate();
	}

	public float getCurrentGlobalAngle()
	{
		return mCurrentGlobalAngle;
	}

	public void setCurrentSweepAngle(float currentSweepAngle)
	{
		mCurrentSweepAngle = currentSweepAngle;
		invalidate();
	}

	public float getCurrentSweepAngle()
	{
		return mCurrentSweepAngle;
	}
}
