package talex.zsw.baselibrary.util;

/**
 * 项目名称: Executor
 * 作用: 基站信息
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 日期: 2016 16/2/26 22:08 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CellInfo
{
	/** 基站id，用来找到基站的位置 */
	private int cellId;
	/** 移动国家码，共3位，中国为460，即imsi前3位 */
	private String mobileCountryCode = "460";
	/** 移动网络码，共2位，在中国，移动的代码为00和02，联通的代码为01，电信的代码为03，即imsi第4~5位 */
	private String mobileNetworkCode = "0";
	/** 地区区域码 */
	private int locationAreaCode;
	/** 信号类型[选 gsm|cdma|wcdma] */
	private String radioType = "";

	public CellInfo()
	{
	}

	public int getCellId()
	{
		return cellId;
	}

	public void setCellId(int cellId)
	{
		this.cellId = cellId;
	}

	public String getMobileCountryCode()
	{
		return mobileCountryCode;
	}

	public void setMobileCountryCode(String mobileCountryCode)
	{
		this.mobileCountryCode = mobileCountryCode;
	}

	public String getMobileNetworkCode()
	{
		return mobileNetworkCode;
	}

	public void setMobileNetworkCode(String mobileNetworkCode)
	{
		this.mobileNetworkCode = mobileNetworkCode;
	}

	public int getLocationAreaCode()
	{
		return locationAreaCode;
	}

	public void setLocationAreaCode(int locationAreaCode)
	{
		this.locationAreaCode = locationAreaCode;
	}

	public String getRadioType()
	{
		return radioType;
	}

	public void setRadioType(String radioType)
	{
		this.radioType = radioType;
	}
}