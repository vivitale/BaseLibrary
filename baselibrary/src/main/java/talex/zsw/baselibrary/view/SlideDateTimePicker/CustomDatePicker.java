package talex.zsw.baselibrary.view.SlideDateTimePicker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import java.lang.reflect.Field;

import talex.zsw.baselibrary.R;
import talex.zsw.baselibrary.util.klog.KLog;

/**
 * A subclass of {@link DatePicker} that uses
 * reflection to allow for customization of the default blue
 * dividers.
 *
 * @author jjobes
 */
public class CustomDatePicker extends DatePicker
{
	private static final String TAG = "CustomDatePicker";

	public CustomDatePicker(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		Class<?> idClass = null;
		Class<?> numberPickerClass = null;
		Field selectionDividerField = null;
		Field monthField = null;
		Field dayField = null;
		Field yearField = null;
		NumberPicker monthNumberPicker = null;
		NumberPicker dayNumberPicker = null;
		NumberPicker yearNumberPicker = null;

		try
		{
			// Create an instance of the id class
			idClass = Class.forName("com.android.internal.R$id");

			// Get the fields that store the resource IDs for the month, day and year NumberPickers
			monthField = idClass.getField("month");
			dayField = idClass.getField("day");
			yearField = idClass.getField("year");

			// Use the resource IDs to get references to the month, day and year NumberPickers
			monthNumberPicker = (NumberPicker) findViewById(monthField.getInt(null));
			dayNumberPicker = (NumberPicker) findViewById(dayField.getInt(null));
			yearNumberPicker = (NumberPicker) findViewById(yearField.getInt(null));

			numberPickerClass = Class.forName("android.widget.NumberPicker");

			// Set the value of the mSelectionDivider field in the month, day and year NumberPickers
			// to refer to our custom drawables
			selectionDividerField = numberPickerClass.getDeclaredField("mSelectionDivider");
			selectionDividerField.setAccessible(true);
			selectionDividerField
				.set(monthNumberPicker, getResources().getDrawable(R.drawable.selection_divider));
			selectionDividerField
				.set(dayNumberPicker, getResources().getDrawable(R.drawable.selection_divider));
			selectionDividerField
				.set(yearNumberPicker, getResources().getDrawable(R.drawable.selection_divider));
		} catch (ClassNotFoundException e)
		{
			KLog.e(TAG, "ClassNotFoundException in CustomDatePicker", e);
		} catch (NoSuchFieldException e)
		{
			KLog.e(TAG, "NoSuchFieldException in CustomDatePicker", e);
		} catch (IllegalAccessException e)
		{
			KLog.e(TAG, "IllegalAccessException in CustomDatePicker", e);
		} catch (IllegalArgumentException e)
		{
			KLog.e(TAG, "IllegalArgumentException in CustomDatePicker", e);
		}
	}
}
