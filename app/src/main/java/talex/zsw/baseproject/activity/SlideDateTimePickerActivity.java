package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.SlideDateTimePicker.SlideDateTimeListener;
import talex.zsw.baselibrary.view.SlideDateTimePicker.SlideDateTimePicker;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 日期时间选择器
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/11/24 11:00 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SlideDateTimePickerActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mTextView) TextView mTextView;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_slidedatetimepicker);
		ButterKnife.bind(this);

		mTextView.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				new SlideDateTimePicker.Builder(getSupportFragmentManager())
					.setListener(listener)
					.setInitialDate(new Date())
						//.setMinDate(minDate)
						//.setMaxDate(maxDate)
					.setIs24HourTime(true)
					.setTheme(SlideDateTimePicker.HOLO_DARK)
					.setIndicatorColor(Color.parseColor("#990000"))
					.build()
					.show();
			}
		});
	}

	@Override protected void initData()
	{

	}

	private SlideDateTimeListener listener = new SlideDateTimeListener()
	{
		@Override
		public void onDateTimeSet(Date date)
		{
			showToast(date.toString());
		}

		@Override
		public void onDateTimeCancel()
		{
			showToast("Canceled");
		}
	};
}
