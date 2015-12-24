package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.PullZoomView.PullToZoomScrollViewEx;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 下拉顶部放大的View
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/11/24 10:19 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PullZoomViewActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mScrollView) PullToZoomScrollViewEx mScrollView;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_pullzoomview);
		ButterKnife.bind(this);

		View headView = LayoutInflater.from(this).inflate(R.layout.personal_head_view, null, false);
		View zoomView = LayoutInflater.from(this).inflate(R.layout.personal_zoom_view, null, false);
		View contentView =
			LayoutInflater.from(this).inflate(R.layout.personal_content_view, null, false);
		mScrollView.setHeaderView(headView);
		mScrollView.setZoomView(zoomView);
		mScrollView.setScrollContentView(contentView);

		ImageView imageView = (ImageView) mScrollView.getPullRootView().findViewById(R.id.mIvPhoto);
	}

	@Override protected void initData()
	{
		mScrollView.setParallax(false);
	}
}
