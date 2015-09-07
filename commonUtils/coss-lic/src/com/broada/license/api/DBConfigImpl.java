package com.broada.license.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBConfigImpl
  implements DBConfig
{
  private String C;
  private String B;
  private String D;
  private String A;

  public DBConfigImpl()
  {
    String str1 = System.getProperty("dbConfFile");
    if (str1 == null)
      str1 = System.getProperty("user.dir") + "/conf/jdbc.properties";
    Properties localProperties = new Properties();
    try
    {
      localProperties.load(new FileInputStream(str1));
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      throw new RuntimeException(String.format("数据库配置读取失败，配置文件[%s]不存在", new Object[] { str1 }), localFileNotFoundException);
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(String.format("数据库配置读取失败，配置文件[%s]IO错误", new Object[] { str1 }), localIOException);
    }
    this.C = localProperties.getProperty("jdbc.driverClassName");
    this.D = localProperties.getProperty("jdbc.username");
    this.A = localProperties.getProperty("jdbc.password");
    this.B = localProperties.getProperty("jdbc.url");
    if ((this.B == null) || (this.B.contains("${")))
    {
      String str2 = localProperties.getProperty("jdbc.database.ip");
      String str3 = localProperties.getProperty("jdbc.database.port");
      String str4 = localProperties.getProperty("jdbc.database.instance");
      this.B = ("jdbc:oracle:thin:@" + str2 + ":" + str3 + ":" + str4);
    }
  }

  public String getDriver()
  {
    return this.C;
  }

  public String getUrl()
  {
    return this.B;
  }

  public String getUsername()
  {
    return this.D;
  }

  public String getPassword()
  {
    return this.A;
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.license.api.DBConfigImpl
 * JD-Core Version:    0.6.0
 */