package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.IconText;
import talex.zsw.baseproject.R;

/**
 * 项目包名: talex.zsw.baseproject.activity
 * 作用: 测试用
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/4/6 16:08 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TestActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mIconText) IconText mIconText;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_test);
		ButterKnife.bind(this);

		mIconText.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				mIconText.toggle(true);
			}
		});
	}

	@Override protected void initData()
	{

	}
}
