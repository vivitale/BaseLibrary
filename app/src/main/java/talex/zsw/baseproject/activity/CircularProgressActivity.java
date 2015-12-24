package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.CircularProgressDrawable;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-11-0011 16:16 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CircularProgressActivity extends BaseAppCompatActivity
{
	@Bind(R.id.image) ImageView image;

	private CircularProgressDrawable drawable;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_circularprogress);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		drawable = new CircularProgressDrawable(Color.BLUE, 6);
		image.setImageDrawable(drawable);
		drawable.start();
	}
}
