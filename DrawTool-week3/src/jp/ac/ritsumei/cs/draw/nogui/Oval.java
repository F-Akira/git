
package jp.ac.ritsumei.cs.draw.nogui;

/**
 * Represents an oval.
 */
public class Oval extends Figure {
    
    /**
     * The constant value of the name of this figure.
     */
    public static final String name = "Oval";
    
    /**
     * Creates a new, empty object.
     */
    public Oval() {
        super();
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Figure createClone() {
    	Figure oval = new Oval();
    	oval.setStart(startX, startY);
    	oval.setEnd(endX, endY);
        return oval;
    }
}
