package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.SpotsDialog.SpotsDialog;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 仿Win8的loading
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/1/12 13:41 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SpotsDialogActivity extends BaseAppCompatActivity implements OnClickListener
{
	@Bind(android.R.id.button1) Button button1;
	@Bind(android.R.id.button2) Button button2;
	@Bind(android.R.id.button3) Button button3;
	@Bind(android.R.id.closeButton) Button closeButton;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView( R.layout.activity_spotsdialog );
		ButterKnife.bind( this );

		button1.setOnClickListener( this );
		button2.setOnClickListener( this );
		button3.setOnClickListener( this );
		closeButton.setOnClickListener( this );
	}

	@Override protected void initData()
	{

	}

	@Override public void onClick(View v)
	{
		switch(v.getId())
		{
			case android.R.id.button1:
				new SpotsDialog( this ).show();
				break;
			case android.R.id.button2:
				new SpotsDialog( this, R.style.CustomSpotsDialog ).show();
				break;
			case android.R.id.button3:
				new SpotsDialog( this, "Custom message" ).show();
				break;
			case android.R.id.closeButton:
				new SpotsDialog( this, "Custom message & style", R.style.CustomSpotsDialog ).show();
				break;
		}
	}
}
