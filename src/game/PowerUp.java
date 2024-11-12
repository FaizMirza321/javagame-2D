package game;

import city.cs.engine.Body;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
/**
 *  removes power-ups and enables them
 */
public class PowerUp implements CollisionListener {
    private Player player;
    public PowerUp(Player a){
        player = a;
    }

    @Override
    public void collide(CollisionEvent e) {
        Body other = e.getReportingBody();
        if (other instanceof FireFlower && e.getOtherBody() instanceof Player){
            player.firePower();
            player.fireImage(1);
            other.destroy();
        }
        if (other instanceof Jetpack && e.getOtherBody() instanceof Player){
            player.jetpack();
            other.destroy();
        }
    }
}
