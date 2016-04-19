package talex.zsw.baselibrary.view.AndroidAnimations.library.specials.in;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

import talex.zsw.baselibrary.view.AndroidAnimations.easing.Glider;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.Skill;
import talex.zsw.baselibrary.view.AndroidAnimations.library.BaseViewAnimator;

public class LandingAnimator extends BaseViewAnimator
{
    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                Glider.glide(Skill.QuintEaseOut, getDuration(),
                    ObjectAnimator.ofFloat(target, "scaleX", 1.5f, 1f)),
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "scaleY", 1.5f, 1f)),
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "alpha", 0, 1f))
        );
    }
}
