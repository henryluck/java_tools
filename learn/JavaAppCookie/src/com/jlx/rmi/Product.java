package com.jlx.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Clint and Server share this interface
 * 
 * @author Administrator
 */
public interface Product extends Remote {
	public String getDescription() throws RemoteException;

}
