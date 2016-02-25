package talex.zsw.baselibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Date;

import talex.zsw.baselibrary.R;

public class TimeSinceTextView extends TextView
{
	private static final int[] TIMESPAN_IDS =
		{R.plurals.timespan_years, R.plurals.timespan_months, R.plurals.timespan_weeks,
			R.plurals.timespan_days, R.plurals.timespan_hours, R.plurals.timespan_minutes,
			R.plurals.timespan_seconds};

	private static final int[] TIMESPAN_IDS_ABBR =
		{R.plurals.timespan_years_abbr, R.plurals.timespan_months_abbr,
			R.plurals.timespan_weeks_abbr, R.plurals.timespan_days_abbr,
			R.plurals.timespan_hours_abbr, R.plurals.timespan_minutes_abbr,
			R.plurals.timespan_seconds_abbr};

	private static int NOW_THRESHOLD_SECONDS = 10;

	private boolean mAbbreviated = false;

	public TimeSinceTextView(Context context)
	{
		super( context );
	}

	public TimeSinceTextView(Context context, AttributeSet attrs)
	{
		super( context, attrs );
		mAbbreviated = attrs
			.getAttributeBooleanValue( "http://schemas.android.com/apk/res-auto", "abbreviated",
				false );
	}

	public void setDate(Date date)
	{
		setText( getFormattedDateString( date.getTime() / 1000, mAbbreviated, getContext() ) );
	}

	public void setDate(long utc)
	{
		setText( getFormattedDateString( utc, mAbbreviated, getContext() ) );
	}

	public static String getFormattedDateString(long utc, boolean abbreviated, Context context)
	{
		long currentTime = System.currentTimeMillis() / 1000;
		return getFormattedDateString( utc, currentTime, abbreviated, context );
	}

	public static String getFormattedDateString(long start, long end, boolean abbreviated,
		Context context)
	{
		int seconds = (int) (end - start);
		int[] units = new int[]{seconds / 31536000, // years
			seconds / 2592000, // months
			seconds / 604800, // weeks
			seconds / 86400, // days
			seconds / 3600, // hours
			seconds / 60, // minutes
			seconds};

		String output = "";
		int unit = 0;
		for(int i = 0; i < units.length; i++)
		{
			unit = units[i];
			if(unit > 0)
			{
				output = context.getResources()
					.getQuantityString( abbreviated ? TIMESPAN_IDS_ABBR[i] : TIMESPAN_IDS[i], unit,
						unit );
				break;
			}
		}

		if(unit == seconds && seconds <= NOW_THRESHOLD_SECONDS)
		{
			output = context.getString( R.string.timespan_now );
		}

		return String.format( output, unit );
	}
}
