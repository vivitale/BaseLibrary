package talex.zsw.baselibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import talex.zsw.baselibrary.R;

@SuppressWarnings("deprecation")
public class DialogUtils
{
	public static TextView dTvInfo;
	public static TextView dTvButtonInfo;
	public static TextView dTvConfirm;
	public static TextView dTvCancel;
	public static ListView dListView;
	public static ImageView dIvImage;

	/**
	 * 底部弹出仿QQ Dialog
	 *
	 * @param mContext            上下文
	 * @param title               标题
	 * @param items               项目列表
	 * @param dialogClickListener 点击事件 public void confirm(String result)根据result为items中的字段
	 */
	public static Dialog ShowDialog(Context mContext, String title, String[] items,
									final DialogItemClickListener dialogClickListener)
	{
		final Dialog dialog = new Dialog(mContext,
			R.style.DialogStyle);
		dialog.setCancelable(false);
		View view = LayoutInflater.from(mContext).inflate(
			R.layout.dialog_radio, null);
		dialog.setContentView(view);
		if (title.equals("") || title == null)
		{
			((TextView) view.findViewById(R.id.title)).setVisibility(View.GONE);
			((TextView) view.findViewById(R.id.title_divider))
				.setVisibility(View.GONE);
		}
		else
		{
			((TextView) view.findViewById(R.id.title)).setText(title);
		}
		// 根据items动态创建
		LinearLayout parent = (LinearLayout) view
			.findViewById(R.id.dialogLayout);
		parent.removeAllViews();
		int length = items.length;
		for (int i = 0; i < items.length; i++)
		{
			LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(-1, -2);
			params1.rightMargin = 1;
			final TextView tv = new TextView(mContext);
			tv.setLayoutParams(params1);
			tv.setTextSize(18);
			tv.setText(items[i]);
			tv.setTextColor(mContext.getResources().getColor(
				R.color.dialogTxtColor));
			int pad = mContext.getResources().getDimensionPixelSize(
				R.dimen.padding10);
			tv.setPadding(pad, pad, pad, pad);
			tv.setSingleLine(true);
			tv.setGravity(Gravity.CENTER);
			if (i != length - 1)
			{
				if ((title.equals("") || title == null) && i == 0)
				{
					tv.setBackgroundColor(Color.WHITE);
				}
				else
				{
					tv.setBackgroundColor(Color.WHITE);
				}
			}

			else if (length == 1)
			{
				tv.setBackgroundColor(Color.WHITE);
			}
			else
			{
				tv.setBackgroundColor(Color.WHITE);
			}

			tv.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View arg0)
				{
					dialog.dismiss();
					dialogClickListener.confirm(tv.getText().toString());
				}
			});
			parent.addView(tv);
			if (i != length - 1)
			{
				TextView divider = new TextView(mContext);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, (int) 1);
				divider.setLayoutParams(params);
				divider.setBackgroundResource(android.R.color.darker_gray);
				parent.addView(divider);
			}
		}
		view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				dialog.dismiss();
			}
		});
		Window mWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = mWindow.getAttributes();
		lp.width = getScreenWidth(mContext);
		mWindow.setGravity(Gravity.BOTTOM);
		// 添加动画
		mWindow.setWindowAnimations(R.style.dialogAnim);
		mWindow.setAttributes(lp);
		dialog.show();
		return dialog;
	}

	public interface DialogItemClickListener
	{
		public abstract void confirm(String result);
	}

	/** 获取屏幕分辨率宽 */
	public static int getScreenWidth(Context context)
	{
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
			.getMetrics(dm);
		return dm.widthPixels;
	}

	/** 获取屏幕分辨率高 */
	public static int getScreenHeight(Context context)
	{
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
			.getMetrics(dm);
		return dm.heightPixels;
	}

	/** 获取屏幕分辨率宽计算dialog的宽度 */
	private static int dip2px(Context context, float dipValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	private static View inflate(int resource, Context context)
	{
		return LayoutInflater.from(context).inflate(resource, null);
	}
}
