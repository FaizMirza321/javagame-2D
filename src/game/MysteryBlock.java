package game;

import city.cs.engine.*;

/**
 *  mystery block class
 */
public class MysteryBlock extends StaticBody {
    private static final Shape blockShape = new BoxShape(0.5f,0.5f);
    private static final BodyImage image = new BodyImage("data/mysteryBlock.png", 2f);
    private int hit;
    /**
     * creates a mystery block
     */
    public MysteryBlock(GameLevel level) {
        super(level, blockShape);
        addImage(image);
        hit = 0;
    }
    /**
     * updates block to empty image when hit from the bottom
     */
    public void powerUp(){
        removeAllImages();
        addImage(new BodyImage("data/emptyBlock.png", 1));
        hit = 1;
    }
    /**
     * @return current status of block
     * hit is 1 when power-up is released and player collides with the bottom
     */
    public int getHit(){
        return hit;
    }
}
