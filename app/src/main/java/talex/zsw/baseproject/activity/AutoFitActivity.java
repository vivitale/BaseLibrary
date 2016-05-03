package talex.zsw.baseproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import talex.zsw.baseproject.R;

/**
 * 项目包名: talex.zsw.baseproject.activity
 * 作用: 文字自适应大小
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/4/25 09:49 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AutoFitActivity  extends Activity
{

	private TextView mOutput, mAutofitOutput;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autofit);

		mOutput = (TextView) findViewById(R.id.output);
		mAutofitOutput = (TextView) findViewById(R.id.output_autofit);

		((EditText) findViewById(R.id.input)).addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
			{
				// do nothing
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
			{
				mOutput.setText(charSequence);
				mAutofitOutput.setText(charSequence);
			}

			@Override
			public void afterTextChanged(Editable editable)
			{
				// do nothing
			}
		});
	}
}