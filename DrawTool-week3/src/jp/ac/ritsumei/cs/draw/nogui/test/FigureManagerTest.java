/*
 * Test code for the class FigureManager
 */

package jp.ac.ritsumei.cs.draw.nogui.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import jp.ac.ritsumei.cs.draw.nogui.*;

public class FigureManagerTest {
    
    @Test
    public void testAddFigure() {
        FigureManager figureManager = defaultInit();
        List<Figure> figures = figureManager.getFigures();
        
        assertEquals(3, figures.size());
        assertTrue(figures.get(0).getName().equals("Line"));
        assertTrue(figures.get(1).getName().equals("Rect"));
        assertTrue(figures.get(2).getName().equals("Oval"));
    }
    
    @Test
    public void testRemoveFigure() {
        FigureManager figureManager = defaultInit();
        
        List<Figure> figures = figureManager.getFigures();
        figures.remove(figures.get(1));
        
        assertEquals(2, figures.size());
        assertTrue(figures.get(0).getName().equals("Line"));
        assertTrue(figures.get(1).getName().equals("Oval"));
    }
    
    @Test
    public void testClearFigure() {
        FigureManager figureManager = defaultInit();
        
        figureManager.clear();
        List<Figure> figures = figureManager.getFigures();
        
        assertEquals(0, figures.size());
    }
    
    @Test
    public void testEqual1() {
        FigureManager figureManager = new FigureManager();
        Figure line1 = figureManager.createFigure(Line.name, 100, 120, 110, 150);
        Figure line2 = figureManager.createFigure(Line.name, 100, 120, 110, 150);
        
        assertTrue(line1.equals(line2));
    }
    
    @Test
    public void testEqual2() {
        FigureManager figureManager = new FigureManager();
        Figure rect1 = figureManager.createFigure(Rect.name, 100, 120, 110, 150);
        Figure rect2 = figureManager.createFigure(Rect.name, 110, 150, 100, 120);
        
        assertTrue(rect1.equals(rect2));
    }
    
    @Test
    public void testEqual3() {
        FigureManager figureManager = new FigureManager();
        Figure rect1 = figureManager.createFigure(Rect.name, 100, 120, 110, 150);
        Figure line2 = figureManager.createFigure(Line.name, 100, 120, 110, 150);
        
        assertTrue(!rect1.equals(line2));
    }
    
    @Test
    public void testClone() {
        FigureManager figureManager = new FigureManager();
        Figure line1 = figureManager.createFigure(Line.name, 100, 120, 110, 150);
        Figure line2 = line1.createClone();
        
        assertTrue(line1.equals(line2));
    }
    
    @Test
    public void testStoreLoad() {
        FigureManager figureManager = defaultInit();
        
        List<Figure> figures = figureManager.getFigures();
        Figure figure0 = figures.get(0);
        Figure figure1 = figures.get(1);
        Figure figure2 = figures.get(2);
        
        figureManager.store("storeLoadTest.txt");
        figureManager.load("storeLoadTest.txt");
        
        assertTrue(figures.get(0).equals(figure0));
        assertTrue(figures.get(1).equals(figure1));
        assertTrue(figures.get(2).equals(figure2));
    }
    
    private FigureManager defaultInit() {
        FigureManager figureManager = new FigureManager();
        Figure line = figureManager.createFigure(Line.name, 100, 120, 150, 110);
        figureManager.add(line);
        Figure rect = figureManager.createFigure(Rect.name, 90, 140, 0, 30);
        figureManager.add(rect);
        Figure oval = figureManager.createFigure(Oval.name, 50, 80, 210, 130);
        figureManager.add(oval);
        return figureManager;
    }
}
