package talex.zsw.baseproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import talex.zsw.baselibrary.widget.OnTapListener;
import talex.zsw.baseproject.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>
{
	private final ArrayList<String> textArrayList;
	private OnTapListener onTapListener;

	public CustomAdapter()
	{
		this.textArrayList = new ArrayList<String>();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
	{
		View v = LayoutInflater.from(viewGroup.getContext())
			.inflate(R.layout.item_rippleview, null);

		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, final int i)
	{
		viewHolder.itemView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (onTapListener != null)
				{
					onTapListener.onTapView(i);
				}
			}
		});
		viewHolder.textView.setText(textArrayList.get(i));
	}

	@Override
	public int getItemCount()
	{
		return textArrayList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder
	{
		TextView textView;

		public ViewHolder(View v)
		{
			super(v);
			textView = (TextView) v.findViewById(R.id.text);
		}
	}

	public void updateList(ArrayList<String> stringArrayList)
	{
		this.textArrayList.clear();
		this.textArrayList.addAll(stringArrayList);
		this.notifyDataSetChanged();
	}

	public void setOnTapListener(OnTapListener onTapListener)
	{
		this.onTapListener = onTapListener;
	}
}
