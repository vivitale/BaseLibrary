package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.util.DimenUtils;
import talex.zsw.baselibrary.util.klog.KLog;
import talex.zsw.baseproject.R;

/**
 * 项目包名: talex.zsw.baseproject.activity
 * 作用: 测试用
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/4/6 16:08 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TestActivity extends BaseAppCompatActivity
{

	@Bind(R.id.mTvOld) TextView mTvOld;
	@Bind(R.id.mTvOld2) TextView mTvOld2;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_test);
		ButterKnife.bind(this);

		setSpannable(mTvOld);
		setSpannable(mTvOld2);

		KLog.e("scaledDensity = " + getResources().getDisplayMetrics().scaledDensity);
		KLog.e("density = " + getResources().getDisplayMetrics().density);

		KLog.e("1dp = " + DimenUtils.dpToPx(getResources(), 1) + "px");
		KLog.e("1sp = " + DimenUtils.sp2px(this, 1.0f) + "px");
	}

	@Override protected void initData()
	{
	}

	private void setSpannable(TextView textView)
	{
		SpannableString spanText = new SpannableString(textView.getText().toString());
		spanText.setSpan(new StrikethroughSpan(), 0, spanText.length(),
			Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		textView.setText("");
		textView.append(spanText);
	}
}
