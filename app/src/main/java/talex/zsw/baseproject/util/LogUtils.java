package talex.zsw.baseproject.util;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author talexemail:vvtale@gmail.com
 * @ClassName: LogUtils
 * @Description: 一个简单的日志工具封装，可简单自己定位TAG，TAG的格式为：类名[方法名， 调用行数]
 * 需要先写定义PROJECT的值，这样日志会同时打印到SD卡的PROJECT文件夹下
 * 同时需要在AndroidManifest中加入以下权限
 * <uses-permission
 * android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 * <uses-permission
 * android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
 * @date 2014年5月14日 下午4:34:34
 */
@SuppressWarnings("ALL") public abstract class LogUtils
{
	/**
	 * 自行定义项目名称
	 */
	protected static final String PROJECT = "公共项目";

	/**
	 * @Fields TAG : Tag开头
	 */
	protected static final String TAG = "TALEX->";
	/**
	 * 日志开关
	 */
	protected static final boolean LOG_OPEN_LOG = true;//日志开关
	protected static final boolean LOG_OPEN_POINT = true;//打印开关
	protected static final boolean LOG_OPEN_POINT_ERROR = true;// 只打印error
	/**
	 * 日志类型开关，必须 LOG_OPEN_DEBUG = true的时候才能启作用
	 */
	public static boolean allowD = true;
	public static boolean allowE = true;
	public static boolean allowI = true;
	public static boolean allowV = true;
	public static boolean allowW = true;
	public static boolean allowWtf = true;
	/**
	 * 日志打印地址
	 */
	protected static final String PATH_ROOT = Environment
		.getExternalStorageDirectory() + "/log/" + PROJECT + "/";
	protected static final String PATH_LOG_LOG = PATH_ROOT + "normal/";
	protected static final String PATH_LOG_ERROR = PATH_ROOT + "error/";

	// d方法
	// -----------------------------------------------------------------------------------------------------------------
	public static void d(String content)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowD && LOG_OPEN_LOG)
		{
			Log.d(tag, content);
		}
		if (LOG_OPEN_POINT)
		{
			point(PATH_LOG_LOG, tag, "-D-  " + content);
		}
	}

	public static void d(String content, Throwable tr)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowD && LOG_OPEN_LOG)
		{
			Log.d(tag, content, tr);
		}
		if (LOG_OPEN_POINT)
		{
			point(PATH_LOG_LOG, tag,
				"-D-  " + content + "  " + "##" + tr.toString());
		}
	}

	// e方法
	// ------------------------------------------------------------------------------------------------------------------
	public static void e(String content)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowE && LOG_OPEN_LOG)
		{
			Log.e(tag, content);
		}
		if (LOG_OPEN_POINT_ERROR)
		{
			point(PATH_LOG_ERROR, tag, "-E-  " + content);
		}
	}

	public static void e(String content, Throwable tr)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowE && LOG_OPEN_LOG)
		{
			Log.e(tag, content, tr);
		}
		if (LOG_OPEN_POINT_ERROR)
		{
			point(PATH_LOG_ERROR, tag,
				"-E-  " + content + "  " + "##" + tr.toString());
		}
	}

	// i方法
	// -----------------------------------------------------------------------------------------------------------------
	public static void i(String content)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowI && LOG_OPEN_LOG)
		{
			Log.i(tag, content);
		}
		if (LOG_OPEN_POINT)
		{
			point(PATH_LOG_LOG, tag, "-I-  " + content);
		}
	}

	public static void i(String content, Throwable tr)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowI && LOG_OPEN_LOG)
		{
			Log.i(tag, content, tr);
		}
		if (LOG_OPEN_POINT)
		{
			point(PATH_LOG_LOG, tag,
				"-I-  " + content + "  " + "##" + tr.toString());
		}
	}

	// v方法
	// -----------------------------------------------------------------------------------------------------------------
	public static void v(String content)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowV && LOG_OPEN_LOG)
		{
			Log.v(tag, content);
		}
		if (LOG_OPEN_POINT)
		{
			point(PATH_LOG_LOG, tag, "-V-  " + content);
		}
	}

	public static void v(String content, Throwable tr)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowV && LOG_OPEN_LOG)
		{
			Log.v(tag, content, tr);
		}
		if (LOG_OPEN_POINT)
		{
			point(PATH_LOG_LOG, tag,
				"-V-  " + content + "  " + "##" + tr.toString());
		}
	}

	// w方法
	// -----------------------------------------------------------------------------------------------------------------
	public static void w(String content)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowW && LOG_OPEN_LOG)
		{
			Log.w(tag, content);
		}
		if (LOG_OPEN_POINT)
		{
			point(PATH_LOG_LOG, tag, "-W-  " + content);
		}
	}

	public static void w(String content, Throwable tr)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowW && LOG_OPEN_LOG)
		{
			Log.w(tag, content, tr);
		}
		if (LOG_OPEN_POINT)
		{
			point(PATH_LOG_LOG, tag,
				"-W-  " + content + "  " + "##" + tr.toString());
		}
	}

	public static void w(Throwable tr)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowW && LOG_OPEN_LOG)
		{
			Log.w(tag, tr);
		}
		if (LOG_OPEN_POINT)
		{
			point(PATH_LOG_LOG, tag, "-W-  " + "##" + tr.toString());
		}
	}

	// wtf方法，非常恐怖的错误，这种错误原则上不应该出现在系统中，哈哈
	// -----------------------------------------------------------------------------------------------------------------
	public static void wtf(String content)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowWtf && LOG_OPEN_LOG)
		{
			Log.wtf(tag, content);
		}
		if (LOG_OPEN_POINT_ERROR)
		{
			point(PATH_LOG_ERROR, tag, "-WTF-  " + content);
		}
	}

	public static void wtf(String content, Throwable tr)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowWtf && LOG_OPEN_LOG)
		{
			Log.wtf(tag, content, tr);
		}
		if (LOG_OPEN_POINT_ERROR)
		{
			point(PATH_LOG_ERROR, tag,
				"-WTF-  " + content + "  " + "##" + tr.toString());
		}
	}

	public static void wtf(Throwable tr)
	{
		String tag = generateTag(getCallerMethodName());
		if (allowWtf && LOG_OPEN_LOG)
		{
			Log.wtf(tag, tr);
		}
		if (LOG_OPEN_POINT_ERROR)
		{
			point(PATH_LOG_ERROR, tag, "-WTF-  " + "##" + tr.toString());
		}
	}

	// 跟踪到调用该日志的方法
	private static StackTraceElement getCallerMethodName()
	{
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		return stacks[4];
	}

	// 规范TAG格式：类名[方法名， 调用行数]
	private static String generateTag(StackTraceElement caller)
	{
		String tag = "%s[%s, %d]";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName
			.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(),
			caller.getLineNumber());
		return TAG + tag;
	}

	public static void point(String path, String tag, String msg)
	{
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("",
			Locale.SIMPLIFIED_CHINESE);
		dateFormat.applyPattern("yyyy-MM-dd");
		path += dateFormat.format(date) + ".log";
		dateFormat.applyPattern("[yyyy-MM-dd HH:mm:ss]");
		String time = dateFormat.format(date);
		File file = new File(path);
		if (!file.exists())
		{
			createDipPath(path);
		}
		BufferedWriter out = null;
		try
		{
			out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file, true)));
			out.write(time + " " + tag + " " + msg + "\r\n");
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (out != null)
				{
					out.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	;

	/**
	 * 根据文件路径 递归创建文件
	 *
	 * @param file
	 */
	public static void createDipPath(String file)
	{
		String parentFile = file.substring(0, file.lastIndexOf("/"));
		File file1 = new File(file);
		File parent = new File(parentFile);
		if (!file1.exists())
		{
			parent.mkdirs();
			try
			{
				file1.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
