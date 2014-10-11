/*
 * EncryptException.java<br> 2006-4-10<br>
 */
package com.jlx.encrypt;

/**
 * Title: EncryptException.java<br>
 * Description:
 * </p>
 * 
 * @author jianglx
 * @version 1.0<br>
 */
public class EncryptException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2757439966038642407L;

	/**
	 * 
	 */
	public EncryptException() {
		super();
	}

	/**
	 * @param message
	 */
	public EncryptException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public EncryptException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EncryptException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
