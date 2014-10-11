package com.jlx.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import sun.io.ByteToCharConverter;
import sun.io.CharToByteConverter;

public class UnicodeAscii {

	public static String AsciiToChineseString(String s) {
		char[] orig = s.toCharArray();
		byte[] dest = new byte[orig.length];
		for (int i = 0; i < orig.length; i++)
			dest[i] = (byte) (orig[i] & 0xFF);
		try {
			ByteToCharConverter toChar = ByteToCharConverter
					.getConverter("gb2312");
			return new String(toChar.convertAll(dest));
		} catch (Exception e) {
			System.out.println(e);
			return s;
		}
	}

	public static String ChineseStringToAscii(String s) {
		try {
			CharToByteConverter toByte = CharToByteConverter
					.getConverter("gb2312");
			byte[] orig = toByte.convertAll(s.toCharArray());
			char[] dest = new char[orig.length];
			for (int i = 0; i < orig.length; i++)
				dest[i] = (char) (orig[i] & 0xFF);
			return new String(dest);
		} catch (Exception e) {
			System.out.println(e);
			return s;
		}
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */

	public static void main(String[] args) throws UnsupportedEncodingException {
		//String s = "¶£¶£ßËßË¶£¶£ßËßË¶£¶£ßËßË";
		//System.out.println(ChineseStringToAscii(s));
		//System.out.println(AsciiToChineseString("¶£¶£µ±µ±"));
		System.out.println(URLDecoder.decode("%E6%A6%A7%7E%5B1730%5D%20%E9%B8%B5%7E%5B1730%5D%20%E5%B5%8B%7E%5B1688%5D%20%E6%8B%8A%7E%5B1283%5D%20%E6%89%BF%E5%8A%9E%7E%5B1241%5D%20%E9%B9%91%7E%5B1228%5D%20%E9%82%A2%7E%5B1228%5D%20%E3%84%90%7E%5B1206%5D%20%EE%85%84%7E%5B1197%5D%20%EE%88%82%7E%5B1197%5D%20%E5%BA%9E%7E%5B1185%5D%20%E5%96%82%7E%5B1185%5D%20%EE%82%88%7E%5B1185%5D%20%E5%87%BA%E5%B8%AD%7E%5B1173%5D%20%E7%BC%93%E6%80%A5%7E%5B1153%5D%20%E6%94%B6%E6%96%87%7E%5B1153%5D%20%E8%B4%AB%7E%5B1142%5D%20%E5%AF%86%7E%5B1064%5D%20%E6%B2%88%E6%9D%B0%7E%5B1021%5D%20%E5%87%80%7E%5B1001%5D%20%E6%9D%8E%E6%98%8E%E7%9B%8A%7E%5B980%5D%20%E6%89%BE%7E%5B934%5D%202043%E4%B8%87%7E%5B851%5D%204953%E4%BA%BF%7E%5B828%5D%20%E7%8E%8B%E5%BB%BA%7E%5B819%5D%20%E7%BB%84%E9%95%BF%7E%5B816%5D%20%E6%A0%87%E9%A2%98%7E%5B782%5D%20%E5%86%B3%E7%AD%96%7E%5B774%5D%20%E4%B8%BB%E6%8C%81%7E%5B770%5D%209%E6%9C%9F%7E%5B767%5D%20","utf-8"));

	}

}
