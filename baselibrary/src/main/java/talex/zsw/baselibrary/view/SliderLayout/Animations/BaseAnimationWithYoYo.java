package talex.zsw.baselibrary.view.SliderLayout.Animations;

import android.view.View;

import talex.zsw.baselibrary.R;
import talex.zsw.baselibrary.view.AndroidAnimations.library.Techniques;
import talex.zsw.baselibrary.view.AndroidAnimations.library.YoYo;

public class BaseAnimationWithYoYo implements BaseAnimationInterface
{

	private final static String TAG = "ChildAnimationExample";
	private Techniques animtionIn, animtionOut;

	public BaseAnimationWithYoYo(Techniques animationIn, Techniques animationOut)
	{
		this.animtionIn = animationIn;
		this.animtionOut = animationOut;
	}

	@Override
	public void onPrepareCurrentItemLeaveScreen(View current)
	{
		View descriptionLayout =
			current.findViewById(R.id.description_layout);
		if (descriptionLayout != null)
		{
			current.findViewById(R.id.description_layout)
				.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onPrepareNextItemShowInScreen(View next)
	{
		View descriptionLayout =
			next.findViewById(R.id.description_layout);
		if (descriptionLayout != null)
		{
			next.findViewById(R.id.description_layout)
				.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onCurrentItemDisappear(View view)
	{
		View descriptionLayout =
			view.findViewById(R.id.description_layout);
		if (descriptionLayout != null)
		{
			descriptionLayout.setVisibility(View.VISIBLE);
			if (animtionOut != null)
			{
				YoYo.with(animtionOut).playOn(descriptionLayout);
			}
		}
	}

	@Override
	public void onNextItemAppear(View view)
	{
		View descriptionLayout =
			view.findViewById(R.id.description_layout);
		if (descriptionLayout != null)
		{
			descriptionLayout.setVisibility(View.VISIBLE);
			if (animtionIn != null)
			{
				YoYo.with(animtionIn).playOn(descriptionLayout);
			}
		}
	}
}
