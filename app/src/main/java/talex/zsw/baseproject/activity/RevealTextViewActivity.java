package talex.zsw.baseproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import talex.zsw.baselibrary.widget.RevealTextView;
import talex.zsw.baseproject.R;

public class RevealTextViewActivity extends AppCompatActivity
{

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_reveal );
		new Handler().postDelayed( new Runnable()
		{
			@Override public void run()
			{
				((RevealTextView) findViewById( R.id.text )).setAnimatedText( "Sample test" );
			}
		}, 5000 );
	}
}
