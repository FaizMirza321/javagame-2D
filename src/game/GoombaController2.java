package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;
/**
 * 2nd movement class for goomba
 */
public class GoombaController2 implements StepListener {
    private Goomba goomba;
    private float left;
    private float right;
    /**
     *  makes the goomba controller
     */
    GoombaController2(Goomba a, float l, float r){
        goomba = a;
        left = l;
        right = r;
    }
    /**
     *  different speeds when patrolling left and right
     *  2 jumps from the right, 1 jump from the left
     *  faster movement going left
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        Vec2 pos = goomba.getPosition(); // goomba position
        if (pos.x <= left) {
            goomba.speedInc(1); //increases speed
            goomba.jump(); // goomba jumps
        } else if (pos.x >= right) {
            goomba.speedInc(-3f);
            goomba.jump();
            goomba.jump();
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
