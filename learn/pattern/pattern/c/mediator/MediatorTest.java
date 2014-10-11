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
public class MediatorTest {
    
    public static void main(String[] args) {
        Mediator mediator = new ConMediator();
        
        ConColleague1 col1 = new ConColleague1(mediator);
        col1.setState("lq test in the MediatorTest main 18");
        ConColleague2 col2 = new ConColleague2(mediator);
        
        mediator.setCon1(col1);
        mediator.setCon2(col2);
        
        col1.change();
    }
    
    

    
    
}
