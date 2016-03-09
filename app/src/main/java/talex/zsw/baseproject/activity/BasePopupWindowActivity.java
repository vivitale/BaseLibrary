package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.BasePopupWindow.interpolator.CustomInterpolatorFactory;
import talex.zsw.baselibrary.view.BasePopupWindow.utils.ToastUtils;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.popupwindow.CommentPopup;
import talex.zsw.baseproject.popupwindow.CustomInterpolatorPopup;
import talex.zsw.baseproject.popupwindow.DialogPopup;
import talex.zsw.baseproject.popupwindow.InputPopup;
import talex.zsw.baseproject.popupwindow.ListPopup;
import talex.zsw.baseproject.popupwindow.MenuPopup;
import talex.zsw.baseproject.popupwindow.ScalePopup;
import talex.zsw.baseproject.popupwindow.SlideFromBottomPopup;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/2/26 15:36 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BasePopupWindowActivity extends BaseAppCompatActivity implements View.OnClickListener
{

	@Bind(R.id.Comment) TextView mComment;
	@Bind(R.id.Dialog) TextView mDialog;
	@Bind(R.id.Input) TextView mInput;
	@Bind(R.id.List) TextView mList;
	@Bind(R.id.Menu) TextView mMenu;
	@Bind(R.id.Scale) TextView mScale;
	@Bind(R.id.SlideFrom) TextView mSlideFrom;
	@Bind(R.id.Test) TextView mTest;
	@Bind(R.id.jelly) Button jelly;
	@Bind(R.id.anti) Button anti;
	@Bind(R.id.anti2) Button anti2;
	@Bind(R.id.overshoot) Button overshoot;
	@Bind(R.id.spring) Button spring;

	public static final int TAG_CREATE = 0x01;
	public static final int TAG_DELETE = 0x02;
	public static final int TAG_MODIFY = 0x03;

	private CommentPopup mCommentPopup;
	private DialogPopup mDialogPopup;
	private InputPopup mInputPopup;
	private ListPopup mListPopup;
	private MenuPopup mMenuPopup;
	private ScalePopup mScalePopup;
	private SlideFromBottomPopup mSlideFromBottomPopup;
	private CustomInterpolatorPopup mInterpolatorPopup;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView( R.layout.activity_popup );
		ButterKnife.bind( this );

		mComment.setOnClickListener( this );
		mDialog.setOnClickListener( this );
		mInput.setOnClickListener( this );
		mList.setOnClickListener( this );
		mMenu.setOnClickListener( this );
		mScale.setOnClickListener( this );
		mSlideFrom.setOnClickListener( this );
		mTest.setOnClickListener( this );
		jelly.setOnClickListener( this );
		anti.setOnClickListener( this );
		anti2.setOnClickListener( this );
		overshoot.setOnClickListener( this );
		spring.setOnClickListener( this );

		mInterpolatorPopup=new CustomInterpolatorPopup(BasePopupWindowActivity.this);
	}

	@Override protected void initData()
	{

	}

	@Override public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.Comment:
				mCommentPopup = new CommentPopup( this );
				mCommentPopup
					.setOnCommentPopupClickListener( new CommentPopup.OnCommentPopupClickListener()
					{
						@Override public void onLikeClick(View v, TextView likeText)
						{
							if(v.getTag() == null)
							{
								v.setTag( 1 );
								likeText.setText( "取消" );
							}
							else
							{
								switch((int) v.getTag())
								{
									case 0:
										v.setTag( 1 );
										likeText.setText( "取消" );
										break;
									case 1:
										v.setTag( 0 );
										likeText.setText( "赞  " );
										break;
								}
							}
						}

						@Override public void onCommentClick(View v)
						{
							showToast( "评论" );
						}
					} );
				mCommentPopup.showPopupWindow( v );
				break;
			case R.id.Dialog:
				mDialogPopup = new DialogPopup( BasePopupWindowActivity.this );
				mDialogPopup.showPopupWindow();
				break;
			case R.id.Input:
				mInputPopup = new InputPopup( BasePopupWindowActivity.this );
				mInputPopup.showPopupWindow();
				break;
			case R.id.List:
				ListPopup.Builder builder = new ListPopup.Builder( BasePopupWindowActivity.this );
				builder.addItem( TAG_CREATE, "Create-01" );
				builder.addItem( TAG_MODIFY, "Modify-01" );
				builder.addItem( TAG_CREATE, "Create-02" );
				builder.addItem( TAG_DELETE, "Delete-01" );
				builder.addItem( TAG_MODIFY, "Modify-02" );
				builder.addItem( TAG_CREATE, "Create-03" );
				builder.addItem( TAG_DELETE, "Delete-02" );
				builder.addItem( TAG_MODIFY, "Modify-03" );
				builder.addItem( TAG_DELETE, "Delete-03" );
				builder.addItem( TAG_MODIFY, "Modify-04" );
				builder.addItem( TAG_DELETE, "Delete-04" );
				builder.addItem( TAG_CREATE, "Create-04" );
				mListPopup = builder.build();

				mListPopup
					.setOnListPopupItemClickListener( new ListPopup.OnListPopupItemClickListener()
					{
						@Override public void onItemClick(int what)
						{
							switch(what)
							{
								case TAG_CREATE:
									ToastUtils.ToastMessage( BasePopupWindowActivity.this,
										"click create" );
									break;
								case TAG_DELETE:
									ToastUtils.ToastMessage( BasePopupWindowActivity.this,
										"click delete" );
									break;
								case TAG_MODIFY:
									ToastUtils.ToastMessage( BasePopupWindowActivity.this,
										"click modify" );
									break;
								default:
									break;
							}
						}
					} );
				break;
			case R.id.Menu:
				mMenuPopup = new MenuPopup( BasePopupWindowActivity.this );
				mMenuPopup.showPopupWindow( v );
				break;
			case R.id.Scale:
				mScalePopup = new ScalePopup( BasePopupWindowActivity.this );
				mScalePopup.showPopupWindow();
				break;
			case R.id.SlideFrom:
				mSlideFromBottomPopup = new SlideFromBottomPopup( BasePopupWindowActivity.this );
				mSlideFromBottomPopup.showPopupWindow();
				break;

			case R.id.jelly:
				Animation scaleAnimation =
					new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				scaleAnimation.setDuration(3000);
				scaleAnimation.setInterpolator(CustomInterpolatorFactory.getJellyInterpolator());
				scaleAnimation.setFillEnabled(true);
				scaleAnimation.setFillAfter(true);
				mInterpolatorPopup.setCustomAnimation(scaleAnimation);
				mInterpolatorPopup.showPopupWindow();
				break;
			case R.id.anti:
				Animation rotateAnima =
					new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
				rotateAnima.setDuration(2500);
				rotateAnima.setInterpolator( CustomInterpolatorFactory.getAnticipateInterpolator());
				rotateAnima.setFillEnabled(true);
				rotateAnima.setFillAfter(true);
				mInterpolatorPopup.setCustomAnimation(rotateAnima);
				mInterpolatorPopup.showPopupWindow();
				break;
			case R.id.anti2:
				Animation rotateAnima2 =
					new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
				rotateAnima2.setDuration(2500);
				rotateAnima2.setInterpolator(CustomInterpolatorFactory.getAnticipateOverShootInterpolator());
				rotateAnima2.setFillEnabled(true);
				rotateAnima2.setFillAfter(true);
				mInterpolatorPopup.setCustomAnimation(rotateAnima2);
				mInterpolatorPopup.showPopupWindow();
				break;
			case R.id.spring:
				Animation scaleAnimation2 =
					new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				scaleAnimation2.setDuration(2500);
				scaleAnimation2.setInterpolator(CustomInterpolatorFactory.getSpringInterPolator());
				scaleAnimation2.setFillEnabled(true);
				scaleAnimation2.setFillAfter(true);
				mInterpolatorPopup.setCustomAnimation(scaleAnimation2);
				mInterpolatorPopup.showPopupWindow();
				break;
			case R.id.overshoot:
				Animation translateAnimation = new TranslateAnimation(0, 0, 250*2, 0);
				translateAnimation.setDuration(2500);
				translateAnimation.setFillEnabled(true);
				translateAnimation.setFillAfter(true);
				translateAnimation.setInterpolator(CustomInterpolatorFactory.getOverShootInterpolator());
				mInterpolatorPopup.setCustomAnimation(translateAnimation);
				mInterpolatorPopup.showPopupWindow();
				break;
		}
	}
}
