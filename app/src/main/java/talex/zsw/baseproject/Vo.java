package talex.zsw.baseproject;

import java.io.Serializable;

/**
 * 项目名称: BaseProject
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2015-11-11-0011 15:16 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class Vo implements Serializable
{
	private String title;
	private String content;
	private Class cla;

	public Vo(String title, String content, Class cla)
	{
		this.title = title;
		this.content = content;
		this.cla = cla;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Class getCla()
	{
		return cla;
	}

	public void setCla(Class cla)
	{
		this.cla = cla;
	}
}
