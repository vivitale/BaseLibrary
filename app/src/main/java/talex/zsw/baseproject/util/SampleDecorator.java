package talex.zsw.baseproject.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;

import java.util.Date;

import talex.zsw.baselibrary.view.TimesSquare.CalendarCellDecorator;
import talex.zsw.baselibrary.view.TimesSquare.CalendarCellView;

public class SampleDecorator implements CalendarCellDecorator
{
	@Override
	public void decorate(CalendarCellView cellView, Date date)
	{
		String dateString = Integer.toString(date.getDate());
		SpannableString string = new SpannableString(dateString + "\ntitle");
		string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
			Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		cellView.setText(string);
	}
}
