package game;

import city.cs.engine.*;
/**
 * Responds to collisions relating to fire
 *
 */
public class FireCollision implements CollisionListener {
    private Fire fire;
    /**
     * creates an instance of the fire collision listener
     */
    FireCollision(Fire f){
        fire = f;
    }

    /**
     *
     *  @param e finds the bodies that collided with fire
     *
     */
    @Override
    public void collide(CollisionEvent e) {
        Body fr = e.getReportingBody();
        Body gb = e.getOtherBody();
        if (gb instanceof Goomba && fr instanceof Fire){ // destroys fire and the goomba hit
            fr.destroy();
            gb.destroy();
        }
        else{
            fr.destroy(); // fireball removed if it misses
        }

    }
}
