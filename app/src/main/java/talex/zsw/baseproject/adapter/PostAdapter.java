package talex.zsw.baseproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import talex.zsw.baselibrary.view.NineGridImageView.GridImageView;
import talex.zsw.baselibrary.view.NineGridImageView.NineGridImageView;
import talex.zsw.baselibrary.view.NineGridImageView.NineGridImageViewAdapter;
import talex.zsw.baseproject.R;
import talex.zsw.baseproject.test.Post;

/**
 * Created by Jaeger on 16/2/24.
 * <p/>
 * Email: chjie.jaeger@gamil.com
 * GitHub: https://github.com/laobie
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>
{
	private LayoutInflater mInflater;
	private List<Post> mPostList;
	private int mShowStyle;

	public PostAdapter(Context context, List<Post> postList, int showStyle)
	{
		super();
		mPostList = postList;
		mInflater = LayoutInflater.from( context );
		mShowStyle = showStyle;
	}

	@Override public void onBindViewHolder(PostViewHolder holder, int position)
	{
		holder.bind( mPostList.get( position ) );
	}

	@Override public int getItemCount()
	{
		return mPostList.size();
	}

	@Override public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		if(mShowStyle == NineGridImageView.STYLE_FILL)
		{
			return new PostViewHolder(
				mInflater.inflate( R.layout.item_post_fill_style, parent, false ) );
		}
		else
		{
			return new PostViewHolder(
				mInflater.inflate( R.layout.item_post_grid_style, parent, false ) );
		}
	}

	public class PostViewHolder extends RecyclerView.ViewHolder
	{
		private NineGridImageView mNglContent;
		private TextView mTvContent;

		private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>()
		{
			@Override protected void onDisplayImage(Context context, ImageView imageView, String s)
			{
				Picasso.with( context ).load( s ).placeholder( R.drawable.ic_default_image )
					.into( imageView );
			}

			@Override protected ImageView generateImageView(Context context)
			{
				GridImageView imageView = new GridImageView( context );
				imageView.setScaleType( ImageView.ScaleType.FIT_XY );
				return imageView;
				//	return super.generateImageView( context );
			}

			@Override protected void onItemImageClick(Context context, int index, List<String> list)
			{
				Toast.makeText( context, "image position is " + index, Toast.LENGTH_SHORT ).show();
			}
		};

		public PostViewHolder(View itemView)
		{
			super( itemView );
			mTvContent = (TextView) itemView.findViewById( R.id.tv_content );
			mNglContent = (NineGridImageView) itemView.findViewById( R.id.ngl_images );
			mNglContent.setAdapter( mAdapter );
		}

		public void bind(Post post)
		{
			mNglContent.setImagesData( post.getImgUrlList() );
			mTvContent.setText( post.getContent() );
		}
	}
}
