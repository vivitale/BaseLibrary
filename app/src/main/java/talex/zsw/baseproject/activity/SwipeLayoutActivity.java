package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.SwipeLayout.SwipeLayout;
import talex.zsw.baselibrary.view.SwipeLayout.util.Attributes;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.adapter.SwipeAdapter;

/**
 * 项目名称: BaseProject
 * 作用: SwipeLayout,上下左右滑动显示更多项目
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/3 17:39 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SwipeLayoutActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mSwipeLayout) SwipeLayout mSwipeLayout;
	@Bind(R.id.bottom_left) LinearLayout bottomLeft;
	@Bind(R.id.archive) TextView archive;
	@Bind(R.id.delete) TextView delete;
	@Bind(R.id.bottom_right) LinearLayout bottomRight;
	@Bind(R.id.magnifier2) ImageView magnifier2;
	@Bind(R.id.star2) ImageView star2;
	@Bind(R.id.trash2) ImageView trash2;
	@Bind(R.id.bottom_wrapper_child1) RelativeLayout bottomWrapperChild1;
	@Bind(R.id.star) ImageView star;
	@Bind(R.id.bottom_top) LinearLayout bottomTop;

	@Bind(R.id.mListView) ListView mListView;
	private SwipeAdapter adapter;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_swipelayout);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		initTop();

		initList();
	}

	private void initTop()
	{
		mSwipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);//设置滑动效果
		mSwipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(SwipeLayoutActivity.this, "Click on surface", Toast.LENGTH_SHORT)
					.show();
				Log.d(SwipeLayoutActivity.class.getName(), "click on surface");
			}
		});
		mSwipeLayout.findViewById(R.id.star2).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(SwipeLayoutActivity.this, "Star", Toast.LENGTH_SHORT).show();
			}
		});

		mSwipeLayout.findViewById(R.id.trash2).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(SwipeLayoutActivity.this, "Trash Bin", Toast.LENGTH_SHORT).show();
			}
		});

		mSwipeLayout.findViewById(R.id.magnifier2).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(SwipeLayoutActivity.this, "Magnifier", Toast.LENGTH_SHORT).show();
			}
		});
		archive.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(SwipeLayoutActivity.this, "archive", Toast.LENGTH_SHORT).show();
			}
		});
		delete.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(SwipeLayoutActivity.this, "delete", Toast.LENGTH_SHORT).show();
			}
		});

		mSwipeLayout.addSwipeListener(new SwipeLayout.SwipeListener()
		{
			@Override public void onStartOpen(SwipeLayout layout)
			{

			}

			@Override public void onOpen(SwipeLayout layout)
			{

			}

			@Override public void onStartClose(SwipeLayout layout)
			{

			}

			@Override public void onClose(SwipeLayout layout)
			{

			}

			@Override public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset)
			{

			}

			@Override public void onHandRelease(SwipeLayout layout, float xvel, float yvel)
			{

			}
		});
	}

	private void initList()
	{
		adapter = new SwipeAdapter(SwipeLayoutActivity.this);
		mListView.setAdapter(adapter);
		adapter.setMode(Attributes.Mode.Single);//设置只能滑开一个项目
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				//				((SwipeLayout) (mListView
				//					.getChildAt(position - mListView.getFirstVisiblePosition()))).open(true);
				showToast("点击了第 " + position + "个");
			}
		});
	}
}
