/*
 * MyCalculator.java<br> 2006-4-10<br>
 */
package com.jlx.jdk15.annotation;

import java.util.Date;

/**
 * Title: MyCalculator.java<br>
 * Description:
 * 
 * @author jianglx
 * @version 1.0<br>
 */
public class MyCalculator {
	boolean isReady;

	@TOFORMATE
	double concurrency;

	@TOFORMATE
	Date debitDate;

	public MyCalculator() {
		super();
	}

	@TODO
	public void calculateRate() {
		System.out.println("Calculating...");
	}
}
