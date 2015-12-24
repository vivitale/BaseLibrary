package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.AnimateCheckBox;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.test.Demo;

/**
 * 项目名称: BaseProject
 * 作用: 带有动画效果的CheckBox 
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-23-0023 14:05 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AnimateCheckBoxActivity extends BaseAppCompatActivity
{
	private List<Demo> dataList = new ArrayList<>();
	private Set<Demo> checkedSet = new HashSet<>();

	@Override protected void initArgs(Intent intent)
	{
		setContentView(R.layout.activity_animatecheckbox);
		bindDatas();
		bindViews();
	}

	@Override protected void initView(Bundle bundle)
	{

	}

	@Override protected void initData()
	{

	}

	private void bindDatas()
	{
		for (int i = 0; i < 100; i++)
		{
			Demo demo = new Demo();
			demo.setContent("this is a simple item : " + i);
			dataList.add(demo);
		}
	}

	private void bindViews()
	{
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new BaseAdapter()
		{
			@Override public int getCount()
			{
				return dataList.size();
			}

			@Override public Object getItem(int position)
			{
				return null;
			}

			@Override public long getItemId(int position)
			{
				return 0;
			}

			@Override public View getView(int position, View convertView, ViewGroup parent)
			{
				if (convertView == null)
				{
					convertView = LayoutInflater.from(parent.getContext())
						.inflate(R.layout.item_animatecheckbox, parent, false);
				}

				TextView text = (TextView) convertView.findViewById(R.id.text);
				final AnimateCheckBox checkBox =
					(AnimateCheckBox) convertView.findViewById(R.id.checkbox);

				final Demo item = dataList.get(position);
				text.setText(item.getContent());
				if (checkedSet.contains(item))
				{
					checkBox.setChecked(true);
				}
				else
				{
					//checkBox.setChecked(false); //has animation
					checkBox.setUncheckStatus();
				}
				checkBox.setOnCheckedChangeListener(new AnimateCheckBox.OnCheckedChangeListener()
				{
					@Override public void onCheckedChanged(View buttonView, boolean isChecked)
					{
						if (isChecked)
						{
							checkedSet.add(item);
						}
						else
						{
							checkedSet.remove(item);
						}
					}
				});
				return convertView;
			}
		});
	}
}
