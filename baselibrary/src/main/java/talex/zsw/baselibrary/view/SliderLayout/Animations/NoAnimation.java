package talex.zsw.baselibrary.view.SliderLayout.Animations;

import android.view.View;

public class NoAnimation implements BaseAnimationInterface
{
	@Override public void onPrepareCurrentItemLeaveScreen(View current)
	{
		View descriptionLayout =
			current.findViewById( talex.zsw.baselibrary.R.id.description_layout );
		if(descriptionLayout != null)
		{
//			current.findViewById( talex.zsw.baselibrary.R.id.description_layout )
//				.setVisibility( View.INVISIBLE );
		}
	}

	/**
	 * When next item is coming to show, let's hide the description layout.
	 *
	 * @param next
	 */
	@Override public void onPrepareNextItemShowInScreen(View next)
	{
		View descriptionLayout = next.findViewById( talex.zsw.baselibrary.R.id.description_layout );
		if(descriptionLayout != null)
		{
//			next.findViewById( talex.zsw.baselibrary.R.id.description_layout )
//				.setVisibility( View.INVISIBLE );
		}
	}


	@Override public void onCurrentItemDisappear(View view)
	{

	}

	/**
	 * When next item show in ViewPagerEx, let's make an animation to show the
	 * description layout.
	 *
	 * @param view
	 */
	@Override public void onNextItemAppear(View view)
	{

		View descriptionLayout = view.findViewById( talex.zsw.baselibrary.R.id.description_layout );
		if(descriptionLayout != null)
		{
			view.findViewById( talex.zsw.baselibrary.R.id.description_layout )
				.setVisibility( View.VISIBLE );
		}
	}
}
