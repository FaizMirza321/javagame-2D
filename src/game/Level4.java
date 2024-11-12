package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
/**
 * level 4 class
 */
public class Level4 extends GameLevel{
    private Pipe pipe;
    private ArrayList<Goomba> list2 = new ArrayList<>();
    private ArrayList<Koopa> list3 = new ArrayList<>();
    public Level4(Game game) {
        super(game);
        // update player
        Player player = getPlayer();
        player.setPosition(new Vec2(-15f, 8f));

        // ground
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

        Shape shape1 = new BoxShape(6.7f,1f);
        StaticBody groundup = new StaticBody(this, shape1);
        StaticBody groundup1 = new StaticBody(this, shape1);
        StaticBody groundup2 = new StaticBody(this, shape1);

        groundup.addImage(new BodyImage("data/ground.png", 2.2f));
        groundup1.addImage(new BodyImage("data/ground.png", 2.2f));
        groundup2.addImage(new BodyImage("data/ground.png", 2.2f));

        groundup.setPosition(new Vec2(-14f, 4.5f));
        groundup1.setPosition(new Vec2(-1f,4.5f));
        groundup2.setPosition(new Vec2(10.2f,4.5f));

        // creating suspended platforms
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

        Shape platformShape = new BoxShape(2.1f,0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.addImage(new BodyImage("data/platform.png", 5f));
        platform1.setPosition(new Vec2(-15, 7));
        // pipe to exit
        pipe = new Pipe(this);
        pipe.setPosition(new Vec2(15,-9));
        // koopa enemy
        Koopa koopa = new Koopa(this);
        koopa.setPosition(new Vec2(-2,5));
        KoopaShell koopaShell = new KoopaShell(player, koopa);
        player.addCollisionListener(koopaShell);
        list3.add(koopa); // koopa list
        // creating the Goomba enemies in the level
        Goomba goomba = new Goomba(this);
        goomba.setPosition(new Vec2(0, 5));
        GoombaStomp goombaStomp = new GoombaStomp(player, goomba);
        player.addCollisionListener(goombaStomp);

        Goomba goomba1 = new Goomba(this);
        goomba1.setPosition(new Vec2(5,5));
        GoombaStomp goombaStomp1 = new GoombaStomp(player, goomba1);
        player.addCollisionListener(goombaStomp1);

        Goomba goomba2 = new Goomba(this);
        goomba2.setPosition(new Vec2(7,5));
        GoombaStomp goombaStomp2 = new GoombaStomp(player, goomba2);
        player.addCollisionListener(goombaStomp2);

        koopa.addCollisionListener(koopaShell);
        list2.add(goomba);
        list2.add(goomba1);
        list2.add(goomba2);
    }

    @Override
    public boolean isComplete() {
        return list2.isEmpty(); // checks if there are any goombas left
    }

    @Override
    public Goomba getPatroller(int i) {
        return list2.get(i); // fetch a specific goomba in the list
    }

    /**
     *
     * @param i specific koopa in the list
     * @return specific koopa
     */
    @Override
    public Koopa getKoopa(int i){
        return list3.get(i);
    }
    @Override
    public void showFlower() {

    }

    @Override
    public void showJetpack() {

    }

    @Override
    public int getLevel() {
        return 4;
    }

    /**
     *
     * @param goomba the goomba which has been destroyed
     *  removes destroyed goombas
     */
    public void reduceList(Goomba goomba){
        list2.remove(goomba); // removes an enemy from the goomba list when they are destroyed
    }
}
