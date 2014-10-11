package com.mdcl.web.sso;

import java.io.Reader;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLUtils {

  public static Document toDOMDocument(String xmlString) {
    Document document = null;
    Reader in = new StringReader(xmlString);
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = factory.newDocumentBuilder();
      document = parser.parse(new InputSource(in));
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return document;

  }

}