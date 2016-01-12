package talex.zsw.baselibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import talex.zsw.baselibrary.util.ACache;
import talex.zsw.baselibrary.util.StringUtils;
import talex.zsw.baselibrary.view.appmsg.AppMsg;
import talex.zsw.baselibrary.widget.LoadingDialog;
import talex.zsw.baselibrary.widget.RichText;


public abstract class BaseFragment extends Fragment
{
	private static final int ZOOM = 0x10001;
	private static final int LEFT = 0x10002;
	private static final int RIGHT = 0x10003;
	private static final int UP_DOWN = 0x10004;

	protected LoadingDialog loadingDialog;
	private LayoutInflater inflater;
	private InputMethodManager mInputMethodManager;
	private ViewGroup container;
	public View mView;
	public ACache mACache;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		this.inflater = inflater;
		this.container = container;
		if (mACache == null)
		{
			mACache = ACache.get(getActivity());
		}
		try
		{
			initArgs(getArguments());
			initView(savedInstanceState);
			initData();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		mInputMethodManager = (InputMethodManager) getActivity()
			.getSystemService(Context.INPUT_METHOD_SERVICE);
		hideInputMethod();
		return mView;
	}

	protected abstract void initArgs(Bundle bundle);

	protected abstract void initView(Bundle bundle);

	protected abstract void initData();

	protected void setContentView(int layout)
	{
		mView = inflater.inflate(layout, container, false);
	}

	/**
	 * 显示键盘
	 */
	public void showInputMethod(EditText editText)
	{
		mInputMethodManager.showSoftInput(editText,
			InputMethodManager.SHOW_IMPLICIT);
	}

	/**
	 * 隐藏键盘
	 */
	public void hideInputMethod()
	{
		try
		{
			//noinspection ConstantConditions
			mInputMethodManager.hideSoftInputFromWindow(getActivity()
					.getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception ignored)
		{

		}
	}

	@TargetApi(Build.VERSION_CODES.KITKAT) @Override
	public void onDetach()
	{
		try
		{
			Field childFragmentManager = Fragment.class
				.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException | IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		super.onDetach();
	}

	/**
	 * @return 获取屏幕宽度
	 */
	public int getScrnWeight()
	{
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
			.getMetrics(mDisplayMetrics);
		return mDisplayMetrics.widthPixels;
	}

	/**
	 * @return 获取屏幕高度
	 */
	public int getScrnHeight()
	{
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
			.getMetrics(mDisplayMetrics);
		return mDisplayMetrics.heightPixels;
	}

	public void showDialog()
	{
		if (null == loadingDialog)
		{
			loadingDialog = new LoadingDialog(getActivity());
		}
		if (!loadingDialog.isShowing())
		{
			loadingDialog.show();
		}
	}

	public void disDialog()
	{
		if (loadingDialog != null && loadingDialog.isShowing())
		{
			loadingDialog.dismiss();
		}
	}

	public void showToast(String string)
	{
		Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
	}

	public void showAppMsg(String string)
	{
		AppMsg.Style style = new AppMsg.Style(AppMsg.LENGTH_SHORT, R.color.appmsg_custom);
		showAppMsg(string, style);
	}

	/**
	 * style = AppMsg.ALERT;
	 * style = AppMsg.CONFIRM;
	 * style = AppMsg.INFO;
	 */
	public void showAppMsg(String string, AppMsg.Style style)
	{
		AppMsg appMsg = AppMsg.makeText(getActivity(), string, style);
		appMsg.setLayoutGravity(Gravity.BOTTOM);
		appMsg.show();
	}

	public void showSnackbar(String string)
	{
		Snackbar snackbar;
		// 修改字的颜色
		SpannableString spanText = new SpannableString(string);
		spanText.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spanText.length(),
			Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

		snackbar = Snackbar.make(mView, spanText, Snackbar.LENGTH_LONG);
		//修改背景为红色
		snackbar.getView().setBackgroundColor(0xEC000000);
		snackbar.show();
	}

	public void showSnackbar(String string, String action, View.OnClickListener listener)
	{
		Snackbar snackbar;
		// 修改字的颜色
		SpannableString spanText = new SpannableString(string);
		spanText.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spanText.length(),
			Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

		snackbar = Snackbar.make(mView, spanText, Snackbar.LENGTH_LONG)
			.setAction(action, listener);
		//修改背景为红色
		snackbar.getView().setBackgroundColor(0xEC000000);
		snackbar.show();
	}

	public void start(Class<?> cls)
	{
		start( new Intent( getActivity(), cls ), RIGHT );
	}

	public void start(Class<?> cls, int style)
	{
		start( new Intent( getActivity(), cls ), style );
	}

	public void start(Intent intent)
	{
		start(intent, RIGHT);
	}

	public void start(Intent intent, int style)
	{
		startActivity(intent);
		if (style == ZOOM)
		{
			getActivity().overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_unzoom_out);
		}
		else if (style == LEFT)
		{
			getActivity().overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_staty);
		}
		else if (style == RIGHT)
		{
			getActivity().overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_staty);
		}
		else if (style == UP_DOWN)
		{
			getActivity().overridePendingTransition(R.anim.activity_slide_in_bottom, R.anim.activity_staty);
		}
	}

	public void end(int style)
	{
		getActivity().finish();
		if (style == ZOOM)
		{
			getActivity().overridePendingTransition(R.anim.activity_staty, R.anim.activity_unzoom_out);
		}
		else if (style == LEFT)
		{
			getActivity().overridePendingTransition(R.anim.activity_staty, R.anim.activity_slide_out_left);
		}
		else if (style == RIGHT)
		{
			getActivity().overridePendingTransition(R.anim.activity_staty, R.anim.activity_slide_out_right);
		}
		else if (style == UP_DOWN)
		{
			getActivity().overridePendingTransition(R.anim.activity_staty, R.anim.activity_slide_out_top);
		}
	}

	//=====================================WebView===================================
	public void setWebData(String content, WebView mWebView, RichText mRichText,
		final ProgressBar mProgressBar)
	{
		float scale = getResources().getDisplayMetrics().density;
		int x = (int) (getScrnWeight() / 20 / scale);
		content = "<style>\n" +
			"    img {\n" +
			"        max-width: 100%;\n" +
			"        width: 100%;\n" +
			"        height: auto\n" +
			"    }\n" +
			"    \n" +
			"    video {\n" +
			"        width: 100%;\n" +
			"        max-width: 100%;\n" +
			"        height: auto\n" +
			"    }\n" +
			"</style>\n" +
			content + "<script type=\"text/javascript\">" +
			"var imgs = document.getElementsByTagName('img');" +
			"for(var i = 0; i<tables.length; i++){" +  // 逐个改变
			"imgs[i].style.width = '100%';" +  // 宽度改为100%
			"imgs[i].style.height = 'auto';" +
			"}" +
			"</script>";
		// 设置WebView的属性，此时可以去执行JavaScript脚本`
		mWebView.getSettings().setJavaScriptEnabled( true ); // 设置支持javascript脚本
		mWebView.getSettings().setAllowFileAccess( true ); // 允许访问文件
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically( true );
		mWebView.getSettings().setLayoutAlgorithm( WebSettings.LayoutAlgorithm.SINGLE_COLUMN );
		mWebView.getSettings().setDefaultTextEncodingName( "UTF-8" );//设置默认为utf-8
		mWebView.getSettings().setDefaultFontSize( (int) (46 / scale) );
		mWebView.getSettings().setMinimumFontSize( (int) (38 / scale) );
		mWebView.getSettings().setSupportZoom( false );// 支持缩放
		mWebView.getSettings().setBuiltInZoomControls( false ); // 设置显示缩放按钮
		//		mWebView.getSettings().setUseWideViewPort(true);
		//		mWebView.getSettings().setLoadWithOverviewMode(true);
		//		mWebView.setInitialScale(960 * 100 / getScrnHeight());

		int screenDensity = getResources().getDisplayMetrics().densityDpi;
		WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
		switch(screenDensity)
		{
			case DisplayMetrics.DENSITY_LOW:
				zoomDensity = WebSettings.ZoomDensity.CLOSE;
				break;
			case DisplayMetrics.DENSITY_MEDIUM:
				zoomDensity = WebSettings.ZoomDensity.MEDIUM;
				break;
			case DisplayMetrics.DENSITY_HIGH:
				zoomDensity = WebSettings.ZoomDensity.FAR;
				break;
			default:
				zoomDensity = WebSettings.ZoomDensity.MEDIUM;
				break;
		}
		mWebView.getSettings().setDefaultZoom( zoomDensity );
		if(StringUtils.isBlank( content ))
		{
			mWebView.setVisibility( View.GONE );
			mRichText.setVisibility( View.VISIBLE );
			mRichText.setText( "无信息" );
		}
		else if(content.startsWith( "http://" ) || content.startsWith( "https://" ))
		{
			mProgressBar.setVisibility( View.VISIBLE );
			mProgressBar.setMax( 100 );
			// 当webview里面能点击是 在当前页面上显示！
			mWebView.setWebViewClient( new WebViewClient()
			{
				@Override public boolean shouldOverrideUrlLoading(WebView view, String url)
				{
					view.loadUrl( url );
					return true;
				}
			} );

			// 重写webview的值
			mWebView.setWebChromeClient( new WebChromeClient()
			{
				// 加载网页的进度条
				@Override public void onProgressChanged(WebView view, int newProgress)
				{
					mProgressBar.setProgress( newProgress );
					if(newProgress == 100)
					{
						mProgressBar.setVisibility( View.GONE );
					}
					else
					{
						if(mProgressBar.getVisibility() == View.GONE)
						{
							mProgressBar.setVisibility( View.VISIBLE );
							// pb.setProgress(newProgress);
						}
					}
					super.onProgressChanged( view, newProgress );
				}
			} );
			mWebView.loadUrl( content );
		}
		else
		{
			String regEx = "</?[^>]+>";
			Pattern pat = Pattern.compile( regEx );
			Matcher mat = pat.matcher( content );
			boolean rs = mat.find();
			if(rs)
			{
				if(content.contains( "https" ))//如果含有包括https
				{
					WebViewClient mWebviewclient = new WebViewClient()
					{
						public void onReceivedSslError(WebView view, SslErrorHandler handler,
							SslError error)
						{
							handler.proceed();
							//handler.cancel(); 默认的处理方式，WebView变成空白页
							//handler.process();接受证书
							//handleMessage(Message msg); 其他处理
						}
					};
					mWebView.setWebViewClient( mWebviewclient );
				}
				else// 当webview里面能点击是 在当前页面上显示！
				{
					mWebView.setWebViewClient( new WebViewClient()
					{
						@Override public boolean shouldOverrideUrlLoading(WebView view, String url)
						{
							view.loadUrl( url );
							return true;
						}
					} );
				}
				mWebView.loadData( content, "text/html; charset=UTF-8", null );
				// mWebView.loadData(fmtString(content), "text/html", "utf-8");
			}
			else
			{
				mWebView.setVisibility( View.GONE );
				mRichText.setVisibility( View.VISIBLE );
				mRichText.setRichText( content );
			}
		}
		if(Build.VERSION.SDK_INT > 10 && Build.VERSION.SDK_INT < 17)
		{
			fixWebView( mWebView );
		}
	}

	@TargetApi(11) private void fixWebView(WebView mWebView)
	{
		mWebView.removeJavascriptInterface( "searchBoxJavaBridge_" );
	}
}
