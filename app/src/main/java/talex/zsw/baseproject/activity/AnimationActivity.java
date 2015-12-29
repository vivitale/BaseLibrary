package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.Animation.AnimationListener;
import talex.zsw.baselibrary.view.Animation.ViewAnimator;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/25 23:00 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AnimationActivity extends BaseAppCompatActivity implements View.OnClickListener
{
	@Bind(R.id.mountain) ImageView mountain;
	@Bind(R.id.percent) TextView percent;
	@Bind(R.id.image) ImageView image;
	@Bind(R.id.text) TextView text;
	@Bind(R.id.parallel) Button parallel;
	@Bind(R.id.sequentially) Button sequentially;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_animation);
		ButterKnife.bind(this);

		parallel.setOnClickListener(this);
		mountain.setOnClickListener(this);
		sequentially.setOnClickListener(this);
	}

	@Override protected void initData()
	{

	}

	@Override public void onClick(View v)
	{
		if (v == parallel)
		{
			animateParallel();
		}
		else if (v == mountain)
		{
			simpleAnimation();
		}
		else if (v == sequentially)
		{
			animateSequentially();
		}
	}

	protected void simpleAnimation()
	{
		ViewAnimator
			.animate(mountain)
			.translationY(-1000, 0)
			.alpha(0, 1)
			.andAnimate(text)
			.translationX(-200, 0)
			.descelerate()
			.duration(2000)

			.thenAnimate(mountain)
			.scale(1f, 0.5f, 1f)
			.accelerate()
			.duration(1000)

			.start();
	}

	protected void animateParallel()
	{
		ViewAnimator
			.animate(mountain, image)
			.dp().translationY(-1000, 0)
			.alpha(0, 1)

			.andAnimate(percent)
			.scale(0, 1)

			.andAnimate(text)
			.dp().translationY(1000, 0)
			.textColor(Color.BLACK, Color.WHITE)
			.backgroundColor(Color.WHITE, Color.BLACK)

			.waitForHeight()
			.descelerate()
			.duration(2000)

			.thenAnimate(percent)
			.custom(new AnimationListener.Update<TextView>()
			{
				@Override public void update(TextView view, float value)
				{
					view.setText(String.format("%.02f%%", value));
				}
			}, 0, 1)

			.andAnimate(image)
			.rotation(0, 360)

			.duration(5000)

			.start();
	}

	protected void animateSequentially()
	{
		ViewAnimator
			.animate(image)
			.dp().width(100f, 150f)
			.alpha(1, 0.1f)
			.descelerate()
			.duration(800)
			.thenAnimate(image)
			.dp().width(150f, 100f)
			.alpha(0.1f, 1f)
			.accelerate()
			.duration(1200)
			.start();
	}
}
