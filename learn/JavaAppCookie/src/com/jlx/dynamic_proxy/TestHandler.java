/**  @(#)TestHandler.java  Dec 8, 2005
 * 
 * Copyright (c) 2001-2008 MDCL-FRONTLINE, Inc. 
 * All rights reserved. 
 * */
package com.jlx.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ����print�����������Լ���ʵ�֣�����Ҫʵ��java.lang.reflect.InvocationHandler�ӿ�
 * 
 * @author ����ѩ jianglx@sc.mdcl.com.cn
 * @version ��ϰ
 */
public class TestHandler implements InvocationHandler {

	ITest test;

	/**
	 * 
	 */
	public TestHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * ����̬����󶨵�ָ����TestInterface
	 * 
	 * @param test
	 *            TestInterface
	 * @return TestInterface �󶨴�����TestInterface
	 */
	public ITest bind(ITest test) {
		this.test = test;

		ITest proxyTest = (ITest) Proxy.newProxyInstance(test.getClass()
				.getClassLoader(), test.getClass().getInterfaces(), this);

		return proxyTest;
	}

	/**
	 * ��������������������print����
	 * 
	 * @param proxy
	 *            Object
	 * @param method
	 *            Method
	 * @param args
	 *            Object[]
	 * @return Object
	 * @throws Throwable
	 */

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// ������õ���print���������滻��
		if("print".equals(method.getName())) {
			return "HaHa, It's come from TestHandler";
		}else {
			return method.invoke(this.test, args);
		}

	}

}
