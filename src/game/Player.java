package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * player class
 */
public class Player extends Walker {
    /**
     * damage sound effect when you lose health
     */
    private static SoundClip damageSound;
    static {
        try {
            damageSound = new SoundClip("data/damageSound.wav");
            System.out.println("Loading damage sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    private int health;
    private static final Shape playerShape = new PolygonShape(-0.44f,0.952f, -0.588f,0.544f, -0.592f,-0.928f, 0.724f,-0.968f, 0.62f,0.9f);
    private int score = 0; // level score
    private int fire = 0; // fire power-up status
    private int jp = 0; // jetpack power-up status
    private int move; // direction reference
    private String img = "data/marioRight.png";
    private BodyImage stance; // image stance
    private static final BodyImage image =
            new BodyImage("data/marioRight.png", 2f);

    public Player(GameLevel level) {
        super(level, playerShape);
        addImage(image);
        health = 2; // initial health is 2
        score = 0; // score starts at 0
        fire = 0; // fire power-up
        jp = 0; // jetpack power-up
    }
    /**
     *  removes health when called
     * @return gives a reduced health
     */
    public int removeHealth(){
        health-=1;
        damageSound.play();
        return health;
    }
    /**
     * @return current health
     */
    public int getHealth(){
        return health;
    }
    /**
     * @return increased health
     */
    public int addHealth(){
        health+=1;
        return health;
    }
    /**
     * @return current score
     */
    public int getScore(){
        return score;
    }
    /**
     * increase score
     */
    public void increaseScore(){
        score+=1;
    }
    /**
     *  changes player image based on direction
     */
    public void direction(int i){
        move = i;
        if(i==-1){
            img = "data/marioLeft.png";
            stance = new BodyImage(img, 2);

        }
        else if (i==1){
            img = "data/marioRight.png";
            stance = new BodyImage(img, 2);
        }
        addImage(stance);
    }
    /**
     *  enables fire power-up
     */
    public void firePower(){
        fire = 1;
    }
    /**
     * @return fire status
     */
    public int fireStatus(){
        return fire;
    }
    /**
     *  updates stance with fire player image
     */
    public void fireImage(int i){
        if(i==-1){
            img = "data/fireMarioL.png";
            stance = new BodyImage(img,2);


        }
        else if (i==1){
            img = "data/fireMarioR.png";
            stance = new BodyImage(img,2);
        }
        addImage(stance);
    }
    /**
     *  gets image direction of player
     */
    public String getMove(){
        return img;
    }
    /**
     *  enables jetpack
     */
    public void jetpack(){
        jp = 1;
        setLinearVelocity(new Vec2(3*move,8));
    }
    /**
     *  gets jetpack status
     */
    public int jpStatus(){
        return jp;
    }
}
