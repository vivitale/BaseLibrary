package talex.zsw.baselibrary.view.PullToNextLayout;

import android.support.v4.app.FragmentManager;

/**
 * @ClassName: PullToNextEntity 
 * @Description: 滑动到下一个页面实体
 * @author tale email:vvtale@gmail.com
 * @date 2015年4月15日 下午1:28:51  
 */
public class PullToNextEntity
{
	private PullToNextView pullToNextView;
	private int frameId;
	private int position;

	public PullToNextView getPullToNextView()
	{
		return pullToNextView;
	}

	public void setPullToNextView(PullToNextView pullToNextView)
	{
		this.pullToNextView = pullToNextView;
	}

	public int getFrameId()
	{
		return frameId;
	}

	public void setFrameId(int frameId)
	{
		this.frameId = frameId;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public boolean isAttach()
	{

		if (pullToNextView == null)
		{
			return false;
		}

		if (pullToNextView.getParent() == null)
		{
			return false;
		}
		return true;

	}

	public void reset(FragmentManager fm)
	{
		getPullToNextView().reset(fm, getPosition());
	}
}
