package game;
import city.cs.engine.UserView;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

/**
 * makes the view for the levels
 */
public class GameView extends UserView {
    private Image background; // background image
    private Image health; // health image
    private Image coin; // coin image
    private int hearts = 2;
    private GameLevel level;

    /**
     * creates instances of the view
     */
    public GameView(GameLevel l, int width, int height) {
        super(l, width, height);
        background = new ImageIcon("data/background.png").getImage();
        health = new ImageIcon("data/maxHealth.png").getImage();
        coin = new ImageIcon("data/coin.png").getImage();
        this.level = l;

    }
    /**
     * changes the background when called
     * @param img takes an image when called to replace the current one
     */
    public void changeBackground(Image img){
        background = img;
    }
    /**
     *  displays selected background
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }
    /**
     * replaces health image when hit
     */
    public void loseHealth(){
        health = new ImageIcon("data/halfHealth.png").getImage();
    }
    /**
     * replaces health image when health is gone
     */
    public void noHealth(){
        health = new ImageIcon("data/noHealth.png").getImage();
    }
    /**
     * image of full health displayed
     */
    public void fullHealth(){
        health = new ImageIcon("data/maxHealth.png").getImage();
    }

    public static final Font STATUS_FONT = new Font("Monospaced", Font.PLAIN, 20);
    /**
     * notifies the view of level progression
     */
    public void updateLevel(GameLevel l){
        level = l;
    }
    /**
     *  resets the player when they die
     */
    public void playerFail(){
        Player player = level.getPlayer();
        if (player.getPosition().y<-12){
            player.setPosition(new Vec2( -15,-10));
        }
        if (player.getHealth()<0){
            while (player.getHealth()!=2) { // health is reset
                player.addHealth();
            }
            player.setPosition(new Vec2( -15,-10)); // prevents falling out of the map
        }
    }
    /**
     *  displays game statistics of health and score
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        int healthCount = level.getPlayer().getHealth(); // gets current health
        int scoreCount = level.getPlayer().getScore(); // gets current score
        String scoreString = ""+scoreCount; // score number
        g.setColor(Color.RED);
        g.setFont(STATUS_FONT);
        g.drawString("Health:", 40, 50);
        g.drawImage(coin, 200,25,this);
        g.drawString(scoreString,240,45);
        playerFail();
        if (healthCount==2) { // max health
            fullHealth();
            g.drawImage(health,133,40, this);
        }
        else if (healthCount==1){ // low health
            loseHealth();
            g.drawImage(health,133,40,this);
        }
        else if (healthCount<1){ // no health causes reset
            noHealth();
            g.drawImage(health, 133, 40, this);
            g.drawImage(health, 150, 40, this);
            g.drawString("GAME OVER: 0 lives left", 250,300);
            playerFail(); // player is reset
        }
    }

}
