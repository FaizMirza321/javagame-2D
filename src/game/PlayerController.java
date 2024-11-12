package game;

import org.jbox2d.common.Vec2;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 *  player movement class
 */
public class PlayerController implements KeyListener{
    private Player player;
    private int move = 0;
    private GameLevel world;
    /**
     *  creates instance of player controls
     */
    public PlayerController(Player a, GameLevel w){
        this.player = a;
        world = w;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    /**
     *  movement based on keys pressed and environment
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_SPACE){ // jump
            player.jump(14);
        }
        else if (code == KeyEvent.VK_SHIFT){  // sprint
            player.startWalking(5*move*2);
        }
        else if (code == KeyEvent.VK_LEFT) { // left
            move = -1;
            player.startWalking(-2);
            player.removeAllImages();
            if (player.fireStatus()==1){ // checks fire
                player.fireImage(move);
            }
            else{
                player.direction(move);
            }
        }
        else if (code == KeyEvent.VK_RIGHT) { // right
            move = 1;
            player.startWalking(2);
            player.removeAllImages();
            if (player.fireStatus()==1){
                player.fireImage(move);
            }
            else{
                player.direction(move);
            }
        }
        /**
         * press j to fly when you collect a jetpack
         */
        if ((code == KeyEvent.VK_J || code == KeyEvent.VK_UP) && player.jpStatus()==1) { // jetpack flying
            player.jetpack();
        }
        /**
         *  press f to fly when you collect a fire power-up
         */
        if (code == KeyEvent.VK_F && player.fireStatus()==1) { // shoot fireball
            Fire fi = new Fire(world);
            fi.addCollisionListener(new FireCollision(fi));
            String img = player.getMove();
            if (img.equals("data/marioRight.png") || img.equals("data/fireMarioR.png")){ // checks direction fireball was shot
                fi.setPosition(new Vec2(player.getPosition().x+1.5f ,player.getPosition().y+1f));
                fi.setLinearVelocity(new Vec2(20,0)); // fireball velocity
            }
            else if (img.equals("data/marioLeft.png") || img.equals("data/fireMarioL.png")){
                fi.setPosition(new Vec2(player.getPosition().x -1.5f,player.getPosition().y+1f));
                fi.setLinearVelocity(new Vec2(-20,0));
            }
        }


    }
    /**
     * stops walking when key is released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) { // stop moving left
            move = 0;
            player.stopWalking();
        }
        else if (code == KeyEvent.VK_RIGHT) { // stop moving right
            move = 0;
            player.stopWalking();
        }
    }
    /**
     * updates player
     * @param p new player
     */
    public void updatePlayer(Player p){
        player = p;
    }
    /**
     *  updates level
     * @param l new level
     */
    public void updateLevel(GameLevel l){
        world = l;
    }

}
