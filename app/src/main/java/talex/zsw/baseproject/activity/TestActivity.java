package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.RecyleView.DividerItemDecoration;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.OnLoadMoreListener;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.OnRefreshListener;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeToLoadLayout;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.adapter.TestAdapter;
import talex.zsw.baseproject.test.TestData;

/**
 * 项目包名: talex.zsw.baseproject.activity
 * 作用: 测试用
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/4/6 16:08 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TestActivity extends BaseAppCompatActivity
	implements OnRefreshListener, OnLoadMoreListener
{
	@Bind(R.id.swipe_target) RecyclerView mRecyclerView;
	@Bind(R.id.mSwipeToLoadLayout) SwipeToLoadLayout mSwipeToLoadLayout;

	private TestAdapter adapter;
	private int i;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_test);
		ButterKnife.bind(this);

		adapter = new TestAdapter(TestActivity.this);
//    adapter.setOnItemChildClickListener(this);
		mRecyclerView.setAdapter(adapter);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.addItemDecoration(
			new DividerItemDecoration(DividerItemDecoration.VERTICAL_LIST,
				Color.rgb(231, 231, 231)));
		mRecyclerView.setLayoutManager(new LinearLayoutManager(TestActivity.this));

		mSwipeToLoadLayout.setOnRefreshListener(this);
		mSwipeToLoadLayout.setOnLoadMoreListener(this);
	}

	@Override protected void initData()
	{
		mSwipeToLoadLayout.post(new Runnable()
		{
			@Override public void run()
			{
				mSwipeToLoadLayout.setRefreshing(true);
			}
		});
	}

	@Override public void onLoadMore()
	{

	}

	@Override public void onRefresh()
	{
		i++;
		if (i % 3 == 0)
		{
			adapter.setDatas(null);
		}
		else
		{
			adapter.setDatas(TestData.getData(20));
		}
		mSwipeToLoadLayout.setRefreshing(false);
	}
}
