package talex.zsw.baseproject.activity;

import android.app.Activity;
import android.os.Bundle;

import talex.zsw.baselibrary.view.DiscreteSeekBar.DiscreteSeekBar;
import talex.zsw.baseproject.R;

/**
 * 项目包名: talex.zsw.baseproject.activity
 * 作用: 带有指示器的SeekBar
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/6/20 13:49 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DiscreteSeekBarActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discrete_seekbar);
		DiscreteSeekBar discreteSeekBar1 = (DiscreteSeekBar) findViewById(R.id.discrete1);
		discreteSeekBar1.setNumericTransformer(new DiscreteSeekBar.NumericTransformer()
		{
			@Override
			public int transform(int value)
			{
				return value * 100;
			}
		});
	}
}
