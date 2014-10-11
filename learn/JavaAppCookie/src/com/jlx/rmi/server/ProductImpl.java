package com.jlx.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.jlx.rmi.Product;

/**
 * The serever terminal object
 * 
 * @author Administrator
 */
public class ProductImpl extends UnicastRemoteObject implements Product {

	public ProductImpl(String d) throws RemoteException {
		super();
		descr = d;
	}

	public String getDescription() throws RemoteException {

		return "I am a " + descr + ".buy me!";
	}

	private String descr;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3833746568716235058L;
}
