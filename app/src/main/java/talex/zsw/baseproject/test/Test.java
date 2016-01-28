package talex.zsw.baseproject.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称: AppLmsq
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/1/19 09:40 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@XStreamAlias("index") public class Test implements Serializable
{
	@XStreamImplicit
	List<indeXmls> indeXmls;

	public List<talex.zsw.baseproject.test.indeXmls> getIndeXmls()
	{
		return indeXmls;
	}

	public void setIndeXmls(List<talex.zsw.baseproject.test.indeXmls> indeXmls)
	{
		this.indeXmls = indeXmls;
	}

	@Override public String toString()
	{
		return "Test{" +
			"indeXmls=" + indeXmls +
			'}';
	}
}
