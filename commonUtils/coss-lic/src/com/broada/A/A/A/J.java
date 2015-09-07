package com.broada.A.A.A;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class J
{
  public static String A(InputStream paramInputStream)
    throws IOException
  {
    int i = B(paramInputStream);
    if (i >= 200)
      throw new IOException("错误的编码。");
    byte[] arrayOfByte = new byte[i];
    paramInputStream.read(arrayOfByte);
    return new String(arrayOfByte);
  }

  public static boolean D(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1];
    paramInputStream.read(arrayOfByte);
    return arrayOfByte[0] != 0;
  }

  public static int B(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[4];
    paramInputStream.read(arrayOfByte);
    return arrayOfByte[0] & 0xFF | (arrayOfByte[1] & 0xFF) << 8 | (arrayOfByte[2] & 0xFF) << 16 | (arrayOfByte[3] & 0xFF) << 24;
  }

  public static void A(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    if (paramString == null)
    {
      A(paramOutputStream, 0);
    }
    else
    {
      A(paramOutputStream, paramString.getBytes().length);
      paramOutputStream.write(paramString.getBytes());
    }
  }

  public static void A(OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException
  {
    paramOutputStream.write(paramBoolean ? 1 : 0);
  }

  public static void A(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    paramOutputStream.write(paramInt & 0xFF);
    paramOutputStream.write(paramInt >> 8 & 0xFF);
    paramOutputStream.write(paramInt >> 16 & 0xFF);
    paramOutputStream.write(paramInt >> 24 & 0xFF);
  }

  public static void A(OutputStream paramOutputStream, Date paramDate)
    throws IOException
  {
    if (paramDate == null)
    {
      A(paramOutputStream, 0L);
    }
    else
    {
      long l = paramDate.getTime();
      A(paramOutputStream, l);
    }
  }

  public static void A(OutputStream paramOutputStream, long paramLong)
    throws IOException
  {
    paramOutputStream.write((int)(paramLong & 0xFF));
    paramOutputStream.write((int)(paramLong >> 8 & 0xFF));
    paramOutputStream.write((int)(paramLong >> 16 & 0xFF));
    paramOutputStream.write((int)(paramLong >> 24 & 0xFF));
    paramOutputStream.write((int)(paramLong >> 32 & 0xFF));
    paramOutputStream.write((int)(paramLong >> 40 & 0xFF));
    paramOutputStream.write((int)(paramLong >> 48 & 0xFF));
    paramOutputStream.write((int)(paramLong >> 56 & 0xFF));
  }

  public static Date C(InputStream paramInputStream)
    throws IOException
  {
    long l = E(paramInputStream);
    return new Date(l);
  }

  public static long E(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[8];
    paramInputStream.read(arrayOfByte);
    return arrayOfByte[0] & 0xFF | (arrayOfByte[1] & 0xFF) << 8 | (arrayOfByte[2] & 0xFF) << 16 | (arrayOfByte[3] & 0xFF) << 24 | (arrayOfByte[4] & 0xFF) << 32 | (arrayOfByte[5] & 0xFF) << 40 | (arrayOfByte[6] & 0xFF) << 48 | (arrayOfByte[7] & 0xFF) << 56;
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.A.J
 * JD-Core Version:    0.6.0
 */