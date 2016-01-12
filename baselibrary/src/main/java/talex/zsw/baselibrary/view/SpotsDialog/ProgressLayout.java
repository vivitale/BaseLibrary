package talex.zsw.baselibrary.view.SpotsDialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import talex.zsw.baselibrary.R;

/**
 * Created by Maxim Dybarsky | maxim.dybarskyy@gmail.com
 * on 13.01.15 at 17:34
 */
public class ProgressLayout extends FrameLayout
{

	private static final int DEFAULT_COUNT = 5;
	private int spotsCount, spotsDrawable;

	public ProgressLayout(Context context)
	{
		this( context, null );
	}

	public ProgressLayout(Context context, AttributeSet attrs)
	{
		this( context, attrs, 0 );
	}

	public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super( context, attrs, defStyleAttr );
		init( attrs, defStyleAttr, 0 );
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super( context, attrs, defStyleAttr, defStyleRes );
		init( attrs, defStyleAttr, defStyleRes );
	}

	private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		TypedArray a = getContext().getTheme()
			.obtainStyledAttributes( attrs, R.styleable.SpotsDialog, defStyleAttr, defStyleRes );

		spotsCount = a.getInt( R.styleable.SpotsDialog_SD_SpotCount, DEFAULT_COUNT );
		spotsDrawable =
			a.getResourceId( R.styleable.SpotsDialog_SD_Drawable, R.drawable.dmax_spots_spot );
		a.recycle();
	}

	public int getSpotsCount()
	{
		return spotsCount;
	}
	public int getSpotsDrawable()
	{
		return spotsDrawable;
	}
}
