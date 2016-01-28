package talex.zsw.baseproject.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 项目名称: BaseProject
 * 作用: TODO (用一句话描述该文件做什么) 
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/1/19 13:00 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@XStreamAlias( "bnsType" ) public class indeXmls implements Serializable
{
	String bnsType;

	public String getBnsType()
	{
		return bnsType;
	}

	public void setBnsType(String bnsType)
	{
		this.bnsType = bnsType;
	}
}
