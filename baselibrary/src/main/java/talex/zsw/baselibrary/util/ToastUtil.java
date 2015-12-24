/**
 *
 */
package talex.zsw.baselibrary.util;

/**
 * @author Administrator
 */

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil
{

	public static void show(Context context, String info)
	{
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}

	public static void show(Context context, int info)
	{
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}

	public static void show(Context context, String info, int intX, int intY)
	{
		Toast toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP, intX, intY);
		toast.show();
	}
}
