package talex.zsw.baseproject.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import talex.zsw.baselibrary.BaseFragment;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 百分比布局
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/1/14 15:50 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("ValidFragment") public class PercentLayoutFragment extends BaseFragment
{
	private int position = -1;

	public PercentLayoutFragment(int position)
	{
		this.position = position;
	}

	@Override protected void initArgs(Bundle bundle)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		switch(position)
		{
			case 0:
				setContentView( R.layout.view1 );
				break;
			case 1:
				setContentView( R.layout.view2 );
				break;
			case 2:
				setContentView( R.layout.view3 );
				break;
			case 3:
				setContentView( R.layout.view4 );
				break;
			case 4:
				setContentView( R.layout.view5 );
				break;
		}
	}

	@Override protected void initData()
	{

	}
}
