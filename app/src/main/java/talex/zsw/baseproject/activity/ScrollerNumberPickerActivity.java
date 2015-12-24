package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.ScrollerNumberPicker;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 滑动选择器 
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/11/24 16:40 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ScrollerNumberPickerActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mPicker1) ScrollerNumberPicker mPicker1;
	@Bind(R.id.mPicker2) ScrollerNumberPicker mPicker2;
	@Bind(R.id.mPicker3) ScrollerNumberPicker mPicker3;
	@Bind(R.id.mTextView) TextView mTextView;

	private List<String> strings1 = new ArrayList<>();
	private List<String> strings2 = new ArrayList<>();
	private List<String> strings3 = new ArrayList<>();

	private Handler mHandler;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_scrollernumberpicker);
		ButterKnife.bind(this);

		for (int i = 1; i < 25; i++)
		{
			strings1.add("滑动器一 | " + i);
			strings2.add("滑动器二 | " + i);
			strings3.add("滑动器三 | " + i);
		}
		mPicker1.setData(strings1); // 设置数据
		mPicker2.setData(strings2); // 设置数据
		mPicker3.setData(strings3); // 设置数据
		mPicker1.setDefault(8);    // 设置默认选中项目
		mPicker1.setDefault(12);    // 设置默认选中项目
		mPicker1.setDefault(16);    // 设置默认选中项目
	}

	@Override protected void initData()
	{
		mHandler = new Handler()
		{
			@Override public void handleMessage(Message msg)
			{
				super.handleMessage(msg);
				mTextView.setText("mPicker1选中的是" + mPicker1.getSelectedText());
			}
		};
		mPicker1.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener()
		{
			@Override
			public void selecting(int id, String text)
			{

			}

			@Override
			public void endSelect(int id, String text)
			{
				mHandler.sendEmptyMessage(0);
			}
		});
	}
}
