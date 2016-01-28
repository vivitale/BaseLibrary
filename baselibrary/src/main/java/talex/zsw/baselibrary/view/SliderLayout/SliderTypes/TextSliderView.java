package talex.zsw.baselibrary.view.SliderLayout.SliderTypes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import talex.zsw.baselibrary.R;

/**
 * This is a slider with a description TextView.
 */
public class TextSliderView extends BaseSliderView
{
	private int background, textcolor;

	public TextSliderView(Context context)
	{
		super( context );
		background = Color.parseColor( "#77000000" );
		textcolor = Color.WHITE;
	}

	public TextSliderView(Context context, int background, int textcolor)
	{
		super( context );
		this.background = background;
		this.textcolor = textcolor;
	}

	@Override public View getView()
	{
		View v = LayoutInflater.from( getContext() )
			.inflate( R.layout.sliderlayout_render_type_text, null );
		ImageView target = (ImageView) v.findViewById( R.id.daimajia_slider_image );
		TextView description = (TextView) v.findViewById( R.id.description );
		LinearLayout layout = (LinearLayout) v.findViewById( R.id.description_layout );
		layout.setBackgroundColor( background );
		description.setTextColor( textcolor );
		description.setText( getDescription() );
		bindEventAndShow( v, target );
		return v;
	}
}
