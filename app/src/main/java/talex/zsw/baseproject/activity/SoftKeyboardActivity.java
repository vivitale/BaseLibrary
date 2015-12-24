package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-16-0016 18:13 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SoftKeyboardActivity extends BaseAppCompatActivity
{
	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_softkeyboardlistenerlayout);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		listenerSoftInput();
	}

	private void listenerSoftInput()
	{
		// View为FrameLayout同时设置  android:windowSoftInputMode="stateAlwaysHidden|adjustResize"
		final View activityRootView = findViewById(R.id.layout);
		activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(
			new ViewTreeObserver.OnGlobalLayoutListener()
			{
				@Override public void onGlobalLayout()
				{
					int heightDiff =
						activityRootView.getRootView().getHeight() - activityRootView.getHeight();
					// 如果高度差超过100像素，就很有可能是有软键盘...
					if (heightDiff > 250)
					{
						showToast("键盘弹出");
					}
					else
					{// 软键盘关闭
						showToast("键盘关闭");
					}
				}
			});
	}
}
