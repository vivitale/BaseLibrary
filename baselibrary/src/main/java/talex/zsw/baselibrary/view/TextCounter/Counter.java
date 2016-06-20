package talex.zsw.baselibrary.view.TextCounter;

import java.math.BigDecimal;

/**
 * Created by prem on 10/28/14.
 * <p/>
 * Class that handles the counting up/down of the text value
 */
class Counter implements Runnable
{

	final CounterView view;
	final BigDecimal increment, startValue, endValue;
	final long interval;
	BigDecimal currentValue, newValue;

	Counter(CounterView view, BigDecimal startValue, BigDecimal endValue, long interval,
			BigDecimal increment)
	{
		this.view = view;
		this.startValue = startValue;
		this.endValue = endValue;
		this.interval = interval;
		this.increment = increment;
		this.newValue = this.startValue;
//		this.currentValue = this.startValue - increment;
		this.currentValue = this.startValue.subtract(increment);
	}

	@Override
	public void run()
	{
		if (valuesAreCorrect())
		{
			final BigDecimal valueToSet;
			if (increment.doubleValue() > 0)
			{
				if (newValue.subtract(endValue).doubleValue() <= 0)
				{
					valueToSet = newValue;
				}
				else
				{
					valueToSet = endValue;
				}
			}
			else if (increment.doubleValue() < 0)
			{
				if (newValue.subtract(endValue).doubleValue() >= 0)
				{
					valueToSet = newValue;
				}
				else
				{
					valueToSet = endValue;
				}
			}
			else
			{
				return;
			}
			view.setCurrentTextValue(valueToSet);
			currentValue = newValue;
			newValue = newValue.add(increment);
			view.removeCallbacks(Counter.this);
			view.postDelayed(Counter.this, interval);
		}
		else
		{
			view.setCurrentTextValue(endValue);
		}
	}

	private boolean valuesAreCorrect()
	{
		if (increment.doubleValue() > 0)
		{
			return newValue.subtract(currentValue).doubleValue() >= 0 &&
				newValue.subtract(endValue).doubleValue() < 0;
		}
		else if (increment.doubleValue() < 0)
		{
			return newValue.subtract(currentValue).doubleValue() < 0 &&
				newValue.subtract(endValue).doubleValue() > 0;
		}
		else
		{
			return false;
		}
	}
}
