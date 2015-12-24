package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.OverScroll.OverScrollDecoratorHelper;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/8 10:07 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class OverScrollActivity extends BaseAppCompatActivity
{
	@Bind(R.id.text) TextView text;
	@Bind(R.id.image) ImageView image;
	@Bind(R.id.chronometer) Chronometer chronometer;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_overscroll);
		ButterKnife.bind(this);

		chronometer.start();
	}

	@Override protected void initData()
	{
		OverScrollDecoratorHelper
			.setUpStaticOverScroll(text, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
		OverScrollDecoratorHelper
			.setUpStaticOverScroll(image, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
		OverScrollDecoratorHelper
			.setUpStaticOverScroll(chronometer, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

		//RecyclerView
		// Horizontal
		//		OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
		// Vertical
		//		OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

		//ListView
		//		OverScrollDecoratorHelper.setUpOverScroll(listView);

		//GridView
		//OverScrollDecoratorHelper.setUpOverScroll(gridView);

		//ScrollView
		//		OverScrollDecoratorHelper.setUpOverScroll(scrollView);

		//HorizontalScrollView
		//		OverScrollDecoratorHelper.setUpOverScroll(horizontalScrollView);
	}
}
