package talex.zsw.baseproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-11-0011 15:14 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MainAdapter extends BaseAdapter
{
	private List<Vo> list = new ArrayList<Vo>();
	private Context context;
	private List<Integer> colors = new ArrayList<>();

	public MainAdapter(Context context)
	{
		super();
		this.context = context;
		colors.add(Color.parseColor("#9CCAF4"));
		colors.add(Color.parseColor("#9CF4E5"));
		colors.add(Color.parseColor("#B2F79E"));
		colors.add(Color.parseColor("#F1FFA3"));
		colors.add(Color.parseColor("#FFF9A3"));
		colors.add(Color.parseColor("#FFE7A3"));
		colors.add(Color.parseColor("#FFD5A3"));
		colors.add(Color.parseColor("#FFBDA3"));
		colors.add(Color.parseColor("#FAA0A8"));
		colors.add(Color.parseColor("#EF99DA"));
		colors.add(Color.parseColor("#C598ED"));
		colors.add(Color.parseColor("#9998ED"));
	}

	public void setContentArray(List<Vo> newsList, boolean refresh)
	{
		if (refresh)
		{
			this.list.clear();
		}
		this.list = newsList;
		notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		return list.size();
	}

	@Override
	public Object getItem(int position)
	{
		return list.get(position);
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
			convertView = inflate(R.layout.item);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		Vo resource = list.get(position);
		holder.showResource(resource, position);

		return convertView;
	}

	protected View inflate(int resource)
	{
		return LayoutInflater.from(context).inflate(resource, null);
	}

	class ViewHolder
	{
		public LinearLayout layout;
		public TextView title;
		public TextView content;

		public ViewHolder(View convertView)
		{
			this.layout = (LinearLayout) convertView.findViewById(R.id.layout);
			this.title = (TextView) convertView.findViewById(R.id.iTitle);
			this.content = (TextView) convertView.findViewById(R.id.iContent);
		}

		public void showResource(Vo resource, int position)
		{
			title.setText(resource.getTitle());
			content.setText(resource.getContent());
			layout.setBackgroundColor(colors.get(position % colors.size()));
		}
	}
}