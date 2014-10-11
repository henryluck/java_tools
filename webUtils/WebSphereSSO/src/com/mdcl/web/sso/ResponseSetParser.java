package com.mdcl.web.sso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ResponseSetParser {
  public ResponseSetParser(Document xmlDoc) {
    document = xmlDoc;
  }

  public ResponseSetParser(String xmlString) {
    document = XMLUtils.toDOMDocument(xmlString);

  }

  public ResponseSet parseXML() {
    if (document == null)
      return null;
    Element responseSetElem = document.getDocumentElement();
    ResponseSet responseSet = new ResponseSet();
    setResponseSetAttributes(responseSetElem, responseSet);
    NodeList responses = responseSetElem.getElementsByTagName("Response");
    if (responses == null)
      return responseSet;
    int nodeLen = responses.getLength();
    for (int i = 0; i < nodeLen; i++)
      responseSet.addResponseContent(parseResponseElement( (Element) responses.
          item(i)));

    return responseSet;
  }

  public void setResponseSetAttributes(Element elem, ResponseSet responseSet) {
    String temp = elem.getAttribute("vers");
    if (temp != null)
      responseSet.setResponseSetVersion(temp);
    temp = elem.getAttribute("svcid");
    if (temp != null)
      responseSet.setServiceID(temp);
    temp = elem.getAttribute("reqid");
    if (temp != null)
      responseSet.setRequestSetID(temp);
  }

  private String parseResponseElement(Element elem) {

    Node text = elem.getFirstChild();
    if (text != null)
      return text.getNodeValue();
    else
      return null;
  }

  Document document;

}