package com.broada.license.api;

public class LicenseException extends RuntimeException
{
  private static final long A = 1L;

  public LicenseException(String paramString)
  {
    super(paramString);
  }

  public LicenseException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.license.api.LicenseException
 * JD-Core Version:    0.6.0
 */