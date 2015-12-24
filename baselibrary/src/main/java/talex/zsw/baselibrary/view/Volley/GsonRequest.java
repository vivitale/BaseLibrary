package talex.zsw.baselibrary.view.Volley;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import talex.zsw.baselibrary.util.klog.KLog;

public class GsonRequest<T> extends Request<T>
{
	private final Listener<T> mListener;
	private Gson mGson;
	private Class<T> mClass;
	//获取cookie
//	private String mHeader, cookieFromResponse;
//	private Map<String, String> sendHeader = new HashMap<String, String>(1);

	public GsonRequest(int method, String url, Class<T> clazz,
					   Listener<T> listener, ErrorListener errorLisener)
	{
		super(method, url, errorLisener);
		KLog.v("地址:" + url);
		mGson = new Gson();
		mClass = clazz;
		mListener = listener;
		setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 2.0f));
	}

	public GsonRequest(String url, Class<T> clazz, Listener<T> listener,
					   ErrorListener errorListener)
	{
		this(Method.GET, url, clazz, listener, errorListener);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response)
	{
		try
		{
			String jsonString = new String(response.data,
				HttpHeaderParser.parseCharset(response.headers));
			KLog.json(jsonString);
			try
			{
				jsonString = new String(response.data, "utf-8");
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
				return Response.error(new ParseError(e));
			}
//			mHeader = response.headers.toString();
//			Log.w("LOG", "get headers in parseNetworkResponse " + response.headers.toString());
//			//使用正则表达式从reponse的头中提取cookie内容的子串
//			Pattern pattern = Pattern.compile("Set-Cookie.*?;");
//			Matcher m = pattern.matcher(mHeader);
//			if (m.find())
//			{
//				cookieFromResponse = m.group();
//				Log.w("LOG", "cookie from server " + cookieFromResponse);
//			}
//			//去掉cookie末尾的分号
//			cookieFromResponse = cookieFromResponse.substring(11, cookieFromResponse.length() - 1);
//			Log.w("LOG", "cookie substring " + cookieFromResponse);

			return Response.success(mGson.fromJson(jsonString, mClass),
				HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e)
		{
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response)
	{
		mListener.onResponse(response);
	}

//	@Override
//	public Map<String, String> getHeaders() throws AuthFailureError
//	{
//		return sendHeader;
//	}
//
//	public void setSendCookie(String cookie)
//	{
//		sendHeader.put("Cookie", cookie);
//	}
}
