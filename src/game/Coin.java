package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 *  Class for creating an instance of a coin
 */
public class Coin extends StaticBody{
    private static SoundClip coinSound; // finds the sound of the coin and assigns it to the variable soundClip
    static {
        try {
            coinSound = new SoundClip("data/coinCollect.wav");
            System.out.println("Loading coin sound");
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e); // throws an error if the sound fails to work
        }
    }
    private static final Shape coinShape = new CircleShape(0.001f);
    private static final BodyImage image = new BodyImage("data/Coin.gif");
    /**
     *  Constructor that makes an instance of a coin
     *  @param level  to add the level reference for the coin
     */
    public Coin(GameLevel level) {
        super(level, coinShape);
        addImage(image); // coin image assigned to coin object

    };
    /**
     *  Overrides the method of destroy to play the coin sound when called
     */
    @Override
    public void destroy(){
        coinSound.play(); // makes the coin sound when collected then it is destroyed
        super.destroy();
    }
}
