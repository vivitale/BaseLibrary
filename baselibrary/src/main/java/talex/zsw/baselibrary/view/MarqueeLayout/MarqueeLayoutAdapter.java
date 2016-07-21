package talex.zsw.baselibrary.view.MarqueeLayout;

import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MarqueeLayoutAdapter<T>
{

	private ArrayList<View> mViews;

	private List<T> mDatas;

	public ArrayList<View> getViews()
	{
		return mViews;
	}

	public List<T> getDatas()
	{
		return mDatas;
	}

	public void setCustomView(MarqueeLayout layout, int layoutId, List<T> data,
							  InitViewCallBack<T> callBack)
	{
		if (data == null)
		{
			return;
		}
		else if (data.size() > 1)
		{
			switch (layout.getOrientation())
			{
				case MarqueeLayout.ORIENTATION_UP:
				case MarqueeLayout.ORIENTATION_LEFT:
					data.add(data.get(0));
					break;
				case MarqueeLayout.ORIENTATION_DOWN:
				case MarqueeLayout.ORIENTATION_RIGHT:
					data.add(0, data.get(data.size() - 1));
					break;
			}
		}

		mViews = new ArrayList<>(data.size());
		mDatas = data;

		for (int i = 0; i < data.size(); i++)
		{
			final View view =
				LayoutInflater.from(layout.getContext()).inflate(layoutId, layout, false);
			if (callBack != null)
			{
				callBack.init(view, i, data.get(i));
			}
			mViews.add(view);
		}
	}

	public interface InitViewCallBack<T>
	{
		void init(View view, int position, T item);
	}
}
