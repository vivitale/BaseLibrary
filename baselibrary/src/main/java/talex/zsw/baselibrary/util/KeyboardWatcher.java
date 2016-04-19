package talex.zsw.baselibrary.util;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/**
 * 项目名称: BaseProject
 * 作用: 键盘的监听
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 使用:
 * 1. 清单文件中 android:windowSoftInputMode="adjustResize"
 * 2. implements KeyboardWatcher.OnKeyboardToggleListener
 * 3.onCreate()中 keyboardWatcher = KeyboardWatcher.initWith(this).bindKeyboardWatcher(this);
 * 4.onDestroy()中 keyboardWatcher.unbindKeyboardWatcher();
 * 日期: 2016 16/3/24 17:14 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class KeyboardWatcher
{
	private Activity activity;
	private ViewGroup rootView;
	private ViewTreeObserver.OnGlobalLayoutListener viewTreeObserverListener;
	private OnKeyboardToggleListener onKeyboardToggleListener;

	public static KeyboardWatcher initWith(Activity activity)
	{
		KeyboardWatcher keyboardWatcher = new KeyboardWatcher();
		keyboardWatcher.activity = activity;
		return keyboardWatcher;
	}

	public KeyboardWatcher bindKeyboardWatcher(OnKeyboardToggleListener onKeyboardToggleListener)
	{
		this.onKeyboardToggleListener = onKeyboardToggleListener;
		final View root = activity.findViewById( android.R.id.content );
		if(hasAdjustResizeInputMode())
		{
			if(root instanceof ViewGroup)
			{
				rootView = (ViewGroup) root;
				viewTreeObserverListener = new GlobalLayoutListener();
				rootView.getViewTreeObserver()
					.addOnGlobalLayoutListener( viewTreeObserverListener );
			}
		}
		else
		{
			Log.w( "KeyboardWatcher", "Activity " + activity.getClass().getSimpleName() +
				" should have windowSoftInputMode=\"adjustResize\"" +
				"to make KeyboardWatcher working. You can set it in AndroidManifest.xml" );
		}

		return this;
	}

	private boolean hasAdjustResizeInputMode()
	{
		return (activity.getWindow().getAttributes().softInputMode &
			WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) != 0;
	}

	public void unbindKeyboardWatcher()
	{
		if(rootView != null && viewTreeObserverListener != null)
		{
			if(Build.VERSION.SDK_INT >= 16)
			{
				rootView.getViewTreeObserver()
					.removeOnGlobalLayoutListener( viewTreeObserverListener );
			}
			else
			{
				rootView.getViewTreeObserver()
					.removeGlobalOnLayoutListener( viewTreeObserverListener );
			}

			this.onKeyboardToggleListener = null;
			this.activity = null;
		}
	}

	private class GlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener
	{
		int initialValue;
		private boolean hasSentInitialAction;
		private boolean isKeyboardShown;

		@Override public void onGlobalLayout()
		{
			if(initialValue == 0)
			{
				initialValue = rootView.getHeight();
			}
			else
			{
				if(initialValue > rootView.getHeight())
				{
					if(onKeyboardToggleListener != null)
					{
						if(!hasSentInitialAction || !isKeyboardShown)
						{
							isKeyboardShown = true;
							onKeyboardToggleListener
								.onKeyboardShown( initialValue - rootView.getHeight() );
						}
					}
				}
				else
				{
					if(!hasSentInitialAction || isKeyboardShown)
					{
						isKeyboardShown = false;
						rootView.post( new Runnable()
						{
							@Override public void run()
							{
								if(onKeyboardToggleListener != null)
								{
									onKeyboardToggleListener.onKeyboardClosed();
								}
							}
						} );
					}
				}

				hasSentInitialAction = true;
			}
		}
	}

	public interface OnKeyboardToggleListener
	{
		void onKeyboardShown(int keyboardSize);

		void onKeyboardClosed();
	}
}