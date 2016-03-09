package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.view.JCVideoPlayer.JCVideoPlayer;
import talex.zsw.baselibrary.view.RecyleView.FullyLinearLayoutManager;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.adapter.JCVideoPlayerAdapter;
import talex.zsw.baseproject.test.Demo;

/**
 * 项目名称: BaseProject
 * 作用: 简易的视频播放器
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/3/9 10:38 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class JCVideoPlayerActivity extends BaseAppCompatActivity
{
	@Bind(R.id.videocontroller1) JCVideoPlayer videoController1;
	@Bind(R.id.videocontroller2) JCVideoPlayer videoController2;
	@Bind(R.id.videocontroller3) JCVideoPlayer videoController3;
	@Bind(R.id.mRecyclerView) RecyclerView mRecyclerView;

	JCVideoPlayerAdapter adapter;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView( R.layout.activity_jcvideoplayer );
		ButterKnife.bind( this );

		adapter = new JCVideoPlayerAdapter( this );
		mRecyclerView.setAdapter( adapter );
	}

	@Override protected void initData()
	{
		videoController1
			.setUp( "http://2449.vod.myqcloud.com/2449_43b6f696980311e59ed467f22794e792.f20.mp4",
				"http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640",
				"嫂子别摸我" );

		videoController2
			.setSkin( R.color.colorAccent, R.color.colorPrimary, R.drawable.skin_seek_progress,
				R.color.wheat, R.mipmap.icon, R.mipmap.icon );
		videoController2
			.setUp( "http://2449.vod.myqcloud.com/2449_ded7b566b37911e5942f0b208e48548d.f20.mp4",//
				"http://p.qpic.cn/videoyun/0/2449_ded7b566b37911e5942f0b208e48548d_2/640", "嫂子还摸我",
				false );

		videoController3.setUp( "http://121.40.64.47/resource/mp3/music_yangguang3.mp3",//
			"http://p.qpic.cn/videoyun/0/2449_38e65894d9e211e5b0e0a3699ca1d490_1/640", "嫂子别闹" );

		List<Demo> demos = new ArrayList<>();
		demos.add( getDemo1() );
		demos.add( getDemo2() );
		demos.add( getDemo3() );
		demos.add( getDemo1() );
		demos.add( getDemo2() );
		demos.add( getDemo3() );
		demos.add( getDemo1() );
		demos.add( getDemo2() );
		demos.add( getDemo3() );

		adapter.setDatas( demos );
		mRecyclerView.setItemAnimator( new DefaultItemAnimator() );
		mRecyclerView.setLayoutManager( new FullyLinearLayoutManager( this ) );
	}

	@Override protected void onPause()
	{
		super.onPause();
		JCVideoPlayer.releaseAllVideos();
	}

	private Demo getDemo1()
	{
		Demo demo = new Demo();
		demo.setContent( "标题1" );
		demo.setImg( "http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640" );
		demo.setUrl( "http://2449.vod.myqcloud.com/2449_43b6f696980311e59ed467f22794e792.f20.mp4" );
		return demo;
	}

	private Demo getDemo2()
	{
		Demo demo = new Demo();
		demo.setContent( "标题2" );
		demo.setImg( "http://p.qpic.cn/videoyun/0/2449_ded7b566b37911e5942f0b208e48548d_2/640" );
		demo.setUrl( "http://2449.vod.myqcloud.com/2449_ded7b566b37911e5942f0b208e48548d.f20.mp4" );
		return demo;
	}

	private Demo getDemo3()
	{
		Demo demo = new Demo();
		demo.setContent( "标题3" );
		demo.setImg( "http://p.qpic.cn/videoyun/0/2449_38e65894d9e211e5b0e0a3699ca1d490_1/640" );
		demo.setUrl( "http://121.40.64.47/resource/mp3/music_yangguang3.mp3" );
		return demo;
	}
}
