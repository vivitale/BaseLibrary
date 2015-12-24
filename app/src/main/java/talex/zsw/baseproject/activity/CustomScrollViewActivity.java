package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.util.FakeX509TrustManager;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 测试CustomScrollView的效果
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-09-0009 17:33 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CustomScrollViewActivity extends BaseAppCompatActivity
{
	@Bind(R.id.mImage) ImageView mImage;
	@Bind(R.id.mFrameLayout) FrameLayout mFrameLayout;

	private double level = 1.5;
	private double nLenStart = 0;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_customscrollview);
		ButterKnife.bind(this);
	}

	@Override protected void initData()
	{
		FakeX509TrustManager.allowAllSSL();
		setLevel();
		String url =
			"http://img1.imgtn.bdimg.com/it/u=3229727480,642457655&fm=21&gp=0.jpg";
		ImageLoader.getInstance().displayImage(url, mImage);
	}

	@Override public boolean dispatchTouchEvent(MotionEvent ev)
	{
		if (ev.getPointerCount() > 1)
		{
			onTouchEvent(ev);
			return true;
		}
		else
		{
			return super.dispatchTouchEvent(ev);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int nCnt = event.getPointerCount();
		int n = event.getAction();
		if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN &&
			2 == nCnt)//<span style="color:#ff0000;">2表示两个手指</span>
		{
			for (int i = 0; i < nCnt; i++)
			{
				float x = event.getX(i);
				float y = event.getY(i);
				Point pt = new Point((int) x, (int) y);
			}
			int xlen = Math.abs((int) event.getX(0) - (int) event.getX(1));
			int ylen = Math.abs((int) event.getY(0) - (int) event.getY(1));
			nLenStart = Math.sqrt((double) xlen * xlen + (double) ylen * ylen);
		}
		else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP &&
			2 == nCnt)
		{
			for (int i = 0; i < nCnt; i++)
			{
				float x = event.getX(i);
				float y = event.getY(i);
				Point pt = new Point((int) x, (int) y);
			}
			int xlen = Math.abs((int) event.getX(0) - (int) event.getX(1));
			int ylen = Math.abs((int) event.getY(0) - (int) event.getY(1));
			double nLenEnd = Math.sqrt((double) xlen * xlen + (double) ylen * ylen);
			if (nLenEnd > nLenStart)//通过两个手指开始距离和结束距离，来判断放大缩小
			{
				//放大
				if (level < 2.5)
				{
					level = level + 0.5;
					setLevel();
				}
			}
			else
			{
				//缩小
				if (level > 1)
				{
					level = level - 0.5;
					setLevel();
				}
			}
		}
		return super.onTouchEvent(event);
	}

	private void setLevel()
	{
		ViewGroup.LayoutParams params = mImage.getLayoutParams();
		params.width = (int) (1054 * level);
		params.height = (int) (612 * level);
		mImage.setLayoutParams(params);

		mFrameLayout.removeViews(1, mFrameLayout.getChildCount() - 1);
		ImageView imageView = new ImageView(CustomScrollViewActivity.this);
		imageView.setImageResource(android.R.drawable.arrow_down_float);
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
			FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
		layoutParams.leftMargin = (int) (366 * level);
		layoutParams.topMargin = (int) (392 * level);
		imageView.setLayoutParams(layoutParams);
		mFrameLayout.addView(imageView);
	}
}
