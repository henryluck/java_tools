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
public class ConMediator implements Mediator {
    
    private ConColleague1 con1;
    private ConColleague2 con2;
    
    public void setCon1(ConColleague1 con1) {
        this.con1 = con1;
    }
    
    public void setCon2(ConColleague2 con2) {
        this.con2 = con2;
    }
    
    public void changeCol1() {
        String state = con1.getState();
        con2.setState(state);
        con2.action();
    }
    
    public void changeCol2() {
        String state = con2.getState();
        con1.setState(state);
        con1.action();        
    }
    
}
