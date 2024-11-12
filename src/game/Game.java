package game;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import city.cs.engine.*;
import javax.swing.*;

/**
 * Your main game entry point
 */
public class Game {
    private GameLevel level;
    private GameView view;
    private SoundClip gameMusic;
    private PlayerController controller;
    private Image background;
    private String music = "data/gametheme.wav";
    /** Initialise a new Game. */
    public Game() {
        // initialize level to Level1
        level = new Level1(this);

        // first music track called
        changeTrack(music);

        //code that creates bodies was moved
        //in the GameWorld constructor

        //3. make a view to look into the game world
        view = new GameView(level, 800, 500);
        view.setZoom(20);

        //we get access to the player character from the world (world.getPlayer())
        //and pass into the constructor of the PlayerController; now our
        //PlayerController object (named controller) has access to the player!
        controller = new PlayerController(level.getPlayer(), level);
        view.addKeyListener(controller);
        // defines the patrolling area for the enemies
        patrollerStep(0,-9,-8);
        patrollerStep(1,8,9);

        //patrollerStepListener(world);
        //optional: draw a 1-metre grid over the view
        // view.setGridResolution(1);


        //4. create a Java window (frame) and add the game
        //   view to it
        final JFrame frame = new JFrame("City Game");
        frame.add(view);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        //optional: uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(level, 800, 500);

        // start our game world simulation!
        //world.start();
        level.start();
        // ensures inputs are prioritised
        view.requestFocus();

    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
    /**
     *  Used to get the current level as a reference
     *  @return returns the current level number running
     */
    public GameLevel getLevel(){
        return level;
    }
    /**
     *  Starts and loops the track of the current level
     *
     *  @param track is used to replace the current music with
     *  the one requested when called
     */
    public void changeTrack(String track){
        try {
            if(level instanceof Level2 || level instanceof Level3 || level instanceof Level4) {
                gameMusic.stop();
            }
            gameMusic = new SoundClip(track);   // Open an audio input stream
            gameMusic.loop();                                           // Set it to continous playback (looping)
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            //code in here will deal with any errors
            //that might occur while loading/playing sound
            System.out.println(e);
        }
    }
    /**
     *  changes the level when called from other classes
     *  level specific features are set up
     */
    public void goToNextLevel(){
        if (level instanceof Level1){
            level.stop();
            level = new Level2(this); // level 1 replaced with level 2
            //level now refer to the new level
            view.setWorld(level); // sets the view to the current level
            updateController();
            background = new ImageIcon("data/background2.png").getImage();
            view.changeBackground(background); // changes to the next background
            controller.updatePlayer(level.getPlayer());
            music = "data/gametheme2.wav"; // music is switched
            changeTrack(music);
            patrollerStep(0, 0, 1);
            patrollerStep2(2,9,10);
            patrollerStep(3, 0,1);
            level.start();

        }
        else if (level instanceof Level2){
            level.stop();
            level = new Level3(this); // level 2 replaced with level 3
            //level now refer to the new level
            updateController();
            view.setWorld(level);
            background = new ImageIcon("data/background3.png").getImage();
            view.changeBackground(background);
            controller.updatePlayer(level.getPlayer());
            patrollerStep2(0, 0, 1);
            patrollerStep2(1,11,12);
            level.start();

        }
        else if (level instanceof Level3){
            level.stop();
            level = new Level4(this); // level 3 replaced with level 4
            //level now refer to the new level
            updateController();
            view.setWorld(level);
            background = new ImageIcon("data/background4.png").getImage();
            view.changeBackground(background);
            controller.updatePlayer(level.getPlayer());
            music = "data/gametheme.wav"; // music switched
            changeTrack(music);
            level.addStepListener(new KoopaController(level.getKoopa(0), -2, -1));
            patrollerStep(0, 0, 1);
            patrollerStep(1, 5, 6);
            patrollerStep(2, 7, 8);
            level.start();

        }
        else if (level instanceof Level4){
            System.out.println("Well done! Game complete.");
            System.exit(0);
        }

    }
    /**
     *  updates the player controller to the next level
     *  updates the view controller to the next level
     */
    public void updateController(){
        controller.updateLevel(level);
        controller.updatePlayer(level.getPlayer());
        view.updateLevel(level);
    }
    /**
     * Used to make an enemy patrol a boundary
     * @param i selects a specific patroller
     * @param x is the left patrol boundary
     * @param y is the right patrol boundary
     */
    public void patrollerStep(int i, float x, float y){
        level.addStepListener(new GoombaController(level.getPatroller(i), x, y));
    }
    /**
     * Used to make an enemy patrol a boundary with different movement
     * @param i selects a specific patroller
     * @param x is the left patrol boundary
     * @param y is the right patrol boundary
     */
    public void patrollerStep2(int i, float x, float y){
        level.addStepListener(new GoombaController2(level.getPatroller(i), x, y));
    }

}

