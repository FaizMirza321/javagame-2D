package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
/**
 *  mystery block collision listener for jetpack
 */
public class MysteryCollision2 implements CollisionListener {
    private Player player;
    private MysteryBlock block;
    private GameLevel level;

    public MysteryCollision2(GameLevel l, Player p, MysteryBlock m){
        level = l;
        player = p;
        block = m;
    }
    /**
     *  checks for collisions with the bottom of the block
     *  reveals the jetpack and empties the block
     */
    @Override
    public void collide(CollisionEvent e) {
        float y = player.getPosition().y;
        float y2 = block.getPosition().y;
        if (e.getOtherBody() instanceof MysteryBlock && y2>y){
            block.powerUp();
            level.showJetpack();

        }
    }
}