package com.mdcl.web.sso;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.mdcl.util.SystemProperties;

public class SSOToken {


  Principal principal = null;
  Cookie[] cookies = null;

  /**
   * ����һ��SSOToken
   * @param request HttpServletRequest
   */
  public SSOToken(HttpServletRequest request) {

    cookies = request.getCookies();

 
  }

  /**
   * �õ�SSOToken�󶨵��û����ʻ�
   * @return Principal
   */
  public Principal getPrincipal() {

    return principal;

  }

  /**
   * ��֤SSOToken����Ч�ԡ�
   * @return boolean true ���token����Ч,��Ч�򷵻�false
   */
  public boolean validSelf() {
    boolean retval = false;

    String ssourl = SystemProperties.get("com.mdcl.sso.url");
    RequestSet reqset = new RequestSet("authority");
    reqset.setCookie(cookies);

    try {


      ResponseSet responseSet = SSOClient.send(new URL(ssourl), reqset);
      ArrayList al = responseSet.getResponses();


      if (al.size() > 0) {

        String name = (String) al.get(0);


        if (name != null && name.length() > 0) {

          principal = new SimplePrincipal(name);
          retval = true;

        }
      }

    }
    catch (MalformedURLException ex) {
    	
      System.err.println("URL is not in correct format!");
      ex.printStackTrace();
    }
    catch (SendRequestException ex) {
    	
      System.err.println("Send Request to SSO Servlet Failed!");
      ex.printStackTrace();
    }


    return retval;
  }

}
