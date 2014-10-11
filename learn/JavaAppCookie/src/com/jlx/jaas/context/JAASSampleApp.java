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
 * 调用验证模块，验证用户身份
 * 
 * @author 蒋林雪 jianglx@sc.mdcl.com.cn
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
		
		/* 实例化LoginContext，用Sample做为名字，在配置文件里要用到它 */
		LoginContext loginContext = new LoginContext("Sample",
				new UsernamePasswordCallbackHandler(username, password));
		loginContext.login();
		
		Subject subject = loginContext.getSubject();
		/* 打印subject */
		System.out.println(subject);
	}

}
