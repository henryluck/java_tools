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
 * jaas��֤����ĺ���ģ��
 * 
 * @author ����ѩ jianglx@sc.mdcl.com.cn
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
	 * Ĭ�ϵĹ��캯��
	 */
	public PasswordLoginModule() {
		super();
	}

	/*
	 * ��ʼ�����ģ��
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
	 * ��½���� �������У� username:"testuser" password:"123456"
	 */
	public boolean login() throws LoginException {

		if(mCallbackHandler == null) {
			throw new LoginException("No CallbackHandler defined");
		}

		/* ��������callback��һ������username��һ������password */
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("Username");
		callbacks[1] = new PasswordCallback("Password", false);

		try {
			/* ����callbackhandler������� */
			mCallbackHandler.handle(callbacks);
			mUsername = ((NameCallback) callbacks[0]).getName();
			mPassword = ((PasswordCallback) callbacks[1]).getPassword();

			/* ���callback������ */
			((PasswordCallback) callbacks[1]).clearPassword();

		}catch (IOException e) {
			throw new LoginException(e.toString());
		}catch (UnsupportedCallbackException e) {
			throw new LoginException(e.toString());
		}

		/* ��������û������������Ч�ԣ��˴������滻����������֤��ʽ��db��ldap�� */
		if("testuser".equals(mUsername)
				&& "123456".equals(new String(mPassword))) {

			/* ��֤ͨ�� */
			mLoginSucceeded = true;
			return true;
		}else {

			/* ��֤ʧ�� */
			mLoginSucceeded = false;
			mUsername = null;
			clearPassword();
			throw new FailedLoginException("Incorrect login info��");
		}
	}

	/*
	 * ��֤ʧ��ʱִ�д˷���
	 */
	public boolean abort() throws LoginException {
		/* �����֤ʧ�ܣ�return false */
		if(mLoginSucceeded == false) {
			return false;
		}else if(mLoginSucceeded == true && mCommitSucceeded == false) {
			/* ��ģ����֤�ɹ��ˣ�����������ʧ���� */
			mLoginSucceeded = false;
			mUsername = null;
			clearPassword();
			mPrincipal = null;
		}else {
			/* ��ģ���ύ�ˣ�������ʧ���� */
			logout();
		}
		return true;
	}

	/*
	 * ��������֤���ɹ���ʱ��ִ�д˷����� �����½�ɹ�������subject������һ��principal
	 */
	public boolean commit() throws LoginException {

		if(mLoginSucceeded == false) {
			return false;
		}

		/* ��½�ɹ�������һ��principal�����Ұ����ӵ�subject�� */
		mPrincipal = new PrincipalImpl(mUsername);
		if(!(mSubject.getPrincipals().contains(mPrincipal))) {
			mSubject.getPrincipals().add(mPrincipal);
		}

		/*
		 * �����subject������ ֤�飬�����ڴ˴����ӣ���
		 */
		/* ����û��������� */
		mUsername = null;
		clearPassword();
		mCommitSucceeded = true;

		return true;
	}

	/*
	 * ע���û�����subject��ɾ��principal
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
