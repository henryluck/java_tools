/*
 * TestCalculator.java<br> 2006-4-10<br>
 */
package com.jlx.jdk15.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Title: TestCalculator.java<br>
 * Description:
 * 
 * @author jianglx
 * @version 1.0<br>
 */
public class TestCalculator {
	public static void main(String[] args) {
		MyCalculator cal = new MyCalculator();
		cal.calculateRate();
		try {
			Class c = cal.getClass();
			Method[] methods = c.getDeclaredMethods();

			for (Method m : methods) {
				// 判断这个方法有没有使用TODO
				if(m.isAnnotationPresent(TODO.class))
					System.out.println("Method " + m.getName()
							+ ": the TODO is present");
			}

			Field[] fields = c.getDeclaredFields();
			for (Field f : fields) {
				// 判断这个域有没有使用TOFORMATE
				if(f.isAnnotationPresent(TOFORMATE.class))
					System.out.println("Field " + f.getName()
							+ ": the TOFORMATE is present");
			}
		}catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
