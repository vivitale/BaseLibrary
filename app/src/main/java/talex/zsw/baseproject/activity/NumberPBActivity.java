package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.NumberProgressBar.NumberProgressBar;
import talex.zsw.baselibrary.view.NumberProgressBar.OnProgressBarListener;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 带数字的ProgressBar
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/7 17:11 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NumberPBActivity extends BaseAppCompatActivity
{
	@Bind(R.id.numberbar1) NumberProgressBar numberbar1;
	@Bind(R.id.numberbar2) NumberProgressBar numberbar2;
	@Bind(R.id.numberbar3) NumberProgressBar numberbar3;
	@Bind(R.id.numberbar4) NumberProgressBar numberbar4;
	@Bind(R.id.numberbar5) NumberProgressBar numberbar5;
	@Bind(R.id.numberbar6) NumberProgressBar numberbar6;
	@Bind(R.id.numberbar7) NumberProgressBar numberbar7;
	@Bind(R.id.numberbar8) NumberProgressBar numberbar8;

	private Timer timer;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_numberprogressbar);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		numberbar1.setOnProgressBarListener(new OnProgressBarListener()
		{
			@Override public void onProgressChange(int current, int max)
			{
				if (current == max)
				{
					Toast.makeText(getApplicationContext(), "到底啦~", Toast.LENGTH_SHORT)
						.show();
					numberbar1.setProgress(0);
				}
			}
		});
		timer = new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						numberbar1.incrementProgressBy(1);
					}
				});
			}
		}, 1000, 100);
	}
}
