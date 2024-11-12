package game;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
/**
 *  class for shell collision
 */
public class KoopaShell implements CollisionListener {
    private Player player;
    private Koopa p;
    private Boolean flat = false;

    /**
     * creates shell collision listener
     */
    KoopaShell(Player a, Koopa b) {
        player = a;
        p = b;
    }
    /**
     *  removes health when colliding with player from the side
     */
    public void removeHealth() {
        player.removeHealth();
    }
    /**
     * shell destroys other enemies during collision
     */
    @Override
    public void collide(CollisionEvent e) {
        float y = player.getPosition().y;
        float x = player.getPosition().x;
        float y2 = p.getPosition().y;
        float x2 = p.getPosition().x;
        Body rep = e.getReportingBody();
        Body other = e.getOtherBody();
        if (rep == player && other == p && y > y2 + 1) {
            p.removeAllImages();
            p.addImage(new BodyImage("data/shell.png", 1f)); // koopa transitions to shell
            flat = true;
            p.shellSpin(); // starts spin
        }
        else if (rep == player && other == p && y < y2 + 0.9f) {
            removeHealth(); // player damage
        }
        else if (rep instanceof Goomba) {
            rep.destroy(); // goomba destroyed
        }
        else if (other instanceof Goomba){
            other.destroy();
        }
        if (flat && x>x2+0.2f) {
            p.destroy();
        }
    }
}
