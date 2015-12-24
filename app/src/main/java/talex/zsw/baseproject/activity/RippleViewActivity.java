package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.OnTapListener;
import talex.zsw.baselibrary.widget.RippleView;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.adapter.CustomAdapter;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-16-0016 13:32 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RippleViewActivity extends BaseAppCompatActivity
{
	@Bind(R.id.more) RippleView more;
	@Bind(R.id.more2) RippleView more2;
	@Bind(R.id.actionbar) RelativeLayout actionbar;
	@Bind(R.id.rect_child) TextView rectChild;
	@Bind(R.id.rect) RippleView rect;
	@Bind(R.id.recycler_view) RecyclerView recyclerView;

	private ArrayList<String> sourcesArrayList = new ArrayList<String>();

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_rippleview);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		rect.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Log.e("Sample", "点击之后立即执行！");
			}
		});
		rectChild.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Log.e("Sample", "点击之后会等动画效果结束之后才执行");
			}
		});

		sourcesArrayList.add("Samsung");
		sourcesArrayList.add("Android");
		sourcesArrayList.add("Google");
		sourcesArrayList.add("Asus");
		sourcesArrayList.add("Apple");
		sourcesArrayList.add("Samsung");
		sourcesArrayList.add("Android");
		sourcesArrayList.add("Google");
		sourcesArrayList.add("Asus");
		sourcesArrayList.add("Apple");
		sourcesArrayList.add("Samsung");
		sourcesArrayList.add("Android");
		sourcesArrayList.add("Google");
		sourcesArrayList.add("Asus");
		sourcesArrayList.add("Apple");

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		CustomAdapter customAdapter = new CustomAdapter();
		customAdapter.updateList(sourcesArrayList);

		customAdapter.setOnTapListener(new OnTapListener()
		{
			@Override
			public void onTapView(int position)
			{
				Log.e("MainActivity", "Tap item : " + position);
			}
		});
		recyclerView.setAdapter(customAdapter);
	}
}
