
<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK"%>
<%@ page import="java.security.Principal"%>

<%
com.mdcl.web.sso.SSOTokenManager manager = null;
manager = com.mdcl.web.sso.SSOTokenManager.getInstance(); //�õ�TokenManager��ʵ��
   com.mdcl.web.sso.SSOToken token = manager.createSSOToken(request); //����һ��Token
   //��֤Token����Ч��
String name = null;
if (manager.isValidToken(token)) {
     // ��֤�ɹ������Եõ��û��ĵ�¼��
      java.security.Principal p = token.getPrincipal();
      name = p.getName(); //�û��ĵ�¼��
      
   } else {
    // ��֤ʧ�ܣ����򵽵�½ҳ�棬��Ҫ�û����µ�¼ϵͳ
    out.println("��֤ʧ��");
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
