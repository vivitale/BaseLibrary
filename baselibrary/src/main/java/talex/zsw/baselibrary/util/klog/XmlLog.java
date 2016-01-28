package talex.zsw.baselibrary.util.klog;

import android.util.Log;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by zhaokaiqiang on 15/11/18.
 */
public class XmlLog extends BaseLog
{

	public static void printXml(String tag, String xml, String headString)
	{

		if (xml != null)
		{
			xml = XmlLog.formatXML(xml);
			xml = headString + "\n" + xml;
		}
		else
		{
			xml = headString + NULL_TIPS;
		}

		printLine(tag, true);
		String[] lines = xml.split(LINE_SEPARATOR);
		String str="";
		for (String line : lines)
		{
			if (!isEmpty(line))
			{
//				Log.d(tag, "║ " + line);
				str += line+"\n";
			}
		}
		Log.d( tag,str );
		printLine(tag, false);
	}

	public static String formatXML(String inputXML)
	{
		XMLWriter writer = null;
		String requestXML = null;

		try
		{
			SAXReader reader = new SAXReader();
			Document document = reader.read(new StringReader(inputXML));
			StringWriter stringWriter = new StringWriter();
			OutputFormat format = new OutputFormat(" ", true);
			writer = new XMLWriter(stringWriter, format);
			writer.write(document);
			writer.flush();
			requestXML = stringWriter.getBuffer().toString();
		} catch (IOException e)
		{
			return inputXML;
		} catch (DocumentException e)
		{
			return inputXML;
		} finally
		{
			if (writer != null)
			{
				try
				{
					writer.close();
				} catch (IOException e)
				{
					return inputXML;
				}
			}
		}

		return requestXML;
	}
}
