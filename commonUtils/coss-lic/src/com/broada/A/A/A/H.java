package com.broada.A.A.A;

import com.broada.A.A.D.B;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public final class H
{
  public static void A(B paramB, char[] paramArrayOfChar1, char[] paramArrayOfChar2)
    throws Exception
  {
    String str1 = "BroadviewCOSS.keystore";
    String str2 = "BroadviewCOSS";
    String str3 = "BroadviewCOSS.cer";
    A(paramB, "BroadviewCOSS.keystore", "BroadviewCOSS", "BroadviewCOSS.cer", paramArrayOfChar1, paramArrayOfChar2);
  }

  public static void A(B paramB, String paramString1, String paramString2, String paramString3, char[] paramArrayOfChar1, char[] paramArrayOfChar2)
    throws Exception
  {
    InputStream localInputStream = A(paramString1);
    PrivateKey localPrivateKey = A(localInputStream, paramString2, paramArrayOfChar1, paramArrayOfChar2);
    localInputStream.close();
    byte[] arrayOfByte1 = MessageDigest.getInstance("SHA-1").digest(paramB.E().getBytes());
    String str = A(arrayOfByte1, localPrivateKey);
    paramB.E(str);
    localInputStream = A(paramString3);
    if (paramString3 != null)
    {
      byte[] arrayOfByte2 = A(localInputStream);
      paramB.D(Base64.encode(arrayOfByte2));
    }
    localInputStream.close();
  }

  public static String A(byte[] paramArrayOfByte, PrivateKey paramPrivateKey)
    throws Exception
  {
    Signature localSignature = Signature.getInstance("DSA");
    localSignature.initSign(paramPrivateKey);
    localSignature.update(paramArrayOfByte);
    byte[] arrayOfByte = localSignature.sign();
    return Base64.encode(arrayOfByte);
  }

  public static void A(B paramB)
    throws Exception
  {
    X509Certificate localX509Certificate = null;
    try
    {
      localX509Certificate = B(paramB.D());
    }
    catch (Exception localException1)
    {
      throw new Exception("读取数字证书失败。", localException1);
    }
    try
    {
      localX509Certificate.verify(localX509Certificate.getPublicKey());
    }
    catch (Exception localException2)
    {
      throw new Exception("获取公钥失败。", localException2);
    }
    Signature localSignature = null;
    byte[] arrayOfByte1 = MessageDigest.getInstance("SHA-1").digest(paramB.E().getBytes());
    try
    {
      localSignature = Signature.getInstance("DSA");
      localSignature.initVerify(localX509Certificate.getPublicKey());
      localSignature.update(arrayOfByte1);
    }
    catch (Exception localException3)
    {
      throw new Exception("验证签名信息失败。", localException3);
    }
    byte[] arrayOfByte2 = Base64.decode(paramB.G());
    if (!localSignature.verify(arrayOfByte2))
      throw new Exception("验证签名信息失败，可能许可被修改！");
  }

  private static PrivateKey A(InputStream paramInputStream, String paramString, char[] paramArrayOfChar1, char[] paramArrayOfChar2)
    throws Exception
  {
    PrivateKey localPrivateKey = null;
    KeyStore localKeyStore = null;
    try
    {
      localKeyStore = KeyStore.getInstance("JKS", "SUN");
      localKeyStore.load(paramInputStream, paramArrayOfChar1);
    }
    catch (IOException localIOException)
    {
      if (localIOException.getMessage().equals("Keystore was tampered with, or password was incorrect"))
        throw new Exception("密钥库密码不正确。", localIOException);
      new Exception("读取密钥失败。", localIOException);
    }
    catch (Exception localException1)
    {
      throw localException1;
    }
    Arrays.fill(paramArrayOfChar1, ' ');
    try
    {
      localPrivateKey = (PrivateKey)localKeyStore.getKey(paramString, paramArrayOfChar2);
    }
    catch (UnrecoverableKeyException localUnrecoverableKeyException)
    {
      throw new Exception("密钥库主密码不正确。", localUnrecoverableKeyException);
    }
    catch (Exception localException2)
    {
      throw localException2;
    }
    Arrays.fill(paramArrayOfChar2, ' ');
    return localPrivateKey;
  }

  private static final X509Certificate B(String paramString)
    throws Exception
  {
    byte[] arrayOfByte = Base64.decode(paramString);
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
    X509Certificate localX509Certificate = (X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(localByteArrayInputStream);
    localByteArrayInputStream.close();
    return localX509Certificate;
  }

  private static InputStream A(String paramString)
    throws Exception
  {
    String str = "/com/broada/carrier/license/maker/";
    InputStream localInputStream = H.class.getResourceAsStream(str + paramString);
    if (localInputStream == null)
      throw new FileNotFoundException(str + paramString + "文件不存在.");
    return localInputStream;
  }

  public static byte[] A(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i = -1;
    while ((i = paramInputStream.read()) != -1)
      localByteArrayOutputStream.write(i);
    paramInputStream.close();
    localByteArrayOutputStream.flush();
    return localByteArrayOutputStream.toByteArray();
  }
}

/* Location:           C:\Users\mike\Desktop\platform.common.l4c.jar
 * Qualified Name:     com.broada.A.A.A.H
 * JD-Core Version:    0.6.0
 */