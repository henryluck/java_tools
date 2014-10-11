/**
 * 
 */
package com.jlx.xml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <p>
 * Title:dom4j的使用实例
 * </p>
 * <p>
 * Description:使用dom4j解析xml文件
 * </p>
 * <p>
 * Copyright: 2005-8-28
 * </p>
 * <p>
 * Company: MDCL-Frontline
 * </p>
 * 
 * @author 蒋林雪 jianglx@sc.mdcl.com.cn
 */

public class Dom4jTest {

	private static final Logger logger = Logger.getLogger(Dom4jTest.class);

	public static void main(String[] args) throws IOException,
			DocumentException {
		// -----------------------第一种用法------------------------------------------------
		BufferedReader reader = new BufferedReader(new FileReader(
				"Dom4jTest.xml"));
		String tempStr;
		String ewXml = "";
		while ((tempStr = reader.readLine()) != null) {
			ewXml = ewXml + tempStr;
		}
		Element root = null;

		root = DocumentHelper.parseText(ewXml).getRootElement();
		Attribute rootCmd = root.attribute("cmd");
		Attribute rootVersion = root.attribute("version");
		logger.debug("rootNmae = " + root.getName());
		logger.debug("EW'cmd = " + rootCmd.getValue());
		logger.debug("EW'version = " + rootVersion.getValue());

		Element usrName = root.element("Username");
		logger.debug("EW.Username value = " + usrName.getTextTrim());

		Element source = root.element("Source");
		Attribute sourceUns = source.attribute("uns");
		logger.debug("EW.Source'uns" + sourceUns.getValue());
		Attribute sourceType = source.attribute("type");
		logger.debug("EW.Source'type = " + sourceType.getValue());

		// 创建一个Xml文件
		Element user = DocumentHelper.createElement("User");
		user.addAttribute("type", "user");
		user.addElement("name").addAttribute("type", "PinYin").setText(
				"Julysea");
		user.addElement("age").setText("29");
		String oneXml = user.asXML();

		BufferedWriter out = new BufferedWriter(new FileWriter("oneXml.xml"));
		out.write(oneXml);
		out.close();
		// -----------------------第二种用法------------------------------------------------
		Dom4jTest t = new Dom4jTest();
		Document document = null;
		root = null;
		try {
			document = t.read("");
			root = t.getRootElement(document);
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (DocumentException e) {
			e.printStackTrace();
		}

		System.out.println("type=" + root.attributeValue("type"));

		String xPath = "/invoke/parameters/object/property";

		Map map = t.processXml(root, xPath);

		System.out.println("---------map.size()---------" + map.size());

		for (Iterator i = map.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			System.out.println(key + "=" + map.get(key));
		}
	}

	/**
	 * 根据xPath解析xml
	 * 
	 * @param element
	 *            要查询的元素
	 * @param xPath
	 *            xPath字符串
	 * @return Map对象,key=name value=text,如果没有查到元素返回size为0的map
	 */
	@SuppressWarnings("unchecked")
	public Map processXml(Element element, String xPath) {

		List list = element.selectNodes(xPath);
		Map map = new HashMap();

		for (Iterator i = list.iterator(); i.hasNext();) {
			Element e = (Element) i.next();
			map.put(e.attributeValue("name"), e.getStringValue());
		}

		return map;
	}

	/**
	 * 读取xml文件
	 * 
	 * @param fileName
	 *            xml文件名
	 * @return xml的Document对象
	 * @throws MalformedURLException
	 *             MalformedURLException
	 * @throws DocumentException
	 *             DocumentException
	 */
	public Document read(String fileName) throws MalformedURLException,
			DocumentException {

		SAXReader reader = new SAXReader();

		Document document = reader.read("D:\\test.xml");
		return document;

	}

	/**
	 * 返回root节点
	 * 
	 * @param doc
	 *            待操纵的文档
	 * @return
	 */
	public Element getRootElement(Document doc) {

		return doc.getRootElement();

	}
}