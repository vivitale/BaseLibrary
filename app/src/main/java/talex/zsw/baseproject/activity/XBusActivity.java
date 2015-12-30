package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.xbus.Bus;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.fragment.xBusBottomFragment;
import talex.zsw.baseproject.fragment.xBusTopFragment;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/29 15:14 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class XBusActivity extends BaseAppCompatActivity
{
	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_xbus);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.demo_rxbus_frag_1,
				new xBusTopFragment()).replace(R.id.demo_rxbus_frag_2,
			new xBusBottomFragment())
			.commit();
	}

	@Override protected void onDestroy()
	{
		super.onDestroy();
		Bus.getDefault().unregister(this);
	}
}
