package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-16-0016 10:02 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LoadingDialogActivity extends BaseAppCompatActivity
{
	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_empty);
	}

	@Override protected void initData()
	{
		showDialog();
		new Handler().postDelayed(new Runnable()
		{
			@Override public void run()
			{
				disDialog();
				finish();
			}
		}, 3000);
	}
}
