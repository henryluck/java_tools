/**  @(#)IObserver.java  Dec 7, 2005
 * 
 * Copyright (c) 2001-2008 MDCL-FRONTLINE, Inc. 
 * All rights reserved. 
 * */
package com.jlx.mvc;

/**
 * 观察者接口
 *
 * @author  蒋林雪 jianglx@sc.mdcl.com.cn
 * @version mvc pattern练习
 */
public interface IObserver {
	public void dataUpdate(Model model);
}
