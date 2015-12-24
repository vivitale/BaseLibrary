package talex.zsw.baseproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-23-0023 15:03 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ExpandableAdapter extends BaseAdapter
{
	private List<String> lists = new ArrayList<String>();
	private Context context;

	public ExpandableAdapter(Context context)
	{
		super();
		this.context = context;
	}

	public void setContentArray(List<String> lists, boolean refresh)
	{
		if (refresh)
		{
			this.lists.clear();
		}
		this.lists = lists;
		notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		return lists.size();
	}

	@Override
	public Object getItem(int position)
	{
		return lists.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
		if (convertView == null)
		{
			convertView = inflate(R.layout.item_expandable);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		String resource = lists.get(position);
		holder.showResource(resource);
		return convertView;
	}

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
	}

	protected View inflate(int resource)
	{
		return LayoutInflater.from(context).inflate(resource, null);
	}

	class ViewHolder
	{
		public TextView header_text;
		public Button button;
		public EditText text;

		public ViewHolder(View convertView)
		{
			header_text = (TextView) convertView.findViewById(R.id.header_text);
			button = (Button) convertView.findViewById(R.id.button);
			text = (EditText) convertView.findViewById(R.id.text);
		}

		public void showResource(final String resource)
		{
			header_text.setText(resource + "   " + resource);
			text.setText(resource);
			button.setText(resource + "==");
			button.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					Toast.makeText(context, "点击了resource为" + resource,
						Toast.LENGTH_LONG).show();
				}
			});
		}
	}
}
