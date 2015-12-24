package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.ExpandableLayout.ExpandableLayout;
import talex.zsw.baselibrary.view.ExpandableLayout.ExpandableLayoutListView;
import talex.zsw.baselibrary.widget.OverScrollView;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.adapter.ExpandableAdapter;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-19-0019 16:20 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ExpandableActivity extends BaseAppCompatActivity
{
	private final String[] array = {"Hello", "World", "Android", "is",
		"Awesome", "World", "Android", "is", "Awesome", "World", "Android",
		"is", "Awesome", "World", "Android", "is", "Awesome"
	};
	@Bind(R.id.first) ExpandableLayout expandableLayout;
	@Bind(R.id.listview) ExpandableLayoutListView expandableLayoutListView;
	@Bind(R.id.mOverScrollView) OverScrollView mOverScrollView;
	@Bind(R.id.mImageView) ImageView mImageView;
	@Bind(R.id.header_text) TextView header_text;
	@Bind(R.id.button) Button button;
	@Bind(R.id.text) EditText text;

	private ExpandableAdapter adapter;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_expandablelayout);
		ButterKnife.bind(this);

		adapter = new ExpandableAdapter(ExpandableActivity.this);
		List<String> strings = new ArrayList<>();
		Collections.addAll(strings, array);
		adapter.setContentArray(strings, false);
		expandableLayoutListView.setAdapter(adapter);

		expandableLayout.setScrollView(mOverScrollView);
		expandableLayout.setImageView(mImageView, android.R.drawable.ic_lock_idle_lock,
			android.R.drawable.btn_dropdown);//设置打开关闭效果下的图片变化

		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(ExpandableActivity.this, "3333333333",
					Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override protected void initData()
	{

	}
}
