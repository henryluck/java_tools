/**  @(#)PrincipalImpl.java  2005-9-20
 * 
 * Copyright (c) 2001-2008 jianglx 
 * All rights reserved. 
 * */
package com.jlx.jaas.principal;

import java.io.Serializable;
import java.security.Principal;

/**
 * principal接口的一个实现类
 * 
 * @author 蒋林雪 jianglx@sc.mdcl.com.cn
 * @version
 */
public class PrincipalImpl implements Principal, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3689346628636915508L;

	private String mName;

	/**
	 * 
	 */
	public PrincipalImpl(String name) {
		mName = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.Principal#getName()
	 */
	public String getName() {
		return mName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof PrincipalImpl)){
			return false;
		}
		PrincipalImpl other = (PrincipalImpl)obj;
		if(mName.equals(other.getName())){
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return mName.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getName();
	}

}
