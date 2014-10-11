package com.jlx.StringUtil;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * <p>
 * Title: StringUtils.java
 * </p>
 * <p>
 * Description:字符串的工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003-2006
 * </p>
 * <p>
 * Company: MDCL-FRONTLINE, Inc.
 * </p>
 * <p>
 * 修改历史:<br>
 * 修改人 修改日期 修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 * </p>
 * 
 * @author 蒋林雪
 * @version 1.0<br>
 */
public class StringUtils {

	public static Iterator tokenise(String s, String s1) {
		ArrayList arraylist = new ArrayList();
		if(strValid(s) && strValid(s1)) {
			int i = s1.length();
			int j = s.indexOf(s1);
			if(j != 0)
				arraylist.add(s.substring(0, j));
			int k;
			for (; j > -1; j = s.indexOf(s1, k + 1)) {
				k = j + i;
				int i1 = s.indexOf(s1, k + 1);
				if(i1 > -1)
					arraylist.add(s.substring(k, i1));
			}

			int l = s.lastIndexOf(s1) + i;
			if(l != -1) {
				String s2 = s.substring(l, s.length());
				if(strValid(s2))
					arraylist.add(s2);
			}
		}
		return arraylist.iterator();
	}

	public static String[] split(String s, String s1) {
		if(strValid(s)) {
			StringTokenizer stringtokenizer = new StringTokenizer(s, s1);
			int i = stringtokenizer.countTokens();
			int j = 0;
			String as[] = new String[i];
			while (stringtokenizer.hasMoreElements()) {
				String s2 = (String) stringtokenizer.nextElement();
				as[j] = s2.trim();
				j++;
			}
			return as;
		}else {
			return new String[0];
		}
	}

	public static String combine(String as[], String s) {
		StringBuffer stringbuffer = new StringBuffer("");
		if(as != null && as.length > 0) {
			for (int i = 0; i < as.length; i++)
				if(as[i] != null) {
					if(i > 0)
						stringbuffer.append(s);
					stringbuffer.append(as[i].trim());
				}

		}
		return stringbuffer.toString();
	}

	public static String combine(ArrayList arraylist, String s) {
		StringBuffer stringbuffer = new StringBuffer("");
		if(arraylist != null && arraylist.size() > 0) {
			for (int i = 0; i < arraylist.size(); i++) {
				String s1 = (String) arraylist.get(i);
				if(s1 != null) {
					if(i > 0)
						stringbuffer.append(s);
					stringbuffer.append(s1.trim());
				}
			}

		}
		return stringbuffer.toString();
	}

	public static ArrayList CSVToArrayList(String s, String s1) {
		ArrayList arraylist = new ArrayList();
		if(strValid(s)) {
			StringTokenizer stringtokenizer = new StringTokenizer(s, s1);
			Object obj = null;
			int i = stringtokenizer.countTokens();
			for (int j = 0; stringtokenizer.hasMoreElements(); j++) {
				String s2 = (String) stringtokenizer.nextElement();
				arraylist.add(s2.trim());
			}

		}
		return arraylist;
	}

	public static boolean isTrue(String s) {
		if(s == null) {
			return false;
		}else {
			s = s.trim().toLowerCase();
			boolean flag = s.equals("yes") || s.equals("on")
					|| s.equals("true") || s.equals("1");
			return flag;
		}
	}

	public static String ensureSeparatorAtEnd(String s) {
		String s1 = "\\";
		String s2 = s;
		if(s != null) {
			try {
				s1 = File.separator;
			}catch (Exception exception) {
			}
			try {
				if(!s.endsWith(s1))
					s2 = (new StringBuffer(s.length() + 1)).append(s)
							.append(s1).toString();
				else
					s2 = s;
			}catch (Exception exception1) {
			}
		}else {
			s2 = "";
		}
		return s2;
	}

	public static String ensureNoSeparatorAtEnd(String s) {
		String s1 = "\\";
		String s2 = s;
		try {
			s1 = File.separator;
		}catch (Exception exception) {
		}
		if(s != null)
			try {
				if(s.endsWith(s1))
					s2 = s.substring(0, s.length() - s1.length());
			}catch (Exception exception1) {
			}
		else
			s2 = "";
		return s2;
	}

	public static String getHTMLColorFromString(String s) {
		if(s == null)
			return "";
		String s1 = null;
		s = s.toUpperCase();
		int i = s.indexOf('#');
		if(i != -1) {
			for (int j = i + 1; j < i + 6; j++)
				try {
					char c = s.charAt(j);
					if(!Character.isDigit(c) && (c < 'A' || c > 'F'))
						return "";
				}catch (StringIndexOutOfBoundsException stringindexoutofboundsexception) {
					return "";
				}

			s1 = s.substring(i, i + 7);
		}
		if(s1 == null)
			return "";
		else
			return s1;
	}

	public static String getImageNameFromString(String s)
			throws StringIndexOutOfBoundsException {
		int i = s.indexOf("=");
		char c;
		do {
			i++;
			c = s.charAt(i);
		}while (!Character.isLetterOrDigit(c) && c != '_');
		StringBuffer stringbuffer = new StringBuffer(64);
		for (int j = i; j < s.length(); j++) {
			char c1 = s.charAt(j);
			if(!Character.isLetterOrDigit(c1) && c1 != '.' && c1 != '_') {
				if(c1 != '/')
					break;
				stringbuffer = new StringBuffer(64);
			}else {
				stringbuffer.append(c1);
			}
		}

		return stringbuffer.toString();
	}

	public static String nullToEmpty(String s) {
		return s != null ? s : "";
	}

	public static boolean strValid(String s, int i) {
		boolean flag = false;
		if(s != null)
			if(i > 0) {
				if(s.length() > 0 && s.length() <= i)
					flag = true;
			}else if(s.length() > 0)
				flag = true;
		return flag;
	}

	public static boolean strValid(String s) {
		return strValid(s, -1);
	}

	public static boolean containsIllegalChars(String s) {
		if(strValid(s, -1)) {
			char ac[] = s.toCharArray();
			for (int i = 0; i < ac.length; i++)
				if(ac[i] == '<' || ac[i] == '>' || ac[i] == '\''
						|| ac[i] == '"')
					return true;

			int j = 0;
			do {
				j = s.indexOf("&");
				if(j != -1) {
					String s1 = s.substring(j);
					if(!s1.startsWith("&amp;") && !s1.startsWith("&apos;")
							&& !s1.startsWith("&quot;")
							&& !s1.startsWith("&lt;") && !s1.startsWith("&gt;"))
						return true;
					try {
						s = s1.substring(1);
					}catch (StringIndexOutOfBoundsException stringindexoutofboundsexception) {
					}
				}
			}while (j != -1);
			return false;
		}else {
			return true;
		}
	}

	public static boolean containsNonAlphaNumericChars(String s) {
		if(strValid(s, -1)) {
			char ac[] = s.toCharArray();
			for (int i = 0; i < ac.length; i++)
				if(!Character.isLetterOrDigit(ac[i]) && ac[i] != '_'
						&& ac[i] != '@' && ac[i] != '.')
					return true;

			int j = 0;
			do {
				j = s.indexOf("&");
				if(j != -1) {
					String s1 = s.substring(j);
					if(!s1.startsWith("&amp;") && !s1.startsWith("&apos;")
							&& !s1.startsWith("&quot;")
							&& !s1.startsWith("&lt;") && !s1.startsWith("&gt;"))
						return true;
					try {
						s = s1.substring(1);
					}catch (StringIndexOutOfBoundsException stringindexoutofboundsexception) {
					}
				}
			}while (j != -1);
			return false;
		}else {
			return true;
		}
	}

	public static String[] quickSort(String as[]) {
		if(as != null)
			return sortBetween(as, null, 0, as.length);
		else
			return new String[0];
	}

	public static String[] quickSortWithMirror(String as[], String as1[]) {
		if(as != null)
			return sortBetween(as, as1, 0, as.length);
		else
			return new String[0];
	}

	protected static String[] sortBetween(String as[], String as1[], int i,
			int j) {
		if(as1 == null || as1 != null && as1.length == as.length) {
			int k = j - i;
			if(k > 1) {
				Object obj = null;
				String s1 = null;
				int l = i;
				int i1 = i + 1;
				int j1 = j;
				int k1 = (int) Math.floor(Math.random() * (double) k
						+ (double) i);
				String s2 = as[k1];
				as[k1] = as[i];
				as[i] = s2;
				if(as1 != null) {
					String s3 = as1[k1];
					as1[k1] = as1[i];
					as1[i] = s3;
				}
				while (i1 != j1) {
					String s = as[i1];
					if(s == null)
						s = "";
					if(s2 == null)
						s2 = "";
					if(as1 != null)
						s1 = nullToEmpty(as1[i1]);
					int l1 = s.toLowerCase().compareTo(s2.toLowerCase());
					if(l1 < 0) {
						as[i1] = as[l];
						as[l] = s;
						if(as1 != null) {
							as1[i1] = as1[l];
							as1[l] = s1;
						}
						l++;
						i1++;
					}else if(l1 == 0) {
						i1++;
					}else {
						j1--;
						as[i1] = as[j1];
						as[j1] = s;
						if(as1 != null) {
							as1[i1] = as1[j1];
							as1[j1] = s1;
						}
					}
				}
				sortBetween(as, as1, i, l);
				sortBetween(as, as1, j1, j);
			}
		}
		return as;
	}

	public static void quickSort(Vector vector) {
		if(vector != null)
			sortBetween(vector, 0, vector.size());
	}

	protected static void sortBetween(Vector vector, int i, int j) {
		int k = j - i;
		if(k > 1) {
			Object obj = null;
			int l = i;
			int i1 = i + 1;
			int j1 = j;
			int k1 = (int) Math.floor(Math.random() * (double) k + (double) i);
			String s1 = (String) vector.elementAt(k1);
			vector.setElementAt(vector.elementAt(i), k1);
			vector.setElementAt(s1, i);
			while (i1 != j1) {
				String s = (String) vector.elementAt(i1);
				if(s == null)
					s = "";
				if(s1 == null)
					s1 = "";
				int l1 = s.toLowerCase().compareTo(s1.toLowerCase());
				if(l1 < 0) {
					vector.setElementAt(vector.elementAt(l), i1);
					vector.setElementAt(s, l);
					l++;
					i1++;
				}else if(l1 == 0) {
					i1++;
				}else {
					j1--;
					vector.setElementAt(vector.elementAt(j1), i1);
					vector.setElementAt(s, j1);
				}
			}
			sortBetween(vector, i, l);
			sortBetween(vector, j1, j);
		}
	}

	public static String[] or(String as[], String as1[]) {
		String as2[] = new String[as.length + as1.length];
		String s = combine(as, "!-<%>-!") + "!-<%>-!" + combine(as1, "!-<%>-!");
		s = s.substring(0, s.length() - 7);
		as2 = split(s, "!-<%>-!");
		quickSort(as2);
		Vector vector = new Vector();
		for (int i = 0; i < as2.length - 1; i++)
			if(!as2[i].equals(as2[i + 1]))
				vector.addElement(as2[i]);

		String as3[] = new String[vector.size()];
		vector.copyInto(as3);
		return as3;
	}

	public static String makeUniqueElement(String as[], String s) {
		String s1 = s;
		int i = isStringInStringArray(as, s, true);
		if(i != -1) {
			char c = s.charAt(s.length() - 1);
			if(Character.isDigit(c)) {
				int j = Character.digit(c, 10);
				s1 = s.substring(0, s.length() - 1) + ++j;
			}else {
				s1 = s + "1";
			}
			s1 = makeUniqueElement(as, s1);
		}
		return s1;
	}

	public static void print(String s) {
		System.out.println(s);
	}

	public static String stringReplace(String s, String s1, String s2) {
		if(s != null && s1 != null && s2 != null) {
			StringBuffer stringbuffer = new StringBuffer(s.length() + 256);
			int i = -1;
			do {
				i++;
				i = s.indexOf(s1);
				if(i > -1) {
					stringbuffer.append(s.substring(0, i));
					stringbuffer.append(s2);
					s = s.substring(i + s1.length());
				}
			}while (i != -1);
			stringbuffer.append(s);
			return stringbuffer.toString();
		}else {
			return s;
		}
	}

	public static String stringReplaceIgnoreCase(String s, String s1, String s2) {
		StringBuffer stringbuffer = new StringBuffer(s.length() + 256);
		if(s != null && s1 != null && s2 != null) {
			int i = -1;
			do {
				i++;
				i = indexOfIgnoreCase(s, s1);
				if(i > -1) {
					stringbuffer.append(s.substring(0, i));
					stringbuffer.append(s2);
					s = s.substring(i + s1.length());
				}
			}while (i != -1);
			stringbuffer.append(s);
			return stringbuffer.toString();
		}else {
			return s;
		}
	}

	public static int indexOfIgnoreCase(String s, String s1) {
		return indexOfIgnoreCase(s, s1, 0);
	}

	public static int indexOfIgnoreCase(String s, String s1, int i) {
		if(s != null && s1 != null && i >= 0) {
			String s2 = s.toLowerCase();
			String s3 = s1.toLowerCase();
			return s2.indexOf(s3, i);
		}else {
			return -1;
		}
	}

	public static String XMLEscape(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		if(s != null) {
			char ac[] = s.toCharArray();
			int i = ac.length;
			for (int j = 0; j < i; j++) {
				char c = ac[j];
				if(c == '&') {
					if(j < i - 1 && ac[j + 1] == '#')
						stringbuffer.append("&");
					else
						stringbuffer.append("&#038;");
				}else if(c == '>')
					stringbuffer.append("&gt;");
				else if(c == '<')
					stringbuffer.append("&lt;");
				else if(c == '"')
					stringbuffer.append("&#034;");
				else if(c == '\'')
					stringbuffer.append("&#039;");
				else
					stringbuffer.append(c);
			}

		}
		return stringbuffer.toString();
	}

	public static String XMLUnescape(String s) {
		StringBuffer stringbuffer = new StringBuffer(s.length());
		if(s != null) {
			int i = 0;
			int j = -1;
			boolean flag = false;
			Object obj = null;
			do {
				i = ++j;
				j = s.indexOf("&", j);
				if(j > -1) {
					stringbuffer.append(s.substring(i, j));
					try {
						int k = s.indexOf(";", j);
						if(s.charAt(++j) == '#') {
							stringbuffer.append(s.substring(j - 1, k + 1));
							j = k;
						}else {
							String s1 = s.substring(j, k);
							j = k;
							if(s1.equals("amp"))
								stringbuffer.append('&');
							else if(s1.equals("apos"))
								stringbuffer.append('\'');
							else if(s1.equals("quot"))
								stringbuffer.append('"');
							else if(s1.equals("gt"))
								stringbuffer.append('>');
							else if(s1.equals("lt"))
								stringbuffer.append('<');
						}
					}catch (StringIndexOutOfBoundsException stringindexoutofboundsexception) {
					}
				}
			}while (j != -1);
			stringbuffer.append(s.substring(i));
		}
		return stringbuffer.toString();
	}

	public static int binarySearch(String as[], char c, boolean flag) {
		int i = 0;
		int j = as.length - 1;
		int k = -1;
		int l = -1;
		int i1 = -2;
		c = Character.toLowerCase(c);
		byte byte0 = 32;
		while (i1 != k) {
			i1 = k;
			k = (i + j) / 2;
			char c1 = Character.toLowerCase(as[k].charAt(0));
			if(c1 == c)
				l = k;
			if(c1 < c)
				i = k + 1;
			else
				j = k;
		}
		if(flag)
			return l;
		else
			return k;
	}

	public static int binarySearch(Vector vector, char c, boolean flag) {
		return binarySearch(vector, (new StringBuffer(2)).append(c).toString(),
				flag);
	}

	public static int binarySearch(Vector vector, String s, boolean flag) {
		if(vector.size() == 0)
			return 0;
		int i = 0;
		int j = vector.size();
		int k = -1;
		int l = -1;
		int i1 = -2;
		if(s != null)
			s = s.toLowerCase();
		while (i1 != k) {
			i1 = k;
			k = (i + j) / 2;
			String s1 = null;
			try {
				s1 = (String) vector.elementAt(k);
			}catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
				break;
			}
			s1 = s1.toLowerCase();
			int j1 = s1.compareTo(s);
			if(j1 == 0)
				l = k;
			else if(j1 < 0)
				i = k + 1;
			else
				j = k;
		}
		if(flag)
			return l;
		else
			return k;
	}

	public static int[] stringArrayToIntArray(String as[]) {
		int ai[] = new int[as.length];
		for (int i = 0; i < as.length; i++) {
			ai[i] = 0;
			try {
				ai[i] = Integer.valueOf(as[i]).intValue();
			}catch (Exception exception) {
			}
		}

		return ai;
	}

	public static long[] stringArrayToLongArray(String as[]) {
		long al[] = new long[as.length];
		for (int i = 0; i < as.length; i++) {
			al[i] = 0L;
			try {
				al[i] = Long.valueOf(as[i]).longValue();
			}catch (Exception exception) {
			}
		}

		return al;
	}

	public static int isStringInStringArray(String as[], String s, boolean flag) {
		if(as == null || !strValid(s))
			return -1;
		if(flag)
			s = s.toLowerCase();
		for (int i = 0; i < as.length; i++) {
			String s1 = as[i];
			if(flag)
				s1 = s1.toLowerCase();
			if(s1.equals(s))
				return i;
		}

		return -1;
	}

	public static Vector stringArrayToVector(Object aobj[]) {
		Vector vector = new Vector();
		if(aobj != null && aobj.length != 0) {
			for (int i = 0; i < aobj.length; i++)
				vector.addElement(aobj[i]);

		}
		return vector;
	}

	public static String[] vectorToStringArray(Vector vector) {
		if(vector == null)
			return new String[0];
		int i = vector.size();
		String as[] = new String[i];
		for (int j = 0; j < i; j++)
			as[j] = (String) vector.elementAt(j);

		return as;
	}

	public static int atoi(String s, int i) {
		int j = i;
		if(s != null)
			try {
				j = (new Double(s)).intValue();
			}catch (NumberFormatException numberformatexception) {
			}
		return j;
	}

	public static long atol(String s, long l) {
		long l1 = l;
		if(s != null)
			try {
				l1 = (new Double(s)).longValue();
			}catch (NumberFormatException numberformatexception) {
			}
		return l1;
	}

	public static float atof(String s, float f) {
		float f1 = f;
		if(s != null)
			try {
				f1 = (new Double(s)).floatValue();
			}catch (NumberFormatException numberformatexception) {
			}
		return f1;
	}

	public static double atod(String s, double d) {
		double d1 = d;
		if(s != null)
			try {
				d1 = (new Double(s)).doubleValue();
			}catch (NumberFormatException numberformatexception) {
			}
		return d1;
	}

	public static boolean atob(String s, boolean flag) {
		boolean flag1 = flag;
		if(s != null) {
			s = s.trim().toLowerCase();
			if(s.equals("yes") || s.equals("on") || s.equals("true")
					|| s.equals("1"))
				flag1 = true;
			if(s.equals("no") || s.equals("off") || s.equals("false")
					|| s.equals("0"))
				flag1 = false;
		}
		return flag1;
	}

	public static String stripDangerousChars(String s) {
		if(s == null)
			return "";
		StringBuffer stringbuffer = new StringBuffer(s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == '<' || c == '>' || c == '"')
				stringbuffer.setCharAt(i, ' ');
		}

		return stringbuffer.toString();
	}

	public static String stripNonAlphaNum(String s) {
		if(s == null)
			return "";
		StringBuffer stringbuffer = new StringBuffer("");
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(Character.isLetterOrDigit(c))
				stringbuffer.append(c);
		}

		return stringbuffer.toString();
	}

	public static String toSentenceCase(String s) {
		if(strValid(s)) {
			String as[] = split(s, " ");
			for (int i = 0; i < as.length; i++)
				if(strValid(as[i])) {
					StringBuffer stringbuffer = new StringBuffer(as[i]);
					stringbuffer.setCharAt(0, Character.toUpperCase(as[i]
							.charAt(0)));
					as[i] = stringbuffer.toString();
				}

			return combine(as, " ");
		}else {
			return "";
		}
	}

	public static String pluralise(String s, String s1, int i) {
		return i != 1 ? s1 : s;
	}

	public static String padWithZeros(String s, int i) {
		if(s == null)
			s = "";
		int j = s.length();
		int k = i - j;
		Object obj = null;
		if(k > 0) {
			StringBuffer stringbuffer = new StringBuffer(i);
			for (int l = 1; l <= k; l++)
				stringbuffer.append('0');

			stringbuffer.append(s);
			return stringbuffer.toString();
		}else {
			return s;
		}
	}

	public static String urlDecode(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case 43: // '+'
				stringbuffer.append(' ');
				break;

			case 37: // '%'
				try {
					stringbuffer.append((char) Integer.parseInt(s.substring(
							i + 1, i + 3), 16));
				}catch (NumberFormatException numberformatexception) {
					return null;
				}
				i += 2;
				break;

			default:
				stringbuffer.append(c);
				break;
			}
		}

		return stringbuffer.toString();
	}

	public static boolean wildcardStringEquals(String s, String s1, boolean flag) {
		int i = 0;
		int j = 0;
		if(s.equals("") ^ s1.equals(""))
			return false;
		while (i < s.length()) {
			if(s.charAt(i) == '*') {
				for (; j <= s1.length(); j++)
					if(wildcardStringEquals(s.substring(i + 1),
							s1.substring(j), flag))
						return true;

				return false;
			}
			if(j >= s1.length())
				return false;
			char c = s.charAt(i);
			char c1 = s1.charAt(j);
			if(!flag) {
				if(c >= 'a' && c <= 'z')
					c -= ' ';
				if(c1 >= 'a' && c1 <= 'z')
					c1 -= ' ';
			}
			if(c != c1 && c != '?')
				return false;
			i++;
			j++;
		}
		return j == s1.length();
	}

	public static String encryptString(String s) {
		if(s == null)
			return "";
		byte abyte0[] = s.getBytes();
		StringBuffer stringbuffer = new StringBuffer(abyte0.length << 1);
		for (int i = 0; i < abyte0.length; i++) {
			abyte0[i] = (byte) ((abyte0[i] ^ 0xffffffc5) + -122 ^ 0xffffffcf);
			stringbuffer.append(NumberUtils.byteToHexString(abyte0[i]));
		}

		return new String(stringbuffer);
	}

	public static String decryptString(String s) {
		if(s == null)
			return "";
		byte abyte0[] = new byte[s.length() >> 1];
		for (int i = 0; i < abyte0.length; i++) {
			abyte0[i] = NumberUtils.parseByteToHex(s.substring(i << 1,
					(i << 1) + 2));
			abyte0[i] = (byte) ((abyte0[i] ^ 0xffffffcf) - -122 ^ 0xffffffc5);
		}

		return new String(abyte0);
	}

	public static String getString(InputStream inputstream) {
		String s = new String("");
		int i = 0;
		char c = '\u7D00';
		int j = 4 * c;
		int k = 0;
		byte abyte0[] = new byte[j];
		if(inputstream != null) {
			while (k > -1) {
				try {
					k = inputstream.read(abyte0, i, c);
				}catch (Exception exception) {
					k = -1;
				}
				if(k != -1) {
					i += k;
					if(i >= j - c) {
						j += c;
						try {
							byte abyte1[] = new byte[j];
							System.arraycopy(abyte0, 0, abyte1, 0, i);
							abyte0 = abyte1;
						}catch (Exception exception1) {
						}
					}
				}
			}
			if(abyte0 != null)
				s = new String(abyte0);
		}
		return s;
	}

	public static String removeFormatting(String s) {
		String s1 = null;
		if(s != null) {
			int i = s.length();
			int j = 0;
			char ac[] = new char[i];
			char ac1[] = new char[i];
			s.getChars(0, i, ac, 0);
			for (int k = 0; k < i; k++) {
				char c = ac[k];
				if(c != '\n' && c != '\t' && c != '\r' && c != '\f')
					ac1[j++] = c;
			}

			for (int l = j; l < i; l++)
				ac1[l] = ' ';

			s1 = new String(ac1);
		}
		return s1;
	}

	public static Enumeration deduplicate(String as[]) {
		if(as != null && as.length > 0) {
			Hashtable hashtable = new Hashtable(as.length);
			for (int i = 0; i < as.length; i++)
				hashtable.put(as[i], new Boolean(true));

			return hashtable.keys();
		}else {
			return null;
		}
	}

	public static String stripHTML(String s) {
		StringBuffer stringbuffer = new StringBuffer(s.length());
		int i = 0;
		int j = 0;
		if(s != null)
			do {
				i = s.indexOf("<", j);
				if(i != -1) {
					stringbuffer.append(s.substring(j, i));
					j = s.indexOf(">", i) + 1;
				}else {
					stringbuffer.append(s.substring(j));
				}
			}while (i != -1 && j != 0);
		return stringbuffer.toString();
	}

	public static String javaScriptEscape(String s) {
		StringBuffer stringbuffer = new StringBuffer("i");
		char ac[] = s.toCharArray();
		for (int i = 0; i < ac.length; i++) {
			char c = ac[i];
			char c1 = c;
			if(c1 >= '0' && c1 < ':')
				stringbuffer.append(c);
			else if(c1 >= 'A' && c1 < '[')
				stringbuffer.append(c);
			else if(c1 >= 'a' && c1 < '{')
				stringbuffer.append(c);
			else
				stringbuffer.append(c1);
		}

		return stringbuffer.toString();
	}

	public static boolean isNotEmpty(String s) {
		boolean flag = false;
		if(s != null && s.length() > 0)
			flag = true;
		return flag;
	}
}