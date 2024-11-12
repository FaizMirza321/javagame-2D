package game;

import city.cs.engine.*;
/**
 * Defines a fireflower body
 */
public class FireFlower extends StaticBody {
    private static final BodyImage image = new BodyImage("data/flower.png", 1f);
    private static final Shape flowerShape = new CircleShape(0.1f);
    /**
     *  Constructor of a fireflower
     */
    public FireFlower(GameLevel w) {
        super(w, flowerShape);
        addImage(image);
    }
}
