package talex.zsw.baseproject.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import talex.zsw.baselibrary.widget.MaterialFavoriteButton;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: MaterialFavoriteButton的基本使用方法
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/30 15:04 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MaterialFavoriteButtonActivity extends AppCompatActivity
{
	private TextView niceCounter;
	private int niceCounterValue = 37;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materialfavorite);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		//in the toolbar
		MaterialFavoriteButton toolbarFavorite = new MaterialFavoriteButton.Builder(this) //
			.favorite(true)
			.color(MaterialFavoriteButton.STYLE_WHITE)
			.type(MaterialFavoriteButton.STYLE_HEART)
			.rotationDuration(400)
			.create();
		toolbar.addView(toolbarFavorite);
		toolbarFavorite.setOnFavoriteChangeListener(
			new MaterialFavoriteButton.OnFavoriteChangeListener()
			{
				@Override
				public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite)
				{
					Snackbar.make(buttonView, getString(R.string.toolbar_favorite_snack) + favorite,
						Snackbar.LENGTH_SHORT).show();
				}
			});

		//nice cardview
		niceCounter = (TextView) findViewById(R.id.counter_value);
		niceCounter.setText(String.valueOf(niceCounterValue));
		MaterialFavoriteButton materialFavoriteButtonNice =
			(MaterialFavoriteButton) findViewById(R.id.favorite_nice);
		materialFavoriteButtonNice.setFavorite(true, false);
		materialFavoriteButtonNice.setOnFavoriteChangeListener(
			new MaterialFavoriteButton.OnFavoriteChangeListener()
			{
				@Override
				public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite)
				{
					if (favorite)
					{
						niceCounterValue++;
					}
					else
					{
						niceCounterValue--;
					}
				}
			});
		materialFavoriteButtonNice.setOnFavoriteAnimationEndListener(
			new MaterialFavoriteButton.OnFavoriteAnimationEndListener()
			{
				@Override
				public void onAnimationEnd(MaterialFavoriteButton buttonView, boolean favorite)
				{
					niceCounter.setText(String.valueOf(niceCounterValue));
				}
			});
	}
}
