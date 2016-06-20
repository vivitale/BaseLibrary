package talex.zsw.baseproject.adapter;

import android.content.Context;

import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGARecyclerViewAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGAViewHolderHelper;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.test.TestDto;

/**
 * 项目包名: talex.zsw.baseproject.adapter
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/5/26 22:15 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TestAdapter extends BGARecyclerViewAdapter<TestDto>
{
	public TestAdapter(Context context)
	{
		super(context, R.layout.item_test);
	}

	@Override
	protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, TestDto model)
	{
		viewHolderHelper.setText(R.id.iTextView, model.getTitle());
	}
}
