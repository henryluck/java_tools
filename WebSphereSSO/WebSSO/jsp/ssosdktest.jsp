
<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK"%>
<%@ page import="java.security.Principal"%>

<%
com.mdcl.web.sso.SSOTokenManager manager = null;
manager = com.mdcl.web.sso.SSOTokenManager.getInstance(); //得到TokenManager的实例
   com.mdcl.web.sso.SSOToken token = manager.createSSOToken(request); //创建一个Token
   //验证Token的有效性
String name = null;
if (manager.isValidToken(token)) {
     // 认证成功，可以得到用户的登录名
      java.security.Principal p = token.getPrincipal();
      name = p.getName(); //用户的登录名
      
   } else {
    // 认证失败，定向到登陆页面，需要用户重新登录系统
    out.println("验证失败");
   }

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>ltpatest</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
  
  <body>
    you name is <%=(name==null?"null":name)%>
  </body>
</html>
