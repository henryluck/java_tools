package com.broada.A.A.A;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.broada.A.A.C.C;
import com.broada.license.api.LicenseException;


public final class I
  implements ServletContextListener
{

  public void contextDestroyed(final ServletContextEvent paramServletContextEvent)
  {
  }

  public void contextInitialized(final ServletContextEvent paramServletContextEvent)
  {
    String str = paramServletContextEvent.getServletContext().getInitParameter("dbConfFile");
    System.out.println("dbConfFile(InitParam)=" + str);
    str = paramServletContextEvent.getServletContext().getRealPath(str);
    A(str, paramServletContextEvent.getServletContext().getInitParameter("moduleIds"), paramServletContextEvent.getServletContext().getRealPath("/../.."), false);
  }

  public void A(final boolean paramBoolean)
  {
    String str1;
    if (paramBoolean)
    {
      str1 = "throwError";
    }
    else
    {
      str1 = System.getProperty("l4c.loadFaildAction", "showImport");
      if ((!str1.equalsIgnoreCase("showImport")) && (!str1.equalsIgnoreCase("exit")))
        throw new IllegalArgumentException("错误的l4c.loadFailedAction参数");
    }
    C localC = C.D();
    String str2 = localC.I();
    if (str2 != null)
      throw new RuntimeException("License校验失败：" + str2);
  }

  public void A(final String paramString1, final String paramString2, final String paramString3, final boolean paramBoolean)
  {
//    if (D.C() == null)
//      D.C(AppContextMgr.getSystemProp("system.version"));
//    if (D.B() == null)
//      D.B(AppContextMgr.getSystemProvider());
//    if (D.A() == null)
//      D.A(AppContextMgr.getSystemId());
    String str1;
    if (paramBoolean)
    {
      str1 = "throwError";
    }
    else
    {
      str1 = System.getProperty("l4c.loadFaildAction", "showImport");
      if ((!str1.equalsIgnoreCase("showImport")) && (!str1.equalsIgnoreCase("exit")))
        throw new IllegalArgumentException("错误的l4c.loadFailedAction参数");
    }
    C localC = C.D();
    try
    {
      try
      {
        System.out.println("dbConfFile-InitParam realPath=" + paramString1);
        if ((paramString1 != null) && (System.getProperty("dbConfFile") == null))
          System.setProperty("dbConfFile", paramString1);
        System.out.println("dbConfFile(SysProperties)=" + System.getProperty("dbConfFile"));
        localC.B(paramString3);
        localC.F();
        localC.C(str1);
      }
      catch (Exception localException)
      {
        if ((localException instanceof LicenseException))
            
        System.out.println("License加载处理失败,dbConfFile=" + System.getProperty("dbConfFile"));
//        throw new RuntimeException("License加载处理失败.", localException);
        localException.printStackTrace();
      }
      if (paramString2 != null)
      {
        int i = 0;
        String[] arrayOfString = paramString2.split(",");
        for (int j = 0; j < arrayOfString.length; j++)
        {
          String str2 = arrayOfString[j];
          if (!localC.A(str2))
            continue;
          i = 1;
          break;
        }
//        if (i == 0)
//          throw new LicenseException(paramString2 + "模块没有授权，请联系产品服务支持获取模块授权.");
      }
    }
    finally
    {
      localC.H();
    }
  }
}
