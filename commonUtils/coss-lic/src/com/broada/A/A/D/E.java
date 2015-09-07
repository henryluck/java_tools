package com.broada.A.A.D;

public enum E
{
  private double E;
  private String C;
  private String A;

  private E(double paramDouble, String paramString1, String paramString2)
  {
    this.E = paramDouble;
    this.C = paramString1;
    this.A = paramString2;
  }

  public double C()
  {
    return this.E;
  }

  public String A()
  {
    return this.C;
  }

  public String B()
  {
    return this.A;
  }

  public static E B(String paramString)
  {
    for (E localE : values())
      if (localE.A().equals(paramString))
        return localE;
    throw new IllegalArgumentException(String.format("未知的版本[编码：%s]", new Object[] { paramString }));
  }

  public static E A(String paramString)
  {
    for (E localE : values())
      if (localE.B().equals(paramString))
        return localE;
    throw new IllegalArgumentException(String.format("未知的版本[名称：%s]", new Object[] { paramString }));
  }

  public String toString()
  {
    return this.A;
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.D.E
 * JD-Core Version:    0.6.0
 */