package talex.zsw.baselibrary.view.AndroidAnimations.library.specials.out;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

import talex.zsw.baselibrary.view.AndroidAnimations.easing.Glider;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.Skill;
import talex.zsw.baselibrary.view.AndroidAnimations.library.BaseViewAnimator;

public class TakingOffAnimator extends BaseViewAnimator
{
    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                Glider.glide(Skill.QuintEaseOut, getDuration(),
                    ObjectAnimator.ofFloat(target, "scaleX", 1f, 1.5f)),
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "scaleY", 1f, 1.5f)),
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "alpha", 1, 0))
        );
    }
}
