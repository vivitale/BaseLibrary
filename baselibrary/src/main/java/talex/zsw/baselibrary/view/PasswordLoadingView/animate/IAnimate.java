package talex.zsw.baselibrary.view.PasswordLoadingView.animate;

import android.graphics.Canvas;

import talex.zsw.baselibrary.view.PasswordLoadingView.PsdLoadingView;


public interface IAnimate
{
	void init(PsdLoadingView mPsdLoadingView);

	void startLoading();

	void stopLoading();

	void setDuration(int duration);

	void onDraw(Canvas canvas);

	void onVisibilityChanged(boolean isVisibiable);

	boolean isLoading();
}
