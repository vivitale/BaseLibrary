package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.PullToNextLayout.OnItemSelectListener;
import talex.zsw.baselibrary.view.PullToNextLayout.PullToNextLayout;
import talex.zsw.baselibrary.view.PullToNextLayout.adapter.PullToNextFragmentAdapter;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.fragment.PTNOtherFragment;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-23-0023 16:15 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PTNOtherActivity extends BaseAppCompatActivity
{
	@Bind(R.id.pullToNextLayout) PullToNextLayout pullToNextLayout;

	private ArrayList<Fragment> list;
	private String[] names = {"于文文", "张钧甯", "陈乔恩", "贾青"};

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_pulltonextlayout);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		list = new ArrayList<Fragment>();
		list.add(new PTNOtherFragment(0));
		list.add(new PTNOtherFragment(1));
		list.add(new PTNOtherFragment(2));
		list.add(new PTNOtherFragment(3));

		pullToNextLayout.setAdapter(new PullToNextFragmentAdapter(
			getSupportFragmentManager(), list));

		pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener()
		{
			@Override
			public void onSelectItem(int position, View view)
			{
				setTitle(names[position]);
			}
		});
		setTitle(names[0]);
	}
}
