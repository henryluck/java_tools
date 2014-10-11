/**  @(#)UsernamePasswordCallbackHandler.java  2005-9-20
 * 
 * All rights reserved. 
 * */
package com.jlx.jaas.callbackhandler;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * Callback处理类，负责callback的set操作,处理用户名和密码
 *
 * @author  蒋林雪 jianglx@sc.mdcl.com.cn
 * @version 
 */
public class UsernamePasswordCallbackHandler implements CallbackHandler {
	
	private String mUsername;
	private char[] mPassword;

	/**
	 * 
	 */
	public UsernamePasswordCallbackHandler() {
		super();
	}
	
	public UsernamePasswordCallbackHandler(String username,char[] password) {
		mUsername = username;
		mPassword = new char[password.length];
		System.arraycopy(password,0,mPassword,0,password.length);
	}
	
	/* (non-Javadoc)
	 * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
	 */
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		
		/* 遍历callbck处理名和口令 */
		for (int i = 0; i < callbacks.length; i++) {
			Callback callback = callbacks[i];
			
			/* 通过callback的类型，做相应的处理 */
			if(callback instanceof NameCallback) {
				NameCallback nameCallback = (NameCallback)callback;
				nameCallback.setName(mUsername);							
			}else if(callback instanceof PasswordCallback){
					PasswordCallback passwordCallback = (PasswordCallback)callback;
					passwordCallback.setPassword(mPassword);
			}else{
				throw new UnsupportedCallbackException(callback,"unsupported type");
			}
		}

	}

}
