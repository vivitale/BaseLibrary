package talex.zsw.baseproject.adapter;

import android.content.Context;

import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGAAdapterViewAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGAViewHolderHelper;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/11/24 15:06 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SwitchAnimationAdapter extends BGAAdapterViewAdapter<String>
{
	public SwitchAnimationAdapter(Context context)
	{
		super(context, R.layout.item_switchanimation);
	}

	@Override
	protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, String model)
	{
		viewHolderHelper.setText(R.id.iTvTitle,"标题"+position);
		viewHolderHelper.setText(R.id.iTvContent,"内容区域"+position);
	}
}
