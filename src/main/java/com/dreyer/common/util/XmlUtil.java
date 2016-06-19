package com.dreyer.common.util;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description
 * @author Dreyer
 * @email hy.dreyer@qq.com
 * @date 2015年9月7日 下午8:44:57
 * @version 1.0
 */
public class XmlUtil {
	
	/**
	 * 简单xml转为Map集合
	 * @param xml
	 * @return
	 */
	public static Map<String, String> xmlToMap(String xml) {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(new ByteArrayInputStream(
					xml.getBytes()));
			Element element = document.getDocumentElement();
			NodeList nodeList = element.getChildNodes();
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element e = (Element) nodeList.item(i);
				map.put(e.getNodeName(), e.getTextContent());
			}
			return map;
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
