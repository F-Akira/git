
package jp.ac.ritsumei.cs.draw.nogui;

import java.util.*;

public class FigureManager {
    
    /**
     * The collection of all figures managed by this manager.
     */
    private List<Figure> figures = new LinkedList<Figure>();
    
    /**
     * Creates a figure manager.
     */
    public FigureManager() {
    }
    
    /**
     * Obtains the collection of all figures managed by this manager.
     * @return the collection of all figures
     */
    public List<Figure> getFigures() {
    	// Change code here
        return null;
    }
    
    /**
     * Adds a given figure into the collection managed by this manager.
     * @param figure the figure to be added
     */
    public void add(Figure figure) {
    	// Insert code here
    }
    
    /**
     * Removes a given figure from the collection managed by this manager.
     * @param figure the figure to be removed
     */
    public void remove(Figure figure) {
    	// Insert code here
    }
    
    /**
     * Removes all the figures in the collection managed by this manager.
     */
    public void clear() {
    	// Insert code here
    }
    
    /**
     * Creates a new figure according to a given kind.
     * @param kind the name indicating the kind of the figure to be created
     * @return the created figure, or <code>null</code> if the given kind is invalid
     */
    public Figure createFigure(String kind) {
        if (kind.equals(Line.name)) {
            return new Line();
        } else if (kind.equals(Rect.name)) {
            return new Rect();
        } else if (kind.equals(Oval.name)) {
            return new Oval();
        } else if (kind.equals(FilledRect.name)) {
            return new FilledRect();
        } else if (kind.equals(FilledOval.name)) {
            return new FilledOval();
        }
        return null;
    }
    
    /**
     * Creates a new figure.
     * @param kind the name indicating the kind of the figure
     * @param x1 the x-coordinate of the start point of the figure
     * @param y1 the y-coordinate of the start point of the figure
     * @param x2 the x-coordinate of the end point of the figure
     * @param y2 the y-coordinate of the end point of the figure
     * @return the created figure, or <code>null</code> if the given kind is invalid
     */
    public Figure createFigure(String kind, int x1, int y1, int x2, int y2) {
    	// Change code here
        return null;
    }
    
    /**
     * Stores information about all the figures into a file
     * @param filename the name of a file that intends to store the information
     * @return <code>true</code> if the storing was successful, otherwise <code>false</code>
     */
    public boolean store(String filename) {
    	// Change code here
        return false;
    }
    
    /**
     * Loads information about figures stored in a file
     * @param filename the name of the file storing the information
     * @return <code>true</code> if the loading was successful, otherwise <code>false</code>
     */
    public boolean load(String filename) {
    	// Change code here
        return false;
    }
}
