package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
/**
 *  base game level class
 */
public abstract class GameLevel extends World {
    private Player player;

    /**
     * Base class for all levels to inherit
     * @param game references the game with the level
     */
    public GameLevel(Game game) {
        player = new Player(this);
        player.addCollisionListener(new PipeExit(game,this, player));
        Shape shape = new BoxShape(1,14);
        Shape shape2 = new BoxShape(20,1);
        //Barriers that cannot be seen by the player to remove fireballs that miss and to stop the player leaving the map
        StaticBody wall = new StaticBody(this, shape);
        wall.setPosition(new Vec2(-21f, -1));
        StaticBody wall2 = new StaticBody(this, shape);
        wall2.setPosition(new Vec2(21f, -1));
        StaticBody ceiling = new StaticBody(this, shape2);
        ceiling.setPosition(new Vec2(0, 15));
    }

    /**
     *
     * @return returns the player for the current level
     */
    public Player getPlayer() {
        return player;
    }
    /**
     *  defines the level progression condition
     */
    public abstract boolean isComplete();
    /**
     *  @param i specific patroller in the list
     */
    public abstract Goomba getPatroller(int i);
    /**
     *  makes the flower visible
     */
    public abstract void showFlower();
    /**
     * makes the jetpack visible
     */
    public abstract void showJetpack();
    /**
     *  gets the current level
     */
    public abstract int getLevel();
    /**
     *  @param goomba the goomba which has been destroyed
     *  removes destroyed goombas
     */
    public void reduceList(Goomba goomba) {
    }
    /**
     * @param i specific koopa in the list
     */
    public abstract Koopa getKoopa(int i);
}


