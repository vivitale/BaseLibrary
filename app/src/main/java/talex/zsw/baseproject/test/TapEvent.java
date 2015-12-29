package talex.zsw.baseproject.test;

import java.io.Serializable;

public class TapEvent implements Serializable
{
	private String title = "deleteFeeds";
	private String content;

	public String getTitle()
	{
		return title;
	}

	public TapEvent setTitle(String title)
	{
		this.title = title;
		return this;
	}

	public String getContent()
	{
		return content;
	}

	public TapEvent setContent(String content)
	{
		this.content = content;
		return this;
	}

	@Override public String toString()
	{
		return "TapEvent{" +
			"title='" + title + '\'' +
			", content='" + content + '\'' +
			'}';
	}
}
