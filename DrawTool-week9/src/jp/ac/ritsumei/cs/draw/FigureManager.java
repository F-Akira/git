
package jp.ac.ritsumei.cs.draw;

import java.util.*;
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
    
    /**
     * Stores information about all the figures into a file
     * @param filename the name of a file that intends to store the information
     * @return <code>true</code> if the storing was successful, otherwise <code>false</code>
     */
    public boolean store(String filename) {
        PrintWriter pw = null;
        
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            for (Figure figure : figures) {
                StringBuilder sb = new StringBuilder();
                sb.append(figure.getName() + " ");
                sb.append(String.valueOf(figure.startX) + " ");
                sb.append(String.valueOf(figure.startY)+ " ");
                sb.append(String.valueOf(figure.endX) + " ");
                sb.append(String.valueOf(figure.endY));
                
                pw.println(sb.toString());
            }
        } catch (IOException e) {
            System.err.println("Cannot write: " + filename);
        } finally {
            if (pw != null) {
                pw.close();
            } else {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Loads information about figures stored in a file
     * @param filename the name of the file storing the information
     * @return <code>true</code> if the loading was successful, otherwise <code>false</code>
     */
    public boolean load(String filename) {
        figures.clear();
        
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                String name = st.nextToken();
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                
                Figure figure = createFigure(name, x1, y1, x2, y2);
                add(figure);
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found: " + filename);
            return false;
        } catch (IOException e) {
            System.err.println("Cannot read: " + filename);
            return false;
        } finally {
            try {
                if (br != null) {
                    br.close();
                } else {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }
}
