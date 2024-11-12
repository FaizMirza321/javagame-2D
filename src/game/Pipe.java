package game;

import city.cs.engine.*;
/**
 *  pipe class
 */
public class Pipe extends StaticBody{
    private static final BodyImage image = new BodyImage("data/pipe.png", 4f); // pipe image
    private static final Shape pipeShape = new BoxShape(1,1.5f);
    public Pipe(GameLevel w) {
        super(w, pipeShape);
        addImage(image);
    }
}
