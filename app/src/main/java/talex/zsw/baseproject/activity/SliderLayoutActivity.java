package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.AndroidAnimations.library.Techniques;
import talex.zsw.baselibrary.view.SliderLayout.Animations.BaseAnimationWithYoYo;
import talex.zsw.baselibrary.view.SliderLayout.Indicators.PagerIndicator;
import talex.zsw.baselibrary.view.SliderLayout.SliderLayout;
import talex.zsw.baselibrary.view.SliderLayout.SliderTypes.BaseSliderView;
import talex.zsw.baselibrary.view.SliderLayout.SliderTypes.TextSliderView;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 轮播
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/11/24 11:16 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SliderLayoutActivity extends BaseAppCompatActivity
	implements BaseSliderView.OnSliderClickListener
{
	@Bind(R.id.mSlider) SliderLayout mSlider;
	@Bind(R.id.mPagerIndicator) PagerIndicator mPagerIndicator;

	private List<String> urls = new ArrayList<>();

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_slider);
		ButterKnife.bind(this);

		mSlider.setCustomIndicator(mPagerIndicator);
		mSlider.setPresetTransformer(SliderLayout.Transformer.Default);
//		mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
//		mSlider.setCustomAnimation(new DescriptionAnimation());
		mSlider.setCustomAnimation(new BaseAnimationWithYoYo(Techniques.FadeInLeft,Techniques.FadeOutRight));//
	}

	@Override protected void initData()
	{
		urls.add("http://img1.imgtn.bdimg.com/it/u=132975978,798355267&fm=23&gp=0.jpg");
		urls.add("http://img5.imgtn.bdimg.com/it/u=3684571077,424122074&fm=23&gp=0.jpg");
		urls.add("http://img4.imgtn.bdimg.com/it/u=131072171,3128643167&fm=23&gp=0.jpg");
		urls.add("http://img1.imgtn.bdimg.com/it/u=416840079,518385556&fm=23&gp=0.jpg");
		urls.add("http://img0.imgtn.bdimg.com/it/u=3595702563,2722075171&fm=23&gp=0.jpg");
		initBanner();
	}

	private void initBanner()
	{
		mSlider.removeAllSliders();
		for (String url : urls)
		{
			TextSliderView textSliderView = new TextSliderView(SliderLayoutActivity.this);
			textSliderView
				.description("说明文本")
				.image(url)//支持URL,RES,本地文件
				.setScaleType(BaseSliderView.ScaleType.Fit)
				.setOnSliderClickListener(this)
				.error(R.mipmap.icon)
				.empty(R.mipmap.icon)
				.bundle(new Bundle())//添加数据
				.errorDisappear(true);//出错的时候是否隐藏

			mSlider.addSlider(textSliderView);
		}
		mSlider.setCurrentPosition(0, true);
		mSlider.startAutoCycle();
	}

	@Override public void onSliderClick(BaseSliderView slider)
	{

	}
}
