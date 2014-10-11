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
public class ConColleague1 implements Colleague {
    
    private String state = null;
    private Mediator mediator;
    
    public void change() {
        mediator.changeCol1();
    }
    
    public ConColleague1 (Mediator mediator) {
        this.mediator = mediator;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }
    
    public void action() {
        System.out.println("this 2 and the state is " + state);
    }
}
