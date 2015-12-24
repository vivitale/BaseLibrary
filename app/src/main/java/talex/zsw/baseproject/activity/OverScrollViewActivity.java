package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.OverScrollView;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-16-0016 10:31 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class OverScrollViewActivity extends BaseAppCompatActivity
	implements OverScrollView.OverScrollListener, OverScrollView.OverScrollTinyListener
{
	@Bind(R.id.mOverScrollView) OverScrollView mOverScrollView;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_overscrollview);
		ButterKnife.bind(this);
		mOverScrollView.setOverScrollListener(this);
	}

	@Override protected void initData()
	{

	}

	@Override public void headerScroll()
	{
		// 处理下拉超过一定临界值时 的回调
		Toast.makeText(getApplicationContext(), "开始刷新", Toast.LENGTH_SHORT).show();
	}

	@Override public void footerScroll()
	{
		// 处理上拉超过一定临界值时 的回调
		Toast.makeText(getApplicationContext(), "开始加载", Toast.LENGTH_SHORT).show();
	}

	/**
	 * 滚动距离
	 *
	 * @param tinyDistance  当前滚动的细小距离
	 * @param totalDistance 滚动的总距离
	 */
	@Override public void scrollDistance(int tinyDistance, int totalDistance)
	{

	}

	@Override public void scrollLoosen()
	{
		//滚动松开
	}
}
