package com.jlx.jndiutils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;

public class JNDIDirectory {
	private DirContext ctx = null;
	
	public JNDIDirectory(String ip,String port,String root,String uid,String pwd){
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
	
	public String getAttributebySeach(String uid){
		//Specify the attributes to match
		//Ask for objects that has a surname ("sn") attribute with 
		//the value "Geisel" and the "mail" attribute
		Attributes matchAttrs = new BasicAttributes(true); // ignore attribute name case
		matchAttrs.put(new BasicAttribute("uid", uid));
		matchAttrs.put(new BasicAttribute("mail"));

		//Specify the ids of the attributes to return
		String[] attrIDs = {"sn","telephonenumber","mail"};
		
		try{
			//Search for objects that have those matching attributes
			NamingEnumeration answer = ctx.search("ou=people,o=SGS", matchAttrs);
	
			while (answer.hasMore()) {
				System.out.println("wwww");
			    SearchResult sr = (SearchResult)answer.next();
			    System.out.println(">>>" + sr.getName());
				Attributes atts = sr.getAttributes();
				NamingEnumeration enum1 = atts.getAll();
		        while (enum1.hasMore()) {
		            Attribute attr = (Attribute)enum1.next();
					//System.out.println("attribute: " + attr.getID());
					/* Print each value */
					NamingEnumeration e = attr.getAll();
					while(e.hasMore()){
						System.out.println(attr.getID()+":"+e.next());
						//sb.append(attr.getID()).append(":").append(e.next()).append(",");
					}
		        }
			}
		}catch(NamingException e){
			e.printStackTrace(System.out);
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		long l = System.currentTimeMillis();
		
		String[] attrIDs = {"sn", "telephonenumber","mail"};
		String dir = "uid=adminsgs,ou=people,o=SGS";
		
		JNDIDirectory jd = new JNDIDirectory("10.32.166.166","389","o=js.cmcc","","");
		System.out.println(jd.getAttribute(dir,attrIDs));
		System.out.println(System.currentTimeMillis()-l);
		
		jd.getAttributebySeach("chengwz");

	}

}
