package talex.zsw.baselibrary.view.TextCounter.formatters;


import java.math.BigDecimal;

import talex.zsw.baselibrary.util.MathUtils;
import talex.zsw.baselibrary.view.TextCounter.Formatter;

/**
 * Created by prem on 10/28/14.
 */
public class DecimalFormatter implements Formatter
{
	@Override
	public String format(String prefix, String suffix, BigDecimal value)
	{
		value = value.setScale(2,BigDecimal.ROUND_HALF_UP);
		return prefix + MathUtils.doubleToStringWith2(value.doubleValue()) + suffix;
	}
}
