package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.SwipyRefreshLayout.SwipyRefreshLayout;
import talex.zsw.baselibrary.view.SwipyRefreshLayout.SwipyRefreshLayoutDirection;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 下拉刷新上拉加载
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/11/24 14:38 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SwipyRefreshLayoutActivity extends BaseAppCompatActivity
	implements SwipyRefreshLayout.OnRefreshListener
{
	@Bind(R.id.mScrollView) ScrollView mScrollView;
	@Bind(R.id.mSwipyRefreshLayout) SwipyRefreshLayout mSwipyRefreshLayout;

	@Override protected void initArgs(Intent intent)
	{
	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_swipyrefreshlayout);
		ButterKnife.bind(this);

		mSwipyRefreshLayout.setOnRefreshListener(this);
	}

	@Override protected void initData()
	{

	}

	@Override public void onRefresh(SwipyRefreshLayoutDirection direction)
	{
		if (direction == SwipyRefreshLayoutDirection.TOP)
		{
			showToast("正在更新");
		}
		else
		{
			showToast("正在加载");
		}
		new Handler().postDelayed(new Runnable()
		{
			@Override public void run()
			{
				mSwipyRefreshLayout.setRefreshing(false);
			}
		}, 2000);
	}
}
