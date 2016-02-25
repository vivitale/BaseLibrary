package talex.zsw.baseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baseproject.activity.AndroidAnimationsActivity;
import talex.zsw.baseproject.activity.AnimateCheckBoxActivity;
import talex.zsw.baseproject.activity.AnimationActivity;
import talex.zsw.baseproject.activity.CircleImageViewActivity;
import talex.zsw.baseproject.activity.CircularProgressActivity;
import talex.zsw.baseproject.activity.CollSwitchActivity;
import talex.zsw.baseproject.activity.ComplexActivity;
import talex.zsw.baseproject.activity.ContentMenuActivity;
import talex.zsw.baseproject.activity.CustomNumberPickActivity;
import talex.zsw.baseproject.activity.CustomScrollViewActivity;
import talex.zsw.baseproject.activity.DropDownMenuActivity;
import talex.zsw.baseproject.activity.DynamicHeightImageViewActivity;
import talex.zsw.baseproject.activity.ExpandableActivity;
import talex.zsw.baseproject.activity.ExpandablePanelActivity;
import talex.zsw.baseproject.activity.FlowLayoutActivity;
import talex.zsw.baseproject.activity.GestureImageViewActivity;
import talex.zsw.baseproject.activity.LikeButtonActivity;
import talex.zsw.baseproject.activity.LoadToastActivity;
import talex.zsw.baseproject.activity.LoadingDialogActivity;
import talex.zsw.baseproject.activity.MaterialFavoriteButtonActivity;
import talex.zsw.baseproject.activity.MyWebViewActivity;
import talex.zsw.baseproject.activity.NiceSpinnerActivity;
import talex.zsw.baseproject.activity.NumberPBActivity;
import talex.zsw.baseproject.activity.OverScrollActivity;
import talex.zsw.baseproject.activity.OverScrollViewActivity;
import talex.zsw.baseproject.activity.PercentLayoutActivity;
import talex.zsw.baseproject.activity.ProgressActivity;
import talex.zsw.baseproject.activity.PsdLoadingViewActivity;
import talex.zsw.baseproject.activity.PullToNextLayoutActivity;
import talex.zsw.baseproject.activity.PullZoomViewActivity;
import talex.zsw.baseproject.activity.RecyclerViewActivity;
import talex.zsw.baseproject.activity.RefreshLayoutActivity;
import talex.zsw.baseproject.activity.RichTextActivity;
import talex.zsw.baseproject.activity.RippleViewActivity;
import talex.zsw.baseproject.activity.RoundedImageActivity;
import talex.zsw.baseproject.activity.ScrollerNumberPickerActivity;
import talex.zsw.baseproject.activity.SimpleActivity;
import talex.zsw.baseproject.activity.SlideDateTimePickerActivity;
import talex.zsw.baseproject.activity.SliderLayoutActivity;
import talex.zsw.baseproject.activity.SmallBangActivity;
import talex.zsw.baseproject.activity.SoftKeyboardActivity;
import talex.zsw.baseproject.activity.SpotsDialogActivity;
import talex.zsw.baseproject.activity.SquareLayoutActivity;
import talex.zsw.baseproject.activity.SweetAlertDialogActivity;
import talex.zsw.baseproject.activity.SweetSheetActivity;
import talex.zsw.baseproject.activity.SwipeLayoutActivity;
import talex.zsw.baseproject.activity.SwipyRefreshLayoutActivity;
import talex.zsw.baseproject.activity.SwitchAnimationActivity;
import talex.zsw.baseproject.activity.TimeSinceActivity;
import talex.zsw.baseproject.activity.TimesSquareActivity;
import talex.zsw.baseproject.activity.WTextViewActivity;
import talex.zsw.baseproject.activity.WheelViewActivity;
import talex.zsw.baseproject.activity.XBusActivity;

@SuppressWarnings("ALL") public class MainActivity extends BaseAppCompatActivity
	implements OnItemClickListener
{
	@Bind(R.id.mListView) ListView mListView;

	private MainAdapter adapter;
	private List<Vo> datas = new ArrayList<>();

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView( R.layout.activity_main );
		ButterKnife.bind( this );
		adapter = new MainAdapter( this );
		mListView.setAdapter( adapter );
		mListView.setOnItemClickListener( this );
	}

	@Override protected void initData()
	{
		datas.add( new Vo( "简单动画", "", SimpleActivity.class ) );
		datas.add( new Vo( "复杂动画", "", ComplexActivity.class ) );
		datas.add(
			new Vo( "AndroidAnimations", "Android的一些简单动画效果", AndroidAnimationsActivity.class ) );
		datas.add( new Vo( "Animation", "属性动画的封装", AnimationActivity.class ) );
		datas.add( new Vo( "AnimateCheckBox", "带动画效果的CheckBox", AnimateCheckBoxActivity.class ) );
		datas.add( new Vo( "CircleImageView", "将图片自动修正为圆形", CircleImageViewActivity.class ) );
		datas.add( new Vo( "CircularProgress", "一直转圈的CircularProgress和CircularProgressDrawable",
			CircularProgressActivity.class ) );
//				datas.add( new Vo( "CircularReveal", "圆形展示，替换底部View", CircularRevealActivity.class ) );
		datas.add( new Vo( "CollSwitch", "酷炫的开关按钮，选中与否可以改变布局", CollSwitchActivity.class ) );
		datas.add( new Vo( "ContentMenu", "右上角向下展示的Menu", ContentMenuActivity.class ) );
		datas.add( new Vo( "CustomNumberPick", "数字加减选择器", CustomNumberPickActivity.class ) );
		datas.add( new Vo( "CustomScrollView", "可以上下左右滑动的View，且项目支持手势放大缩小",
			CustomScrollViewActivity.class ) );
		datas.add( new Vo( "DropDownMenu", "顶部下拉框", DropDownMenuActivity.class ) );
		datas.add(
			new Vo( "DynamicHeightImageView", "指定图片的宽高比", DynamicHeightImageViewActivity.class ) );
		datas.add( new Vo( "ExpandableLayout", "可以扩展开的布局,包含ListView", ExpandableActivity.class ) );
		datas.add( new Vo( "ExpandablePanel", "可以扩展开的布局", ExpandablePanelActivity.class ) );
		datas.add( new Vo( "FlowLayout", "单选多选标签", FlowLayoutActivity.class ) );
		datas.add( new Vo( "GestureImageView", "支持缩放功能的ImageView", GestureImageViewActivity.class ) );
		datas.add( new Vo( "LikeButton", "显示一个带有动画效果的喜欢按钮", LikeButtonActivity.class ) );
		datas.add( new Vo( "LoadingDialog", "加载等待转圈圈", LoadingDialogActivity.class ) );
		datas.add( new Vo( "LoadToast", "简单的View来实现加载，加载成功与失败的效果", LoadToastActivity.class ) );
		datas.add( new Vo( "MaterialFavoriteButton", "动画效果的星星和爱心，适合用在收藏赞等操作上",
			MaterialFavoriteButtonActivity.class ) );
		datas.add( new Vo( "MyWebView", "加载网页或者富文本，并且自动适应高度", MyWebViewActivity.class ) );
		datas.add( new Vo( "NiceSpinner", "简易的下拉Spinner工具", NiceSpinnerActivity.class ) );
		datas.add( new Vo( "NumberProgressBar", "带数字的ProgressBar", NumberPBActivity.class ) );
		datas.add( new Vo( "OverScrollView", "阻尼效果的上下拉ScrollView,且支持上拉刷新下来加载",
			OverScrollViewActivity.class ) );
		datas.add(
			new Vo( "OverScroll-Decor", "Android>=14 可以给任意View加上阻尼效果", OverScrollActivity.class ) );
		datas.add( new Vo( "PsdLoadingView", "密码输入框的动画效果", PsdLoadingViewActivity.class ) );
		datas.add( new Vo( "Progress", "几个自定义的Progress的使用", ProgressActivity.class ) );
		datas.add( new Vo( "PercentLayout", "百分比布局", PercentLayoutActivity.class ) );
		datas.add( new Vo( "PullToNextLayout", "下拉到下一个界面的View", PullToNextLayoutActivity.class ) );
		datas.add( new Vo( "PullZoomView", "下拉放大，适用于个人中心", PullZoomViewActivity.class ) );
		datas.add( new Vo( "RecyclerView", "RecyclerView配合layoutAnimation实现动画效果",
			RecyclerViewActivity.class ) );
		datas.add(
			new Vo( "RefreshLayout", "修改SwipeRefreshLayout,使其支持下拉加载,下拉加载时没有动画效果,适合使用在一到底部就加载的情况",
				RefreshLayoutActivity.class ) );
		datas.add( new Vo( "RichText", "加载富文本的简单方法,且可以添加图片点击的监听", RichTextActivity.class ) );
		datas.add( new Vo( "RippleView", "点击波纹扩散效果", RippleViewActivity.class ) );
		datas.add( new Vo( "RoundedImage", "圆角图片", RoundedImageActivity.class ) );
		datas.add( new Vo( "ScrollerNumberPicker", "滑动选择器", ScrollerNumberPickerActivity.class ) );
		datas.add( new Vo( "SlideDateTimePicker", "日期时间选择框", SlideDateTimePickerActivity.class ) );
		datas.add( new Vo( "SliderLayout", "轮播", SliderLayoutActivity.class ) );
		datas.add( new Vo( "SmallBang", "点击效果", SmallBangActivity.class ) );
		datas.add( new Vo( "SoftKeyboardListenerLayout", "根布局为FrameLayout,监听键盘弹出事件",
			SoftKeyboardActivity.class ) );
		datas.add( new Vo( "SquareLayout", "正方形布局", SquareLayoutActivity.class ) );
		datas.add( new Vo( "SpotsDialog", "仿Win8的loading", SpotsDialogActivity.class ) );
		datas.add( new Vo( "SweetAlertDialog", "简易dialog的使用", SweetAlertDialogActivity.class ) );
		datas.add( new Vo( "SweetSheet", "底部弹出一个布局，可以拖拽改变高度", SweetSheetActivity.class ) );
		datas.add( new Vo( "SwipeLayout", "上下左右滑动展开效果", SwipeLayoutActivity.class ) );
		datas.add( new Vo( "SwipyRefreshLayout", "下拉刷新，上拉加载", SwipyRefreshLayoutActivity.class ) );
		datas
			.add( new Vo( "SwitchAnimation", "使View中的项目带有动画效果入场", SwitchAnimationActivity.class ) );
		datas.add( new Vo( "TimeSince", "时间的另一种表达方式", TimeSinceActivity.class ) );
		datas.add( new Vo( "TimesSquare", "简单易用的日历程序", TimesSquareActivity.class ) );
		datas.add( new Vo( "WheelView", "时间选择器", WheelViewActivity.class ) );
		datas.add( new Vo( "WTextView", "无限循环跑马灯", WTextViewActivity.class ) );
		datas.add( new Vo( "XBus", "事件处理", XBusActivity.class ) );

		adapter.setContentArray( datas, false );
	}

	@Override public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Vo data = (Vo) adapter.getItem( position );
		start( new Intent( MainActivity.this, data.getCla() ) );
	}
}
