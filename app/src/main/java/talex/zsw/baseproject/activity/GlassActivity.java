package talex.zsw.baseproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ms.square.android.glassview.GlassView;

import talex.zsw.baseproject.R;


public class GlassActivity extends ActionBarActivity
{
	private LinearLayout mLinearLayout;
	private GlassView mTopGlassView;
	private GlassView mBottomGlassView;
	private TextView mTvText;
	private SeekBar mSeekBar;

	private Handler handler;
	private int i = 0, x = 0;
	private int height = 0;
	private ViewGroup.LayoutParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glass);

		mTopGlassView = (GlassView) findViewById(R.id.top_glass_view);
		mBottomGlassView = (GlassView) findViewById(R.id.bottom_glass_view);
		mTvText = (TextView) findViewById(R.id.mTvText);
		mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
		mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);

		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				// allow blur radius is 0 < r <= 25
				if (progress > 0)
				{
					mTopGlassView.setBlurRadius(progress);
					mBottomGlassView.setBlurRadius(progress);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
			}
		});

		handler = new Handler()
		{
			@Override public void handleMessage(Message msg)
			{
				super.handleMessage(msg);
				if (msg.what < height)
				{
					params.height = msg.what;
					mBottomGlassView.setLayoutParams(params);
					i = msg.what + x;
					handler.sendEmptyMessageDelayed(i, 10);
				}
				else
				{
					params.height = height;
					mBottomGlassView.setLayoutParams(params);
				}
			}
		};

		mTvText.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				i = 10;
				height = mLinearLayout.getHeight() - mBottomGlassView.getTop()-100;
				x = height / 50;

				params = mBottomGlassView.getLayoutParams();
				handler.sendEmptyMessageDelayed(i, 10);
			}
		});
	}
}
