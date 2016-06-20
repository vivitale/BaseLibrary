package talex.zsw.baselibrary.view.TextCounter.formatters;


import java.math.BigDecimal;

import talex.zsw.baselibrary.view.TextCounter.Formatter;

public class IntegerFormatter implements Formatter
{

	@Override
	public String format(String prefix, String suffix, BigDecimal value)
	{
		return prefix + value.intValue() + suffix;
	}
}
