package com.jlx.StringUtil;

/**
 * Native to Ascii ��ת������ ע��java��ȡascii����ַ������Զ�ת������
 * 
 * @author Administrator
 */
public class Native2Ascii {

	/**
	 * @param s
	 *            ��ת�����ַ���
	 * @return ת�����ascii��
	 */
	public static String encode(String s) {
		String unicode = "";
		String _Hex = "";
		char[] charAry = new char[s.length()];

		for (int i = 0; i < charAry.length; i++) {
			charAry[i] = (char) s.charAt(i);
			_Hex = Integer.toString(charAry[i], 16);

			// _Hex.length()С����λ����Ҫת��
			if (_Hex.length() < 4) {
				_Hex = String.valueOf(s.charAt(i));
				unicode += _Hex;
			} else {
				unicode += "\\u" + _Hex;
			}

		}
		return unicode;
	}

	public static void main(String[] args) {
		// \u5f88\u597d
		// System.out.println(Native2Ascii.encode("����ѩ"));
		// System.out.println("\u848b\u6797\u96ea");
		System.out.println(Integer.toString('&', 16));

	}

	/**
	 * Stringtounicode
	 * 
	 * @param asString
	 * @return
	 */
	public static final String StringToUnicode(String asString) {
		char[] ac = asString.toCharArray();
		int iValue;
		String s = null;
		StringBuffer sb = new StringBuffer();
		for (int ndx = 0; ndx < ac.length; ndx++) {
			iValue = ac[ndx];
			if (iValue < 0x10) {
				s = "\\u000";
			} else if (iValue < 0x100) {
				s = "\\u00";
			} else if (iValue < 0x1000) {
				s = "\\u0";
			} else {
				s = "\\u";
			}
			sb.append(s + Integer.toHexString(iValue));
		}
		return sb.toString();
	}

	/**
	 * unicodetoString
	 * 
	 * @param s
	 * @return
	 */
	public static final String UnicodeToString(String s) {
		if (s == null || "".equalsIgnoreCase(s.trim()))
			return "";
		StringBuffer sb = new StringBuffer();
		boolean escape = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\\':
			case '%':
				escape = true;
				break;
			case 'u':
			case 'U':
				if (escape) {
					try {
						sb.append((char) Integer.parseInt(s.substring(i + 1,
								i + 5), 16));
						escape = false;
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException();
					}
					i += 4;
				} else {
					sb.append(c);
				}
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

}
