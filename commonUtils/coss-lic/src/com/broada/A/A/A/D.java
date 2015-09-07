package com.broada.A.A.A;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;

public class D
{
  public static InputStream C(String paramString)
  {
    if (paramString == null)
      paramString = "";
    return new ByteArrayInputStream(paramString.getBytes());
  }

  public static Reader B(String paramString)
  {
    if (paramString == null)
      paramString = "";
    return new StringReader(paramString);
  }

  public static byte[] A(final Serializable paramSerializable)
  {
    byte[] arrayOfByte = new byte[0];
    ByteArrayOutputStream localByteArrayOutputStream = null;
    ObjectOutputStream localObjectOutputStream = null;
    try
    {
      localByteArrayOutputStream = new ByteArrayOutputStream();
      localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObjectOutputStream.writeObject(paramSerializable);
      localObjectOutputStream.flush();
      arrayOfByte = localByteArrayOutputStream.toByteArray();
    }
    catch (Exception localException1)
    {
    }
    try
    {
      if (localByteArrayOutputStream != null)
        localByteArrayOutputStream.close();
      if (localObjectOutputStream != null)
        localObjectOutputStream.close();
    }
    catch (Exception localException2)
    {
    }
    localByteArrayOutputStream = null;
    localObjectOutputStream = null;
    return arrayOfByte;
  }

  public static Object A(final byte[] paramArrayOfByte)
    throws Exception
  {
    Object localObject = null;
    ByteArrayInputStream localByteArrayInputStream = null;
    ObjectInputStream localObjectInputStream = null;
    try
    {
      localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
      localObjectInputStream = new ObjectInputStream(localByteArrayInputStream);
      localObject = localObjectInputStream.readObject();
    }
    catch (Exception localException1)
    {
      throw localException1;
    }
    try
    {
      if (localByteArrayInputStream != null)
        localByteArrayInputStream.close();
      if (localObjectInputStream != null)
        localObjectInputStream.close();
    }
    catch (Exception localException2)
    {
    }
    localByteArrayInputStream = null;
    localObjectInputStream = null;
    return localObject;
  }

  public static String A(final String paramString)
    throws FileNotFoundException, IOException
  {
      
      System.out.println("path======================="+paramString);
    return A(new File(paramString));
  }

  public static String A(final File paramFile)
    throws FileNotFoundException, IOException
  {
    char[] arrayOfChar = new char[1024];
    StringBuffer localStringBuffer = new StringBuffer();
    FileReader localFileReader = new FileReader(paramFile);
    for (int i = localFileReader.read(arrayOfChar); i >= 0; i = localFileReader.read(arrayOfChar))
      localStringBuffer.append(arrayOfChar, 0, i);
    localFileReader.close();
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.A.D
 * JD-Core Version:    0.6.0
 */