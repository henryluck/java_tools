package com.jlx.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Recursive routine to print out DOM tree nodes
 * 
 * @author Administrator
 */
public class DOMEchoXML {

	/**
	 * 遍历所有的节点,打印数据
	 * 
	 * @param n
	 *            需要遍历的节点
	 */
	private static void echo(Node n) {

		int type = n.getNodeType();

		switch (type) {
		case Node.DOCUMENT_NODE:
			System.out.print("DOC:");
			break;
		case Node.DOCUMENT_TYPE_NODE:
			System.out.print("DOC_TYPE:");
			break;
		case Node.ELEMENT_NODE:
			System.out.print("ELEM:");
			break;
		case Node.TEXT_NODE:
			System.out.print("TEXT:");
			break;
		default:
			System.out.print("OTHER TYPE" + type);
			break;
		}

		System.out.print("node name=\"" + n.getNodeName() + "\"");

		String val = n.getNodeValue();
		if (val != null) {
			if (!(val.trim().equals(""))) {
				System.out.print(" nodevalue \"" + n.getNodeValue() + "\"");
			}
		}
		System.out.println();

		// prnt children of any
		for (Node child = n.getFirstChild(); child != null; child = child
				.getNextSibling()) {
			echo(child);
		}

	}

	/**
	 * 遍历打印xml文件,打印成xml文件的原貌
	 * 
	 * @param node
	 *            需要遍历的节点
	 */
	public static void printDomTree(Node node) {
		int type = node.getNodeType();

		switch (type) {
		// print the document element
		case Node.DOCUMENT_NODE: {
			System.out.println("<?xml version=\"1.0\" ?>");
			printDomTree(((Document) node).getDocumentElement());
			break;
		}
		case Node.ELEMENT_NODE: {
			System.out.print("<");
			System.out.print(node.getNodeName());

			NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++)
				printDomTree(attrs.item(i));

			System.out.print(">");

			if (node.hasChildNodes()) {
				NodeList children = node.getChildNodes();
				for (int i = 0; i < children.getLength(); i++)
					printDomTree(children.item(i));
			}

			System.out.print("</");
			System.out.print(node.getNodeName());
			System.out.print('>');

			break;
		}
		case Node.ATTRIBUTE_NODE: {
			System.out.print(" " + node.getNodeName() + "=\""
					+ ((Attr) node).getValue() + "\"");
			break;
		}

		case Node.TEXT_NODE: {
			System.out.print(node.getNodeValue());
			break;
		}
		}
	}

	/**
	 * @param args
	 *            args[0] is xml file location
	 */
	public static void main(String[] args) {
		// Step 1 Create a DocumentbuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//dbf.setIgnoringElementContentWhitespace(true);
		// We can set various configuration choices on dbf now
		// (to ignore comments,do validation,etc)

		// Step 2:create a DocumentBuilder
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Step 3:parse the file
		Document doc = null;
		try {
			doc = db.parse(new File(args[0]));
		} catch (SAXException se) {
			se.printStackTrace();
			System.exit(1);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}

		// Step 4:echo the document
		echo(doc);
		System.out.println("-------------------------------------------------------");
		printDomTree(doc);

	}

}
