/*
 * OnLineCountListener.java<br> 2006-4-27<br> Copyright (c) 2003-2006
 * MDCL-FRONTLINE, Inc.<br> All rights reserved.
 */
package com.jlx.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <p>
 * Title: OnLineCountListener.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003-2006
 * </p>
 * <p>
 * Company: MDCL-FRONTLINE, Inc.
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

public class OnLineCountListener implements HttpSessionListener,
		ServletContextListener, ServletContextAttributeListener {
	private int count;

	private ServletContext context = null;

	public OnLineCountListener() {
		count = 0;
		// setContext();
	}

	// ����һ��sessionʱ����
	public void sessionCreated(HttpSessionEvent se) {
		count++;
		setContext(se);

	}

	// ��һ��sessionʧЧʱ����
	public void sessionDestroyed(HttpSessionEvent se) {
		count--;
		setContext(se);
	}

	// ����context�����ԣ���������attributeReplaced��attributeAdded����
	public void setContext(HttpSessionEvent se) {
		se.getSession().getServletContext().setAttribute("onLine",
				new Integer(count));
	}

	// ����һ���µ�����ʱ����
	public void attributeAdded(ServletContextAttributeEvent event) {

		log("attributeAdded('" + event.getName() + "', '" + event.getValue()
				+ "')");

	}

	// ɾ��һ���µ�����ʱ����
	public void attributeRemoved(ServletContextAttributeEvent event) {

		log("attributeRemoved('" + event.getName() + "', '" + event.getValue()
				+ "')");

	}

	// ���Ա����ʱ����
	public void attributeReplaced(ServletContextAttributeEvent event) {

		log("attributeReplaced('" + event.getName() + "', '" + event.getValue()
				+ "')");
	}

	// contextɾ��ʱ����
	public void contextDestroyed(ServletContextEvent event) {

		log("contextDestroyed()");
		this.context = null;

	}

	// context��ʼ��ʱ����
	public void contextInitialized(ServletContextEvent event) {

		this.context = event.getServletContext();
		log("contextInitialized()");

	}

	private void log(String message) {

		System.out.println("ContextListener: " + message);
	}
}
