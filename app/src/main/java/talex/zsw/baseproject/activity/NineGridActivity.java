package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import talex.zsw.baseproject.R;


public class NineGridActivity extends AppCompatActivity
{

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_ninegrid );
		setSupportActionBar( (Toolbar) findViewById( R.id.toolbar ) );
		findViewById( R.id.btn_fill_style ).setOnClickListener( new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				Intent intent = new Intent( NineGridActivity.this, NineFillStyleActivity.class );
				startActivity( intent );
			}
		} );

		findViewById( R.id.btn_grid_style ).setOnClickListener( new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				Intent intent = new Intent( NineGridActivity.this, NineGridStyleActivity.class );
				startActivity( intent );
			}
		} );
	}
}
