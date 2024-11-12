package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
/**
 *  Level 3 class
 */
public class Level3 extends GameLevel{
    private MysteryBlock mysteryBlock;
    private Player player;
    private Jetpack jp;
    private ArrayList<Coin> list = new ArrayList<>(); // list of coins
    private ArrayList<Goomba> list2 = new ArrayList<>(); // list of goomba enemies
    public Level3(Game game) {
        super(game);
        // update player for the current level
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
        // brick platforms
        Shape brickShape = new BoxShape(0.5f,0.5f);
        StaticBody brick0 = new StaticBody(this, brickShape);
        brick0.addImage(new BodyImage("data/brick.png",1f));
        brick0.setPosition(new Vec2(-1,-3));
        StaticBody brick = new StaticBody(this, brickShape);
        brick.addImage(new BodyImage("data/brick.png",1f));
        brick.setPosition(new Vec2(0,-3));

        StaticBody brick1 = new StaticBody(this, brickShape);
        brick1.addImage(new BodyImage("data/brick.png",1f));
        brick1.setPosition(new Vec2(-8,3));

        StaticBody brick2 = new StaticBody(this, brickShape);
        brick2.addImage(new BodyImage("data/brick.png",1f));
        brick2.setPosition(new Vec2(-7,3));

        StaticBody brick3 = new StaticBody(this, brickShape);
        brick3.addImage(new BodyImage("data/brick.png",1f));
        brick3.setPosition(new Vec2(-10,6));

        StaticBody brick4 = new StaticBody(this, brickShape);
        brick4.addImage(new BodyImage("data/brick.png",1f));
        brick4.setPosition(new Vec2(-11,6));

        StaticBody brick5 = new StaticBody(this, brickShape);
        brick5.addImage(new BodyImage("data/brick.png",1f));
        brick5.setPosition(new Vec2(10,8));

        StaticBody brick6 = new StaticBody(this, brickShape);
        brick6.addImage(new BodyImage("data/brick.png",1f));
        brick6.setPosition(new Vec2(11,8));
        // pipe to next level
        mysteryBlock = new MysteryBlock(this);
        mysteryBlock.setPosition(new Vec2(-10,-5));
        player.addCollisionListener(new MysteryCollision2(this, player, mysteryBlock));
        jp = new Jetpack(this);
        jp.setPosition(new Vec2(-10, -5));
        jp.addCollisionListener(new PowerUp(player));

        CoinCollection coinCollection = new CoinCollection(this, game, player);
        player.addCollisionListener(coinCollection);
        for (int a=0; a<2; a++){
            coinGen(a+1,a+2);
            coinGen(a+8, a+8);
            coinGen(a-6, a+7);
        }

        Goomba goomba = new Goomba(this);
        goomba.setPosition(new Vec2(0, -11));
        GoombaStomp goombaStomp = new GoombaStomp(player, goomba); //change this to for each instance of patroller
        player.addCollisionListener(goombaStomp);

        Goomba goomba1 = new Goomba(this);
        goomba1.setPosition(new Vec2(11,10));
        GoombaStomp goombaStomp1 = new GoombaStomp(player, goomba1);
        player.addCollisionListener(goombaStomp1);

        list2.add(goomba);
        list2.add(goomba1);
    }

    /**
     *
     * @return true if 6 coins are collected, next level starts
     */
    @Override
    public boolean isComplete() {
        if(player.getScore()==6) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param i specific patroller in the list
     * @return
     */
    @Override
    public Goomba getPatroller(int i) {
        return list2.get(i);
    }

    @Override
    public void showFlower() {

    }

    /**
     *
     * @return jetpack
     */
    public Jetpack getJetpack(){
        return jp;
    }

    /**
     *  reveals jetpack
     */
    public void showJetpack(){
        float x = getJetpack().getPosition().x;
        float y = getJetpack().getPosition().y;
        if (mysteryBlock.getHit()==1){
            jp.setPosition(new Vec2(x,y+1));
        }
    }
    /**
     * get the current level number
     */
    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    public Koopa getKoopa(int i) {
        return null;
    }
    /**
     * generate coins
     */
    public void coinGen(float a, float b){
        Coin coin1 = new Coin(this);
        coin1.setPosition(new Vec2(a +1, b +1));
        list.add(coin1);
    }

}
