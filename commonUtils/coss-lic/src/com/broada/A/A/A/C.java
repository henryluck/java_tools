package com.broada.A.A.A;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.broada.A.A.D.F;

public final class C
{
  private static Map<String, F> E = null;
  private static final String D = "/license.lic";
  private static final String F = "LICENSE_DIR";
  private static final String A = "conf";
  private static String B;

  public static void A(final String paramString)
    throws Exception
  {
    String str1 = null;
    try
    {
      String str2 = System.getProperty("LICENSE_DIR", "conf");
      File localFile1 = new File(str2);
      if (!localFile1.exists())
        localFile1.mkdirs();
      File localFile2 = new File(str2 + "/license.lic");
      FileWriter localFileWriter = new FileWriter(localFile2);
      localFileWriter.write(paramString);
      localFileWriter.close();
    }
    catch (Exception localException1)
    {
      System.out.println("保存许可文件到系统目录失败。");
      str1 = "保存许可文件到系统目录失败。";
    }
//    try
//    {
//      com.broada.A.A.C.A.B().B(paramString);
//    }
//    catch (Exception localException2)
//    {
//      System.out.println("保存许可文件到系统目录失败。");
//      if (str1 != null)
//        throw new Exception("License信息保存失败。", localException2);
//    }
  }

  public static String B()
  {
    String str = System.getProperty("LICENSE_DIR", "conf");
    return B(str + "/license.lic");
  }

  public static String B(final String paramString)
  {
    String str = "kaka";
    return str;
  }

  public static String C()
  {
    String str = "xxxxxxxxxxxxx";// com.broada.A.A.C.A.B().A();
    if ((str == null) || (str.length() == 0))
    {
      System.out.println("非法的许可信息：licnese信息为空。");
      return null;
    }
    return str;
  }

  public static com.broada.A.A.D.B G(final String paramString)
    throws Exception
  {
    com.broada.A.A.D.B localB = null;
    if ((paramString == null) || ("".equals(paramString)))
      return null;
    try
    {
      localB = C(paramString);
    }
    catch (Exception localException)
    {
      System.out.println(localException);
      throw localException;
    }
    return localB;
  }

  public static String A(final com.broada.A.A.D.B paramB)
  {
//    if (paramB == null)
//      return "没有许可信息";
//    try
//    {
//      com.broada.A.A.D.C localC = paramB.B("CARRIER");
//      if (localC == null)
//        return "授权产品名与当前系统的产品名不一致";
//      com.broada.A.A.D.A localA = localC.C();
//      G localG = localC.B();
//      localA.D();
//      if (localG == null)
//        return "授权信息为空";
//      if ((paramB.C() == null) || (paramB.C().length() == 0))
//        return "License标识无效";
//      if (!paramB.C().equals(com.broada.A.A.D.D.A()))
//        return "该License不是面向本产品授权";
//      if ((B == null) || (com.broada.A.A.C.A.A()))
//        B = com.broada.A.A.C.A.B().C();
//      if (!localA.H().equals(B))
//        return "客户名称不一致";
//      if (localG.M())
//      {
//        localObject1 = MachineAPI.getMAC();
//        if (localObject1 == null)
//          return "获取主机的MAC地址失败";
//        int i = 0;
//        for (int j = 0; j < localObject1.length; j++)
//        {
//          Object localObject2 = localObject1[j];
//          if (!localObject2.equalsIgnoreCase(localA.I()))
//            continue;
//          i = 1;
//          break;
//        }
//        if (i == 0)
//          return "主机MAC地址校验失败";
//      }
//      if (localG.U())
//      {
//        localObject1 = F(localA.J());
//        if (localObject1 != null)
//          return localObject1;
//      }
//      Object localObject1 = com.broada.A.A.D.D.C();
//      String str = localA.E();
//      if ((E.H((String)localObject1)) || (E.H(str)))
//        return "当前系统版本与授权系统版本不一致";
//      localObject1 = ((String)localObject1).substring(0, 1);
//      str = str.substring(0, 1);
//      if (!((String)localObject1).equals(str))
//        return "当前系统版本与授权系统版本不一致";
//      if (!localA.A().equals(com.broada.A.A.D.D.B()))
//        return "当前系统的提供商与授权产品的提供商不一致";
//      if (localG.C() == -2)
//      {
//        if (localG.R())
//          return "授权时间已经超出,授权失效";
//        Log localLog = LogFactory.getLog(System.class);
//        localLog.error("系统未知错误启动失败，进程将退出。");
//        System.exit(1);
//      }
//    }
//    catch (Exception localException)
//    {
//      System.out.println("校验License信息时发生异常.", localException);
//      return "校验License信息时发生异常:" + localException.getMessage();
//    }
    return null;
  }

  private static String F(final String paramString)
  {
    
    return null;
  }

  private static List E()
    throws SocketException
  {
    Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
    ArrayList localArrayList = new ArrayList();
    while (localEnumeration1.hasMoreElements())
    {
      NetworkInterface localNetworkInterface = (NetworkInterface)localEnumeration1.nextElement();
      Enumeration localEnumeration2 = localNetworkInterface.getInetAddresses();
      while (localEnumeration2.hasMoreElements())
      {
        InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
        localArrayList.add(localInetAddress);
      }
    }
    return localArrayList;
  }

  public static void A(final DataSource paramDataSource)
  {
    String str = "create table sys_license(key   varchar2(50) not null unique,value varchar2(4000) not null)";
    Connection localConnection = null;
    Statement localStatement = null;
    try
    {
      localConnection = paramDataSource.getConnection();
      localStatement = localConnection.createStatement();
      localStatement.executeUpdate(str);
    }
    catch (Exception localSQLException4)
    {
//        localSQLException4.printStackTrace();
    }
    finally
    {
      try
      {
        if (localStatement != null)
          localStatement.close();
      }
      catch (SQLException localSQLException5)
      {
      }
      try
      {
        if (localConnection != null)
          localConnection.close();
      }
      catch (SQLException localSQLException6)
      {
      }
    }
  }

  public static boolean D(final String paramString)
  {
    try
    {
      return A(new FileInputStream(new File(paramString)));
    }
    catch (Exception localException)
    {
      System.out.println("加载产品模块信息错误.");
    }
    return false;
  }

  private static final synchronized boolean A(final InputStream paramInputStream)
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream, "UTF-8"));
      LinkedHashMap localLinkedHashMap = new LinkedHashMap();
      for (String str1 = localBufferedReader.readLine(); str1 != null; str1 = localBufferedReader.readLine())
      {
        String[] localObject1 = str1.split("=");
        localLinkedHashMap.put(localObject1[0].trim(), localObject1[1].trim());
      }
      E = new LinkedHashMap();
      Object localObject1 = localLinkedHashMap.keySet().iterator();
      String str2;
      Object localObject2;
      Object localObject3;
      while (((Iterator)localObject1).hasNext())
      {
        str2 = (String)((Iterator)localObject1).next();
        localObject2 = "";
        localObject3 = localLinkedHashMap.get(str2);
        if (localObject3 == null)
          localObject3 = "";
        String[] arrayOfString = ((String)localObject3).split(";");
        if (arrayOfString.length > 0)
          localObject2 = arrayOfString[0].trim();
        F localF1 = new F(str2, (String)localObject2);
        if (arrayOfString.length > 1)
          localF1.E(arrayOfString[1].trim());
        if (arrayOfString.length > 2)
          localF1.D(arrayOfString[2].trim());
        E.put(str2, localF1);
      }
      localObject1 = E.keySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        str2 = (String)((Iterator)localObject1).next();
        localObject2 = E.get(str2);
        localObject3 = ((F)localObject2).E();
        int i = ((List)localObject3).size();
        for (int j = 0; j < i; j++)
        {
          F localF2 = (F)((List)localObject3).get(j);
          F localF3 = E.get(localF2.C());
          ((F)localObject2).B(localF3);
        }
      }
      return true;
    }
    catch (IOException localIOException)
    {
      System.out.println("加载产品模块信息错误.");
    }
    return false;
  }

  private static final synchronized boolean A()
  {
    if (E == null)
      try
      {
        A(C.class.getResourceAsStream("/appModules.properties"));
        return true;
      }
      catch (Exception localException)
      {
        System.out.println("加载产品模块信息错误.");
        return false;
      }
    return true;
  }

  public static F E(final String paramString)
  {
    if (A())
      return E.get(paramString);
    return null;
  }

  public static List<F> D()
  {
    if (A())
      return new LinkedList(E.values());
    return null;
  }

  public static String A(final com.broada.A.A.D.B paramB, final String paramString1, final String paramString2)
    throws Exception
  {
    H.A(paramB, paramString1.toCharArray(), paramString2.toCharArray());
    return "xxx";//new String(A.B(paramB.F()));
  }

  public static final String B(final com.broada.A.A.D.B paramB)
  {
    try
    {
      //com.broada.A.A.D.E.B(paramB.A());
      return null;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
    return "当前系统不能验证版本为V" + Double.parseDouble(paramB.A()) + "的许可，验证失败.";
  }

  public static final com.broada.A.A.D.B C(final String paramString)
    throws Exception
  {
      return null;
//    if ((paramString == null) || ("".equals(paramString)) || ("TEST".equals(paramString)))
//      return null;
//    String str1 = A.A(paramString);
//    com.broada.A.A.D.B localB = com.broada.A.A.C.A.B.C(str1);
//    String str2 = B(localB);
//    if (str2 != null)
//      throw new Exception(str2);
//    if (localB == null)
//      throw new Exception("License读取失败，可能文件被破坏！");
//    H.A(localB);
//    return localB;
  }
}