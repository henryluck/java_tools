package com.broada.A.A.D;

import com.broada.A.A.A.J;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class A
{
  public static final String E = "CARRIER";
  private String G = "";
  private Date C = null;
  private String H = "";
  private String B = "";
  private String F = "3.0.0";
  private String A = "Broada";
  private String D = "CARRIER";

  public String F()
  {
    return this.D;
  }

  public void E(String paramString)
  {
    this.D = paramString;
  }

  public String G()
    throws Exception
  {
    D();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      J.A(localByteArrayOutputStream, this.G);
      J.A(localByteArrayOutputStream, this.F);
      J.A(localByteArrayOutputStream, this.A);
      J.A(localByteArrayOutputStream, this.B);
      J.A(localByteArrayOutputStream, this.H);
      J.A(localByteArrayOutputStream, this.C);
      J.A(localByteArrayOutputStream, this.D);
    }
    finally
    {
      localByteArrayOutputStream.close();
    }
    char[] arrayOfChar = com.broada.A.A.A.A.A(localByteArrayOutputStream.toByteArray());
    return com.broada.A.A.A.A.B(new String(arrayOfChar));
  }

  public static A D(String paramString)
    throws Exception
  {
    if (com.broada.A.A.A.E.H(paramString))
      throw new Exception("非法的许可申请号：许可申请信息不能为空。");
    A localA = new A();
    paramString = com.broada.A.A.A.A.A(paramString);
    byte[] arrayOfByte = com.broada.A.A.A.A.A(paramString.toCharArray());
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
    try
    {
      localA.F(J.A(localByteArrayInputStream));
      localA.A(J.A(localByteArrayInputStream));
      localA.G(J.A(localByteArrayInputStream));
      localA.C(J.A(localByteArrayInputStream));
      localA.B(J.A(localByteArrayInputStream));
      localA.A(J.C(localByteArrayInputStream));
      localA.E(J.A(localByteArrayInputStream));
    }
    finally
    {
      localByteArrayInputStream.close();
    }
    localA.D();
    return localA;
  }

  public String K()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    if (this.C == null)
      return "";
    return localSimpleDateFormat.format(this.C);
  }

  public String B()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<xml>");
    localStringBuffer.append("<reqinfo licensee=\"" + this.G + "\"");
    localStringBuffer.append(" reqTime=\"" + K() + "\"");
    localStringBuffer.append(" sysVersion=\"" + this.F + "\"");
    localStringBuffer.append(" productProvidor=\"" + this.A + "\"");
    localStringBuffer.append(" productName=\"" + this.D + "\"");
    localStringBuffer.append(" macAddr=\"" + this.H + "\"");
    localStringBuffer.append(" ipAddr=\"" + this.B + "\"/>");
    localStringBuffer.append("</xml>");
    return localStringBuffer.toString();
  }

  public String A(E paramE)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<reqinfo licensee=\"" + this.G + "\"");
    localStringBuffer.append(" reqTime=\"" + K() + "\"");
    localStringBuffer.append(" sysVersion=\"" + this.F + "\"");
    localStringBuffer.append(" productProvidor=\"" + this.A + "\"");
    localStringBuffer.append(" productName=\"" + this.D + "\"");
    localStringBuffer.append(" macAddr=\"" + this.H + "\"");
    localStringBuffer.append(" ipAddr=\"" + this.B + "\"/>");
    return localStringBuffer.toString();
  }

  public void D()
    throws Exception
  {
  }

  public boolean A(A paramA)
  {
    if (paramA == null)
      return false;
    return B().equals(paramA.B());
  }

  public String J()
  {
    return this.B;
  }

  public void C(String paramString)
  {
    this.B = paramString;
  }

  public String H()
  {
    return this.G;
  }

  public void F(String paramString)
  {
    this.G = paramString;
  }

  public String I()
  {
    return this.H;
  }

  public void B(String paramString)
  {
    this.H = paramString;
  }

  public Date C()
  {
    return this.C;
  }

  public void A(Date paramDate)
  {
    this.C = paramDate;
  }

  public String A()
  {
    return this.A;
  }

  public void G(String paramString)
  {
    this.A = paramString;
  }

  public String E()
  {
    return this.F;
  }

  public void A(String paramString)
  {
    this.F = paramString;
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.D.A
 * JD-Core Version:    0.6.0
 */