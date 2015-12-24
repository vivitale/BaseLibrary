package talex.zsw.baselibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 项目名称: BaseLibrary
 * 作用: 主要修改isFocused()，让TextView始终获得焦点
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-09-12 16:17 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class WTextView extends TextView
{
	public WTextView(Context context)
	{
		super(context);
	}

	public WTextView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public WTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public boolean isFocused()
	{
		return true;
	}
}
