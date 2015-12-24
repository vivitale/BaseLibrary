package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.DynamicHeightImageView;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-12-0012 17:43 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DynamicHeightImageViewActivity extends BaseAppCompatActivity
{
	@Bind(R.id.image1) DynamicHeightImageView image1;
	@Bind(R.id.image2) DynamicHeightImageView image2;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_dynamicheightimageview);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		image1.setHeightRatio(0.5);
		ImageLoader.getInstance()
			.displayImage("http://img4.imgtn.bdimg.com/it/u=4063725368,1081438728&fm=21&gp=0.jpg",
				image1);
		ImageLoader.getInstance()
			.displayImage("http://img4.imgtn.bdimg.com/it/u=4063725368,1081438728&fm=21&gp=0.jpg",
				image2);
	}
}
