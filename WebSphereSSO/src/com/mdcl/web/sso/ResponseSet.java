package com.mdcl.web.sso;

import java.util.ArrayList;

public class ResponseSet {

  public ResponseSet(String service) {
    responseSetVersion = null;
    serviceID = null;
    timeStamp = String.valueOf(System.currentTimeMillis());
    responseVector = new ArrayList();
    serviceID = service;
    responseSetVersion = "1.0";
  }

  ResponseSet() {
    responseSetVersion = null;
    serviceID = null;
    timeStamp = String.valueOf(System.currentTimeMillis());
    responseVector = new ArrayList();
  }

  public static ResponseSet parseXML(String xml) {
    ResponseSetParser parser = new ResponseSetParser(xml);
    return parser.parseXML();
  }

  public void setRequestSetID(String id) {
    timeStamp = id;
  }

  public ArrayList getResponses() {
    return responseVector;
  }

  public void addResponseContent(String response) {
    responseVector.add(response);
  }

  public String toXMLString() {
    StringBuffer xml = new StringBuffer(300);
    xml.append(
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
    xml.append("<ResponseSet vers=\"").append(responseSetVersion).
        append("\" svcid=\"").append(serviceID).append(
        "\" timeid=\"").append(timeStamp).append("\">\n");
    for (int i = 0, n = responseVector.size(); i < n; i++) {
      String res = (String) responseVector.get(i);
      xml.append("<Response>");
      xml.append("<![CDATA[").append(res).append("]]>");
      xml.append("</Response>").append("\n");
    }
    xml.append("</ResponseSet>");
    return xml.toString();
  }

  void setResponseSetVersion(String ver) {
    responseSetVersion = ver;
  }

  void setServiceID(String id) {
    serviceID = id;
  }

  private static final String sccsID = "$Id: ResponseSet.java,v 1.12 2004/03/25 03:02:29 ganesh Exp $ $Date: 2004/03/25 03:02:29 $  MDCL, Inc.";
  static final String QUOTE = "\"";
  static final String NL = "\n";
  static final String BEGIN_CDATA = "<![CDATA[";
  static final String END_CDATA = "]]>";
  private String responseSetVersion;
  private String serviceID;
  private String timeStamp;
  private ArrayList responseVector;
}
