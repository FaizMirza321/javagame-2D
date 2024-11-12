package game;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;
/**
 * koopa movement class
 */
public class KoopaController implements StepListener {
    private Koopa koopa;
    private float left;
    private float right;
    private int speed = 1;
    private BodyImage stance;
    private String img;
    /**
     * creates a koopa controller
     */
    KoopaController(Koopa a, float l, float r) {
        koopa = a;
        left = l;
        right = r;
    }
    /**
     * defines patrol movement for koopa
     * changes image based on direction and if stomped
     * speed increases when spinning
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        Vec2 pos = koopa.getPosition();
        if (koopa.getSpin()==1){ // spin enabled
            speed = 10;
            koopa.startWalking(speed);
        }
        else if (koopa.getSpin()==0) { // normal patrol before spin
            if (pos.x <= left) {
                koopa.startWalking(speed);
                img = "data/koopaR.png";
                stance = new BodyImage(img, 2);
            } else if (pos.x >= right) {
                koopa.startWalking(-speed);
                img = "data/koopaL.png";
                stance = new BodyImage(img, 2);
            }
            koopa.removeAllImages();
            koopa.addImage(stance); // updates image
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
