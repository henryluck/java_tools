package com.mdcl.web.sso;

import javax.servlet.http.HttpServletRequest;

public class SSOTokenManager {

  private static SSOTokenManager instance = new SSOTokenManager();

  private SSOTokenManager() {

  }

  /**
   * Singleton
   * @return SSOTokenManager
   */
  public static SSOTokenManager getInstance() {

    return instance;

  }

  /**
   * ����һ��SSO��Token
   * @param request HttpServletRequest
   * @return SSOToken
   */
  public SSOToken createSSOToken(HttpServletRequest request) {

    return new SSOToken(request);

  }

  /**
   * ��֤SSOToken����Ч��
   * @param token SSOToken
   * @return boolean
   */
  public boolean isValidToken(SSOToken token) {

    return token.validSelf();

  }

}
