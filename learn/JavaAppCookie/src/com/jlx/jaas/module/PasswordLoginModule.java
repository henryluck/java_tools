/**  @(#)PasswordLoginModule.java  2005-9-20
 * 
 * Copyright (c) 2001-2008 jianglx.
 * All rights reserved. 
 * */
package com.jlx.jaas.module;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.jlx.jaas.principal.PrincipalImpl;

/**
 * jaas验证程序的核心模块
 * 
 * @author 蒋林雪 jianglx@sc.mdcl.com.cn
 * @version
 */
public class PasswordLoginModule implements LoginModule {

	private Subject mSubject;

	private CallbackHandler mCallbackHandler;

	private boolean mLoginSucceeded = false;

	private boolean mCommitSucceeded = false;

	private String mUsername;

	private char[] mPassword;

	private Principal mPrincipal;

	/**
	 * 默认的构造函数
	 */
	public PasswordLoginModule() {
		super();
	}

	/*
	 * 初始化这个模块
	 */
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {

		mSubject = subject;
		mCallbackHandler = callbackHandler;
		mLoginSucceeded = false;
		mCommitSucceeded = false;
		mUsername = null;
		clearPassword();
	}

	private void clearPassword() {

		if(mPassword == null) {
			return;
		}
		for (int i = 0; i < mPassword.length; i++) {
			mPassword[i] = ' ';
		}
		mPassword = null;
	}

	/*
	 * 登陆动作 此例子中： username:"testuser" password:"123456"
	 */
	public boolean login() throws LoginException {

		if(mCallbackHandler == null) {
			throw new LoginException("No CallbackHandler defined");
		}

		/* 建立两个callback，一个处理username，一个处理password */
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("Username");
		callbacks[1] = new PasswordCallback("Password", false);

		try {
			/* 调用callbackhandler填充数据 */
			mCallbackHandler.handle(callbacks);
			mUsername = ((NameCallback) callbacks[0]).getName();
			mPassword = ((PasswordCallback) callbacks[1]).getPassword();

			/* 清除callback中密码 */
			((PasswordCallback) callbacks[1]).clearPassword();

		}catch (IOException e) {
			throw new LoginException(e.toString());
		}catch (UnsupportedCallbackException e) {
			throw new LoginException(e.toString());
		}

		/* 下面检验用户名和密码的有效性，此处可以替换成其他的验证方式，db、ldap等 */
		if("testuser".equals(mUsername)
				&& "123456".equals(new String(mPassword))) {

			/* 验证通过 */
			mLoginSucceeded = true;
			return true;
		}else {

			/* 验证失败 */
			mLoginSucceeded = false;
			mUsername = null;
			clearPassword();
			throw new FailedLoginException("Incorrect login info！");
		}
	}

	/*
	 * 验证失败时执行此方法
	 */
	public boolean abort() throws LoginException {
		/* 如果验证失败，return false */
		if(mLoginSucceeded == false) {
			return false;
		}else if(mLoginSucceeded == true && mCommitSucceeded == false) {
			/* 此模块验证成功了，但是其他的失败了 */
			mLoginSucceeded = false;
			mUsername = null;
			clearPassword();
			mPrincipal = null;
		}else {
			/* 此模块提交了，其他的失败了 */
			logout();
		}
		return true;
	}

	/*
	 * 当所有验证都成功的时候，执行此方法。 如果登陆成功，就向subject中增加一个principal
	 */
	public boolean commit() throws LoginException {

		if(mLoginSucceeded == false) {
			return false;
		}

		/* 登陆成功，创建一个principal，并且把他加到subject中 */
		mPrincipal = new PrincipalImpl(mUsername);
		if(!(mSubject.getPrincipals().contains(mPrincipal))) {
			mSubject.getPrincipals().add(mPrincipal);
		}

		/*
		 * 如果在subject里增加 证书，可以在此处增加，略
		 */
		/* 清除用户名和密码 */
		mUsername = null;
		clearPassword();
		mCommitSucceeded = true;

		return true;
	}

	/*
	 * 注销用户，从subject中删除principal
	 */
	public boolean logout() throws LoginException {
		mSubject.getPrincipals().remove(mPrincipal);
		mLoginSucceeded = false;
		mCommitSucceeded = false;
		mUsername = null;
		clearPassword();
		mPrincipal = null;
		return true;
	}

}
