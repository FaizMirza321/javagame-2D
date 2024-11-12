package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
/**
 * level 1 class
 */
public class Level1  extends GameLevel {
    private Player player;
    private ArrayList<Coin> list = new ArrayList<>();
    private ArrayList<Goomba> list2 = new ArrayList<>();
    private GoombaStomp goombaStomp,goombaStomp1;
    public Level1(Game game) {
        //base class will create the player
        super(game);

        //the player object is now linked
        //to a field rather than a variable
        // make the character
        // update player for the current level
        player = getPlayer();

        /* create level specific platforms, enemies,
           pickups, collision listeners, etc.*/

        //creating the ground
        Shape shape = new BoxShape(7,1f);
        StaticBody ground = new StaticBody(this, shape);
        StaticBody ground2 = new StaticBody(this, shape);
        StaticBody ground3 = new StaticBody(this, shape);
        StaticBody ground4 = new StaticBody(this, shape);
        ground.addImage(new BodyImage("data/ground.png", 2.2f));
        ground2.addImage(new BodyImage("data/ground.png", 2.2f));
        ground3.addImage(new BodyImage("data/ground.png", 2.2f));
        ground4.addImage(new BodyImage("data/ground.png", 2.2f));
        ground.setPosition(new Vec2(-15f, -11.5f));
        ground2.setPosition(new Vec2(-2f,-11.5f));
        ground3.setPosition(new Vec2(11.2f,-11.5f));
        ground4.setPosition(new Vec2(23f,-11.5f));

        // make a suspended platform
        Shape platformShape = new BoxShape(2,0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.addImage(new BodyImage("data/platform.png", 5f));
        platform1.setPosition(new Vec2(-8, -4f));
        Shape platformShape1 = new BoxShape(1.5f,0.5f);
        StaticBody platform2 = new StaticBody(this, platformShape1);
        platform2.addImage(new BodyImage("data/platform.png", 4f));
        platform2.setPosition(new Vec2(-2, -8f));
        StaticBody platform3 = new StaticBody(this, platformShape);
        platform3.addImage(new BodyImage("data/platform.png", 5f));
        platform3.setPosition(new Vec2(8, 4f));

        Shape brickShape = new BoxShape(0.5f,0.5f);
        StaticBody brick = new StaticBody(this, brickShape);
        brick.addImage(new BodyImage("data/Brick1.png",1f));
        brick.setPosition(new Vec2(0,-3));
        StaticBody brick1 = new StaticBody(this, brickShape);
        brick1.addImage(new BodyImage("data/Brick1.png",1f));
        brick1.setPosition(new Vec2(-1,-3));
        StaticBody brick2 = new StaticBody(this, brickShape);
        brick2.addImage(new BodyImage("data/Brick1.png",1f));
        brick2.setPosition(new Vec2(3,0));
        StaticBody brick3 = new StaticBody(this, brickShape);
        brick3.addImage(new BodyImage("data/Brick1.png",1f));
        brick3.setPosition(new Vec2(4,0));

        // player position
        player.setPosition(new Vec2(-15f, -10f));

        // make patrolling enemy
        Goomba goomba = new Goomba(this);
        goomba.setPosition(new Vec2(-8, -10));
        goombaStomp = new GoombaStomp(player, goomba);
        player.addCollisionListener(goombaStomp);

        Goomba goomba1 = new Goomba(this);
        goomba1.setPosition(new Vec2(8,5));
        goombaStomp1 = new GoombaStomp(player, goomba1);
        player.addCollisionListener(goombaStomp1);
        list2.add(goomba);
        list2.add(goomba1);

        // collectable coins
        CoinCollection coinCollection = new CoinCollection(this, game, player);
        player.addCollisionListener(coinCollection);
        for (int a=0; a<5; a++){
            coinGen(a+1,a+2); // makes coins
        }

    }
    /**
     * used to start level2
     * @return true when 5 coins are collected
     */
    @Override
    public boolean isComplete() {
        if (getPlayer().getScore() == 5)
            return true;
        return false;
    }
    /**
     * @return specific patroller
     */
    @Override
    public Goomba getPatroller(int i){
        return list2.get(i);
    }

    @Override
    public void showFlower() {
    }

    @Override
    public void showJetpack() {

    }

    @Override
    public int getLevel() {
        return 1;
    }
    /**
     * @return speciifc koopa
     */
    @Override
    public Koopa getKoopa(int i) {
        return null;
    }

    /**
     *  makes coins
     * @param a x coordinate
     * @param b y coordinate
     */
    public void coinGen(float a, float b){
        Coin coin1 = new Coin(this);
        coin1.setPosition(new Vec2(a +1, b +1));
        list.add(coin1);
    }
}