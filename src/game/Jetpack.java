package game;

import city.cs.engine.*;
/**
 * jetpack class
 */
public class Jetpack extends StaticBody {
    private static final BodyImage image = new BodyImage("data/jetpack.png", 1f);
    private static final Shape jetShape = new CircleShape(0.1f); // jetpack shape
    /**
     * creates a jetpack
     */
    public Jetpack(GameLevel w) {
        super(w, jetShape);
        addImage(image);
    }
}
