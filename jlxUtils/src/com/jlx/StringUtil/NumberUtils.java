package com.jlx.StringUtil;

/**
 * <p>
 * Title: NumberUtils.java
 * </p>
 * <p>
 * Description:对数字的一些处理
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
public class NumberUtils {

	public static int[] quickSort(int ai[]) {
		return sortBetween(ai, (int[]) null, 0, ai.length);
	}

	public static long[] quickSort(long al[]) {
		return sortBetween(al, (long[]) null, 0, al.length);
	}

	public static int[] quickSortWithMirror(int ai[], int ai1[]) {
		return sortBetween(ai, ai1, 0, ai.length);
	}

	public static long[] quickSortWithMirror(long al[], String as[]) {
		return sortBetween(al, as, 0, al.length);
	}

	public static long[] quickSortWithMirror(long al[], long al1[]) {
		return sortBetween(al, al1, 0, al.length);
	}

	protected static long[] sortBetween(long al[], String as[], int i, int j) {
		if(as == null || as != null && as.length == al.length) {
			int k = j - i;
			if(k > 1) {
				long l = 0L;
				String s = null;
				int i1 = i;
				int j1 = i + 1;
				int k1 = j;
				int i2 = (int) Math.floor(Math.random() * (double) k
						+ (double) i);
				long l2 = al[i2];
				al[i2] = al[i];
				al[i] = l2;
				if(as != null) {
					String s1 = as[i2];
					as[i2] = as[i];
					as[i] = s1;
				}
				while (j1 != k1) {
					long l1 = al[j1];
					if(as != null)
						s = as[j1];
					if(l1 < l2) {
						al[j1] = al[i1];
						al[i1] = l1;
						if(as != null) {
							as[j1] = as[i1];
							as[i1] = s;
						}
						i1++;
						j1++;
					}else if(l1 == l2) {
						j1++;
					}else {
						k1--;
						al[j1] = al[k1];
						al[k1] = l1;
						if(as != null) {
							as[j1] = as[k1];
							as[k1] = s;
						}
					}
				}
				sortBetween(al, as, i, i1);
				sortBetween(al, as, k1, j);
			}
		}
		return al;
	}

	protected static int[] sortBetween(int ai[], int ai1[], int i, int j) {
		if(ai1 == null || ai1 != null && ai1.length == ai.length) {
			int k = j - i;
			if(k > 1) {
				boolean flag = false;
				int i1 = 0;
				int j1 = i;
				int k1 = i + 1;
				int l1 = j;
				int i2 = (int) Math.floor(Math.random() * (double) k
						+ (double) i);
				int j2 = ai[i2];
				ai[i2] = ai[i];
				ai[i] = j2;
				if(ai1 != null) {
					int k2 = ai1[i2];
					ai1[i2] = ai1[i];
					ai1[i] = k2;
				}
				while (k1 != l1) {
					int l = ai[k1];
					if(ai1 != null)
						i1 = ai1[k1];
					if(l < j2) {
						ai[k1] = ai[j1];
						ai[j1] = l;
						if(ai1 != null) {
							ai1[k1] = ai1[j1];
							ai1[j1] = i1;
						}
						j1++;
						k1++;
					}else if(l == j2) {
						k1++;
					}else {
						l1--;
						ai[k1] = ai[l1];
						ai[l1] = l;
						if(ai1 != null) {
							ai1[k1] = ai1[l1];
							ai1[l1] = i1;
						}
					}
				}
				sortBetween(ai, ai1, i, j1);
				sortBetween(ai, ai1, l1, j);
			}
		}
		return ai;
	}

	protected static long[] sortBetween(long al[], long al1[], int i, int j) {
		if(al1 == null || al1 != null && al1.length == al.length) {
			int k = j - i;
			if(k > 1) {
				long l = 0L;
				long l2 = 0L;
				int i1 = i;
				int j1 = i + 1;
				int k1 = j;
				int i2 = (int) Math.floor(Math.random() * (double) k
						+ (double) i);
				long l3 = al[i2];
				al[i2] = al[i];
				al[i] = l3;
				if(al1 != null) {
					long l4 = al1[i2];
					al1[i2] = al1[i];
					al1[i] = l4;
				}
				while (j1 != k1) {
					long l1 = al[j1];
					if(al1 != null)
						l2 = al1[j1];
					if(l1 < l3) {
						al[j1] = al[i1];
						al[i1] = l1;
						if(al1 != null) {
							al1[j1] = al1[i1];
							al1[i1] = l2;
						}
						i1++;
						j1++;
					}else if(l1 == l3) {
						j1++;
					}else {
						k1--;
						al[j1] = al[k1];
						al[k1] = l1;
						if(al1 != null) {
							al1[j1] = al1[k1];
							al1[k1] = l2;
						}
					}
				}
				sortBetween(al, al1, i, i1);
				sortBetween(al, al1, k1, j);
			}
		}
		return al;
	}

	public static String byteToHexString(byte byte0) {
		int i = byte0 & 0xf;
		int j = byte0 >>> 4 & 0xf;
		StringBuffer stringbuffer = new StringBuffer(2);
		stringbuffer.append(acHexDigits[j]).append(acHexDigits[i]);
		return stringbuffer.toString();
	}

	public static byte parseByteToHex(String s) throws NumberFormatException {
		byte byte0 = 0;
		s = s.toLowerCase();
		if(s.length() != 2)
			throw new NumberFormatException();
		char c = s.charAt(0);
		char c1 = s.charAt(1);
		if(c >= 'a' && c <= 'f')
			byte0 = (byte) ((c - 97) + 10 << 4);
		else
			byte0 = (byte) (c - 48 << 4);
		if(c1 >= 'a' && c1 <= 'f')
			byte0 += (byte) ((c1 - 97) + 10);
		else
			byte0 += (byte) (c1 - 48);
		return byte0;
	}

	public static final char acHexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

}