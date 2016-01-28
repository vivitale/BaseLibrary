package talex.zsw.baselibrary.util.klog;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This is a Log tool，with this you can the following
 * <ol>
 * <li>use KLog.d(),you could print whether the method execute,and the default tag is current class's name</li>
 * <li>use KLog.d(msg),you could print log as before,and you could location the method with a click in Android Studio Logcat</li>
 * <li>use KLog.json(),you could print json string with well format automatic</li>
 * </ol>
 *
 * @author zhaokaiqiang
 *         github https://github.com/ZhaoKaiQiang/KLog
 *         15/11/17 扩展功能，添加对文件的支持
 *         15/11/18 扩展功能，增加对XML的支持，修复BUG
 */
public class KLog implements Constant
{

	private static boolean IS_SHOW_LOG = true;
	private static String TAG = "TALEX";

	public static void init(boolean isShowLog)
	{
		IS_SHOW_LOG = isShowLog;
	}

	public static void v()
	{
		printLog( V, null, DEFAULT_MESSAGE );
	}

	public static void v(Object msg)
	{
		printLog( V, null, msg );
	}

	public static void v(String tag, String msg, Object... args)
	{
		printLog( V, tag, msg, args );
	}

	public static void d()
	{
		printLog( D, null, DEFAULT_MESSAGE );
	}

	public static void d(Object msg)
	{
		printLog( D, null, msg );
	}

	public static void d(String tag, Object msg, Object... args)
	{
		printLog( D, tag, msg, args );
	}

	public static void i()
	{
		printLog( I, null, DEFAULT_MESSAGE );
	}

	public static void i(Object msg)
	{
		printLog( I, null, msg );
	}

	public static void i(String tag, Object msg, Object... args)
	{
		printLog( I, tag, msg, args );
	}

	public static void w()
	{
		printLog( W, null, DEFAULT_MESSAGE );
	}

	public static void w(Object msg)
	{
		printLog( W, null, msg );
	}

	public static void w(String tag, Object msg, Object... args)
	{
		printLog( W, tag, msg, args );
	}

	public static void e()
	{
		printLog( E, null, DEFAULT_MESSAGE );
	}

	public static void e(Object msg)
	{
		printLog( E, null, msg );
	}

	public static void e(String tag, Object msg, Object... args)
	{
		printLog( E, tag, msg, args );
	}

	public static void a()
	{
		printLog( A, null, DEFAULT_MESSAGE );
	}

	public static void a(Object msg)
	{
		printLog( A, null, msg );
	}

	public static void a(String tag, Object msg, Object... args)
	{
		printLog( A, tag, msg, args );
	}

	public static void json(String jsonFormat)
	{
		printLog( JSON, null, jsonFormat );
	}

	public static void json(String tag, String jsonFormat)
	{
		printLog( JSON, tag, jsonFormat );
	}

	public static void xml(String xml)
	{
		printLog( XML, null, xml );
	}

	public static void xml(String tag, String xml)
	{
		printLog( XML, tag, xml );
	}

	public static void file(File targetDirectory, Object msg)
	{
		printFile( null, targetDirectory, null, msg );
	}

	public static void file(String tag, File targetDirectory, Object msg)
	{
		printFile( tag, targetDirectory, null, msg );
	}

	public static void file(String tag, File targetDirectory, String fileName, Object msg)
	{
		printFile( tag, targetDirectory, fileName, msg );
	}

	private static void printLog(int type, String tagStr, Object objectMsg, Object... args)
	{
		if(!IS_SHOW_LOG)
		{
			return;
		}

		if(args.length > 0 && objectMsg != null)
		{
			objectMsg = String.format( (String) objectMsg, args );
		}

		String[] contents = wrapperContent( tagStr, objectMsg );
		String tag = contents[0];
		String msg = contents[1];
		String headString = contents[2];

		switch(type)
		{
			case V:
			case D:
			case I:
			case W:
			case E:
			case A:
				BaseLog.printDefault( type, tag, headString + msg );
				break;
			case JSON:
				JsonLog.printJson( tag, msg, headString );
				break;
			case XML:
				XmlLog.printXml( tag, msg, headString );
				break;
		}
	}


	private static void printFile(String tagStr, File targetDirectory, String fileName,
		Object objectMsg)
	{

		if(!IS_SHOW_LOG)
		{
			return;
		}

		String[] contents = wrapperContent( tagStr, objectMsg );
		String tag = contents[0];
		String msg = contents[1];
		String headString = contents[2];

		FileLog.printFile( tag, targetDirectory, fileName, headString, msg );
	}

	private static String[] wrapperContent(String tagStr, Object objectMsg)
	{

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		int index = 5;
		String className = stackTrace[index].getFileName();
		String methodName = stackTrace[index].getMethodName();
		int lineNumber = stackTrace[index].getLineNumber();
		String methodNameShort =
			methodName.substring( 0, 1 ).toUpperCase() + methodName.substring( 1 );
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( "[ (" ).append( className ).append( ":" ).append( lineNumber )
			.append( ")#" ).append( methodNameShort ).append( " ] " );

		String tag = (tagStr == null ? TAG : tagStr);
		String msg = (objectMsg == null) ? NULL_TIPS : objectMsg.toString();
		String headString = stringBuilder.toString();

		return new String[]{tag, msg, headString};
	}

	/**
	 * =================打印相关====================
	 * <p/>
	 * 需要在AndroidManifest中加入以下权限
	 * <uses-permission
	 * android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
	 * <uses-permission
	 * android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
	 */
	protected static final String PATH_ROOT = Environment.getExternalStorageDirectory() + "/log/";
	protected static final String PATH_LOG_LOG = PATH_ROOT + "normal/";
	protected static final String PATH_LOG_ERROR = PATH_ROOT + "error/";

	public static void pv(String msg)
	{
		printLog( V, null, msg );
		print( PATH_LOG_LOG, generateTag( getCallerMethodName() ), "-V- " + msg );
	}

	public static void pd(String msg)
	{
		printLog( D, null, msg );
		print( PATH_LOG_LOG, generateTag( getCallerMethodName() ), "-D- " + msg );
	}

	public static void pi(String msg)
	{
		printLog( I, null, msg );
		print( PATH_LOG_LOG, generateTag( getCallerMethodName() ), "-I- " + msg );
	}

	public static void pw(String msg)
	{
		printLog( W, null, msg );
		print( PATH_LOG_LOG, generateTag( getCallerMethodName() ), "-W- " + msg );
	}

	public static void pe(String msg)
	{
		printLog( E, null, msg );
		print( PATH_LOG_ERROR, generateTag( getCallerMethodName() ), "-E- " + msg );
	}

	public static void pa(String msg)
	{
		printLog( A, null, msg );
		print( PATH_LOG_ERROR, generateTag( getCallerMethodName() ), "-WTF- " + msg );
	}

	public static void pjson(String jsonFormat)
	{
		printLog( JSON, null, jsonFormat );
		print( PATH_LOG_LOG, generateTag( getCallerMethodName() ), "-JSON- " + jsonFormat );
	}

	public static void print(String path, String tag, String msg)
	{
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat( "", Locale.SIMPLIFIED_CHINESE );
		dateFormat.applyPattern( "yyyy-MM-dd" );
		path += dateFormat.format( date ) + ".log";
		dateFormat.applyPattern( "[yyyy-MM-dd HH:mm:ss]" );
		String time = dateFormat.format( date );
		File file = new File( path );
		if(!file.exists())
		{
			createDipPath( path );
		}
		BufferedWriter out = null;
		try
		{
			out =
				new BufferedWriter( new OutputStreamWriter( new FileOutputStream( file, true ) ) );
			out.write( time + " " + tag + " " + msg + "\r\n" );
		} catch(Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if(out != null)
				{
					out.close();
				}
			} catch(IOException e)
			{
				e.printStackTrace();
			}
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
		callerClazzName = callerClazzName.substring( callerClazzName.lastIndexOf( "." ) + 1 );
		tag = String.format( tag, callerClazzName, caller.getMethodName(), caller.getLineNumber() );
		return TAG + "  " + tag;
	}

	/**
	 * 根据文件路径 递归创建文件
	 *
	 * @param file
	 */
	public static void createDipPath(String file)
	{
		String parentFile = file.substring( 0, file.lastIndexOf( "/" ) );
		File file1 = new File( file );
		File parent = new File( parentFile );
		if(!file1.exists())
		{
			parent.mkdirs();
			try
			{
				file1.createNewFile();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
