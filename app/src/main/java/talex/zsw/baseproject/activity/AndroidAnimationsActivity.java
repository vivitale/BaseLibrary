package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.AndroidAnimations.library.Techniques;
import talex.zsw.baselibrary.view.AndroidAnimations.library.YoYo;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.adapter.EffectAdapter;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-19-0019 15:07 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AndroidAnimationsActivity extends BaseAppCompatActivity
{
	@Bind(R.id.hello_world) TextView mTarget;
	@Bind(R.id.list_items) ListView mListView;

	private EffectAdapter mAdapter;
	private YoYo.YoYoString rope;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_androidanimations);
		ButterKnife.bind(this);

		mAdapter = new EffectAdapter(this);
		mListView.setAdapter(mAdapter);

		rope = YoYo.with(Techniques.FadeIn).duration(1000)
			.playOn(mTarget);// after start,just click mTarget view, rope is not init
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Techniques technique = (Techniques) view.getTag();
				rope = YoYo.with(technique)
					.duration(1200)
					.interpolate(new AccelerateDecelerateInterpolator())
					.withListener(new Animator.AnimatorListener()
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
							showToast("canceled");
						}

						@Override
						public void onAnimationRepeat(Animator animation)
						{

						}
					})
					.playOn(mTarget);
			}
		});

		mTarget.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (rope != null)
				{
					rope.stop(true);
				}
			}
		});

	}

	@Override protected void initData()
	{
	}
}
