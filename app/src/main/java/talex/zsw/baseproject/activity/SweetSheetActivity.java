package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.SweetSheet.entity.MenuEntity;
import talex.zsw.baselibrary.view.SweetSheet.sweetpick.BlurEffect;
import talex.zsw.baselibrary.view.SweetSheet.sweetpick.CustomDelegate;
import talex.zsw.baselibrary.view.SweetSheet.sweetpick.DimEffect;
import talex.zsw.baselibrary.view.SweetSheet.sweetpick.RecyclerViewDelegate;
import talex.zsw.baselibrary.view.SweetSheet.sweetpick.SweetSheet;
import talex.zsw.baselibrary.view.SweetSheet.sweetpick.ViewPagerDelegate;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 底部弹出布局
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015 15/11/24 14:08 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SweetSheetActivity extends BaseAppCompatActivity implements View.OnClickListener
{
	@Bind(R.id.action_recyclerView) TextView actionRecyclerView;
	@Bind(R.id.action_viewpager) TextView actionViewpager;
	@Bind(R.id.action_custom) TextView actionCustom;
	@Bind(R.id.rl) RelativeLayout rl;
	private SweetSheet mSweetSheet;
	private SweetSheet mSweetSheet2;
	private SweetSheet mSweetSheet3;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_sweetsheet);
		ButterKnife.bind(this);

		setupViewpager();
		setupRecyclerView();
		setupCustomView();

		actionRecyclerView.setOnClickListener(this);
		actionCustom.setOnClickListener(this);
		actionViewpager.setOnClickListener(this);
	}

	@Override protected void initData()
	{
	}

	private void setupCustomView()
	{
		mSweetSheet3 = new SweetSheet(rl);
		CustomDelegate customDelegate = new CustomDelegate(true,
			CustomDelegate.AnimationType.DuangLayoutAnimation);
		View view = LayoutInflater.from(this).inflate(R.layout.sweetsheet, null, false);
		customDelegate.setCustomView(view);
		mSweetSheet3.setDelegate(customDelegate, Color.RED, Color.BLACK);
		view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mSweetSheet3.dismiss();
			}
		});
	}

	private void setupRecyclerView()
	{
		ArrayList<MenuEntity> list = new ArrayList<>();
		//添加假数据
		MenuEntity menuEntity1 = new MenuEntity();
		menuEntity1.iconId = R.mipmap.icon;
		menuEntity1.title = "code";
		MenuEntity menuEntity = new MenuEntity();
		menuEntity.iconId = R.mipmap.icon;
		menuEntity.title = "QQ";
		list.add(menuEntity1);
		list.add(menuEntity);
		list.add(menuEntity);
		list.add(menuEntity);
		list.add(menuEntity);
		list.add(menuEntity);
		list.add(menuEntity);
		list.add(menuEntity);
		list.add(menuEntity);
		list.add(menuEntity);
		list.add(menuEntity);
		list.add(menuEntity);
		list.add(menuEntity);
		// SweetSheet 控件,根据 rl 确认位置
		mSweetSheet = new SweetSheet(rl);

		//设置数据源 (数据源支持设置 list 数组,也支持从菜单中获取)
		mSweetSheet.setMenuList(list);
		//根据设置不同的 Delegate 来显示不同的风格.
		mSweetSheet.setDelegate(new RecyclerViewDelegate(true));
		//根据设置不同Effect 来显示背景效果BlurEffect:模糊效果.DimEffect 变暗效果
		mSweetSheet.setBackgroundEffect(new BlurEffect(8));
		//设置点击事件
		mSweetSheet.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener()
		{
			@Override
			public boolean onItemClick(int position, MenuEntity menuEntity1)
			{

				//根据返回值, true 会关闭 SweetSheet ,false 则不会.
				Toast.makeText(SweetSheetActivity.this, menuEntity1.title + "  " + position,
					Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}

	private void setupViewpager()
	{
		mSweetSheet2 = new SweetSheet(rl);
		//从menu 中设置数据源
		mSweetSheet2.setMenuList(R.menu.menu_sweet);
		mSweetSheet2.setDelegate(new ViewPagerDelegate());
		mSweetSheet2.setBackgroundEffect(new DimEffect(0.5f));
		mSweetSheet2.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener()
		{
			@Override
			public boolean onItemClick(int position, MenuEntity menuEntity1)
			{

				Toast.makeText(SweetSheetActivity.this, menuEntity1.title + "  " + position,
					Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}

	@Override
	public void onBackPressed()
	{
		if (mSweetSheet.isShow() || mSweetSheet2.isShow())
		{
			if (mSweetSheet.isShow())
			{
				mSweetSheet.dismiss();
			}
			if (mSweetSheet2.isShow())
			{
				mSweetSheet2.dismiss();
			}
		}
		else
		{
			super.onBackPressed();
		}
	}

	@Override public void onClick(View v)
	{
		int id = v.getId();
		if (id == R.id.action_recyclerView)
		{
			if (mSweetSheet2.isShow())
			{
				mSweetSheet2.dismiss();
			}
			if (mSweetSheet3.isShow())
			{
				mSweetSheet3.dismiss();
			}
			mSweetSheet.toggle();
		}
		else if (id == R.id.action_viewpager)
		{
			if (mSweetSheet.isShow())
			{
				mSweetSheet.dismiss();
			}
			if (mSweetSheet3.isShow())
			{
				mSweetSheet3.dismiss();
			}
			mSweetSheet2.toggle();
		}
		else if (id == R.id.action_custom)
		{
			if (mSweetSheet.isShow())
			{
				mSweetSheet.dismiss();
			}
			if (mSweetSheet2.isShow())
			{
				mSweetSheet2.dismiss();
			}
			mSweetSheet3.toggle();
		}
	}
}
