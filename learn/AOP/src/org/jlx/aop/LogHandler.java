package org.jlx.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * <strong>Title : LogHandler<br>
 * </strong> <strong>Description : </strong><br>
 * <strong>Create on : 2008-4-16<br>
 * </strong>
 * <p>
 * <strong>Copyright (C) Mocha Software Co.,Ltd.<br>
 * </strong>
 * <p>
 * 
 * @author jianglx jianglx@mochasoft.com.cn<br>
 * @version <strong>test/strong><br>
 *          <br>
 *          <strong>修改历史:</strong><br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class LogHandler implements InvocationHandler {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private Object delegate;

	public LogHandler(Object delegate) {
		this.delegate = delegate;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("**** proxy: ****\n" + proxy.getClass()
	              + "\nmethod: " + method + "\nargs: " + args);
		Object o = null;
		try {
			logger.info("method stats..." + method);
			o = method.invoke(delegate, args);
			logger.info("method ends..." + method);
		} catch (Exception e) {
			logger.info("Exception happends...");
		}
		return o;
	}
}
