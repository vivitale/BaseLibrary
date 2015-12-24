package talex.zsw.baselibrary.util;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称: BaseProject
 * 作用: 文件操作类
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-04-0004 13:38 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressWarnings("ALL") public class FileUtils
{
	/**
	 * 判断指定路径的文件或文件夹是否存在
	 */
	public static boolean isFileExists(String path)
	{
		File file = new File(path);
		return file.exists();
	}

	/**
	 * 删除指定路径的文件
	 */
	public static void deleteFile(String path)
	{
		File file = new File(path);
		if (file.exists())
		{
			file.delete();
		}
	}

	/**
	 * 将文本读取到List中并返回
	 *
	 * @param path - 文件路径
	 * @return List<String> - 返回读取文件行的集合
	 */
	public static List<String> getFileContent(String path)
	{
		List<String> strList = new ArrayList<String>();
		File file = new File(path);
		InputStreamReader read = null;
		BufferedReader reader = null;
		try
		{
			read = new InputStreamReader(new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null)
			{
				strList.add(line);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (read != null)
			{
				try
				{
					read.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (reader != null)
			{
				try
				{
					reader.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return strList;
	}

	/**
	 * 获取指定行的值
	 *
	 * @param path - 文件路径
	 * @param row  - 指定行
	 * @return String - 返回指定行的数据,没有指定行时数据返回空字符串
	 */
	public static String listFileByRow(String path, Integer row)
	{
		List<String> strList = getFileContent(path);
		int size = strList.size();
		if (size >= (row - 1))
		{
			return strList.get(row - 1);
		}
		else
		{
			return "";
		}
	}

	/**
	 * 读取第几行到第几行的值
	 *
	 * @param path      - 文件路径
	 * @param startLine - 开始行
	 * @param endLine   - 结束行
	 * @return List<String> - 返回指定区间的集合
	 */
	public static List<String> listFileByRegionRow(String path,
												   Integer startLine, Integer endLine)
	{
		List<String> strList = getFileContent(path);
		// 指定区间的值存到regionList
		List<String> regionList = new ArrayList<String>();
		int size = strList.size();
		if (size >= (endLine - 1))
		{
			for (int i = startLine; i <= endLine; i++)
			{
				regionList.add(strList.get(i - 1));
			}
		}
		return regionList;
	}

	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 * 一次读取一个字节
	 *
	 * @param fileName 文件的路径
	 */
	public static void readFileByByte(String fileName)
	{
		File file = new File(fileName);
		InputStream in = null;
		try
		{
			System.out.println("以字节为单位读取文件内容，一次读一个字节：");
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1)
			{
				System.out.write(tempbyte);
			}
			in.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				} catch (IOException ignored)
				{
				}
			}
		}
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * 一次读一个字符
	 *
	 * @param fileName 文件名
	 */
	public static void readFileByChar(String fileName)
	{
		File file = new File(fileName);
		Reader reader = null;
		try
		{
			System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1)
			{
				// 对于windows下，rn这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。
				if (((char) tempchar) != 'r')
				{
					System.out.print((char) tempchar);
				}
			}
			reader.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				} catch (IOException ignored)
				{
				}
			}
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 *
	 * @param fileName 文件名
	 */
	public static void readFileByLines(String fileName)
	{
		File file = new File(fileName);
		BufferedReader reader = null;
		try
		{
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null)
			{
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				} catch (IOException ignored)
				{
				}
			}
		}
	}

	/**
	 * 随机读取文件内容
	 *
	 * @param fileName 文件名
	 */
	public static void readFileByRandomAccess(String fileName)
	{
		RandomAccessFile randomFile = null;
		try
		{
			System.out.println("随机读取一段文件内容：");
			// 打开一个随机访问文件流，按只读方式
			randomFile = new RandomAccessFile(fileName, "r");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 读文件的起始位置
			int beginIndex = (fileLength > 4) ? 4 : 0;
			// 将读文件的开始位置移到beginIndex位置。
			randomFile.seek(beginIndex);
			byte[] bytes = new byte[10];
			int byteread = 0;
			// 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
			// 将一次读取的字节数赋给byteread
			while ((byteread = randomFile.read(bytes)) != -1)
			{
				System.out.write(bytes, 0, byteread);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (randomFile != null)
			{
				try
				{
					randomFile.close();
				} catch (IOException ignored)
				{
				}
			}
		}
	}

	/**
	 * 显示输入流中还剩的字节数
	 *
	 * @param in 输入流
	 */
	public static void showAvailableBytes(InputStream in)
	{
		try
		{
			System.out.println("当前字节输入流中的字节数为:" + in.available());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * 写文件
	 */
	public static void write(String string, String path)
	{
		try
		{
			// =========== 1 ==============
			File file = new File(path);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(string);
			// fw.write(string,0,string.length());
			// fw.flush();
			fileWriter.close();
			// =========== 2 ==============
			// OutputStreamWriter osw = new OutputStreamWriter(
			// new FileOutputStream(path));
			// osw.write(string, 0, string.length());
			// osw.flush();
			// osw.close();
			// =========== 3 ==============
			// PrintWriter pw = new PrintWriter(new OutputStreamWriter(
			// new FileOutputStream(path)), true);
			// pw.println(string);
			// pw.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 存储文本数据
	 *
	 * @param context  程序上下文
	 * @param fileName 文件名，要在系统内保持唯一
	 * @param content  文本内容
	 * @return boolean 存储成功的标志
	 */
	public static boolean writeFile(Context context, String fileName,
									String content)
	{
		boolean success = false;
		FileOutputStream fos = null;
		try
		{
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			byte[] byteContent = content.getBytes();
			fos.write(byteContent);
			success = true;
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (fos != null)
				{
					fos.close();
				}
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		return success;
	}

	/**
	 * 读取文本数据
	 *
	 * @param context  程序上下文
	 * @param fileName 文件名
	 * @return String, 读取到的文本内容，失败返回null
	 */
	public static String readFile(Context context, String fileName)
	{
		if (!isFileExists(fileName))
		{
			return null;
		}
		FileInputStream fis = null;
		String content = null;
		try
		{
			fis = context.openFileInput(fileName);
			if (fis != null)
			{
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
				while (true)
				{
					int readLength = fis.read(buffer);
					if (readLength == -1)
					{
						break;
					}
					arrayOutputStream.write(buffer, 0, readLength);
				}
				fis.close();
				arrayOutputStream.close();
				content = new String(arrayOutputStream.toByteArray());
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
			content = null;
		} finally
		{
			try
			{
				if (fis != null)
				{
					fis.close();
				}
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * 读取文本数据
	 *
	 * @param filePath 文件名
	 * @return String, 读取到的文本内容，失败返回null
	 */
	public static String readFile(String filePath)
	{
		if (filePath == null || !new File(filePath).exists())
		{
			return null;
		}
		FileInputStream fis = null;
		String content = null;
		try
		{
			fis = new FileInputStream(filePath);
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			while (true)
			{
				int readLength = fis.read(buffer);
				if (readLength == -1)
				{
					break;
				}
				arrayOutputStream.write(buffer, 0, readLength);
			}
			fis.close();
			arrayOutputStream.close();
			content = new String(arrayOutputStream.toByteArray());
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
			content = null;
		} finally
		{
			try
			{
				if (fis != null)
				{
					fis.close();
				}
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * 读取文本数据
	 *
	 * @param context  程序上下文
	 * @param fileName 文件名
	 * @return String, 读取到的文本内容，失败返回null
	 */
	public static String readAssets(Context context, String fileName)
	{
		InputStream is = null;
		String content = null;
		try
		{
			is = context.getAssets().open(fileName);
			if (is != null)
			{
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
				while (true)
				{
					int readLength = is.read(buffer);
					if (readLength == -1)
					{
						break;
					}
					arrayOutputStream.write(buffer, 0, readLength);
				}
				is.close();
				arrayOutputStream.close();
				content = new String(arrayOutputStream.toByteArray());
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
			content = null;
		} finally
		{
			try
			{
				if (is != null)
				{
					is.close();
				}
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * 存储单个Parcelable对象
	 *
	 * @param context      程序上下文
	 * @param fileName     文件名，要在系统内保持唯一
	 * @param parcelObject 对象必须实现Parcelable
	 * @return boolean 存储成功的标志
	 */
	public static boolean writeParcelable(Context context, String fileName,
										  Parcelable parcelObject)
	{
		boolean success = false;
		FileOutputStream fos = null;
		try
		{
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			Parcel parcel = Parcel.obtain();
			parcel.writeParcelable(parcelObject,
				Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
			byte[] data = parcel.marshall();
			fos.write(data);
			success = true;
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fos != null)
			{
				try
				{
					fos.close();
				} catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		}
		return success;
	}


	/**
	 * 存储List对象
	 *
	 * @param context  程序上下文
	 * @param fileName 文件名，要在系统内保持唯一
	 * @param list     对象数组集合，对象必须实现Parcelable
	 * @return boolean 存储成功的标志
	 */
	public static boolean writeParcelableList(Context context, String fileName,
											  List<Parcelable> list)
	{
		boolean success = false;
		FileOutputStream fos = null;
		try
		{
			if (list != null)
			{
				fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
				Parcel parcel = Parcel.obtain();
				parcel.writeList(list);
				byte[] data = parcel.marshall();
				fos.write(data);
				success = true;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fos != null)
			{
				try
				{
					fos.close();
				} catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * 读取单个数据对象
	 *
	 * @param context  程序上下文
	 * @param fileName 文件名
	 * @return Parcelable, 读取到的Parcelable对象，失败返回null
	 */
	public static Parcelable readParcelable(Context context, String fileName,
											ClassLoader classLoader)
	{
		Parcelable parcelable = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try
		{
			fis = context.openFileInput(fileName);
			if (fis != null)
			{
				bos = new ByteArrayOutputStream();
				byte[] b = new byte[4096];
				int bytesRead;
				while ((bytesRead = fis.read(b)) != -1)
				{
					bos.write(b, 0, bytesRead);
				}
				byte[] data = bos.toByteArray();
				Parcel parcel = Parcel.obtain();
				parcel.unmarshall(data, 0, data.length);
				parcel.setDataPosition(0);
				parcelable = parcel.readParcelable(classLoader);
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
			parcelable = null;
		} finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (bos != null)
			{
				try
				{
					bos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return parcelable;
	}

	/**
	 * 读取数据对象列表
	 *
	 * @param context  程序上下文
	 * @param fileName 文件名
	 * @return List, 读取到的对象数组，失败返回null
	 */
	@SuppressWarnings("unchecked")
	public static List<Parcelable> readParcelableList(Context context,
													  String fileName, ClassLoader classLoader)
	{
		List<Parcelable> results = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try
		{
			fis = context.openFileInput(fileName);
			if (fis != null)
			{
				bos = new ByteArrayOutputStream();
				byte[] b = new byte[4096];
				int bytesRead;
				while ((bytesRead = fis.read(b)) != -1)
				{
					bos.write(b, 0, bytesRead);
				}
				byte[] data = bos.toByteArray();
				Parcel parcel = Parcel.obtain();
				parcel.unmarshall(data, 0, data.length);
				parcel.setDataPosition(0);
				results = parcel.readArrayList(classLoader);
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
			results = null;
		} finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (bos != null)
			{
				try
				{
					bos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return results;
	}

	/**
	 * 保存Serializable数据
	 *
	 * @param context  上下文
	 * @param fileName 文件名
	 * @param data     Serializable数据
	 * @return 成功失败标志
	 */
	public static boolean saveSerializable(Context context, String fileName,
										   Serializable data)
	{
		boolean success = false;
		ObjectOutputStream oos = null;
		try
		{
			oos = new ObjectOutputStream(context.openFileOutput(fileName,
				Context.MODE_PRIVATE));
			oos.writeObject(data);
			success = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (oos != null)
			{
				try
				{
					oos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * 读取Serializable数据
	 *
	 * @param context  上下文
	 * @param fileName 文件名
	 * @return Serializable数据
	 */
	public static Serializable readSerialLizable(Context context,
												 String fileName)
	{
		Serializable data = null;
		ObjectInputStream ois = null;
		try
		{
			ois = new ObjectInputStream(context.openFileInput(fileName));
			data = (Serializable) ois.readObject();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (ois != null)
			{
				try
				{
					ois.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	/**
	 * 从assets文件夹里边读取字符串
	 *
	 * @param context  上下文
	 * @param fileName 文件名
	 * @return 字符串
	 */
	public static String getFromAssets(Context context, String fileName)
	{
		try
		{
			InputStreamReader inputReader = new InputStreamReader(context
				.getResources().getAssets().open(fileName));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";
			String Result = "";
			while ((line = bufReader.readLine()) != null)
			{
				Result += line;
			}
			return Result;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 复制文件
	 *
	 * @param srcFile 原文件路径
	 * @param dstFile 复制之后的文件路径
	 */
	public static boolean copy(String srcFile, String dstFile)
	{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try
		{
			File dst = new File(dstFile);
			if (!dst.getParentFile().exists())
			{
				dst.getParentFile().mkdirs();
			}
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(dstFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) != -1)
			{
				fos.write(buffer, 0, len);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (fos != null)
			{
				try
				{
					fos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	/**
	 * 复制单个文件
	 *
	 * @param oldPath String 原文件路径 如：c:/fqf.txt
	 * @param newPath String 复制后路径 如：f:/搜索fqf.txt
	 */
	public static void copyFile(String oldPath, String newPath)
	{
		try
		{
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			File newFile = new File(newPath);
			if (!newFile.exists())
			{
				newFile.createNewFile();
			}
			if (oldfile.exists())
			{ // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1)
				{
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e)
		{
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 复制整个文件夹内容
	 *
	 * @param oldPath String 原文件路径 如：c:/fqf
	 * @param newPath String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath)
	{
		try
		{
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++)
			{
				if (oldPath.endsWith(File.separator))
				{
					temp = new File(oldPath + file[i]);
				}
				else
				{
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile())
				{
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
						+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1)
					{
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory())
				{// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e)
		{
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();
		}
	}
}