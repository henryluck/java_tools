package com.mdcl.web.sso;

import java.util.ArrayList;
import javax.servlet.http.Cookie;

public class RequestSet {
  public RequestSet(String service)
    {
        requestSetVersion = null;
        serviceID = null;
        requestSetID = null;
        requestVector = new ArrayList();
        serviceID = service;
        requestSetVersion = "1.0";
        requestSetID = Integer.toString(requestCount++);
    }

    RequestSet()
    {
        requestSetVersion = null;
        serviceID = null;
        requestSetID = null;
        requestVector = new ArrayList();
    }

    public static RequestSet parseXML(String xml)
    {
        RequestSetParser parser = new RequestSetParser(xml);
        return parser.parseXML();
    }

    public String getRequestSetVersion()
    {
        return requestSetVersion;
    }

    public String getServiceID()
    {
        return serviceID;
    }

    public String getRequestSetID()
    {
        return requestSetID;
    }

    public ArrayList getRequests()
    {
        return requestVector;
    }

    public void addRequestContent(String request)
    {
        requestVector.add(request);
    }

    public String toXMLString()
    {
        StringBuffer xml = new StringBuffer(300);
        xml.append("<?xml version=").append("\"").append("1.0").append("\"").append(" encoding=").append("\"").append("UTF-8").append("\"").append(" standalone=").append("\"").append("yes").append("\"").append("?>").append("\n");
        xml.append("<RequestSet vers=").append("\"").append(requestSetVersion).append("\"").append(" svcid=").append("\"").append(serviceID).append("\"").append(" reqid=").append("\"").append(requestSetID).append("\"").append(">").append("\n");
        int numRequests = requestVector.size();
        for(int i = 0; i < numRequests; i++)
        {
            String req = (String)requestVector.get(i);
            xml.append("<Request>");
            xml.append("<![CDATA[").append(req).append("]]>");
            xml.append("</Request>").append("\n");
        }

        xml.append("</RequestSet>");
        return xml.toString();
    }

    void setRequestSetVersion(String vers)
    {
        requestSetVersion = vers;
    }

    void setServiceID(String id)
    {
        serviceID = id;
    }

    void setRequestSetID(String id)
    {
        requestSetID = id;
    }
  public Cookie[] getCookie() {
    return cookie;
  }
  public void setCookie(Cookie[] cookie) {
    this.cookie = cookie;
  }

    private static final String sccsID = "$Id: RequestSet.java,v 1.12 2002/11/05 03:02:28 ganesh Exp $ $Date: 2002/11/05 03:02:28 $  Sun Microsystems, Inc.";
    static final String QUOTE = "\"";
    static final String NL = "\n";
    static final String BEGIN_CDATA = "<![CDATA[";
    static final String END_CDATA = "]]>";
    private String requestSetVersion;
    private String serviceID;
    private String requestSetID;
    private ArrayList requestVector;
    private static int requestCount = 0;
    private Cookie[] cookie = null;

}