
package jp.ac.ritsumei.cs.draw;

import java.awt.*;

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
        Figure figure = new Rect();
        figure.setStart(startX, startY);
        figure.setEnd(endX, endY);
        return figure;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(SHAPE_COLOR);
        g.drawRect(getLeft(), getTop(), getWidth(), getHeight());
    }
    
    @Override
    public void drawRubber(Graphics g) {
        g.setColor(RUBBER_COLOR);
        g.drawRect(getLeft(), getTop(), getWidth(), getHeight());
    }
}
