package talex.zsw.baseproject.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseFragment;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-23-0023 16:23 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("ValidFragment")
public class PTNWebViewFragment extends BaseFragment
{
	@Bind(R.id.webView) WebView webView;

	private int index;

	public PTNWebViewFragment(int index)
	{
		this.index = index;
	}

	@Override protected void initArgs(Bundle bundle)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.fragment_ptnwebview);
		ButterKnife.bind(this, mView);
	}

	@Override protected void initData()
	{
		String[] urls = getResources().getStringArray(R.array.urls);

		webView.loadUrl(urls[index]);
		webView.setWebChromeClient(new WebChromeClient());
	}

	@Override public void onDestroyView()
	{
		super.onDestroyView();
		ButterKnife.unbind(this);
	}
}
