package talex.zsw.baselibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import talex.zsw.baselibrary.R;


public class LoadingDialog extends Dialog
{
	public LoadingDialog(Context context)
	{
		super(context, R.style.DialogStyle);
		initView(context);
	}

	public LoadingDialog(Context context, int theme)
	{
		super(context, theme);
		initView(context);
	}

	private void initView(final Context context)
	{
		this.setOnCancelListener(new OnCancelListener()
		{
			@Override
			public void onCancel(DialogInterface dialog)
			{
				Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
			}
		});
		this.setCancelable(false);
		this.setCanceledOnTouchOutside(false);
		LayoutInflater inflater = LayoutInflater.from(context);
		final View v = inflater.inflate(R.layout.widget_loading_dialog, null);
		setCancelable(false);
		setCanceledOnTouchOutside(false);
		setContentView(v);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return super.onTouchEvent(event);
	}

	@Override
	public void dismiss()
	{
		if (this != null && isShowing())
		{
			super.dismiss();
		}
	}

	@Override
	public void show()
	{
		if (this != null && !isShowing())
		{
			super.show();
		}
	}
}
