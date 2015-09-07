package com.broada.A.A.A;

public class E
{
  public static String B(String paramString)
  {
    return paramString == null ? "" : paramString;
  }

  public static String A(Long paramLong)
  {
    return paramLong == null ? "" : paramLong.toString();
  }

  public static String A(Integer paramInteger)
  {
    return paramInteger == null ? "" : paramInteger.toString();
  }

  public static boolean D(String paramString)
  {
    return paramString == null;
  }

  public static boolean A(String paramString)
  {
    return paramString.trim().length() < 1;
  }

  public static boolean H(String paramString)
  {
    return (D(paramString)) || (A(paramString));
  }

  public static String E(String paramString)
  {
    if (A(paramString))
      return null;
    return paramString;
  }

  public static String F(String paramString)
  {
    if (D(paramString))
      return "";
    return paramString;
  }

  public static String G(String paramString)
  {
    if (H(paramString))
      return "&nbsp;";
    return paramString;
  }

  public static String A(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    return paramString1.substring(0, paramInt1) + paramString2 + paramString1.substring(paramInt2);
  }

  public static String A(String paramString, boolean paramBoolean)
  {
    String str = F(paramString);
    if ((paramBoolean) && (str.equalsIgnoreCase("null")))
      str = "";
    return str;
  }

  public static String C(String paramString)
  {
    paramString = paramString.replaceAll(":", "-");
    paramString = paramString.replaceAll("\\\\", "-");
    paramString = paramString.replaceAll("/", "-");
    paramString = paramString.replaceAll("\\*", "-");
    paramString = paramString.replaceAll("\\?", "-");
    paramString = paramString.replaceAll("\"", "-");
    paramString = paramString.replaceAll("<", "-");
    paramString = paramString.replaceAll(">", "-");
    paramString = paramString.replaceAll("\\|", "-");
    return paramString;
  }

  public static String A(String paramString, int paramInt)
  {
    if ((paramString != null) && (paramString.length() > paramInt))
      return paramString.substring(0, paramInt);
    return F(paramString);
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.A.E
 * JD-Core Version:    0.6.0
 */