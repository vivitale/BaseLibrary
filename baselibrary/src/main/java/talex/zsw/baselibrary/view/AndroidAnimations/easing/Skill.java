/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package talex.zsw.baselibrary.view.AndroidAnimations.easing;

import talex.zsw.baselibrary.view.AndroidAnimations.easing.back.BackEaseIn;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.back.BackEaseInOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.back.BackEaseOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.bounce.BounceEaseIn;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.bounce.BounceEaseInOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.bounce.BounceEaseOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.circ.CircEaseIn;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.circ.CircEaseInOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.circ.CircEaseOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.cubic.CubicEaseIn;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.cubic.CubicEaseInOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.cubic.CubicEaseOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.elastic.ElasticEaseIn;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.elastic.ElasticEaseOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.expo.ExpoEaseIn;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.expo.ExpoEaseInOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.expo.ExpoEaseOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.quad.QuadEaseIn;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.quad.QuadEaseInOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.quad.QuadEaseOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.quint.QuintEaseIn;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.quint.QuintEaseInOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.quint.QuintEaseOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.sine.SineEaseIn;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.sine.SineEaseInOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.sine.SineEaseOut;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.linear.Linear;


public enum Skill
{

	BackEaseIn(BackEaseIn.class),
	BackEaseOut(BackEaseOut.class),
	BackEaseInOut(BackEaseInOut.class),

	BounceEaseIn(BounceEaseIn.class),
	BounceEaseOut(BounceEaseOut.class),
	BounceEaseInOut(BounceEaseInOut.class),

	CircEaseIn(CircEaseIn.class),
	CircEaseOut(CircEaseOut.class),
	CircEaseInOut(CircEaseInOut.class),

	CubicEaseIn(CubicEaseIn.class),
	CubicEaseOut(CubicEaseOut.class),
	CubicEaseInOut(CubicEaseInOut.class),

	ElasticEaseIn(ElasticEaseIn.class),
	ElasticEaseOut(ElasticEaseOut.class),

	ExpoEaseIn(ExpoEaseIn.class),
	ExpoEaseOut(ExpoEaseOut.class),
	ExpoEaseInOut(ExpoEaseInOut.class),

	QuadEaseIn(QuadEaseIn.class),
	QuadEaseOut(QuadEaseOut.class),
	QuadEaseInOut(QuadEaseInOut.class),

	QuintEaseIn(QuintEaseIn.class),
	QuintEaseOut(QuintEaseOut.class),
	QuintEaseInOut(QuintEaseInOut.class),

	SineEaseIn(SineEaseIn.class),
	SineEaseOut(SineEaseOut.class),
	SineEaseInOut(SineEaseInOut.class),

	Linear(Linear.class);


	private Class easingMethod;

	private Skill(Class clazz)
	{
		easingMethod = clazz;
	}

	public BaseEasingMethod getMethod(float duration)
	{
		try
		{
			return (BaseEasingMethod) easingMethod.getConstructor(float.class)
				.newInstance(duration);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Error("Can not init easingMethod instance");
		}
	}
}
