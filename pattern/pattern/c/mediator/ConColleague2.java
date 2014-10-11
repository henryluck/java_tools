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
public class ConColleague2 implements Colleague {
    
    private String state = null;
    private Mediator mediator;
    
    public ConColleague2 (Mediator mediator) {
        this.mediator = mediator;
    }    
    
    public void change() {
        mediator.changeCol2();
    }    
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }
    
    public void action() {
        System.out.println("this 1 and the state is " + state);
    }    
}
