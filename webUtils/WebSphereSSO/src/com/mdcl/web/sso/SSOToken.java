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
   * 创建一个SSOToken
   * @param request HttpServletRequest
   */
  public SSOToken(HttpServletRequest request) {

    cookies = request.getCookies();

 
  }

  /**
   * 得到SSOToken绑定的用户的帐户
   * @return Principal
   */
  public Principal getPrincipal() {

    return principal;

  }

  /**
   * 验证SSOToken的有效性。
   * @return boolean true 如果token是有效,无效则返回false
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
