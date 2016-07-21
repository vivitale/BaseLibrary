package talex.zsw.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import talex.zsw.baselibrary.view.MarqueeLayout.MarqueeLayout;
import talex.zsw.baselibrary.view.MarqueeLayout.MarqueeLayoutAdapter;
import talex.zsw.baseproject.R;

public class MarqueeLayoutActivity extends AppCompatActivity
{

	private MarqueeLayout mMarqueeLayout;
	private MarqueeLayout mMarqueeLayout1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marquee_layout);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mMarqueeLayout = (MarqueeLayout) findViewById(R.id.marquee_layout);
		final List<String> list = new ArrayList<>();
		list.add("我听见了你的声音 也藏着颗不敢见的心");
		list.add("我们的爱情到这刚刚好 剩不多也不少 还能忘掉");
		list.add("像海浪撞过了山丘以后还能撑多久 他可能只为你赞美一句后往回流");
		list.add("少了有点不甘 但多了太烦");
		MarqueeLayoutAdapter<String> adapter = new MarqueeLayoutAdapter<>();
		adapter.setCustomView(mMarqueeLayout, R.layout.marquee_layout_simple_text, list,
			new MarqueeLayoutAdapter.InitViewCallBack<String>()
			{
				@Override
				public void init(View view, int position, String item)
				{
					((TextView) view).setText(item);
				}
			});
		MarqueeLayout.ScrollCallBack scrollCallBack =new MarqueeLayout.ScrollCallBack()
		{
			@Override public void scrollTo(int position)
			{
				Log.d("TAG", "===================  " + position + "  =====================");
			}
		};
		mMarqueeLayout.setScrollCallBack(scrollCallBack);
		mMarqueeLayout.setAdapter(adapter);
		mMarqueeLayout.start();


		mMarqueeLayout1 = (MarqueeLayout) findViewById(R.id.marquee_layout1);
		final List<String> imgs = new ArrayList<>();
		imgs.add("http://img3.imgtn.bdimg.com/it/u=936722914,2010466745&fm=11&gp=0.jpg");
		imgs.add("http://img5.imgtn.bdimg.com/it/u=793061750,504065085&fm=11&gp=0.jpg");
		imgs.add("http://img5.imgtn.bdimg.com/it/u=506823331,38014690&fm=11&gp=0.jpg");
		imgs.add(
			"http://h.hiphotos.baidu.com/baike/pic/item/2fdda3cc7cd98d10e6a5b4aa273fb80e7bec903c.jpg");
		MarqueeLayoutAdapter<String> adapter1 = new MarqueeLayoutAdapter<>();
		adapter1.setCustomView(mMarqueeLayout, R.layout.marquee_layout_simple_image, imgs,
			new MarqueeLayoutAdapter.InitViewCallBack<String>()
			{
				@Override
				public void init(View view, int position, String item)
				{
//					Glide.with(view.getContext()).load(item).into((ImageView) view);
					ImageLoader.getInstance().displayImage(item,(ImageView) view);
				}
			});
		mMarqueeLayout1.setAdapter(adapter1);
		mMarqueeLayout1.start();
	}
}
