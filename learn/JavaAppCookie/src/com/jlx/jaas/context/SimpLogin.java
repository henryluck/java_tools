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
 * JAAS��һ�������ӣ�ʹ��sun�ṩ��com.sun.security.auth.module.KeyStoreLoginModule����Ϊ��֤ģ��
 * 
 * @author ����ѩ jianglx@sc.mdcl.com.cn
 * @version
 */
public class SimpLogin {

	public static void main(String[] args) throws Exception {
		// ��¼
		DialogCallbackHandler handler = new DialogCallbackHandler();
		//TextCallbackHandler handler=new TextCallbackHandler( );
		
		LoginContext c = new LoginContext("simp", handler);
		
		boolean pass;
		try {
			c.login();
			// ��¼�ɹ�
			pass = true;
		}catch (LoginException le) {
			// ��¼ʧ��
			pass = false;
			System.err.println("Authentication failed:");
			System.err.println("  " + le.getMessage());
		}
		// ��ʾ��¼���
		if(!pass) {
			System.out.println("Sorry");
		}else {
			System.out.println("Authentication succeeded!");
			Subject s = c.getSubject();
			System.out.println(s.getPrincipals());
		}
	}
}
