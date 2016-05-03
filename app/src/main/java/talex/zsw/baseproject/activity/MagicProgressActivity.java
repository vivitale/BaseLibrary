package talex.zsw.baseproject.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.Random;

import talex.zsw.baselibrary.view.MagicProgress.AnimTextView;
import talex.zsw.baselibrary.view.MagicProgress.ISmoothTarget;
import talex.zsw.baselibrary.view.MagicProgress.MagicProgressBar;
import talex.zsw.baselibrary.view.MagicProgress.MagicProgressCircle;
import talex.zsw.baseproject.R;

/**
 * 项目包名: talex.zsw.baseproject.activity
 * 作用: 渐变色圆环进度条和彩色进度条
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/4/21 17:32 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MagicProgressActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_magicprogress);
		assignViews();

		anim();
	}

	private boolean isAnimActive;
	private final Random random = new Random();

	private void anim()
	{
		final int ceil = 26;
		final int progress = random.nextInt(ceil);

		AnimatorSet set = new AnimatorSet();
		set.playTogether(
			ObjectAnimator.ofFloat(demoMpc, "percent", 0, progress / 100f),
			ObjectAnimator.ofInt(demoTv, "progress", 0, progress),
			ObjectAnimator.ofFloat(demo1Mpb, "percent", 0, random.nextInt(ceil) / 100f),
			ObjectAnimator.ofFloat(demo2Mpb, "percent", 0, random.nextInt(ceil) / 100f),
			ObjectAnimator.ofFloat(demo3Mpb, "percent", 0, random.nextInt(ceil) / 100f),
			ObjectAnimator.ofFloat(demo4Mpb, "percent", 0, random.nextInt(ceil) / 100f)
		);
		set.setDuration(600);
		set.addListener(new Animator.AnimatorListener()
		{
			@Override
			public void onAnimationStart(Animator animation)
			{
				isAnimActive = true;
			}

			@Override
			public void onAnimationEnd(Animator animation)
			{
				isAnimActive = false;
			}

			@Override
			public void onAnimationCancel(Animator animation)
			{

			}

			@Override
			public void onAnimationRepeat(Animator animation)
			{

			}
		});
		set.setInterpolator(new AccelerateInterpolator());
		set.start();
	}


	public void onReRandomPercent(final View view)
	{
		if (isAnimActive)
		{
			return;
		}
		anim();
	}

	public void onClickIncreaseSmoothly(final View view)
	{
		if (isAnimActive)
		{
			return;
		}

		float mpcPercent = getIncreasedPercent(demoMpc);
		demoMpc.setSmoothPercent(mpcPercent);
		demoTv.setSmoothPercent(mpcPercent);
		demo1Mpb.setSmoothPercent(getIncreasedPercent(demo1Mpb));
		demo2Mpb.setSmoothPercent(getIncreasedPercent(demo2Mpb));
		demo3Mpb.setSmoothPercent(getIncreasedPercent(demo3Mpb));
		demo4Mpb.setSmoothPercent(getIncreasedPercent(demo4Mpb));
	}

	private float getIncreasedPercent(ISmoothTarget target)
	{
		float increasedPercent = target.getPercent() + 0.1f;

		return Math.min(1, increasedPercent);
	}

	private MagicProgressCircle demoMpc;
	private AnimTextView demoTv;
	private MagicProgressBar demo1Mpb;
	private MagicProgressBar demo2Mpb;
	private MagicProgressBar demo3Mpb;
	private MagicProgressBar demo4Mpb;

	private void assignViews()
	{
		demoMpc = (MagicProgressCircle) findViewById(R.id.demo_mpc);
		demoTv = (AnimTextView) findViewById(R.id.demo_tv);
		demo1Mpb = (MagicProgressBar) findViewById(R.id.demo_1_mpb);
		demo2Mpb = (MagicProgressBar) findViewById(R.id.demo_2_mpb);
		demo3Mpb = (MagicProgressBar) findViewById(R.id.demo_3_mpb);
		demo4Mpb = (MagicProgressBar) findViewById(R.id.demo_4_mpb);
	}
}
