package talex.zsw.baseproject.adapter;

import android.content.Context;

import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGAAdapterViewAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGAViewHolderHelper;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.Vo;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-16-0016 11:45 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CommonAdapter extends BGAAdapterViewAdapter<Vo>
{
	public CommonAdapter(Context context)
	{
		super(context, R.layout.item);
	}

	@Override
	protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, Vo data)
	{
		viewHolderHelper.setText(R.id.iTitle, data.getTitle());
		viewHolderHelper.setText(R.id.iContent, data.getContent());
	}
}
