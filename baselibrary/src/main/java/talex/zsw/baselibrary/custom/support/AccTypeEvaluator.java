package talex.zsw.baselibrary.custom.support;

import com.nineoldandroids.animation.TypeEvaluator;

public class AccTypeEvaluator
	implements TypeEvaluator<Float>
{

	@Override
	public Float evaluate(float fraction, Float startValue, Float endValue)
	{
		return fraction * (endValue - startValue);
	}
}
