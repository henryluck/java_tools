package com.jlx.xml;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxOne extends DefaultHandler {

	/**
	 * Create parse object and parse the file
	 * 
	 * @param uri
	 *            Need parsed XML file
	 */
	public void parseURI(File uri) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			// xml file,and a event handler
			sp.parse(uri, this);
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/*
	 * the method is overrided from DefaultHandlers---------------------
	 */
	public void startDocument() {
		System.out.println("<?xml version=\"1.0\"?>");
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.print(new String(ch, start, length));
	}

	public void startElement(String namespaceURI, String localName,
			String rawName, Attributes attrs) throws SAXException {
		System.out.print("<");
		System.out.print(rawName);
		if (attrs != null) {
			int len = attrs.getLength();
			for (int i = 0; i < len; i++) {
				System.out.print(" ");
				System.out.print(attrs.getQName(i));
				System.out.print("=\"");
				System.out.print(attrs.getValue(i));
				System.out.print("\"");
			}
		}
		System.out.print(">");

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.print("</");
		System.out.print(qName);
		System.out.print(">");

	}

	public void endDocument() throws SAXException {

	}

	/*
	 * Deal the errors-------------------------------------------------------
	 */
	public void warning(SAXParseException ex) {
		System.err.println("[Warning] " + getLocationString(ex) + ": "
				+ ex.getMessage());
	}

	public void error(SAXParseException ex) {
		System.err.println("[Error] " + getLocationString(ex) + ": "
				+ ex.getMessage());
	}

	public void fatalError(SAXParseException ex) throws SAXException {
		System.err.println("[Fatal Error] " + getLocationString(ex) + ": "
				+ ex.getMessage());
		throw ex;
	}

	/**
	 * return error information
	 * 
	 * @param ex
	 *            SAXParseException
	 * @return the error line number and col number
	 */
	private String getLocationString(SAXParseException ex) {
		return "line:" + ex.getLineNumber() + "col:" + ex.getColumnNumber();
	}

	/*
	 * Ignore the whitesapce
	 */
	public void ignorableWhitespace(char ch[], int start, int length) {
		try {
			characters(ch, start, length);
		} catch (SAXException spe) {
			spe.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String argv[]) {
		if (argv.length == 0 || (argv.length == 1 && argv[0].equals("-help"))) {
			System.out
					.println("Parameter error!\ne.g \n \t java SaxOne c:/temp/qq.xml \n :)");
			System.exit(1);
		}

		File xmlfile = new File(argv[0]);
		SaxOne s1 = new SaxOne();
		s1.parseURI(xmlfile);
	}

}
