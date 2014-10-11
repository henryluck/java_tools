package org.loon.game.simple.j25d.rpg;

public class MoveEvent extends Event {

    public int destMapNo;

    public int destX;

    public int destY;
    
    public MoveEvent(int x, int y, int chipNo, int destMapNo, int destX, int destY) {
        super(x, y, chipNo, false);
        this.destMapNo = destMapNo;
        this.destX = destX;
        this.destY = destY;
    }
    
}
