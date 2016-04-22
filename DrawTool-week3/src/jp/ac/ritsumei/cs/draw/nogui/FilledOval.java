
package jp.ac.ritsumei.cs.draw.nogui;

/**
 * Represents a filled oval.
 */
public class FilledOval extends Oval {
    
    /**
     * The constant value of the name of this figure.
     */
    public static final String name = "FilledOval";
    
    /**
     * Creates a new, empty object.
     */
    public FilledOval() {
        super();
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Figure createClone() {
    	Figure foval = new FilledOval();
    	foval.setStart(startX, startY);
    	foval.setEnd(endX, endY);
    	return foval;
    }
}
