package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.adapter.AnimationAdapter;
import talex.zsw.baseproject.util.ContantValue;

/**
 * 项目名称: BaseProject
 * 作用: 复杂动画
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/8 11:17 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ComplexActivity extends BaseAppCompatActivity implements AdapterView.OnItemClickListener
{
	@Bind(R.id.mListView) ListView mListView;

	private AnimationAdapter adapter;
	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_simple);
		ButterKnife.bind(this);

		adapter = new AnimationAdapter(this, ContantValue.animName);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);
	}

	@Override protected void initData()
	{

	}

	@Override public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Animation anim = AnimationUtils.loadAnimation(ComplexActivity.this,
			ContantValue.simple[position]);
		mListView.startAnimation(anim);
	}
}
