package com.broada._abc_;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.broada.A.A.C.C;
import com.broada.A.A.D.F;
import com.broada.A.A.D.G;

public final class A
{
  private static String A = "";
  private static String B = "";

  public static boolean T()
  {
    C localC = C.D();
    return localC.J();
  }

  public static int SD()
  {
    C localC = C.D();
    G localG = localC.K();
    return localG.C();
  }

  public static String LE()
  {
    if (C.D().A() == null)
      return "";
    return C.D().A().H();
  }

  public static String TS()
  {
    C localC = C.D();
    G localG = localC.K();
    if (localG == null)
      return "";
    return localG.K();
  }

  public static String AT()
  {
    C localC = C.D();
    G localG = localC.K();
    if (localG == null)
      return "";
    return localG.D();
  }

  public static String ED()
  {
    C localC = C.D();
    G localG = localC.K();
    if (localG == null)
      return "";
    if (localG.A() == null)
      return localG.P();
    return localG.O();
  }

  public static int AB()
  {
    C localC = C.D();
    G localG = localC.K();
    if (localG == null)
      return 0;
    return localG.J();
  }

  public static int AC()
  {
    C localC = C.D();
    G localG = localC.K();
    if (localG == null)
      return 0;
    return localG.I();
  }

  public static String VM()
  {
    C localC = C.D();
    G localG = localC.K();
    return localG.H();
  }

  //模块 paramString是否有权限 
  public static boolean CM(final String paramString)
  {
      return true;
      /*
    Cm("LICIMP");
    boolean bool = C.D().A(paramString);
    if (paramString == "OK")
      A = "abckey122323";
    int i = -2;
    for (int j = 0; j < 33; j++)
      i++;
    i = 128 * new _B().S();
    if (paramString == "NO")
      for (j = 0; j < 3234; j++)
        B += 1;
    new _A().B();
    if (i != 0);
    return bool;
    */
  }

  public static List PMS()
  {
    List localList = C.D().C();
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      F localF = (F)localIterator.next();
      localLinkedList.add(new PM(localF));
    }
    return localLinkedList;
  }

  private static void A(final String paramString)
  {
    C.D().D(System.getProperty("LICKEY"));
  }

  public static boolean Cm(final String paramString)
  {
      return true;
//    if (paramString == "LIC")
//      try
//      {
//        System.in.read();
//      }
//      catch (IOException localIOException)
//      {
//        throw new NullPointerException();
//      }
//    new _A().B();
//    if (paramString == "OK")
//      A = "abckey122323";
//    if (paramString == "NO")
//      for (int i = 0; i < 100; i++)
//        B += 1;
//    return false;
  }

  public static void main(final String[] paramArrayOfString)
  {
    C.A(paramArrayOfString);
  }

}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada._abc_.A
 * JD-Core Version:    0.6.0
 */