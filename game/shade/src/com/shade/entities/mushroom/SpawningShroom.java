package com.shade.entities.mushroom;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.shade.base.Entity;
import com.shade.base.util.State;
import com.shade.entities.Roles;

/**
 * A mushroom which hasn't sprouted and is determining whether to sprout.
 * 
 * Spawning mushrooms: + Are not rendered on screen + Should play the
 * "spawnling" role + Only surface in the shadows + Only surface on clear ground +
 * Wait to surface a set amount of time
 * 
 * @author Alexander Schearer <aschearer@gmail.com>
 */
public class SpawningShroom implements State {

    private Mushroom shroom;
    private int timer;
    private boolean clear;

    public SpawningShroom(Mushroom mushroom) {
        shroom = mushroom;
    }

    public void enter() {
        timer = 0;
        clear = true;
    }

    public int getRole() {
        return Roles.SPAWNLING.ordinal();
    }

    public boolean isNamed(Object o) {
        return o == Mushroom.States.SPAWNING;
    }

    public void onCollision(Entity obstacle) {
        clear = false;
    }

    public void render(StateBasedGame game, Graphics g) {
        // don't render at this stage
    }

    public void update(StateBasedGame game, int delta) {
        timer += delta;
        // it was clear so spawn
        if (clear&& shroom.inShadows()) {
            shroom.unsize();
            shroom.manager.enter(Mushroom.States.NORMAL);
            Mushroom.spawning.play();
        }
        // it was not clear, you waited, so respawn
        else if (timer>200){
            shroom.kill();
        }
    }

}
