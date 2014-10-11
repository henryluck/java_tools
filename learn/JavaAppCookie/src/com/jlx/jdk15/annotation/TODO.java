/*
 * TODO.java<br> 2006-4-10<br> Copyright (c) 2003-2006 MDCL-FRONTLINE, Inc.<br>
 * All rights reserved.
 */
package com.jlx.jdk15.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: TODO.java<br>
 * Description:
 * 
 * @author jianglx
 * @version 1.0<br>
 */
@Target( { ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TODO {
	int priority() default 0;
}
