package com.broada.A.A.B.B;

import java.io.CharArrayReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

public class A
  implements com.broada.A.A.B.A
{
  private DataSource D;
  private static final String B = "s";
  private static final String A = "info";
  private static final String C = "t1";
  private static final String E = "t2";

  public void A(DataSource paramDataSource)
  {
    this.D = paramDataSource;
  }

  public void D()
  {
    if (this.D == null)
      throw new NullPointerException("数据源还没有被设置.");
    Connection localConnection = null;
    PreparedStatement localPreparedStatement = null;
    try
    {
      localConnection = this.D.getConnection();
      localPreparedStatement = localConnection.prepareStatement("delete from sys_license");
      localPreparedStatement.executeUpdate();
    }
    catch (SQLException localSQLException)
    {
      throw new RuntimeException("删除License信息错误.", localSQLException);
    }
    finally
    {
      A(localConnection, localPreparedStatement, null);
    }
  }

  public String E()
  {
    return E("s");
  }

  public String B()
  {
    return E("info");
  }

  public String A()
  {
    return E("t1");
  }

  public String C()
  {
    return E("t2");
  }

  public void B(String paramString)
  {
    A("s", paramString);
  }

  public void D(String paramString)
  {
    A("info", paramString);
  }

  public void C(String paramString)
  {
    if (this.D == null)
      throw new NullPointerException("数据源还没有被设置.");
    Connection localConnection = null;
    PreparedStatement localPreparedStatement = null;
    try
    {
      localConnection = this.D.getConnection();
      localPreparedStatement = localConnection.prepareStatement("delete from sys_license where key='t1'");
      localPreparedStatement.executeUpdate();
    }
    catch (SQLException localSQLException)
    {
      throw new RuntimeException("删除许可信息失败", localSQLException);
    }
    finally
    {
      A(localConnection, localPreparedStatement, null);
    }
    A("t1", paramString);
  }

  public void A(String paramString)
  {
    A("t2", paramString);
  }

  private int A(String paramString1, String paramString2)
  {
    if (this.D == null)
      throw new NullPointerException("数据源还没有被设置.");
    Connection localConnection = null;
    PreparedStatement localPreparedStatement = null;
    try
    {
      localConnection = this.D.getConnection();
      String str1 = A(localConnection, paramString1);
      String str2 = null;
      if (str1 == null)
        str2 = "insert into sys_license(key, value) values('" + paramString1 + "', ?)";
      else
        str2 = "update sys_license set value=? where key='" + paramString1 + "'";
      localPreparedStatement = localConnection.prepareStatement(str2);
      localPreparedStatement.setCharacterStream(1, new CharArrayReader(paramString2.toCharArray()), paramString2.length());
      int i = localPreparedStatement.executeUpdate();
      return i;
    }
    catch (SQLException localSQLException)
    {
      throw new RuntimeException("更新key=" + paramString1 + "的信息错误.", localSQLException);
    }
    finally
    {
      A(localConnection, localPreparedStatement, null);
    }
    throw localObject;
  }

  private String E(String paramString)
  {
    if (this.D == null)
      throw new NullPointerException("数据源还没有被设置.");
    Connection localConnection = null;
    PreparedStatement localPreparedStatement = null;
    ResultSet localResultSet = null;
    try
    {
      localConnection = this.D.getConnection();
      localPreparedStatement = localConnection.prepareStatement("select value from sys_license where key=?");
      localPreparedStatement.setString(1, paramString);
      localResultSet = localPreparedStatement.executeQuery();
      if (localResultSet.next())
      {
        str1 = localResultSet.getString("value");
        if (str1 == null)
        {
          str2 = "";
          return str2;
        }
        String str2 = str1;
        return str2;
      }
      String str1 = null;
      return str1;
    }
    catch (SQLException localSQLException)
    {
      throw new RuntimeException("根据key=" + paramString + "获取信息错误.", localSQLException);
    }
    finally
    {
      A(localConnection, localPreparedStatement, localResultSet);
    }
    throw localObject;
  }

  private String A(Connection paramConnection, String paramString)
  {
    PreparedStatement localPreparedStatement = null;
    ResultSet localResultSet = null;
    try
    {
      localPreparedStatement = paramConnection.prepareStatement("select value from sys_license where key=?");
      localPreparedStatement.setString(1, paramString);
      localResultSet = localPreparedStatement.executeQuery();
      if (localResultSet.next())
      {
        str1 = localResultSet.getString("value");
        if (str1 == null)
        {
          str2 = "";
          return str2;
        }
        String str2 = str1;
        return str2;
      }
      String str1 = null;
      return str1;
    }
    catch (SQLException localSQLException)
    {
      throw new RuntimeException("根据key=" + paramString + "获取信息错误.", localSQLException);
    }
    finally
    {
      A(null, localPreparedStatement, localResultSet);
    }
    throw localObject;
  }

  private void A(Connection paramConnection, Statement paramStatement, ResultSet paramResultSet)
  {
    if (paramResultSet != null)
      try
      {
        paramResultSet.close();
      }
      catch (SQLException localSQLException1)
      {
      }
    if (paramStatement != null)
      try
      {
        paramStatement.close();
      }
      catch (SQLException localSQLException2)
      {
      }
    if (paramConnection != null)
      try
      {
        paramConnection.close();
      }
      catch (SQLException localSQLException3)
      {
      }
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.B.B.A
 * JD-Core Version:    0.6.0
 */