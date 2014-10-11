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
 * Title:dom4j��ʹ��ʵ��
 * </p>
 * <p>
 * Description:ʹ��dom4j����xml�ļ�
 * </p>
 * <p>
 * Copyright: 2005-8-28
 * </p>
 * <p>
 * Company: MDCL-Frontline
 * </p>
 * 
 * @author ����ѩ jianglx@sc.mdcl.com.cn
 */

public class Dom4jTest {

	private static final Logger logger = Logger.getLogger(Dom4jTest.class);

	public static void main(String[] args) throws IOException,
			DocumentException {
		// -----------------------��һ���÷�------------------------------------------------
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

		// ����һ��Xml�ļ�
		Element user = DocumentHelper.createElement("User");
		user.addAttribute("type", "user");
		user.addElement("name").addAttribute("type", "PinYin").setText(
				"Julysea");
		user.addElement("age").setText("29");
		String oneXml = user.asXML();

		BufferedWriter out = new BufferedWriter(new FileWriter("oneXml.xml"));
		out.write(oneXml);
		out.close();
		// -----------------------�ڶ����÷�------------------------------------------------
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
	 * ����xPath����xml
	 * 
	 * @param element
	 *            Ҫ��ѯ��Ԫ��
	 * @param xPath
	 *            xPath�ַ���
	 * @return Map����,key=name value=text,���û�в鵽Ԫ�ط���sizeΪ0��map
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
	 * ��ȡxml�ļ�
	 * 
	 * @param fileName
	 *            xml�ļ���
	 * @return xml��Document����
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
	 * ����root�ڵ�
	 * 
	 * @param doc
	 *            �����ݵ��ĵ�
	 * @return
	 */
	public Element getRootElement(Document doc) {

		return doc.getRootElement();

	}
}