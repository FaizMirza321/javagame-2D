package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 *  goomba enemy class
 */
public class Goomba extends Walker {
    /**
     *  stomp sound created and used when goomba is stomped
     */
    private static SoundClip stompSound; // sound when stomped
    static {
        try {
            stompSound = new SoundClip("data/stompSound.wav");
            System.out.println("Loading stomp sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    private GameLevel level;
    private static final Shape patrollerShape = new PolygonShape(0.378f,0.968f, 0.946f,0.461f, 0.93f,-0.92f, -0.93f,-0.916f, -0.942f,0.372f, -0.326f,0.964f);
    private static final BodyImage image =
            new BodyImage("data/goombaRight.png", 3f);

    /**
     * creates an goomba object
     */
    public Goomba(GameLevel level) {
        super(level, patrollerShape);
        addImage(image);
        this.level = level;
    }
    /**
     * overrides the initial destroy method to play the stomp sound
     * removes the goomba from the goomba array list
     */
    @Override
    public void destroy(){
        stompSound.play();
        level.reduceList(this);
        super.destroy();
    }
    /**
     *  increases goomba speed for the 2nd controller
     * @param i is used to define the direction using positive or negative
     * and it can adjust the speed
     */
    public void speedInc(float i){
        startWalking(i*5);
    }
    /**
     * makes the goomba jump
     */
    public void jump(){
        jump(10);
    }
}

