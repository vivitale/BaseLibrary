package talex.zsw.baselibrary.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import talex.zsw.baselibrary.R;

public class MaterialFavoriteButton extends ImageView
{
	private static final int DEFAULT_PADDING = 12;
	private static final boolean DEFAULT_FAVORITE = false;
	private static final boolean DEFAULT_ANIMATE_FAVORITE = true;
	private static final boolean DEFAULT_ANIMATE_UNFAVORITE = false;
	private static final int DEFAULT_ROTATION_DURATION = 400;
	private static final int DEFAULT_ROTATION_ANGLE = 360;
	private static final int DEFAULT_BOUNCE_DURATION = 300;
	private static final int FAVORITE_STAR_BLACK = R.drawable.ic_star_black_24dp;
	private static final int FAVORITE_STAR_BORDER_BLACK = R.drawable.ic_star_border_black_24dp;
	private static final int FAVORITE_STAR_WHITE = R.drawable.ic_star_white_24dp;
	private static final int FAVORITE_STAR_BORDER_WHITE = R.drawable.ic_star_border_white_24dp;
	private static final int FAVORITE_HEART_BLACK = R.drawable.ic_favorite_black_24dp;
	private static final int FAVORITE_HEART_BORDER_BLACK = R.drawable.ic_favorite_border_black_24dp;
	private static final int FAVORITE_HEART_WHITE = R.drawable.ic_favorite_white_24dp;
	private static final int FAVORITE_HEART_BORDER_WHITE = R.drawable.ic_favorite_border_white_24dp;
	public static final int STYLE_BLACK = 0;
	public static final int STYLE_WHITE = 1;
	public static final int STYLE_STAR = 0;
	public static final int STYLE_HEART = 1;
	private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR =
		new AccelerateInterpolator();
	private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR =
		new OvershootInterpolator(4);

	private int mButtonSize;
	private int mPadding;
	private boolean mFavorite;
	private boolean mAnimateFavorite;
	private boolean mAnimateUnfavorite;
	private int mFavoriteResource;
	private int mNotFavoriteResource;
	private int mRotationDuration;
	private int mRotationAngle;
	private int mBounceDuration;
	private int mColor;
	private int mType;

	private OnFavoriteChangeListener mOnFavoriteChangeListener;
	private OnFavoriteAnimationEndListener mOnFavoriteAnimationEndListener;
	private boolean mBroadcasting;

	public MaterialFavoriteButton(Context context)
	{
		super(context);
		init(context, null);
	}

	public MaterialFavoriteButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context, attrs);
	}

	public MaterialFavoriteButton(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	/**
	 * Interface definition for a callback to be invoked when the favorite state is changed.
	 * 定义接口：当喜欢状态改变时候调用
	 */
	public interface OnFavoriteChangeListener
	{
		/**
		 * Called when the favorite state is changed.
		 *
		 * @param buttonView the button view whose state has changed
		 * @param favorite   the favorite state
		 */
		public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite);
	}

	/**
	 * Set a listener will be called when the favorite state is changed.
	 * 设置当喜欢状态改变时候调用的接口
	 *
	 * @param listener the {@link MaterialFavoriteButton.OnFavoriteChangeListener} will be called
	 */
	public void setOnFavoriteChangeListener(OnFavoriteChangeListener listener)
	{
		mOnFavoriteChangeListener = listener;
	}

	/**
	 * Interface definition for a callback to be invoked when the favorite animation ends.
	 * 定义接口：当动画结束时候调用的方法
	 */
	public interface OnFavoriteAnimationEndListener
	{
		/**
		 * Called when the favorite animation ended.
		 *
		 * @param buttonView the button view whose animation ended
		 * @param favorite   the favorite state
		 */
		public void onAnimationEnd(MaterialFavoriteButton buttonView, boolean favorite);
	}

	/**
	 * Set a listener will be called when the favorite state is changed.
	 * 设置当动画结束时候调用的接口
	 *
	 * @param listener the {@link MaterialFavoriteButton.OnFavoriteAnimationEndListener} will be
	 *                 called
	 */
	public void setOnFavoriteAnimationEndListener(OnFavoriteAnimationEndListener listener)
	{
		mOnFavoriteAnimationEndListener = listener;
	}

	/**
	 * Initialize the default values
	 * <ul>
	 * <li>size = 48 dp</li>
	 * <li>padding = 12 dp</li>
	 * <li>is mFavorite = false</li>
	 * <li>animated = true</li>
	 * <li>default drawables - stars</li>
	 * <li>rotation duration = 300 ms</li>
	 * <li>rotation angle = 360 degrees</li>
	 * <li>bounce duration = 300 ms</li>
	 * <li>color of default icon = black</li>
	 * <li>type of default icon = star</li>
	 * </ul>
	 */
	private void init(Context context, AttributeSet attrs)
	{
		mButtonSize = dpToPx(48, getResources());
		mPadding = dpToPx(DEFAULT_PADDING, getResources());
		mFavorite = DEFAULT_FAVORITE;
		mAnimateFavorite = DEFAULT_ANIMATE_FAVORITE;
		mAnimateUnfavorite = DEFAULT_ANIMATE_UNFAVORITE;
		mFavoriteResource = FAVORITE_STAR_BLACK;
		mNotFavoriteResource = FAVORITE_STAR_BORDER_BLACK;
		mRotationDuration = DEFAULT_ROTATION_DURATION;
		mRotationAngle = DEFAULT_ROTATION_ANGLE;
		mBounceDuration = DEFAULT_BOUNCE_DURATION;
		mColor = STYLE_BLACK;
		mType = STYLE_STAR;
		if (!isInEditMode())
		{
			if (attrs != null)
			{
				initAttributes(context, attrs);
			}
			setOnClickListener(new OnClickListener()
			{
				@Override public void onClick(View v)
				{
					toggleFavorite();
				}
			});
		}
		if (mFavorite)
		{
			setImageResource(mFavoriteResource);
		}
		else
		{
			setImageResource(mNotFavoriteResource);
		}
		setPadding(mPadding, mPadding, mPadding, mPadding);
	}

	private void initAttributes(Context context, AttributeSet attributeSet)
	{
		TypedArray attr = getTypedArray(context, attributeSet, R.styleable.MaterialFavoriteButton);
		if (attr != null)
		{
			try
			{
				mAnimateFavorite =
					attr.getBoolean(R.styleable.MaterialFavoriteButton_mfb_animate_favorite,
						mAnimateFavorite);
				mAnimateUnfavorite =
					attr.getBoolean(R.styleable.MaterialFavoriteButton_mfb_animate_unfavorite,
						mAnimateUnfavorite);
				mPadding = dpToPx(
					attr.getInt(R.styleable.MaterialFavoriteButton_mfb_padding, DEFAULT_PADDING),
					getResources());
				if (attr.getResourceId(R.styleable.MaterialFavoriteButton_mfb_favorite_image, 0) !=
					0
					&&
					attr.getResourceId(R.styleable.MaterialFavoriteButton_mfb_not_favorite_image, 0)
						!= 0)
				{
					mFavoriteResource =
						attr.getResourceId(R.styleable.MaterialFavoriteButton_mfb_favorite_image,
							FAVORITE_STAR_BLACK);
					mNotFavoriteResource =
						attr.getResourceId(
							R.styleable.MaterialFavoriteButton_mfb_not_favorite_image,
							FAVORITE_STAR_BORDER_BLACK);
				}
				else
				{
					setTheme(attr.getInt(R.styleable.MaterialFavoriteButton_mfb_color, STYLE_BLACK),
						attr.getInt(R.styleable.MaterialFavoriteButton_mfb_type, STYLE_STAR));
				}

				mRotationDuration =
					attr.getInt(R.styleable.MaterialFavoriteButton_mfb_rotation_duration,
						mRotationDuration);
				mRotationAngle =
					attr.getInt(R.styleable.MaterialFavoriteButton_mfb_rotation_angle,
						mRotationAngle);
				mBounceDuration =
					attr.getInt(R.styleable.MaterialFavoriteButton_mfb_bounce_duration,
						mBounceDuration);
			} finally
			{
				attr.recycle();
			}
		}
	}

	private TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr)
	{
		return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
	}

	private void setTheme(int color, int type)
	{
		if (color == STYLE_BLACK)
		{
			if (type == STYLE_STAR)
			{
				mFavoriteResource = FAVORITE_STAR_BLACK;
				mNotFavoriteResource = FAVORITE_STAR_BORDER_BLACK;
			}
			else
			{
				mFavoriteResource = FAVORITE_HEART_BLACK;
				mNotFavoriteResource = FAVORITE_HEART_BORDER_BLACK;
			}
		}
		else
		{
			if (type == STYLE_STAR)
			{
				mFavoriteResource = FAVORITE_STAR_WHITE;
				mNotFavoriteResource = FAVORITE_STAR_BORDER_WHITE;
			}
			else
			{
				mFavoriteResource = FAVORITE_HEART_WHITE;
				mNotFavoriteResource = FAVORITE_HEART_BORDER_WHITE;
			}
		}
	}

	private void setResources()
	{
		if (mFavorite)
		{
			setImageResource(mFavoriteResource);
		}
		else
		{
			setImageResource(mNotFavoriteResource);
		}
	}

	@Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(mButtonSize, mButtonSize);
	}

	/**
	 * Returns favorite state.
	 * 返回按钮当前的状态
	 *
	 * @return true if button is in favorite state, false if not
	 */
	public boolean isFavorite()
	{
		return mFavorite;
	}

	/**
	 * Changes the favorite state of this button.
	 * 修改按钮的状态
	 *
	 * @param favorite true to favorite the button, false to uncheck it
	 */
	public void setFavorite(boolean favorite)
	{
		if (mFavorite != favorite)
		{
			mFavorite = favorite;
			// Avoid infinite recursions if setChecked() is called from a listener
			if (mBroadcasting)
			{
				return;
			}

			mBroadcasting = true;
			if (mOnFavoriteChangeListener != null)
			{
				mOnFavoriteChangeListener.onFavoriteChanged(this, mFavorite);
			}
			updateFavoriteButton(favorite);
			mBroadcasting = false;
		}
	}

	/**
	 * Changes the favorite state of this button.
	 * 修改按钮的状态
	 *
	 * @param favorite true to favorite the button, false to uncheck it
	 * @param animated true to force animated change, false to force not animated one
	 */
	public void setFavorite(boolean favorite, boolean animated)
	{
		if (favorite)
		{
			boolean orig = mAnimateFavorite;
			mAnimateFavorite = animated;
			setFavorite(favorite);
			mAnimateFavorite = orig;
		}
		else
		{
			boolean orig = mAnimateUnfavorite;
			mAnimateUnfavorite = animated;
			setFavorite(favorite);
			mAnimateUnfavorite = orig;
		}
	}

	/**
	 * Toggle the favorite state of this button.
	 * 改变喜欢状态
	 */
	public void toggleFavorite()
	{
		setFavorite(!mFavorite);
	}

	/**
	 * Toggle the favorite state of this button.
	 * 改变喜欢状态
	 *
	 * @param animated true to force animated toggle, false to force not animated one
	 */
	public void toggleFavorite(boolean animated)
	{
		if (!mFavorite)
		{
			boolean orig = mAnimateFavorite;
			mAnimateFavorite = animated;
			setFavorite(!mFavorite);
			mAnimateFavorite = orig;
		}
		else
		{
			boolean orig = mAnimateUnfavorite;
			mAnimateUnfavorite = animated;
			setFavorite(!mFavorite);
			mAnimateUnfavorite = orig;
		}
	}

	private void updateFavoriteButton(boolean favorite)
	{
		if (favorite)
		{
			if (mAnimateFavorite)
			{
				animateButton(favorite);
			}
			else
			{
				super.setImageResource(mFavoriteResource);
				if (mOnFavoriteAnimationEndListener != null)
				{
					mOnFavoriteAnimationEndListener.onAnimationEnd(this, mFavorite);
				}
			}
		}
		else
		{
			if (mAnimateUnfavorite)
			{
				animateButton(favorite);
			}
			else
			{
				super.setImageResource(mNotFavoriteResource);
				if (mOnFavoriteAnimationEndListener != null)
				{
					mOnFavoriteAnimationEndListener.onAnimationEnd(this, mFavorite);
				}
			}
		}
	}

	private void animateButton(boolean toFavorite)
	{
		final int startAngle = 0;
		int endAngle;
		float startBounce;
		float endBounce;
		if (toFavorite)
		{
			endAngle = mRotationAngle;
			startBounce = 0.2f;
			endBounce = 1.0f;
		}
		else
		{
			endAngle = -mRotationAngle;
			startBounce = 1.3f;
			endBounce = 1.0f;
		}

		AnimatorSet animatorSet = new AnimatorSet();
		ObjectAnimator rotationAnim =
			ObjectAnimator.ofFloat(this, "rotation", startAngle, endAngle);
		rotationAnim.setDuration(mRotationDuration);
		rotationAnim.setInterpolator(ACCELERATE_INTERPOLATOR);

		ObjectAnimator bounceAnimX = ObjectAnimator.ofFloat(this, "scaleX", startBounce, endBounce);
		bounceAnimX.setDuration(mBounceDuration);
		bounceAnimX.setInterpolator(OVERSHOOT_INTERPOLATOR);

		ObjectAnimator bounceAnimY = ObjectAnimator.ofFloat(this, "scaleY", startBounce, endBounce);
		bounceAnimY.setDuration(mBounceDuration);
		bounceAnimY.setInterpolator(OVERSHOOT_INTERPOLATOR);
		bounceAnimY.addListener(new AnimatorListenerAdapter()
		{
			@Override public void onAnimationStart(Animator animation)
			{
				if (mFavorite)
				{
					setImageResource(mFavoriteResource);
				}
				else
				{
					setImageResource(mNotFavoriteResource);
				}
			}
		});

		animatorSet.play(rotationAnim);
		animatorSet.play(bounceAnimX).with(bounceAnimY).after(rotationAnim);

		animatorSet.addListener(new AnimatorListenerAdapter()
		{
			@Override public void onAnimationEnd(Animator animation)
			{
				if (mOnFavoriteAnimationEndListener != null)
				{
					mOnFavoriteAnimationEndListener
						.onAnimationEnd(MaterialFavoriteButton.this, mFavorite);
				}
			}
		});

		animatorSet.start();
	}

	/**
	 * Builder.
	 */
	public static final class Builder
	{
		private final Context context;

		private int mPadding = DEFAULT_PADDING;
		private boolean mFavorite = DEFAULT_FAVORITE;
		private boolean mAnimateFavorite = DEFAULT_ANIMATE_FAVORITE;
		private boolean mAnimateUnfavorite = DEFAULT_ANIMATE_UNFAVORITE;
		private int mFavoriteResource = FAVORITE_STAR_BLACK;
		private int mNotFavoriteResource = FAVORITE_STAR_BORDER_BLACK;
		private int mRotationDuration = DEFAULT_ROTATION_DURATION;
		private int mRotationAngle = DEFAULT_ROTATION_ANGLE;
		private int mBounceDuration = DEFAULT_BOUNCE_DURATION;
		private int mColor = STYLE_WHITE;
		private int mType = STYLE_BLACK;
		private boolean mCustomRecources = false;

		public Builder(Context context)
		{
			this.context = context;
		}

		public Builder padding(int padding)
		{
			this.mPadding = padding;
			return this;
		}

		public Builder favorite(boolean favorite)
		{
			this.mFavorite = favorite;
			return this;
		}

		public Builder animateFavorite(boolean animation)
		{
			this.mAnimateFavorite = animation;
			return this;
		}

		public Builder animateUnfavorite(boolean animation)
		{
			this.mAnimateUnfavorite = animation;
			return this;
		}

		public Builder favoriteResource(int resource)
		{
			this.mFavoriteResource = resource;
			mCustomRecources = true;
			return this;
		}

		public Builder notFavoriteResource(int recsource)
		{
			this.mNotFavoriteResource = recsource;
			mCustomRecources = true;
			return this;
		}

		public Builder rotationDuration(int rotationDuration)
		{
			this.mRotationDuration = rotationDuration;
			return this;
		}

		public Builder rotationAngle(int rotationAngle)
		{
			this.mRotationAngle = rotationAngle;
			return this;
		}

		public Builder bounceDuration(int bounceDuration)
		{
			this.mBounceDuration = bounceDuration;
			return this;
		}

		public Builder color(int color)
		{
			this.mColor = color;
			mCustomRecources = false;
			return this;
		}

		public Builder type(int type)
		{
			this.mType = type;
			mCustomRecources = false;
			return this;
		}

		public MaterialFavoriteButton create()
		{
			MaterialFavoriteButton materialFavoriteButton = new MaterialFavoriteButton(context);
			materialFavoriteButton.setPadding(mPadding);
			materialFavoriteButton.setFavorite(mFavorite, false);
			materialFavoriteButton.setAnimateFavorite(mAnimateFavorite);
			materialFavoriteButton.setAnimateUnfavorite(mAnimateUnfavorite);
			materialFavoriteButton.setFavoriteResource(mFavoriteResource);
			materialFavoriteButton.setNotFavoriteResource(mNotFavoriteResource);
			materialFavoriteButton.setRotationDuration(mRotationDuration);
			materialFavoriteButton.setRotationAngle(mRotationAngle);
			materialFavoriteButton.setBounceDuration(mBounceDuration);
			if (!mCustomRecources)
			{
				materialFavoriteButton.setColor(mColor);
				materialFavoriteButton.setType(mType);
			}
			materialFavoriteButton.setResources();

			return materialFavoriteButton;
		}
	}

	public void setPadding(int padding)
	{
		this.mPadding = padding;
	}

	public void setAnimateFavorite(boolean animation)
	{
		this.mAnimateFavorite = animation;
	}

	public void setAnimateUnfavorite(boolean animation)
	{
		this.mAnimateUnfavorite = animation;
	}

	public void setFavoriteResource(int favoriteResource)
	{
		this.mFavoriteResource = favoriteResource;
	}

	public void setNotFavoriteResource(int notFavoriteResource)
	{
		this.mNotFavoriteResource = notFavoriteResource;
	}

	public void setRotationDuration(int rotationDuration)
	{
		this.mRotationDuration = rotationDuration;
	}

	public void setRotationAngle(int rotationAngle)
	{
		this.mRotationAngle = rotationAngle;
	}

	public void setBounceDuration(int bounceDuration)
	{
		this.mBounceDuration = bounceDuration;
	}

	public void setColor(int color)
	{
		this.mColor = color;
		setTheme(color, mType);
	}

	public void setType(int type)
	{
		this.mType = type;
		setTheme(mColor, type);
	}

	/**
	 * Convert Dp to Pixel
	 */
	public static int dpToPx(float dp, Resources resources)
	{
		float px =
			TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
		return (int) px;
	}
}