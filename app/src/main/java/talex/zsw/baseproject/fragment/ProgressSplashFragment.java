package talex.zsw.baseproject.fragment;

import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseFragment;
import talex.zsw.baselibrary.custom.progress.RefreshProgress;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/24 14:25 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProgressSplashFragment extends BaseFragment
{
	@Bind(R.id.refresh) RefreshProgress refresh;

	@Override protected void initArgs(Bundle bundle)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.fragment_splashprogress);
		ButterKnife.bind(this, mView);
	}

	@Override protected void initData()
	{

	}

	@Override public void onDestroyView()
	{
		super.onDestroyView();
		ButterKnife.unbind(this);
	}
}
