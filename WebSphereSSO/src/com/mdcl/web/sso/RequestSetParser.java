package com.mdcl.web.sso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

class RequestSetParser {

  public RequestSetParser(Document xmlDoc) {
    document = xmlDoc;
  }

  public RequestSetParser(String xmlString) {
    document = XMLUtils.toDOMDocument(xmlString);
  }

  public RequestSet parseXML() {
    if (document == null)
      return null;
    Element reqSetElem = document.getDocumentElement();
    RequestSet reqSet = new RequestSet();
    setRequestSetAttributes(reqSetElem, reqSet);
    NodeList requests = reqSetElem.getElementsByTagName("Request");
    if (requests == null)
      return reqSet;
    int nodeLen = requests.getLength();
    for (int i = 0; i < nodeLen; i++)
      reqSet.addRequestContent(parseRequestElement( (Element) requests.item(i)));

    return reqSet;
  }

  private void setRequestSetAttributes(Element elem, RequestSet requestSet) {
    String temp = elem.getAttribute("vers");
    if (temp != null)
      requestSet.setRequestSetVersion(temp);
    temp = elem.getAttribute("svcid");
    if (temp != null)
      requestSet.setServiceID(temp);
    temp = elem.getAttribute("reqid");
    if (temp != null)
      requestSet.setRequestSetID(temp);
  }

  private String parseRequestElement(Element elem)

  {
    String ret = null;
    Node text = elem.getFirstChild();
    if (text != null)
      ret = text.getNodeValue();
    return ret;
  }

  private Document document;

}