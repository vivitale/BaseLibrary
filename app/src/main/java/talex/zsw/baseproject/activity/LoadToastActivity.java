package talex.zsw.baseproject.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import talex.zsw.baselibrary.view.LoadToast.LoadToast;
import talex.zsw.baseproject.R;


public class LoadToastActivity extends AppCompatActivity
{
	@Override public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_loadtoast );

		final String text = "自定义文本";
		final LoadToast lt = new LoadToast( this ).setText( text ).setTranslationY( 100 ).show();
		//lt.success();
		final ViewGroup root = (ViewGroup) findViewById( android.R.id.content );

		View v = new View( this );
		v.setBackgroundColor( Color.RED );
		root.addView( v, new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, 400 ) );
		findViewById( R.id.show ).setOnClickListener( new View.OnClickListener()
		{
			@Override public void onClick(View view)
			{
				lt.show();
			}
		} );
		findViewById( R.id.error ).setOnClickListener( new View.OnClickListener()
		{
			@Override public void onClick(View view)
			{
				lt.error();
			}
		} );
		findViewById( R.id.success ).setOnClickListener( new View.OnClickListener()
		{
			@Override public void onClick(View view)
			{
				lt.success();
			}
		} );
		findViewById( R.id.refresh ).setOnClickListener( new View.OnClickListener()
		{
			@Override public void onClick(View view)
			{
				View v = new View( LoadToastActivity.this );
				v.setBackgroundColor( Color
					.rgb( (int) (Math.random() * 255), (int) (Math.random() * 255),
						(int) (Math.random() * 255) ) );
				root.addView( v,
					new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, 400 ) );
			}
		} );
	}
}