package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.CircleImageView;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: CircleImageView测试类
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-09-0009 16:35 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CircleImageViewActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mImage) CircleImageView mImage;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_circleimageview);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		ImageLoader.getInstance()
			.displayImage("http://img5.imgtn.bdimg.com/it/u=632680172,595028883&fm=23&gp=0.jpg",
				mImage);
	}
}
