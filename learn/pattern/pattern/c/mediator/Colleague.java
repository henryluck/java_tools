/**
 * @(#)filename.java    2004-11-09
 * Copyright (c) 1994-2004 MDCL-FRONTLINE, Inc.
 * All rights reserved.
 */
package pattern.c.mediator;

/**
 * @author Mocha\liqiang
 * @version
 */
public interface Colleague {
    
    void setState(String state);
    
    String getState();
    
    void change();
    
    void action();
    
}
