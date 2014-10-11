package com.mdcl.web.sso;

public class SendRequestException
    extends Exception {

  public SendRequestException(String msg) {
    super(msg);
    fillInStackTrace();
  }

  public SendRequestException(Throwable t) {
    super(t.getMessage());
    fillInStackTrace();
  }

  private static final String sccsID = "$Id: SendRequestException.java,v 1.6 2002/11/05 03:02:22 ganesh Exp $ $Date: 2002/11/05 03:02:22 $  Sun Microsystems, Inc.";
}