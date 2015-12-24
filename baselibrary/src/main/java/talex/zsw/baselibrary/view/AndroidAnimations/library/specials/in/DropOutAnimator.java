package talex.zsw.baselibrary.view.AndroidAnimations.library.specials.in;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

import talex.zsw.baselibrary.view.AndroidAnimations.easing.Glider;
import talex.zsw.baselibrary.view.AndroidAnimations.easing.Skill;
import talex.zsw.baselibrary.view.AndroidAnimations.library.BaseViewAnimator;

public class DropOutAnimator extends BaseViewAnimator
{
    @Override
    protected void prepare(View target) {
        int distance = target.getTop() + target.getHeight();
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 0, 1),
                Glider.glide(Skill.BounceEaseOut, getDuration(),
                    ObjectAnimator.ofFloat(target, "translationY", -distance, 0))
        );
    }
}
