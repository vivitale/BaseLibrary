package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.LikeButton.LikeButton;
import talex.zsw.baselibrary.view.LikeButton.OnLikeListener;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/1/5 10:09 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LikeButtonActivity extends BaseAppCompatActivity implements OnLikeListener
{
	@Bind(R.id.star_button) LikeButton starButton;
	@Bind(R.id.heart_button) LikeButton heartButton;
	@Bind(R.id.thumb_button) LikeButton thumbButton;
	@Bind(R.id.smile_button) LikeButton smileButton;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView( R.layout.activity_likebutton );
		ButterKnife.bind( this );

		starButton.setOnLikeListener( this );
		heartButton.setOnLikeListener( this );
		smileButton.setOnLikeListener( this );
		thumbButton.setOnLikeListener( this );

		thumbButton.setLiked( true );

		usingCustomIcons();
	}

	@Override protected void initData()
	{

	}

	public void usingCustomIcons()
	{
		//shown when the button is in its default state or when unLiked.
		smileButton.setUnlikeDrawableRes( R.mipmap.icon_pay_failure );
		//shown when the button is liked!
		smileButton.setLikeDrawableRes( R.mipmap.icon_pay_success );
	}

	@Override public void liked()
	{
		Toast.makeText( this, "Liked!", Toast.LENGTH_SHORT ).show();
	}

	@Override public void unLiked()
	{
		Toast.makeText( this, "Disliked!", Toast.LENGTH_SHORT ).show();
	}
}
