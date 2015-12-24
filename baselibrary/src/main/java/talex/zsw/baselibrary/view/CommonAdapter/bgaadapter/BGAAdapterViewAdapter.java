package talex.zsw.baselibrary.view.CommonAdapter.bgaadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/21 上午1:05
 * 描述:AdapterView适配器
 */
public abstract class BGAAdapterViewAdapter<M> extends BaseAdapter
{
	protected final int mItemLayoutId;
	protected Context mContext;
	protected List<M> mDatas;
	protected BGAOnItemChildClickListener mOnItemChildClickListener;
	protected BGAOnItemChildLongClickListener mOnItemChildLongClickListener;

	public BGAAdapterViewAdapter(Context context, int itemLayoutId)
	{
		mContext = context;
		mItemLayoutId = itemLayoutId;
		mDatas = new ArrayList<M>();
	}

	@Override
	public int getCount()
	{
		return mDatas.size();
	}

	@Override
	public M getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final BGAAdapterViewHolder viewHolder = BGAAdapterViewHolder
				.dequeueReusableAdapterViewHolder(mContext, convertView,
						parent, mItemLayoutId);
		viewHolder.getViewHolderHelper().setPosition(position);
		viewHolder.getViewHolderHelper().setOnItemChildClickListener(
				mOnItemChildClickListener);
		viewHolder.getViewHolderHelper().setOnItemChildLongClickListener(
				mOnItemChildLongClickListener);
		setItemChildListener(viewHolder.getViewHolderHelper());

		fillData(viewHolder.getViewHolderHelper(), position, getItem(position));
		return viewHolder.getConvertView();
	}

	/**
	 * 为item的孩子节点设置监听器，并不是每一个数据列表都要为item的子控件添加事件监听器，所以这里采用了空实现，需要设置事件监听器时重写该方法即可
	 * 
	 * @param viewHolderHelper
	 */
	protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper)
	{
	}

	/**
	 * 填充item数据
	 * 
	 * @param viewHolderHelper
	 * @param position
	 * @param model
	 */
	protected abstract void fillData(BGAViewHolderHelper viewHolderHelper,
			int position, M model);

	/**
	 * 设置item中的子控件点击事件监听器
	 * 
	 * @param onItemChildClickListener
	 */
	public void setOnItemChildClickListener(
			BGAOnItemChildClickListener onItemChildClickListener)
	{
		mOnItemChildClickListener = onItemChildClickListener;
	}

	/**
	 * 设置item中的子控件长按事件监听器
	 * 
	 * @param onItemChildLongClickListener
	 */
	public void setOnItemChildLongClickListener(
			BGAOnItemChildLongClickListener onItemChildLongClickListener)
	{
		mOnItemChildLongClickListener = onItemChildLongClickListener;
	}

	/**
	 * 获取数据集合
	 * 
	 * @return
	 */
	public List<M> getDatas()
	{
		return mDatas;
	}

	/**
	 * 在集合头部添加新的数据集合（下拉从服务器获取最新的数据集合，例如新浪微博加载最新的几条微博数据）
	 * 
	 * @param datas
	 */
	public void addNewDatas(List<M> datas)
	{
		if (datas != null)
		{
			mDatas.addAll(0, datas);
			notifyDataSetChanged();
		}
	}

	/**
	 * 在集合尾部添加更多数据集合（上拉从服务器获取更多的数据集合，例如新浪微博列表上拉加载更晚时间发布的微博数据）
	 * 
	 * @param datas
	 */
	public void addMoreDatas(List<M> datas)
	{
		if (datas != null)
		{
			mDatas.addAll(mDatas.size(), datas);
			notifyDataSetChanged();
		}
	}

	/**
	 * 设置全新的数据集合，如果传入null，则清空数据列表（第一次从服务器加载数据，或者下拉刷新当前界面数据表）
	 * 
	 * @param datas
	 */
	public void setDatas(List<M> datas)
	{
		if (datas != null)
		{
			mDatas = datas;
		}
		else
		{
			mDatas.clear();
		}
		notifyDataSetChanged();
	}

	/**
	 * 清空数据列表
	 */
	public void clear()
	{
		mDatas.clear();
		notifyDataSetChanged();
	}

	/**
	 * 删除指定索引数据条目
	 * 
	 * @param position
	 */
	public void removeItem(int position)
	{
		mDatas.remove(position);
		notifyDataSetChanged();
	}

	/**
	 * 删除指定数据条目
	 * 
	 * @param model
	 */
	public void removeItem(M model)
	{
		mDatas.remove(model);
		notifyDataSetChanged();
	}

	/**
	 * 在指定位置添加数据条目
	 * 
	 * @param position
	 * @param model
	 */
	public void addItem(int position, M model)
	{
		mDatas.add(position, model);
		notifyDataSetChanged();
	}

	/**
	 * 在集合头部添加数据条目
	 * 
	 * @param model
	 */
	public void addFirstItem(M model)
	{
		addItem(0, model);
	}

	/**
	 * 在集合末尾添加数据条目
	 * 
	 * @param model
	 */
	public void addLastItem(M model)
	{
		addItem(mDatas.size(), model);
	}

	/**
	 * 替换指定索引的数据条目
	 * 
	 * @param location
	 * @param newModel
	 */
	public void setItem(int location, M newModel)
	{
		mDatas.set(location, newModel);
		notifyDataSetChanged();
	}

	/**
	 * 替换指定数据条目
	 * 
	 * @param oldModel
	 * @param newModel
	 */
	public void setItem(M oldModel, M newModel)
	{
		setItem(mDatas.indexOf(oldModel), newModel);
	}

	/**
	 * 交换两个数据条目的位置
	 * 
	 * @param fromPosition
	 * @param toPosition
	 */
	public void moveItem(int fromPosition, int toPosition)
	{
		Collections.swap(mDatas, fromPosition, toPosition);
		notifyDataSetChanged();
	}
}