package talex.zsw.baseproject.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseFragment;
import talex.zsw.baselibrary.util.klog.KLog;
import talex.zsw.baselibrary.xbus.Bus;
import talex.zsw.baselibrary.xbus.annotation.BusReceiver;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.test.Demo;

/**
 * 项目名称: Rxjava
 * 作用: 在这个Fragment来获取事件
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/10 15:52 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class xBusBottomFragment extends BaseFragment
{
	@Bind(R.id.demo_rxbus_tap_count) TextView demoRxbusTapCount;
	@Bind(R.id.demo_rxbus_tap_txt) TextView demoRxbusTapTxt;

	@Override protected void initArgs(Bundle bundle)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.fragment_xbus_bottom);
		ButterKnife.bind(this, mView);

		Bus.getDefault().register(this);
	}

	@Override protected void initData()
	{

	}

	@Override public void onDestroyView()
	{
		super.onDestroyView();
		ButterKnife.unbind(this);
		Bus.getDefault().unregister(this);
	}

	@BusReceiver(mode = Bus.EventMode.Main)
	public void senderEvent(final Demo event)
	{
		KLog.e(event.toString());
		showTapText();
	}

//	RxJava中的使用方法
//	@BusReceiver(mode = Bus.EventMode.Main)
//	public void senderEvent(final Observable<TapEvent> event)
//	{
//		event.subscribeOn(Schedulers.io())
//			.observeOn(AndroidSchedulers.mainThread()).map(
//			tapEvent -> tapEvent.toString()).subscribe(s -> {
//				KLog.e(s);
//			});
//		showTapText();
//	}

	// -----------------------------------------------------------------------------------
	// Helper to show the text via an animation

	@SuppressLint("NewApi") private void showTapText()
	{
		demoRxbusTapTxt.setVisibility(View.VISIBLE);
		demoRxbusTapTxt.setAlpha(1f);
		ViewCompat.animate(demoRxbusTapTxt).alphaBy(-1f).setDuration(400);
	}

	@SuppressLint("NewApi") private void showTapCount(int size)
	{
		demoRxbusTapCount.setText(String.valueOf(size));
		demoRxbusTapCount.setVisibility(View.VISIBLE);
		demoRxbusTapCount.setScaleX(1f);
		demoRxbusTapCount.setScaleY(1f);
		ViewCompat.animate(demoRxbusTapCount)
			.scaleXBy(-1f)
			.scaleYBy(-1f)
			.setDuration(800)
			.setStartDelay(100);
	}
}
