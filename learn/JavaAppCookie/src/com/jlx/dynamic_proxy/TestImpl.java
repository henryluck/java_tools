/**  @(#)TestImpl.java  Dec 8, 2005
 * 
 * Copyright (c) 2001-2008 MDCL-FRONTLINE, Inc. 
 * All rights reserved. 
 * */
package com.jlx.dynamic_proxy;

/**
 * @author ����ѩ jianglx@sc.mdcl.com.cn
 * @version ѧϰ
 */
public class TestImpl implements ITest {

	/**
	 * 
	 */
	public TestImpl() {
		super();
	}

	public String print() {
		return "Hello, it's from TestImpl class";
	}
}
