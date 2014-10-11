package com.jlx.encrypt.md5;

import java.security.MessageDigest;

/**
 * <p>
 * Title: MD5���빤��
 * </p>
 * <p>
 * Description: ���ַ���ת����MD5����
 * </p>
 * <p>
 * Copyright: jianglx Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author jianglx
 * @version 1.0
 */

public class MD5Util {

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * ת���ֽ�����Ϊ16�����ִ�
	 * 
	 * @param b �ֽ�
	 * @return 16�����ִ�
	 */

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * ת���ֽ�Ϊ16�����ִ�
	 * 
	 * @param b �ֽ�
	 * @return 16�����ִ�
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * @param origin ��������ַ���
	 * @return  MD5���ַ���
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}

	public static void main(String[] args) {
		System.err.println(MD5Util.MD5Encode("password"));
		System.out.println("5f4dcc3b5aa765d61d8327deb882cf99");
	}
}