package talex.zsw.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import talex.zsw.baselibrary.view.PasswordLoadingView.PsdLoadingView;
import talex.zsw.baselibrary.view.PasswordLoadingView.animate.EatAnimate;
import talex.zsw.baselibrary.view.PasswordLoadingView.animate.IAnimate;
import talex.zsw.baselibrary.view.PasswordLoadingView.animate.TranslationX2Animate;
import talex.zsw.baselibrary.view.PasswordLoadingView.animate.TranslationXAnimate;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作用: 密码文本的动画效果
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/2/24 17:01 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class PsdLoadingViewActivity extends AppCompatActivity
{
	Spinner spinner;


	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_psdloadingview );

		final PsdLoadingView psd = (PsdLoadingView) findViewById( R.id.psdloadingview );

		spinner = (Spinner) findViewById( R.id.spinner );
		String[] mItems = getResources().getStringArray( R.array.animate );
		ArrayAdapter<String> adapter =
			new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, mItems );
		adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		spinner.setAdapter( adapter );
		spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener()
		{
			@Override public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
			{
				IAnimate iAnimate;
				switch(pos)
				{
					case 0:
						iAnimate = new TranslationXAnimate();
						break;
					case 1:
						iAnimate = new TranslationX2Animate();
						break;
					case 2:
						iAnimate = new EatAnimate();
						break;
					default:
						iAnimate = new TranslationXAnimate();
				}
				psd.init( iAnimate );
			}


			@Override public void onNothingSelected(AdapterView<?> parent)
			{
			}
		} );

		findViewById( R.id.button ).setOnClickListener( new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				psd.startLoading();
			}
		} );

		findViewById( R.id.success ).setOnClickListener( new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				psd.stopLoading();
			}
		} );
	}
}
