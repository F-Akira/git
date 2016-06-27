
package jp.ac.ritsumei.cs.draw;

import java.util.*;
import java.awt.Color;
import java.io.*;

/**
 * Manages figures drawn on the canvas.
 */
public class FigureManager {
    
    /**
     * The collection of all figures managed by this manager.
     */
    private List<Figure> figures;
    
    /**
     * Creates a figure manager.
     */
    public FigureManager() {
        figures = new LinkedList<Figure>();
    }
    
    /**
     * Obtains the collection of all figures managed by this manager.
     * @return
     */
    public List<Figure> getFigures() {
        return figures;
    }
    
    /**
     * Adds a given figure into the collection managed by this manager.
     * @param figure the figure to be added
     */
    public void add(Figure figure) {
        figures.add(figure);
    }
    
    /**
     * Removes a given figure from the collection managed by this manager.
     * @param figure the figure to be removed
     */
    public void remove(Figure figure) {
        figures.remove(figure);
    }
    
    /**
     * Removes all the figures in the collection managed by this manager.
     */
    public void clear() {
        figures.clear();
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
        Figure figure = createFigure(kind);
        if (figure != null) {
            figure.setStart(x1, y1);
            figure.setEnd(x2, y2);
        }
        return figure;
    }
    
    public Figure createFigure(String kind, int x1, int y1, int x2, int y2, String c) {
        Figure figure = createFigure(kind);
        if (figure != null) {
            figure.setStart(x1, y1);
            figure.setEnd(x2, y2);
            figure.setColor(getColorKind(c), c);
        }
        return figure;
    }
    
    Color getColorKind(String str) {
    	switch (str) {
    	case "red"	: return Color.red;
    	case "green": return Color.green;
    	case "blue"	: return Color.blue;
    	default		: return Color.blue;
    	}
    }
    
    /**
     * Stores information about all the figures into a file
     * @param filename the name of a file that intends to store the information
     * @return <code>true</code> if the storing was successful, otherwise <code>false</code>
     */
    public boolean store(String filename) {
    	try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {    		
    		figures.forEach(fig -> pw.println(fig.getName() + " "
    										  + fig.startX + " "
    										  + fig.startY + " "
    										  + fig.endX + " "
    										  + fig.endY + " "
    										  + fig.colorS));
    		return true;
    	}
    	catch (IOException e) {
    		System.err.println("Cannot Write: " + filename);
    	}
        return false;
    }
    
    /**
     * Loads information about figures stored in a file
     * @param filename the name of the file storing the information
     * @return <code>true</code> if the loading was successful, otherwise <code>false</code>
     */
    public boolean load(String filename) {
    	try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
    		String line;
    		while ((line = br.readLine()) != null) {
    			StringTokenizer st = new StringTokenizer(line);
    			String name = st.nextToken();
    			int x1 = Integer.parseInt(st.nextToken());
    			int y1 = Integer.parseInt(st.nextToken());
    			int x2 = Integer.parseInt(st.nextToken());
    			int y2 = Integer.parseInt(st.nextToken());
    			String color = st.nextToken();
    			
    			Figure figure = createFigure(name, x1, y1, x2, y2, color);
    			add(figure);
    		}
    		return true;
    	}
    	catch (FileNotFoundException e){
    		System.out.println("File Not Found: " + filename);
    	}
    	catch (IOException e) {
    		System.out.println("Cannot read: " + filename);
    	}
        return false;
    }
}
