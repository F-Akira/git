
package jp.ac.ritsumei.cs.draw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Implements a canvas on which figures are drawn.
 */
public class DrawCanvas extends JPanel implements MouseListener, MouseMotionListener {
    
    private static final long serialVersionUID = 6136134230542448464L;
    
    /**
     * The application for a drawing tool.
     */
    private DrawTool tool;
    
    /**
     * The selector binding to this canvas.
     */
    private FigureSelector selector;
    
    /**
     * The figure manager binding to this canvas.
     */
    private FigureManager figureManager;
    
    /**
     * A figure a user is currently drawing.
     */
    private Figure currentFigure = null;
    
    /**
     * A flag indicating there exits a figure that was not saved.
     */
    private boolean changed;
    
    /**
     * An auto saver of information about figures.
     */
    private AutoSave autoSave;
    
    private String filename;
    
    /**
     * Creates a new canvas on which a figure is drawn.
     * @param tool the application for a drawing tool
     * @param selector the selector for a figure currently drawn
     */
    DrawCanvas(DrawTool tool, FigureSelector selector) {
        this.tool = tool;
        this.selector = selector;
        figureManager = new FigureManager();
        
        setChanged(false);
        
        setBackground(Color.white);
        setPreferredSize(new Dimension(700, 500));
        
        addMouseListener(this);
        addMouseMotionListener(this);
        
        autoSave = new AutoSave(this);
        autoSave.start();
    }
    
    /**
     * Tests if there exits a figure that was not saved.
     * @return <code>true</code> if there exits a figure that was not saved, otherwise <code>false</code>
     */
    boolean hasChanged() {
        return changed;
    }
    
    /**
     * Sets the value of the flag indicating there exits a figure that was not saved.
     * @param bool <code>true</code> if there exits a figure that was not saved, otherwise <code>false</code>
     */
    synchronized void setChanged(boolean bool) {
        changed = bool;
    }
    
    /**
     * Returns the manager that manages figures drawn on the canvas.
     * @return the figure manager binding to this canvas.
     */
    FigureManager getFigureManager() {
        return figureManager;
    }
    
    /**
     * Returns the figure a user is currently drawing.
     * @return the figure currently drawn
     */
    Figure getCurrentFigure() {
        return currentFigure;
    }
    
    /**
     * Sets the figure a user is currently drawing.
     * @param figure the figure currently drawn
     */
    synchronized void setCurrentFigure(Figure figure) {  	
        currentFigure = figure;
        
        notifyAll();
    }
    
    void setFileName(String name) {
    	filename = name;
    }
    
    String getFilename() {
    	return filename;
    }
    
    /**
     * Automatically saves figure information.
     */
    synchronized void autoSave() {
        DrawMenuBar menuBar = tool.getDrawMenuBar();
        if (menuBar != null) {
            
        	while (getCurrentFigure() != null) {
        		try {
        			wait();
        		} catch (InterruptedException e) { }
        	}
        	
            if (hasChanged()) {
                menuBar.saveTempFile();
            }
        }
    }
    
    /**
     * Terminates a thread that automatically saves figure information.
     */
    void stopAutoSave() {
        autoSave.terminate();
    }
    
    /**
     * Do anything when a mouse button has been pressed on a component.
     * @param e an event indicating that a mouse action occurred in a component
     */
    public void mousePressed(MouseEvent e) {
        Graphics g = getGraphics();
        
        String kind = selector.getFigureKind();
        setCurrentFigure(figureManager.createFigure(kind));
        getCurrentFigure().setStart(e.getX(), e.getY());
        getCurrentFigure().setEnd(e.getX(), e.getY());
        getCurrentFigure().setColor(selector.getColorKind(), selector.getColorName());
        drawRubber(g, getCurrentFigure());
    }
    
    /**
     * Do anything when a mouse button has been released on a component.
     * @param e an event indicating that a mouse action occurred in a component
     */
    public void mouseReleased(MouseEvent e) {
        Graphics g = getGraphics();
        drawRubber(g, getCurrentFigure());
        
        if (getCurrentFigure() != null) {
            if (getCurrentFigure().getStartX() != e.getX() || getCurrentFigure().getTop() != e.getY()) {
                getCurrentFigure().setEnd(e.getX(), e.getY());
                figureManager.add(getCurrentFigure());
                drawFigure(g, getCurrentFigure());
                setChanged(true);
                
                repaint();
            }
            setCurrentFigure(null);
        }
    }
    
    /**
     * Do nothing when the mouse enters a component.
     * @param e an event indicating that a mouse action occurred in a component
     */
    public void mouseEntered(MouseEvent e) {
    }
    
    /**
     * Do nothing when the mouse exits a component.
     * @param e an event indicating that a mouse action occurred in a component
     */
    public void mouseExited(MouseEvent e) {
    }
    
    /**
     * Do nothing when the mouse button has been clicked (pressed and released) on a component.
     * @param e an event indicating that a mouse action occurred in a component
     */
    public void mouseClicked(MouseEvent e) {
    }
    
    /**
     * Do anything when a mouse button is pressed on a component and then dragged.
     * @param e an event indicating that a mouse action occurred in a component
     */
    public void mouseDragged(MouseEvent e) {
        if (getCurrentFigure() != null) {
            Graphics g = getGraphics();
            
            drawRubber(g, getCurrentFigure());
            getCurrentFigure().setEnd(e.getX(), e.getY());
            drawRubber(g, getCurrentFigure());
        }
    }
    
    /**
     * Do noting when the mouse cursor has been moved onto a component but no buttons have been pushed.
     * @param e an event indicating that a mouse action occurred in a component
     */
    public void mouseMoved(MouseEvent e) {
    }
    
    /**
     * Draws a given figure.
     * @param g the graphics context
     * @param figure the figure to be drawn
     */
    private void drawFigure(Graphics g, Figure figure) {
        Color c = figure.SHAPE_COLOR;
        g.setPaintMode();
        figure.draw(g);
        g.setColor(c);
    }
    
    /**
     * Draws a rubber representing a given figure while drawing it.
     * @param g the graphics context
     * @param figure the figure represented by the drawn rubber
     */
    private void drawRubber(Graphics g, Figure figure) {
        Color c = g.getColor();
        g.setXORMode(getBackground());
        figure.drawRubber(g);
        g.setColor(c);
    }
    
    /**
     * Draws all the figures on the canvas and the grabbed figure when the repaint is occurred.
     * @param g the graphics context
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Figure figure : figureManager.getFigures()) {
            drawFigure(g, figure);
        }
    }
}
