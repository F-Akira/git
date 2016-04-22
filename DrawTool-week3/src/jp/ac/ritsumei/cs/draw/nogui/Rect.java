
package jp.ac.ritsumei.cs.draw.nogui;

/**
 * Represents a rectangle.
 */
public class Rect extends Figure {
    
    /**
     * The constant value of the name of this figure.
     */
    public static final String name = "Rect";
    
    /**
     * Creates a new, empty object.
     */
    public Rect() {
        super();
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Figure createClone() {
    	Figure rect = new Rect();
    	rect.setStart(startX, startY);
    	rect.setEnd(endX, endY);
        return rect;
    }
}
