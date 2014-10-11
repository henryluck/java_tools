package org.jlx.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class AOPTest {
	public static void main(String[] args) {
		BusinessInterface businessImp = new BusinessObject();
		InvocationHandler handler = new LogHandler(businessImp);
		BusinessInterface proxy = (BusinessInterface)Proxy.newProxyInstance(businessImp.getClass()
				.getClassLoader(), businessImp.getClass().getInterfaces(),
				handler);
		proxy.processBusiness();
	}
}
