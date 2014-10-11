package com.jlx.rmi.client;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import com.jlx.rmi.Product;

public class ProductClient {
public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		String url = "";
			// chang to "rmi://www.jlx.com/"
			// when server runs on remote machine www.jlx.com
		try{
			Product p1 = (Product)Naming.lookup(url+"toaster");
			Product p2 = (Product)Naming.lookup(url + "microwave");
			System.out.println(p1.getDescription());
			System.out.println(p2.getDescription());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
