package com.broada.A.A.D;

public final class C
{
  private String B;
  private String A;
  private A C;
  private G D;

  public String A(E paramE)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("<product name=\"" + this.B + "\" version=\"" + this.A + "\">");
    if (this.C != null)
      localStringBuffer.append(this.C.A(paramE));
    if (this.D != null)
      localStringBuffer.append(this.D.A(paramE));
    localStringBuffer.append("</product>");
    return localStringBuffer.toString();
  }

  public String D()
  {
    return this.B;
  }

  public void B(String paramString)
  {
    this.B = paramString;
  }

  public A C()
  {
    return this.C;
  }

  public void A(A paramA)
  {
    this.C = paramA;
  }

  public String A()
  {
    return this.A;
  }

  public void A(String paramString)
  {
    this.A = paramString;
  }

  public G B()
  {
    return this.D;
  }

  public void A(G paramG)
  {
    this.D = paramG;
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.D.C
 * JD-Core Version:    0.6.0
 */