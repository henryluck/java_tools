package com.broada._abc_;

import com.broada.A.A.D.F;

public final class PM
{
  private F A;

  public PM(F paramF)
  {
    if (paramF == null)
      throw new NullPointerException();
    this.A = paramF;
  }

  public PM(String paramString1, String paramString2)
  {
    this.A = new F(paramString1, paramString2);
  }

  public String getId()
  {
    return this.A.C();
  }

  public String getName()
  {
    return this.A.B();
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (getId() == null))
      return false;
    if ((paramObject instanceof PM))
    {
      PM localPM = (PM)paramObject;
      if (getId().equals(localPM.getId()))
        return true;
    }
    return false;
  }

  public int hashCode()
  {
    if (getId() == null)
      return 0;
    return getId().hashCode();
  }

  public String toString()
  {
    return getName();
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada._abc_.PM
 * JD-Core Version:    0.6.0
 */