package talex.zsw.baseproject.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseFragment;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-23-0023 16:23 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("ValidFragment")
public class PTNOtherFragment extends BaseFragment
{
	@Bind(R.id.nameTV) TextView nameTV;
	@Bind(R.id.birthdayTV) TextView birthdayTV;
	@Bind(R.id.image) ImageView image;

	private String[] names = {"于文文", "张钧甯", "陈乔恩", "贾青"};
	private String[] birthday = {"1989年11月7日", "1982年9月4日", "1979年04月04日",
		"1986年11月2日"
	};
	private int[] imgRes = {R.mipmap.ic_icon1, R.mipmap.ic_icon2,
		R.mipmap.ic_icon3, R.mipmap.ic_icon4
	};
	private int index;

	public PTNOtherFragment(int index)
	{
		this.index = index;
	}

	@Override protected void initArgs(Bundle bundle)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.fragment_ptnother);
		ButterKnife.bind(this, mView);
	}

	@Override protected void initData()
	{
		nameTV.setText(names[index]);
		birthdayTV.setText("出生日期: " + birthday[index]);
		image.setImageResource(imgRes[index]);
		mView.setClickable(true);
	}

	@Override public void onDestroyView()
	{
		super.onDestroyView();
		ButterKnife.unbind(this);
	}
}
