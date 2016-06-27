
package jp.ac.ritsumei.cs.draw;

import java.awt.*;

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
        Figure figure = new Oval();
        figure.setStart(startX, startY);
        figure.setEnd(endX, endY);
        return figure;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(SHAPE_COLOR);
        g.drawOval(getLeft(), getTop(), getWidth(), getHeight());
    }
    
    @Override
    public void drawRubber(Graphics g) {
        g.setColor(RUBBER_COLOR);
        g.drawOval(getLeft(), getTop(), getWidth(), getHeight());
    }
}
