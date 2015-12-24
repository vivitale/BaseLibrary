package talex.zsw.baselibrary.view.SweetSheet.sweetpick;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;

import java.util.List;

import talex.zsw.baselibrary.R;
import talex.zsw.baselibrary.view.SweetSheet.adapter.MenuRVAdapter;
import talex.zsw.baselibrary.view.SweetSheet.entity.MenuEntity;
import talex.zsw.baselibrary.view.SweetSheet.widget.CRImageView;
import talex.zsw.baselibrary.view.SweetSheet.widget.FreeGrowUpParentRelativeLayout;
import talex.zsw.baselibrary.view.SweetSheet.widget.SweetView;

/**
 * @author zzz40500
 * @version 1.0
 * @date 2015/8/5.
 * @github: https://github.com/zzz40500
 */
public class RecyclerViewDelegate extends Delegate  {

    private SweetView mSweetView;
    private RecyclerView mRV;
    private MenuRVAdapter mMenuRVAdapter;
    private CRImageView sliderIm;
    private FreeGrowUpParentRelativeLayout mFreeGrowUpParentRelativeLayout;
    private boolean mIsDragEnable;
    private int mContentViewHeight;

    public RecyclerViewDelegate(boolean dragEnable) {
        mIsDragEnable=dragEnable;

    }

    public RecyclerViewDelegate(boolean dragEnable  ,int contentViewHeight) {
        mContentViewHeight = contentViewHeight;
        mIsDragEnable = dragEnable;
    }

    @Override
    protected View createView(int SSColor, int BGColor) {

        View rootView = LayoutInflater.from(mParentVG.getContext())
                .inflate(R.layout.sweetsheet_layout_rv_sweet, null, false);

        mSweetView = (SweetView) rootView.findViewById(R.id.sv);
        mFreeGrowUpParentRelativeLayout = (FreeGrowUpParentRelativeLayout) rootView.findViewById(R.id.freeGrowUpParentF);
        mRV = (RecyclerView) rootView.findViewById(R.id.rv);
        mRV.setBackgroundColor(BGColor);
        sliderIm = (CRImageView) rootView.findViewById(R.id.sliderIM);
        mRV.setLayoutManager(new LinearLayoutManager(mParentVG.getContext(), LinearLayoutManager.VERTICAL, false));
        mSweetView.setAnimationListener(new AnimationImp());
        mSweetView.reDraw(SSColor);
        if(mContentViewHeight > 0){
            mFreeGrowUpParentRelativeLayout.setContentHeight(mContentViewHeight);
        }

        return rootView;
    }

    public  RecyclerViewDelegate setContentHeight(int height){

        if(height >0 && mFreeGrowUpParentRelativeLayout != null){
            mFreeGrowUpParentRelativeLayout.setContentHeight(height);
        }else{
            mContentViewHeight=height;
        }

        return this;

    }



    protected void setMenuList(final List<MenuEntity> menuEntities) {

        mMenuRVAdapter = new MenuRVAdapter(menuEntities, SweetSheet.Type.RecyclerView);
        mRV.setAdapter(mMenuRVAdapter);
        mMenuRVAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (mOnMenuItemClickListener != null) {
                    if (mOnMenuItemClickListener.onItemClick(position, menuEntities.get(position))) {
                        delayedDismiss();
                    }
                }
            }
        });
        mRV.setLayoutAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mRV.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
                mFreeGrowUpParentRelativeLayout.setClipChildren(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mRV.setOnTouchListener(null);

                mFreeGrowUpParentRelativeLayout.setClipChildren(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    protected void show() {
        super.show();
        ViewGroup.LayoutParams lp =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mParentVG.addView(mRootView, lp);
        mSweetView.show();
    }

    @Override
    protected void dismiss() {
        super.dismiss();

    }


    class AnimationImp implements SweetView.AnimationListener {

        @Override
        public void onStart() {
            mFreeGrowUpParentRelativeLayout.reset();
            mStatus = SweetSheet.Status.SHOWING;
            sliderIm.setVisibility(View.INVISIBLE);
            mRV.setVisibility(View.GONE);
        }

        @Override
        public void onEnd() {

            if(mIsDragEnable) {
                sliderIm.setVisibility(View.VISIBLE);
                sliderIm.circularReveal(sliderIm.getWidth() / 2, sliderIm.getHeight() / 2, 0, sliderIm.getWidth());
            }
            mStatus = SweetSheet.Status.SHOW;

        }

        @Override
        public void onContentShow() {
            mRV.setVisibility(View.VISIBLE);
            mRV.setAdapter(mMenuRVAdapter);
            mRV.scheduleLayoutAnimation();
        }
    }
}
