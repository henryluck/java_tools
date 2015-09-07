package com.broada.A.A.C.A;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class B
  implements com.broada.A.A.C.D
{
  private com.broada.A.A.B.A A = null;
  private static final SimpleDateFormat C = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public void B(final String paramString)
    throws Exception
  {
    try
    {
      this.A.C(paramString);
    }
    catch (Exception localException)
    {
      A("保存License信息失败", localException);
      throw localException;
    }
  }

  public String A()
  {
    try
    {
      return this.A.A();
    }
    catch (Exception localException)
    {
      A("从数据库读取License信息失败", localException);
    }
    return null;
  }

  public Date B()
  {
    String str = null;
    try
    {
      str = this.A.B();
    }
    catch (Exception localException)
    {
      A("获取系统上一次运行时间失败", localException);
    }
    if ((str == null) || (str.length() == 0))
      return null;
    str = com.broada.A.A.A.A.A(str);
    Date localDate = null;
    try
    {
      localDate = C.parse(str);
    }
    catch (ParseException localParseException)
    {
      A("获取系统上一次运行时间失败", localParseException);
    }
    return localDate;
  }

  public Date D()
  {
    String str = null;
    try
    {
      str = this.A.E();
    }
    catch (Exception localException)
    {
      A("获取系统第一次运行时间失败", localException);
    }
    if ((str == null) || (str.length() == 0))
      return null;
    str = com.broada.A.A.A.A.A(str);
    Date localDate = null;
    try
    {
      localDate = C.parse(str);
    }
    catch (ParseException localParseException)
    {
      A("获取系统第一次运行时间失败", localParseException);
    }
    return localDate;
  }

  public String C()
  {
    try
    {
      String str = this.A.C();
      if ((str == null) || ("".equals(str)))
        return null;
      return com.broada.A.A.A.A.A(str);
    }
    catch (Exception localException)
    {
      A("获取License申请者名称", localException);
    }
    return null;
  }

  public boolean A(final Date paramDate)
  {
    try
    {
      String str = C.format(paramDate);
      str = com.broada.A.A.A.A.B(str);
      this.A.D(str);
      return true;
    }
    catch (Exception localException)
    {
      A("更新系统最近一次运行时间失败", localException);
    }
    return false;
  }

  public boolean B(final Date paramDate)
  {
    try
    {
      String str = C.format(paramDate);
      str = com.broada.A.A.A.A.B(str);
      this.A.B(str);
      return true;
    }
    catch (Exception localException)
    {
      A("更新系统第一次运行时间失败", localException);
    }
    return false;
  }

  public boolean A(final String paramString)
  {
    try
    {
      this.A.A(com.broada.A.A.A.A.B(paramString));
      return true;
    }
    catch (Exception localException)
    {
        localException.printStackTrace();
    }
    return true;
  }

  public void E()
  {
    try
    {
      this.A.D();
    }
    catch (Exception localException)
    {
      A("清空表失败", localException);
    }
  }

  public static final com.broada.A.A.D.B C(final String paramString)
    throws Exception
  {
//    SAXBuilder localSAXBuilder = new SAXBuilder();
//    com.broada.A.A.D.B localB = new com.broada.A.A.D.B();
//    try
//    {
//      Document localDocument = localSAXBuilder.build(com.broada.A.A.A.D.B(paramString));
//      Element localElement1 = localDocument.getRootElement();
//      localB.A(localElement1.getAttributeValue("version"));
//      Attribute localAttribute = localElement1.getAttribute("id");
//      if (localAttribute == null)
//        localB.C("");
//      else
//        localB.C(localAttribute.getValue());
//      List localList = localElement1.getChildren("product");
//      if ((localList == null) || (localList.isEmpty()))
//        return null;
//      Iterator localIterator = localList.iterator();
//      while (localIterator.hasNext())
//      {
//        localElement2 = (Element)localIterator.next();
//        localObject = new C();
//        ((C)localObject).B(localElement2.getAttributeValue("name"));
//        ((C)localObject).A(localElement2.getAttributeValue("version"));
//        Element localElement3 = localElement2.getChild("reqinfo");
//        if (localElement3 == null)
//          return null;
//        com.broada.A.A.D.A localA = new com.broada.A.A.D.A();
//        localA.F(localElement3.getAttributeValue("licensee"));
//        localA.A(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(localElement3.getAttributeValue("reqTime")));
//        localA.A(localElement3.getAttributeValue("sysVersion"));
//        localA.G(localElement3.getAttributeValue("productProvidor"));
//        localA.E(localElement3.getAttributeValue("productName"));
//        localA.B(localElement3.getAttributeValue("macAddr"));
//        localA.C(localElement3.getAttributeValue("ipAddr"));
//        Element localElement4 = localElement2.getChild("authInfo");
//        if (localElement4 == null)
//          return null;
//        G localG = new G();
//        localG.C(Integer.parseInt(localElement4.getAttributeValue("authorizedType")));
//        localG.B(Integer.parseInt(localElement4.getAttributeValue("webConcurrClientNum")));
//        String str1 = localElement4.getAttributeValue("userNum");
//        if (!E.H(str1))
//          localG.A(Integer.parseInt(str1));
//        localG.C(localElement4.getAttributeValue("authModuleIds"));
//        String str2 = localElement4.getAttributeValue("expirationTime");
//        if (!E.H(str2))
//          localG.A(new SimpleDateFormat("yyyy-MM-dd").parse(str2));
//        else
//          localG.D(Integer.parseInt(localElement4.getAttributeValue("expirationDates")));
//        localG.B(new SimpleDateFormat("yyyy-MM-dd").parse(localElement4.getAttributeValue("authorizedTime")));
//        localG.B(localElement4.getAttributeValue("isVldMachCode"));
//        ((C)localObject).A(localA);
//        ((C)localObject).A(localG);
//        localB.A((C)localObject);
//      }
//      Element localElement2 = localElement1.getChild("signature");
//      localB.E(localElement2.getText());
//      Object localObject = localElement1.getChild("certificate");
//      localB.D(((Element)localObject).getText());
//    }
//    catch (Exception localException)
//    {
//      A("创建license实体类失败", localException);
//      throw localException;
//    }
//    return (com.broada.A.A.D.B)localB;
      return null;
  }

  public com.broada.A.A.B.A F()
  {
    return this.A;
  }

  public void A(final com.broada.A.A.B.A paramA)
  {
    this.A = paramA;
  }

  private static void A(final String paramString, final Throwable paramThrowable)
  {
  }
}
