package talex.zsw.baseproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import talex.zsw.baselibrary.view.AndroidAnimations.library.Techniques;
import talex.zsw.baselibrary.view.AndroidAnimations.library.YoYo;
import talex.zsw.baselibrary.view.SwipeLayout.SimpleSwipeListener;
import talex.zsw.baselibrary.view.SwipeLayout.SwipeLayout;
import talex.zsw.baselibrary.view.SwipeLayout.adapters.BaseSwipeAdapter;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: SwipeLayout使用的Adapter
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/12/4 13:06 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SwipeAdapter extends BaseSwipeAdapter
{

	private Context mContext;

	public SwipeAdapter(Context mContext)
	{
		this.mContext = mContext;
	}

	@Override
	public int getSwipeLayoutResourceId(int position)
	{
		return R.id.swipe;
	}

	@Override
	public View generateView(int position, ViewGroup parent)
	{
		View v = LayoutInflater.from(mContext).inflate(R.layout.item_swipe, null);
		SwipeLayout swipeLayout = (SwipeLayout) v.findViewById(getSwipeLayoutResourceId(position));
		swipeLayout.addSwipeListener(new SimpleSwipeListener()
		{
			@Override
			public void onOpen(SwipeLayout layout)
			{
				YoYo.with(Techniques.Tada).duration(500).delay(100)
					.playOn(layout.findViewById(R.id.trash));
			}
		});
		swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener()
		{
			@Override
			public void onDoubleClick(SwipeLayout layout, boolean surface)
			{
				Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
			}
		});
		v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Toast.makeText(mContext, "click delete", Toast.LENGTH_SHORT).show();
			}
		});
		return v;
	}

	@Override
	public void fillValues(int position, View convertView)
	{
		TextView t = (TextView) convertView.findViewById(R.id.position);
		t.setText((position + 1) + ".");
	}

	@Override
	public int getCount()
	{
		return 50;
	}

	@Override
	public Object getItem(int position)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}
}
