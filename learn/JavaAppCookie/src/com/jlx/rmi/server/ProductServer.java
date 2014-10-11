package com.jlx.rmi.server;

import java.rmi.Naming;

/**
 * @author Administrator
 *
 */
public class ProductServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.setSecurityManager(new RMISecurityManager());
		
		try{
			ProductImpl p1 = new ProductImpl("Blackwell Toaster");
			ProductImpl p2 = new ProductImpl("Microwave Oven");
			
			Naming.rebind("toaster",p1);
			Naming.rebind("microwave",p2);
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}

}
