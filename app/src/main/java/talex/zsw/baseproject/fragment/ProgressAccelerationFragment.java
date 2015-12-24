package talex.zsw.baseproject.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseFragment;
import talex.zsw.baselibrary.custom.progress.AccelerationProgress;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: AccelerationProgress的使用方法
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/24 14:14 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProgressAccelerationFragment extends BaseFragment
{
	@Bind(R.id.acc_progress_loading_complete) AccelerationProgress mAPComplete;
	@Bind(R.id.acc_progress_loading) AccelerationProgress mAPLoading;
	@Bind(R.id.acc_progress_loading_time) AccelerationProgress mAPTime;

	private static final int LOADING_MSG = 0x001;
	private static final int COMPLETED_MSG = 0x002;
	private static final int COUNTDOWN_MSG = 0x003;
	private static final int DELAY_TIME = 5 * 1000;

	private Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
				case LOADING_MSG:
					mAPComplete.startLoading();
					mHandler.sendEmptyMessageDelayed(COMPLETED_MSG, DELAY_TIME);
					break;

				case COMPLETED_MSG:
					mAPComplete.loadCompleted(true);
					mHandler.sendEmptyMessageDelayed(LOADING_MSG, DELAY_TIME);
					break;
				case COUNTDOWN_MSG:
					mAPTime.startLoading();
					break;
			}
		}
	};

	@Override protected void initArgs(Bundle bundle)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.fragment_acclerationprogress);
		ButterKnife.bind(this, mView);
	}

	@Override protected void initData()
	{

	}


	@Override
	public void onResume()
	{
		super.onResume();
		mAPComplete.startLoading();
		mAPLoading.startLoading();
		//10 seconds
		mAPTime.setCountDownTime(10);
		mAPTime.setCountdownCompleteListener(new AccelerationProgress.CountdownCompleteListener()
		{
			@Override
			public void countdownComplete()
			{
				mHandler.sendEmptyMessageDelayed(COUNTDOWN_MSG, 2 * 1000);
			}
		});
		mAPTime.startLoading();
		mHandler.sendEmptyMessageDelayed(COMPLETED_MSG, DELAY_TIME);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		mAPComplete.stopLoading();
		mAPLoading.stopLoading();
		mAPTime.stopLoading();
		mHandler.removeCallbacksAndMessages(null);
	}

	@Override public void onDestroyView()
	{
		super.onDestroyView();
		ButterKnife.unbind(this);
	}
}
