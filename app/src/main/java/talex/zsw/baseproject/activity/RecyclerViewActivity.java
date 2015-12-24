package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.RecyleView.FullyGridLayoutManager;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.adapter.RecyclerViewAdapter;

/**
 * 项目名称: BaseProject
 * 作用: RecyclerView 的使用方法
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/7 17:54 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RecyclerViewActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mRecyclerView) RecyclerView mRecyclerView;

	private RecyclerViewAdapter adapter;
	private List<String> datas = new ArrayList<>();

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_recyclerview);
		ButterKnife.bind(this);

		for (int i = 0; i < 20; i++)
		{
			datas.add("数据" + i);
		}

		adapter = new RecyclerViewAdapter(RecyclerViewActivity.this);
		adapter.setDatas(datas);
		mRecyclerView.setAdapter(adapter);
	}

	@Override protected void initData()
	{
//		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//		mRecyclerView.setLayoutManager(new FullyLinearLayoutManager(RecyclerViewActivity.this));
		//横向
//		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//		mRecyclerView
//			.addItemDecoration(new DividerItemDecoration(DividerItemDecoration.VERTICAL_LIST,
//				R.color.gray));
//		mRecyclerView.setLayoutManager(new FullyLinearLayoutManager(RecyclerViewActivity.this));
		//两排的Grid
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//		mRecyclerView
//			.addItemDecoration(new DividerGridItemDecoration(R.color.gray));
		mRecyclerView
			.setLayoutManager(new FullyGridLayoutManager(RecyclerViewActivity.this, 2));
		//瀑布流
//		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//		mRecyclerView
//			.addItemDecoration(new DividerGridItemDecoration(R.color.gray));
//		mRecyclerView.setLayoutManager(new FullyStaggeredGridLayoutManager(2,
//			StaggeredGridLayoutManager.VERTICAL));
//		adapter.setDatas(datas);
	}
}
