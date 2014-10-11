package com.jlx.dynamic_proxy;

import junit.framework.TestCase;

public class TestDynamicProxy extends TestCase {

	private ITest test = null;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestDynamicProxy.class);
	}

	public TestDynamicProxy(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		TestHandler handler = new TestHandler();
		// 用handler去生成实例
		test = handler.bind(new TestImpl());
	}

	protected void tearDown() throws Exception {
		test = null;
		super.tearDown();
	}
	
	public void testPrint() {
	    System.out.println(test.print());
	  }
}
