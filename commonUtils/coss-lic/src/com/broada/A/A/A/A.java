package com.broada.A.A.A;

import java.io.PrintStream;

public class A
{
  private static byte[] A = new byte[256];
  private static char[] B;

  public static char[] A(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[(paramArrayOfByte.length + 2) / 3 * 4];
    int i = 0;
    for (int j = 0; i < paramArrayOfByte.length; j += 4)
    {
      int k = 0;
      int m = 0;
      int n = 0xFF & paramArrayOfByte[i];
      n <<= 8;
      if (i + 1 < paramArrayOfByte.length)
      {
        n |= 0xFF & paramArrayOfByte[(i + 1)];
        m = 1;
      }
      n <<= 8;
      if (i + 2 < paramArrayOfByte.length)
      {
        n |= 0xFF & paramArrayOfByte[(i + 2)];
        k = 1;
      }
      arrayOfChar[(j + 3)] = B[64];
      n >>= 6;
      arrayOfChar[(j + 2)] = B[64];
      n >>= 6;
      arrayOfChar[(j + 1)] = B[(n & 0x3F)];
      n >>= 6;
      arrayOfChar[(j + 0)] = B[(n & 0x3F)];
      i += 3;
    }
    return arrayOfChar;
  }

  public static byte[] A(char[] paramArrayOfChar)
  {
    int i = paramArrayOfChar.length;
    for (int j = 0; j < paramArrayOfChar.length; j++)
    {
      if ((paramArrayOfChar[j] <= 'ÿ') && (A[paramArrayOfChar[j]] >= 0))
        continue;
      i--;
    }
    j = i / 4 * 3;
    if (i % 4 == 3)
      j += 2;
    if (i % 4 == 2)
      j++;
    byte[] arrayOfByte = new byte[j];
    int k = 0;
    int m = 0;
    int n = 0;
    for (int i1 = 0; i1 < paramArrayOfChar.length; i1++)
    {
      int i2 = paramArrayOfChar[i1] > 'ÿ' ? -1 : A[paramArrayOfChar[i1]];
      if (i2 < 0)
        continue;
      m <<= 6;
      k += 6;
      m |= i2;
      if (k < 8)
        continue;
      k -= 8;
      arrayOfByte[(n++)] = (byte)(m >> k & 0xFF);
    }
    if (n != arrayOfByte.length)
      throw new Error("Miscalculated data length (wrote " + n + " instead of " + arrayOfByte.length + ")");
    return arrayOfByte;
  }

  public static String B(String paramString)
  {
    return new String(A(paramString.getBytes()));
  }

  public static String A(String paramString)
  {
    return new String(A(paramString.toCharArray()));
  }

  public static void A(String[] paramArrayOfString)
  {
    String str1 = "中文测试<xml>\n<Test1 name=\"name\">中文</Test1>";
    String str2 = B(str1);
    System.out.println(str2);
    System.out.println(A(str2));
  }

  static
  {
    for (int i = 0; i < 256; i++)
      A[i] = -1;
    for (i = 65; i <= 90; i++)
      A[i] = (byte)(i - 65);
    for (i = 97; i <= 122; i++)
      A[i] = (byte)(26 + i - 97);
    for (i = 48; i <= 57; i++)
      A[i] = (byte)(52 + i - 48);
    A[43] = 62;
    A[47] = 63;
    B = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.A.A
 * JD-Core Version:    0.6.0
 */