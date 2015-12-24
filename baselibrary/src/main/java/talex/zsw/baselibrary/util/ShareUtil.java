package talex.zsw.baselibrary.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 项目名称: PuJiang
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-02-0002 0:43 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ShareUtil
{
	/**
	 * 分享功能
	 *
	 * @param context       上下文
	 * @param activityTitle Activity的名字
	 * @param msgTitle      消息标题
	 * @param msgText       消息内容
	 * @param imgPath       图片路径，不分享图片则传null
	 */
	public static void shareMsg(Context context, String activityTitle, String msgTitle,
								String msgText, String imgPath)
	{
		Intent intent = new Intent(Intent.ACTION_SEND);
		if (imgPath == null || imgPath.equals(""))
		{
			intent.setType("text/plain"); // 纯文本
		}
		else
		{
			File f = new File(imgPath);
			if (f != null && f.exists() && f.isFile())
			{
				intent.setType("image/jpg");
				Uri u = Uri.fromFile(f);
				intent.putExtra(Intent.EXTRA_STREAM, u);
			}
		}
		intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
		intent.putExtra(Intent.EXTRA_TEXT, msgText);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(Intent.createChooser(intent, activityTitle));
	}

	/**
	 * 分享功能
	 *
	 * @param context       上下文
	 * @param activityTitle Activity的名字
	 * @param msgTitle      消息标题
	 * @param msgText       消息内容
	 * @param bitmap        图片
	 */
	public static void shareBitemap(Context context, String activityTitle, String msgTitle,
									String msgText, Bitmap bitmap)
	{
		File tmpFile = null;
		try
		{
			tmpFile = File
				.createTempFile("photoview", ".png", Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_DOWNLOADS));
			FileOutputStream out = new FileOutputStream(tmpFile);
			bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tmpFile));
		intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
		intent.putExtra(Intent.EXTRA_TEXT, msgText);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(Intent.createChooser(intent, activityTitle));
	}
}
