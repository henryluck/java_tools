package com.broada.A.A.D;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class F
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 3450108768691481358L;
  private String F;
  private String C;
  private boolean E = false;
  private List<F> B = new LinkedList();
  private String A;
  private List<F> D = new LinkedList();

  public F()
  {
  }

  public F(String paramString1, String paramString2)
  {
    this.F = paramString1;
    this.C = paramString2;
  }

  public String C()
  {
    return this.F;
  }

  public void A(String paramString)
  {
    this.F = paramString;
  }

  public String B()
  {
    return this.C;
  }

  public void C(String paramString)
  {
    this.C = paramString;
  }

  public boolean A()
  {
    return this.E;
  }

  public void A(boolean paramBoolean)
  {
    this.E = paramBoolean;
  }

  public void E(String paramString)
  {
    if (paramString != null)
      paramString = paramString.trim();
    this.E = (("1".equals(paramString)) || ("Y".equalsIgnoreCase(paramString)));
  }

  public List<F> E()
  {
    return Collections.unmodifiableList(this.B);
  }

  public List<F> D()
  {
    return Collections.unmodifiableList(this.D);
  }

  private void A(List<F> paramList)
  {
    if (paramList == null)
      paramList = new LinkedList();
    this.B = paramList;
    this.A = B(paramList);
  }

  public void B(F paramF)
  {
    if (paramF == null)
      return;
    int i = this.B.indexOf(paramF);
    if (i >= 0)
      this.B.set(i, paramF);
    else
      this.B.add(paramF);
    paramF.A(this);
    this.A = B(this.B);
  }

  private void A(F paramF)
  {
    if (paramF == null)
      return;
    int i = this.D.indexOf(paramF);
    if (i >= 0)
      this.D.set(i, paramF);
    else
      this.D.add(paramF);
  }

  public static List<F> B(String paramString)
  {
    if (paramString == null)
      return null;
    if (paramString.trim().length() == 0)
      return Collections.EMPTY_LIST;
    String[] arrayOfString1 = paramString.split(",");
    LinkedList localLinkedList = new LinkedList();
    for (String str : arrayOfString1)
      localLinkedList.add(new F(str.trim(), ""));
    return localLinkedList;
  }

  public static String B(List<F> paramList)
  {
    if (paramList == null)
      return "";
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      F localF = (F)localIterator.next();
      localStringBuffer.append(localF.C() + ",");
    }
    localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
    return localStringBuffer.toString();
  }

  public String F()
  {
    return this.A;
  }

  public void D(String paramString)
  {
    this.A = paramString;
    if (this.B.size() == 0)
      this.B = B(paramString);
  }

  protected Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (this.F == null))
      return false;
    if ((paramObject instanceof F))
    {
      F localF = (F)paramObject;
      if (this.F.equals(localF.C()))
        return true;
    }
    return false;
  }

  public int hashCode()
  {
    if (this.F == null)
      return 0;
    return this.F.hashCode();
  }

  public String toString()
  {
    return this.C;
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.D.F
 * JD-Core Version:    0.6.0
 */