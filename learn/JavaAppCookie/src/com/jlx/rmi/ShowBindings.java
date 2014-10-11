package com.jlx.rmi;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * Show all registered object
 * 
 * @author jianglx
 */
public class ShowBindings {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());

		try {
			String[] bindings = Naming.list("");
			for (int i = 0; i < bindings.length; i++) {
				System.out.println(bindings[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
