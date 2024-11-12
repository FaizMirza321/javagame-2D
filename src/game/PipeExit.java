package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
/**
 *  makes a pipe exit
 */
public class PipeExit implements CollisionListener {
    private Game game;
    private GameLevel level;
    private Player player;
    /**
     * makes a pipe exit
     */
    public PipeExit(Game g, GameLevel l, Player p){
        level = l;
        game = g;
        player = p;

    }
    /**
     * checks for collisions with the top of the pipe and starts the next level if true
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Pipe && player.getPosition().y>-9 && level.isComplete()){
            game.goToNextLevel();
        }
        if(level.getLevel()==4 && e.getOtherBody() instanceof Pipe){
            game.goToNextLevel();
        }
    }
}
