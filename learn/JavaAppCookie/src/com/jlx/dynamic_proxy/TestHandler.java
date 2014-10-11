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
 * 拦截print方法，调用自己的实现，这需要实现java.lang.reflect.InvocationHandler接口
 * 
 * @author 蒋林雪 jianglx@sc.mdcl.com.cn
 * @version 练习
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
	 * 将动态代理绑定到指定的TestInterface
	 * 
	 * @param test
	 *            TestInterface
	 * @return TestInterface 绑定代理后的TestInterface
	 */
	public ITest bind(ITest test) {
		this.test = test;

		ITest proxyTest = (ITest) Proxy.newProxyInstance(test.getClass()
				.getClassLoader(), test.getClass().getInterfaces(), this);

		return proxyTest;
	}

	/**
	 * 方法调用拦截器，拦截print方法
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
		// 如果调用的是print方法，则替换掉
		if("print".equals(method.getName())) {
			return "HaHa, It's come from TestHandler";
		}else {
			return method.invoke(this.test, args);
		}

	}

}
