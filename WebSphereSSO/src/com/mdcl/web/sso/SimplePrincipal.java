package com.mdcl.web.sso;

import java.security.Principal;

public class SimplePrincipal implements Principal {
  private String name = null;

  public SimplePrincipal(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
