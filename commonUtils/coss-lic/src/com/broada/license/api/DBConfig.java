package com.broada.license.api;

public abstract interface DBConfig
{
  public static final String IMPL_CONFIG = "license.dbConfigClass";

  public abstract String getDriver();

  public abstract String getUrl();

  public abstract String getUsername();

  public abstract String getPassword();
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.license.api.DBConfig
 * JD-Core Version:    0.6.0
 */