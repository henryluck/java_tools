package com.broada.A.A.C.A;

import com.broada.A.A.A.E;
import com.broada.A.A.C.B;
import com.broada.common.machine.MachineAPI;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class A
  implements B
{
  private static final Log A = LogFactory.getLog(A.class);

  public String A()
  {
    String[] arrayOfString = MachineAPI.getMAC();
    if ((arrayOfString != null) && (arrayOfString.length > 0))
      return arrayOfString[0];
    return "";
  }

  public com.broada.A.A.D.A A(String paramString1, String paramString2)
  {
    if ((E.H(paramString1)) || (E.H(paramString2)))
      return null;
    com.broada.A.A.D.A localA = new com.broada.A.A.D.A();
    localA.F(paramString1);
    localA.A(new Date());
    localA.B(paramString2);
    localA.A(com.broada.A.A.D.D.C());
    localA.G(com.broada.A.A.D.D.B());
    String str = "127.0.0.1";
    try
    {
      str = InetAddress.getLocalHost().getHostAddress();
      if ("127.0.0.1".equals(str))
      {
        A.debug("通过localhost获取到的是回环地址，尝试通过遍历网卡获取地址.");
        try
        {
          str = B();
        }
        catch (SocketException localSocketException1)
        {
          A.error("遍历网卡获取地址时失败.", localSocketException1);
        }
      }
    }
    catch (UnknownHostException localUnknownHostException)
    {
      A.error("获取本机IP地址失败,尝试通过遍历网卡获取地址.", localUnknownHostException);
      try
      {
        str = B();
      }
      catch (SocketException localSocketException2)
      {
        A.error("遍历网卡获取地址时失败.", localSocketException2);
      }
    }
    localA.C(str);
    return localA;
  }

  private static String B()
    throws SocketException
  {
    Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
    while (localEnumeration1.hasMoreElements())
    {
      NetworkInterface localNetworkInterface = (NetworkInterface)localEnumeration1.nextElement();
      Enumeration localEnumeration2 = localNetworkInterface.getInetAddresses();
      while (localEnumeration2.hasMoreElements())
      {
        InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
        String str = localInetAddress.getHostAddress();
        if (!"127.0.0.1".equals(str))
          return str;
      }
    }
    return "127.0.0.1";
  }

  public boolean A(String paramString)
  {
    try
    {
      return com.broada.A.A.C.A.B().A(paramString);
    }
    catch (Exception localException)
    {
      A.error("保存客户名称错误:" + localException.getMessage(), localException);
    }
    throw new RuntimeException("保存客户名称错误,可能是数据库链接信息配置错误或者数据表不正确。", localException);
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.C.A.A
 * JD-Core Version:    0.6.0
 */