
package jp.ac.ritsumei.cs.draw;

import java.awt.*;

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
        Figure figure = new FilledRect();
        figure.setStart(startX, startY);
        figure.setEnd(endX, endY);
        return figure;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(SHAPE_COLOR);
        g.fillRect(getLeft(), getTop(), getWidth(), getHeight());
    }
}
