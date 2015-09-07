package com.broada.A.A.D;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class B
  implements Serializable
{
  private static final long serialVersionUID = 3099294540475539892L;
  public static final double C = 1.0D;
  public static final double A = 1.1D;
  public static final E E = E.D;
  private String B = "";
  private String F = E.A();
  private String D;
  private String G;
  private List H = new ArrayList();

  public String C()
  {
    return this.B;
  }

  public void C(String paramString)
  {
    this.B = paramString;
  }

  public List B()
  {
    return this.H;
  }

  public void A(List paramList)
  {
    this.H = paramList;
  }

  public String A()
  {
    return this.F;
  }

  public void A(String paramString)
  {
    this.F = paramString;
  }

  public String D()
  {
    return this.G;
  }

  public String G()
  {
    return this.D;
  }

  public void E(String paramString)
  {
    this.D = paramString;
  }

  public void D(String paramString)
  {
    this.G = paramString;
  }

  public void A(C paramC)
  {
    this.H.add(paramC);
  }

  public void B(C paramC)
  {
    this.H.remove(paramC);
  }

  public C B(String paramString)
  {
    return A(paramString, null);
  }

  public C A(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || ("".equals(paramString1)))
      return null;
    if ("".equals(paramString2))
      paramString2 = null;
    int i = paramString2 == null ? 0 : 1;
    Iterator localIterator = this.H.iterator();
    while (localIterator.hasNext())
    {
      C localC = (C)localIterator.next();
      if ((localC != null) && (localC.D().equals(paramString1)))
      {
        if (i == 0)
          return localC;
        if ((i != 0) && (paramString2.equals(localC.A())))
          return localC;
      }
    }
    return null;
  }

  public String F()
  {
    String str = "\r\n";
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n");
    localStringBuffer.append("<license version=\"" + A() + "\"");
    if ((C() != null) && (C().length() > 0))
      localStringBuffer.append(" id=\"" + C() + "\"");
    localStringBuffer.append(">\r\n");
    Iterator localIterator = this.H.iterator();
    while (localIterator.hasNext())
    {
      C localC = (C)localIterator.next();
      localStringBuffer.append(localC.A(E.B(A())) + "\r\n");
    }
    localStringBuffer.append("<signature>" + G() + "</signature>" + "\r\n");
    localStringBuffer.append("<certificate>" + D() + "</certificate>" + "\r\n");
    localStringBuffer.append("</license>");
    return localStringBuffer.toString();
  }

  public String E()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<?xml version=\"1.0\"?>");
    localStringBuffer.append("<license version=\"" + A() + "\"");
    if ((C() != null) && (C().length() > 0))
      localStringBuffer.append(" id=\"" + C() + "\"");
    localStringBuffer.append(">");
    Iterator localIterator = this.H.iterator();
    while (localIterator.hasNext())
    {
      C localC = (C)localIterator.next();
      localStringBuffer.append(localC.A(E.B(A())));
    }
    localStringBuffer.append("</license>");
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.D.B
 * JD-Core Version:    0.6.0
 */