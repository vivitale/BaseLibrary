package talex.zsw.baselibrary.view.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import talex.zsw.baselibrary.util.XmlUtil;
import talex.zsw.baselibrary.util.klog.KLog;


public class XMLRequest<T> extends Request<T>
{
	private final Listener<T> mListener;
	private Class<T> mClass;
	private Map<String, String> map;

	public XMLRequest(int method, String url, String xmlData, Class<T> clazz,
					  Listener<T> listener, ErrorListener errorListener)
	{
		super(method, url, errorListener);
		KLog.i( "请求地址:" + url);
		mClass = clazz;
		mListener = listener;
		map = new HashMap<>();
		map.put("data", xmlData);
		setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 2.0f));
	}

	public XMLRequest(String url, String xmlData, Class<T> clazz, Listener<T> listener,
					  ErrorListener errorListener)
	{
		this(Method.POST, url, xmlData, clazz, listener, errorListener);
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError
	{
		for (Map.Entry<String, String> entry : map.entrySet())
		{
			KLog.v( "请求参数:" + entry.getKey() + "--->" + entry.getValue());
		}
		return map;
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response)
	{
		try
		{
			String responseString = new String(response.data,
				HttpHeaderParser.parseCharset(response.headers));
			KLog.v( "返回数据:" + responseString);
			try
			{
				responseString = new String(response.data, "utf-8");
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
				return Response.error(new ParseError(e));
			}
			return Response.success(XmlUtil.toBean(responseString, mClass),
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
}
