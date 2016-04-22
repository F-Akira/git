
package jp.ac.ritsumei.cs.draw.nogui;

/**
 * Represents a figure.
 */
public abstract class Figure {
    
    /**
     * The x-coordinate of the start point of this figure.
     */
    protected int startX;
    
    /**
     * The y-coordinate of the start point of this figure.
     */
    protected int startY;
    
    /**
     * The x-coordinate of the end point of this figure.
     */
    protected int endX;
    
    /**
     * The y-coordinate of the end point of this figure.
     */
    protected int endY;
    
    /**
     * Creates a new, empty object.
     */
    public Figure() {
    }
    
    /**
     * Sets the start point of this figure.
     * @param x the x-coordinate of the start point
     * @param y the y-coordinate of the start point
     */
    public void setStart(int x, int y) {
    	startX = x;
    	startY = y;
    }
    
    /**
     * Sets the end point of this figure.
     * @param x the x-coordinate of the end point
     * @param y the y-coordinate of the end point
     */
    public void setEnd(int x, int y) {
    	endX = x;
    	endY = y;
    }
    
    /**
     * Returns the left-most x-coordinate of this figure
     * @return the left-most x-coordinate
     */
    public int getLeft() {
    	return Math.min(startX, endX);
    }
    
    /**
     * Returns the right-most x-coordinate of this figure
     * @return the right-most x-coordinate
     */
    public int getRight() {
    	return Math.max(startX, endX);
    }
    
    /**
     * Returns the top-most y-coordinate of this figure
     * @return the top-most y-coordinate
     */
    public int getTop() {
        return Math.min(startY, endY);
    }
    
    /**
     * Returns the bottom-most y-coordinate of this figure
     * @return the bottom-most y-coordinate
     */
    public int getBottom() {
        return Math.max(startY, endY);
    }
    
    /**
     * Returns the width of the least rectangle enclosing this figure.
     * @return the width
     */
    public int getWidth() {
        return Math.abs(startX - endX);
    }
    
    /**
     * Returns the height of the least rectangle enclosing this figure.
     * @return the height
     */
    public int getHeight() {
        return Math.abs(startY - endY);
    }
    
    /**
     * Obtains information about the z- and y-coordinates.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the information string
     */
    private String getPositionInfo(int x, int y) {
        return "(" + x + "," + y +")"; 
    }
    
    /**
     * Obtains information about this figure.
     * @return the information string
     */
    public String getInfo() {
        return getName() + ": " + getPositionInfo(getLeft(), getTop()) + "-" + getPositionInfo(getRight(), getBottom());
    }
    
    /**
     * Tests of this figure equals to a given one.
     * @param figure the figure object to be compared
     * @return <code>true</code> if this figure equals to the given one, otherwise <code>false</code>
     */
    private boolean equals(Figure figure) {
        return getName().equals(figure.getName()) && isOverlapped(figure);
    }
    
    /**
     * Tests of this figure equals to a given one.
     * @param figure the figure object to be compared
     * @return <code>true</code> if this figure equals to the given one, otherwise <code>false</code>
     */
    public boolean equals(Object obj) {
        if (obj instanceof Figure) {
            Figure figure = (Figure)obj;
            return equals(figure);
        }
        return false;
    }
    
    /**
     * Returns the hash code for this figure.
     * @return the hash code
     */
    public int hashCode() {
        return getName().hashCode();
    }
    
    /**
     * Checks if this figure overlaps a given one.
     * @param figure the figure object to be checked
     * @return <code>true</code> if this figure overlaps the given one, otherwise <code>false</code>
     */
    public boolean isOverlapped(Figure figure) {
        return getLeft() == figure.getLeft() && getTop() == figure.getTop() &&
               getRight() == figure.getRight() && getBottom() == figure.getBottom();
    }
    
    /**
     * Returns the name of this figure.
     * @return the name
     */
    public abstract String getName();
    
    /**
     * Creates a clone of this figure and returns it.
     * @return the clone object
     */
    public abstract Figure createClone();
}
