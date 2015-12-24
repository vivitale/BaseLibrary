package talex.zsw.baseproject.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import talex.zsw.baselibrary.view.TimesSquare.CalendarCellDecorator;
import talex.zsw.baselibrary.view.TimesSquare.CalendarPickerView;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.util.SampleDecorator;

/**
 * 项目名称: BaseProject
 * 作用: 日历控件
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/11/24 15:27 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TimesSquareActivity extends Activity
{
	private static final String TAG = "SampleTimesSquareActivi";
	private CalendarPickerView calendar;
	private AlertDialog theDialog;
	private CalendarPickerView dialogView;
	private final Set<Button> modeButtons = new LinkedHashSet<Button>();

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timessquare);

		final Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.YEAR, 1);

		final Calendar lastYear = Calendar.getInstance();
		lastYear.add(Calendar.YEAR, -1);

		calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
		calendar.init(lastYear.getTime(), nextYear.getTime()) //
			.inMode(CalendarPickerView.SelectionMode.SINGLE) //
			.withSelectedDate(new Date());

		initButtonListeners(nextYear, lastYear);
	}

	private void initButtonListeners(final Calendar nextYear, final Calendar lastYear)
	{
		final Button single = (Button) findViewById(R.id.button_single);
		final Button multi = (Button) findViewById(R.id.button_multi);
		final Button range = (Button) findViewById(R.id.button_range);
		final Button displayOnly = (Button) findViewById(R.id.button_display_only);
		final Button dialog = (Button) findViewById(R.id.button_dialog);
		final Button customized = (Button) findViewById(R.id.button_customized);
		final Button decorator = (Button) findViewById(R.id.button_decorator);
		final Button rtl = (Button) findViewById(R.id.button_rtl);

		modeButtons.addAll(Arrays.asList(single, multi, range, displayOnly, decorator));

		single.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				setButtonsEnabled(single);

				calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
				calendar.init(lastYear.getTime(), nextYear.getTime()) //
					.inMode(CalendarPickerView.SelectionMode.SINGLE) //
					.withSelectedDate(new Date());
			}
		});

		multi.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				setButtonsEnabled(multi);

				Calendar today = Calendar.getInstance();
				ArrayList<Date> dates = new ArrayList<Date>();
				for (int i = 0; i < 5; i++)
				{
					today.add(Calendar.DAY_OF_MONTH, 3);
					dates.add(today.getTime());
				}
				calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
				calendar.init(new Date(), nextYear.getTime()) //
					.inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
					.withSelectedDates(dates);
			}
		});

		range.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				setButtonsEnabled(range);

				Calendar today = Calendar.getInstance();
				ArrayList<Date> dates = new ArrayList<Date>();
				today.add(Calendar.DATE, 3);
				dates.add(today.getTime());
				today.add(Calendar.DATE, 5);
				dates.add(today.getTime());
				calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
				calendar.init(new Date(), nextYear.getTime()) //
					.inMode(CalendarPickerView.SelectionMode.RANGE) //
					.withSelectedDates(dates);
			}
		});

		displayOnly.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				setButtonsEnabled(displayOnly);

				calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
				calendar.init(new Date(), nextYear.getTime()) //
					.inMode(CalendarPickerView.SelectionMode.SINGLE) //
					.withSelectedDate(new Date()) //
					.displayOnly();
			}
		});

		dialog.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View view)
			{
				String title = "I'm a dialog!";
				showCalendarInDialog(title, R.layout.ts_dialog);
				dialogView.init(lastYear.getTime(), nextYear.getTime()) //
					.withSelectedDate(new Date());
			}
		});

		customized.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View view)
			{
				showCalendarInDialog("Pimp my calendar!", R.layout.ts_dialog_customized);
				dialogView.init(lastYear.getTime(), nextYear.getTime())
					.withSelectedDate(new Date());
			}
		});

		decorator.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				setButtonsEnabled(decorator);

				calendar.setDecorators(Arrays.<CalendarCellDecorator>asList(new SampleDecorator()));
				calendar.init(lastYear.getTime(), nextYear.getTime()) //
					.inMode(CalendarPickerView.SelectionMode.SINGLE) //
					.withSelectedDate(new Date());
			}
		});

		rtl.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View view)
			{
				showCalendarInDialog("I'm right-to-left!", R.layout.ts_dialog);
				dialogView.init(lastYear.getTime(), nextYear.getTime(), new Locale("iw", "IL")) //
					.withSelectedDate(new Date());
			}
		});

		findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View view)
			{
				Log.d(TAG, "Selected time in millis: " + calendar.getSelectedDate().getTime());
				String toast = "Selected: " + calendar.getSelectedDate().getTime();
				Toast.makeText(TimesSquareActivity.this, toast, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void showCalendarInDialog(String title, int layoutResId)
	{
		dialogView = (CalendarPickerView) getLayoutInflater().inflate(layoutResId, null, false);
		theDialog = new AlertDialog.Builder(this) //
			.setTitle(title)
			.setView(dialogView)
			.setNeutralButton("Dismiss", new DialogInterface.OnClickListener()
			{
				@Override public void onClick(DialogInterface dialogInterface, int i)
				{
					dialogInterface.dismiss();
				}
			})
			.create();
		theDialog.setOnShowListener(new DialogInterface.OnShowListener()
		{
			@Override public void onShow(DialogInterface dialogInterface)
			{
				Log.d(TAG, "onShow: fix the dimens!");
				dialogView.fixDialogDimens();
			}
		});
		theDialog.show();
	}

	private void setButtonsEnabled(Button currentButton)
	{
		for (Button modeButton : modeButtons)
		{
			modeButton.setEnabled(modeButton != currentButton);
		}
	}

	@Override public void onConfigurationChanged(Configuration newConfig)
	{
		boolean applyFixes = theDialog != null && theDialog.isShowing();
		if (applyFixes)
		{
			Log.d(TAG, "Config change: unfix the dimens so I'll get remeasured!");
			dialogView.unfixDialogDimens();
		}
		super.onConfigurationChanged(newConfig);
		if (applyFixes)
		{
			dialogView.post(new Runnable()
			{
				@Override public void run()
				{
					Log.d(TAG, "Config change done: re-fix the dimens!");
					dialogView.fixDialogDimens();
				}
			});
		}
	}
}
