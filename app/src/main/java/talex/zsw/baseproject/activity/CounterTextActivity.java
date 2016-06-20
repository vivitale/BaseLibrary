package talex.zsw.baseproject.activity;

import android.app.Activity;
import android.os.Bundle;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import talex.zsw.baselibrary.view.TextCounter.CounterView;
import talex.zsw.baselibrary.view.TextCounter.Formatter;
import talex.zsw.baseproject.R;

/**
 * 项目包名: talex.zsw.baseproject.activity
 * 作用: 文字自动增长
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/6/20 09:49 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CounterTextActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_countertext);

		final CounterView counterView = (CounterView) findViewById(R.id.secondCounter);
		counterView.setAutoFormat(false);
		counterView.setFormatter(new Formatter()
		{
			@Override
			public String format(String prefix, String suffix, BigDecimal value)
			{
				return prefix +
					NumberFormat.getNumberInstance(Locale.US).format(value.floatValue()) + suffix;
			}
		});
		counterView.setAutoStart(false);
		counterView.setStartValue(new BigDecimal("200"));
		counterView.setEndValue(new BigDecimal("1000"));
		counterView.setIncrement(new BigDecimal("5")); // the amount the number increments at each time interval
		counterView.setTimeInterval(2); // the time interval (ms) at which the text changes
		counterView.setPrefix("You have ");
		counterView.setSuffix(" points!");
		counterView.start();
	}
}
