/**  @(#)JAASSampleApp.java  2005-9-21
 * 
 * Copyright (c) 2001-2008 jianglx. 
 * All rights reserved. 
 * */
package com.jlx.jaas.context;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;

import com.jlx.jaas.callbackhandler.UsernamePasswordCallbackHandler;

/**
 * ������֤ģ�飬��֤�û����
 * 
 * @author ����ѩ jianglx@sc.mdcl.com.cn
 * @version
 */
public class JAASSampleApp {

	/**
	 * 
	 */
	public JAASSampleApp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		if(args.length != 2) {
			System.err.println("Useage:java JAASSampleApp username password");
			System.exit(1);
		}
		String username = args[0];
		char[] password = args[1].toCharArray();
		
		/* ʵ����LoginContext����Sample��Ϊ���֣��������ļ���Ҫ�õ��� */
		LoginContext loginContext = new LoginContext("Sample",
				new UsernamePasswordCallbackHandler(username, password));
		loginContext.login();
		
		Subject subject = loginContext.getSubject();
		/* ��ӡsubject */
		System.out.println(subject);
	}

}
