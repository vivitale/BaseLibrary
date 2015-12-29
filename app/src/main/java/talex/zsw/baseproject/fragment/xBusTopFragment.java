package talex.zsw.baseproject.fragment;

import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import talex.zsw.baselibrary.BaseFragment;
import talex.zsw.baselibrary.util.klog.KLog;
import talex.zsw.baselibrary.xbus.Bus;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.test.Demo;

/**
 * 项目名称: Rxjava
 * 作用: 在这边来send一个事件
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/10 15:52 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class xBusTopFragment extends BaseFragment
{
	@Bind(R.id.btn_demo_rxbus_tap) Button btnDemoRxbusTap;

	@Override protected void initArgs(Bundle bundle)
	{
	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.fragment_xbus_top);
		ButterKnife.bind(this, mView);
	}

	@Override protected void initData()
	{

	}

	@OnClick(R.id.btn_demo_rxbus_tap)
	public void onTapButtonClicked()
	{
		KLog.d();
		Demo data = new Demo();
		data.setContent("测试");
		Bus.getDefault().post(data);
		//		RxJava中使用
		//		Observable<TapEvent> event = Observable.just(new TapEvent().setTitle("测试标题").setContent("内容"));
		//		Bus.getDefault().post(event);
	}

	@Override public void onDestroyView()
	{
		super.onDestroyView();
		ButterKnife.unbind(this);
	}
}
