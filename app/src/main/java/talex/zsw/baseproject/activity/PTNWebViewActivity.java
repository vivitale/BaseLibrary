package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.PullToNextLayout.OnItemSelectListener;
import talex.zsw.baselibrary.view.PullToNextLayout.PullToNextLayout;
import talex.zsw.baselibrary.view.PullToNextLayout.adapter.PullToNextFragmentAdapter;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.fragment.PTNWebViewFragment;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-23-0023 16:15 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PTNWebViewActivity extends BaseAppCompatActivity
{
	@Bind(R.id.pullToNextLayout) PullToNextLayout pullToNextLayout;

	private ArrayList<Fragment> list;

	private String[] titles = { "PullToNextLayout",
		"使用 DrawerLayout 实现 Material Design风格的侧滑", "分享3个 自定义控件",
		"BouncyEditText 不一样的 EditText", "会变色的 ViewPager",
		"android Heads-Up风格通知", "快速集成图片游览器 android",
		"JSon实体类快速生成插件 GsonFormat使用", "ListView适配器的超省写法" };

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_pulltonextlayout);
		ButterKnife.bind(this);

		list = new ArrayList<Fragment>();

		list.add(new PTNWebViewFragment(0));
		list.add(new PTNWebViewFragment(1));
		list.add(new PTNWebViewFragment(2));
		list.add(new PTNWebViewFragment(3));
		list.add(new PTNWebViewFragment(4));
		list.add(new PTNWebViewFragment(5));
		list.add(new PTNWebViewFragment(6));
		list.add(new PTNWebViewFragment(7));
		list.add(new PTNWebViewFragment(8));

		pullToNextLayout.setAdapter(new PullToNextFragmentAdapter(
			getSupportFragmentManager(), list));

		pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener()
		{
			@Override
			public void onSelectItem(int position, View view)
			{

				PTNWebViewActivity.this.setTitle(titles[position]);

			}
		});
		PTNWebViewActivity.this.setTitle(titles[0]);
	}

	@Override protected void initData()
	{

	}
}
