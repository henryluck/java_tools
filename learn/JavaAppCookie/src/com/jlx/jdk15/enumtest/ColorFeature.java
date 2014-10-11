package com.jlx.jdk15.enumtest;

/**
 * <p>
 * Title: ColorFeature.java
 * </p>
 * <p>
 * Description: 我们要小心使用构造函数和函数重载方法
 * </p>
 * <p>
 * 修改历史:<br>
 * 修改人 修改日期 修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 * </p>
 * 
 * @author jianglx
 * @version 1.0<br>
 */
public enum ColorFeature {
	RED(0), BLUE(0), GREEN(300), YELLOW(0), BLACK(0);

	/** The degree for each kind of color */
	private int degree;

	ColorFeature(int degree) {
		this.degree = degree;
	}

	public int getDegree() {
		return degree;
	}

	public String getDescription() {
		switch (this) {
		case RED:
			return "the color is red";
		case BLUE:
			return "the color is blue";
		case GREEN:
			return "the color is green";
		case BLACK:
			return "the color is black";
		case YELLOW:
			return "the color is yellow";
		default:
			return "Unknown Color";
		}
	}
}