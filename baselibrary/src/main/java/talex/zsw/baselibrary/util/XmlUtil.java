package talex.zsw.baselibrary.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.List;

@SuppressWarnings("ALL")
public class XmlUtil
{
	public static <T> T toBean(String xmlStr, Class<T> cls)
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(cls);
		T obj = (T) xstream.fromXML(xmlStr);
		return obj;
	}

	public static <T> List<T> toBeanList(String xmlStr, Class<T> cls)
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(cls);
		List<T> obj = (List<T>) xstream.fromXML(xmlStr);
		return obj;
	}

//	public static <T> String getXMLData(Object obj, Class<T> cls)
//	{
//		XStream xstream = new XStream();
//		xstream.processAnnotations(cls);
//		String dataInfo = Constant.HEADER + xstream.toXML(obj);
//		_Request lsRequest = new _Request(dataInfo);
//		xstream.processAnnotations(_Request.class);
//		String xmlData = Constant.HEADER + xstream.toXML(lsRequest);
//		return xmlData;
//	}
}
