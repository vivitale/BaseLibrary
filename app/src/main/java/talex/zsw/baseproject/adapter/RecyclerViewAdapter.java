package talex.zsw.baseproject.adapter;

import android.content.Context;

import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGARecyclerViewAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGAViewHolderHelper;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: RecyclerView的Adapter
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/7 17:55 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RecyclerViewAdapter extends BGARecyclerViewAdapter<String>
{
	public RecyclerViewAdapter(Context context)
	{
		super(context, R.layout.item);
	}

	@Override
	protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, String model)
	{
		viewHolderHelper.setText(R.id.iTitle, model);
		viewHolderHelper.setText(R.id.iContent, "");
	}
}
