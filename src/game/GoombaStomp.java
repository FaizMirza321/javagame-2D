package game;

import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
/**
 *  goomba stomp class
 */
public class GoombaStomp implements CollisionListener {
    private Player player;
    private Goomba p;
    private Boolean flat = false; // goomba is not flat yet
    /**
     * creates instance of goomba stomp checker
     */
    GoombaStomp(Player a, Goomba b){
        player = a;
        p = b;
    }
    /**
     * health reduction when hit from the side
     */
    public void removeHealth(){
        player.removeHealth(); // removes player health
    }
    /**
     *  checks from stomp
     *  removes health if player collides on the side of the goomba
     *  destroys goomba after stomp
     */
    @Override
    public void collide(CollisionEvent e) {
        float y = player.getPosition().y;
        float x = player.getPosition().x;
        float y2 = p.getPosition().y;
        float x2 = p.getPosition().x;
        if (e.getReportingBody()==player && e.getOtherBody()==p && y>y2+1){
            p.removeAllImages();
            p.addImage(new BodyImage("data/goombaFlat.png", 1f));
            flat = true;
        }
        else if(e.getReportingBody()==player && e.getOtherBody()==p && y<y2+0.9f){
            removeHealth();
        }
        if (flat && ((x>x2+1.5f)||(x<x2-1.5f))){
            p.destroy();
            flat=false;
        }
    }
}
