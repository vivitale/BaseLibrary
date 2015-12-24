
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

package talex.zsw.baselibrary.view.AndroidAnimations.library;

import talex.zsw.baselibrary.view.AndroidAnimations.library.attention.BounceAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.attention.FlashAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.attention.PulseAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.attention.RubberBandAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.attention.ShakeAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.attention.StandUpAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.attention.SwingAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.attention.TadaAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.attention.WaveAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.attention.WobbleAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.bouncing_entrances.BounceInAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.bouncing_entrances.BounceInDownAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.bouncing_entrances.BounceInLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.bouncing_entrances.BounceInRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.bouncing_entrances.BounceInUpAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.fading_entrances.FadeInAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.fading_entrances.FadeInDownAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.fading_entrances.FadeInLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.fading_entrances.FadeInRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.fading_entrances.FadeInUpAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.fading_exits.FadeOutAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.fading_exits.FadeOutDownAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.fading_exits.FadeOutLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.fading_exits.FadeOutRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.fading_exits.FadeOutUpAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.flippers.FlipInXAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.flippers.FlipInYAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.flippers.FlipOutXAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.flippers.FlipOutYAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.rotating_entrances.RotateInAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.rotating_entrances.RotateInDownLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.rotating_entrances.RotateInDownRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.rotating_entrances.RotateInUpLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.rotating_entrances.RotateInUpRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.rotating_exits.RotateOutAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.rotating_exits.RotateOutDownLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.rotating_exits.RotateOutDownRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.rotating_exits.RotateOutUpLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.rotating_exits.RotateOutUpRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.sliders.SlideInDownAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.sliders.SlideInLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.sliders.SlideInRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.sliders.SlideInUpAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.sliders.SlideOutDownAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.sliders.SlideOutLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.sliders.SlideOutRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.sliders.SlideOutUpAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.specials.HingeAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.specials.RollInAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.specials.RollOutAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.specials.in.DropOutAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.specials.in.LandingAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.specials.out.TakingOffAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.zooming_entrances.ZoomInAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.zooming_entrances.ZoomInDownAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.zooming_entrances.ZoomInLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.zooming_entrances.ZoomInRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.zooming_entrances.ZoomInUpAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.zooming_exits.ZoomOutAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.zooming_exits.ZoomOutDownAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.zooming_exits.ZoomOutLeftAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.zooming_exits.ZoomOutRightAnimator;
import talex.zsw.baselibrary.view.AndroidAnimations.library.zooming_exits.ZoomOutUpAnimator;

public enum Techniques {

    DropOut(DropOutAnimator.class),
    Landing(LandingAnimator.class),
    TakingOff(TakingOffAnimator.class),

    Flash(FlashAnimator.class),
    Pulse(PulseAnimator.class),
    RubberBand(RubberBandAnimator.class),
    Shake(ShakeAnimator.class),
    Swing(SwingAnimator.class),
    Wobble(WobbleAnimator.class),
    Bounce(BounceAnimator.class),
    Tada(TadaAnimator.class),
    StandUp(StandUpAnimator.class),
    Wave(WaveAnimator.class),

    Hinge(HingeAnimator.class),
    RollIn(RollInAnimator.class),
    RollOut(RollOutAnimator.class),

    BounceIn(BounceInAnimator.class),
    BounceInDown(BounceInDownAnimator.class),
    BounceInLeft(BounceInLeftAnimator.class),
    BounceInRight(BounceInRightAnimator.class),
    BounceInUp(BounceInUpAnimator.class),

    FadeIn(FadeInAnimator.class),
    FadeInUp(FadeInUpAnimator.class),
    FadeInDown(FadeInDownAnimator.class),
    FadeInLeft(FadeInLeftAnimator.class),
    FadeInRight(FadeInRightAnimator.class),

    FadeOut(FadeOutAnimator.class),
    FadeOutDown(FadeOutDownAnimator.class),
    FadeOutLeft(FadeOutLeftAnimator.class),
    FadeOutRight(FadeOutRightAnimator.class),
    FadeOutUp(FadeOutUpAnimator.class),

    FlipInX(FlipInXAnimator.class),
    FlipOutX(FlipOutXAnimator.class),
    FlipInY(FlipInYAnimator.class),
    FlipOutY(FlipOutYAnimator.class),
    RotateIn(RotateInAnimator.class),
    RotateInDownLeft(RotateInDownLeftAnimator.class),
    RotateInDownRight(RotateInDownRightAnimator.class),
    RotateInUpLeft(RotateInUpLeftAnimator.class),
    RotateInUpRight(RotateInUpRightAnimator.class),

    RotateOut(RotateOutAnimator.class),
    RotateOutDownLeft(RotateOutDownLeftAnimator.class),
    RotateOutDownRight(RotateOutDownRightAnimator.class),
    RotateOutUpLeft(RotateOutUpLeftAnimator.class),
    RotateOutUpRight(RotateOutUpRightAnimator.class),

    SlideInLeft(SlideInLeftAnimator.class),
    SlideInRight(SlideInRightAnimator.class),
    SlideInUp(SlideInUpAnimator.class),
    SlideInDown(SlideInDownAnimator.class),

    SlideOutLeft(SlideOutLeftAnimator.class),
    SlideOutRight(SlideOutRightAnimator.class),
    SlideOutUp(SlideOutUpAnimator.class),
    SlideOutDown(SlideOutDownAnimator.class),

    ZoomIn(ZoomInAnimator.class),
    ZoomInDown(ZoomInDownAnimator.class),
    ZoomInLeft(ZoomInLeftAnimator.class),
    ZoomInRight(ZoomInRightAnimator.class),
    ZoomInUp(ZoomInUpAnimator.class),

    ZoomOut(ZoomOutAnimator.class),
    ZoomOutDown(ZoomOutDownAnimator.class),
    ZoomOutLeft(ZoomOutLeftAnimator.class),
    ZoomOutRight(ZoomOutRightAnimator.class),
    ZoomOutUp(ZoomOutUpAnimator.class);



    private Class animatorClazz;

    private Techniques(Class clazz) {
        animatorClazz = clazz;
    }

    public BaseViewAnimator getAnimator() {
        try {
            return (BaseViewAnimator) animatorClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
