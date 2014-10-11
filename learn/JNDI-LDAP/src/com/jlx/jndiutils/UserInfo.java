package com.jlx.jndiutils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class UserInfo{
	private DirContext ctx = null;
	
	public UserInfo(String ip,String port,String root,String uid,String pwd){
		StringBuffer url = new StringBuffer("ldap://");
		url.append(ip);
		url.append(":");
		url.append(port);
		url.append("/");
		url.append(root);
	    Hashtable env = new Hashtable();
	    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	    env.put(Context.PROVIDER_URL, url.toString());
	    env.put(Context.SECURITY_AUTHENTICATION, "simple");
	    env.put(Context.SECURITY_PRINCIPAL, uid);
	    env.put(Context.SECURITY_CREDENTIALS, pwd);

	    try {
	        ctx = new InitialDirContext(env);
	    } catch (NamingException e) {
			e.printStackTrace(System.out);
	    }
	}

	/**
	 * @param args
	 */
	public String getAttribute(String dir,String[] AttrName){
		StringBuffer sb = new StringBuffer();
		try {
	        // Specify the ids of the attributes to return
			String[] attrIDs = AttrName;
	    
	        // Get the attributes requested
	        Attributes answer = ctx.getAttributes(dir, attrIDs);
	    
	        NamingEnumeration enum1 = answer.getAll();
	        while (enum1.hasMore()) {
	            Attribute attr = (Attribute)enum1.next();
				//System.out.println("attribute: " + attr.getID());
				/* Print each value */
				NamingEnumeration e = attr.getAll();
				while(e.hasMore()){
					sb.append(attr.getID()).append(":").append(e.next()).append(",");
				}
	        }
	    } catch(NameNotFoundException e){
			System.out.println("No Such Object:"+dir);
			return null;
	    }
		catch (NamingException e) {
			e.printStackTrace(System.out);
	    }

		return sb.toString();
	}
	
	public static void main(String[] args) {
		long l = System.currentTimeMillis();
		
		String[] attrIDs = {"sn", "telephonenumber","mail"};
		String dir = "uid=adminsgs,ou=people,o=SGS";
		
		UserInfo jd = new UserInfo("10.32.166.166","389","o=js.cmcc","","");
		System.out.println(jd.getAttribute(dir,attrIDs));
		System.out.println(System.currentTimeMillis()-l);

	}

}
