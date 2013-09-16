package jlx.tools.research.zhaopin;

import java.net.URLEncoder;

import jlx.tools.research.utils.DebugLogger;
import jlx.tools.research.utils.RegexUtils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2012-7-1<br>
 * <p>
 * </p>
 * <br>
 * @author henry.luck@gmail.com<br>
 * @version CompanyResearchTool v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class WebRoboter {
    public static final String LOGINURL = "http://nw.zhaopin.com/cas/login";
    public static final String USER = "vanessa.li";
    public static final String PWD = "840731";
    
    public static String jsesionid=null;
    
    public HttpClient login() throws Exception{
        long start = System.currentTimeMillis();
        HttpClient client = new HttpClient();
        //需要两次连接，第一次访问登陆页，获得lt隐藏域的值
        //第二次真正发送用户名密码，登陆，需要lt值
        GetMethod get = new GetMethod(LOGINURL);
        client.executeMethod(get);
        String loginPage = get.getResponseBodyAsString();
        String ltInput = RegexUtils.getMatchString(loginPage, "<input type=\"hidden\" name=\"lt\" value=\"([^\"]*)\" />");
        get.releaseConnection();
        
        DebugLogger.log("lt="+ltInput);
        
        PostMethod post = new PostMethod(LOGINURL);
        NameValuePair username = new NameValuePair("username", USER);
        NameValuePair password = new NameValuePair("password", PWD);
        NameValuePair submit = new NameValuePair("Submit", "+");
        NameValuePair event = new NameValuePair("_eventId", "submit");
        NameValuePair datasource = new NameValuePair("dataSource", "crm");
        NameValuePair lt = new NameValuePair("lt", ltInput);
        post.setRequestBody(new NameValuePair[]{username, password,submit,event,datasource,lt});
        client.executeMethod(post);
        
        
        DebugLogger.log(post.getStatusLine().toString());
        //DebugLogger.log(post.getResponseBodyAsString());
        
        post.releaseConnection();
         
        //************
        //登录http://nw.zhaopin.com/intranet/interface/view_friends.jsp，需要获得jsseionid
        //************
        PostMethod fpost = new PostMethod("http://nw.zhaopin.com/intranet/interface/view_friends.jsp");
        client.executeMethod(fpost);
        
        Header cookies = fpost.getResponseHeader("Set-Cookie");
        jsesionid = RegexUtils.getMatchString(cookies.getValue(),"JSESSIONID=([^;]*);");
        fpost.releaseConnection();
        
        
        DebugLogger.log("login cost time:" + (System.currentTimeMillis() - start));
        return client;
    }
    
    public String search(String corName,HttpClient client) throws Exception{
        long start = System.currentTimeMillis();
        DebugLogger.log("start searching com:" + corName);
        String name = URLEncoder.encode(corName,"UTF-8");
//        
//        PostMethod postSearch = new PostMethod("http://nw.zhaopin.com/intranet/dwr/exec/Multiple.2.dwr");//locationHeader.getValue());
//        NameValuePair n1 = new NameValuePair("callCount", "2");
//        NameValuePair n2 = new NameValuePair("c0-scriptName", "FriendService");
//        NameValuePair n3 = new NameValuePair("c0-methodName", "getFriendsByAll");
//        NameValuePair n4 = new NameValuePair("c0-id", "6822_1340804010859");
//        NameValuePair n5 = new NameValuePair("c0-param0", "string:" + name);
//        NameValuePair n6 = new NameValuePair("c0-param1", "string:");
//        NameValuePair n7 = new NameValuePair("c0-param2", "string:110");
//        NameValuePair n8 = new NameValuePair("c0-param3", "string:420");
//        NameValuePair n9 = new NameValuePair("c0-param4", "string:-1");
//        NameValuePair n10 = new NameValuePair("c0-param5", "string:1");
//        NameValuePair n11 = new NameValuePair("c0-param6", "number:1");
//        NameValuePair n12 = new NameValuePair("c1-scriptName", "FriendService");
//        NameValuePair n13 = new NameValuePair("c1-methodName", "getSaleInputedFriends");
//        NameValuePair n14 = new NameValuePair("c1-id", "8405_1340804010859");
//        NameValuePair n15 = new NameValuePair("c1-param0", "string:" + name);
//        NameValuePair n16 = new NameValuePair("c1-param1", "string:");
//        NameValuePair n17 = new NameValuePair("c1-param2", "string:1");
//        NameValuePair n18 = new NameValuePair("c1-param3", "string:420");
//        NameValuePair n19 = new NameValuePair("c1-param4", "number:1");
//        NameValuePair n20 = new NameValuePair("xml", "true");
        
        PostMethod postSearch = new PostMethod("http://nw.zhaopin.com/intranet/dwr/call/plaincall/Multiple.2.dwr");//locationHeader.getValue());
      NameValuePair n1 = new NameValuePair("callCount", "2");
      NameValuePair nn1 = new NameValuePair("page", "/intranet/interface/view_friends.jsp");
      NameValuePair nn2 = new NameValuePair("httpSessionId", jsesionid);
      NameValuePair nn3 = new NameValuePair("scriptSessionId", "2");
      NameValuePair n2 = new NameValuePair("c0-scriptName", "FriendService");
      NameValuePair n3 = new NameValuePair("c0-methodName", "getFriendsByAll");
      NameValuePair n4 = new NameValuePair("c0-id", "0");
      NameValuePair n5 = new NameValuePair("c0-param0", "string:" + name);
      NameValuePair n6 = new NameValuePair("c0-param1", "string:");
      NameValuePair n7 = new NameValuePair("c0-param2", "string:110");
      NameValuePair n8 = new NameValuePair("c0-param3", "string:420");
      NameValuePair n9 = new NameValuePair("c0-param4", "string:-1");
      NameValuePair n10 = new NameValuePair("c0-param5", "string:1");
      NameValuePair n11 = new NameValuePair("c0-param6", "number:1");
      NameValuePair n12 = new NameValuePair("c1-scriptName", "FriendService");
      NameValuePair n13 = new NameValuePair("c1-methodName", "getSaleInputedFriends");
      NameValuePair n14 = new NameValuePair("c1-id", "1");
      NameValuePair n15 = new NameValuePair("c1-param0", "string:" + name);
      NameValuePair n16 = new NameValuePair("c1-param1", "string:");
      NameValuePair n17 = new NameValuePair("c1-param2", "string:1");
      NameValuePair n18 = new NameValuePair("c1-param3", "string:420");
      NameValuePair n19 = new NameValuePair("c1-param4", "number:1");
      NameValuePair n20 = new NameValuePair("batchId", "2");
        
        
        
        
        
        postSearch.setRequestBody(new NameValuePair[]{n1,nn1,nn2,nn3,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17,n18,n19,n20});
        
        client.executeMethod(postSearch);
        DebugLogger.log("executeMethod time:" + (System.currentTimeMillis() - start));
        String page = postSearch.getResponseBodyAsString();
        DebugLogger.log("getResponseBodyAsString time:" + (System.currentTimeMillis() - start));
        postSearch.releaseConnection();
        
        // DebugLogger.log(page);
        DebugLogger.log("search company cost time:" + (System.currentTimeMillis() - start));
        return page;
    }
    
    public static void main(String[] args) throws Exception {
//        SystemOutSetter.setSystOut();
        WebRoboter w = new WebRoboter();
        String page = w.search("齐心文仪", w.login());
        
    }

}
