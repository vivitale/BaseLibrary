package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.fragment.PercentLayoutFragment;

/**
 * 项目名称: BaseProject
 * 作用: 百分比布局
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/1/14 15:28 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PercentLayoutActivity extends BaseAppCompatActivity
{
	@Bind(R.id.tab_layout) TabLayout mTabLayout;
	@Bind(R.id.content) FrameLayout content;
	@Bind(R.id.pager) ViewPager mPager;

	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	private List<String> titleList = new ArrayList<String>();
	private TabFragmentAdapter mAdapter;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView( R.layout.activity_percentlayout );
		ButterKnife.bind( this );
	}

	@Override protected void initData()
	{
		titleList.add( "RelativeLayout1" );
		fragmentList.add( null );
		titleList.add( "RelativeLayout2" );
		fragmentList.add( null );
		titleList.add( "FrameLayout" );
		fragmentList.add( null );
		titleList.add( "LinearLayout" );
		fragmentList.add( null );
		titleList.add( "Complex RelativeLayout" );
		fragmentList.add( null );

		mTabLayout.setTabMode( TabLayout.MODE_SCROLLABLE );
		// 单行不能滑动,平均分配每一个title的大小
		//mTabLayout.setTabMode(TabLayout.MODE_FIXED);
		//绑定tab标题数据
		for(int i = 0; i < titleList.size(); i++)
		{
			mTabLayout.addTab( mTabLayout.newTab().setText( titleList.get( i ) ) );
		}

		//实例化适配器
		mAdapter = new TabFragmentAdapter( getSupportFragmentManager(), mTabLayout.getTabCount(),
			titleList );
		//viewPager绑定适配器
		mPager.setAdapter( mAdapter );
		//TabLayout绑定ViewPager滑动
		mTabLayout.setupWithViewPager( mPager );
		//TabLayout绑定标题栏点击适配器
		mTabLayout.setTabsFromPagerAdapter( mAdapter );
	}

	/**
	 * 自定义ViewPager适配器
	 */
	public class TabFragmentAdapter extends FragmentPagerAdapter
	{
		private int tabNum;
		private FragmentManager manager;
		private List<String> titles;

		//构造函数，Fragment管理工具manger，标题栏个数nNumTab，标题栏文本List
		public TabFragmentAdapter(FragmentManager manager, int nNumTab, List<String> list)
		{
			super( manager );
			this.tabNum = nNumTab;
			this.manager = manager;
			this.titles = list;
		}

		@Override public Fragment getItem(int position)
		{
			//隐藏所有fragment
			hideFragments( manager.beginTransaction() );
			if(fragmentList.get( position ) == null)
			{
				fragmentList.set( position, new PercentLayoutFragment( position ) );
			}
			return fragmentList.get( position );
		}

		@Override public int getCount()
		{
			return tabNum;
		}

		/**
		 * 设置标题栏文本，必须复写
		 */
		@Override public CharSequence getPageTitle(int position)
		{
			return titles.get( position );
		}
	}

	/**
	 * 隐藏所有fragment，提高复用率
	 */
	public void hideFragments(FragmentTransaction transaction)
	{
		for(Fragment tabFragment : fragmentList)
		{
			if(tabFragment != null)
			{
				transaction.hide( tabFragment );
			}
		}
	}
}
