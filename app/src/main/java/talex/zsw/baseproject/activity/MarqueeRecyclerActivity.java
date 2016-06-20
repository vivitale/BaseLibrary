package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.RecyleView.DividerItemDecoration;
import talex.zsw.baselibrary.widget.MarqueeRecyclerView;
import talex.zsw.baseproject.R;

/**
 * 项目包名: talex.zsw.baseproject.activity
 * 作用: 跑马灯列表
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/6/20 15:54 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MarqueeRecyclerActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mMarqueeRecyclerView) MarqueeRecyclerView mMarqueeRecyclerView;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_marquee_recycler);
		ButterKnife.bind(this);

		mMarqueeRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mMarqueeRecyclerView.addItemDecoration(
			new DividerItemDecoration(DividerItemDecoration.HORIZONTAL_LIST, Color.TRANSPARENT));
		mMarqueeRecyclerView
			.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
	}

	@Override protected void initData()
	{
		List<String> list = new ArrayList<>();
		for (int i = 1; i < 10; i++)
		{
			list.add("测试数据" + i);
		}
		mMarqueeRecyclerView
			.setAdapter(new MarqueeRecyclerView.InnerAdapter(list, MarqueeRecyclerActivity.this));
	}
}
