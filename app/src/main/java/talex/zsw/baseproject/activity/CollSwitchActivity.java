package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;

import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.CoolSwitch.CoolSwitch;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 酷炫的开关按钮，选中与否可以改变布局
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/1/6 11:13 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CollSwitchActivity extends BaseAppCompatActivity implements CoolSwitch.AnimationListener
{

	private CoolSwitch coolSwitch;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView( R.layout.activity_collswitch );

		coolSwitch = (CoolSwitch) findViewById( R.id.cool_switch_foo );
		coolSwitch.addAnimationListener( this );
	}

	@Override protected void initData()
	{

	}

	@Override public void onCheckedAnimationFinished()
	{
		showToast( "onCheckedAnimationFinished" );
	}

	@Override public void onUncheckedAnimationFinished()
	{

		showToast( "onUncheckedAnimationFinished" );
	}

	@Override protected void onDestroy()
	{
		coolSwitch.removeAnimationListener( this );

		super.onDestroy();
	}
}
