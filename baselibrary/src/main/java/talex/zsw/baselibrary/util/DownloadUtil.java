package talex.zsw.baselibrary.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import talex.zsw.baselibrary.util.klog.KLog;

/**
 * 提供常用的下载方法，包括文本文件，音频文件的下载，同时满足直接读取文本文件将其显示的显示的方法
 * 建议在使用此工具类，在声明构造方法时传入Activity的上下文（当不是在Activity内调用此方法时，如在某service中下载，最好将上下文传入，
 * 在使用工具类时，传递给构造函数，这样可以获得更明确的前端提示信息，当然，后台同样提供错误信息），方便调试
 */
public class DownloadUtil
{
	private Context context;//上下文
	public static String ERROTAG = "DownLoadUtilError";

	/**
	 * 构造函数
	 *
	 * @param context 需要提供上下文,在出现错误时可通过上下文来提供Toast信息，因而最好不要设为NULL
	 */
	public DownloadUtil(Context context)
	{
		if (context != null)
		{
			this.context = context;
		}
		else
		{
			KLog.w( "上下文为空");
		}
	}

	/**
	 * 获取流文件
	 *
	 * @param url 文件url
	 * @return 网络文件流文件
	 */
	private InputStream getinputStream(String url)
	{
		InputStream input = null;
		try
		{
			URL connectURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(1000);
			input = conn.getInputStream();
		} catch (MalformedURLException e)
		{
			KLog.e( "请确认输入的地址正确或符合标准格式（http://）并确认权限是否添加");
			e.printStackTrace();
		} catch (IOException e)
		{
			toasterror("连接失败");
			KLog.w( "请求的路径异常！");
			e.printStackTrace();
		}
		return input;
	}

	/**
	 * 获得网络文本文件的内容
	 *
	 * @param url 文本文件的url
	 * @return 文本文件的内容
	 */
	public String gettextfilestring(String url)
	{
		InputStream input = getinputStream(url);
		StringBuilder sb = new StringBuilder("");
		BufferedReader bfr = new BufferedReader(new InputStreamReader(input));
		String line = "";
		try
		{
			while ((line = bfr.readLine()) != null)
			{
				sb.append(line);
			}
		} catch (IOException e)
		{
			toasterror("流文件读写错误");
			e.printStackTrace();
		} finally
		{
			try
			{
				bfr.close();
			} catch (IOException e)
			{
				toasterror("流文件未能正常关闭");
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 下载文件到应用目录下
	 *
	 * @param url      网络地址
	 * @param filename 保存名称
	 */
	public void downFiletoDecive(String url, String filename)
	{
		if ((url != null && !"".equals(url)) && (filename != null && !"".equals(filename)))
		{
			InputStream input = getinputStream(url);
			FileOutputStream outStream = null;
			try
			{
				outStream = context.openFileOutput(filename,
					Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
				int temp = 0;
				byte[] data = new byte[1024];
				while ((temp = input.read(data)) != -1)
				{
					outStream.write(data, 0, temp);
				}
			} catch (FileNotFoundException e)
			{
				toasterror("请传入正确的上下文");
				e.printStackTrace();
			} catch (IOException e)
			{
				toastemessage("读写错误");
				e.printStackTrace();
			} finally
			{
				try
				{
					outStream.flush();
					outStream.close();
				} catch (IOException e)
				{
					toasterror("流文件未能正常关闭");
					e.printStackTrace();
				}
			}
		}
		toastemessage("下载成功");
	}

	/**
	 * 提供下载的方法
	 *
	 * @param url      下载文件的网络路径, 网络地址需加上“http://"头
	 * @param path     下载到本地的路径
	 * @param filename 为下载的文件命名
	 */
	public void downFiletoSDCard(String url, String path, String filename)
	{
		if ((url != null && !"".equals(url)) && (path != null) &&
			(filename != null && !"".equals(filename)))
		{
			InputStream input = getinputStream(url);
			downloader(input, path, filename);
		}
		else
		{
				/*
				 * 对不合发的参数做处理
				 */
			if (url == null || "".equals(url))
			{
				toasterror("url不能为空或为“”");
			}
			if (path == null)
			{
				toasterror("path不能为空");
			}
			if (filename == null || "".equals(filename))
			{
				toasterror("filename不能为空");
			}
		}
	}

	/**
	 * 显示错误信息
	 *
	 * @param message 错误信息内容
	 */
	private void toasterror(String message)
	{
		if (context != null)
		{
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		}
		try
		{
			throw new Exception(message);
		} catch (Exception e)
		{
			KLog.w( "未能捕获所有异常");
			e.printStackTrace();
		}
	}

	/**
	 * 显示提示信息
	 *
	 * @param message 提示信息内容
	 */
	private void toastemessage(String message)
	{
		if (context != null)
		{
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		}
		KLog.i( message);
	}

	/**
	 * 文件下载
	 *
	 * @param input 文件流
	 * @param path  存储路径
	 * @param name  下载后文件的名字
	 */
	private void downloader(InputStream input, String path, String name)
	{
		String realpath = null;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			realpath = Environment.getExternalStorageDirectory() + path;
		}
		else
		{
			toasterror("SDCard异常，请检查是否存在SDCard并确认其状态和程序的访问权限");
		}
		if (!dirsexits(realpath))
		{
			creatdir(realpath);
		}
		if (!fileexits(realpath + "/" + name))
		{
			makefile(input, realpath, name);
		}
		else
		{
			toasterror("已存在的命名的文件，请删掉原有文件或重命名");
		}
	}

	/**
	 * 判断文件存放文件的目录是否存在，同时对外提供接口
	 *
	 * @param path 存放文件目录
	 */
	public boolean dirsexits(String path)
	{
		File file = new File(path);
		KLog.i( "文件" + path + "存在情况为：" + file.exists());
		return file.exists();
	}

	/**
	 * 判断文件是否存在，同时对外提供接口
	 *
	 * @param path 文件路径及名称
	 * @return
	 */
	public boolean fileexits(String path)
	{
		return dirsexits(path);
	}

	/**
	 * 创建指定的目录
	 *
	 * @param path 目录的路径
	 */
	private void creatdir(String path)
	{
		File file = new File(path);
		file.mkdirs();
	}

	/**
	 * 创建文件
	 *
	 * @param input    输入流
	 * @param realpath 真实路径
	 * @param name     文件存放的名字
	 */
	private void makefile(InputStream input, String realpath, String name)
	{
		File file = new File(realpath + "/" + name);
		OutputStream out = null;
		try
		{
			out = new FileOutputStream(file);
			int temp = 0;
			byte[] data = new byte[1024];
			while ((temp = input.read(data)) != -1)
			{
				out.write(data, 0, temp);
			}
		} catch (FileNotFoundException e)
		{
			toasterror("创建文件失败");
			e.printStackTrace();
		} catch (IOException e)
		{
			toasterror("读写错误");
			e.printStackTrace();
		} finally
		{
			try
			{
				assert out != null;
				out.flush();
				out.close();
			} catch (IOException e)
			{
				toasterror("流文件未能正常关闭");
				e.printStackTrace();
			}
		}
		toastemessage("下载成功");
	}
}
