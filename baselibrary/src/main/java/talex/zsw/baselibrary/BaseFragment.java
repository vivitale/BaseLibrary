package talex.zsw.baselibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Field;

import talex.zsw.baselibrary.util.ACache;
import talex.zsw.baselibrary.view.appmsg.AppMsg;
import talex.zsw.baselibrary.widget.LoadingDialog;


public abstract class BaseFragment extends Fragment
{
	private static final int ZOOM = 0x10001;
	private static final int LEFT = 0x10002;
	private static final int RIGHT = 0x10003;
	private static final int UP_DOWN = 0x10004;

	protected LoadingDialog loadingDialog;
	private LayoutInflater inflater;
	private InputMethodManager mInputMethodManager;
	private ViewGroup container;
	public View mView;
	public ACache mACache;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		this.inflater = inflater;
		this.container = container;
		if (mACache == null)
		{
			mACache = ACache.get(getActivity());
		}
		try
		{
			initArgs(getArguments());
			initView(savedInstanceState);
			initData();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		mInputMethodManager = (InputMethodManager) getActivity()
			.getSystemService(Context.INPUT_METHOD_SERVICE);
		hideInputMethod();
		return mView;
	}

	protected abstract void initArgs(Bundle bundle);

	protected abstract void initView(Bundle bundle);

	protected abstract void initData();

	protected void setContentView(int layout)
	{
		mView = inflater.inflate(layout, container, false);
	}

	/**
	 * 显示键盘
	 */
	public void showInputMethod(EditText editText)
	{
		mInputMethodManager.showSoftInput(editText,
			InputMethodManager.SHOW_IMPLICIT);
	}

	/**
	 * 隐藏键盘
	 */
	public void hideInputMethod()
	{
		try
		{
			//noinspection ConstantConditions
			mInputMethodManager.hideSoftInputFromWindow(getActivity()
					.getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception ignored)
		{

		}
	}

	@TargetApi(Build.VERSION_CODES.KITKAT) @Override
	public void onDetach()
	{
		try
		{
			Field childFragmentManager = Fragment.class
				.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException | IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		super.onDetach();
	}

	/**
	 * @return 获取屏幕宽度
	 */
	public int getScrnWeight()
	{
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
			.getMetrics(mDisplayMetrics);
		return mDisplayMetrics.widthPixels;
	}

	/**
	 * @return 获取屏幕高度
	 */
	public int getScrnHeight()
	{
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
			.getMetrics(mDisplayMetrics);
		return mDisplayMetrics.heightPixels;
	}

	public void showDialog()
	{
		if (null == loadingDialog)
		{
			loadingDialog = new LoadingDialog(getActivity());
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

	public void showToast(String string)
	{
		Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
	}

	public void showAppMsg(String string)
	{
		AppMsg.Style style = new AppMsg.Style(AppMsg.LENGTH_SHORT, R.color.appmsg_custom);
		showAppMsg(string, style);
	}

	/**
	 * style = AppMsg.ALERT;
	 * style = AppMsg.CONFIRM;
	 * style = AppMsg.INFO;
	 */
	public void showAppMsg(String string, AppMsg.Style style)
	{
		AppMsg appMsg = AppMsg.makeText(getActivity(), string, style);
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

		snackbar = Snackbar.make(mView, spanText, Snackbar.LENGTH_LONG);
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

		snackbar = Snackbar.make(mView, spanText, Snackbar.LENGTH_LONG)
			.setAction(action, listener);
		//修改背景为红色
		snackbar.getView().setBackgroundColor(0xEC000000);
		snackbar.show();
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
			getActivity().overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_unzoom_out);
		}
		else if (style == LEFT)
		{
			getActivity().overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_staty);
		}
		else if (style == RIGHT)
		{
			getActivity().overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_staty);
		}
		else if (style == UP_DOWN)
		{
			getActivity().overridePendingTransition(R.anim.activity_slide_in_bottom, R.anim.activity_staty);
		}
	}

	public void end(int style)
	{
		getActivity().finish();
		if (style == ZOOM)
		{
			getActivity().overridePendingTransition(R.anim.activity_staty, R.anim.activity_unzoom_out);
		}
		else if (style == LEFT)
		{
			getActivity().overridePendingTransition(R.anim.activity_staty, R.anim.activity_slide_out_left);
		}
		else if (style == RIGHT)
		{
			getActivity().overridePendingTransition(R.anim.activity_staty, R.anim.activity_slide_out_right);
		}
		else if (style == UP_DOWN)
		{
			getActivity().overridePendingTransition(R.anim.activity_staty, R.anim.activity_slide_out_top);
		}
	}
}
