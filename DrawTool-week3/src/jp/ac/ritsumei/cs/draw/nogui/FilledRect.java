
package jp.ac.ritsumei.cs.draw.nogui;

/**
 * Represents a filled rectangle.
 */
public class FilledRect extends Rect {
    
    /**
     * The constant value of the name of this figure.
     */
    public static final String name = "FilledRect";
    
    /**
     * Creates a new, empty object.
     */
    public FilledRect() {
        super();
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Figure createClone() {
    	Figure frect = new FilledRect();
    	frect.setStart(startX, startY);
    	frect.setEnd(endX, endY);
    	return frect;
    }
}
