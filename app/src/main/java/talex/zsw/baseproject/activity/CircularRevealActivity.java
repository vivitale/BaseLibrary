package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.util.ViewUtils;
import talex.zsw.baselibrary.view.CircularReveal.animation.SupportAnimator;
import talex.zsw.baselibrary.view.CircularReveal.animation.ViewAnimationUtils;
import talex.zsw.baselibrary.view.CircularReveal.widget.RevealFrameLayout;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/1/6 10:37 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CircularRevealActivity extends AppCompatActivity
{
	@Bind(R.id.cardsGroup) RecyclerView mCardsGroup;
	@Bind(R.id.floatingActionButton) FloatingActionButton mFloatingButton;
	@Bind(R.id.toolbar) Toolbar mToolbar;

	private LinearLayoutManager mLayoutManager;
	private RecycleAdapter mCardsAdapter;

	private SupportAnimator mAnimator;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_circualreveal );
		ButterKnife.bind( this );

		setSupportActionBar( mToolbar );

		mLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false );
		mCardsAdapter = new RecycleAdapter();
		mCardsAdapter.setHasStableIds( true );

		mCardsGroup.setHasFixedSize( true );
		mCardsGroup.setItemViewCacheSize( 3 );
		mCardsGroup.setClipToPadding( false );
		mCardsGroup.setAdapter( mCardsAdapter );
		mCardsGroup.setLayoutManager( mLayoutManager );

		mToolbar.getViewTreeObserver()
			.addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener()
			{
				@Override public void onGlobalLayout()
				{
					ViewUtils.removeGlobalListeners( mToolbar, this );

					final int outOfScreenY = ((ViewGroup.MarginLayoutParams) mFloatingButton
						.getLayoutParams()).bottomMargin + mFloatingButton.getHeight();

					ViewAnimationUtils
						.liftingFromBottom( mFloatingButton, 0, outOfScreenY, 500, 0 );
				}
			} );

		mFloatingButton.setOnClickListener( new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{

				if(mAnimator != null && !mAnimator.isRunning())
				{
					mAnimator = mAnimator.reverse();
					mAnimator.addListener( new SupportAnimator.AnimatorListener()
					{
						@Override public void onAnimationStart()
						{

						}

						@Override public void onAnimationEnd()
						{
							mAnimator = null;
						}

						@Override public void onAnimationCancel()
						{

						}

						@Override public void onAnimationRepeat()
						{

						}
					} );
				}
				else if(mAnimator != null)
				{
					mAnimator.cancel();
					return;
				}
				else
				{

					final View myView =
						((RevealFrameLayout) mCardsGroup.getChildAt( 0 )).getChildAt( 0 );

					// get the center for the clipping circle
					//int cx = (myView.getLeft() + myView.getRight()) / 2;
					//int cy = (myView.getTop() + myView.getBottom()) / 2;
					int cx = myView.getRight();
					int cy = myView.getBottom();

					// get the final radius for the clipping circle
					float finalRadius = hypo( myView.getWidth(), myView.getHeight() );

					mAnimator =
						ViewAnimationUtils.createCircularReveal( myView, cx, cy, 0, finalRadius );
					mAnimator.addListener( new SupportAnimator.AnimatorListener()
					{
						@Override public void onAnimationStart()
						{
						}

						@Override public void onAnimationEnd()
						{
							Toast.makeText( getApplicationContext(), "Done", Toast.LENGTH_LONG )
								.show();
						}

						@Override public void onAnimationCancel()
						{

						}

						@Override public void onAnimationRepeat()
						{

						}
					} );
				}

				mAnimator.setInterpolator( new AccelerateDecelerateInterpolator() );
				mAnimator.setDuration( 1500 );
				mAnimator.start();
			}
		} );
	}

	static float hypo(int a, int b)
	{
		return (float) Math.sqrt( Math.pow( a, 2 ) + Math.pow( b, 2 ) );
	}

	@Override protected void onStart()
	{
		super.onStart();
		mCardsGroup.setOnScrollListener( new HideExtraOnScroll( mToolbar ) );
	}

	@Override protected void onStop()
	{
		super.onStop();

		// Prevent memory leaks, fuck yeah!
		mCardsGroup.setOnScrollListener( null );
	}

	public static class RecycleAdapter extends RecyclerView.Adapter<CardHolder>
	{

		@Override public CardHolder onCreateViewHolder(ViewGroup group, int i)
		{
			LayoutInflater factory = LayoutInflater.from( group.getContext() );
			return new CardHolder( factory.inflate( R.layout.card_item, group, false ) );
		}

		@Override public void onBindViewHolder(CardHolder cardHolder, int i)
		{
		}

		@Override public int getItemCount()
		{
			return 10;
		}
	}

	public static class CardHolder extends RecyclerView.ViewHolder
	{

		private CardView mCard;

		RevealFrameLayout mReveal;

		public CardHolder(View itemView)
		{
			super( itemView );
			mCard = (CardView) itemView.findViewById( R.id.card );

			mReveal = (RevealFrameLayout) mCard.getParent();
		}
	}


	public static class HideExtraOnScrollHelper
	{
		public final static int UNKNOWN = -1;
		public final static int TOP = 0;
		public final static int BOTTOM = 1;

		int mDraggedAmount;
		int mOldDirection;
		int mDragDirection;

		final int mMinFlingDistance;

		public HideExtraOnScrollHelper(int minFlingDistance)
		{
			mOldDirection = mDragDirection = mDraggedAmount = UNKNOWN;

			mMinFlingDistance = minFlingDistance;
		}

		/**
		 * Checks need to hide extra objects on scroll or not
		 *
		 * @param dy scrolled distance y
		 * @return true if need to hide extra objects on screen
		 */
		public boolean isObjectsShouldBeOutside(int dy)
		{
			boolean needHide = false;
			mDragDirection = dy > 0 ? BOTTOM : TOP;

			if(mDragDirection != mOldDirection)
			{
				mDraggedAmount = 0;
			}

			mDraggedAmount += dy;
			boolean shouldBeOutside = false;

			if(mDragDirection == TOP && Math.abs( mDraggedAmount ) > mMinFlingDistance)
			{
				shouldBeOutside = false;
			}
			else if(mDragDirection == BOTTOM && mDraggedAmount > mMinFlingDistance)
			{
				shouldBeOutside = true;
			}

			if(mOldDirection != mDragDirection)
			{
				mOldDirection = mDragDirection;
			}

			return shouldBeOutside;
		}
	}


	public static class HideExtraOnScroll extends RecyclerView.OnScrollListener
	{

		final static Interpolator ACCELERATE = new AccelerateInterpolator();
		final static Interpolator DECELERATE = new DecelerateInterpolator();

		WeakReference<View> mTarget;
		HideExtraOnScrollHelper mScrollHelper;

		boolean isExtraObjectsOutside;

		public HideExtraOnScroll(View target)
		{
			int minimumFlingVelocity =
				ViewConfiguration.get( target.getContext() ).getScaledMinimumFlingVelocity();

			mScrollHelper = new HideExtraOnScrollHelper( minimumFlingVelocity );
			mTarget = new WeakReference<View>( target );
		}

		@Override public void onScrolled(RecyclerView recyclerView, int dx, int dy)
		{
			super.onScrolled( recyclerView, dx, dy );

			final View target = mTarget.get();

			if(target == null)
			{
				return;
			}

			boolean isObjectsShouldBeOutside = mScrollHelper.isObjectsShouldBeOutside( dy );

			if(!isVisible( target ) && !isObjectsShouldBeOutside)
			{
				show( target );
				isExtraObjectsOutside = false;
			}
			else if(isVisible( target ) && isObjectsShouldBeOutside)
			{
				hide( target, -target.getHeight() );
				isExtraObjectsOutside = true;
			}
		}

		public boolean isVisible(View target)
		{
			return !isExtraObjectsOutside;
		}

		public void hide(final View target, float distance)
		{
			ObjectAnimator animator = ObjectAnimator
				.ofFloat( target, "translationY", ViewHelper.getTranslationY( target ), distance );
			animator.setInterpolator( DECELERATE );
			animator.start();
		}

		public void show(final View target)
		{
			ObjectAnimator animator = ObjectAnimator
				.ofFloat( target, "translationY", ViewHelper.getTranslationY( target ), 0f );
			animator.setInterpolator( ACCELERATE );
			animator.start();
		}
	}

	@Override public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate( R.menu.menu_circularreveal, menu );
		return true;
	}

	@Override public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent = null;

		switch(item.getItemId())
		{
			case R.id.sampl2:
				intent = new Intent( this, CircularReveal1Activity.class );
				break;

			case R.id.sampl3:
				intent = new Intent( this, CircularReveal2Activity.class );
				break;
		}

		startActivity( intent );
		return intent != null;
	}
}