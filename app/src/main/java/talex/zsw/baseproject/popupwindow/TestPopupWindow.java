package talex.zsw.baseproject.popupwindow;

import android.app.Activity;
import android.view.View;

import talex.zsw.baselibrary.view.BasePopupWindow.BasePopupWindow;

/**
 * 项目名称: BaseProject
 * 作用: TODO (用一句话描述该文件做什么) 
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/2/26 14:45 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TestPopupWindow extends BasePopupWindow implements View.OnClickListener
{
	private View popupView;

	public TestPopupWindow(Activity context)
	{
		super( context );
	}

	@Override protected View getClickToDismissView()
	{
		return null;
	}

	@Override public View getPopupView()
	{
		return null;
	}

	@Override public View getAnimaView()
	{
		return null;
	}

	@Override public void onClick(View v)
	{

	}
}
