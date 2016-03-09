package talex.zsw.baseproject.popupwindow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import talex.zsw.baseproject.R;
import talex.zsw.baselibrary.view.BasePopupWindow.BasePopupWindow;
import talex.zsw.baselibrary.view.BasePopupWindow.utils.ToastUtils;


/**
 * Created by 大灯泡 on 2016/1/15.
 * 从底部滑上来的popup
 */
public class SlideFromBottomPopup extends BasePopupWindow implements View.OnClickListener{

    private View popupView;
    public SlideFromBottomPopup(Activity context) {
        super(context);
        bindEvent();
    }

    @Override
    public Animation getShowAnimation() {
        return getTranslateAnimation(250*2,0,300);
    }

    @Override
    protected View getClickToDismissView() {
        return popupView.findViewById( R.id.click_to_dismiss);
    }

    @Override
    public View getPopupView() {
        popupView= LayoutInflater.from(mContext).inflate(R.layout.popup_slide_from_bottom,null);
        return popupView;
    }

    @Override
    public View getAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
            popupView.findViewById(R.id.tx_1).setOnClickListener(this);
            popupView.findViewById(R.id.tx_2).setOnClickListener(this);
            popupView.findViewById(R.id.tx_3).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.tx_1)
        {
            ToastUtils.ToastMessage( mContext, "click tx_1" );
        }
        else if(i == R.id.tx_2)
        {
            ToastUtils.ToastMessage( mContext, "click tx_2" );
        }
        else if(i == R.id.tx_3)
        {
            ToastUtils.ToastMessage( mContext, "click tx_3" );
        }
    }
}
