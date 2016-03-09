package talex.zsw.baseproject.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import talex.zsw.baselibrary.view.CoolMenu.CoolMenuFrameLayout;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.fragment.CoolMenuFragment1;
import talex.zsw.baseproject.fragment.CoolMenuFragment2;
import talex.zsw.baseproject.fragment.CoolMenuFragment3;
import talex.zsw.baseproject.fragment.CoolMenuFragment4;

public class CoolMenuActivity extends AppCompatActivity
{

	Button bt;

	boolean open;

	CoolMenuFrameLayout coolMenuFrameLayout;

	List<Fragment> fragments = new ArrayList<>();

	List<String> titleList = null;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_coolmenu );

		//        coolMenuFrameLayout = (CoolMenuFrameLayout) findViewById(R.id.rl_main);
		coolMenuFrameLayout = $( R.id.rl_main );
		String[] titles = {"CONTACT", "ABOUT", "TEAM", "PROJECTS"};
		titleList = Arrays.asList( titles );
		List<Integer> resList = new ArrayList<>();
		resList.add( R.color.beige );
		resList.add( R.color.rosybrown );
		resList.add( R.color.tan );
		resList.add( R.color.royalblue );
		coolMenuFrameLayout.setTitles( titleList );
		coolMenuFrameLayout.setTitleBackground( resList );
		coolMenuFrameLayout.setMenuIcon( R.drawable.menu );

		fragments.add( new CoolMenuFragment1() );
		fragments.add( new CoolMenuFragment2() );
		fragments.add( new CoolMenuFragment3() );
		fragments.add( new CoolMenuFragment4() );

		FragmentPagerAdapter adapter = new FragmentPagerAdapter( getSupportFragmentManager() )
		{
			@Override public Fragment getItem(int position)
			{
				return fragments.get( position );
			}

			@Override public int getCount()
			{
				return fragments.size();
			}
		};
		coolMenuFrameLayout.setAdapter( adapter );
	}

	@SuppressWarnings("unchecked") private <T extends View> T $(@IdRes int id)
	{
		return (T) findViewById( id );
	}
}
