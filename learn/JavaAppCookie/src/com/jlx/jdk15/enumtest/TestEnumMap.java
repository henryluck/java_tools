package com.jlx.jdk15.enumtest;

import java.io.IOException;
import java.util.EnumMap;

/**
 * Title: TestEnumMap.java <br>
 * Description: 测试TestEnumSet类的用法
 * 
 * @author jianglx
 * @version 1.0<br>
 */
public class TestEnumMap {
	/**
	 * @throws IOException
	 */
	public void test() throws IOException {
		EnumMap<Priority, String> descriptionMessages = new EnumMap<Priority, String>(
				Priority.class);
		descriptionMessages.put(Priority.High, "High means ...");
		descriptionMessages.put(Priority.Medium, " Medium represents...");
		descriptionMessages.put(Priority.Low, " Low means...");
		for (Priority p : Priority.values()) {
			System.out.println("For priority " + p + ", decription is: "
					+ descriptionMessages.get(p));
		}
	}

	public static void main(String args[]) throws IOException {
		new TestEnumMap().test();
	}
}
