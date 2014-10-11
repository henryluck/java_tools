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
public interface Mediator {
    
    void setCon1(ConColleague1 con1);
    
    void setCon2(ConColleague2 con2);
    
    void changeCol1();
    
    void changeCol2();
    
}
