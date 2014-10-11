/*
 * ewrqwer.java<br> 2006-4-10<br> Copyright (c) 2003-2006 MDCL-FRONTLINE, Inc.<br>
 * All rights reserved.
 */
package com.jlx.encrypt.des;

/**
 * Title: DESEncryptUtil.java<br>
 * Description: �����Ǽ��ܴ�����.��Ҫ���������ݽ��м���,���ܲ���
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
	 * ���DES���ܵ���Կ���ڽ��״���Ĺ�����Ӧ�ö�ʱ�� ����Կ����ҪJCE��֧�֣����jdk�汾����1.4������Ҫ
	 * ��װjce-1_2_2��������ʹ�á�
	 * 
	 * @return Key ���ضԳ���Կ
	 * @throws java.security.NoSuchAlgorithmException
	 * @see util.EncryptUtil ���а������ܺͽ��ܵķ���
	 */
	public static Key getKey() throws NoSuchAlgorithmException {
		Security.insertProviderAt(new com.sun.crypto.provider.SunJCE(), 1);
		KeyGenerator generator = KeyGenerator.getInstance("DES");
		generator.init(new SecureRandom());
		Key key = generator.generateKey();
		return key;
	}

	/**
	 * ��ָ�������ݸ����ṩ����Կ���м���
	 * 
	 * @param key
	 *            ��Կ
	 * @param data
	 *            ��Ҫ���ܵ�����
	 * @return byte[] ���ܺ������
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
	 * ���������Ѽ��ܵ�����ͨ��ָ������Կ���н���
	 * 
	 * @param key
	 *            ��Կ
	 * @param raw
	 *            �����ܵ�����
	 * @return byte[] ���ܺ������
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
	 * �õ�һ����Կ������
	 * 
	 * @param key
	 *            ��Կ
	 * @param cipherMode
	 *            ���������
	 * @return Cipher
	 * @throws util.EncryptException
	 *             �����ܳ����쳣���ʱ,�����쳣��Ϣ
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
		File file = new File("test.html");// ����Ҹ��ļ����ڵ�ǰ����Ŀ¼��
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
