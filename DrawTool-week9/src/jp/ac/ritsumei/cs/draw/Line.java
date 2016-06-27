
package jp.ac.ritsumei.cs.draw;

import java.awt.*;

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
        Figure figure = new Line();
        figure.setStart(startX, startY);
        figure.setEnd(endX, endY);
        return figure;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(SHAPE_COLOR);
        g.drawLine(startX, startY, endX, endY);
    }
    
    @Override
    public void drawRubber(Graphics g) {
        g.setColor(RUBBER_COLOR);
        g.drawLine(startX, startY, endX, endY);
    }
}
