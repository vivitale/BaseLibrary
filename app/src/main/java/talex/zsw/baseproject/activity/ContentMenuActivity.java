package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.ContentMenu.ContextMenuDialogFragment;
import talex.zsw.baselibrary.view.ContentMenu.MenuObject;
import talex.zsw.baselibrary.view.ContentMenu.MenuParams;
import talex.zsw.baselibrary.view.ContentMenu.interfaces.OnMenuItemClickListener;
import talex.zsw.baselibrary.view.ContentMenu.interfaces.OnMenuItemLongClickListener;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-19-0019 15:55 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ContentMenuActivity extends BaseAppCompatActivity implements OnMenuItemClickListener,
	OnMenuItemLongClickListener, View.OnClickListener

{
	@Bind(R.id.button) Button button;
	private FragmentManager fragmentManager;
	private DialogFragment mMenuDialogFragment;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_contentmenu);
		ButterKnife.bind(this);

		fragmentManager = getSupportFragmentManager();
		initMenuFragment();

		button.setOnClickListener(this);
	}

	@Override protected void initData()
	{

	}

	/**
	 * 初始化菜单
	 */
	private void initMenuFragment()
	{
		MenuParams menuParams = new MenuParams();
		menuParams.setActionBarSize((int) getResources().getDimension(
			R.dimen.tool_bar_height));
		menuParams.setMenuObjects(getMenuObjects());
//		menuParams.setClosableOutside(false);
//		menuParams.setAnimationDelay(50);
//		menuParams.setAnimationDuration(1000);
//		menuParams.setFitsSystemWindow(true);
//		menuParams.setClipToPadding(true);
		mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
	}

	/**
	 * 生成菜单项
	 */
	private List<MenuObject> getMenuObjects()
	{
		// You can use any [resource, bitmap, drawable, color] as image:
		// item.setResource(...)
		// item.setBitmap(...)
		// item.setDrawable(...)
		// item.setColor(...)
		// You can set image ScaleType:
		// item.setScaleType(ScaleType.FIT_XY)
		// You can use any [resource, drawable, color] as background:
		// item.setBgResource(...)
		// item.setBgDrawable(...)
		// item.setBgColor(...)
		// You can use any [color] as text color:
		// item.setTextColor(...)
		// You can set any [color] as divider color:
		// item.setDividerColor(...)

		List<MenuObject> menuObjects = new ArrayList<MenuObject>();

		MenuObject close = new MenuObject();
		close.setBgColor(Color.parseColor("#40000000"));
		close.setDividerColor(Color.WHITE);
		close.setResource(android.R.drawable.ic_input_delete);

		MenuObject send = new MenuObject("Send message");
		send.setBgColor(Color.parseColor("#40000000"));
		send.setDividerColor(Color.WHITE);
		send.setResource(android.R.drawable.ic_dialog_alert);

		MenuObject like = new MenuObject("Like profile");
		Bitmap b = BitmapFactory.decodeResource(getResources(),
			android.R.drawable.ic_dialog_email);
		like.setBgColor(Color.parseColor("#40000000"));
		like.setDividerColor(Color.WHITE);
		like.setBitmap(b);

		MenuObject addFr = new MenuObject("Add to friends");
		BitmapDrawable bd = new BitmapDrawable(getResources(),
			BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_dialog_info));
		addFr.setBgColor(Color.parseColor("#40000000"));
		addFr.setDividerColor(Color.WHITE);
		addFr.setDrawable(bd);

		MenuObject addFav = new MenuObject("Add to favorites");
		addFav.setResource(android.R.drawable.ic_dialog_map);
		addFav.setBgColor(Color.parseColor("#40000000"));
		addFav.setDividerColor(Color.WHITE);

		MenuObject block = new MenuObject("Block user");
		block.setResource(android.R.drawable.ic_lock_idle_low_battery);
		block.setBgColor(Color.parseColor("#40000000"));
		block.setDividerColor(Color.WHITE);

		menuObjects.add(close);
		menuObjects.add(send);
		menuObjects.add(like);
		menuObjects.add(addFr);
		menuObjects.add(addFav);
		menuObjects.add(block);
		return menuObjects;
	}

	@Override public void onMenuItemClick(View clickedView, int position)
	{
		Toast.makeText(this, "Clicked on position: " + position,
			Toast.LENGTH_SHORT).show();
	}

	@Override public void onMenuItemLongClick(View clickedView, int position)
	{
		Toast.makeText(this, "Long clicked on position: " + position,
			Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onBackPressed()
	{
		if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded())
		{
			mMenuDialogFragment.dismiss();
		}
		else
		{
			finish();
		}
	}

	@Override public void onClick(View v)
	{
		if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null)
		{
			mMenuDialogFragment.show(fragmentManager,
				ContextMenuDialogFragment.TAG);
		}
	}
}
