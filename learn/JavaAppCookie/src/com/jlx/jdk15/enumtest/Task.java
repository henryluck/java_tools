package com.jlx.jdk15.enumtest;

/**
 * <p>
 * Title: Task.java
 * </p>
 * <p>
 * Description:测试switch语句
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
public class Task {
	EnumTest test;

	public Task(EnumTest p) {
		test = p;
	}

	public EnumTest getTest() {
		return test;
	}

	/**
	 * 测试switch语句,在Switch语句里使用枚举类型时，一定不能在每一个枚举类型值的前面加上枚举类型的类名,(case
	 * EnumTest.High)否则编译器就会报错
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Task task = new Task(EnumTest.Medium);
		switch (task.getTest()) {
		case High:
			// do case High
			break;
		case Medium: // fall through to Low
		case Low:
			// do case Low
			break;
		default:
			throw new AssertionError("Unexpected enumerated value!");
		}

	}
}
