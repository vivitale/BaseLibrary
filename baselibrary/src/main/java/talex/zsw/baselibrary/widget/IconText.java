package talex.zsw.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Target;

import java.util.HashSet;

import talex.zsw.baselibrary.R;
import talex.zsw.baselibrary.view.Animation.ViewAnimator;


/**
 * 项目包名: widget
 * 作用: 顶部有图片的TextView
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 2016 16/4/6 10:38 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class IconText extends RelativeLayout
{
	private TextView mTextView;
	private ImageView mImageView;
	private RelativeLayout mRelativeLayout;

	public static final int DEFAULT_TEXT_SIZE = 9;
	public static final int DEFAULT_TEXT_COLOR = 0xff404040;
	public static final int DEFAULT_SELECTED_COLOR = 0xfff0f0f0;
	public static final int DEFAULT_UNSELECTED_COLOR = 0xff3ebdd8;

	private String text;
	private int textSelectedColor, textUnselectedColor;
	private int textSize;
	private int padding;
	private int mIconHeight;
	private Drawable selectedIcon, unselectedIcon;
	private boolean selected = false;
	private int selectedColor;
	private int unselectedColor;

	public IconText(Context context)
	{
		this(context, null);
	}

	public IconText(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public IconText(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs)
	{
		View rootView = LayoutInflater.from(context).inflate(R.layout.widget_icontext, this);

		mTextView = (TextView) rootView.findViewById(R.id.mTextView);
		mImageView = (ImageView) rootView.findViewById(R.id.mImageView);
		mRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.mRelativeLayout);

		HashSet<Target> targets = new HashSet<>();
		final float density = getContext().getResources().getDisplayMetrics().density;
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconText);
		text = typedArray.getString(R.styleable.IconText_android_text);
		textSize = typedArray.getDimensionPixelOffset(
			R.styleable.IconText_IT_text_size,
			(int) (DEFAULT_TEXT_SIZE * density));
		textSelectedColor = typedArray.getColor(R.styleable.IconText_IT_text_selected_color,
			DEFAULT_TEXT_COLOR);
		textUnselectedColor = typedArray.getColor(R.styleable.IconText_IT_text_unselected_color,
			DEFAULT_TEXT_COLOR);

		selected = typedArray.getBoolean(R.styleable.IconText_IT_selected, false);
		selectedColor = typedArray.getColor(R.styleable.IconText_IT_selected_color,
			DEFAULT_SELECTED_COLOR);
		unselectedColor = typedArray.getColor(R.styleable.IconText_IT_unselected_color,
			DEFAULT_UNSELECTED_COLOR);

		padding = typedArray.getDimensionPixelSize(R.styleable.IconText_IT_padding, 10);
//		mIconHeight = typedArray.getDimensionPixelSize(R.styleable.IconText_IT_icon_height, 80);
		selectedIcon = typedArray.getDrawable(R.styleable.IconText_IT_selected_icon);
		unselectedIcon = typedArray.getDrawable(R.styleable.IconText_IT_unselected_icon);

		mTextView.setText(text);
		mTextView.setTextSize(textSize);
		mRelativeLayout.setPadding(padding, padding, padding, padding);

		setSelected(selected);

//		LayoutParams params = (LayoutParams) mImageView.getLayoutParams();
//		params.height=mIconHeight;
//		mImageView.setLayoutParams(params);


		typedArray.recycle();
	}

	public void setText(String string)
	{
		mTextView.setText(string);
	}

	public void setTextColor(int color)
	{
		mTextView.setTextColor(color);
	}

	public void setTextSize(float size)
	{
		mTextView.setTextSize(size);
	}

	public void setPadding(int padding)
	{
		mImageView.setPadding(0, 0, 0, padding);
	}

	public void setIcon(int res)
	{
		mImageView.setImageResource(res);
	}

	public boolean getSelected()
	{
		return selected;
	}

	public void toggle(boolean doAnim)
	{
		boolean flag = !selected;
		setSelected(flag);
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
		if (selected)
		{
			if (selectedIcon != null)
			{
				mImageView.setImageDrawable(selectedIcon);
			}
			ViewAnimator.animate(mTextView).textColor(textUnselectedColor, textSelectedColor)
				.andAnimate(mRelativeLayout).backgroundColor(unselectedColor, selectedColor)
				.andAnimate(mImageView).scale(1.0f, 1.2f)
				.duration(400).start();
		}
		else
		{
			if (unselectedIcon != null)
			{
				mImageView.setImageDrawable(unselectedIcon);
			}
			ViewAnimator.animate(mTextView).textColor(textUnselectedColor, textUnselectedColor)
				.andAnimate(mRelativeLayout).backgroundColor(unselectedColor, unselectedColor)
				.andAnimate(mImageView).scale(1.0f, 1.0f)
				.duration(400).start();
		}
	}

	public void animIt()
	{
		ViewAnimator.animate(mTextView)
			.textColor(textSelectedColor, textUnselectedColor, textSelectedColor)
			.andAnimate(mRelativeLayout)
			.backgroundColor(selectedColor, unselectedColor, selectedColor)
			.andAnimate(mImageView).scale(1.0f, 1.2f, 1.0f)
			.duration(400).start();
	}
}
