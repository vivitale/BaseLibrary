package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.CustomNumberPick;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-12-0012 10:44 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CustomNumberPickActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mCustomNumber) CustomNumberPick mCustomNumber;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_customnumberpick);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		mCustomNumber.setMinNum(1);
		mCustomNumber.setMaxNum(10);
	}
}
