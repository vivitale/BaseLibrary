package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.util.KeyboardWatcher;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 键盘监听类
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/3/24 17:28 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class KeyboardWatcherActivity extends BaseAppCompatActivity
	implements KeyboardWatcher.OnKeyboardToggleListener
{
	@Bind(R.id.editText) EditText editText;
	private KeyboardWatcher keyboardWatcher;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView( R.layout.activity_keyboardwatcher );
		ButterKnife.bind( this );
	}

	@Override protected void initData()
	{
		keyboardWatcher = KeyboardWatcher.initWith( this ).bindKeyboardWatcher( this );
	}

	@Override protected void onDestroy()
	{
		keyboardWatcher.unbindKeyboardWatcher();
		super.onDestroy();
	}

	@Override public void onKeyboardShown(int keyboardSize)
	{
		showToast( "onKeyboardShown" );
	}

	@Override public void onKeyboardClosed()
	{
		showToast( "onKeyboardClosed" );
	}
}
