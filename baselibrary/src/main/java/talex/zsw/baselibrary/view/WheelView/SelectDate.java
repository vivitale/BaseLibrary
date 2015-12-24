package talex.zsw.baselibrary.view.WheelView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Calendar;

import talex.zsw.baselibrary.R;


public class SelectDate extends PopupWindow implements OnClickListener {
	private View mMenuView;
	private ViewFlipper mViewFipper;
	private Button mBtnConfirm, mBtnCancel;
	private WheelView year, month, day;
	private TextView textView, mTvdate;

	private Activity mContext;
	/** 日期,格式为 yyyy-MM-dd */
	private String dateString;
	private String[] dateType;
	private int mCurYear = 1990, mCurMonth = 11, mCurDay = 1;
	private DateNumericAdapter monthAdapter, dayAdapter, yearAdapter;

	public SelectDate(Activity context, String dateString) {
		super(context);
		mContext = context;
		this.dateString = dateString;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.view_select_date, null);
		mViewFipper = new ViewFlipper(context);
		mViewFipper.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		mTvdate = (TextView) mMenuView.findViewById(R.id.date);
		year = (WheelView) mMenuView.findViewById(R.id.year);
		month = (WheelView) mMenuView.findViewById(R.id.month);
		day = (WheelView) mMenuView.findViewById(R.id.day);
		mBtnConfirm = (Button) mMenuView.findViewById(R.id.submit);
		mBtnCancel = (Button) mMenuView.findViewById(R.id.cancel);
		mBtnConfirm.setOnClickListener(this);
		mBtnCancel.setOnClickListener(this);
		Calendar calendar = Calendar.getInstance();
		OnWheelChangedListener listener = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				updateDays(year, month, day);

			}
		};
		int curYear = calendar.get(Calendar.YEAR);
		if (dateString != null && dateString.contains("-")) {
			String str[] = dateString.split("-");
			mCurYear = 100 - (curYear - Integer.parseInt(str[0]));
			mCurMonth = Integer.parseInt(str[1]) - 1;
			mCurDay = Integer.parseInt(str[2]) - 1;
			;
		}
		dateType = mContext.getResources().getStringArray(R.array.date);
		monthAdapter = new DateNumericAdapter(context, 1, 12, 5);
		monthAdapter.setTextType(dateType[1]);
		month.setViewAdapter(monthAdapter);
		month.setCurrentItem(mCurMonth);
		month.addChangingListener(listener);
		// year

		yearAdapter = new DateNumericAdapter(context, curYear - 100,
				curYear + 100, 100 - 20);
		yearAdapter.setTextType(dateType[0]);
		year.setViewAdapter(yearAdapter);
		year.setCurrentItem(mCurYear);
		year.addChangingListener(listener);
		// day

		updateDays(year, month, day);
		day.setCurrentItem(mCurDay);
		updateDays(year, month, day);
		day.addChangingListener(listener);

		mViewFipper.addView(mMenuView);
		mViewFipper.setFlipInterval(6000000);
		this.setContentView(mViewFipper);
		this.setWidth(LayoutParams.FILL_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0x00000000);
		this.setBackgroundDrawable(dw);
		this.update();
	}

	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		super.showAtLocation(parent, gravity, x, y);
		mViewFipper.startFlipping();
	}

	public void setDate(String date) {
		dateString = date;
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		if (dateString != null && dateString.contains("-")) {
			String str[] = dateString.split("-");
			mCurYear = 100 - (curYear - Integer.parseInt(str[0]));
			mCurMonth = Integer.parseInt(str[1]) - 1;
			mCurDay = Integer.parseInt(str[2]) - 1;
		}
		updateDays(year, month, day);
	}

	public void setTextView(TextView textView) {
		this.textView = textView;
	}

	private void updateDays(WheelView year, WheelView month, WheelView day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				calendar.get(Calendar.YEAR) + year.getCurrentItem());
		calendar.set(Calendar.MONTH, month.getCurrentItem());

		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		dayAdapter = new DateNumericAdapter(mContext, 1, maxDays,
				calendar.get(Calendar.DAY_OF_MONTH) - 1);
		dayAdapter.setTextType(dateType[2]);
		day.setViewAdapter(dayAdapter);
		int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
		day.setCurrentItem(curDay - 1, true);
		int years = calendar.get(Calendar.YEAR) - 100;
		dateString = years + "-" + (month.getCurrentItem() + 1) + "-"
				+ (day.getCurrentItem() + 1);
		mTvdate.setText(dateString);
	}

	/**
	 * Adapter for numeric wheels. Highlights the current value.
	 */
	private class DateNumericAdapter extends NumericWheelAdapter {
		int currentItem;
		int currentValue;

		/**
		 * Constructor
		 */
		public DateNumericAdapter(Context context, int minValue, int maxValue,
				int current) {
			super(context, minValue, maxValue);
			this.currentValue = current;
			setTextSize(24);
		}

		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			view.setTypeface(Typeface.SANS_SERIF);
		}

		public CharSequence getItemText(int index) {
			currentItem = index;
			return super.getItemText(index);
		}

	}

	public void onClick(View v) {
		if (v == mBtnConfirm) {
			if (textView != null) {
				textView.setText(dateString);
			}
		}
		this.dismiss();
	}
}
