package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.NiceSpinner.NiceSpinner;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 简易的Spinner
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-23-0023 15:41 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NiceSpinnerActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mNiceSpinner) NiceSpinner mNiceSpinner;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_nicespinner);
		ButterKnife.bind(this);

		ArrayList<String> dataset = new ArrayList<>(Arrays
			.asList("One", "Two", "Three", "Four", "Five"));
		mNiceSpinner.attachDataSource(dataset);
	}

	@Override protected void initData()
	{

	}
}
