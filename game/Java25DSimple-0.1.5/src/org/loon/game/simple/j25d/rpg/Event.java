package org.loon.game.simple.j25d.rpg;

public abstract class Event {

    protected int x;

    protected int y;

    protected int chipNo;

    protected boolean isHit;
    
    public Event(int x, int y, int chipNo, boolean isHit) {
        this.x = x;
        this.y = y;
        this.chipNo = chipNo;
        this.isHit = isHit;
    }
    
}
