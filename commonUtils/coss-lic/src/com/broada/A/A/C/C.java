package com.broada.A.A.C;

import com.broada.A.A.D.B;
import com.broada.A.A.D.D;
import com.broada.A.A.D.G;
import com.broada.itil.common.AppContextMgr;
import com.broada.license.api.DBConfig;
import com.broada.license.api.DBConfigImpl;
import com.broada.license.api.LicenseException;
import com.jgoodies.looks.Options;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class C
{
  private static final Log K = LogFactory.getLog(C.class);
  public static final String J = "showImport";
  public static final String C = "exit";
  public static final String H = "throwError";
  private static C I = new C();
  private B D;
  private G F;
  private com.broada.A.A.D.A E;
  private String B = "";
  private int G = -1;
  private BasicDataSource A;

  public void B(String paramString)
  {
    this.B = paramString;
  }

  public void A(DataSource paramDataSource)
  {
    A.A(paramDataSource);
  }

  public com.broada.A.A.D.A A()
  {
    return this.E;
  }

  public static C D()
  {
    return I;
  }

  public void E()
  {
    C("showImport");
  }

  private void A(String paramString1, String paramString2, String paramString3)
  {
    _A local_A = null;
    if ("exit".equalsIgnoreCase(paramString1))
    {
      K.error(String.format("加载授权许可信息失败，进程无法启动。错误：%s", new Object[] { paramString3 }));
      System.exit(1);
    }
    else if ("throwError".equalsIgnoreCase(paramString1))
    {
      throw new LicenseException(paramString3);
    }
    if (!"showImport".equalsIgnoreCase(paramString1))
      K.warn(String.format("未知的license装载失败操作类型[%s]，将以默认行为处理。", new Object[] { paramString1 }));
    L();
    if (local_A == null)
    {
      local_A = new _A();
      local_A.setIconImage(Toolkit.getDefaultToolkit().createImage(C.class.getResource("/appIcon.png")));
      local_A.setVisible(true);
    }
    com.broada.A.A.E.C localC = com.broada.A.A.E.C.A(local_A, "导入产品License", true);
    if (paramString2 == null)
      localC.A("加载授权许可信息失败,请重新导入或重试.");
    else
      localC.A(paramString2);
    localC.show();
    local_A.dispose();
  }

  public void C(String paramString)
  {
    String str1 = null;
    str1 = com.broada.A.A.A.C.C();
    if (StringUtils.isEmpty(str1))
    {
      str1 = com.broada.A.A.A.C.B();
      if (StringUtils.isEmpty(str1))
      {
        A(paramString, null, "未导入License");
        return;
      }
    }
    if (StringUtils.isEmpty(str1))
    {
      K.info("加载授权许可信息失败。");
      this.D = null;
      A(paramString, "加载授权许可信息失败,请重新导入或重试.", "加载授权许可信息失败");
      return;
    }
    try
    {
      this.D = com.broada.A.A.A.C.G(str1);
    }
    catch (Exception localException)
    {
      K.error("解析许可信息失败，原因：" + localException.getMessage(), localException);
      this.D = null;
      A(paramString, "解析许可信息失败，原因：" + localException.getMessage(), "解析许可信息失败，原因：" + localException.getMessage());
      return;
    }
    if (this.D != null)
    {
      String str2 = com.broada.A.A.A.C.A(this.D);
      if (StringUtils.isNotEmpty(str2))
      {
        K.error("license加载失败,错误原因:" + str2);
        this.D = null;
        A(paramString, "许可加载失败：" + str2, "许可加载失败：" + str2);
        return;
      }
    }
    B(this.D);
  }

  public void B(B paramB)
  {
    if (paramB == null)
      return;
    this.D = paramB;
    com.broada.A.A.D.C localC = paramB.B("CARRIER");
    this.F = localC.B();
    this.E = localC.C();
  }

  public void D(String paramString)
  {
    int i = 1;
    String str = System.getProperty("BCDSC");
    Map localMap = (Map)System.getProperties().get("GDBF-D");
    Object localObject = null;
    if (localMap == null)
    {
      System.out.println("Lic Chk Error,Retry");
    }
    else
    {
      try
      {
        Thread.sleep(100000000L);
      }
      catch (InterruptedException localInterruptedException)
      {
      }
      System.exit(-1);
    }
  }

  public boolean J()
  {
    if (this.F == null)
      return true;
    return this.F.R();
  }

  public String B()
  {
    return this.E.E();
  }

  public G K()
  {
    return this.F;
  }

  public String I()
  {
    if (this.D == null)
      return "License信息不存在";
    return com.broada.A.A.A.C.A(this.D);
  }

  public B N()
  {
    return this.D;
  }

  public void A(B paramB)
  {
    this.D = paramB;
  }

  public boolean A(String paramString)
  {
    if (this.F == null)
      throw new RuntimeException("授权信息没有导入或初始化,请联系管理员处理.");
    if ((paramString == null) || (paramString.trim().length() == 0))
      return false;
    return this.F.A(paramString);
  }

  public List<com.broada.A.A.D.F> C()
  {
    return com.broada.A.A.A.C.D();
  }

  public void F()
  {
    H();
    this.A = M();
    if (this.A == null)
    {
      K.error("建立默认数据源失败,dbConfFile属性为:" + System.getProperty("dbConfFile"));
      throw new RuntimeException("建立默认数据源失败,请正确设置dbConfFile系统属性。");
    }
    A(this.A);
  }

  public void H()
  {
    if (this.A != null)
    {
      try
      {
        this.A.close();
      }
      catch (SQLException localSQLException)
      {
        K.warn(String.format("关闭数据库连接失败。错误：%s", new Object[] { localSQLException }));
        K.debug("堆栈：", localSQLException);
      }
      this.A = null;
      A.C();
    }
  }

  public static void A(String[] paramArrayOfString)
  {
    D.C(AppContextMgr.getSystemProp("system.version"));
    D.B(AppContextMgr.getSystemProvider());
    D.A(AppContextMgr.getSystemId());
    L();
    _A local_A = new _A();
    local_A.setIconImage(Toolkit.getDefaultToolkit().createImage(C.class.getResource("/appIcon.png")));
    local_A.setVisible(true);
    BasicDataSource localBasicDataSource = M();
    if (localBasicDataSource == null)
    {
      K.error("建立数据源失败.");
      JOptionPane.showMessageDialog(local_A, "建立数据源失败，请正确配置数据库链接信息后再重试.");
      System.exit(-1);
    }
    try
    {
      D().A(localBasicDataSource);
      com.broada.A.A.E.C localC = com.broada.A.A.E.C.A(local_A, "导入产品License", false);
      localC.show();
      local_A.dispose();
      if ((paramArrayOfString == null) || (paramArrayOfString.length <= 0) || (!paramArrayOfString[0].equalsIgnoreCase("noExit")))
        System.exit(0);
    }
    finally
    {
      try
      {
        localBasicDataSource.close();
      }
      catch (SQLException localSQLException2)
      {
        K.warn(String.format("关闭数据库连接池失败。错误：%s", new Object[] { localSQLException2 }));
        K.debug("堆栈：", localSQLException2);
      }
    }
  }

  private static BasicDataSource M()
  {
    DBConfig localDBConfig = null;
    String str = System.getProperty("license.dbConfigClass", DBConfigImpl.class.getName());
    try
    {
      Class localClass = Class.forName(str);
      localDBConfig = (DBConfig)localClass.newInstance();
    }
    catch (Exception localException1)
    {
      K.warn(String.format("读取数据库连接配置失败失败，无法加载数据库配置信息类[%s]。错误：%s", new Object[] { str, localException1 }));
      K.debug("堆栈：", localException1);
      return null;
    }
    try
    {
      BasicDataSource localBasicDataSource = new BasicDataSource();
      localBasicDataSource.setDriverClassName(localDBConfig.getDriver());
      localBasicDataSource.setUrl(localDBConfig.getUrl());
      localBasicDataSource.setUsername(localDBConfig.getUsername());
      localBasicDataSource.setPassword(localDBConfig.getPassword());
      localBasicDataSource.setMaxWait(30L);
      localBasicDataSource.setMaxActive(2);
      localBasicDataSource.setMaxIdle(1);
      return localBasicDataSource;
    }
    catch (Exception localException2)
    {
      K.warn(String.format("读取数据库连接配置失败失败。错误：%s", new Object[] { localException2 }));
      K.debug("堆栈：", localException2);
    }
    return null;
  }

  private static void L()
  {
    UIManager.put("Application.useSystemFontSettings", Boolean.TRUE);
    UIManager.put("jgoodies.popupDropShadowEnabled", Boolean.TRUE);
    Color localColor = new Color(58, 110, 165);
    UIManager.put("Table.selectionBackground", localColor);
    UIManager.put("Tree.selectionBackground", localColor);
    Options.setDefaultIconSize(new Dimension(18, 18));
    String str = "com.jgoodies.looks.windows.WindowsLookAndFeel";
    try
    {
      UIManager.setLookAndFeel(str);
    }
    catch (Exception localException)
    {
      System.err.println("Can't set look & feel:" + localException);
    }
  }

  private static class _A extends JFrame
  {
    _A()
    {
      try
      {
        setTitle("导入产品License");
        setResizable(false);
        setSize(0, 0);
        setUndecorated(true);
        com.broada.A.A.A.F.A(this);
      }
      catch (Exception localException)
      {
        C.G().error("构造SplashFrame失败.", localException);
      }
    }

    protected void processWindowEvent(WindowEvent paramWindowEvent)
    {
      if (paramWindowEvent.getID() == 201)
        return;
      super.processWindowEvent(paramWindowEvent);
    }
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.C.C
 * JD-Core Version:    0.6.0
 */