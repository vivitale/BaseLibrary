package talex.zsw.baseproject.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 作用: 测试数据
 * 作者: 赵小白 email:edisonzsw@icloud.com
 * 日期: 2015-06-19 0:45 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TestData
{
	private static String[] imgs =
		{"http://img5.imgtn.bdimg.com/it/u=4171925720,1146667141&fm=21&gp=0.jpg",
			"http://img4.imgtn.bdimg.com/it/u=2931472824,945899176&fm=21&gp=0.jpg",
			"http://img3.imgtn.bdimg.com/it/u=1664236787,1468527652&fm=11&gp=0.jpg",
			"http://img3.imgtn.bdimg.com/it/u=642587267,14576284&fm=21&gp=0.jpg",
			"http://img3.imgtn.bdimg.com/it/u=642587267,14576284&fm=21&gp=0.jpg",
			"http://img5.imgtn.bdimg.com/it/u=3375564714,4085274303&fm=21&gp=0.jpg",
			"http://img3.imgtn.bdimg.com/it/u=4216152187,2387269284&fm=21&gp=0.jpg",
			"http://img2.imgtn.bdimg.com/it/u=911336027,353198019&fm=21&gp=0.jpg",
			"http://img1.imgtn.bdimg.com/it/u=222251286,534401557&fm=21&gp=0.jpg"
		};

	public static String[] getImgs()
	{
		return imgs;
	}


	public static List<TestDto> getData(int size)
	{
		List<TestDto> testVos = new ArrayList<TestDto>();
		for (int i = 0; i < size; i++)
		{
			TestDto testVo = new TestDto();
			testVo.setTitle("测试数据" + " - " + i);
			testVo.setContent("测试内容" + " - " + i);
			testVo.setDate("2016-05-" + (i + 1));
			testVo.setImage(imgs[i % imgs.length]);
			testVo.setCount(i + "");
			testVos.add(testVo);
		}
		return testVos;
	}

	public static String getString()
	{
		String string = "<div id=\"footer\"><div id=\"footer-body\">\n" +
			"<a id=\"footer-logo\" href=\"http://www.ipc.me\" rel=\"nofollow\"></a><div id=\"footer-content\">\n" +
			"<a href=\"http://www.iplaysoft.com\" target=\"_blank\" rel=\"nofollow\">异次元软件世界</a> 旗下网站  |  <a href=\"http://www.iplaysoft.com/go/store\" target=\"_blank\" rel=\"nofollow\">正版数字商城</a>  |  基于 <a href=\"http://cn.wordpress.org/\" target=\"_blank\" rel=\"nofollow\">WordPress</a> 技术构建  |  <a title=\"站点地图\" href=\"http://www.ipc.me/sitemap.html\">站点地图</a>  |  <a href=\"http://www.miibeian.gov.cn/\" target=\"_blank\" rel=\"nofollow\">粤ICP备06080643号</a>  |  本站使用 <a href=\"http://www.iplaysoft.com/go/aliyun\" target=\"_blank\" rel=\"nofollow\">阿里云主机</a>+<a href=\"http://www.iplaysoft.com/go/linode\" target=\"_blank\" rel=\"nofollow\">Linode</a></div></div><style type=\"text/css\">#goToTop{display:block;position:fixed;bottom:25px;right:25px;z-index:9999;_bottom:none;_right:10px;_position:absolute;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop, 10)||0)-(parseInt(this.currentStyle.marginBottom, 10)||0))-10);}#footer #goToTop a,#goToTop a{display:block;text-indent:-99999px;width:56px;height:56px;background:url(\"http://cdn.iplaysoft.com/common/gototop/gototop.png\") no-repeat;_background:#eee;_text-indent:0;_border:1px solid #ccc;_width:0;_height:0;_padding:5px 10px;}</style><div id=\"goToTop\" style=\"left: 1465px; display: none;\"><a href=\"#\">回到顶部</a></div><div class=\"entry-content\" itemprop=\"description\"><p class=\"entry-image\"><a href=\"http://www.ipc.me/weight-of-galaxy.html\" title=\"科学家计算出银河系最精确“体重”，相当于2100亿个太阳质量！\" itemprop=\"url\"><img border=\"0\" width=\"450\" height=\"250\" itemprop=\"image\" src=\"http://ipc.chotee.com/uploads/post/15386/557e4ba673a96.jpg\"></a></p><p>据国外媒体报道，<a href=\"http://www.ipc.me/weight-of-galaxy.html\">银河系最精确的“体重”</a>出炉——相当于2100亿个太阳的质量。这一结果可帮助科学确认银河有究竟有多大……</p></div></div>";
		return string;
	}
}
