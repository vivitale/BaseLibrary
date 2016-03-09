package talex.zsw.baseproject.adapter;

import android.content.Context;

import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGARecyclerViewAdapter;
import talex.zsw.baselibrary.view.CommonAdapter.bgaadapter.BGAViewHolderHelper;
import talex.zsw.baselibrary.view.JCVideoPlayer.JCVideoPlayer;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.test.Demo;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/3/9 10:45 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class JCVideoPlayerAdapter extends BGARecyclerViewAdapter<Demo>
{
	public JCVideoPlayerAdapter(Context context)
	{
		super( context, R.layout.item_jcvideoplayer );
	}

	@Override
	protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, Demo model)
	{
		JCVideoPlayer player =viewHolderHelper.getView( R.id.videoplayer );
		player.setUp( model.getUrl(),model.getImg(),model.getContent() );
	}
}
