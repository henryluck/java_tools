/*
 * EnumTest.java<br> 2006-4-10<br> Copyright (c) jianglx
 */
package com.jlx.jdk15.enumtest;

/**
 * <p>
 * Title: EnumTest.java
 * </p>
 * <p>
 * Description:枚举类型的定义
 * </p>
 * <p>
 * 枚举类型每一个值都是public, static and * final的.也就是说，这些值是唯一的而且一旦定 义了是不能被重写或修改. *
 * 而且尽管在枚举类型每一个值声明时没有出现static关键字， 实际上 值都是静态的, 而且我们不能在值前面加上static, public，final.
 * 修饰符 <br>
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
	 * 测试一下新的枚举类,还用了jdk1.5的新的循环
	 * 
	 * @param args
	 *            传人的参数
	 */
	public static void main(String args[]) {
		for (EnumTest g : EnumTest.values()) {
			System.out.println(g.toString());
		}

	}
}
