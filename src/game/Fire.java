package game;

import city.cs.engine.*;
/**
 * Defines the fire object
 *
 */
public class Fire extends DynamicBody {
    private static final Shape fireShape = new CircleShape(0.5f);
    private static final BodyImage image =
            new BodyImage("data/fire.png", 1f);
    /**
     *  creates an instance of the fire object with 0 gravity
     */
    public Fire(World w) {
        super(w, fireShape);
        addImage(image);
        setGravityScale(0);
        
    }
}
