package talex.zsw.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import talex.zsw.baselibrary.R;


public class CustomNumberPick extends RelativeLayout
{

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private TextView mTvreduce, mTvadd, mEt_num;

	public int mCount = 1, maxNum = 99, minNum = 1, stepNum = 1;

	private float mWidth, mHeight, mEditWidth;
	private Drawable addDrawable, reduceDrawable;

	public int getMaxNum()
	{
		return maxNum;
	}

	public void setMaxNum(int maxNum)
	{
		this.maxNum = maxNum;
		setButtonAble();
	}

	public int getMinNum()
	{
		return minNum;
	}

	public void setMinNum(int minNum)
	{
		this.minNum = minNum;
		setButtonAble();
	}

	public int getStepNum()
	{
		return stepNum;
	}

	public void setStepNum(int stepNum)
	{
		this.stepNum = stepNum;
	}

	public int getCount()
	{
		return mCount;
	}

	public void setCount(int count)
	{
		this.mCount = count;
		mEt_num.setText( String.valueOf( mCount ) );
		setButtonAble();
	}

	public void setAddClickListener(OnClickListener ock)
	{
		mTvadd.setOnClickListener( ock );
	}

	public void setEtOck(OnClickListener ock)
	{
		mEt_num.setOnClickListener( ock );
	}

	public void setOnClickListener(OnClickListener ock)
	{
		mTvadd.setOnClickListener( ock );
		mTvreduce.setOnClickListener( ock );
	}

	public void setrduceClickListener(OnClickListener ock)
	{
		mTvreduce.setOnClickListener( ock );
	}

	/**
	 * 数量选择 控件
	 *
	 * @param context
	 * @param attrs
	 */
	public CustomNumberPick(Context context, AttributeSet attrs)
	{
		super( context, attrs );
		mContext = context;
		initView( R.layout.widget_customnumpick, attrs );
	}

	private void initView(int menuViewID, AttributeSet attrs)
	{
		float density = mContext.getResources().getDisplayMetrics().density;// 获取屏幕密度
		TypedArray typedArray =
			mContext.obtainStyledAttributes( attrs, R.styleable.CustomNumberPick );
		mEditWidth =
			typedArray.getDimension( R.styleable.CustomNumberPick_CNP_EditWidth, density * 50 );
		mWidth = typedArray.getDimension( R.styleable.CustomNumberPick_CNP_Width, density * 35 );
		mHeight = typedArray.getDimension( R.styleable.CustomNumberPick_CNP_Height, density * 35 );
		addDrawable = typedArray.getDrawable( R.styleable.CustomNumberPick_CNP_AddDrawable );
		reduceDrawable = typedArray.getDrawable( R.styleable.CustomNumberPick_CNP_ReduceDrawable );

		mLayoutInflater = LayoutInflater.from( mContext );
		final View sub_view = mLayoutInflater.inflate( menuViewID, this );
		mTvreduce = (TextView) sub_view.findViewById( R.id.tvreduce );
		mTvadd = (TextView) sub_view.findViewById( R.id.tvadd );
		mEt_num = (TextView) sub_view.findViewById( R.id.et_num );
		mEt_num.setText( String.valueOf( mCount ) );
		mTvadd.setOnClickListener( mOclickListener );
		mTvreduce.setOnClickListener( mOclickListener );
		ViewGroup.LayoutParams params = mTvadd.getLayoutParams();
		params.height = (int) mHeight;
		params.width = (int) mWidth;
		mTvadd.setLayoutParams( params );
		if(addDrawable != null)
		{
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
			{
				mTvadd.setBackground( addDrawable );
			}
			else
			{
				mTvadd.setBackgroundDrawable( addDrawable );
			}
		}
		params = mTvreduce.getLayoutParams();
		params.height = (int) mHeight;
		params.width = (int) mWidth;
		mTvreduce.setLayoutParams( params );
		if(reduceDrawable != null)
		{
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
			{
				mTvreduce.setBackground( reduceDrawable );
			}
			else
			{
				mTvreduce.setBackgroundDrawable( reduceDrawable );
			}
		}
		params = mEt_num.getLayoutParams();
		params.height = (int) mHeight;
		params.width = (int) mEditWidth;
		mEt_num.setLayoutParams( params );

		setButtonAble();
	}

	/**
	 * 添加数量
	 */
	public void addCount()
	{
		if(mTvadd.isEnabled())
		{
			changeEtnum( stepNum );
		}
	}

	/**
	 * 减少数量
	 */
	public void reduceCount()
	{
		if(mTvreduce.isEnabled())
		{
			changeEtnum( -stepNum );
		}
	}

	public void setEnabled(boolean flag)
	{
		mTvreduce.setEnabled( flag );
		mTvadd.setEnabled( flag );
	}

	/**
	 * 该表数量
	 *
	 * @param changValue 需要改变数量的值
	 */
	private void changeEtnum(int changValue)
	{
		setCount( mCount + changValue );
	}

	/**
	 * 设置改变数量按钮可否点击
	 */
	private void setButtonAble()
	{
		if(mCount <= minNum)
		{
			mTvreduce.setEnabled( false );
			mTvadd.setEnabled( true );
		}
		else if(mCount >= maxNum)
		{
			mTvadd.setEnabled( false );
			mTvreduce.setEnabled( true );
		}
		else
		{
			mTvreduce.setEnabled( true );
			mTvadd.setEnabled( true );
		}
	}

	private OnClickListener mOclickListener = new OnClickListener()
	{

		@Override public void onClick(View v)
		{
			int i = v.getId();
			if(i == R.id.tvreduce)
			{
				reduceCount();
			}
			else if(i == R.id.tvadd)
			{
				addCount();
			}
			else
			{
			}
		}
	};
}
