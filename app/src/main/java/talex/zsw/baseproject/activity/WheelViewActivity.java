package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.util.CalendarUtil;
import talex.zsw.baselibrary.view.WheelView.SelectDate;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 时间选择器
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/11/24 16:13 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class WheelViewActivity extends BaseAppCompatActivity implements View.OnClickListener
{
	@Bind(R.id.mTextView) TextView mTextView;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_slidedatetimepicker);
		ButterKnife.bind(this);

		mTextView.setOnClickListener(this);
	}

	@Override protected void initData()
	{

	}

	@Override public void onClick(View v)
	{
		SelectDate date = new SelectDate(WheelViewActivity.this,
			CalendarUtil.getDateTimeNow(CalendarUtil.STR_FOMATER_DATA));
		date.setTextView(mTextView);
		date.showAtLocation(mTextView, Gravity.BOTTOM,0,0);
	}
}
