package com.jlx.jdk15.enumtest;

/**
 * <p>
 * Title: Task.java
 * </p>
 * <p>
 * Description:����switch���
 * </p>
 * <p>
 * �޸���ʷ:<br>
 * �޸��� �޸����� �޸�����<br>
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
	 * ����switch���,��Switch�����ʹ��ö������ʱ��һ��������ÿһ��ö������ֵ��ǰ�����ö�����͵�����,(case
	 * EnumTest.High)����������ͻᱨ��
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
