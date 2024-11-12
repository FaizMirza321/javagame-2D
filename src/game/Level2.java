package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
/**
 * level 2 class
 */
public class Level2 extends GameLevel{
    private Pipe pipe;
    private MysteryBlock mysteryBlock;
    private Player player;
    private FireFlower flower;
    private ArrayList<Goomba> list2 = new ArrayList<>();

    public Level2(Game game) {
        super(game);
        player = getPlayer();
        player.setPosition(new Vec2(-15f, -9f));
        // making the ground
        Shape shape = new PolygonShape(-5.76f,1.0f, 5.714f,1.0f, 5.691f,-0.889f, -5.737f,-0.889f);
        StaticBody ground = new StaticBody(this, shape);
        ground.addImage(new BodyImage("data/floor.png", 2.2f));
        ground.setPosition(new Vec2(-15f, -11.5f));
        StaticBody ground1 = new StaticBody(this, shape);
        ground1.addImage(new BodyImage("data/floor.png", 2.2f));
        ground1.setPosition(new Vec2(0, -11.5f));
        StaticBody ground2 = new StaticBody(this, shape);
        ground2.addImage(new BodyImage("data/floor.png", 2.2f));
        ground2.setPosition(new Vec2(14, -11.5f));
        // making brick platforms
        Shape brickShape = new BoxShape(0.5f,0.5f);
        StaticBody brick = new StaticBody(this, brickShape);
        brick.addImage(new BodyImage("data/Brick1.png",1f));
        brick.setPosition(new Vec2(0,-3));
        StaticBody brick1 = new StaticBody(this, brickShape);
        brick1.addImage(new BodyImage("data/Brick1.png",1f));
        brick1.setPosition(new Vec2(1,-3));
        StaticBody brick2 = new StaticBody(this, brickShape);
        brick2.addImage(new BodyImage("data/Brick1.png",1f));
        brick2.setPosition(new Vec2(-1,-3));
        StaticBody brick3 = new StaticBody(this, brickShape);
        brick3.addImage(new BodyImage("data/Brick1.png",1f));
        brick3.setPosition(new Vec2(10,6));
        StaticBody brick4 = new StaticBody(this, brickShape);
        brick4.addImage(new BodyImage("data/Brick1.png",1f));
        brick4.setPosition(new Vec2(11,6));
        StaticBody brick5 = new StaticBody(this, brickShape);
        brick5.addImage(new BodyImage("data/Brick1.png",1f));
        brick5.setPosition(new Vec2(12,6));
        // making grass platforms
        Shape platformShape = new BoxShape(2.1f,0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.addImage(new BodyImage("data/platform.png", 5f));
        platform1.setPosition(new Vec2(8, -1f));

        // creating the pipe which you enter after defeating all the enemies
        pipe = new Pipe(this);
        pipe.setPosition(new Vec2(11,8));
        // creating a mystery block which has a fire-flower power-up inside
        mysteryBlock = new MysteryBlock(this);
        mysteryBlock.setPosition(new Vec2(0,1));
        player.addCollisionListener(new MysteryCollision(this, player, mysteryBlock));
        flower = new FireFlower(this); // flower power-up
        flower.setPosition(new Vec2(0, 1));
        flower.addCollisionListener(new PowerUp(player));

        // creating the Goomba enemies in the level
        Goomba goomba = new Goomba(this);
        goomba.setPosition(new Vec2(0, -11));
        GoombaStomp goombaStomp = new GoombaStomp(player, goomba); //change this to for each instance of patroller
        player.addCollisionListener(goombaStomp);

        Goomba goomba1 = new Goomba(this);
        goomba1.setPosition(new Vec2(11,10));
        GoombaStomp goombaStomp1 = new GoombaStomp(player, goomba1);
        player.addCollisionListener(goombaStomp1);

        Goomba goomba2 = new Goomba(this);
        goomba2.setPosition(new Vec2(9,-1));
        GoombaStomp goombaStomp2 = new GoombaStomp(player, goomba2);
        player.addCollisionListener(goombaStomp2);

        Goomba goomba3 = new Goomba(this);
        goomba3.setPosition(new Vec2(0,-1));
        GoombaStomp goombaStomp3 = new GoombaStomp(player, goomba3);
        player.addCollisionListener(goombaStomp3);
        // adding the goombas to the array list
        list2.add(goomba);
        list2.add(goomba1);
        list2.add(goomba2);
        list2.add(goomba3);
    }

    /**
     * level is complete when the goomba array list is empty
     * @return returns true if no goombas are left or false if they exist
     */
    @Override
    public boolean isComplete() {
        return list2.isEmpty(); // checks if there are any goombas left
    }

    /**
     *
     * @param i specific patroller in the list
     * @return the specific patroller in the list
     */
    @Override
    public Goomba getPatroller(int i) {
        return list2.get(i); // fetch a specific goomba in the list
    }

    /**
     * @return the flower to the collision listener to access
     */
    public FireFlower getFlower(){
        return flower; // access the flower to detect player collision
    }

    /**
     * sets the position of the flower above the mystery block
     */
    public void showFlower(){
        float x = getFlower().getPosition().x;
        float y = getFlower().getPosition().y;
        if (mysteryBlock.getHit()==1){ // checks if the player hit the bottom of the block
            flower.setPosition(new Vec2(x,y+1)); // makes the flower visible to collect

        }
    }

    @Override
    public void showJetpack() {

    }

    /**
     * @return current level number
     */
    @Override
    public int getLevel() {
        return 2; // returns the current level for the game to keep track
    }
    /**
     * removes a destroyed goomba
     */
    public void reduceList(Goomba goomba){
        list2.remove(goomba); // removes an enemy from the goomba list when they are destroyed
    }

    @Override
    public Koopa getKoopa(int i) {
        return null;
    }
}
