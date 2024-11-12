package game;

import city.cs.engine.Body;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
/**
 * Increases the score of the player when player and coin collide
 * The next level is loaded if the level requires a set number of coins collected
 */
public class CoinCollection implements CollisionListener {
    private Player player;
    private GameLevel level;
    private Game game;

    /**
     * creates an instance of the Coin collision listener
     *
     */
    public CoinCollection(GameLevel l, Game g, Player p){
        level = l;
        game = g;
        player = p;

    }

    /**
     *
     *  @param e gets the collision of the player and coin
     *  during a coin collision, the coin is destroyed and player score increases
     *  if the score is the number needed to progress, the next level is loaded
     */
    @Override
    public void collide(CollisionEvent e) {
        Body other = e.getOtherBody();
        if (other instanceof Coin){
            player.increaseScore(); // score increased
            other.destroy(); // removes coin
            if(level.isComplete()){
                game.goToNextLevel(); // next level loaded
            }
        }
    }
}
