package talex.zsw.baselibrary.view.MagicProgress;

/**
 * 项目包名: talex.zsw.baselibrary.view.MagicProgress
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 16/4/21 17:28 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface ISmoothTarget
{
	float getPercent();

	void setPercent(float percent);

	void setSmoothPercent(float percent);
}
