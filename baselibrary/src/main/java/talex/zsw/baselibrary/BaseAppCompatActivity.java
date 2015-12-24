package talex.zsw.baselibrary;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.List;

import talex.zsw.baselibrary.view.appmsg.AppMsg;
import talex.zsw.baselibrary.widget.LoadingDialog;

@SuppressWarnings("ALL") public abstract class BaseAppCompatActivity extends AppCompatActivity
{
	public static final int ZOOM = 0x10001;
	public static final int LEFT = 0x10002;
	public static final int RIGHT = 0x10003;
	public static final int UP_DOWN = 0x10004;

	protected BaseApplication mApplication;
	private boolean mViewInited = false;
	protected LoadingDialog loadingDialog;
	private InputMethodManager mInputMethodManager;

	protected abstract void initArgs(Intent intent);

	protected abstract void initView(Bundle bundle);

	protected abstract void initData();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mApplication = getAppApplication();
		mApplication.addActivity(this);
		mInputMethodManager = (InputMethodManager) this
			.getSystemService(Context.INPUT_METHOD_SERVICE);
		try
		{
			initArgs(getIntent());
			initView(savedInstanceState);
			initData();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override protected void onDestroy()
	{
		super.onDestroy();
		setContentView(R.layout.activity_empty);
	}

	public void showDialog()
	{
		if (null == loadingDialog)
		{
			loadingDialog = new LoadingDialog(this);
		}
		if (!loadingDialog.isShowing())
		{
			loadingDialog.show();
		}
	}

	public void disDialog()
	{
		if (loadingDialog != null && loadingDialog.isShowing())
		{
			loadingDialog.dismiss();
		}
	}

	protected BaseApplication getAppApplication()
	{
		if (null == mApplication)
		{
			mApplication = (BaseApplication) getApplication();
		}
		return mApplication;
	}

	@Override protected void onPause()
	{
		super.onPause();
		hideInputMethod();
	}

	/**
	 * 隐藏键盘
	 */
	public void hideInputMethod()
	{
		try
		{
			mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus()
				.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception ignored)
		{

		}
	}

	/**
	 * @return 获取屏幕高度
	 */
	public int getScrnHeight()
	{
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		return mDisplayMetrics.heightPixels;
	}

	/**
	 * @return 获取屏幕宽度
	 */
	public int getScrnWeight()
	{
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		return mDisplayMetrics.widthPixels;
	}

	public void showToast(String string)
	{
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
	}

	public void showAppMsg(String string)
	{
		AppMsg.Style style = new AppMsg.Style(
			AppMsg.LENGTH_SHORT, R.color.appmsg_custom);
		showAppMsg(string, style);
	}

	/**
	 * style = AppMsg.ALERT;
	 * style = AppMsg.CONFIRM;
	 * style = AppMsg.INFO;
	 */
	public void showAppMsg(String string, AppMsg.Style style)
	{
		AppMsg appMsg = AppMsg.makeText(this, string, style);
		appMsg.setLayoutGravity(Gravity.BOTTOM);
		appMsg.show();
	}

	public void showSnackbar(String string)
	{
		Snackbar snackbar;
		// 修改字的颜色
		SpannableString spanText = new SpannableString(string);
		spanText.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spanText.length(),
			Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

		snackbar = Snackbar.make(getWindow().getDecorView(), spanText, Snackbar.LENGTH_LONG);
		//修改背景为红色
		snackbar.getView().setBackgroundColor(0xEC000000);
		snackbar.show();
	}

	public void showSnackbar(String string, String action, View.OnClickListener listener)
	{
		Snackbar snackbar;
		// 修改字的颜色
		SpannableString spanText = new SpannableString(string);
		spanText.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spanText.length(),
			Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

		snackbar = Snackbar.make(getWindow().getDecorView(), spanText, Snackbar.LENGTH_LONG)
			.setAction(action, listener);
		//修改背景为红色
		snackbar.getView().setBackgroundColor(0xEC000000);
		snackbar.show();
	}

	/**
	 * @return 程序是否在前台运行
	 */
	public boolean isAppOnForeground()
	{
		// Returns a list of application processes that are running on the
		// device
		ActivityManager activityManager = (ActivityManager) getApplicationContext()
			.getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = getApplicationContext().getPackageName();

		List<RunningAppProcessInfo> appProcesses = activityManager
			.getRunningAppProcesses();
		if (appProcesses == null)
		{
			return false;
		}

		for (RunningAppProcessInfo appProcess : appProcesses)
		{
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
				&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
			{
				return true;
			}
		}
		return false;
	}

	public void start(Intent intent)
	{
		start(intent, RIGHT);
	}

	public void start(Intent intent, int style)
	{
		startActivity(intent);
		if (style == ZOOM)
		{
			overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_unzoom_out);
		}
		else if (style == LEFT)
		{
			overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_staty);
		}
		else if (style == RIGHT)
		{
			overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_staty);
		}
		else if (style == UP_DOWN)
		{
			overridePendingTransition(R.anim.activity_slide_in_bottom, R.anim.activity_staty);
		}
	}


	public void end(int style)
	{
		finish();
		if (style == ZOOM)
		{
			overridePendingTransition(R.anim.activity_staty, R.anim.activity_unzoom_out);
		}
		else if (style == LEFT)
		{
			overridePendingTransition(R.anim.activity_staty, R.anim.activity_slide_out_left);
		}
		else if (style == RIGHT)
		{
			overridePendingTransition(R.anim.activity_staty, R.anim.activity_slide_out_right);
		}
		else if (style == UP_DOWN)
		{
			overridePendingTransition(R.anim.activity_staty, R.anim.activity_slide_out_top);
		}
	}
}
