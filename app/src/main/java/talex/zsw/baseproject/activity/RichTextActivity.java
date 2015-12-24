package talex.zsw.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import talex.zsw.baselibrary.BaseAppCompatActivity;
import talex.zsw.baselibrary.widget.RichText;
import talex.zsw.baseproject.R;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-16-0016 13:24 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RichTextActivity extends BaseAppCompatActivity implements RichText.OnImageClickListener
{
	@Bind(R.id.mRichText) RichText mRichText;

	@Override protected void initArgs(Intent intent)
	{

	}

	@Override protected void initView(Bundle bundle)
	{
		setContentView(R.layout.activity_richtext);
		ButterKnife.bind(this);

		mRichText.setOnImageClickListener(this);
	}

	@Override protected void initData()
	{
		String content =
			"<div id=\"section_show_post\"><div class=\"rbox_t\"></div><div class=\"rbox_c\"><div id=\"show_post_entry\" itemscope=\"\" itemtype=\"http://schema.org/Article\"><div class=\"entry-banner\"><a href=\"http://www.iplaysoft.com/windows10.html\"><img alt=\"微软最新 Windows 10 TH2 操作系统简体中文正式版官方原版 ISO 镜像下载+序列号\" src=\"http://ips.chotee.com/wp-content/uploads/2014/windows10/win10th2.jpg\" srcset=\"http://ips.chotee.com/wp-content/uploads/2014/windows10/win10th2.jpg 1x,http://ips.chotee.com/wp-content/uploads/2014/windows10/win10th2_2x.jpg 2x\" border=\"0\" width=\"680\" height=\"130\"></a></div><div class=\"entry-head\"><h2 class=\"entry-title\" itemprop=\"name\"><a href=\"http://www.iplaysoft.com/windows10.html\">微软最新 Windows 10 TH2 操作系统简体中文正式版官方原版 ISO 镜像下载+序列号</a></h2><div class=\"entry-cat\">\n" +
				"<span class=\"entry-update-summary\">TH2 新版镜像发布 (版本1511)</span>[ <a href=\"http://www.iplaysoft.com/category/system\" rel=\"category tag\">系统工具</a> // <a href=\"http://www.iplaysoft.com/os/windows-platform\" rel=\"tag\">Windows</a>]&nbsp;&nbsp;&nbsp;&nbsp;[2015-11-16<meta itemprop=\"datePublished\" content=\"2015-11-16\">]</div></div><div class=\"entry-show-content\">\n" +
				"<span itemprop=\"description\"><p>随着<a href=\"http://www.iplaysoft.com/tag/微软\" target=\"_blank\">微软</a>的不断努力开发完善 <strong>Windows 10</strong> 系统以及 <a href=\"http://www.iplaysoft.com/office2016.html\" target=\"_blank\">Office 2016</a> 办公软件。如今，这款最新一代的操作系统以及新版的<a href=\"http://www.iplaysoft.com/tag/办公\" target=\"_blank\">办公</a>软件均已经越来越多人用上了！</p><p>这里给大家提供最新的微软官方&nbsp;<strong>Windows 10 TH2 系统正式版原版光盘镜像 ISO下载。</strong>新版系统增加了全新的&nbsp;<a href=\"http://www.iplaysoft.com/microsoft-edge.html\" target=\"_blank\">Microsoft Edge 浏览器</a>。电脑上安装了已经激活的&nbsp;<a href=\"http://www.iplaysoft.com/windows81.html\" target=\"_blank\">Win8</a> / Win7 用户，可以直接在免费线更新升级到最新正式版系统。当然，我们更加推荐你下载独立的 Windows 10 ISO 光盘<a href=\"http://www.iplaysoft.com/tag/镜像\" target=\"_blank\">镜像</a>进行全新安装（<a href=\"http://www.iplaysoft.com/clean-install-windows10-activate.html\" target=\"_blank\">教程</a>）……</p>\n" +
				"</span><div class=\"entry-meta\">\n" +
				"<a href=\"http://www.iplaysoft.com/windows10.html#comments\">评论：190条</a>&nbsp;&nbsp;|&nbsp;&nbsp;围观：<span id=\"spn2216\">1,019,684</span>℃<script type=\"text/javascript\">strBatchView+=\"2216,\";</script>&nbsp;&nbsp;|&nbsp;&nbsp;标签：<span itemprop=\"keywords\"><a href=\"http://www.iplaysoft.com/tag/iso\" rel=\"tag\">ISO</a> , <a href=\"http://www.iplaysoft.com/tag/windows\" rel=\"tag\">windows</a> , <a href=\"http://www.iplaysoft.com/tag/windows10\" rel=\"tag\">windows10</a> , <a href=\"http://www.iplaysoft.com/tag/%e5%8d%87%e7%ba%a7\" rel=\"tag\">升级</a> , <a href=\"http://www.iplaysoft.com/tag/%e5%be%ae%e8%bd%af\" rel=\"tag\">微软</a> , <a href=\"http://www.iplaysoft.com/tag/%e6%a1%8c%e9%9d%a2\" rel=\"tag\">桌面</a> , <a href=\"http://www.iplaysoft.com/tag/%e7%b3%bb%e7%bb%9f\" rel=\"tag\">系统</a> , <a href=\"http://www.iplaysoft.com/tag/%e8%a3%85%e6%9c%ba\" rel=\"tag\">装机</a> , <a href=\"http://www.iplaysoft.com/tag/%e9%95%9c%e5%83%8f\" rel=\"tag\">镜像</a></span></div></div></div><div id=\"show_post_side\" class=\"sidebar_bdad_list\" style=\"margin:-8px 0 0 8px;\"> <script type=\"text/javascript\">BAIDU_CLB_fillSlot('230916');\n" +
				"\t\t\t\t\t\tvar bd_slot_code = shffleArray(new Array('230917','230918'));\n" +
				"\t\t\t\t\t\tfor(var i=0;i<bd_slot_code.length;i++){BAIDU_CLB_fillSlot(bd_slot_code[i]);}</script> </div></div><div class=\"rbox_b\"></div></div>";
		mRichText.setRichText(content);
	}

	@Override public void imageClicked(List<String> imageUrls, int position)
	{
		showToast("图片地址是:" + imageUrls);
	}
}
