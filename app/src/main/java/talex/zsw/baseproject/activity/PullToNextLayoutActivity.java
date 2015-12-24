package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 下拉到下一个Fragment
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-23-0023 16:10 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PullToNextLayoutActivity extends BaseAppCompatActivity
{
	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_pulltonext);
	}

	@Override protected void initData()
	{

	}

	public void onClick(View view)
	{
		startActivity(new Intent(this, PTNScrollViewActivity.class));
	}

	public void onClick2(View view)
	{
		startActivity(new Intent(this, PTNWebViewActivity.class));
	}

	public void onClick3(View view)
	{
		startActivity(new Intent(this, PTNOtherActivity.class));
	}
}
