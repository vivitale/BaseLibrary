package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.SwitchAnimation.SwitchAnimationUtil;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.adapter.SwitchAnimationAdapter;

/**
 * 项目名称: BaseProject
 * 作用: 使View中的项目带有动画效果入场
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/11/24 14:57 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SwitchAnimationActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mListView) ListView mListView;
	private SwitchAnimationAdapter adapter;
	private SwitchAnimationUtil mSwitchAnimationUtil;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_switchanimation);
		ButterKnife.bind(this);

		adapter = new SwitchAnimationAdapter(SwitchAnimationActivity.this);
		mListView.setAdapter(adapter);
	}

	@Override protected void initData()
	{
		List<String> datas = new ArrayList<>();
		for (int i = 0; i < 25; i++)
		{
			datas.add("" + i);
		}
		adapter.setDatas(datas);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		if (mSwitchAnimationUtil == null && mApplication.isShow())
		{
			mSwitchAnimationUtil = new SwitchAnimationUtil();
			mSwitchAnimationUtil.startAnimation(mListView,
				SwitchAnimationUtil.AnimationType.HORIZON_CROSS);
		}
	}
}
