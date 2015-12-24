package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.ExpandablePanel;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 可以扩展开的布局
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-12-0012 17:59 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ExpandablePanelActivity extends BaseAppCompatActivity
	implements ExpandablePanel.OnExpandListener
{
	@Bind(R.id.view) View view;
	@Bind(R.id.expand) Button expand;
	@Bind(R.id.expandL) LinearLayout expandL;
	@Bind(R.id.value) TextView value;
	@Bind(R.id.expandablePanel) ExpandablePanel expandablePanel;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_expandablepanel);
		ButterKnife.bind(this);

		expandablePanel.setOnExpandListener(this);
	}

	@Override protected void initData()
	{

	}

	@Override public void onExpand(View handle, View content)
	{
		expand.setText("Less");
	}

	@Override public void onCollapse(View handle, View content)
	{
		expand.setText("More");
	}
}
