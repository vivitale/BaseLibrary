package talex.zsw.baselibrary.view.SwipeLayout.interfaces;


import java.util.List;

import talex.zsw.baselibrary.view.SwipeLayout.SwipeLayout;
import talex.zsw.baselibrary.view.SwipeLayout.util.Attributes;

public interface SwipeItemMangerInterface
{

	void openItem(int position);

	void closeItem(int position);

	void closeAllExcept(SwipeLayout layout);

	void closeAllItems();

	List<Integer> getOpenItems();

	List<SwipeLayout> getOpenLayouts();

	void removeShownLayouts(SwipeLayout layout);

	boolean isOpen(int position);

	Attributes.Mode getMode();

	void setMode(Attributes.Mode mode);
}
