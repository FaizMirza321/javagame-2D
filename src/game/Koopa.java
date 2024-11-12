package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 *  koopa class
 */
public class Koopa  extends Walker {
    private static SoundClip stompSound;
    static {
        try {
            stompSound = new SoundClip("data/stompSound.wav");
            System.out.println("Loading stomp sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    private int spin = 0; // used to determine when the shell spins after stomp
    private static final Shape patrollerShape = new PolygonShape(0.378f,0.968f, 0.946f,0.461f, 0.93f,-0.92f, -0.93f,-0.916f, -0.942f,0.372f, -0.326f,0.964f);
    private static final BodyImage image =
            new BodyImage("data/koopaR.png", 3f); // koopa image
    /**
     * creates a koopa
     */
    public Koopa(GameLevel level) {
        super(level, patrollerShape);
        addImage(image);
    }
    @Override
    public void destroy(){
        stompSound.play();
        super.destroy();
    }
    /**
     *  allows shell to spin to hit goombas
     */
    public void shellSpin(){
        spin = 1;
    }
    /**
     * @return returns spin status
     */
    public int getSpin(){
        return spin;
    }
}
