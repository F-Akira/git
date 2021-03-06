
package jp.ac.ritsumei.cs.draw.nogui;

/**
 * Represents a line.
 */
public class Line extends Figure {
    
    /**
     * The constant value of the name of this figure.
     */
    public static final String name = "Line";
    
    /**
     * Creates a new, empty object.
     */
    public Line() {
        super();
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Figure createClone() {
    	Figure line = new Line();
    	line.setStart(startX, startY);
    	line.setEnd(endX, endY);
        return line;
    }
}
