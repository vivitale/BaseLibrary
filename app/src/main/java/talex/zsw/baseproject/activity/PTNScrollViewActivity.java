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
import talex.zsw.baseproject.fragment.PTNScrollViewFragment;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-23-0023 16:15 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PTNScrollViewActivity extends BaseAppCompatActivity
{
	@Bind(R.id.pullToNextLayout) PullToNextLayout pullToNextLayout;

	private ArrayList<Fragment> list;

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

		list.add(new PTNScrollViewFragment(0));
		list.add(new PTNScrollViewFragment(1));
		list.add(new PTNScrollViewFragment(2));
		list.add(new PTNScrollViewFragment(3));
		list.add(new PTNScrollViewFragment(4));
		list.add(new PTNScrollViewFragment(5));
		list.add(new PTNScrollViewFragment(6));
		list.add(new PTNScrollViewFragment(7));

		pullToNextLayout.setAdapter(new PullToNextFragmentAdapter(
			getSupportFragmentManager(), list));

		pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener()
		{
			@Override
			public void onSelectItem(int position, View view)
			{

				setTitle(position + 1 + ".0 谷歌仍是毕业生心目中的最佳雇主");
			}
		});
		setTitle(1 + ".0 谷歌仍是毕业生心目中的最佳雇主");
	}
}
