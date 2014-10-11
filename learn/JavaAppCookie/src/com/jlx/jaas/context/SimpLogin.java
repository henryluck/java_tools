/**  @(#)SimpLogin.java  2005-9-23
 * 
 * Copyright (c) 2001-2008
 * All rights reserved. 
 * */
package com.jlx.jaas.context;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.sun.security.auth.callback.DialogCallbackHandler;

/**
 * JAAS的一个简单例子，使用sun提供的com.sun.security.auth.module.KeyStoreLoginModule，作为验证模块
 * 
 * @author 蒋林雪 jianglx@sc.mdcl.com.cn
 * @version
 */
public class SimpLogin {

	public static void main(String[] args) throws Exception {
		// 登录
		DialogCallbackHandler handler = new DialogCallbackHandler();
		//TextCallbackHandler handler=new TextCallbackHandler( );
		
		LoginContext c = new LoginContext("simp", handler);
		
		boolean pass;
		try {
			c.login();
			// 登录成功
			pass = true;
		}catch (LoginException le) {
			// 登录失败
			pass = false;
			System.err.println("Authentication failed:");
			System.err.println("  " + le.getMessage());
		}
		// 显示登录结果
		if(!pass) {
			System.out.println("Sorry");
		}else {
			System.out.println("Authentication succeeded!");
			Subject s = c.getSubject();
			System.out.println(s.getPrincipals());
		}
	}
}
