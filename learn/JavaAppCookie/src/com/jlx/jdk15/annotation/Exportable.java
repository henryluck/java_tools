package com.jlx.jdk15.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: Exportable.java<br>
 * Description:
 * 
 * @author jianglx
 * @version 1.0<br>
 */
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Exportable {
}