package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;
/**
 * patrolling movement class for goomba
 */
public class GoombaController implements StepListener {
    private Goomba goomba;
    private float left; // left boundary
    private float right; // right boundary

    GoombaController(Goomba a, float l, float r){
        goomba = a;
        left = l;
        right = r;
    }
    /**
     * makes the patroller move left and right based on its current position
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        Vec2 pos = goomba.getPosition();
        if (pos.x <= left) {
            goomba.startWalking(1);
        } else if (pos.x >= right) {
            goomba.startWalking(-1);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
