package talex.zsw.baselibrary.view.RecyleView.HeaderAndFooter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGARecyclerViewAdapter;
import talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeToLoadLayout;

/**
 * Created by cundong on 2015/10/22.
 * <p/>
 * RecyclerView设置Header/Footer所用到的工具类
 */
public class RecyclerViewUtils
{

	/**
	 * 设置HeaderView
	 *
	 * @param recyclerView
	 * @param view
	 */
	public static void setHeaderView(RecyclerView recyclerView, View view)
	{
		RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
		if (recyclerView.getWidth() > 0 && recyclerView.getHeight() > 0)
		{
			view.setLayoutParams(
				new ViewGroup.LayoutParams(recyclerView.getWidth(), recyclerView.getHeight()));
		}

		if (outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter))
		{
			return;
		}

		HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter =
			(HeaderAndFooterRecyclerViewAdapter) outerAdapter;
		if (headerAndFooterAdapter.getHeaderViewsCount() == 0)
		{
			headerAndFooterAdapter.addHeaderView(view);
		}
	}

	/**
	 * 设置FooterView
	 *
	 * @param recyclerView
	 * @param view
	 */
	public static void setFooterView(RecyclerView recyclerView, View view)
	{
		RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

		if (outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter))
		{
			return;
		}

		HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter =
			(HeaderAndFooterRecyclerViewAdapter) outerAdapter;
		if (headerAndFooterAdapter.getFooterViewsCount() == 0)
		{
			headerAndFooterAdapter.addFooterView(view);
		}
	}

	/**
	 * 移除FooterView
	 *
	 * @param recyclerView
	 */
	public static void removeFooterView(RecyclerView recyclerView)
	{

		RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

		if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)
		{

			int footerViewCounter =
				((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterViewsCount();
			if (footerViewCounter > 0)
			{
				View footerView =
					((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterView();
				((HeaderAndFooterRecyclerViewAdapter) outerAdapter).removeFooterView(footerView);
			}
		}
	}

	/**
	 * 移除HeaderView
	 *
	 * @param recyclerView
	 */
	public static void removeHeaderView(RecyclerView recyclerView)
	{
		RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
		if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)
		{
			int headerViewCounter =
				((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
			if (headerViewCounter > 0)
			{
				View headerView =
					((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderView();
				((HeaderAndFooterRecyclerViewAdapter) outerAdapter).removeHeaderView(headerView);
			}
		}
	}

	/**
	 * 请使用本方法替代RecyclerView.ViewHolder的getLayoutPosition()方法
	 *
	 * @param recyclerView
	 * @param holder
	 * @return
	 */
	public static int getLayoutPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder)
	{
		RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
		if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)
		{

			int headerViewCounter =
				((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
			if (headerViewCounter > 0)
			{
				return holder.getLayoutPosition() - headerViewCounter;
			}
		}

		return holder.getLayoutPosition();
	}

	/**
	 * 请使用本方法替代RecyclerView.ViewHolder的getAdapterPosition()方法
	 *
	 * @param recyclerView
	 * @param holder
	 * @return
	 */
	public static int getAdapterPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder)
	{
		RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
		if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)
		{

			int headerViewCounter =
				((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
			if (headerViewCounter > 0)
			{
				return holder.getAdapterPosition() - headerViewCounter;
			}
		}

		return holder.getAdapterPosition();
	}

	public static void initRecyclerView(final Context context, final RecyclerView recyclerView,
										BGARecyclerViewAdapter adapter,
										final SwipeToLoadLayout swipeToLoadLayout,
										final View header,
										final View footer, boolean autoLoad,
										RecyclerView.ItemDecoration itemDecoration,
										RecyclerView.LayoutManager layoutManager)
	{
		HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter =
			new HeaderAndFooterRecyclerViewAdapter(adapter);
		recyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		if (itemDecoration != null)
		{
			recyclerView.addItemDecoration(itemDecoration);
		}
		if (autoLoad && swipeToLoadLayout != null)
		{
			recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener()
			{
				@Override public void onScrollStateChanged(RecyclerView recyclerView, int newState)
				{
					if (newState == RecyclerView.SCROLL_STATE_IDLE)
					{
						if (!ViewCompat.canScrollVertically(recyclerView, 1))
						{
							if (swipeToLoadLayout.isLoadMoreEnabled())
							{
								swipeToLoadLayout.setLoadingMore(true);
							}
						}
					}
				}
			});
		}
		if (header != null || footer != null)
		{
			adapter.setShowHeaderAndFooterListener(new ShowHeaderAndFooterListener()
			{
				@Override public void showHeader()
				{
					if (header != null)
					{
						setHeaderView(recyclerView, header);
					}
					if (swipeToLoadLayout != null)
					{
						swipeToLoadLayout.setLoadMoreEnabled(false);
//						swipeToLoadLayout.setRefreshEnabled(false);
					}
				}

				@Override public void showFooter()
				{
					if (footer != null)
					{
						setFooterView(recyclerView, footer);
					}
					if (swipeToLoadLayout != null)
					{
						swipeToLoadLayout.setLoadMoreEnabled(false);
//							swipeToLoadLayout.setRefreshEnabled(true);
					}
					show(context, "没有更多数据");
				}

				@Override public void disHeader()
				{
					removeHeaderView(recyclerView);
					if (swipeToLoadLayout != null)
					{
						swipeToLoadLayout.setLoadMoreEnabled(true);
//						swipeToLoadLayout.setRefreshEnabled(true);
					}
				}

				@Override public void disFooter()
				{
					removeFooterView(recyclerView);
					if (swipeToLoadLayout != null)
					{
						swipeToLoadLayout.setLoadMoreEnabled(true);
//						swipeToLoadLayout.setRefreshEnabled(true);
					}
				}

				@Override public void disLoading()
				{
					if (swipeToLoadLayout != null)
					{
						swipeToLoadLayout.setRefreshing(false);
						swipeToLoadLayout.setLoadingMore(false);
					}
				}
			});
		}
	}

	public static void show(Context context, String info)
	{
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}

	public interface ShowHeaderAndFooterListener
	{
		void showHeader();

		void showFooter();

		void disHeader();

		void disFooter();

		void disLoading();
	}
}