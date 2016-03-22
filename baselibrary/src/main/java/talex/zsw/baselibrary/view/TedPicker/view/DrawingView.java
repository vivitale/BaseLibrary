/*
 * Copyright (c) 2016. Ted Park. All Rights Reserved
 */

package talex.zsw.baselibrary.view.TedPicker.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import talex.zsw.baselibrary.R;
import talex.zsw.baselibrary.view.TedPicker.util.Util;


/**
 * Created by TedPark on 15. 6. 29..
 */
public class DrawingView extends View {
    private boolean haveTouch = false;
    private Rect touchArea;
    private Paint paint;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedValue value = new TypedValue ();
        context.getTheme ().resolveAttribute ( R.attr.colorPrimary, value, true);
        int primary_color = value.data;

        paint = new Paint();
        paint.setColor(primary_color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth( Util.dpToPx(context,2));
        haveTouch = false;
    }

    public void setHaveTouch(boolean val, Rect rect) {
        haveTouch = val;
        touchArea = rect;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(haveTouch){
            canvas.drawRect(
                    touchArea.left, touchArea.top, touchArea.right, touchArea.bottom,
                    paint);
        }
    }
}