/*
 * EnumTest.java<br> 2006-4-10<br> Copyright (c) jianglx
 */
package com.jlx.jdk15.enumtest;

/**
 * <p>
 * Title: EnumTest.java
 * </p>
 * <p>
 * Description:ö�����͵Ķ���
 * </p>
 * <p>
 * ö������ÿһ��ֵ����public, static and * final��.Ҳ����˵����Щֵ��Ψһ�Ķ���һ���� �����ǲ��ܱ���д���޸�. *
 * ���Ҿ�����ö������ÿһ��ֵ����ʱû�г���static�ؼ��֣� ʵ���� ֵ���Ǿ�̬��, �������ǲ�����ֵǰ�����static, public��final.
 * ���η� <br>
 * </p>
 * 
 * @author jianglx
 * @version 1.0<br>
 */
public enum EnumTest {
	High(100), Medium(80), Low(60);
	double temperature;

	EnumTest(double p) {
		temperature = p;
	}

	/**
	 * ����һ���µ�ö����,������jdk1.5���µ�ѭ��
	 * 
	 * @param args
	 *            ���˵Ĳ���
	 */
	public static void main(String args[]) {
		for (EnumTest g : EnumTest.values()) {
			System.out.println(g.toString());
		}

	}
}
