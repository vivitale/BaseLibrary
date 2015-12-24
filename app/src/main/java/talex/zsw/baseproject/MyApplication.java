package talex.zsw.baseproject;

import talex.zsw.baselibrary.BaseApplication;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-10-0010 0:26 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MyApplication extends BaseApplication
{
	@Override public void onCreate()
	{
		super.onCreate();
		setImg(R.mipmap.ic_launcher, R.mipmap.ic_launcher);
	}
}
