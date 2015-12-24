package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.FlowLayout.FlowLayout;
import talex.zsw.baselibrary.view.FlowLayout.TagAdapter;
import talex.zsw.baselibrary.view.FlowLayout.TagFlowLayout;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 单选多选标签
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-23-0023 15:14 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class FlowLayoutActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mFlowLayout) TagFlowLayout mFlowLayout;
	@Bind(R.id.mTextView) TextView mTextView;

	private String[] mVals =
		new String[]{"全部", "亲子", "灵山门票", "周边游", "热门活动", "拈花湾", "美食", "文化体验", "禅旅居"};

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_flowlayout);
		ButterKnife.bind(this);

		final LayoutInflater mInflater = LayoutInflater.from(FlowLayoutActivity.this);
		TagAdapter mAdapter = new TagAdapter<String>(mVals)
		{
			@Override public View getView(FlowLayout parent, int position, String s)
			{
				TextView tv =
					(TextView) mInflater.inflate(R.layout.flowlayout_text, mFlowLayout, false);
				tv.setText(s);
				return tv;
			}
		};
		mFlowLayout.setAdapter(mAdapter);
		mFlowLayout.setMaxSelectCount(5);//设置最大选中数目
		mAdapter.setSelectedList(1, 3, 5);//设置默认选中
		mTextView.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				showSnackbar(mFlowLayout.getSelectedList().toString());
			}
		});
	}

	@Override protected void initData()
	{

	}
}
