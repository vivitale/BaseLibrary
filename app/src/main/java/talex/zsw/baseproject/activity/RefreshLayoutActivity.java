package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.RefreshLayout;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.Vo;
import talex.zsw.baseproject.adapter.CommonAdapter;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-16-0016 11:43 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RefreshLayoutActivity extends BaseAppCompatActivity
	implements SwipeRefreshLayout.OnRefreshListener, RefreshLayout.OnLoadListener
{
	@Bind(R.id.mListView) ListView mListView;
	@Bind(R.id.mRefreshLayout) RefreshLayout mRefreshLayout;

	private CommonAdapter adapter;
	private int x = 0;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_refreshlayout);
		ButterKnife.bind(this);

		adapter = new CommonAdapter(RefreshLayoutActivity.this);
		mRefreshLayout.setOnLoadListener(this);
		mRefreshLayout.setOnRefreshListener(this);
		mRefreshLayout.setChildView(mListView);
		mListView.setAdapter(adapter);
	}

	@Override protected void initData()
	{
		adapter.setDatas(getData(10));
	}

	@Override public void onRefresh()
	{
		showToast("下拉刷新");
		new Handler().postDelayed(new Runnable()
		{
			@Override public void run()
			{
				adapter.addNewDatas(getData(3));
				mRefreshLayout.setRefreshing(false);
			}
		}, 2000);
	}


	@Override public void onLoad()
	{
		showToast("上拉加载");
		new Handler().postDelayed(new Runnable()
		{
			@Override public void run()
			{
				adapter.addMoreDatas(getData(3));
				mRefreshLayout.setLoading(false);
			}
		}, 2000);
	}

	private List<Vo> getData(int count)
	{
		List<Vo> datas = new ArrayList<>();
		for (int i = 0; i < count; i++)
		{
			Vo vo = new Vo("标题" + x, "内容" + x, RefreshLayoutActivity.class);
			datas.add(vo);
			x++;
		}
		return datas;
	}
}
