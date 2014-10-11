/*
 * ewrqwer.java<br> 2006-4-10<br> Copyright (c) 2003-2006 MDCL-FRONTLINE, Inc.<br>
 * All rights reserved.
 */
package com.jlx.encrypt.des;

/**
 * Title: DESEncryptUtil.java<br>
 * Description: 该类是加密处理类.主要用来对数据进行加密,解密操作
 * 
 * @author jianglx
 * @version 1.0<br>
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import com.jlx.encrypt.EncryptException;

public class DESEncryptUtil {
	/**
	 * 获得DES加密的密钥。在交易处理的过程中应该定时更 换密钥。需要JCE的支持，如果jdk版本低于1.4，则需要
	 * 安装jce-1_2_2才能正常使用。
	 * 
	 * @return Key 返回对称密钥
	 * @throws java.security.NoSuchAlgorithmException
	 * @see util.EncryptUtil 其中包括加密和解密的方法
	 */
	public static Key getKey() throws NoSuchAlgorithmException {
		Security.insertProviderAt(new com.sun.crypto.provider.SunJCE(), 1);
		KeyGenerator generator = KeyGenerator.getInstance("DES");
		generator.init(new SecureRandom());
		Key key = generator.generateKey();
		return key;
	}

	/**
	 * 将指定的数据根据提供的密钥进行加密
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            需要加密的数据
	 * @return byte[] 加密后的数据
	 * @throws util.EncryptException
	 */
	public static byte[] doEncrypt(Key key, byte[] data)
			throws EncryptException {
		try {
			// Get a cipher object
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

			// Encrypt
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// byte[] stringBytes = amalgam.getBytes("UTF8");
			byte[] raw = cipher.doFinal(data);
			// BASE64Encoder encoder = new BASE64Encoder();
			// String base64 = encoder.encode(raw);
			return raw;
		}catch (Exception e) {
			e.printStackTrace();
			throw new EncryptException("Do encrypt occurs Exception.["
					+ e.getMessage() + "]");
		}
	}

	/**
	 * 将给定的已加密的数据通过指定的密钥进行解密
	 * 
	 * @param key
	 *            密钥
	 * @param raw
	 *            待解密的数据
	 * @return byte[] 解密后的数据
	 * @throws util.EncryptException
	 */
	public static byte[] doDecrypt(Key key, byte[] raw) throws EncryptException {
		try {
			// Get a cipher object
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// Decrypt
			cipher.init(Cipher.DECRYPT_MODE, key);
			// BASE64Decoder decoder = new BASE64Decoder();
			// byte[] raw = decoder.decodeBuffer(data);
			byte[] data = cipher.doFinal(raw);
			// String result = new String(stringBytes, "UTF8");
			// System.out.println("the decrypted data is: " + result);
			return data;
		}catch (Exception e) {
			e.printStackTrace();
			throw new EncryptException("Do decrypt occurs Exception.["
					+ e.getMessage() + "]");
		}
	}

	/**
	 * 得到一个密钥的密码
	 * 
	 * @param key
	 *            密钥
	 * @param cipherMode
	 *            密码的类型
	 * @return Cipher
	 * @throws util.EncryptException
	 *             当加密出现异常情况时,产生异常信息
	 */
	public static Cipher getCipher(Key key, int cipherMode)
			throws EncryptException {
		try {
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(cipherMode, key);
			return cipher;
		}catch (Exception e) {
			e.printStackTrace();
			throw new EncryptException("Generate Cipher occurs Exception.["
					+ e.getMessage() + "]");
		}
	}

	public static void main(String[] args) throws Exception {
		Key key = DESEncryptUtil.getKey();
		File file = new File("test.html");// 随便找个文件发在当前运行目录下
		FileInputStream in = new FileInputStream(file);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] tmpbuf = new byte[1024];
		int count = 0;
		while ((count = in.read(tmpbuf)) != -1) {
			bout.write(tmpbuf, 0, count);
			tmpbuf = new byte[1024];
		}
		in.close();
		byte[] orgData = bout.toByteArray();
		byte[] raw = DESEncryptUtil.doEncrypt(key, orgData);

		file = new File("encrypt_result.dat");
		OutputStream out = new FileOutputStream(file);
		out.write(raw);
		out.close();

		byte[] data = DESEncryptUtil.doDecrypt(key, raw);
		file = new File("decrypt_result.html");
		out = new FileOutputStream(file);
		out.write(data);
		out.flush();
		out.close();
	}
}
