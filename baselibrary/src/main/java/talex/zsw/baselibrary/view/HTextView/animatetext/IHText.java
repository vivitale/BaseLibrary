package talex.zsw.baselibrary.view.HTextView.animatetext;

import android.graphics.Canvas;
import android.util.AttributeSet;

import talex.zsw.baselibrary.view.HTextView.HTextView;


/**
 * interface used in HTextView
 * Created by hanks on 15-12-14.
 */
public interface IHText {
    void init(HTextView hTextView, AttributeSet attrs, int defStyle);
    void animateText(CharSequence text);
    void onDraw(Canvas canvas);
    void reset(CharSequence text);
}
